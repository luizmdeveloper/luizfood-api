package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizmariodev.luizfood.domain.exception.CidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EstadoNaoEncontradoException;
import com.luizmariodev.luizfood.domain.exception.NegocioException;
import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.model.Estado;
import com.luizmariodev.luizfood.domain.repository.CidadeRepository;

@Service
public class CidadeService {
	
	private static final String CIDADE_NAO_EXCLUIDA = "Cidade com o código %d, não pode ser excluída";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@Transactional
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		try {
			Estado estado = buscarEstadoPorCodigo(estadoId);
			cidade.setEstado(estado);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		return cidadeRepository.save(cidade);
	}
	
	@Transactional
	public Cidade atualizar(Long id, Cidade cidade) {
		Cidade cidadeSalvo = buscarCidadePorCodigo(id);
		cidadeSalvo.setEstado(buscarEstadoPorCodigo(cidade.getEstado().getId()));
		BeanUtils.copyProperties(cidade, cidadeSalvo, "id");
		return cidadeRepository.save(cidadeSalvo);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(CIDADE_NAO_EXCLUIDA, id));
		}
	}
	
	public Cidade buscarCidadePorCodigo(Long cidadeId) {
		Cidade cidade = cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
			
		return cidade;
	}
	
	private Estado buscarEstadoPorCodigo(Long estadoId) {
		return estadoService.buscarEstadoPorCodigo(estadoId);
	}
}