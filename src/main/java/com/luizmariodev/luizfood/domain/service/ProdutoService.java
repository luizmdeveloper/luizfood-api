package com.luizmariodev.luizfood.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.NegocioException;
import com.luizmariodev.luizfood.domain.exception.ProdutoNaoEncontradoException;
import com.luizmariodev.luizfood.domain.exception.RestauranteNaoEncontradoException;
import com.luizmariodev.luizfood.domain.model.Produto;
import com.luizmariodev.luizfood.domain.model.Restaurante;
import com.luizmariodev.luizfood.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Transactional
	public Produto salvar(Long restauranteId, Produto produto) {
		var restaurante = buscarRestaurantePorCodigo(restauranteId);
		produto.setRestaurante(restaurante);
		return produtoRepository.save(produto);
	}

	@Transactional
	public Produto alterar(Long restauranteId, Long produtoId, Produto produto) {
		var restauranteSalvo = buscarRestaurantePorCodigo(restauranteId);			
		var produtoSalvo = buscarProdutoPorCodigo(restauranteId, produtoId);
		produto.setRestaurante(restauranteSalvo);
		BeanUtils.copyProperties(produto, produtoSalvo, "id");		
		return produtoRepository.save(produtoSalvo);
	}
		
	public Produto buscarProdutoPorCodigo(Long restauranteId, Long produtoId) {
		Optional<Produto> optionalProduto = produtoRepository.findByRestauranteAndProduto(produtoId, restauranteId);
		 
		if (!optionalProduto.isPresent())
			throw new ProdutoNaoEncontradoException(restauranteId, produtoId);
			
		return optionalProduto.get();
	}
	
	public List<Produto> buscarTodosProdutoPorRestaurante(Long restauranteId) {
		return produtoRepository.findByRestaurante(restauranteId);
	}
	
	private Restaurante buscarRestaurantePorCodigo(Long restauranteId) {
		Restaurante restaurante = null;
		try {
			restaurante = restauranteService.buscarRestaurantePorCodigo(restauranteId);
			return restaurante;
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(String.format("Não existe restaurante com o código %d", restauranteId));
		}
	}

}