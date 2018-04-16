package com.github.quiram.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static com.github.quiram.utils.Math.pow;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MathTest {
    private final int expected;
    private final int base;
    private final int exponent;

    public MathTest(int expected, int base, int exponent) {
        this.expected = expected;
        this.base = base;
        this.exponent = exponent;
    }

    @Test
    public void basicTest() {
        assertEquals(format("%s == %s ^ %s", expected, base, exponent), expected, pow(base, exponent));
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return asList(new Object[][]{
                {8, 2, 3},
                {9, 3, 2}
        });
    }
}