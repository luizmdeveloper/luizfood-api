package com.luizmariodev.luizfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.api.model.input.GrupoModelInput;
import com.luizmariodev.luizfood.domain.model.Grupo;

@Component
public class GrupoModelInputDissembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Grupo toDomainObject(GrupoModelInput grupoModel) {
		return modelMapper.map(grupoModel, Grupo.class);
	}
}
