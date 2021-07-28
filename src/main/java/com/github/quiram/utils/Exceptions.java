package com.github.quiram.utils;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public static <T> Supplier<T> asSupplier(Callable<T> callable) {
        return () -> unchecked(callable);
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
        return attempt(statement, Exception.class);
    }

    static public <T> Optional<T> attempt(Callable<T> statement, Class<? extends Exception> suppressedExceptionType) {
        try {
            return Optional.of(statement.call());
        } catch (Exception e) {
            if (suppressedExceptionType.isAssignableFrom(e.getClass())) {
                return Optional.empty();
            }

            throw new RuntimeException(e);
        }
    }

    static public void ignoreFailures(VoidCallable statement) {
        try {
            statement.call();
        } catch (Exception ignored) {
        }
    }

    static public <T> Consumer<T> ignoreFailures(Consumer<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception ignored) {
            }
        };
    }
}
