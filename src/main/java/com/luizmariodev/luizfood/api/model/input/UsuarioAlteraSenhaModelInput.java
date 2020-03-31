package com.luizmariodev.luizfood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAlteraSenhaModelInput {

	@NotBlank
	private String senhaAtual;
	
	@NotBlank
	private String novaSenha;
}
