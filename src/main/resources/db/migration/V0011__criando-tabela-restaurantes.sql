CREATE TABLE restaurantes (
	id BIGINT NOT NULL,
	taxa_entrega NUMERIC(8,2) NOT NULL,
	codigo_cozinha BIGINT NOT NULL,
	data_cadastro DATETIME NOT NULL,
	data_ultima_atualizacao DATETIME NOT NULL,
	cep VARCHAR(10) NOT NULL,
	logradouro VARCHAR(80) NOT NULL,
	complemento VARCHAR(60) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	bairro VARCHAR(45) NOT NULL,
	codigo_cidade BIGINT NOT NULL,
	
	PRIMARY KEY (id),
	CONSTRAINT fk_cozinhas FOREIGN KEY (codigo_cozinha) REFERENCES cozinhas (id),
	CONSTRAINT restaurantes_fk_cidades FOREIGN KEY (codigo_cidade) REFERENCES cidades (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;