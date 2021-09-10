package com.yosep.product.practice.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    @Test
    public void filterTest() {
        List<String> elements = new ArrayList<>();

        elements.add("READY");
        elements.add("READY");
        elements.add("READY");
        elements.add("READY");
        elements.add("PENDING");
        elements.add("PENDING");
        elements.add("READY");

        List<String> result = elements.stream().filter(e -> e.equals("READY"))
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }
}
