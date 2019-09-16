package com.github.quiram.utils;

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
}
