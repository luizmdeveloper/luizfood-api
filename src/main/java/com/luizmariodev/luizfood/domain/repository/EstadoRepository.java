package com.luizmariodev.luizfood.domain.repository;

import java.util.List;

import com.luizmariodev.luizfood.domain.model.Estado;

public interface EstadoRepository {

	public List<Estado> buscarTodos();
	public Estado buscarPorId(Long id);
	public Estado salvar(Estado estado);
	public void excluir(Estado estado);
	
}
