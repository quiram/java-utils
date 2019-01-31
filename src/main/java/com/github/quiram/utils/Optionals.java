package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class Optionals {
    public static <T> List<T> toList(Optional<T> o) {
        return o.isPresent() ? singletonList(o.get()) : emptyList();
    }

    public static <L, R> Optional<Pair<L, R>> combine(Optional<L> o1, Optional<R> o2) {
        if (o1.isPresent() && o2.isPresent()) {
            return Optional.of(Pair.of(o1.get(), o2.get()));
        }

        return Optional.empty();
    }
}
