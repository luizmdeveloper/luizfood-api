package com.luizmariodev.luizfood.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.filtro.PedidoFilter;
import com.luizmariodev.luizfood.domain.model.dto.PedidosDiario;

@Service
public interface PedidosDiarioQueryService {
	
	public List<PedidosDiario> consultarPedidos(PedidoFilter filtro, String timeZoneOffset);

}
