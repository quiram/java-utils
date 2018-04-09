package com.amarinperez.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class ReflectiveToString {
    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
