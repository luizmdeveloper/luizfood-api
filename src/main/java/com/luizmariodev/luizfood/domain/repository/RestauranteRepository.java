package com.luizmariodev.luizfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luizmariodev.luizfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	@Query("FROM Restaurante r JOIN FETCH r.cozinha LEFT JOIN FETCH r.endereco.cidade LEFT JOIN FETCH r.endereco.cidade.estado LEFT JOIN FETCH r.pagamentos")
	public List<Restaurante> findAll();
	
}
