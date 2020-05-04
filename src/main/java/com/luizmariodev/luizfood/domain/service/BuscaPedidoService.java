package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.PedidoNaoEncontradoException;
import com.luizmariodev.luizfood.domain.model.Pedido;
import com.luizmariodev.luizfood.domain.repository.PedidoRepository;

@Service
public class BuscaPedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido buscar(String codigoPedido) {
		Pedido pedido = pedidoRepository.findByCodigo(codigoPedido)
				.orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
		
		return pedido;
	}
	
}
