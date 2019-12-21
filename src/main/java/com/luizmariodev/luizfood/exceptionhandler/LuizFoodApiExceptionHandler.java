package com.luizmariodev.luizfood.exceptionhandler;

import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
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
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable causaRaiz = ExceptionUtils.getRootCause(ex);
		
		if (causaRaiz instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) causaRaiz, headers, status, request);
		}
		
		String mensagemDetalhe = "Corpo da requisição incropreensível. Por favor, verifique e tente mais tarde";
		var problema = criarProblemaBuilder(TipoProblema.MENSAGEM_INCROPREENSIVEL, status, mensagemDetalhe).build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream()
					.map(erro -> erro.getFieldName())
					.collect(Collectors.joining("."));
		
		String mensagemDetalhe = String.format("A propriedade '%s' recebeu o valor '%s' porém, o valor é inválido. Para continuar, " + 
											   " corrija o tipo de dado para um tipo compatível com '%s'", path, ex.getValue(), ex.getTargetType().getSimpleName());
		var problema = criarProblemaBuilder(TipoProblema.MENSAGEM_INCROPREENSIVEL, status, mensagemDetalhe).build();
		return handleExceptionInternal(ex, problema, headers, status, request);
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
