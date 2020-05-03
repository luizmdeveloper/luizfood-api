package com.luizmariodev.luizfood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResumoModel {
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String numero;
	private String bairro;
	private String cidadeNome;

}
