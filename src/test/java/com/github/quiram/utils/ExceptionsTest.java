package com.github.quiram.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.github.quiram.utils.Exceptions.attempt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ExceptionsTest {
    @Rule
    public ExpectedException onBadInput = ExpectedException.none();

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
    public void attemptingStatementThatSucceedsWithConfiguredExceptionReturnsValue() {
        final Optional<String> result = attempt(() -> "hello", RuntimeException.class);
        assertEquals(Optional.of("hello"), result);
    }

    @Test
    public void attemptingStatementThatFailsWithExpectedExceptionReturnsEmpty() {
        final Optional<Object> result = attempt(() -> {
            throw new IllegalArgumentException();
        }, IllegalArgumentException.class);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void attemptingStatementThatFailsWithExceptionChildOfConfiguredReturnsEmpty() {
        final Optional<Object> result = attempt(() -> {
            throw new RuntimeException();
        }, Exception.class);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void attemptingStatementThatFailsWithExceptionDifferentFromConfiguredPropagatesException() {
        onBadInput.expect(Exception.class);
        attempt(() -> {
            throw new Exception();
        }, RuntimeException.class);
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