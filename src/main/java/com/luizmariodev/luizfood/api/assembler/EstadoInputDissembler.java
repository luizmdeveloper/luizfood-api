package com.luizmariodev.luizfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.api.model.input.EstadoModelInput;
import com.luizmariodev.luizfood.domain.model.Estado;

@Component
public class EstadoInputDissembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Estado toDomainObject(EstadoModelInput estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}	

}
