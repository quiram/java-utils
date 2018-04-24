package com.github.quiram.utils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Collections {
    public static <T, R> R map(T o, Function<T, R> mapper) {
        return o == null ? null : mapper.apply(o);
    }

    public static <T> String mapToString(T o) {
        return map(o, T::toString);
    }

    public static <T, R> List<R> map(List<T> in, Function<T, R> mapper) {
        return in.stream().map(mapper).collect(toList());
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

}
