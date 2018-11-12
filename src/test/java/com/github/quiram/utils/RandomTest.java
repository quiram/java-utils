package com.github.quiram.utils;

import org.junit.Test;

import static com.github.quiram.utils.Random.randomEnum;
import static com.github.quiram.utils.Random.randomPositiveInt;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class RandomTest {

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