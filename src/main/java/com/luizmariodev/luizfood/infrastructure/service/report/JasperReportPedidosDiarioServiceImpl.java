package com.luizmariodev.luizfood.infrastructure.service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.filtro.PedidoFilter;
import com.luizmariodev.luizfood.domain.service.PedidosDiarioQueryService;
import com.luizmariodev.luizfood.domain.service.PedidosDiarioReportService;

@Service
public class JasperReportPedidosDiarioServiceImpl implements PedidosDiarioReportService {
	
	@Autowired
	private PedidosDiarioQueryService pedidosDiarioQueryService;
		
	@Override
	public byte[] emitirRelatorio(PedidoFilter filtro, String timeZoneOffset) {		
		var pedidosDiario = pedidosDiarioQueryService.consultarPedidos(filtro, timeZoneOffset);
		
		

		return null;
	}
}
