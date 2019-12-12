package com.github.quiram.utils;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.github.quiram.utils.Collections.*;
import static com.github.quiram.utils.Random.randomString;
import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;

public class CollectionsTest {
    @Test
    public void concatNoListProducesEmptyList() {
        assertEquals(emptyList(), concat());
    }

    @Test
    public void concatOneListProducesSameList() {
        final List<Integer> integers = asList(1, 2, 3);
        assertEquals(integers, concat(integers));
    }

    @Test
    public void concatTwoListsProducesCombinedList() {
        final List<Integer> list1 = asList(1, 2, 3);
        final List<Integer> list2 = asList(4, 5);
        final List<Integer> result = asList(1, 2, 3, 4, 5);
        assertEquals(result, concat(list1, list2));
    }

    @Test
    public void noFilterReturnsSameThing() {
        final List<Integer> integers = asList(1, 2, 3);
        assertEquals(integers, filter(integers));
    }

    @Test
    public void filtersAreApplied() {
        final List<Integer> integers = asList(1, 2, 3, 4, 5);
        final List<Integer> result = singletonList(2);
        assertEquals(result, filter(integers, i -> i % 2 == 0, i -> i < 3));
    }

    @Test
    public void intersectionOfEmptySetsIsEmpty() {
        assertEquals(emptySet(), intersect(emptySet(), emptySet()));
    }

    @Test
    public void intersectionOfTwoNonEmptyIdenticalSetsHasTheSameContent() {
        final Set<String> set = singleton(randomString());
        assertEquals(set, intersect(set, set));
    }

    @Test
    public void intersectionOfTwoNonEmptySetsWithDifferentContentReturnsEmpty() {
        assertEquals(emptySet(), intersect(singleton(randomString()), singleton(randomString())));
    }

    @Test
    public void intersectionOfTwoSetsWithCommonItemsReturnsTheCommonBits() {
        final String commonContent = randomString();
        final Set<String> set1 = Stream.of(commonContent, randomString()).collect(toSet());
        final Set<String> set2 = Stream.of(commonContent, randomString()).collect(toSet());
        assertEquals(singleton(commonContent), intersect(set1, set2));
    }
}