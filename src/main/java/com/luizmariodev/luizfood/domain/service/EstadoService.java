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
	
	private static final String ESTADO_NAO_PODE_SER_EXCLUIDA = "Estado com o código %d, não pode ser excluída";
	private static final String ESTADO_NAO_FOI_ENCONTRADO = "Estado de código %d, não foi encontrado";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public Estado atualizar(Long id, Estado estado) {
		Estado estadoAtual = buscarEstadoPorCodigo(id);
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoRepository.save(estadoAtual);
	}
	
	public Estado buscarEstadoPorCodigo(Long id)  {
		Estado estado = estadoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(ESTADO_NAO_FOI_ENCONTRADO, id)));
			
		return estado;
	}
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(ESTADO_NAO_FOI_ENCONTRADO, estadoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ESTADO_NAO_PODE_SER_EXCLUIDA, estadoId));
		}
	}
}
