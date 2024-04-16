package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransformationService {
    public static void main(String[] args) {
        var transformationService = new TransformationService();
        var withFlux = transformationService.transformToMonoWithFlux(getMono());
        System.out.println("With flux:");
        withFlux.subscribe(System.out::println);

        var withFlatMapMany = transformationService.transformToMonoWithFlatMapMany(getMono());
        System.out.println("With flatMapMany:");
        withFlatMapMany.subscribe(System.out::println);
    }

    // Transform object to flux without modifying its data
    public Flux<String> transformToMonoWithFlux(Mono<String> monoList) {
        return monoList.flux();
    }

    // Asynchronously handle each element from stream, then combine them into one Flux
    public Flux<Object> transformToMonoWithFlatMapMany(Mono<String> monoList) {
        return monoList.flatMapMany(name -> Flux.just(name.toUpperCase(), name.toLowerCase()));
    }

    private static Mono<String> getMono() {
        return Mono.just("Robert");
    }
}
