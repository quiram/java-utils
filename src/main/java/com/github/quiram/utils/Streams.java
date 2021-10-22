package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Streams {
    @SafeVarargs
    public static <T> Stream<T> concat(Stream<T>... streams) {
        return concat(Arrays.stream(streams));
    }

    public static <T> Stream<T> concat(Stream<Stream<T>> streams) {
        return streams.reduce(Stream.empty(), Stream::concat);
    }

    public static <T, R> Stream<Pair<T, R>> crossProduct(Stream<T> stream1, Stream<R> stream2) {
        final List<R> list2 = stream2.collect(toList());
        return stream1.flatMap(x1 -> list2.stream().map(x2 -> Pair.of(x1, x2)));
    }
}
