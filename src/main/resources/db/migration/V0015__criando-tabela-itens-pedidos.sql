CREATE TABLE itens_pedidos (
	id BIGINT NOT NULL AUTO_INCREMENT,
    codigo_pedido BIGINT NOT NULL,
    codigo_produto BIGINT NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario NUMERIC(10,2) NOT NULL,
    valor_total NUMERIC(15,2) NOT NULL,
    observacao VARCHAR(255),
    
    PRIMARY KEY(id),
    CONSTRAINT itens_pedidos_fk_pedidos FOREIGN KEY (codigo_pedido) REFERENCES pedidos (id),
    CONSTRAINT itens_pedidos_fk_produtos FOREIGN KEY (codigo_produto) REFERENCES produtos (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;