package com.luizmariodev.luizfood.domain.repository;

import java.util.List;

import com.luizmariodev.luizfood.domain.model.Cidade;

public interface CidadeRepository {

	public List<Cidade> buscarTodos();
	public Cidade buscarPorId(Long id);
	public Cidade salvar(Cidade cidade);
	public void exlcuir(Long id);
	
}
