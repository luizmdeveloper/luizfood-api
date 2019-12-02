package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
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
		buscarCozinhaPorCodigo(cozinhaId);
		
		return restauranteRepository.salvar(restaurante);
	}

	public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {		
		Restaurante restauranteSalvo = buscarRestaurantePorCodigo(restauranteId);
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinhaSalva = buscarCozinhaPorCodigo(cozinhaId);
		restaurante.setCozinha(cozinhaSalva);
		BeanUtils.copyProperties(restaurante, restauranteSalvo, "id");
		return restauranteRepository.salvar(restauranteSalvo);
	}

	private Restaurante buscarRestaurantePorCodigo(Long restauranteId) {
		Restaurante restaurante = restauranteRepository.buscarPorId(restauranteId);
		if (restaurante == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe restaurante cadastrado com o código %d", restauranteId));
		}
		
		return restaurante;
	}
	
	private Cozinha buscarCozinhaPorCodigo(Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.buscarPorId(cozinhaId);
		
		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cozinha cadastrada com o código %d", cozinhaId));
		}
		
		return cozinha;
	}


}
