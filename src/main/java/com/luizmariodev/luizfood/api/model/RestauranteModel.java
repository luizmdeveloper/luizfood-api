package com.luizmariodev.luizfood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {
	
	private Long id;
	private String nome;
	private BigDecimal taxaEntrega;
	private CozinhaModel cozinha;
	private Boolean ativo;
	private EnderecoModel endereco;

}
