package com.luizmariodev.luizfood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {
	
	private String codigo;
	private RestauranteResumoModel restaurante;
	private UsuarioModel cliente;
	private OffsetDateTime dataCricacao;	
	private OffsetDateTime dataEntrega;	
	private BigDecimal valorTotal;	
	private BigDecimal subTotal;	
	private BigDecimal taxaFrete;	
	private String status;
}
