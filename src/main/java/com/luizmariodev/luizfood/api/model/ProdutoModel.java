package com.luizmariodev.luizfood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {
	
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private boolean ativo;

}
