package com.luizmariodev.luizfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.luizmariodev.luizfood.core.validator.GroupValidation;

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
	
	@NotBlank
	private String nome;
	
	@Column(name="taxa_entrega")
	private BigDecimal taxaEntrega;
	
	private Boolean ativo = Boolean.TRUE;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = GroupValidation.SalvarRestaurante.class)
	@ManyToOne
	@JoinColumn(name="codigo_cozinha", nullable = true)
	private Cozinha cozinha;
	
	@CreationTimestamp
	@Column(name="data_cadastro", nullable = true, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(name="data_ultima_atualizacao", nullable = true, columnDefinition = "datetime")
	private OffsetDateTime dataUltimaAtualizacao;
	
	@Embedded
	private Endereco endereco;
	
	@ManyToMany
	@JoinTable(name = "restaurantes_formas_pagamentos",
			   joinColumns = @JoinColumn(name="codigo_restaurante"),
			   inverseJoinColumns = @JoinColumn(name="codigo_forma_pagamento"))
	private List<FormaPagamento> pagamentos = new ArrayList<FormaPagamento>();
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}	
}
