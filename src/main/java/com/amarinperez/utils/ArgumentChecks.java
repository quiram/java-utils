package com.amarinperez.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Function;

public class ArgumentChecks {
    static public void ensureNotNegative(int param, String paramName) {
        ensure(param, paramName, p -> p < 0, "not be negative");
    }

    static public void ensureGreaterThanZero(int param, String paramName) {
        ensure(param, paramName, p -> p <= 0, "be greater than zero");
    }

    static public void ensureNotBlank(String param, final String paramName) {
        ensure(param, paramName, StringUtils::isBlank, "have a value");
    }

    static public <T> void ensureNotNull(T param, String paramName) {
        ensure(param, paramName, Objects::isNull, "not be null");
    }

    static public <T> void ensure(T param, String paramName, Function<T, Boolean> failCondition, String message) {
        if (failCondition.apply(param)) {
            throw new IllegalArgumentException(paramName + " must " + message);
        }
    }
}
