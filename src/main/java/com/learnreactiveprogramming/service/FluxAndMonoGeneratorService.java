package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {
    public static void main(String[] args) {
        var generatorService = new FluxAndMonoGeneratorService();
        generatorService.getFluxNames()
                .subscribe(System.out::println);
        generatorService.getMonoName()
                .subscribe(System.out::println);
    }

    private Flux<String> getFluxNames() {
        return Flux.fromIterable(List.of("Katerina", "Pam", "Frodo"));
    }

    private Mono<String> getMonoName() {
        return Mono.just("Aman");
    }

}
