package com.github.quiram.utils;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.github.quiram.utils.Collections.concat;
import static com.github.quiram.utils.Collections.filter;
import static com.github.quiram.utils.Collections.intersect;
import static com.github.quiram.utils.Collections.merge;
import static com.github.quiram.utils.Random.randomString;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void mergingTwoEmptyMapsProducesEmptyMap() {
        final Map<Object, Object> result = merge(($1, $2) -> $1, emptyMap(), emptyMap());
        assertEquals(emptyMap(), result);
    }

    @Test
    public void mergingEmptyMapWithNonEmptyMapProducesTheNonEmptyMap() {
        final Map<String, String> nonEmptyMap = singletonMap(randomString(), randomString());
        assertEquals(nonEmptyMap, merge(($1, $2) -> $1, nonEmptyMap, emptyMap()));
        assertEquals(nonEmptyMap, merge(($1, $2) -> $1, emptyMap(), nonEmptyMap));
    }

    @Test
    public void mergingTwoNonEmptyMapsWithDifferentKeysProducesMapWithAllKeysCombined() {
        final String key1 = randomString();
        final String value1 = randomString();
        final String key2 = randomString();
        final String value2 = randomString();
        final Map<String, String> nonEmptyMap1 = singletonMap(key1, value1);
        final Map<String, String> nonEmptyMap2 = singletonMap(key2, value2);
        final Map<String, String> result = merge(($1, $2) -> $1, nonEmptyMap1, nonEmptyMap2);
        assertEquals(2, result.size());
        assertEquals(value1, result.get(key1));
        assertEquals(value2, result.get(key2));
    }

    @Test
    public void mergingTwoNonEmptyMapsWithOverlappingKeysUsesMergingFunction() {
        final String key = randomString();
        final String value1 = randomString();
        final String value2 = randomString();
        final Map<String, String> nonEmptyMap1 = singletonMap(key, value1);
        final Map<String, String> nonEmptyMap2 = singletonMap(key, value2);
        assertEquals(nonEmptyMap1, merge(($1, $2) -> $1, nonEmptyMap1, nonEmptyMap2));
        assertEquals(nonEmptyMap2, merge(($1, $2) -> $2, nonEmptyMap1, nonEmptyMap2));
    }

    @Test
    public void canMergeThreeMaps() {
        final Map<String, String> result = merge(($1, $2) -> $1, singletonMap(randomString(), randomString()), singletonMap(randomString(), randomString()), singletonMap(randomString(), randomString()));
        assertEquals(3, result.size());
    }
}