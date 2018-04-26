package com.github.quiram.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;

import static com.github.quiram.utils.Exceptions.unchecked;
import static java.lang.String.format;

public class ArgumentChecks {

    static public void ensureNotNegative(int param, String paramName) throws IllegalArgumentException {
        ensure(param, paramName, p -> p >= 0, "not be negative");
    }

    static public void ensureGreaterThanZero(int param, String paramName) throws IllegalArgumentException {
        ensureGreaterThan(0, param, paramName);
    }

    static public void ensureGreaterThan(int threshold, int param, String paramName) throws IllegalArgumentException {
        ensure(param, paramName, p -> p > threshold, "be greater than " + threshold);
    }

    static public void ensureInRange(int lowerBound, int higherBound, int param, String paramName) throws IllegalArgumentException {
        ensure(() -> param >= lowerBound && param <= higherBound, format("%s must be between %s and %s", paramName, lowerBound, higherBound));
    }

    static public void ensureNotBlank(String param, final String paramName) throws IllegalArgumentException {
        ensure(param, paramName, StringUtils::isNotBlank, "have a value");
    }

    static public <T> void ensureNotNull(T param, String paramName) throws IllegalArgumentException {
        ensure(param, paramName, Objects::nonNull, "not be null");
    }

    static public <T> void ensure(T param, String paramName, Function<T, Boolean> passCondition, String message) throws IllegalArgumentException {
        ensure(() -> passCondition.apply(param), paramName + " must " + message);
    }

    static public void ensure(Callable<Boolean> passCondition, String message) {
        if (!unchecked(passCondition)) {
            throw new IllegalArgumentException(message);
        }
    }
}
