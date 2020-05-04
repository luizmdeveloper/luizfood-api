package com.luizmariodev.luizfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizmariodev.luizfood.domain.exception.NegocioException;

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
	
	@NotBlank
	private String codigo;
	
	@ManyToOne
	@JoinColumn(name="codigo_restaurante")
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name="codigo_usuario")
	private Usuario cliente;

	@ManyToOne
	@JoinColumn(name="codigo_forma_pagamento")
	private FormaPagamento pagamento;
	
	@Column(name="data_criacao")
	private OffsetDateTime dataCricacao;
	
	@Column(name="data_confirmacao")
	private OffsetDateTime dataConfirmacao;
	
	@Column(name="data_cancelamento")
	private OffsetDateTime dataCancelamento;
	
	@Column(name="data_entrega")
	private OffsetDateTime dataEntrega;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@Column(name="valor_total")
	private BigDecimal valorTotal;
	
	@Column(name="valor_subtotal")
	private BigDecimal subTotal;
	
	@Column(name="taxa_frete")
	private BigDecimal taxaFrete;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();
	
	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
	}
	
	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());		
	}
	
	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());		
	}	

	public void calcularSubTotal() {
		subTotal = itens.stream()
					.map(item -> item.getValorTotal())
					.reduce(BigDecimal.ZERO, BigDecimal::add);		
	}

	public void clacularValorTotal() {
		valorTotal = subTotal.add(taxaFrete);	
	}

	@JsonIgnore
	public boolean isCriacdo() {
		return getStatus().equals(StatusPedido.CRIADO);
	}
	
	@JsonIgnore
	public boolean isConfirmado() {
		return getStatus().equals(StatusPedido.CONFIRMADO);
	}
	
	private void setStatus(StatusPedido novoStatus) {
		
		if (getStatus().naoPodeAlterarStatusPara(novoStatus)) {
			throw new NegocioException(String.format("Pedido %s não pode ser altera status %s para %s", getCodigo(), getStatus().getDescricao(), novoStatus.getDescricao()));			
		}
		
		this.status = novoStatus;
	}
	
	@PrePersist
	private void adicionarCodigoUUID() {
		this.codigo = UUID.randomUUID().toString();
	}

}
