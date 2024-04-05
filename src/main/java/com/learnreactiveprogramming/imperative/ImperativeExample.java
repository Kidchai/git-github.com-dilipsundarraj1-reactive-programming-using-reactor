package com.learnreactiveprogramming.imperative;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImperativeExample {
    public static void main(String[] args) {
        var names = List.of("Oleg", "Sam", "Barbara", "Katy", "Tom", "Barbara");
        var filteredNames = returnNamesGreaterThanSizeNoDuplicatesUppercaseSorted(names, 3);
        System.out.println(filteredNames);
    }

    private static List<String> returnNamesGreaterThanSizeNoDuplicatesUppercaseSorted(List<String> names, int size) {
        var filteredNames = new ArrayList<String>();
        for (var name : names) {
            if (name.length() > size && !filteredNames.contains(name.toUpperCase()))
                filteredNames.add(name.toUpperCase());
        }
        Collections.sort(filteredNames);
        return filteredNames;
    }
}
