package com.digitalinnovationone.matheusko.heroesapi.controller;


import com.digitalinnovationone.matheusko.heroesapi.document.Heroes;
import com.digitalinnovationone.matheusko.heroesapi.repository.HeroesRepository;
import com.digitalinnovationone.matheusko.heroesapi.service.HeroesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.digitalinnovationone.matheusko.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
@AllArgsConstructor
public class HeroesController {

    HeroesService heroesService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger heroesLog = org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Heroes> getAllItems () {
        heroesLog.info("Requesting the list of all heroes");
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Heroes>> findById(@PathVariable String id) {
        heroesLog.info("Requesting hero with id {}", id);
        return heroesService.findById(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        heroesLog.info("A new Hero was created");
        return heroesService.save(heroes);

    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<HttpStatus> deleteById(@PathVariable String id) {
        heroesService.deleteById(id);
        heroesLog.info("Deleting the hero with id {}", id);
        return Mono.just(HttpStatus.NO_CONTENT);
    }
}
