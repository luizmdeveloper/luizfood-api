CREATE TABLE pedidos (
	id BIGINT NOT NULL AUTO_INCREMENT,
    codigo_restaurante BIGINT NOT NULL,
    codigo_usuario BIGINT NOT NULL,
    codigo_forma_pagamento BIGINT NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_cancelamento DATETIME,
    data_confirmacao DATETIME,
    data_entrega DATETIME NOT NULL,
    valor_total NUMERIC(15,2) NOT NULL,
    valor_subtotal NUMERIC(15,2) NOT NULL,
    taxa_frete NUMERIC(10,2) NOT NULL,
    status VARCHAR(10) NOT NULL,
    cep VARCHAR(10) NOT NULL,
	logradouro VARCHAR(80) NOT NULL,
	complemento VARCHAR(60) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	bairro VARCHAR(45) NOT NULL,
	codigo_cidade BIGINT NOT NULL,

    
    PRIMARY KEY(id),
    CONSTRAINT pedidos_fk_restaurantes FOREIGN KEY (codigo_restaurante) REFERENCES restaurantes (id),
    CONSTRAINT pedidos_fk_usuarios FOREIGN KEY (codigo_usuario) REFERENCES usuarios (id),
    CONSTRAINT pedidos_fk_formas_pagamentos FOREIGN KEY (codigo_usuario) REFERENCES formas_pagamentos (id),
    CONSTRAINT pedidos_fk_cidades FOREIGN KEY (codigo_cidade) REFERENCES cidades (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;