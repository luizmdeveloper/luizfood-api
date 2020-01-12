package com.luizmariodev.luizfood.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.luizmariodev.luizfood.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public List<Cozinha> buscar(){
		return cozinhaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Cozinha buscarPorCodigo(@PathVariable Long id) {		
		return cozinhaService.buscarCozinhaPorId(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody @Valid Cozinha cozinha) {
		return cozinhaService.salvar(cozinha);
	}
	
	@PutMapping("/{id}")
	public Cozinha atualizar(@PathVariable Long id, @RequestBody @Valid Cozinha cozinha){
		return cozinhaService.atualizar(id, cozinha);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		cozinhaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
