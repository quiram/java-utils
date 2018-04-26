package com.github.quiram.utils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.quiram.utils.ArgumentChecks.*;
import static com.github.quiram.utils.Random.randomString;

public class ArgumentChecksTest {
    @Rule
    public ExpectedException onBadData = ExpectedException.none();

    @Before
    public void setup() {
        onBadData.expect(IllegalArgumentException.class);
    }

    @Test
    public void ensureNotNullFailWithNullParameter() {
        final String expectedMessage = "expected message";
        onBadData.expectMessage(expectedMessage);
        ensureNotNull(null, expectedMessage);
    }

    @Test
    public void ensureNotNegativeFailsWithNegativeValue() {
        ensureNotNegative(-1, "param");
    }

    @Test
    public void ensureGreaterThanZeroFailsWithZero() {
        ensureGreaterThanZero(0, randomString());
    }

    @Test
    public void ensureGreaterThanFailsWithLowerValue() {
        ensureGreaterThan(10, 9, randomString());
    }

    @Test
    public void ensureInRangeFailsIfOutOfRange() {
        ensureInRange(5, 10, 3, randomString());
        ensureInRange(5, 10, 15, randomString());
    }

    @Test
    public void ensureNotBlankFailsIfBlank() {
        ensureNotBlank("", randomString());
    }
}