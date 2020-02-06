package com.luizmariodev.luizfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum TipoProblema {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"), 
	MENSAGEM_INCROPREENSIVEL("/mensagem-incropreensivel", "Mensagem incropreensível"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não foi encontrado"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS("/erro-dados-invalidos", "Dados inválido");;
	
	private String titulo;
	private String path;
	
	private TipoProblema(String path, String titulo) {
		this.path = "https://api.luizmariofood.com.br" + path;
		this.titulo = titulo;
	}

}
