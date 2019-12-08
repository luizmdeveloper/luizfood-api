CREATE TABLE grupo_autorizacoes(
	codigo_grupo BIGINT NOT NULL,
    codigo_autorizacao BIGINT NOT NULL,
    
    PRIMARY KEY(codigo_grupo, codigo_autorizacao),
    CONSTRAINT fk_grupos FOREIGN KEY (codigo_grupo) REFERENCES grupos (id),
    CONSTRAINT fk_autorizacoes FOREIGN KEY (codigo_autorizacao) REFERENCES autorizacoes (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;