package com.luizmariodev.luizfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.domain.filtro.PedidoFilter;
import com.luizmariodev.luizfood.domain.model.dto.PedidosDiario;
import com.luizmariodev.luizfood.domain.service.PedidosDiarioQueryService;
import com.luizmariodev.luizfood.domain.service.PedidosDiarioReportService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {
	
	@Autowired
	private PedidosDiarioQueryService pedidosDiarioQueryServcice;
	
	@Autowired
	private PedidosDiarioReportService pedidosDiarioReportService;
	
	@GetMapping(path = "/pedidos-diarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PedidosDiario> buscarPedidos(PedidoFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeZoneOffset) {
		return pedidosDiarioQueryServcice.consultarPedidos(filtro, timeZoneOffset);
	}

	
	@GetMapping(path = "/pedidos-diarios", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> buscarPedidosPDF(PedidoFilter filtro, @RequestParam(required = false, defaultValue = "+00:00") String timeZoneOffset) {
		
		var relatorio = pedidosDiarioReportService.emitirRelatorio(filtro, timeZoneOffset);
		
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-pedidos-diarios.pdf");
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(relatorio);
	}
	
}
