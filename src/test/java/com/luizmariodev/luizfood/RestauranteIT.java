package com.luizmariodev.luizfood;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;

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

import com.luizmariodev.luizfood.domain.model.Cidade;
import com.luizmariodev.luizfood.domain.model.Cozinha;
import com.luizmariodev.luizfood.domain.model.Endereco;
import com.luizmariodev.luizfood.domain.model.Estado;
import com.luizmariodev.luizfood.domain.model.Restaurante;
import com.luizmariodev.luizfood.domain.repository.CidadeRepository;
import com.luizmariodev.luizfood.domain.repository.CozinhaRepository;
import com.luizmariodev.luizfood.domain.repository.EstadoRepository;
import com.luizmariodev.luizfood.domain.repository.RestauranteRepository;
import com.luizmariodev.luizfood.util.DatabaseCleaner;
import com.luizmariodev.luizfood.util.ResourcesUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestauranteIT {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	private int quantidadeTotalRestaurantesCadastrados = 0;
	
	@LocalServerPort
	private int porta = 8080;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = porta;
		RestAssured.basePath = "/restaurantes";
		
		databaseCleaner.clearTables();
		prepararDadosParaTeste();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoBuscarTodosRestaurantes() {
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoBuscarRestauranteNaoCadastrado() {
		given()
			.pathParam("restauranteId", 800)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.get("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());			
	}
	
	@Test
	public void deveRetonarStatus200EQuantidadeCorreta_QuandoBuscarTodosRestaurantes() {		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", Matchers.hasSize(quantidadeTotalRestaurantesCadastrados));
	}
	
	@Test
	public void deveRetornar201_QuandoCadastrarRestauranteValido() {
		given()
			.body(retornarJsonRestauranteValido())		
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoCadastrarRestauranteInvalido() {
		given()
			.body(RetornarJsonInvalido())
			.contentType(ContentType.JSON)			
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoExcluirRestauranteInexistente() {
		given()
			.pathParam("restauranteId", 50)
			.accept(ContentType.JSON)
		.when()
			.delete("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());		
	}
	
	@Test
	public void deveRetornarStatus204_QuandoExcluirRestauranteExistente() {
		given()
			.pathParam("restauranteId", 1)
			.accept(ContentType.JSON)
		.when()
			.delete("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());		
	}
	

	private String RetornarJsonInvalido() {
		return ResourcesUtils.getContentFromResource("/json/deveRetornar400_QuandoCadastrarRestauranteInvalido.json");
	}

	private String retornarJsonRestauranteValido() {
		return ResourcesUtils.getContentFromResource("/json/deveRetornar201_QuandoCadastrarRestauranteValido.json");
	}

	private void prepararDadosParaTeste() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Chinesa");
		
		cozinhaRepository.save(cozinha);
		
		Estado pernambuco = new Estado();
		pernambuco.setNome("Pernambuco");
		
		estadoRepository.save(pernambuco);
		
		Estado estadoSalvo = estadoRepository.getOne(1L); 		
		
		Cozinha cozinhaSalva = cozinhaRepository.getOne(1L);
		Cidade cidade = new Cidade();
		cidade.setNome("Salgueiro");
		cidade.setEstado(estadoSalvo);
		
		cidadeRepository.save(cidade);
		
		Cidade cidadeSalva = cidadeRepository.getOne(1L);
		
		Endereco enderecoRestauranteChines = new Endereco();
		enderecoRestauranteChines.setCidade(cidadeSalva);
		enderecoRestauranteChines.setCep("56304360");
		enderecoRestauranteChines.setLogradouro("Av Agamenon Magalhães");
		enderecoRestauranteChines.setNumero("871");
		enderecoRestauranteChines.setComplemento("");
		enderecoRestauranteChines.setBairro("Nossa senhora das graças");
		
		Restaurante restauranteChines = new Restaurante();
		restauranteChines.setNome("Kojina lounge");
		restauranteChines.setCozinha(cozinhaSalva);
		restauranteChines.setEndereco(enderecoRestauranteChines);
		restauranteChines.setTaxaEntrega(new BigDecimal("5"));
		restauranteRepository.save(restauranteChines);
		
		quantidadeTotalRestaurantesCadastrados ++;
		
		Cozinha cozinhaPizza = new Cozinha();
		cozinhaPizza.setNome("Pizzaria");
		
		cozinhaRepository.save(cozinhaPizza);
		
		Cozinha cozinhaBurger = new Cozinha();
		cozinhaBurger.setNome("Burger");
		
		cozinhaRepository.save(cozinhaBurger);
		
		Cozinha cozinhaSalvaPizzaria = cozinhaRepository.getOne(2L);	
		
		Endereco enderecoRestaurantePizzaria = new Endereco();
		enderecoRestaurantePizzaria.setCidade(cidadeSalva);
		enderecoRestaurantePizzaria.setCep("5600000");
		enderecoRestaurantePizzaria.setLogradouro("R. Joaquim Sampaio");
		enderecoRestaurantePizzaria.setNumero("95A");
		enderecoRestaurantePizzaria.setComplemento("");
		enderecoRestaurantePizzaria.setBairro("Nossa senhora das graças");
		
		Restaurante restaurantePizzaria = new Restaurante();
		restaurantePizzaria.setNome("Wilson pizzas");
		restaurantePizzaria.setCozinha(cozinhaSalvaPizzaria);
		restaurantePizzaria.setEndereco(enderecoRestauranteChines);
		restaurantePizzaria.setTaxaEntrega(new BigDecimal("12"));
		restauranteRepository.save(restaurantePizzaria);
		
		quantidadeTotalRestaurantesCadastrados ++;
	}

}
