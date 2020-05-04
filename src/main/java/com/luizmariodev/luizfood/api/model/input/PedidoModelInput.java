package com.luizmariodev.luizfood.api.model.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModelInput {
	
	private Long id;
	
	@NotNull
	@Valid
	private RestauranteIdInputModel restaurante;
	
	@NotNull
	@Valid
	private UsuarioIdModelInput cliente;

	@NotNull
	@Valid
	private FormaPagamentoIdModelInput pagamento;
	
	@NotNull
	@Valid
	private EnderecoModelInput enderecoEntrega;

	@NotNull
	@Size(min = 1)
	@Valid
	private List<ItemPedidoModelInput> itens = new ArrayList<ItemPedidoModelInput>();
	
}