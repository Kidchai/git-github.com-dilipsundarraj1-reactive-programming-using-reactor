package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class CombiningServiceTest {

    CombiningService combiningService = new CombiningService();

    @Test
    void merge1_shouldReturn_mixedStream() {
        StepVerifier.create(combiningService.merge1())
                .expectNext("A", "D", "G", "B", "E", "H", "C", "F", "I")
                .verifyComplete();
    }

    @Test
    void merge2_shouldReturn_mixedStream() {
        StepVerifier.create(combiningService.merge2())
                .expectNext("A", "D", "B", "E", "C", "F")
                .verifyComplete();
    }

    @Test
    void mergeInOrder_shouldReturn_orderedElements() {
        StepVerifier.create(combiningService.mergeInOrder())
                .expectNext("A", "B", "C", "D", "E", "F", "G", "H", "I")
                .verifyComplete();
    }

    @Test
    void zip1_shouldReturn_mergedNameFromCity() {
        StepVerifier.create(combiningService.zip1())
                .expectNext("Ann from Berlin", "Bob from Tbilisi", "Clare from Yerevan")
                .verifyComplete();
    }

    @Test
    void zip2_shouldReturn_mergedNameFromCityYearsOld() {
        StepVerifier.create(combiningService.zip2())
                .expectNext("Ann from Berlin, 28 years old",
                        "Bob from Tbilisi, 42 years old",
                        "Clare from Yerevan, 34 years old")
                .verifyComplete();
    }

    @Test
    void zip3_shouldReturn_mergedNameLovesCity() {
        StepVerifier.create(combiningService.zip3())
                .expectNext("Ann loves Berlin", "Bob loves Tbilisi", "Clare loves Yerevan")
                .verifyComplete();
    }
}