package com.luizmariodev.luizfood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModelInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaEntrega;
	
	@Valid
	@NotNull
	private CozinhaIdModelInput cozinha;
	
	@Valid
	@NotNull
	private EnderecoModelInput endereco;

}
