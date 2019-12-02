package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.model.Restaurante;
import com.luizmariodev.luizfood.domain.repository.CozinhaRepository;
import com.luizmariodev.luizfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscarPorId(cozinhaId);
		
		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cozinha cadastrada com o código %d", cozinhaId));
		}
		
		return restauranteRepository.salvar(restaurante);
	}

}
