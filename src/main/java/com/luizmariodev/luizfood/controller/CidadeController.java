package com.luizmariodev.luizfood.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.repository.CidadeRepository;
import com.luizmariodev.luizfood.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public List<Cidade> buscarTodos() {
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable Long id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		
		if (cidade.isPresent()) {
			return ResponseEntity.ok(cidade);
		} 
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {
		try {
			cidadeService.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
		try {
			Cidade cidadeSalva = cidadeService.atualizar(id, cidade);
			return ResponseEntity.ok(cidadeSalva);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		try {
			cidadeService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
