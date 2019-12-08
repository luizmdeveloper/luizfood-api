CREATE TABLE usuarios_grupos (
	codigo_usuario BIGINT NOT NULL,
    codigo_grupo BIGINT NOT NULL,
    
    PRIMARY KEY (codigo_usuario, codigo_grupo),
    CONSTRAINT fk_usuarios FOREIGN KEY (codigo_usuario) REFERENCES usuarios (id),
    CONSTRAINT usuarios_fk_grupos FOREIGN KEY (codigo_grupo) REFERENCES grupos (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;