package com.luizmariodev.luizfood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.model.FormaPagamento;
import com.luizmariodev.luizfood.domain.model.Produto;

public class RestauranteMixin {
	
	@JsonIgnoreProperties(value="nome", allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private OffsetDateTime dataCadastro;

	@JsonIgnore
	private OffsetDateTime dataUltimaAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamento> pagamentos = new ArrayList<FormaPagamento>();
	
	@JsonIgnore
	private List<Produto> produtos = new ArrayList<Produto>();	
}