SET SQL_SAFE_UPDATES = 0;
set foreign_key_checks = 0;

delete from cidades;
delete from cozinhas;
delete from estados;
delete from formas_pagamentos;
delete from grupos;
delete from grupos_autorizacoes;
delete from autorizacoes;
delete from produtos;
delete from restaurantes;
delete from restaurantes_formas_pagamentos;
delete from usuarios;
delete from usuarios_grupos;
delete from produtos;
delete from pedidos;
delete from itens_pedidos;

set foreign_key_checks = 1;

alter table cidades auto_increment = 1;
alter table cozinhas auto_increment = 1;
alter table estados auto_increment = 1;
alter table formas_pagamentos auto_increment = 1;
alter table grupos auto_increment = 1;
alter table autorizacoes auto_increment = 1;
alter table produtos auto_increment = 1;
alter table restaurantes auto_increment = 1;
alter table usuarios auto_increment = 1;
alter table produtos auto_increment = 1;
alter table pedidos auto_increment = 1;
alter table itens_pedidos auto_increment = 1;


/* inserção na tabelas de cozinhas */
INSERT INTO cozinhas (nome) VALUES ('Brasileira');
INSERT INTO cozinhas (nome) VALUES ('Japônesa');
INSERT INTO cozinhas (nome) VALUES ('Chinesa');
INSERT INTO cozinhas (nome) VALUES ('Árabe');
INSERT INTO cozinhas (nome) VALUES ('Lanches');

/* inserção na tabelas de estados */
INSERT INTO estados (nome) VALUES ('Pernambuco');
INSERT INTO estados (nome) VALUES ('Bahia');
INSERT INTO estados (nome) VALUES ('Paraíba');
INSERT INTO estados (nome) VALUES ('Ceará');
INSERT INTO estados (nome) VALUES ('Piauí');
INSERT INTO estados (nome) VALUES ('Marnhão');
INSERT INTO estados (nome) VALUES ('Rio grande do Norte');

/* inserção na tabelas de cidades */
INSERT INTO cidades (nome, codigo_estado) VALUES ('Petrolina', 1);
INSERT INTO cidades (nome, codigo_estado) VALUES ('Salgueiro', 1);
INSERT INTO cidades (nome, codigo_estado) VALUES ('Juazeiro', 2);

/*inserção na tabela de restaurantes */
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao, ativo, aberto) VALUES ('Nossa Senhora das Graças', '5600-000', 'R. Joaquim Sampaio', '', '95A', 'Wilson Pizzas', '5', '1', '2', utc_timestamp, utc_timestamp, true, false);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao, ativo, aberto) VALUES ('Atrás da Banca', '56308-155', 'Av. Cardoso de Sá', '', '1', 'Habibs Petrolina', '12', '4', '1', utc_timestamp, utc_timestamp, true, false);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao, ativo, aberto) VALUES ('Centro', '56302-000', 'Av. Guararapes', '', '2244', 'China Real', '8', '2', '1', utc_timestamp, utc_timestamp, true, false);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao, ativo, aberto) VALUES ('Centro', '48903-480', 'R. Juvêncio Alves', '', '2244', 'Kaori Sushi Bar', '15', '2', '3', utc_timestamp, utc_timestamp, true, false);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao, ativo, aberto) VALUES ('Centro', '48903-480', 'R. Juvêncio Alves', '', 'S/N', 'Kaori Sushi Bar', '15', '2', '3', utc_timestamp, utc_timestamp, true, false);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao, ativo, aberto) VALUES ('Centro', '48903-510', 'R. Góes Calmom', '', '6B', 'Saboreie Doces e Salgados', '5', '2', '3', utc_timestamp, utc_timestamp, true, false);

/* inserção na tabelas de formas de pagmanetos */
INSERT INTO formas_pagamentos (nome) VALUES ('Cartão de crédito');
INSERT INTO formas_pagamentos (nome) VALUES ('Cartão de débito');
INSERT INTO formas_pagamentos (nome) VALUES ('Dinheiro');

