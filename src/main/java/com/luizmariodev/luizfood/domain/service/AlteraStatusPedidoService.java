package com.luizmariodev.luizfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlteraStatusPedidoService {
	
	@Autowired
	private BuscaPedidoService buscaPedidoService;
	
	@Transactional
	public void confirmar(Long pedidoId) {
		var pedido = buscaPedidoService.buscar(pedidoId);		
		pedido.confirmar();
	}
	
	@Transactional
	public void entregar(Long pedidoId) {
		var pedido = buscaPedidoService.buscar(pedidoId);		
		pedido.entregar();
	}
	
	@Transactional
	public void cancelar(Long pedidoId) {
		var pedido = buscaPedidoService.buscar(pedidoId);		
		pedido.cancelar();
	}	
}
