package com.luizmariodev.luizfood.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizmariodev.luizfood.domain.model.Restaurante;
import com.luizmariodev.luizfood.domain.repository.RestauranteRepository;
import com.luizmariodev.luizfood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@GetMapping
	public List<Restaurante> buscar(){
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Restaurante buscarPorId(@PathVariable Long id) {	
		return restauranteService.buscarRestaurantePorCodigo(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante salvar(@RequestBody Restaurante restaurante) {
		return restauranteService.salvar(restaurante);
	}
	
	@PutMapping("/{id}")
	public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		return restauranteService.atualizar(id, restaurante); 			
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Map<String, Object> dadosOrigem) {
		Restaurante restauranteSalvo = restauranteService.buscarRestaurantePorCodigo(id);
		merge(dadosOrigem, restauranteSalvo);
		return ResponseEntity.ok(atualizar(id, restauranteSalvo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		restauranteService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		dadosOrigem.forEach((nomePropriedade, valorDaPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}

}
