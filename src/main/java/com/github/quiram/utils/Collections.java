package com.github.quiram.utils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.quiram.utils.ArgumentChecks.ensure;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Collections {
    public static <T> Optional<T> head(List<T> list) {
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public static <T, R> R map(T o, Function<T, R> mapper) {
        return o == null ? null : mapper.apply(o);
    }

    public static <T> String mapToString(T o) {
        return map(o, T::toString);
    }

    public static <In, Out> List<Out> map(List<In> in, Function<In, Out> mapper) {
        return in.stream().map(mapper).collect(toList());
    }

    public static <In, Out> List<Out> flatMap(List<In> in, Function<In, List<Out>> mapper) {
        return in.stream().map(mapper).flatMap(List::stream).collect(toList());
    }

    public static <In, Mid, Out> List<Out> map(List<In> in, Function<In, Mid> mapper1, Function<Mid, Out> mapper2) {
        return in.stream().map(mapper1).map(mapper2).collect(toList());
    }

    @SafeVarargs
    public static <T> List<T> filter(List<T> in, Predicate<T>... filters) {
        return stream(filters).reduce(in.stream(), Stream::filter, (s1, s2) -> s2).collect(toList());
    }

    @SafeVarargs
    public static <T> Set<T> filter(Set<T> in, Predicate<T>... filters) {
        return stream(filters).reduce(in.stream(), Stream::filter, (s1, s2) -> s2).collect(toSet());
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> concat(List<T> list1, List<T> list2) {
        return concat(new List[]{list1, list2});
    }

    @SafeVarargs
    public static <T> List<T> concat(List<T>... lists) {
        return Streams.concat(stream(lists).map(List::stream)).collect(toList());
    }

    public static <T, R> Set<R> map(Set<T> in, Function<T, R> mapper) {
        return in.stream().map(mapper).collect(toSet());
    }

    public static <K, V> Map<K, V> toMap(Collection<V> items, Function<V, K> key) {
        return items.stream().collect(Collectors.toMap(key, identity()));
    }

    public static <K, V1, V2> Map<K, V2> toMap(Collection<V1> items, Function<V1, K> key, Function<V1, V2> value) {
        return items.stream().collect(Collectors.toMap(key, value));
    }

    @SafeVarargs
    public static <K, V> Map<K, V> mergeMaps(BiFunction<V, V, V> merger, Map<K, V> map1, Map<K, V> map2, Map<K, V>... moreMaps) {
        return merge(merger, map1, map2, moreMaps);
    }

    @SafeVarargs
    public static <K, V> Map<K, V> merge(BiFunction<V, V, V> merger, Map<K, V> map1, Map<K, V> map2, Map<K, V>... moreMaps) {
        Map<K, V> finalMap = new HashMap<>();
        List<Map<K, V>> allMaps = new LinkedList<>();
        allMaps.add(map1);
        allMaps.add(map2);
        allMaps.addAll(asList(moreMaps));
        allMaps.forEach(m -> m.forEach((i, bd) -> finalMap.merge(i, bd, merger)));

        return finalMap;
    }

    @SafeVarargs
    public static <T> Set<T> mergeSets(Set<T> set1, Set<T> set2, Set<T>... moreSets) {
        return merge(set1, set2, moreSets);
    }

    @SafeVarargs
    public static <T> Set<T> merge(Set<T> set1, Set<T> set2, Set<T>... moreSets) {
        final HashSet<T> finalSet = new HashSet<>(set1);
        finalSet.addAll(set2);
        asList(moreSets).forEach(finalSet::addAll);

        return finalSet;
    }

    public static <T> List<List<T>> transpose(List<List<T>> listOfLists) {
        if (listOfLists == null)
            return null;

        if (listOfLists.isEmpty() || listOfLists.get(0).isEmpty())
            return listOfLists;

        final String errorMessage = "be square (all sublists should be the same size)";
        ensure(listOfLists, "listOfLists", Collections::listsHaveSameSize, errorMessage);

        final LinkedList<List<T>> outerList = new LinkedList<>();

        IntStream.range(0, listOfLists.get(0).size()).forEach(i -> {
            final LinkedList<T> innerList = new LinkedList<>();
            outerList.add(innerList);
            for (List<T> listOfList : listOfLists) {
                innerList.add(listOfList.get(i));
            }
        });

        return outerList;
    }

    private static <T> boolean listsHaveSameSize(List<List<T>> l) {
        return l.stream().map(List::size).distinct().count() == 1;
    }
}
