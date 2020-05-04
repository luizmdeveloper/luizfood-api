package com.luizmariodev.luizfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlteraStatusPedidoService {
	
	@Autowired
	private BuscaPedidoService buscaPedidoService;
	
	@Transactional
	public void confirmar(String codigoPedido) {
		var pedido = buscaPedidoService.buscar(codigoPedido);		
		pedido.confirmar();
	}
	
	@Transactional
	public void entregar(String codigoPedido) {
		var pedido = buscaPedidoService.buscar(codigoPedido);		
		pedido.entregar();
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
		var pedido = buscaPedidoService.buscar(codigoPedido);		
		pedido.cancelar();
	}	
}
