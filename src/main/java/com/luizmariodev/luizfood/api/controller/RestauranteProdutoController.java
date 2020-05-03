package com.luizmariodev.luizfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.api.assembler.ProdutoInputModelDisassembler;
import com.luizmariodev.luizfood.api.assembler.ProdutoModelAssembler;
import com.luizmariodev.luizfood.api.model.ProdutoInputModel;
import com.luizmariodev.luizfood.api.model.ProdutoModel;
import com.luizmariodev.luizfood.domain.model.Produto;
import com.luizmariodev.luizfood.domain.service.ProdutoService;
import com.luizmariodev.luizfood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputModelDisassembler produtoInputModelDisassembler;
		
	@GetMapping
	public List<ProdutoModel> buscar(@PathVariable Long restauranteId) {
		var restaurante = restauranteService.buscarRestaurantePorCodigo(restauranteId);
		List<Produto> todosProdutosDoRestaurante = restaurante.getProdutos();		
		return produtoModelAssembler.toCollectionModel(todosProdutosDoRestaurante);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoModel buscarProdutoPorRestauranteECodigo(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		var produto = produtoService.buscarProdutoPorCodigo(restauranteId, produtoId);
		return produtoModelAssembler.toModel(produto);
	}
	
	@PostMapping
	public ProdutoModel salvar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInputModel produtoInputModel) {
		var produto = produtoService.salvar(restauranteId, produtoInputModelDisassembler.toDomainObject(produtoInputModel));
		return produtoModelAssembler.toModel(produto);
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProdutoInputModel produtoInputModel) {		
		var produtoSalvo = produtoService.alterar(restauranteId, produtoId, produtoInputModelDisassembler.toDomainObject(produtoInputModel));
		return produtoModelAssembler.toModel(produtoSalvo);
	}
}