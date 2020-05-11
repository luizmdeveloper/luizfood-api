package com.luizmariodev.luizfood.api.model.input.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInputFilter {

	private Long restauranteId;
	private Long clienteId;
	private String status;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)	
	private OffsetDateTime dataCriacaoFim;
}
