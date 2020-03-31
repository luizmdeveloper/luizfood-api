package com.luizmariodev.luizfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luizmariodev.luizfood.api.assembler.UsuarioModelAssembler;
import com.luizmariodev.luizfood.api.assembler.UsuarioModelInputDissembler;
import com.luizmariodev.luizfood.api.model.UsuarioModel;
import com.luizmariodev.luizfood.api.model.input.UsuarioAlteraSenhaModelInput;
import com.luizmariodev.luizfood.api.model.input.UsuarioComSenhaModelInput;
import com.luizmariodev.luizfood.api.model.input.UsuarioModelInput;
import com.luizmariodev.luizfood.domain.repository.UsuarioRepository;
import com.luizmariodev.luizfood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioModelInputDissembler usuarioModelDissembler;
	
	@GetMapping
	public List<UsuarioModel> buscarTodos() {
		return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscarPorCodigo(@PathVariable Long usuarioId) {
		return usuarioModelAssembler.toModel(usuarioService.buscarPorCodigo(usuarioId));		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel salvar(@Valid @RequestBody UsuarioComSenhaModelInput usuarioInput) {
		var usuario = usuarioService.salvar(usuarioModelDissembler.toDomainObject(usuarioInput)); 			
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioModelInput usuarioModelInput) {
		var usuario = usuarioService.atualizar(usuarioId, usuarioModelDissembler.toDomainObject(usuarioModelInput));
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@PutMapping("/{usuarioId}/senha")
	public void alterarSenha(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioAlteraSenhaModelInput usuarioAlteraSenhaInput) {
		usuarioService.atualizarSenha(usuarioId, usuarioAlteraSenhaInput.getSenhaAtual(), usuarioAlteraSenhaInput.getNovaSenha());
	}
	
	
}
