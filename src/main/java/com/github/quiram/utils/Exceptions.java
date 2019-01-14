package com.github.quiram.utils;

import java.util.Optional;
import java.util.concurrent.Callable;

public class Exceptions {
    /**
     * Runs a statement that might throw a checked exception, transforming the potential exception into unchecked.
     *
     * @param statement the statement to call
     * @param <T>       return type of the statement
     * @return value returned by the statement
     */
    public static <T> T unchecked(Callable<T> statement) {
        return unchecked(statement, null);
    }

    public static <T> T unchecked(Callable<T> statement, String errorMsg) {
        try {
            return statement.call();
        } catch (Exception e) {
            throw new RuntimeException(errorMsg, e);
        }
    }

    static public void unchecked(VoidCallable statement) {
        unchecked(statement, null);
    }

    static public void unchecked(VoidCallable statement, String errorMsg) {
        try {
            statement.call();
        } catch (Exception e) {
            throw new RuntimeException(errorMsg, e);
        }
    }

    static public <T> Optional<T> attempt(Callable<T> statement) {
        try {
            return Optional.of(statement.call());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    static public void ignoreFailures(VoidCallable statement) {
        try {
            statement.call();
        } catch (Exception ignored) {
        }
    }
}
