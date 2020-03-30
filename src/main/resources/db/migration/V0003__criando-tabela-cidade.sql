CREATE TABLE cidades (
	id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    codigo_estado BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    CONSTRAINT fk_cidades FOREIGN KEY (codigo_estado) REFERENCES estados (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;