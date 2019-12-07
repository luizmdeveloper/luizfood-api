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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.FormaPagamento;
import com.luizmariodev.luizfood.domain.repository.FormaPagamentoRepository;
import com.luizmariodev.luizfood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@GetMapping
	public List<FormaPagamento> buscarTodos() {
		return formaPagamentoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamento> buscarPorId(@PathVariable Long id) {
		Optional<FormaPagamento> optionalPagamento = formaPagamentoRepository.findById(id);
		
		if (optionalPagamento.isPresent()) {
			return ResponseEntity.ok(optionalPagamento.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento salvar(@RequestBody FormaPagamento formaPagamento) {
		return formaPagamentoService.salvar(formaPagamento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {		
		try {
			FormaPagamento formaPagamentoSalvo = formaPagamentoService.atualizar(id, formaPagamento);
			return ResponseEntity.ok(formaPagamentoSalvo);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}		
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		try {
			formaPagamentoService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntidadeEmUsoException e) {
			return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}		
	}
}
