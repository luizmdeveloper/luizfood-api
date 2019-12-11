package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
	private static final String COZINHA_NAO_PODE_SER_EXCLUIDA = "Cozinha de código %d, não pode ser excluída";
	private static final String COZINHA_NAO_FOI_ENCONTRADA = "Cozinha de código %d, não foi encontrada";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(COZINHA_NAO_FOI_ENCONTRADA, cozinhaId));
		} catch (DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException(String.format(COZINHA_NAO_PODE_SER_EXCLUIDA, cozinhaId));
		}
	}

	public Cozinha atualizar(Long cozinhaId, Cozinha cozinha) {
		Cozinha cozinhaAtual = buscarCozinhaPorId(cozinhaId);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return cozinhaRepository.save(cozinhaAtual);
	}
	
	public Cozinha buscarCozinhaPorId(Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
					.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(COZINHA_NAO_FOI_ENCONTRADA, cozinhaId)));

		return cozinha;
	} 
}
