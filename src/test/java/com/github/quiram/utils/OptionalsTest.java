package com.github.quiram.utils;

import org.junit.Test;

import java.util.Optional;

import static com.github.quiram.utils.Optionals.toList;
import static com.github.quiram.utils.Random.randomString;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class OptionalsTest {
    @Test
    public void emptyOptionalProducesEmptyList() {
        assertEquals(emptyList(), toList(Optional.empty()));
    }

    @Test
    public void nonEmptyOptionalProducesSingletonList() {
        String value = randomString();
        assertEquals(singletonList(value), toList(Optional.of(value)));
    }
}