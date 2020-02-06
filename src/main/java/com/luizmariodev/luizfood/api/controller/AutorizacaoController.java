package com.luizmariodev.luizfood.api.controller;

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
	public Autorizacao buscarPorId(@PathVariable Long id) {		
		return autorizacaoService.buscarAutorizacaoPorId(id);
	} 
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Autorizacao salvar(@RequestBody @Valid Autorizacao autorizacao) {
		return autorizacaoService.salvar(autorizacao);
	}
	
	@PutMapping("/{id}")
	public Autorizacao atualizar(@PathVariable Long id, @RequestBody @Valid Autorizacao autorizacao) {
		return autorizacaoService.atualizar(id, autorizacao);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		autorizacaoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
