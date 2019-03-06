package com.github.quiram.utils;

import java.util.Optional;
import java.util.function.Function;

public class Either<L, R> {
    private Optional<L> left;
    private Optional<R> right;

    private Either(Optional<L> left, Optional<R> right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(Optional.of(left), Optional.empty());
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(Optional.empty(), Optional.of(right));
    }

    public <T> T map(Function<L, T> leftFunction, Function<R, T> rightFunction) {
        return left.map(leftFunction).orElse(
                right.map(rightFunction)
                        .orElseThrow(() -> new RuntimeException("Unexpected: Both elements in Either are empty.")
                        )
        );
    }
}
