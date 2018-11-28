package com.github.quiram.utils;

import java.util.Arrays;
import java.util.stream.Stream;

public class Streams {
    @SafeVarargs
    public static <T> Stream<T> concat(Stream<T>... streams) {
        return concat(Arrays.stream(streams));
    }

    public static <T> Stream<T> concat(Stream<Stream<T>> streams) {
        return streams.reduce(Stream.empty(), Stream::concat);
    }
}
