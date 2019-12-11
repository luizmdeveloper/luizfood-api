package com.luizmariodev.luizfood.controller;

import java.util.List;

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
	
	@GetMapping
	public List<Estado> buscar() {
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Estado buscarPorId(@PathVariable Long id) {
		return estadoService.buscarEstadoPorCodigo(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado salvar(@RequestBody Estado estado) {
		return estadoService.salvar(estado);
	}
	
	@PutMapping("/{id}")
	public Estado atualizar(@PathVariable Long id, @RequestBody Estado estado) {				
		return estadoService.atualizar(id, estado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		estadoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
