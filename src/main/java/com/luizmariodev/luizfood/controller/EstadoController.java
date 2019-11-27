package com.luizmariodev.luizfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.domain.model.Estado;
import com.luizmariodev.luizfood.domain.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> buscar() {
		return estadoRepository.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
		Estado estado = estadoRepository.buscarPorId(id);
		
		if (estado != null) {
			return ResponseEntity.ok(estado);
		} 
		
		return ResponseEntity.notFound().build();
	}
}
