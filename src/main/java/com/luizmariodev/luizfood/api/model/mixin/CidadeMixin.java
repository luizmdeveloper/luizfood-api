package com.luizmariodev.luizfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luizmariodev.luizfood.domain.model.Estado;

public class CidadeMixin {
	
	@JsonIgnoreProperties(value="nome", allowGetters = true)
	private Estado estado;

}
