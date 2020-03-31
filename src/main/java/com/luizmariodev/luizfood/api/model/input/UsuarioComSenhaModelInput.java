package com.luizmariodev.luizfood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaModelInput extends UsuarioModelInput {
	
	@NotBlank
	private String senha;

}
