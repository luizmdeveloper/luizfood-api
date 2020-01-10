package com.luizmariodev.luizfood.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.exception.NegocioException;

@ControllerAdvice
public class LuizFoodApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	public static final String MENSAGEM_ERRO_GENERICO_USUARIO = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir," +
															    " entre em contato com o administrador do sistema.";

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,HttpStatus status, WebRequest request) {
		var problema = criarProblemaBuilder(TipoProblema.RECURSO_NAO_ENCONTRADO, status, String.format("O recurso '%s', que você tentou acessar, não existe", ex.getRequestURL()))
				.mensagemUsuario(String.format("O recurso '%s', que você tentou acessar, não existe", ex.getRequestURL()))
				.build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable causaRaiz = ExceptionUtils.getRootCause(ex);
		
		if (causaRaiz instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) causaRaiz, headers, status, request);
		} else if (causaRaiz instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) causaRaiz, headers, status, request);
		}
		
		String mensagemDetalhe = "Corpo da requisição incropreensível. Por favor, verifique e tente mais tarde";
		String mensagemUauario = "Não obtive êxito em converter o(s) valore(s) informado(s). Por favor, verifique e tente mais tarde";
		var problema = criarProblemaBuilder(TipoProblema.MENSAGEM_INCROPREENSIVEL, status, mensagemDetalhe)
						.mensagemUsuario(mensagemUauario)
						.build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		var detalhe = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		
		BindingResult bindResult = ex.getBindingResult();
		List<Problema.Propriedade> propriedades = bindResult.getFieldErrors().stream()
													.map(FieldError -> Problema.Propriedade.builder()
																.nome(FieldError.getField())
																.mensagemUsuario(FieldError.getDefaultMessage())
																.build())
													.collect(Collectors.toList());
		
		var problema = criarProblemaBuilder(TipoProblema.DADOS_INVALIDOS, status, detalhe)
				.mensagemUsuario(detalhe)
				.propriedades(propriedades)
				.build();
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problema.builder()
					.status(status.value())
					.titulo(status.getReasonPhrase())
					.dataHora(LocalDateTime.now())
					.build();
		} else if (body instanceof String) {
			body = Problema.builder()
					.status(status.value())
					.detalhe((String) body)
					.dataHora(LocalDateTime.now())
					.build();
		} 
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> handleExceptionNaoTratada(Exception e, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String mensagem = MENSAGEM_ERRO_GENERICO_USUARIO;
		var problema = criarProblemaBuilder(TipoProblema.ERRO_DE_SISTEMA, status, mensagem)
						.mensagemUsuario(MENSAGEM_ERRO_GENERICO_USUARIO)
						.build();
		
		return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
	}
	
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
		var problema = criarProblemaBuilder(TipoProblema.ERRO_NEGOCIO, status, e.getMessage())
						.mensagemUsuario(e.getMessage())
						.build();
		
		return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpHeaders headers, HttpStatus status, WebRequest request) {		
		String mensagemDetalhe = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é um tipo inválido. Coirrija e informe um valor compatível com o tipo '%s'", e.getName(), e.getValue(), e.getRequiredType().getSimpleName()); 
		var problema = criarProblemaBuilder(TipoProblema.PARAMETRO_INVALIDO, status, mensagemDetalhe)
						.mensagemUsuario(mensagemDetalhe)
						.build();
		
		return handleExceptionInternal(e, problema, headers, status, request);
	}
	
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream()
				.map(erro -> erro.getFieldName())
				.collect(Collectors.joining("."));
	
		String mensagemDetalhe = String.format("A propriedade '%s' não existe nessa entidade. Para continuar," + 
											   " remova-a e envie novamente", path);
		String mensagemUsuario = String.format("O campo '%s' não existe nessa entidade. Para continuar," + 
		   	       							   " remova-a e envie novamente", path);
		
		var problema = criarProblemaBuilder(TipoProblema.MENSAGEM_INCROPREENSIVEL, status, mensagemDetalhe)
						.mensagemUsuario(mensagemUsuario)
						.build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ex.getPath().stream()
					.map(erro -> erro.getFieldName())
					.collect(Collectors.joining("."));
		
		String mensagemDetalhe = String.format("A propriedade '%s' recebeu o valor '%s' porém, o valor é inválido. Para continuar, " + 
											   " corrija o tipo de dado para um tipo compatível com '%s'", path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		String mensagemUsuario = String.format("O campo '%s' recebeu o valor '%s' porém, este valor é inválido. Para continuar, " + 
		   		   							   " corrija-o e envie novamente", path, ex.getValue());
		
		var problema = criarProblemaBuilder(TipoProblema.MENSAGEM_INCROPREENSIVEL, status, mensagemDetalhe)
						.mensagemUsuario(mensagemUsuario)
						.build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}	
	
	private Problema.ProblemaBuilder criarProblemaBuilder(TipoProblema tipoProblema, HttpStatus status, String detalhe){
		return new Problema.ProblemaBuilder()
					.status(status.value())
					.tipo(tipoProblema.getPath())
					.titulo(tipoProblema.getTitulo())
					.detalhe(detalhe)
					.dataHora(LocalDateTime.now());
	}
}

