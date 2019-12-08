package com.luizmariodev.luizfood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name="grupos")
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="grupos_permissoes",
			   joinColumns = @JoinColumn(name="gurpo_id"),
			   inverseJoinColumns = @JoinColumn(name="permissao_id"))
	private List<Permissao> permissoes = new ArrayList<Permissao>();

}
