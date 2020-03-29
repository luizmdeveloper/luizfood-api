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

import com.luizmariodev.luizfood.api.assembler.CidadeModelAssembler;
import com.luizmariodev.luizfood.api.assembler.CidadeModelInputDissembler;
import com.luizmariodev.luizfood.api.model.CidadeModel;
import com.luizmariodev.luizfood.api.model.input.CidadeModelInput;
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
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeModelInputDissembler cidadeModelInputDissembler;
	
	@GetMapping
	public List<CidadeModel> buscarTodos() {
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public CidadeModel buscarPorCodigo(@PathVariable Long id) {
		return cidadeModelAssembler.toModel(cidadeService.buscarCidadePorCodigo(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade salvar(@RequestBody @Valid CidadeModelInput cidadeInput) {
		return cidadeService.salvar(cidadeModelInputDissembler.toDomainObject(cidadeInput));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid CidadeModelInput cidadeInput) {
		Cidade cidadeSalva = cidadeService.atualizar(id, cidadeModelInputDissembler.toDomainObject(cidadeInput));
		cidadeModelInputDissembler.copyToDomainObject(cidadeInput, cidadeSalva);
		return ResponseEntity.ok(cidadeSalva);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		cidadeService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
