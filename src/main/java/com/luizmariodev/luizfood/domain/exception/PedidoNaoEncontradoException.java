package com.luizmariodev.luizfood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String codigoPedido) {
		super(String.format("Nenhum pedido com código %s foi encontrado", codigoPedido));
	}
}
