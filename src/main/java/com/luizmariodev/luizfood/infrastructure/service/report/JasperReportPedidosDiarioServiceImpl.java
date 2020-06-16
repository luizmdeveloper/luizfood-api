package com.luizmariodev.luizfood.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.filtro.PedidoFilter;
import com.luizmariodev.luizfood.domain.service.PedidosDiarioQueryService;
import com.luizmariodev.luizfood.domain.service.PedidosDiarioReportService;
import com.luizmariodev.luizfood.infrastructure.service.report.exception.RelatorioException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperReportPedidosDiarioServiceImpl implements PedidosDiarioReportService {
	
	@Autowired
	private PedidosDiarioQueryService pedidosDiarioQueryService;
		
	@Override
	public byte[] emitirRelatorio(PedidoFilter filtro, String timeZoneOffset) {		
		try {
			var pedidosDiarios = pedidosDiarioQueryService.consultarPedidos(filtro, timeZoneOffset);
			
			var inputStream = this.getClass().getResourceAsStream("/relatorio/vendas-diarias.jasper");
			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			var dataSource = new JRBeanCollectionDataSource(pedidosDiarios);
			
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException ex) {
			throw new RelatorioException("Não foi possível emitir o relatório de vendas diárias", ex);
		}
		
	}
}
