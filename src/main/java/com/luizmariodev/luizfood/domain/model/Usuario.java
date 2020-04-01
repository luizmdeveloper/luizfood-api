package com.luizmariodev.luizfood.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String senha;
	
	@CreationTimestamp
	@Column(name="data_cadastro")
	private OffsetDateTime dataCadastro;
	
	@ManyToMany
	@JoinTable(name="usuarios_grupos",
			   joinColumns = @JoinColumn(name="codigo_usuario"),
			   inverseJoinColumns = @JoinColumn(name="codigo_grupo"))
	private List<Grupo> grupos = new ArrayList<Grupo>();

	public boolean isSenhaAtualConcide(String senhaAtual) {
		return this.senha.equals(senhaAtual);		
	}
	
	public boolean isSenhaAtualDiferenteSenha(String senhaAtual) {
		return !isSenhaAtualConcide(senhaAtual);
	}
}