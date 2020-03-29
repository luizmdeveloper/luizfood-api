package com.luizmariodev.luizfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.api.model.input.CidadeModelInput;
import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.model.Estado;

@Component
public class CidadeModelInputDissembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeModelInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeModelInput cidadeInput, Cidade cidade) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.luizmariodev.luizfood.domain.model.Cidade was altered from 1 to 2
		cidade.setEstado(new Estado());		
		modelMapper.map(cidadeInput, cidade);
	}	
}