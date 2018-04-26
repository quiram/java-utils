package com.github.quiram.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;

import static com.github.quiram.utils.Exceptions.unchecked;

public class ArgumentChecks {

    static public void ensureNotNegative(int param, String paramName) throws IllegalArgumentException {
        ensure(param, paramName, p -> p < 0, "not be negative");
    }

    static public void ensureGreaterThanZero(int param, String paramName) throws IllegalArgumentException {
        ensureGreaterThan(0, param, paramName);
    }

    static public void ensureGreaterThan(int threshold, int param, String paramName) throws IllegalArgumentException {
        ensure(param, paramName, p -> p <= threshold, "be greater than " + threshold);
    }

    static public void ensureNotBlank(String param, final String paramName) throws IllegalArgumentException {
        ensure(param, paramName, StringUtils::isBlank, "have a value");
    }

    static public <T> void ensureNotNull(T param, String paramName) throws IllegalArgumentException {
        ensure(param, paramName, Objects::isNull, "not be null");
    }

    static public <T> void ensure(T param, String paramName, Function<T, Boolean> failCondition, String message) throws IllegalArgumentException {
        ensure(() -> failCondition.apply(param), paramName + " must " + message);
    }

    static public void ensure(Callable<Boolean> failCondition, String message) {
        if (unchecked(failCondition)) {
            throw new IllegalArgumentException(message);
        }
    }
}
