package com.luizmariodev.luizfood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.repository.CozinhaRepository;
import com.luizmariodev.luizfood.util.DatabaseCleaner;
import com.luizmariodev.luizfood.util.ResourcesUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CozinhaIT {
	
	private static final int CODIGO_COZINHA_NAO_CADASTRADA = 50;

	private static final String NOME_COZINHA_AMERICANA = "Americana";

	private static final int COZINHA_AMERICANA = 2;

	@LocalServerPort
	private int porta;

	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private int quantidadeTotalDeCozinhasSalvas = 0;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = porta;
		RestAssured.basePath = "/cozinhas";
		
		databaseCleaner.clearTables();
		prepararDadosParaTestes();
	}
	
	@Test
	public void deveRetornarStatusOk_BuscarTodasCozinhasCadastradas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveConterTotalCozinhasSalvas_QuandoBuscarTodasCozinhasCadastradas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(quantidadeTotalDeCozinhasSalvas));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoSalvarUmaNovaCozinha() {
		given()
			.body(buscarJsonCadastroCozinha())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatusEConteudoCorretos_QuandoConsultaCozinhaExistente() {
		given()
			.pathParam("cozinhaId", COZINHA_AMERICANA)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.get("{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(NOME_COZINHA_AMERICANA));
	}
	
	@Test
	public void deveRetornarStatusCorretos_QuandoConsultaCozinhaInexistente() {
		given()
			.pathParam("cozinhaId", CODIGO_COZINHA_NAO_CADASTRADA)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.get("{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDadosParaTestes() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);
		
		quantidadeTotalDeCozinhasSalvas ++;

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome(NOME_COZINHA_AMERICANA);
		cozinhaRepository.save(cozinha2);
		
		quantidadeTotalDeCozinhasSalvas ++;
		
		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Francesa");
		cozinhaRepository.save(cozinha3);
		
		quantidadeTotalDeCozinhasSalvas ++;

		Cozinha cozinha4 = new Cozinha();
		cozinha4.setNome("Indiana");
		cozinhaRepository.save(cozinha4);
		
		quantidadeTotalDeCozinhasSalvas ++;
	}
	
	private String buscarJsonCadastroCozinha() {
		return ResourcesUtils.getContentFromResource("/json/deveRetornarStatus201_QuandoSalvarUmaNovaCozinha.json");
	}
	
}
