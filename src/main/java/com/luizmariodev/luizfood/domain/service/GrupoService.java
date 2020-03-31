package com.luizmariodev.luizfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.GrupoNaoEncontradoException;
import com.luizmariodev.luizfood.domain.model.Grupo;
import com.luizmariodev.luizfood.domain.repository.GrupoRepository;

@Service
public class GrupoService {
	
	private static final String GRUPO_NAO_PODE_EXCLUIDO = "Grupo com código %d, não pode ser excluído";
	
	@Autowired
	private GrupoRepository grupoRepository;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Transactional
	public Grupo atualizar(Long grupoId, Grupo grupo) {
		Grupo grupoSalvo = buscarPorId(grupoId);
		BeanUtils.copyProperties(grupo, grupoSalvo, "id");
		return grupoRepository.save(grupoSalvo);
	}
	
	@Transactional
	public void excluir(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();			
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(GRUPO_NAO_PODE_EXCLUIDO, grupoId));
		}
	}
	
	public Grupo buscarPorId(Long grupoId) {
		Grupo grupo = grupoRepository.findById(grupoId)
					.orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));				
		return grupo;
	}
}
