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
import com.luizmariodev.luizfood.domain.model.Autorizacao;
import com.luizmariodev.luizfood.domain.repository.AutorizacaoRepository;
import com.luizmariodev.luizfood.domain.service.AutorizacaoService;

@RestController
@RequestMapping("/autorizacoes")
public class AutorizacaoController {
	
	@Autowired
	private AutorizacaoRepository autorizacaoRepository;
	
	@Autowired
	private AutorizacaoService autorizacaoService;
		
	@GetMapping
	public List<Autorizacao> buscarTodos() {
		return autorizacaoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Autorizacao> buscarPorId(@PathVariable Long id) {
		Optional<Autorizacao> autorizacao = autorizacaoRepository.findById(id);
		
		if (autorizacao.isPresent()) {
			return ResponseEntity.ok(autorizacao.get());
		}
		
		return ResponseEntity.notFound().build();
	} 
	
	@PostMapping
	public Autorizacao salvar(@RequestBody Autorizacao autorizacao) {
		return autorizacaoService.salvar(autorizacao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Autorizacao autorizacao) {
		try {
			Autorizacao autorizacaoSalva = autorizacaoService.atualizar(id, autorizacao);
			return ResponseEntity.ok(autorizacaoSalva);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		try {
			autorizacaoService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
