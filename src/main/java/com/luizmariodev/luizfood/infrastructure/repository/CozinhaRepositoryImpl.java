package com.luizmariodev.luizfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Cozinha> buscarTodos() {
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	public Cozinha buscarPorId(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	public void remover(Cozinha cozinha) {
		manager.remove(cozinha);
	}
}
