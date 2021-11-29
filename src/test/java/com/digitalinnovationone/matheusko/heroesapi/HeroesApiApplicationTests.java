package com.digitalinnovationone.matheusko.heroesapi;

import com.digitalinnovationone.matheusko.heroesapi.repository.HeroesRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.digitalinnovationone.matheusko.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class HeroesApiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;

	@Test
	@Order(1)
	public void getOneHeroByIdNotFound() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	@Order(2)
	public void getOneHeroByIdFound() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	@Order(3)
	public void createHero(){
		webTestClient.post().uri(HEROES_ENDPOINT_LOCAL)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("{\"id\": \"10\"," +
						"\"name\": \"Spider-Man\"," +
						"\"universe\": \"Marvel\"," +
						"\"movies\": \"11\"}")
				.exchange()
				.expectStatus().isCreated();
	}

	@Test
	@Order(4)
	public void createHeroNoId(){
		webTestClient.post().uri(HEROES_ENDPOINT_LOCAL)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("\"name\": \"Spider-Man\"," +
						"\"universe\": \"Marvel\"," +
						"\"movies\": \"11\"}")
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	@Order(5)
	public void deleteHeroByIdFound(){
		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"10")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNoContent()
				.expectBody(Void.class);
	}

//	TODO Improve response of delete request
//	@Test
//	@Order(6)
//	public void deleteHeroByIdNotFound(){
//		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"150")
//				.accept(MediaType.APPLICATION_JSON)
//				.exchange()
//				.expectStatus().isNoContent()
//				.expectBody(Void.class);
//	}
}
