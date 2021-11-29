package com.digitalinnovationone.matheusko.heroesapi.service;

import com.digitalinnovationone.matheusko.heroesapi.document.Heroes;
import com.digitalinnovationone.matheusko.heroesapi.repository.HeroesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class HeroesService {
    private final HeroesRepository heroesRepository;

    public Flux<Heroes> findAll() {
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findById(String id) {
        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<Heroes> save(Heroes hero) {
        return Mono.justOrEmpty(this.heroesRepository.save(hero));
    }

    public Mono<Boolean> deleteById(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
