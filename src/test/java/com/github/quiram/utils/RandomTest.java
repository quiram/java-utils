package com.github.quiram.utils;

import org.junit.Test;

import java.util.stream.IntStream;

import static com.github.quiram.utils.Random.*;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class RandomTest {

    @Test
    public void randomStringHasNoNumbers() {
        final String string = randomString();
        IntStream.range(0, 10).mapToObj(Integer::toString).forEach(
                number -> assertThat(string, not(containsString(number)))
        );
    }

    @Test
    public void randomFromWillProduceAnUnspecifiedItemFromTheEnum() {
        final TestEnum element = randomEnum(TestEnum.class);
        assertThat(asList(TestEnum.values()), hasItem(element));
    }

    @Test(expected = IllegalArgumentException.class)
    public void randomIntWithUpperBoundFailsIfNegativeBound() {
        randomPositiveInt(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void randomIntWithUpperBoundFailsIfBoundIsZero() {
        randomPositiveInt(0);
    }

    @Test
    public void randomIntWithUpperBoundOfOneGivesZero() {
        assertEquals(0, randomPositiveInt(1));
    }

    @Test
    public void randomIntWithUpperBoundGreaterThanOneGivesPositiveNumberWithinBounds() {
        final int upperBound = 10;
        final int value = randomPositiveInt(upperBound);
        assertTrue(value >= 0);
        assertTrue(value < upperBound);
    }

    private enum TestEnum {
        FIRST, SECOND, THIRD
    }
}