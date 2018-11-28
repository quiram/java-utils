package com.github.quiram.utils;

import org.junit.Test;

import java.util.List;

import static com.github.quiram.utils.Collections.concat;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
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
}