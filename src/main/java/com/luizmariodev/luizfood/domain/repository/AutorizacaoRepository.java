package com.luizmariodev.luizfood.domain.repository;

import java.util.List;

import com.luizmariodev.luizfood.domain.model.Autorizacao;

public interface AutorizacaoRepository {

	public List<Autorizacao> buscarTodas();
	public Autorizacao buscarPorId(Long id);
	public Autorizacao salvar(Autorizacao autorizacao);
	public void excluir(Autorizacao autorizacao);
}
