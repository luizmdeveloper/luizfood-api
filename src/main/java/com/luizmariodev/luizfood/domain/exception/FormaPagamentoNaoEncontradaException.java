package com.luizmariodev.luizfood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public FormaPagamentoNaoEncontradaException(Long formaPagamentoId) {
		this(String.format("Forma de pagamento com código %d, não foi encontrado", formaPagamentoId));
	}

}
