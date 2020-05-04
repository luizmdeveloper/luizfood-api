package com.luizmariodev.luizfood.api.model.input;

import java.math.BigInteger;

import javax.validation.constraints.PositiveOrZero;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModelInput {
	
	@NotNull
	private Long produtoId;
	
	@PositiveOrZero
	private BigInteger quantidade;
	
	private String observacao;
	
}
