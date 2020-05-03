package com.luizmariodev.luizfood.api.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.api.assembler.GrupoModelAssembler;
import com.luizmariodev.luizfood.api.model.GrupoModel;
import com.luizmariodev.luizfood.domain.model.Grupo;
import com.luizmariodev.luizfood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@GetMapping
	public List<GrupoModel> buscar(@PathVariable Long usuarioId){
		var usuario = usuarioService.buscarPorCodigo(usuarioId);
		Set<Grupo> todosGurposDoUsuario = usuario.getGrupos();
		return grupoModelAssembler.toCollectionModel(todosGurposDoUsuario);
	}
	
	@PutMapping("/{grupoId}")
	public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.associar(usuarioId, grupoId);
	}
	
	@DeleteMapping("/{grupoId}")
	public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.desassociar(usuarioId, grupoId);
	}
}
