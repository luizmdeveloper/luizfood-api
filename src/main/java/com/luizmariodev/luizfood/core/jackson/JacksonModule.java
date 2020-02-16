package com.luizmariodev.luizfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.luizmariodev.luizfood.api.model.mixin.CidadeMixin;
import com.luizmariodev.luizfood.domain.model.Cidade;

@Component
public class JacksonModule extends SimpleModule  {

	private static final long serialVersionUID = 1L;

	public JacksonModule() {
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	}
	
}