/* inserção na tabelas de cozinhas */
INSERT INTO cozinhas (nome) VALUES ('Brasileira');
INSERT INTO cozinhas (nome) VALUES ('Japônesa');
INSERT INTO cozinhas (nome) VALUES ('Chinesa');
INSERT INTO cozinhas (nome) VALUES ('Árabe');

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

/*inserção na tabela de restaurantes */
INSERT INTO restaurantes (bairro, cep, logradouro, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao) VALUES ('Nossa Senhora das Graças', '56.00-000', 'R. Joaquim Sampaio', '95A', 'Wilson Pizzas', '5', '1', '2', now(), now());
INSERT INTO restaurantes (bairro, cep, logradouro, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao) VALUES ('Atrás da Banca', '56308-155', 'Av. Cardoso de Sá', '1', 'Habib\'s Petrolina', '12', '4', '1', now(), now());
INSERT INTO restaurantes (bairro, cep, logradouro, numero, nome, taxa_entrega, codigo_cozinha, codigo_cidade, data_cadastro, data_ultima_atualizacao) VALUES ('Centro', '56302-000', 'Av. Guararapes', '2244', 'China Real', '8', '2', '1', now(), now());

/* inserção na tabelas de formas de pagmanetos */
INSERT INTO formas_pagamentos (nome) VALUES ('Cartão de crédito');
INSERT INTO formas_pagamentos (nome) VALUES ('Cartão de débito');
INSERT INTO formas_pagamentos (nome) VALUES ('Dinheiro');

/* inserção na tabelas de formas de pagmanetos */
INSERT INTO restaurantes_formas_pagamentos (restaurante_id, forma_pagamento_id) VALUES (1,1);
INSERT INTO restaurantes_formas_pagamentos (restaurante_id, forma_pagamento_id) VALUES (1,2);
INSERT INTO restaurantes_formas_pagamentos (restaurante_id, forma_pagamento_id) VALUES (1,3);
INSERT INTO restaurantes_formas_pagamentos (restaurante_id, forma_pagamento_id) VALUES (2,1);
INSERT INTO restaurantes_formas_pagamentos (restaurante_id, forma_pagamento_id) VALUES (2,2);
INSERT INTO restaurantes_formas_pagamentos (restaurante_id, forma_pagamento_id) VALUES (3,3);