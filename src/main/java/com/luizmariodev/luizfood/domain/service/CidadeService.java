package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.model.Estado;
import com.luizmariodev.luizfood.domain.repository.CidadeRepository;
import com.luizmariodev.luizfood.domain.repository.EstadoRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = buscarEstadoPorCodigo(estadoId);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public Cidade atualizar(Long id, Cidade cidade) {
		Cidade cidadeSalvo = buscarCidadePorCodigo(id);
		BeanUtils.copyProperties(cidade, cidadeSalvo, "id");
		return cidadeRepository.save(cidadeSalvo);
	}
	
	public void excluir(Long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com o código %d, não foi encontrada", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cidade com o código %d, não pode ser excluída", id));
		}
	}
	
	private Cidade buscarCidadePorCodigo(Long cidadeId) {
		Cidade cidade = cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cidade com o código %d, não foi encontrada", cidadeId)));
			
		return cidade;
	}

	private Estado buscarEstadoPorCodigo(Long estadoId) {
		Estado estado = estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Estado de código %d, não foi encontrada", estadoId)));
		
		return estado;
	}
}
