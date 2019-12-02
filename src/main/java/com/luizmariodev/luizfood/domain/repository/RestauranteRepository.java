package com.luizmariodev.luizfood.domain.repository;

import java.util.List;

import com.luizmariodev.luizfood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	public List<Restaurante> buscarTodos();
	public Restaurante buscarPorId(Long id);
	public Restaurante salvar(Restaurante restaurante);
	public void excluir(Long id);

}
