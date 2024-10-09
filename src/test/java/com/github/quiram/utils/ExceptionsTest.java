package com.github.quiram.utils;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static com.github.quiram.utils.Exceptions.attempt;
import static com.github.quiram.utils.Exceptions.ignoreFailures;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThrows(Exception.class, () -> attempt(() -> {
            throw new Exception();
        }, RuntimeException.class));
    }

    @Test
    public void attemptingVoidStatementThatWorksDoesNothing() {
        ignoreFailures(() -> {
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
        ignoreFailures(() -> b.set(false));
        assertFalse(b.get());
    }

    @Test
    public void attemptingConsumerThatFailsDoesNothing() {
        Consumer<String> consumer = s -> {
            throw new RuntimeException();
        };
        ignoreFailures(consumer).accept("");
    }

    @Test
    public void attemptingConsumerThatWorksDoesItsJob() {
        Consumer<List<String>> consumer = list -> list.add("");
        final LinkedList<String> stringLinkedList = new LinkedList<>();
        ignoreFailures(consumer).accept(stringLinkedList);
        assertEquals(1, stringLinkedList.size());
    }
}