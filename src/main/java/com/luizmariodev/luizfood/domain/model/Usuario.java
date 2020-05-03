package com.luizmariodev.luizfood.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

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
			   joinColumns = @JoinColumn(name="codigo_grupo"),
			   inverseJoinColumns = @JoinColumn(name="codigo_usuario"))
	private Set<Grupo> grupos = new HashSet<Grupo>();

	public boolean isSenhaAtualConcide(String senhaAtual) {
		return this.senha.equals(senhaAtual);		
	}
	
	public boolean isSenhaAtualDiferenteSenha(String senhaAtual) {
		return !isSenhaAtualConcide(senhaAtual);
	}
	
	public boolean adicionar(Grupo grupo) {
		return getGrupos().add(grupo);
	}
	
	public boolean remover(Grupo grupo) {
		return getGrupos().remove(grupo);
	}	
}