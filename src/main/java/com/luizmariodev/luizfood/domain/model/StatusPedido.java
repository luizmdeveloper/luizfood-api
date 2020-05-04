package com.luizmariodev.luizfood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
	
	CRIADO("Criado"),
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO),
	CANCELADO("Cancelado", CRIADO);
	
	private String descricao;
	private List<StatusPedido> statusAnteriores;
	
	private StatusPedido(String descricao, StatusPedido...statusPedidos) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusPedidos);
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public boolean naoPodeAlterarStatusPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}

}
