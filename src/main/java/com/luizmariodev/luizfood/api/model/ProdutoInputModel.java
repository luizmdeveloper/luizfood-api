package com.luizmariodev.luizfood.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInputModel {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@Positive
	private BigDecimal valor;
	
	@NotNull
	private Boolean ativo;

}
