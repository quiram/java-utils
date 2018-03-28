package com.amarinperez.utils;

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
        try {
            return statement.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static public <T> void unchecked(VoidCallable statement) {
        try {
            statement.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
