package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.Stream;

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
        var names = generatorService.getUppercaseNames();
        StepVerifier.create(names)
                .expectNext("AUGUST", "HERA","ANNET")
                .verifyComplete();
    }

    @Test
    void getNamesImmutabilitySample_shouldReturn_lowercaseNames() {
        var names = generatorService.getNamesImmutabilitySample();
        StepVerifier.create(names)
                .expectNext("Olesya, Umka, Petr")
                .verifyComplete();
    }

    @ParameterizedTest
    @MethodSource("getNamesWithMaxLengthSource")
    void getNamesWithMaxLength_shouldReturn_correctLengthNames(int maxLength, List<String> expectedNames) {
        var names = generatorService.getNamesWithMaxLength(maxLength);
        StepVerifier.create(names)
                .expectNextSequence(expectedNames)
                .verifyComplete();
    }

    private static Stream<Arguments> getNamesWithMaxLengthSource() {
        return Stream.of(Arguments.of(3, List.of("Sam")),
                Arguments.of(5, List.of("Sam", "Kate", "David")),
                Arguments.of(11, List.of("Sam", "Kate", "David", "Veronika", "Anastasia", "Bartholomew")));
    }
}
