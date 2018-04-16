package com.github.quiram.utils;

import java.util.Arrays;
import java.util.stream.Stream;

public class Streams {
    @SafeVarargs
    public static <T> Stream<T> concat(Stream<T>... streams) {
        return Arrays.stream(streams).reduce(Stream.empty(), Stream::concat);
    }
}
