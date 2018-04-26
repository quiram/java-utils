package com.github.quiram.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static com.github.quiram.utils.Collections.transpose;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class TransposePositiveTest<T> {

    private final List<List<T>> in;
    private final List<List<T>> out;

    public TransposePositiveTest(List<List<T>> in, List<List<T>> out) {
        this.in = in;
        this.out = out;
    }

    @Parameterized.Parameters
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

    @Test
    public void directTranspose() {
        assertThat(transpose(in), is(out));
    }

    @Test
    public void inverseTranspose() {
        assertThat(transpose(out), is(in));
    }

    @Test
    public void selfTranspose() {
        assertThat(transpose(transpose(in)), is(in));
    }
}