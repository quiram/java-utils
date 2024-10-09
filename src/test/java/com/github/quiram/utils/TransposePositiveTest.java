package com.github.quiram.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static com.github.quiram.utils.Collections.transpose;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TransposePositiveTest<T> {

    public static List<Object[]> data() {
        return asList(new Object[][]
                {
                        {null, null},
                        {emptyList(), emptyList()},
                        {asList(emptyList(), emptyList(), emptyList()), asList(emptyList(), emptyList(), emptyList())},
                        {asList(singletonList(1), singletonList(2), singletonList(3)), singletonList(asList(1, 2, 3))},
                        {asList(asList(1, 2), asList(3, 4), asList(5, 6)), asList(asList(1, 3, 5), asList(2, 4, 6))}
                }
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void directTranspose(List<List<T>> in, List<List<T>> out) {
        assertThat(transpose(in), is(out));
    }

    @ParameterizedTest
    @MethodSource("data")
    public void inverseTranspose(List<List<T>> in, List<List<T>> out) {
        assertThat(transpose(out), is(in));
    }

    @ParameterizedTest
    @MethodSource("data")
    public void selfTranspose(List<List<T>> in) {
        assertThat(transpose(transpose(in)), is(in));
    }
}