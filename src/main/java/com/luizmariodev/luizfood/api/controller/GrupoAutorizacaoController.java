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

import com.luizmariodev.luizfood.api.assembler.AutorizacaoModelAssembler;
import com.luizmariodev.luizfood.api.model.AutorizacaoModel;
import com.luizmariodev.luizfood.domain.model.Autorizacao;
import com.luizmariodev.luizfood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos/{grupoId}/autorizacoes")
public class GrupoAutorizacaoController {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private AutorizacaoModelAssembler autorizacaoModelAssembler;
		
	@GetMapping
	public List<AutorizacaoModel> buscar(@PathVariable Long grupoId) {
		var grupo = grupoService.buscarPorId(grupoId);
		Set<Autorizacao> todasAutorizacoes = grupo.getAutorizacoes();
		return autorizacaoModelAssembler.toCollection(todasAutorizacoes); 
	}
	
	@PutMapping("/{autorizacaoId}")
	public void associar(@PathVariable Long grupoId, @PathVariable Long autorizacaoId) {
		grupoService.associar(grupoId, autorizacaoId);
	}
	
	@DeleteMapping("/{autorizacaoId}")
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long autorizacaoId) {
		grupoService.desassociar(grupoId, autorizacaoId);
	}
}
