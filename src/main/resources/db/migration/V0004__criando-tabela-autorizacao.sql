CREATE TABLE autorizacoes (
	id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    descricao VARCHAR(80) NOT NULL,
    
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8