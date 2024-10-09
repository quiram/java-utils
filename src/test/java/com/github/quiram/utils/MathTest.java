package com.github.quiram.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.github.quiram.utils.Math.pow;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {
    @ParameterizedTest
    @CsvSource(value = {
            "8,2,3",
            "9,3,2"
    })
    public void basicTest(int expected, int base, int exponent) {
        assertEquals(expected, pow(base, exponent), format("%s == %s ^ %s", expected, base, exponent));
    }
}