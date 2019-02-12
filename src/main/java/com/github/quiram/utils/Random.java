package com.github.quiram.utils;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.github.quiram.utils.ArgumentChecks.ensureGreaterThanZero;
import static com.github.quiram.utils.ArgumentChecks.ensureNotNegative;
import static com.github.quiram.utils.Exceptions.unchecked;
import static com.github.quiram.utils.Math.pow;
import static java.lang.Character.toUpperCase;

public class Random {
    private static java.util.Random random = new java.util.Random();
    private static final String letters = "abcdefghijklmnopqrstuvwxyz";
    private static final String digits = "0123456789";

    public static String randomString() {
        return IntStream.range(0, randomPositiveInt(20) + 1)
                .mapToObj($ -> randomLetter())
                .reduce(new StringBuilder(), StringBuilder::append, (b1, b2) -> b2)
                .toString();
    }

    public static char randomLetter() {
        final char c = randomCharacter(letters);
        if (randomBoolean()) {
            return c;
        } else {
            return toUpperCase(c);
        }
    }

    public static char randomDigit() {
        return randomCharacter(digits);
    }

    public static char randomCharacter(String s) {
        return s.charAt(randomPositiveInt(s.length()));
    }

    public static int randomInt() {
        return random.nextInt();
    }

    public static int randomInt(int bound) {
        return random.nextInt(bound);
    }

    public static int randomPositiveInt() {
        return randomIntGreaterThan(0);
    }

    public static int randomPositiveInt(int upperBound) {
        if (upperBound <= 0) {
            throw new IllegalArgumentException("Upper bound must be 1 or greater.");
        }

        return randomIntWithinRange(0, upperBound);
    }

    public static int randomIntGreaterThan(int threshold) {
        return randomIntWithinRange(threshold + 1, Integer.MAX_VALUE);
    }

    public static int randomIntWithinRange(int lowInclusive, int upExclusive) {
        return randomInt(upExclusive - lowInclusive) + lowInclusive;
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
        final double allDigits = randomInt(bound * precisionFactor);
        return allDigits / precisionFactor;
    }

    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    public static <T> Optional<T> randomOptional(Supplier<T> supplier) {
        if (randomBoolean()) {
            return Optional.empty();
        }

        return Optional.of(supplier.get());
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T randomEnum(Class<T> enumType) {
        final T[] enumValues = (T[]) unchecked(
                () -> enumType.getMethod("values").invoke(null));
        return enumValues[randomInt(enumValues.length)];
    }

    public static <T> T randomElement(T[] array) {
        return array[randomPositiveInt(array.length)];
    }
}
