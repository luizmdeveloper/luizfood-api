package com.luizmariodev.luizfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.api.assembler.PedidoModelAssembler;
import com.luizmariodev.luizfood.api.assembler.PedidoModelInputDisassembler;
import com.luizmariodev.luizfood.api.assembler.PedidoResumoModelAssembler;
import com.luizmariodev.luizfood.api.model.PedidoModel;
import com.luizmariodev.luizfood.api.model.PedidoResumoModel;
import com.luizmariodev.luizfood.api.model.input.PedidoModelInput;
import com.luizmariodev.luizfood.domain.repository.PedidoRepository;
import com.luizmariodev.luizfood.domain.service.BuscaPedidoService;
import com.luizmariodev.luizfood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private BuscaPedidoService buscaPedidoService;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private PedidoModelInputDisassembler pedidoModelInputDisassembler;
	
	@GetMapping
	public List<PedidoResumoModel> buscarTodos() {
		var pedidos = pedidoRepository.findAllResumo();
		return pedidoResumoModelAssembler.toCollectionModel(pedidos);
	}
	
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscarPorCodigo(@PathVariable String codigoPedido) {
		var pedido = buscaPedidoService.buscar(codigoPedido);
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel salvar(@RequestBody @Valid PedidoModelInput pedidoInput) {
		var pedido = pedidoService.salvar(pedidoModelInputDisassembler.toDomainObject(pedidoInput));
		return pedidoModelAssembler.toModel(pedido);
	}
}
