package com.luizmariodev.luizfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.api.assembler.FormaPagamentoModelAssembler;
import com.luizmariodev.luizfood.api.model.FormaPagamentoModel;
import com.luizmariodev.luizfood.domain.model.Restaurante;
import com.luizmariodev.luizfood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/forma-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	
	@GetMapping
	public List<FormaPagamentoModel> buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarRestaurantePorCodigo(restauranteId);		
		return formaPagamentoModelAssembler.toCollectionModel(restaurante.getPagamentos());
	}
	
	@PutMapping("/{formaPagamentoId}")
	public void associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.asscociarFormaPagamento(restauranteId, formaPagamentoId);
	}	
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.desasscociarFormaPagamento(restauranteId, formaPagamentoId);
	}
}
