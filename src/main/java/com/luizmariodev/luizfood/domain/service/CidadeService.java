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

@Service
public class CidadeService {
	
	private static final String CIDADE_NAO_EXCLUIDA = "Cidade com o código %d, não pode ser excluída";
	private static final String CIDADE_NAO_FOI_ENCONTRADA = "Cidade com o código %d, não foi encontrada";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
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
			throw new EntidadeNaoEncontradaException(String.format(CIDADE_NAO_FOI_ENCONTRADA, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(CIDADE_NAO_EXCLUIDA, id));
		}
	}
	
	public Cidade buscarCidadePorCodigo(Long cidadeId) {
		Cidade cidade = cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(CIDADE_NAO_FOI_ENCONTRADA, cidadeId)));
			
		return cidade;
	}
	
	private Estado buscarEstadoPorCodigo(Long estadoId) {
		return estadoService.buscarEstadoPorCodigo(estadoId);
	}
}