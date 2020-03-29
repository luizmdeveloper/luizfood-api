package com.luizmariodev.luizfood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoModelInput {

	@NotBlank
	private String nome;
}
