package com.luizmariodev.luizfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.api.model.input.RestauranteModelInput;
import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.model.Restaurante;

@Component
public class RestauranteInputDissembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteModelInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}	
	
	public void copyToDomainObject(RestauranteModelInput restauranteInput, Restaurante restaurante) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.luizmariodev.luizfood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());	

		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.luizmariodev.luizfood.domain.model.Endereco was altered from 1 to 2		
		if (restaurante.getEndereco().getCidade() != null) {
			restaurante.getEndereco().setCidade(new Cidade());			
		}
		
		modelMapper.map(restauranteInput, restaurante);
	}

}
