CREATE TABLE restaurantes_formas_pagamentos(
	codigo_restaurante BIGINT NOT NULL,
    codigo_forma_pagamento BIGINT NOT NULL,
    
    PRIMARY KEY (codigo_restaurante, codigo_forma_pagamento),
    CONSTRAINT restaurantes_formas_pagamentos_fk_restaurantes FOREIGN KEY (codigo_restaurante) REFERENCES restaurantes (id),
    CONSTRAINT fk_formas_pagamentos FOREIGN KEY (codigo_forma_pagamento) REFERENCES formas_pagamentos (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;