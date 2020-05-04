package com.luizmariodev.luizfood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public ProdutoNaoEncontradoException(Long produtoId, Long restauranteId) {
		super(String.format("Não existe produto com o código %d para orestaurante de código %d", produtoId, restauranteId));
	}

	public ProdutoNaoEncontradoException(Long produtoId) {
		super(String.format("Não existe produto com o código %d", produtoId));
	}

}