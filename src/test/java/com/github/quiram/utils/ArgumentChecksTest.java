package com.github.quiram.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.github.quiram.utils.ArgumentChecks.ensureNotNull;

public class ArgumentChecksTest {
    @Rule
    public ExpectedException onBadData = ExpectedException.none();

    @Test
    public void ensureNotNullFailWithNullParameter() {
        final String expectedMessage = "expected message";
        onBadData.expect(IllegalArgumentException.class);
        onBadData.expectMessage(expectedMessage);
        ensureNotNull(null, expectedMessage);
    }
}