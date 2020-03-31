package com.luizmariodev.luizfood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoModelInput {
	
	@NotBlank
	private String nome;
}
