package com.github.quiram.utils.visibility;

import org.junit.Test;

import static com.github.quiram.utils.Random.randomEnum;

public class RandomTest {
    @Test
    public void canGetRandomValuesFromNonVisibleEnum() {
        randomEnum(PackageVisibleEnum.class);
    }

}
