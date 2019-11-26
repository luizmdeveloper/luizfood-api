package com.luizmariodev.luizfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Cidade> buscarTodos() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	public Cidade buscarPorId(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Transactional
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Transactional
	public void exlcuir(Cidade cidade) {
		manager.remove(cidade);		
	}

}