/* inserção na tabela de autorizações*/
insert into autorizacoes (nome, descricao) values ('ROLE_CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into autorizacoes (nome, descricao) values ('ROLE_EDITAR_COZINHAS', 'Permite editar cozinhas');

/* inserção na tabelas de formas de pagmanetos */
INSERT INTO restaurantes_formas_pagamentos (codigo_restaurante, codigo_forma_pagamento) VALUES (1,1);
INSERT INTO restaurantes_formas_pagamentos (codigo_restaurante, codigo_forma_pagamento) VALUES (1,2);
INSERT INTO restaurantes_formas_pagamentos (codigo_restaurante, codigo_forma_pagamento) VALUES (1,3);
INSERT INTO restaurantes_formas_pagamentos (codigo_restaurante, codigo_forma_pagamento) VALUES (2,1);
INSERT INTO restaurantes_formas_pagamentos (codigo_restaurante, codigo_forma_pagamento) VALUES (2,2);
INSERT INTO restaurantes_formas_pagamentos (codigo_restaurante, codigo_forma_pagamento) VALUES (3,3);

/* Inserindo usuários */
INSERT INTO usuarios (nome, email, senha, data_cadastro) VALUES ('Luiz Mário', 'luizmariodev@gmail.com', 'l849M@r10D3v', utc_timestamp);

/* Inserindo produtos */
INSERT INTO produtos (nome, descricao, valor, ativo, codigo_restaurante) values ('Pizza 4 queijos', 'massa, molho, mussarela, prado, gorgonzola, quoalho', 35.99, 1, 1);
INSERT INTO produtos (nome, descricao, valor, ativo, codigo_restaurante) values ('Pizza calabresa', 'massa, molho, mussarela, calabresa', 24.99, 1, 1);
INSERT INTO produtos (nome, descricao, valor, ativo, codigo_restaurante) values ('Pizza carnel de sol com queijo', 'massa, molho, mussarela, carne de sol desfiada', 49.99, 1, 1);

INSERT INTO produtos (nome, descricao, valor, ativo, codigo_restaurante) values ('Bib’s onion crispy', 'Feito com 200g de hambúrguer de fraldinha, queijo fatiado, mix de temperos, molho de alho e muita cebola crispy', 22.99, 1, 2);
INSERT INTO produtos (nome, descricao, valor, ativo, codigo_restaurante) values ('15 bib’sfihas', 'Leve 15 bib’sfihas do sabor que quiser, inclusive premiuns, por um único preço. Só não vale para esfihas doces.', 36.80, 1, 2);
INSERT INTO produtos (nome, descricao, valor, ativo, codigo_restaurante) values ('Filé de frango à parmegiana + bebida', 'Filé de frango à parmegiana + arroz branco + refri 350 ml ou suco 300 ml + sobremesa (esfiha de chocolate ou pastel de belém).', 39.80, 1, 2);

/* Inserindo pedidos */
INSERT INTO pedidos (codigo_restaurante, codigo_usuario, codigo_forma_pagamento, data_criacao, valor_total, valor_subtotal, taxa_frete, status, cep, logradouro, numero, bairro, codigo_cidade, codigo) VALUES (1,1,3, utc_timestamp, 126.97, 121.97, 5, 'CRIADO', 56331070, 'Rua Santa Rita', '31', 'Vila Eulália', 1, '16c17aa7-9269-11ea-8ab1-0c29effe30ba');
INSERT INTO pedidos (codigo_restaurante, codigo_usuario, codigo_forma_pagamento, data_criacao, valor_total, valor_subtotal, taxa_frete, status, cep, logradouro, numero, bairro, codigo_cidade, codigo) VALUES (2,1,2, utc_timestamp, 214.18,202.18,12, 'CRIADO', 56331070, 'Rua Santa Rita', '31', 'Vila Eulália', 1, '341b0f8b-9269-11ea-8ab1-0c29effe30ba');

INSERT INTO itens_pedidos (codigo_pedido, codigo_produto, quantidade, valor_unitario, valor_total) VALUES (1, 1, 2, 35.99, 71.98);
INSERT INTO itens_pedidos (codigo_pedido, codigo_produto, quantidade, valor_unitario, valor_total) VALUES (1, 2, 2, 49.99, 49.99);

INSERT INTO itens_pedidos (codigo_pedido, codigo_produto, quantidade, valor_unitario, valor_total) VALUES (2, 4, 2, 22.99, 45.98);
INSERT INTO itens_pedidos (codigo_pedido, codigo_produto, quantidade, valor_unitario, valor_total) VALUES (2, 5, 1, 36.80, 36.80);
INSERT INTO itens_pedidos (codigo_pedido, codigo_produto, quantidade, valor_unitario, valor_total) VALUES (2, 6, 3, 39.80, 119.40);

SET SQL_SAFE_UPDATES = 1;