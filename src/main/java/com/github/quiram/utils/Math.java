package com.github.quiram.utils;

import java.util.stream.IntStream;

public class Math {
    public static int pow(int base, int exponent) {
        if (exponent < 0) return 0;
        if (exponent == 0) return 1;

        return IntStream.range(0, exponent - 1).reduce(base, (a, b) -> a * base);
    }
}
