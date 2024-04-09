package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService generatorService = new FluxAndMonoGeneratorService();

    @Test
    void getFluxNames_shouldReturn_3_elements() {
        var names = generatorService.getFluxNames();
        StepVerifier.create(names)
                .expectNextCount(3)
                .verifyComplete();
    }
    @Test
    void getFluxNames_shouldReturn_namesInCorrectOrder() {
        var names = generatorService.getFluxNames();
        StepVerifier.create(names)
                .expectNext("Katerina", "Pam", "Frodo")
                .verifyComplete();
    }

    @Test
    void getMonoName_shouldReturn_correctName() {
        var name = generatorService.getMonoName();
        StepVerifier.create(name)
                .expectNext("Aman")
                .verifyComplete();
    }

    @Test
    void getUppercaseNames_shouldReturn_uppercaseNames() {
        var name = generatorService.getUppercaseNames();
        StepVerifier.create(name)
                .expectNext("AUGUST", "HERA","ANNET")
                .verifyComplete();
    }

    @Test
    void getNamesImmutabilitySample_shouldReturn_lowercaseNames() {
        var name = generatorService.getNamesImmutabilitySample();
        StepVerifier.create(name)
                .expectNext("Olesya, Umka, Petr")
                .verifyComplete();
    }
}
