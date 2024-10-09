package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.github.quiram.utils.Collectors.toPair;
import static com.github.quiram.utils.Collectors.toPairs;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CollectorsTest {

    @Test
    public void streamWithExactlyTwoElementsCanBeCollectedToPair() {
        final Pair<Integer, Integer> pair = Stream.of(1, 2).collect(toPair());
        assertThat(pair.getLeft(), is(1));
        assertThat(pair.getRight(), is(2));
    }

    @Test
    public void streamWithOneElementFailsToBeCollectedToPair() {
        assertThrows(RuntimeException.class, () -> Stream.of(1).collect(toPair()));
    }

    @Test
    public void streamWithThreeElementFailsToBeCollectedToPair() {
        assertThrows(RuntimeException.class, () -> Stream.of(1, 2, 3).collect(toPair()));
    }

    @Test
    public void emptyStreamCanBeCollectedToListOfPairs() {
        final List<Pair<Object, Object>> list = Stream.empty().collect(toPairs());
        assertThat(list, is(emptyList()));
    }

    @Test
    public void streamWithTwoElementsCanBeCollectedToListOfPairs() {
        final List<Pair<Integer, Integer>> pairs = Stream.of(1, 2).collect(toPairs());
        assertThat(pairs.size(), is(1));
        final Pair<Integer, Integer> pair = pairs.get(0);
        assertThat(pair.getLeft(), is(1));
        assertThat(pair.getRight(), is(2));
    }

    @Test
    public void streamWithOneElementFailsToBeCollectedToPairs() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> Stream.of(1).collect(toPairs()));
        assertThat(runtimeException.getMessage(), containsString("even number"));
    }

    @Test
    public void streamWithThreeElementFailsToBeCollectedToPairs() {
        assertThrows(RuntimeException.class, () -> Stream.of(1, 2, 3).collect(toPairs()));
    }

}