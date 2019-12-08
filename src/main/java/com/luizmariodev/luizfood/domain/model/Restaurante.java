package com.luizmariodev.luizfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "restaurantes")
@Entity
public class Restaurante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(name="taxa_entrega")
	private BigDecimal taxaEntrega;
	
	@ManyToOne
	@JoinColumn(name="codigo_cozinha")
	private Cozinha cozinha;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name="data_cadastro", nullable = true)
	private LocalTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(name="data_ultima_atualizacao", nullable = true)
	private LocalTime dataUltimaAtualizacao;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurantes_formas_pagamentos",
			   joinColumns = @JoinColumn(name="restaurante_id"),
			   inverseJoinColumns = @JoinColumn(name="forma_pagamento_id"))
	private List<FormaPagamento> pagamentos = new ArrayList<FormaPagamento>();
}
