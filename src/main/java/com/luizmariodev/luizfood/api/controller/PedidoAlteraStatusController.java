package com.luizmariodev.luizfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.domain.service.AlteraStatusPedidoService;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class PedidoAlteraStatusController {
	
	@Autowired
	private AlteraStatusPedidoService alteraStatusPedidoService;
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmacao(@PathVariable Long pedidoId) {
		alteraStatusPedidoService.confirmar(pedidoId);
	}

	@PutMapping("/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entrega(@PathVariable Long pedidoId) {
		alteraStatusPedidoService.entregar(pedidoId);
	}

	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelamento(@PathVariable Long pedidoId) {
		alteraStatusPedidoService.cancelar(pedidoId);
	}	
}
