package com.luizmariodev.luizfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
@Table(name="pedidos")
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="codigo_restaurante")
	private Restaurante Restaurante;
	
	@ManyToOne
	@JoinColumn(name="codigo_usuario")
	private Usuario cliente;

	@ManyToOne
	@JoinColumn(name="codigo_forma_pagamento")
	private FormaPagamento pagamento;
	
	@Column(name="data_criacao")
	private LocalTime dataCricacao;
	
	@Column(name="data_confirmacao")
	private LocalTime dataConfirmacao;
	
	@Column(name="data_cancelamento")
	private LocalTime dataCancelamento;
	
	@Column(name="data_entrega")
	private LocalTime dataEntrega;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@Column(name="valor_total")
	private BigDecimal valorTotal;
	
	@Column(name="valor_subtotal")
	private BigDecimal subTotal;
	
	@Column(name="taxa_entrega")
	private BigDecimal taxaEntrega;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();

}