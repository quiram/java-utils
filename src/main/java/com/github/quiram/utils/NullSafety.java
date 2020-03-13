package com.github.quiram.utils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class NullSafety {
    public static <T> void setIfNotNull(T item, Consumer<T> setter) {
        Optional.ofNullable(item).ifPresent(setter);
    }

    public static boolean unbox(Boolean b) {
        return Optional.ofNullable(b).orElse(false);
    }

    public static <T> T safelyParse(String value, Function<String, T> parsingFunction) {
        return Optional.ofNullable(value).map(parsingFunction).orElse(null);
    }
}
