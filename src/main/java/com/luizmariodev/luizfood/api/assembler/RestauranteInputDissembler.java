package com.luizmariodev.luizfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.api.model.input.RestauranteModelInput;
import com.luizmariodev.luizfood.domain.model.Restaurante;

@Component
public class RestauranteInputDissembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteModelInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}	

}
