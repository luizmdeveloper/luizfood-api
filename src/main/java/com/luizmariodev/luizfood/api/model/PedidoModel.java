package com.luizmariodev.luizfood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {
	
	private Long id;
	private RestauranteResumoModel restaurante;
	private UsuarioModel cliente;
	private FormaPagamentoModel pagamento;
	private OffsetDateTime dataCricacao;	
	private OffsetDateTime dataConfirmacao;	
	private OffsetDateTime dataCancelamento;	
	private OffsetDateTime dataEntrega;	
	private EnderecoResumoModel enderecoEntrega;	
	private BigDecimal valorTotal;	
	private BigDecimal subTotal;	
	private BigDecimal taxaFrete;	
	private String status;
	private List<ItemPedidoModel> itens;

}
