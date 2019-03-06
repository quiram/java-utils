package com.github.quiram.utils;

import org.junit.Test;

import static com.github.quiram.utils.Either.left;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EitherTest {
    @Test
    public void mapLeftToValue() {
        Either<Integer, Boolean> either = left(1);
        final String result = either.map(i -> Integer.toString(i), b -> Boolean.toString(b));
        assertThat(result, is("1"));
    }
}
