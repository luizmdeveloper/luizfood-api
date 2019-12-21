package com.luizmariodev.luizfood.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.exception.NegocioException;

@ControllerAdvice
public class LuizFoodApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	private ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		var problema = criarProblemaBuilder(TipoProblema.ENTIDADE_NAO_ENCONTRADA, status, e.getMessage()).build();
		
		return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	private ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		var problema = criarProblemaBuilder(TipoProblema.ENTIDADE_EM_USO, status, e.getMessage()).build();
		
		return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	private ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		var problema = criarProblemaBuilder(TipoProblema.ERRO_NEGOCIO, status, e.getMessage()).build();
		
		return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problema.builder()
					.status(status.value())
					.titulo(status.getReasonPhrase())
					.build();
		} else if (body instanceof String) {
			body = Problema.builder()
					.status(status.value())
					.detalhe((String) body)
					.build();
		} 
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problema.ProblemaBuilder criarProblemaBuilder(TipoProblema tipoProblema, HttpStatus status, String detalhe){
		return new Problema.ProblemaBuilder()
					.status(status.value())
					.tipo(tipoProblema.getPath())
					.titulo(tipoProblema.getTitulo())
					.detalhe(detalhe);
	}
}
