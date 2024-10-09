package com.github.quiram.utils;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;

import static com.github.quiram.utils.ComparableList.comparable;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparableListTest {
    private static final List<Integer> EMPTY = emptyList();

    @ParameterizedTest(name = "{0}")
    @MethodSource("data")
    public void run(@SuppressWarnings("unused") String caseName, List<Integer> list1, List<Integer> list2, int expected) {
        final ComparableList<Integer> comparable1 = comparable(list1);
        final ComparableList<Integer> comparable2 = comparable(list2);

        assertEquals(expected, comparable1.compareTo(comparable2));
        assertEquals(-expected, comparable2.compareTo(comparable1));
    }

    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {"two empty lists are equal", EMPTY, EMPTY, 0},
                {"identical singleton lists are equal", singletonList(1), singletonList(1), 0},
                {"identical longer lists are equal", asList(4, 5, 2), asList(4, 5, 2), 0},
                {"empty list goes before non-empty", EMPTY, singletonList(1), -1},
                {"same-size lists are order as per their elements", singletonList(1), singletonList(2), -1},
                {"list that is prefix of another goes first", singletonList(1), asList(1, 2), -1},
                {"comparison of first element is more important than rest", singletonList(1), asList(2, 5), -1}
        });
    }
}
