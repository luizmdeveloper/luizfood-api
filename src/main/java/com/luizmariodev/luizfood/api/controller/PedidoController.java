package com.luizmariodev.luizfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.api.model.PedidoModel;
import com.luizmariodev.luizfood.api.model.PedidoModelAssembler;
import com.luizmariodev.luizfood.domain.repository.PedidoRepository;
import com.luizmariodev.luizfood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@GetMapping
	public List<PedidoModel> buscarTodos() {
		var pedidos = pedidoRepository.findAll();
		return pedidoModelAssembler.toCollectionModel(pedidos);
	}
	
	@GetMapping("/{pedidoId}")
	public PedidoModel buscarPorCodigo(@PathVariable Long pedidoId) {
		var pedido = pedidoService.buscarPedidoPorCodigo(pedidoId);
		return pedidoModelAssembler.toModel(pedido);
	}
}
