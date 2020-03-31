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

import com.luizmariodev.luizfood.api.assembler.GrupoModelAssembler;
import com.luizmariodev.luizfood.api.assembler.GrupoModelInputDissembler;
import com.luizmariodev.luizfood.api.model.GrupoModel;
import com.luizmariodev.luizfood.api.model.input.GrupoModelInput;
import com.luizmariodev.luizfood.domain.repository.GrupoRepository;
import com.luizmariodev.luizfood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoModelInputDissembler grupoModelInputDissembler;	
	
	@GetMapping
	public List<GrupoModel> buscar() {
		return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
	}
	
	@GetMapping("/{grupoId}")
	public GrupoModel buscarPorCodigo(@PathVariable Long grupoId) {
		return grupoModelAssembler.toModel(grupoService.buscarPorId(grupoId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel salvar(@RequestBody @Valid GrupoModelInput grupoModelInput) {
		var grupoSalvo = grupoService.salvar(grupoModelInputDissembler.toDomainObject(grupoModelInput)); 
		return grupoModelAssembler.toModel(grupoSalvo);
	}
	
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoModelInput grupoModelInput) {
		var grupoSalvo = grupoService.atualizar(grupoId, grupoModelInputDissembler.toDomainObject(grupoModelInput));
		return grupoModelAssembler.toModel(grupoSalvo);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		grupoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
