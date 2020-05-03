package com.luizmariodev.luizfood.domain.exception;

public class AutorizacaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public AutorizacaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public AutorizacaoNaoEncontradaException(Long autorizacaoId) {
		this(String.format("Autorização de código %d não foi encontrada", autorizacaoId));
	}
}
