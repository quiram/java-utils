package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.stream.Stream;

import static com.github.quiram.utils.Collectors.toPair;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CollectorsTest {

    @Rule
    public ExpectedException onBadInput = ExpectedException.none();

    @Test
    public void streamWithExactlyTwoElementsCanBeCollectedToPair() {
        final Pair<Integer, Integer> pair = Stream.of(1, 2).collect(toPair());
        assertThat(pair.getLeft(), is(1));
        assertThat(pair.getRight(), is(2));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void streamWithOneElementFailsToBeCollectedToPair() {
        onBadInput.expect(RuntimeException.class);
        Stream.of(1).collect(toPair());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void streamWithThreeElementFailsToBeCollectedToPair() {
        onBadInput.expect(RuntimeException.class);
        Stream.of(1, 2, 3).collect(toPair());
    }
}