package com.luizmariodev.luizfood.core.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {
	
	private int numeroMultiplo;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.numeroMultiplo = constraintAnnotation.numero();
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		boolean valid = true;
		
		if (value != null) {
			var valorDecimal = BigDecimal.valueOf(value.doubleValue());
			var multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);
			var resto = valorDecimal.remainder(multiploDecimal);
			
			valid = BigDecimal.ZERO.compareTo(resto) == 0;
		}
		
		return valid;
	}

}
