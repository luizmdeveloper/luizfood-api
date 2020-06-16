package com.luizmariodev.luizfood.infrastructure.service.report.exception;

public class RelatorioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RelatorioException(String message, Throwable cause) {
		super(message, cause);
	}

	public RelatorioException(String message) {
		super(message);
	}
}
