ALTER TABLE pedidos
  ADD COLUMN codigo VARCHAR(36) NOT NULL;
  
SET SQL_SAFE_UPDATES = 0;
  
UPDATE pedidos SET
	codigo = UUID();  

SET SQL_SAFE_UPDATES = 1;
  
ALTER TABLE pedidos  
  ADD UNIQUE INDEX pedidos_uk_codigo (codigo);