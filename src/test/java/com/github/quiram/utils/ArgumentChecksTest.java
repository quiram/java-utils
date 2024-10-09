package com.github.quiram.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static com.github.quiram.utils.ArgumentChecks.ensureGreaterThan;
import static com.github.quiram.utils.ArgumentChecks.ensureGreaterThanZero;
import static com.github.quiram.utils.ArgumentChecks.ensureInRange;
import static com.github.quiram.utils.ArgumentChecks.ensureNotBlank;
import static com.github.quiram.utils.ArgumentChecks.ensureNotNegative;
import static com.github.quiram.utils.ArgumentChecks.ensureNotNull;
import static com.github.quiram.utils.Random.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ArgumentChecksTest {
    @Test
    public void ensureNotNullFailWithNullParameter() {
        final String expectedMessage = randomString();
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> ensureNotNull(null, expectedMessage));
        assertThat(illegalArgumentException.getMessage(), containsString(expectedMessage));
    }

    @Test
    public void ensureNotNegativeFailsWithNegativeValue() {
        assertThrows(() -> ensureNotNegative(-1, "param"));
    }

    @Test
    public void ensureGreaterThanZeroFailsWithZero() {
        assertThrows(() -> ensureGreaterThanZero(0, randomString()));
    }

    @Test
    public void ensureGreaterThanFailsWithLowerValue() {
        assertThrows(() -> ensureGreaterThan(10, 9, randomString()));
    }

    @Test
    public void ensureInRangeFailsIfOutOfRange() {
        assertThrows(() -> ensureInRange(5, 10, 3, randomString()));
        assertThrows(() -> ensureInRange(5, 10, 15, randomString()));
    }

    @Test
    public void ensureNotBlankFailsIfBlank() {
        assertThrows(() -> ensureNotBlank("", randomString()));
    }

    private static void assertThrows(Executable executable) {
        Assertions.assertThrows(IllegalArgumentException.class, executable);
    }
}