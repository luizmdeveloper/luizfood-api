package com.luizmariodev.luizfood.exceptionhandler;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problema {
	
	private LocalDate dataHora;
	private String mensagem;

}
