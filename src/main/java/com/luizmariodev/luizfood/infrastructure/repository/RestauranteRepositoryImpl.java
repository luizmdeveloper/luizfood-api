package com.luizmariodev.luizfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.domain.model.Restaurante;
import com.luizmariodev.luizfood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Restaurante> buscarTodos() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	public Restaurante buscarPorId(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}

	@Transactional
	public void excluir(Long id) {
		Restaurante restaurante = buscarPorId(id);
		
		if (restaurante == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(restaurante);
	}

}
