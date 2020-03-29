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

import com.luizmariodev.luizfood.api.assembler.FormaPagamentoInputDissembler;
import com.luizmariodev.luizfood.api.assembler.FormaPagamentoModelAssembler;
import com.luizmariodev.luizfood.api.model.FormaPagamentoModel;
import com.luizmariodev.luizfood.api.model.input.FormaPagamentoModelInput;
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
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@Autowired
	private FormaPagamentoInputDissembler formaPagamentoInputDissembler;
	
	@GetMapping
	public List<FormaPagamentoModel> buscarTodos() {
		return formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public FormaPagamentoModel buscarPorId(@PathVariable Long id) {
		return formaPagamentoModelAssembler.toModel(formaPagamentoService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento salvar(@RequestBody @Valid FormaPagamentoModelInput formaPagamentoInput) {
		return formaPagamentoService.salvar(formaPagamentoInputDissembler.toDomainObject(formaPagamentoInput));
	}
	
	@PutMapping("/{id}")
	public FormaPagamento atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoModelInput formaPagamentoInput) {		
		return formaPagamentoService.atualizar(id, formaPagamentoInputDissembler.toDomainObject(formaPagamentoInput));
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		formaPagamentoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
