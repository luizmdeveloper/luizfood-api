package com.luizmariodev.luizfood;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CozinhaIT {
	
	@LocalServerPort
	private int porta;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = porta;
		RestAssured.basePath = "/cozinhas";
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
	public void deveConter7Cozinhas_QuandoBuscarTodasCozinhasCadastradas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(7));
	}

}
