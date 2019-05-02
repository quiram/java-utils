package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class Optionals {
    public static <T> List<T> toList(Optional<T> o) {
        return o.map(Collections::singletonList).orElse(emptyList());
    }

    public static <T> Stream<T> toStream(Optional<T> o) {
        return o.map(Stream::of).orElse(Stream.empty());
    }

    public static <L, R> Optional<Pair<L, R>> combine(Optional<L> o1, Optional<R> o2) {
        if (o1.isPresent() && o2.isPresent()) {
            return Optional.of(Pair.of(o1.get(), o2.get()));
        }

        return Optional.empty();
    }

    public static <T> Optional<T> either(Optional<T> o1, Optional<T> o2) {
        if (o1.isPresent()) {
            return o1;
        }

        return o2;
    }
}
