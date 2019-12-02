package com.luizmariodev.luizfood.domain.repository;

import java.util.List;

import com.luizmariodev.luizfood.domain.model.Cozinha;

public interface CozinhaRepository {
	
	public List<Cozinha> buscarTodos();
	public Cozinha buscarPorId(Long id);
	public Cozinha salvar(Cozinha cozinha);
	public void excluir(Cozinha cozinha);

}
