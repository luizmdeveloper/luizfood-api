package com.luizmariodev.luizfood.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luizmariodev.luizfood.api.model.EnderecoModel;
import com.luizmariodev.luizfood.api.model.input.ItemPedidoModelInput;
import com.luizmariodev.luizfood.domain.model.Endereco;
import com.luizmariodev.luizfood.domain.model.ItemPedido;

@Configuration
public class ModalMapperConfig {
	
	@Bean
	public ModelMapper modalMapper() {
		var modelMapper = new ModelMapper();
		
//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//			.addMapping(Restaurante::getTaxaEntrega, RestauranteModel::setPrecoEntrega);
		
		modelMapper.createTypeMap(ItemPedidoModelInput.class, ItemPedido.class)
			.addMappings(map -> map.skip(ItemPedido::setId));
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);		
		
		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(), 
				(enderecoModelDestino, value) -> enderecoModelDestino.getCidade().setEstado(value)
		);
	
		return modelMapper;
	}

}
