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
	public FormaPagamento buscarPorId(@PathVariable Long id) {
		return formaPagamentoService.buscarPorId(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento salvar(@RequestBody @Valid FormaPagamento formaPagamento) {
		return formaPagamentoService.salvar(formaPagamento);
	}
	
	@PutMapping("/{id}")
	public FormaPagamento atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamento formaPagamento) {		
		return formaPagamentoService.atualizar(id, formaPagamento);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		formaPagamentoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
