ALTER TABLE restaurantes 
 ADD ativo BOOLEAN NOT NULL;

UPDATE restaurantes SET
  ativo = true;