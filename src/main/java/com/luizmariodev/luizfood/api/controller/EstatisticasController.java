package com.luizmariodev.luizfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.domain.filtro.PedidoFilter;
import com.luizmariodev.luizfood.domain.model.dto.PedidosDiario;
import com.luizmariodev.luizfood.domain.service.PedidosDiarioQueryService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {
	
	@Autowired
	private PedidosDiarioQueryService pedidosDiarioQueryServcice;
	
	@GetMapping("/pedidos-diarios")
	public List<PedidosDiario> buscarPedidos(PedidoFilter filtro) {
		return pedidosDiarioQueryServcice.consultarPedidos(filtro);
	}

}
