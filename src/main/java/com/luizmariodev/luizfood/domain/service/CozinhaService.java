package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizmariodev.luizfood.domain.exception.CozinhaNaoEncontradaException;
import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
	private static final String COZINHA_NAO_PODE_SER_EXCLUIDA = "Cozinha de código %d, não pode ser excluída";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	@Transactional
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			cozinhaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
		} catch (DataIntegrityViolationException ex) {
			throw new EntidadeEmUsoException(String.format(COZINHA_NAO_PODE_SER_EXCLUIDA, cozinhaId));
		}
	}

	@Transactional
	public Cozinha atualizar(Long cozinhaId, Cozinha cozinha) {
		Cozinha cozinhaAtual = buscarCozinhaPorId(cozinhaId);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return cozinhaRepository.save(cozinhaAtual);
	}
	
	public Cozinha buscarCozinhaPorId(Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
					.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));

		return cozinha;
	} 
}
