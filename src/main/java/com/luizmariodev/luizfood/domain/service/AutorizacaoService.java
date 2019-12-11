package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.Autorizacao;
import com.luizmariodev.luizfood.domain.repository.AutorizacaoRepository;

@Service
public class AutorizacaoService {
	
	private static final String AUTORIZACAO_NAO_FOI_ENCONTRADA = "Autorização de código %d, não foi encontrada";
	private static final String AUTORIZACAO_NAO_PODE_EXCLUIDA = "Autorização de código %d, não pode ser excluída";
	
	@Autowired
	private AutorizacaoRepository autorizacaoRepository;
	
	public Autorizacao salvar(Autorizacao autorizacao) {
		return autorizacaoRepository.save(autorizacao);
	}
	
	public Autorizacao atualizar(Long id, Autorizacao autorizacao) {
		Autorizacao autorizacaoSalva = buscarAutorizacaoPorId(id);
		BeanUtils.copyProperties(autorizacao, autorizacaoSalva, "id");
		return autorizacaoRepository.save(autorizacaoSalva);
	}

	public void excluir(Long id) {
		try {
			autorizacaoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(AUTORIZACAO_NAO_PODE_EXCLUIDA, id));
		} catch (EmptyResultDataAccessException e ) {
			throw new EntidadeNaoEncontradaException(String.format(AUTORIZACAO_NAO_FOI_ENCONTRADA, id));
		}
	}

	public Autorizacao buscarAutorizacaoPorId(Long id) {
		Autorizacao autorizacao = autorizacaoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(AUTORIZACAO_NAO_PODE_EXCLUIDA, id)));
		
		return autorizacao;
	}
}
