package com.luizmariodev.luizfood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdModelInput {
	
	@NotNull
	private Long id;

}
