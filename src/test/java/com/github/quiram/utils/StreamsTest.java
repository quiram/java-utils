package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.github.quiram.utils.Streams.crossProduct;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;

public class StreamsTest {
    @Test
    public void crossProductIsEmptyIfEitherStreamIsEmpty() {
        assertEquals(0, (crossProduct(Stream.empty(), Stream.of(1)).count()));
        assertEquals(0, (crossProduct(Stream.of(1), Stream.empty()).count()));
    }

    @Test
    public void crossProductHasOneElementIfOneElementInEitherStream() {
        final List<Pair<Integer, Integer>> result = crossProduct(Stream.of(1), Stream.of(2)).collect(toList());
        assertEquals(singletonList(Pair.of(1, 2)), result);
    }

    @Test
    public void crossProductCombinesTheOneElementInFirstStreamWithAllElementsInSecondStream() {
        final List<Pair<Integer, Integer>> result = crossProduct(Stream.of(1), Stream.of(2, 3)).collect(toList());
        final List<Pair<Integer, Integer>> expected = asList(Pair.of(1, 2), Pair.of(1, 3));
        assertEquals(expected, result);
    }

    @Test
    public void crossProductCombinesAllElementsFromFirstStreamWithAllElementsInSecondStream() {
        final List<Pair<Integer, Integer>> result = crossProduct(Stream.of(1, 3), Stream.of(2, 4)).collect(toList());
        assertThat(result, containsInAnyOrder(Pair.of(1, 2), Pair.of(1, 4), Pair.of(3, 2), Pair.of(3, 4)));
    }
}