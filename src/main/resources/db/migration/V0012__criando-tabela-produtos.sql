CREATE TABLE produtos (
	id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    ativo SMALLINT(1) NOT NULL,
    codigo_restaurante BIGINT NOT NULL,
    
    PRIMARY KEY (id),
    CONSTRAINT fk_restaurantes FOREIGN KEY (codigo_restaurante) REFERENCES restaurantes (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;