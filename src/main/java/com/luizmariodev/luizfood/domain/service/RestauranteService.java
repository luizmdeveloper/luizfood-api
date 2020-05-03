package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizmariodev.luizfood.domain.exception.CidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.exception.CozinhaNaoEncontradaException;
import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.NegocioException;
import com.luizmariodev.luizfood.domain.exception.RestauranteNaoEncontradoException;
import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.model.FormaPagamento;
import com.luizmariodev.luizfood.domain.model.Produto;
import com.luizmariodev.luizfood.domain.model.Restaurante;
import com.luizmariodev.luizfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	private static final String RESTURANTE_NAO_PODE_EXCLUIDO = "Resturante de código %d, não pode ser excluído";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeSerivce;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		
		try {
			var cozinhaSalva = buscarCozinhaPorCodigo(cozinhaId);
			var cidadeSalva = buscarCidadePorCodigo(cidadeId);
			
			restaurante.setCozinha(cozinhaSalva);
			restaurante.getEndereco().setCidade(cidadeSalva);
			
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
		
		return restauranteRepository.save(restaurante);
	}

	@Transactional
	public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {		
		Restaurante restauranteSalvo = buscarRestaurantePorCodigo(restauranteId);
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		try {
			var cozinhaSalva = buscarCozinhaPorCodigo(cozinhaId);
			var cidadeSalva = buscarCidadePorCodigo(cidadeId);
			
			restaurante.setCozinha(cozinhaSalva);
			restaurante.getEndereco().setCidade(cidadeSalva);
			BeanUtils.copyProperties(restaurante, restauranteSalvo, "id", "pagamentos","dataCadastro", "dataUltimaAtualizacao");
			
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
		
		return restauranteRepository.save(restauranteSalvo);
	}
	
	@Transactional
	public void ativar(Long restauranteId) {
		var restaurante = buscarRestaurantePorCodigo(restauranteId);
		restaurante.ativar();
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		var restaurante = buscarRestaurantePorCodigo(restauranteId);
		restaurante.inativar();
	}	
	
	@Transactional
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);
			restauranteRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(RESTURANTE_NAO_PODE_EXCLUIDO, id));
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(id);
		}
	}
	
	@Transactional
	public void asscociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarRestaurantePorCodigo(restauranteId);		
		FormaPagamento formaPagamento = buscarFormaPagamentoPorCodigo(formaPagamentoId);
		restaurante.adicionarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void desasscociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = buscarRestaurantePorCodigo(restauranteId);		
		FormaPagamento formaPagamento = buscarFormaPagamentoPorCodigo(formaPagamentoId);
		restaurante.removerFormaPagamento(formaPagamento);
	}
	
	public Restaurante buscarRestaurantePorCodigo(Long restauranteId) {
		Restaurante restaurante = restauranteRepository.findById(restauranteId)
					.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));		
		return restaurante;
	}
	
	
	public Produto buscarProdutoDoRestaurante(Long restauranteId, Long produtoId) {
		var restaurante = buscarRestaurantePorCodigo(restauranteId);
		return restaurante.buscarProduto(produtoId);		
	}
	
	
	private FormaPagamento buscarFormaPagamentoPorCodigo(Long formaPagamentoId) {
		return formaPagamentoService.buscarPorId(formaPagamentoId);
	}

	private Cozinha buscarCozinhaPorCodigo(Long cozinhaId) {				
		return cozinhaService.buscarCozinhaPorId(cozinhaId);
	}
	
	private Cidade buscarCidadePorCodigo(Long cidadeId) {
		return cidadeSerivce.buscarCidadePorCodigo(cidadeId);
	}


}
