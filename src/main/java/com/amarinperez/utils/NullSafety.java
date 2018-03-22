package com.amarinperez.utils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class NullSafety {
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

    public static <T> void setIfNotNull(T item, Consumer<T> setter) {
        Optional.ofNullable(item).ifPresent(setter);
    }
}
