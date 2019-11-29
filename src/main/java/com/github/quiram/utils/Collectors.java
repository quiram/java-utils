package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;
import java.util.stream.Collector;

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
}
