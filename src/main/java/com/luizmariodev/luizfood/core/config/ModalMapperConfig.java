package com.luizmariodev.luizfood.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luizmariodev.luizfood.api.model.RestauranteModel;
import com.luizmariodev.luizfood.domain.model.Restaurante;

@Configuration
public class ModalMapperConfig {
	
	@Bean
	public ModelMapper modalMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
			.addMapping(Restaurante::getTaxaEntrega, RestauranteModel::setPrecoEntrega);
		
		return modelMapper;
	}

}
