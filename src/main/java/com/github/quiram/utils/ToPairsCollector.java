package com.github.quiram.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.Collections.emptySet;

class ToPairsCollector<T> implements Collector<T, ToPairsCollector<T>.Container, List<Pair<T, T>>> {

    public class Container {
        List<T> originalItems;
        List<Pair<T, T>> list;
        Optional<T> item;
    }

    @Override
    public Supplier<Container> supplier() {
        final Container container = new Container();
        container.originalItems = new ArrayList<>();
        container.list = new ArrayList<>();
        container.item = Optional.empty();
        return () -> container;
    }

    @Override
    public BiConsumer<Container, T> accumulator() {
        return ((container, item) -> {
            container.originalItems.add(item);
            if (container.item.isPresent()) {
                final Pair<T, T> pair = Pair.of(container.item.get(), item);
                container.list.add(pair);
                container.item = Optional.empty();
            } else {
                container.item = Optional.of(item);
            }
        });
    }

    @Override
    public Function<Container, List<Pair<T, T>>> finisher() {
        return container -> {
            if (!container.item.isPresent()) {
                return container.list;
            }

            throw new RuntimeException("An even number of elements was expected, but got " + container.originalItems);
        };
    }

    @Override
    public BinaryOperator<Container> combiner() {
        return ($1, $2) -> {
            throw new RuntimeException("This collector doesn't support parallel streams.");
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return emptySet();
    }
}
