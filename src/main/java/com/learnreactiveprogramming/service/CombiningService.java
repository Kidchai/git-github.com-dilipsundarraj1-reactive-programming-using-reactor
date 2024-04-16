package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class CombiningService {
    public static void main(String[] args) {
        var combiningService = new CombiningService();

        System.out.println("method concat:");
        combiningService.concat1()
                .subscribe(System.out::println);

        System.out.println("method concatWith:");
        combiningService.concat2()
                .subscribe(System.out::println);
    }

    // concat - publishers are subscribed one by one
    public Flux<String> concat1() {
        var stream1 = Mono.just("A");
        var stream2 = Flux.just("B", "C");
        var stream3 = Mono.just("D");

        return Flux.concat(stream1, stream2, stream3);
    }

    public Flux<String> concat2() {
        var stream1 = Mono.just("A");
        var stream2 = Flux.just("B", "C");

        return stream1.concatWith(stream2);
    }

    // merge - publisher are subscribed eagerly (mixed)
    public Flux<String> merge1() {
        var stream1 = Flux.just("A", "B", "C")
                .delayElements(Duration.ofMillis(100));
        var stream2 = Flux.just("D", "E", "F")
                .delayElements(Duration.ofMillis(125));
        var stream3 = Flux.just("G", "H", "I")
                .delayElements(Duration.ofMillis(150));
        return Flux.merge(stream1, stream2, stream3);
    }

    public Flux<String> merge2() {
        var stream1 = Flux.just("A", "B", "C")
                .delayElements(Duration.ofMillis(100));
        var stream2 = Flux.just("D", "E", "F")
                .delayElements(Duration.ofMillis(125));
        return stream1.mergeWith(stream2);
    }

    // mergeSequential - publisher are subscribed eagerly, but the merge happens sequentially
    public Flux<String> mergeInOrder() {
        var stream1 = Flux.just("A", "B", "C")
                .delayElements(Duration.ofMillis(100));
        var stream2 = Flux.just("D", "E", "F")
                .delayElements(Duration.ofMillis(125));
        var stream3 = Flux.just("G", "H", "I")
                .delayElements(Duration.ofMillis(150));
        return Flux.mergeSequential(stream1, stream2, stream3);
    }

    /* synchronizes the emission from streams, emitting a new item when each stream has an item ready
    combines elements from each stream into a tuple or custom object via provided function
     */
    public Flux<String> zip1() {
        var names = Flux.just("Ann", "Bob", "Clare")
                .delayElements(Duration.ofMillis(100));
        var cities = Flux.just("Berlin", "Tbilisi", "Yerevan")
                .delayElements(Duration.ofMillis(125));
        return Flux.zip(names, cities, (name, city) -> name + " from " + city)
                .log();
    }

    public Flux<String> zip2() {
        var names = Flux.just("Ann", "Bob", "Clare")
                .delayElements(Duration.ofMillis(100));
        var cities = Flux.just("Berlin", "Tbilisi", "Yerevan")
                .delayElements(Duration.ofMillis(125));
        var ages = Flux.just(28, 42, 34);
        return Flux.zip(names, cities, ages) // tuple here
                .map(t -> t.getT1() + " from " + t.getT2() + ", " + t.getT3() + " years old")
                .log();
    }

    public Flux<String> zip3() {
        var names = Flux.just("Ann", "Bob", "Clare")
                .delayElements(Duration.ofMillis(100));
        var cities = Flux.just("Berlin", "Tbilisi", "Yerevan")
                .delayElements(Duration.ofMillis(125));
        return names.zipWith(cities, (name, city) -> name + " loves " + city);
    }

}
