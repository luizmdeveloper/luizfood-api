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
import com.luizmariodev.luizfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	private static final String RESTAURANTE_NAO_ENCONTRADO = "Não existe restaurante cadastrado com o código %d";
	private static final String RESTURANTE_NAO_PODE_EXCLUIDO = "Resturante de código %d, não pode ser excluído";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
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
		BeanUtils.copyProperties(restaurante, restauranteSalvo, "id", "pagamentos", "endereco", "dataCadastro", "dataUltimaAtualizacao");
		return restauranteRepository.save(restauranteSalvo);
	}
	
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(RESTURANTE_NAO_PODE_EXCLUIDO, id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(RESTAURANTE_NAO_ENCONTRADO, id));
		}
	}
	
	public Restaurante buscarRestaurantePorCodigo(Long restauranteId) {
		Restaurante restaurante = restauranteRepository.findById(restauranteId)
					.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(RESTAURANTE_NAO_ENCONTRADO, restauranteId)));		
		return restaurante;
	}
	
	private Cozinha buscarCozinhaPorCodigo(Long cozinhaId) {				
		return cozinhaService.buscarCozinhaPorId(cozinhaId);
	}
}
