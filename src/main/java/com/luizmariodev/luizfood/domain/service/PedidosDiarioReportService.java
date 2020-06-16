package com.luizmariodev.luizfood.domain.service;

import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.filtro.PedidoFilter;

@Service
public interface PedidosDiarioReportService {
	
	public byte[] emitirRelatorio(PedidoFilter filtro, String timeZoneOffset);

}
