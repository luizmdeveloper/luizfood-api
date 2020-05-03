package com.luizmariodev.luizfood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public PedidoNaoEncontradoException(Long pedidoId) {
		super(String.format("Nenhum pedido com código %d foi encontrado", pedidoId));
	}

}
