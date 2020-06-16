package com.luizmariodev.luizfood.domain.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PedidosDiario {
	
	private Date data;
	private Long quantidade;
	private BigDecimal valorTotal;

}
