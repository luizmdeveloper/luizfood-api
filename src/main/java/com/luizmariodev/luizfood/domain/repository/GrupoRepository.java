package com.luizmariodev.luizfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luizmariodev.luizfood.domain.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
