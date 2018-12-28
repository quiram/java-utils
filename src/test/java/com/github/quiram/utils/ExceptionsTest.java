package com.github.quiram.utils;

import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.github.quiram.utils.Exceptions.attempt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ExceptionsTest {
    @Test
    public void attemptingStatementThatWorksReturnsWrappedValue() {
        final Optional<Integer> result = attempt(() -> 1);
        assertEquals(Optional.of(1), result);
    }

    @Test
    public void attemptingStatementThatFailsReturnsEmpty() {
        final Optional<Object> result = attempt(() -> {
            throw new Exception();
        });

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void attemptingVoidStatementThatWorksDoesNothing() {
        Exceptions.ignoreFailures(() -> {
        });
    }

    @Test
    public void attemptingVoidStatementThatFailsDoesNothing() {
        attempt(() -> {
            throw new Exception();
        });
    }

    @Test
    public void attemptingVoidStatementExecutesTheStatement() {
        AtomicBoolean b = new AtomicBoolean(true);
        Exceptions.ignoreFailures(() -> b.set(false));
        assertFalse(b.get());
    }
}