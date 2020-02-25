package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static com.github.quiram.utils.Collections.head;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Collectors {
    public static <T> Collector<T, ?, Optional<T>> toMaybeOne() {
        return collectingAndThen(
                toList(),
                list -> {
                    if (list.size() > 1) {
                        throw new RuntimeException("At most one element expected, but got " + list);
                    }
                    return head(list);
                }
        );
    }

    public static <T> Collector<T, ?, Pair<T, T>> toPair() {
        return collectingAndThen(
                toList(),
                list -> {
                    if (list.size() != 2) {
                        throw new RuntimeException("Exactly two elements expected, but got " + list);
                    }
                    return Pair.of(list.get(0), list.get(1));
                }
        );
    }

    public static <T> Collector<T, ?, List<Pair<T, T>>> toPairs() {
        return collectingAndThen(
                toList(),
                list -> {
                    if (list.size() % 2 != 0) {
                        throw new RuntimeException("An even number of elements was expected, but got " + list);
                    }

                    return IntStream.range(0, list.size() / 2)
                            .mapToObj(i -> Pair.of(list.get(2 * i), list.get(2 * i + 1)))
                            .collect(java.util.stream.Collectors.toList());
                }
        );
    }
}
