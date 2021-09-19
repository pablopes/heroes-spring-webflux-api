package com.pablocosta.heroesapi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.pablocosta.heroesapi.repository.HeroesRepository;

import static com.pablocosta.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;


@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {
	@Autowired
	WebTestClient webTestClient;
	@Autowired
	HeroesRepository heroesRepository;
	
	@Test
	public void getOneHeroById() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "2")
			.exchange()
			.expectStatus().isOk()
			.expectBody();
	}
	@Test
	public void getOneHeroNoContent() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	public void deleteHero() {
		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "2")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isNoContent()
			.expectBody(Void.class);
	}


}
