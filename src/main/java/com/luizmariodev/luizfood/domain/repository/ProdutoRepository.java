package com.luizmariodev.luizfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luizmariodev.luizfood.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query("from Produto where restaurante.id = :restauranteId")
	public List<Produto> findByRestaurante(@Param("restauranteId") Long restauranteId);
	
	@Query("from Produto where id = :produtoId and restaurante.id = :restauranteId")
	public Optional<Produto> findByRestauranteAndProduto(@Param("produtoId") Long produtoId, @Param("restauranteId") Long restauranteId);

}
