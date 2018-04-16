package com.github.quiram.utils;

import static com.github.quiram.utils.ArgumentChecks.ensureGreaterThanZero;
import static com.github.quiram.utils.ArgumentChecks.ensureNotNegative;
import static com.github.quiram.utils.Math.pow;

public class Random {
    private static java.util.Random random = new java.util.Random();

    public static String randomString() {
        return "text-" + randomLong();
    }

    public static int randomInt() {
        return random.nextInt();
    }

    public static long randomLong() {
        return random.nextInt();
    }

    public static double randomDouble() {
        return random.nextDouble();
    }

    public static double randomDouble(int bound, int precision) {
        ensureGreaterThanZero(bound, "bound");
        ensureNotNegative(precision, "precision");
        final int precisionFactor = pow(10, precision);
        final double allDigits = random.nextInt(bound * precisionFactor);
        return allDigits / precisionFactor;
    }
}
