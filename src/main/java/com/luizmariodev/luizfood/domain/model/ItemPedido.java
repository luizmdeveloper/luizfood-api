package com.luizmariodev.luizfood.domain.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
@Table(name="itens_pedidos")
public class ItemPedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="codigo_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="codigo_produto")
	private Produto produto;
	
	private BigInteger quantidade;
	
	@Column(name="valor_unitario")
	private BigDecimal valorUnitario;
	
	@Column(name="valor_total")
	private BigDecimal valorTotal;
	
	private String observacao;
}
