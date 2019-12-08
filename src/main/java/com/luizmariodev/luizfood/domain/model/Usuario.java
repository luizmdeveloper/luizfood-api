package com.luizmariodev.luizfood.domain.model;

import java.time.LocalTime;
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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name="usuarios")
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String senha;
	
	@CreationTimestamp
	@JoinColumn(name="data_cadastro")
	private LocalTime dataCadastro;
	
	@ManyToMany
	@JoinTable(name="usuarios_grupos",
			   joinColumns = @JoinColumn(name="codigo_usuario"),
			   inverseJoinColumns = @JoinColumn(name="codigo_grupo"))
	private List<Grupo> grupos = new ArrayList<Grupo>();
}