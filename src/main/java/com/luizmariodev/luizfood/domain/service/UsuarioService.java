package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizmariodev.luizfood.domain.exception.NegocioException;
import com.luizmariodev.luizfood.domain.exception.UsuarioNaoEncontradoException;
import com.luizmariodev.luizfood.domain.model.Usuario;
import com.luizmariodev.luizfood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Transactional
	public Usuario atualizar(Long usuarioId, Usuario usuario) {
		var usuarioSalvo = buscarPorCodigo(usuarioId);		
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void atualizarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		var usuario = buscarPorCodigo(usuarioId);
		
		if (usuario.isSenhaAtualDiferenteSenha(senhaAtual))
			throw new NegocioException("Senha atual diverge da cadastrada. Por favor, insira a senha correta.");
		
		usuario.setSenha(novaSenha);		
	}
	
	public Usuario buscarPorCodigo(Long usuarioId) {
		var usuario = usuarioRepository.findById(usuarioId)
						.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
		
		return usuario;
	}




}
