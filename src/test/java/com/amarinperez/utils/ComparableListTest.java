package com.amarinperez.utils;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static com.amarinperez.utils.ComparableList.comparable;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ComparableListTest {
    private static final List<Integer> EMPTY = emptyList();
    private final List<Integer> list1;
    private final List<Integer> list2;
    private final int expected;

    public ComparableListTest(@SuppressWarnings("unused") String caseName, List<Integer> list1, List<Integer> list2, int expected) {
        this.list1 = list1;
        this.list2 = list2;
        this.expected = expected;
    }

    @Test
    public void run() {
        final ComparableList<Integer> comparable1 = comparable(this.list1);
        final ComparableList<Integer> comparable2 = comparable(this.list2);

        assertEquals(expected, comparable1.compareTo(comparable2));
        assertEquals(-expected, comparable2.compareTo(comparable1));
    }

    @Parameterized.Parameters(name = "{0}")
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
