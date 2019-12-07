/* inserção na tabelas de cozinhas */
INSERT INTO cozinhas (nome) VALUES ('Brasileira');
INSERT INTO cozinhas (nome) VALUES ('Japônesa');
INSERT INTO cozinhas (nome) VALUES ('Chinesa');
INSERT INTO cozinhas (nome) VALUES ('Indiana');
INSERT INTO cozinhas (nome) VALUES ('Tailandesa');
INSERT INTO cozinhas (nome) VALUES ('Mexicana');

/*inserção na tabela de restaurantes */
INSERT INTO restaurantes (nome, taxa_entrega, codigo_cozinha) VALUES ('Wilson Pizzas', 5, 1);
INSERT INTO restaurantes (nome, taxa_entrega, codigo_cozinha) VALUES ('Kojima Lounge', 12, 1);
INSERT INTO restaurantes (nome, taxa_entrega, codigo_cozinha) VALUES ('China Real', 8, 3);

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