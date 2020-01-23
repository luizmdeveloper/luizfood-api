package com.luizmariodev.luizfood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.luizmariodev.luizfood.domain.exception.EntidadeEmUsoException;
import com.luizmariodev.luizfood.domain.exception.EntidadeNaoEncontradaException;
import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.service.CozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CozinhaIntegrationTest {
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Test
	public void deveInserir_UmaNovaCozinha() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Açaí");
		
		var novaCozinha = cozinhaService.salvar(cozinha);
		
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_AoInserirCozinha() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome(null);
		
		cozinhaService.salvar(cozinha);		
	}
	
	@Test(expected = EntidadeEmUsoException.class)
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		cozinhaService.excluir(2L);
	}

	@Test(expected = EntidadeNaoEncontradaException.class)
	public void deveFalhar_QuandoExcluirCozinhaNaoExiste() {
		cozinhaService.excluir(10L);
	}	
}
