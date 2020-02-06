package com.luizmariodev.luizfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.luizmariodev.luizfood.api.model.mixin.CidadeMixin;
import com.luizmariodev.luizfood.api.model.mixin.RestauranteMixin;
import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.model.Restaurante;

@Component
public class JacksonModule extends SimpleModule  {

	private static final long serialVersionUID = 1L;

	public JacksonModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	}
	
}