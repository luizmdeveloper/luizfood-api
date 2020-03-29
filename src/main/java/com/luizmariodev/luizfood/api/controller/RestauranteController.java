package com.luizmariodev.luizfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizmariodev.luizfood.api.assembler.RestauranteInputDissembler;
import com.luizmariodev.luizfood.api.assembler.RestauranteModelAssembler;
import com.luizmariodev.luizfood.api.model.RestauranteModel;
import com.luizmariodev.luizfood.api.model.input.RestauranteModelInput;
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
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteInputDissembler restauranteInputDissembler;
	
	@GetMapping
	public List<RestauranteModel> buscar(){
		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public RestauranteModel buscarPorId(@PathVariable Long id) {	
		return restauranteModelAssembler.toModel(restauranteService.buscarRestaurantePorCodigo(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel salvar(@RequestBody @Valid RestauranteModelInput restauranteInput) {
		var restaurante = restauranteService.salvar(restauranteInputDissembler.toDomainObject(restauranteInput));
		return restauranteModelAssembler.toModel(restaurante);
	}
	
	@PutMapping("/{id}")
	public RestauranteModel atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteModelInput restauranteInput) {
		var restaurante = restauranteService.atualizar(id, restauranteInputDissembler.toDomainObject(restauranteInput)); 		
		restauranteInputDissembler.copyToDomainObject(restauranteInput, restaurante);			
		return restauranteModelAssembler.toModel(restaurante); 			
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Map<String, Object> dadosOrigem, HttpServletRequest httpServletRequest) {
		Restaurante restauranteSalvo = restauranteService.buscarRestaurantePorCodigo(id);
		merge(dadosOrigem, restauranteSalvo, httpServletRequest);
		return ResponseEntity.ok(restauranteService.atualizar(id, restauranteSalvo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		restauranteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{restauranteId}/ativo")
	public void ativar(@PathVariable Long restauranteId) {
		restauranteService.ativar(restauranteId);
	}
	
	@DeleteMapping("/{restauranteId}/inativo")
	public void inativar(@PathVariable Long restauranteId) {
		restauranteService.inativar(restauranteId);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		 
		ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
			
			dadosOrigem.forEach((nomePropriedade, valorDaPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);
				
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});			
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
		}
	}

}
