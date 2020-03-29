package com.luizmariodev.luizfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.api.model.input.CidadeModelInput;
import com.luizmariodev.luizfood.domain.model.Cidade;

@Component
public class CidadeModelInputDissembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeModelInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
}