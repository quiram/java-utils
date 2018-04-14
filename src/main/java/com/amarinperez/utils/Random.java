package com.amarinperez.utils;

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
}
