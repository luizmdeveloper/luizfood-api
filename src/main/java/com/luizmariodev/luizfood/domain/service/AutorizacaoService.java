package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizmariodev.luizfood.domain.exception.AutorizacaoNaoEncontradaException;
import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.model.Autorizacao;
import com.luizmariodev.luizfood.domain.repository.AutorizacaoRepository;

@Service
public class AutorizacaoService {
	
	private static final String AUTORIZACAO_NAO_PODE_EXCLUIDO = "Autorização de código %d, não pode ser excluído";
	
	@Autowired
	private AutorizacaoRepository autorizacaoRepository;
	
	@Transactional
	public Autorizacao salvar(Autorizacao autorizacao) {
		return autorizacaoRepository.save(autorizacao);
	}
	
	@Transactional
	public Autorizacao atualizar(Long id, Autorizacao autorizacao) {
		Autorizacao autorizacaoSalva = buscarAutorizacaoPorId(id);
		BeanUtils.copyProperties(autorizacao, autorizacaoSalva, "id");
		return autorizacaoRepository.save(autorizacaoSalva);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			autorizacaoRepository.deleteById(id);
			autorizacaoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(AUTORIZACAO_NAO_PODE_EXCLUIDO, id));
		} catch (EmptyResultDataAccessException e ) {
			throw new AutorizacaoNaoEncontradaException(id);
		}
	}

	public Autorizacao buscarAutorizacaoPorId(Long id) {
		Autorizacao autorizacao = autorizacaoRepository.findById(id)
				.orElseThrow(() -> new AutorizacaoNaoEncontradaException(id));
		
		return autorizacao;
	}
}
