package com.luizmariodev.luizfood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.api.model.AutorizacaoModel;
import com.luizmariodev.luizfood.domain.model.Autorizacao;

@Component
public class AutorizacaoModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;	
	
	public AutorizacaoModel toModel(Autorizacao autorizacao) {
		return modelMapper.map(autorizacao, AutorizacaoModel.class);
	}
	
	public List<AutorizacaoModel> toCollection(Collection<Autorizacao> autorizacoes) {
		return autorizacoes
				.stream()
				.map(permissao -> toModel(permissao))
				.collect(Collectors.toList());
	}

}
