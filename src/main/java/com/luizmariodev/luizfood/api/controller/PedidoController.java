package com.luizmariodev.luizfood.api.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.luizmariodev.luizfood.api.core.data.PageableTransletor;
import com.luizmariodev.luizfood.api.model.PedidoModel;
import com.luizmariodev.luizfood.api.model.PedidoResumoModel;
import com.luizmariodev.luizfood.api.model.input.PedidoModelInput;
import com.luizmariodev.luizfood.api.model.input.filter.PedidoInputFilter;
import com.luizmariodev.luizfood.domain.repository.PedidoRepository;
import com.luizmariodev.luizfood.domain.service.BuscaPedidoService;
import com.luizmariodev.luizfood.domain.service.PedidoService;
import com.luizmariodev.luizfood.infrastructure.repository.specs.PedidoSpecs;

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
	public Page<PedidoResumoModel> pesquisar(PedidoInputFilter filtro, @PageableDefault(size = 5) Pageable pageable) {
		pageable = traduzirPageabble(pageable);
		var pedidosPage = pedidoRepository.findAll(PedidoSpecs.pesquisar(filtro), pageable);		
		var pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidosPage.getContent());		
		Page<PedidoResumoModel> pedidos = new PageImpl<PedidoResumoModel>(pedidosModel, pageable, pedidosPage.getTotalElements());
	
		return pedidos;
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
	
	private Pageable traduzirPageabble(Pageable apiPageable) {		
		var mapeamento = Map.of(
				"codigo", "codigo",
				"valorTotal", "valorTotal",
				"dataCriacao", "dataCriacao",
				"restaurante.id","restaurante.id",
				"restaurante.nome", "restaurante.nome",
				"cliente.id", "cliente.id",
				"cliente.nome", "cliente.nome",
				"nomeCliente", "cliente.nome"
			);				
		
		return PageableTransletor.translate(apiPageable, mapeamento);
	}
}
