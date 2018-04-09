package com.amarinperez.utils;

public class Random {
    public static String randomString() {
        return "" + new java.util.Random().nextLong();
    }

    public static int randomInt() {
        return new java.util.Random().nextInt();
    }
}
