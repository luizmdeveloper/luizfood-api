package com.luizmariodev.luizfood.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.exception.NegocioException;
import com.luizmariodev.luizfood.domain.exception.PedidoNaoEncontradoException;
import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.model.FormaPagamento;
import com.luizmariodev.luizfood.domain.model.Pedido;
import com.luizmariodev.luizfood.domain.model.Produto;
import com.luizmariodev.luizfood.domain.model.Restaurante;
import com.luizmariodev.luizfood.domain.model.Usuario;
import com.luizmariodev.luizfood.domain.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		Restaurante restaurante = null;
		Usuario usuario = null;
		FormaPagamento pagamento = null;
		Cidade cidade = null;
		
		try {
			restaurante = buscarRestaurantePorCodigo(pedido.getRestaurante().getId());
			if (restaurante.isInativo())
				throw new NegocioException(String.format("Restaurante %s está inativo", restaurante.getNome()));
			
			if (restaurante.isFechado())
				throw new NegocioException(String.format("Restaurante %s está fechado", restaurante.getNome()));
			
			usuario = buscarUsuarioPorCodigo(pedido.getCliente().getId());
			cidade = buscarCidadePorCodigo(pedido.getEnderecoEntrega().getCidade().getId());
			pagamento = buscarFormaPaamentoPorCodigo(pedido.getPagamento().getId());
						
			if (!restaurante.isPermiteFormaPagamento(pagamento))
				throw new NegocioException(String.format("Forma de pagamento %s não é aceita pelo restaurante", pagamento.getNome()));
			
			
			pedido.getItens().forEach(item -> {
				var produto = buscarProdutoPorCodigo(pedido.getRestaurante().getId(), item.getProduto().getId());
				
				if (produto.isInativo())
					throw new NegocioException(String.format("Produto %s inativo", produto.getNome()));
								
				item.setPedido(pedido);
				item.setProduto(produto);
				item.setValorUnitario(produto.getValor());
				item.calcularValorTotal();				
			});
								
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
		pedido.setDataCricacao(OffsetDateTime.now());
		pedido.getEnderecoEntrega().setCidade(cidade);;
		pedido.setCliente(usuario);
		pedido.setRestaurante(restaurante);
		pedido.setPagamento(pagamento);
		pedido.setTaxaFrete(restaurante.getTaxaEntrega());
		pedido.calcularSubTotal();
		pedido.clacularValorTotal();
		
		return pedidoRepository.save(pedido);
	}
	
	public Pedido buscarPedidoPorCodigo(Long pedidoId) {
		Pedido pedido = pedidoRepository.findById(pedidoId)
				.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
		
		return pedido;
	}
	
	private Restaurante buscarRestaurantePorCodigo(Long restauranteId) {
		return restauranteService.buscarRestaurantePorCodigo(restauranteId);
	}
	
	private Usuario buscarUsuarioPorCodigo(Long usuarioId) {
		return usuarioService.buscarPorCodigo(usuarioId);
	}
	
	private Produto buscarProdutoPorCodigo(Long restauranteId, Long produtoId) {
		return produtoService.buscarProdutoPorCodigo(restauranteId, produtoId);
	}
	
	private Cidade buscarCidadePorCodigo(Long cidadeId) {
		return cidadeService.buscarCidadePorCodigo(cidadeId);
	}

	private FormaPagamento buscarFormaPaamentoPorCodigo(Long formaPagamentoId) {
		return formaPagamentoService.buscarPorId(formaPagamentoId);
	}
}
