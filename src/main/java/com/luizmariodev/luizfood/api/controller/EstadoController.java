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

import com.luizmariodev.luizfood.api.assembler.EstadoInputDissembler;
import com.luizmariodev.luizfood.api.assembler.EstadoModelAssembler;
import com.luizmariodev.luizfood.api.model.EstadoModel;
import com.luizmariodev.luizfood.api.model.input.EstadoModelInput;
import com.luizmariodev.luizfood.domain.model.Estado;
import com.luizmariodev.luizfood.domain.repository.EstadoRepository;
import com.luizmariodev.luizfood.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@Autowired
	private EstadoInputDissembler estadoInputDissembler;
	
	@GetMapping
	public List<EstadoModel> buscar() {
		return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public EstadoModel buscarPorId(@PathVariable Long id) {
		return estadoModelAssembler.toModel(estadoService.buscarEstadoPorCodigo(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado salvar(@RequestBody @Valid EstadoModelInput estadoInput) {
		return estadoService.salvar(estadoInputDissembler.toDomainObject(estadoInput));
	}
	
	@PutMapping("/{id}")
	public Estado atualizar(@PathVariable Long id, @RequestBody @Valid EstadoModelInput estadoInput) {
		return estadoService.atualizar(id, estadoInputDissembler.toDomainObject(estadoInput));			
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		estadoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
