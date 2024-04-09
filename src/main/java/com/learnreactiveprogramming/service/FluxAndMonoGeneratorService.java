package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class FluxAndMonoGeneratorService {
    public static void main(String[] args) {
        var generatorService = new FluxAndMonoGeneratorService();
        generatorService.getFluxNames()
                .subscribe(System.out::println);
        generatorService.getMonoName()
                .subscribe(System.out::println);
        generatorService.getUppercaseNames()
                .subscribe(System.out::println);
    }

    public Flux<String> getFluxNames() {
        return Flux.fromIterable(List.of("Katerina", "Pam", "Frodo"));
    }

    public Mono<String> getMonoName() {
        return Mono.just("Aman");
    }

    public Flux<String> getUppercaseNames() {
        return Flux.fromIterable(List.of("August", "Hera", "Annet"))
                .map(String::toUpperCase)
                .log();
    }

    // Streams are immutable!
    public Flux<String> getNamesImmutabilitySample() {
        var names = Flux.fromIterable(List.of("Olesya, Umka, Petr"));
        names.map(String::toUpperCase);
        return names;
    }

    public Flux<String> getNamesWithMaxLength(int maxLength) {
        return Flux.fromIterable(List.of("Sam", "Kate", "David", "Veronika", "Anastasia", "Bartholomew", "Ramachandran"))
                .filter(name -> name.length() <= maxLength);
    }

    // map - synchronous transformation. Transforms each incoming element into Object one by one.
    public Flux<Integer> getSquaredNumbersSync() {
        return Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
                .map(n -> n * n)
                .log();
    }

    /* flatMap - asynchronous transformation. Transforms each element into a Publisher, then unites them into one Flux.
    => we can't predict order of elements in result Flux */
    public Flux<Integer> getSquaredNumbersAsync() {
        return Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
                .flatMap(n -> Mono.just(n * n)
                        .delayElement(Duration.ofMillis(100))) // delay to demonstrate asynchrony
                .log();
    }

}
