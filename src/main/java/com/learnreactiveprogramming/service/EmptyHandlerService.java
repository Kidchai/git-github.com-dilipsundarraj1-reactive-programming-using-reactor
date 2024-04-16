package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

public class EmptyHandlerService {
    public static void main(String[] args) {
        var names = List.of("David", "Veronika", "Anastasia");
        var emptyHandlerService = new EmptyHandlerService();
        emptyHandlerService.getDefaultValue(names, 3)
                .subscribe(System.out::println);
        emptyHandlerService.switchToOtherStream(names, 3)
                .subscribe(System.out::println);
    }

    public Flux<String> getDefaultValue(List<String> names, int maxLength) {
        return Flux.fromIterable(names)
                .transform(maxLengthFilter(maxLength))
                .defaultIfEmpty("We used this value since stream is empty");
    }

    public Flux<String> switchToOtherStream(List<String> names, int maxLength) {
        var defaultFlux = Flux.just("We switched to another flux since stream is empty");

        return Flux.fromIterable(names)
                .transform(maxLengthFilter(maxLength))
                .switchIfEmpty(defaultFlux);
    }

    private static Function<Flux<String>, Flux<String>> maxLengthFilter(int maxLength) {
        return name -> name.filter(s -> s.length() <= maxLength);
    }
}

