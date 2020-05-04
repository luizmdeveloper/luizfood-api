package com.luizmariodev.luizfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luizmariodev.luizfood.api.model.input.PedidoModelInput;
import com.luizmariodev.luizfood.domain.model.Pedido;

@Component
public class PedidoModelInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Pedido toDomainObject(PedidoModelInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}	
}
