package com.luizmariodev.luizfood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeModelInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	private EstadoIdInputModel estado;

}
