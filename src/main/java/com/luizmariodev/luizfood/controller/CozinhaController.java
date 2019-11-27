package com.luizmariodev.luizfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping
	public List<Cozinha> buscar(){
		return cozinhaRepository.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscarPorCodigo(@PathVariable Long id) {
		Cozinha cozinha = cozinhaRepository.buscarPorId(id);
		
		if (cozinha != null)
			return ResponseEntity.ok(cozinha);
		else
			return ResponseEntity.notFound().build();
				
	}
}
