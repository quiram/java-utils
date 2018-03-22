package com.amarinperez.utils;

import static org.apache.commons.lang3.builder.CompareToBuilder.reflectionCompare;

public interface ReflectiveComparable<T> extends Comparable<T> {
    @Override
    default int compareTo(T that) {
        return reflectionCompare(this, that);
    }
}
