package com.luizmariodev.luizfood.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){
		Cozinha cozinhaAtual = cozinhaRepository.buscarPorId(id);
		
		if (cozinhaAtual != null) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinhaRepository.salvar(cozinhaAtual);
			return ResponseEntity.ok(cozinhaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		Cozinha cozinha = cozinhaRepository.buscarPorId(id);
		
		if (cozinha != null) {
			cozinhaRepository.remover(cozinha);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();	
	}
}
