package com.learnreactiveprogramming.functional;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalExample {
    public static void main(String[] args) {
        var names = List.of("Oleg", "Sam", "Barbara", "Katy", "Tom", "Barbara");
        var filteredNames = returnNamesGreaterThanSizeNoDuplicatesUppercaseSorted(names, 3);
        System.out.println(filteredNames);
    }

    private static List<String> returnNamesGreaterThanSizeNoDuplicatesUppercaseSorted(List<String> names, int size) {
        return names.stream()
                .filter(name -> name.length() > size)
                .distinct()
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }
}
