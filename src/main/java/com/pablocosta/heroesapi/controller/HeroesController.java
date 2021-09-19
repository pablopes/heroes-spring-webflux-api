package com.pablocosta.heroesapi.controller;

import static com.pablocosta.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pablocosta.heroesapi.document.Heroes;
import com.pablocosta.heroesapi.repository.HeroesRepository;
import com.pablocosta.heroesapi.service.HeroesService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@Slf4j
public class HeroesController {
	HeroesService heroesService;
	HeroesRepository heroesRepository;
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroesController.class);
	
	public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
		this.heroesRepository = heroesRepository;
		this.heroesService = heroesService;
	}
	
	@GetMapping(HEROES_ENDPOINT_LOCAL)
	@ResponseStatus(HttpStatus.OK)
	public Flux<Heroes> getAllItems(){
		log.info("Requesting the list of heroes");
		return heroesService.findAll();
	}
	@GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
	public Mono<ResponseEntity<Heroes>> findById(@PathVariable String id){
		log.info("Requesting the heroe with id {}", id);
		return heroesService.findById(id)
			.map((item) -> new ResponseEntity<>(item,HttpStatus.OK))
			.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping(HEROES_ENDPOINT_LOCAL)
	@ResponseStatus(code= HttpStatus.CREATED)
	public Mono<Heroes> save(@RequestBody Heroes heroes) {
		log.info("A new Hero was created");
		return heroesService.save(heroes);
	}
	
	@DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
	@ResponseStatus(code= HttpStatus.NO_CONTENT)
	public Mono<HttpStatus> deleteById(@PathVariable String id) {
		heroesService.deleteById(id);
		log.info("deleting a hero with id {}", id);
		return Mono.just(HttpStatus.NO_CONTENT);
	}
}
