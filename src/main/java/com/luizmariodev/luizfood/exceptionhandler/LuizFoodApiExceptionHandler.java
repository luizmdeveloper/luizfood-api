package com.luizmariodev.luizfood.exceptionhandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.exception.NegocioException;

@ControllerAdvice
public class LuizFoodApiExceptionHandler {
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	private ResponseEntity<?> handlerEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
		var problema = Problema.builder()
					.dataHora(LocalDate.now())
					.mensagem(e.getMessage())
					.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	private ResponseEntity<?> handlerEntidadeEmUsoException(EntidadeEmUsoException e) {
		var problema = Problema.builder()
					.dataHora(LocalDate.now())
					.mensagem(e.getMessage())
					.build();
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
	}
	
	@ExceptionHandler(NegocioException.class)
	private ResponseEntity<?> handlerNegocioException(NegocioException e) {
		var problema = Problema.builder()
					.dataHora(LocalDate.now())
					.mensagem(e.getMessage())
					.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}

}
