package com.luizmariodev.luizfood.api.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {
	
	private Long produtoId;
	private String produtoNome;
	private BigInteger quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal valorTotal;
	private String observacao;
	
}
