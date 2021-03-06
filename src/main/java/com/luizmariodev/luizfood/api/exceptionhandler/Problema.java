package com.luizmariodev.luizfood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problema {
		
	private Integer status;
	private String tipo;
	private String titulo;
	private String detalhe;
	private LocalDateTime dataHora;
	private String mensagemUsuario;
	private List<Propriedade> propriedades;
	
	@Getter
	@Builder
	public static class Propriedade {
		
		private String nome;
		private String mensagemUsuario;
		
	}

}
