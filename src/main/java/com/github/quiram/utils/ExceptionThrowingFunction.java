package com.github.quiram.utils;

@FunctionalInterface
public interface ExceptionThrowingFunction<T, R, E extends Exception> {
    R apply(T t) throws E;
}
