package com.github.quiram.utils;

import org.junit.Test;

import static com.github.quiram.utils.Random.randomEnum;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class RandomTest {

    @Test
    public void randomFromWillProduceAnUnspecifiedItemFromTheEnum() {
        final TestEnum element = randomEnum(TestEnum.class);
        assertThat(asList(TestEnum.values()), hasItem(element));
    }

    private enum TestEnum {
        FIRST, SECOND, THIRD
    }
}