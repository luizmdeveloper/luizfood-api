package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.Estado;
import com.luizmariodev.luizfood.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}

	public Estado atualizar(Long id, Estado estado) {
		Estado estadoAtual = buscarEstadoPorCodigo(id);
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoRepository.salvar(estadoAtual);
	}
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.excluir(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Estado de código %d, não foi encontrado", estadoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado com o código %d, não pode ser excluída", estadoId));
		}
	}

	private Estado buscarEstadoPorCodigo(Long id)  {
		Estado estado = estadoRepository.buscarPorId(id);
		
		if (estado != null) {
			throw new EntidadeNaoEncontradaException(String.format("Estado de código %d, não foi encontrado", id));
		}
		
		return estado;
	}
}
