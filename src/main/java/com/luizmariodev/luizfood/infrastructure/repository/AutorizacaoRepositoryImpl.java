package com.luizmariodev.luizfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.domain.model.Autorizacao;
import com.luizmariodev.luizfood.domain.repository.AutorizacaoRepository;

@Component
public class AutorizacaoRepositoryImpl implements AutorizacaoRepository {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Autorizacao> buscarTodas() {
		return manager.createQuery("from Autorizacao", Autorizacao.class).getResultList();
	}

	public Autorizacao buscarPorId(Long id) {
		return manager.find(Autorizacao.class, id);
	}

	public Autorizacao salvar(Autorizacao autorizacao) {
		return manager.merge(autorizacao);
	}

	public void excluir(Autorizacao autorizacao) {
		manager.remove(autorizacao);
		
	}

}
