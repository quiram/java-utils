package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;

import static com.github.quiram.utils.Collections.head;
import static java.lang.String.format;
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

    public static <T> Collector<T, ?, T> toSingleton() {
        return toSingleton(
                "Expected exactly one element, got none",
                list -> "Expected exactly one element, got " + list
        );
    }

    public static <T> Collector<T, ?, T> toSingleton(String errorIfEmpty, Function<List<T>, String> errorSupplierIfTooMany) {
        return collectingAndThen(
                toList(),
                list -> {
                    if (list.size() == 1) {
                        return list.get(0);
                    }

                    final String errorMessage = list.size() == 0 ? errorIfEmpty : errorSupplierIfTooMany.apply(list);
                    throw new RuntimeException(errorMessage);
                }
        );
    }

    public static <T> Collector<T, ?, Pair<T, T>> toPair() {
        return collectingAndThen(
                toMaybePair(),
                maybePair -> maybePair.orElseThrow(() -> new RuntimeException("Cannot create pair from empty stream"))
        );
    }

    public static <T> Collector<T, ?, Optional<Pair<T, T>>> toMaybePair() {
        return collectingAndThen(
                toList(),
                list -> {
                    switch (list.size()) {
                        case 0:
                            return Optional.empty();
                        case 2:
                            return Optional.of(Pair.of(list.get(0), list.get(1)));
                        default:
                            throw new RuntimeException("Failed to create a pair from " + list);
                    }
                }
        );
    }

    public static <T> Collector<T, ?, List<Pair<T, T>>> toPairs() {
        return new ToPairsCollector<>();
    }

    public static <P, T extends P> Collector<P, ?, List<T>> toListOf(Class<T> klass) {
        return collectingAndThen(
                toList(),
                list -> {
                    final List<P> offendingItems = list.stream()
                            .filter(x -> !klass.isAssignableFrom(x.getClass()))
                            .collect(toList());

                    if (offendingItems.isEmpty()) {
                        return list.stream()
                                .map(klass::cast)
                                .collect(toList());
                    }

                    throw new RuntimeException(format("The following items cannot be cast to %s: %s", klass.getSimpleName(), offendingItems));
                });
    }
}
