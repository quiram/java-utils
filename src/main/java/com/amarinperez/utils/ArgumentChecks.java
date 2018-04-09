package com.amarinperez.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

public class ArgumentChecks {
    public void ensureNotNegative(int param, String paramName) {
        ensure(param, paramName, p -> p < 0, "not be negative");
    }

    public void ensureGreaterThanZero(int param, String paramName) {
        ensure(param, paramName, p -> p <= 0, "be greater than zero");
    }

    public void ensureNotBlank(String param, final String paramName) {
        ensure(param, paramName, StringUtils::isBlank, "have a value");
    }

    public <T> void ensure(T param, String paramName, Function<T, Boolean> checker, String message) {
        if (checker.apply(param)) {
            throw new IllegalArgumentException(paramName + " must " + message);
        }
    }
}
