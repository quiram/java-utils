package com.github.quiram.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Collections {
    public static <T, R> R map(T o, Function<T, R> mapper) {
        return o == null ? null : mapper.apply(o);
    }

    public static <T> String mapToString(T o) {
        return map(o, T::toString);
    }

    public static <T, R> List<R> map(List<T> in, Function<T, R> mapper) {
        return in.stream().map(mapper).collect(toList());
    }

    public static <T, R> Set<R> map(Set<T> in, Function<T, R> mapper) {
        return in.stream().map(mapper).collect(toSet());
    }

    public static <K, V> Map<K, V> toMap(Collection<V> items, Function<V, K> key) {
        return items.stream().collect(Collectors.toMap(key, identity()));
    }
}
