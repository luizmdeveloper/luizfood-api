package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
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
		
		return restauranteRepository.save(restaurante);
	}

	public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {		
		Restaurante restauranteSalvo = buscarRestaurantePorCodigo(restauranteId);
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinhaSalva = buscarCozinhaPorCodigo(cozinhaId);
		restaurante.setCozinha(cozinhaSalva);
		BeanUtils.copyProperties(restaurante, restauranteSalvo, "id", "pagamentos", "endereco");
		return restauranteRepository.save(restauranteSalvo);
	}
	
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Resturante de código %d, não pode ser excluído", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe restaurante cadastrado com o código %d", id));
		}
	}
	
	private Restaurante buscarRestaurantePorCodigo(Long restauranteId) {
		Restaurante restaurante = restauranteRepository.findById(restauranteId)
					.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe restaurante cadastrado com o código %d", restauranteId)));		
		return restaurante;
	}
	
	private Cozinha buscarCozinhaPorCodigo(Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
					.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cozinha cadastrada com o código %d", cozinhaId)));
				
		return cozinha;
	}


}
