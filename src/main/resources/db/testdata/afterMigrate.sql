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
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao) VALUES ('Nossa Senhora das Graças', '5600-000', 'R. Joaquim Sampaio', '', '95A', 'Wilson Pizzas', '5', '1', '2', utc_timestamp, utc_timestamp);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao) VALUES ('Atrás da Banca', '56308-155', 'Av. Cardoso de Sá', '', '1', 'Habibs Petrolina', '12', '4', '1', utc_timestamp, utc_timestamp);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao) VALUES ('Centro', '56302-000', 'Av. Guararapes', '', '2244', 'China Real', '8', '2', '1', utc_timestamp, utc_timestamp);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao) VALUES ('Centro', '48903-480', 'R. Juvêncio Alves', '', '2244', 'Kaori Sushi Bar', '15', '2', '3', utc_timestamp, utc_timestamp);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao) VALUES ('Centro', '48903-480', 'R. Juvêncio Alves', '', 'S/N', 'Kaori Sushi Bar', '15', '2', '3', utc_timestamp, utc_timestamp);
INSERT INTO restaurantes (bairro, cep, logradouro, complemento, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao) VALUES ('Centro', '48903-510', 'R. Góes Calmom', '', '6B', 'Saboreie Doces e Salgados', '5', '2', '3', utc_timestamp, utc_timestamp);

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

SET SQL_SAFE_UPDATES = 1;