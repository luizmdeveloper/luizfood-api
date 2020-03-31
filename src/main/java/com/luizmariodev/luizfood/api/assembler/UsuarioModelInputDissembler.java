package com.luizmariodev.luizfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.api.model.input.UsuarioModelInput;
import com.luizmariodev.luizfood.domain.model.Usuario;

@Component
public class UsuarioModelInputDissembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioModelInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
}
