package com.luizmariodev.luizfood.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizmariodev.luizfood.domain.exception.NegocioException;
import com.luizmariodev.luizfood.domain.exception.UsuarioNaoEncontradoException;
import com.luizmariodev.luizfood.domain.model.Grupo;
import com.luizmariodev.luizfood.domain.model.Usuario;
import com.luizmariodev.luizfood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GrupoService grupoService;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach(usuario);
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}
		
		return usuarioRepository.save(usuario);
	}

	@Transactional
	public Usuario atualizar(Long usuarioId, Usuario usuario) {
		var usuarioSalvo = buscarPorCodigo(usuarioId);		
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id", "dataCadastro", "senha");		
		return usuarioRepository.save(usuarioSalvo);
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

	@Transactional
	public void associar(Long usuarioId, Long grupoId) {
		var usuario = buscarPorCodigo(usuarioId);
		var grupo = buscarGrupoPorCodigo(grupoId);
		usuario.adicionar(grupo);
	}
	
	@Transactional
	public void desassociar(Long usuarioId, Long grupoId) {
		var usuario = buscarPorCodigo(usuarioId);
		var grupo = buscarGrupoPorCodigo(grupoId);
		usuario.remover(grupo);
	}
	
	private Grupo buscarGrupoPorCodigo(Long grupoId) {
		return grupoService.buscarPorId(grupoId);
	}
	
}