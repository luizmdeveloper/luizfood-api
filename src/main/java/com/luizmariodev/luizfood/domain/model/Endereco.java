package com.luizmariodev.luizfood.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String numero;
	private String bairro;
	
	@ManyToOne
	@JoinColumn(name="codigo_cidade")
	private Cidade cidade; 

}
