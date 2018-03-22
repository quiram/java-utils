package com.amarinperez.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * ComparableLists can be compared with one another in a lexicographic ordering fashion.
 * Lists don't need to be sorted to be comparable.
 * The type contained within the list needs to implement <code>Comparable</code>.
 * The comparison logic is similar to that of comparing Strings:
 * <ol>
 * <li>The first element of this list is compared to the first element of the other list.</li>
 * <li>If the result of this comparison isn't <code>0</code>, this will be the final result and
 * the comparison will end.</li>
 * <li>If the result is <code>0</code>, the next pair of elements will be compared.</li>
 * <li>A list that is effectively a prefix of another list is considered lower in the comparison.</li>
 * </ol>
 *
 * @param <E> The type of the elements contained in this list
 */
public class ComparableList<E extends Comparable<E>> implements List<E>, Comparable<List<E>> {
    private List<E> underlying;

    public ComparableList(List<E> underlying) {
        this.underlying = underlying;
    }

    public static <T extends Comparable<T>> ComparableList<T> comparable(List<T> list) {
        return new ComparableList<>(list);
    }

    @Override
    public int compareTo(List<E> that) {
        if (this.size() == 0 && that.size() == 0)
            return 0;

        if (this.size() == 0)
            return -1;

        if (that.size() == 0)
            return 1;

        final int first = this.get(0).compareTo(that.get(0));
        if (first != 0)
            return first;

        return comparable(this.subList(1, this.size())).compareTo(that.subList(1, that.size()));
    }

    @Override
    public String toString() {
        return underlying.toString();
    }

    @Override
    public boolean equals(Object that) {
        return (that instanceof List) && underlying.equals(that);
    }

    @Override
    public int hashCode() {
        return underlying.hashCode();
    }

    @Override
    public int size() {
        return underlying.size();
    }

    @Override
    public boolean isEmpty() {
        return underlying.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return underlying.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return underlying.iterator();
    }

    @Override
    public Object[] toArray() {
        return underlying.toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public <T> T[] toArray(T[] a) {
        return underlying.toArray(a);
    }

    @Override
    public boolean add(E t) {
        return underlying.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return underlying.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return underlying.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return underlying.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return underlying.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return underlying.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return underlying.retainAll(c);
    }

    @Override
    public void clear() {
        underlying.clear();
    }

    @Override
    public E get(int index) {
        return underlying.get(index);
    }

    @Override
    public E set(int index, E element) {
        return underlying.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        underlying.add(index, element);
    }

    @Override
    public E remove(int index) {
        return underlying.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return underlying.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return underlying.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return underlying.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return underlying.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return underlying.subList(fromIndex, toIndex);
    }
}
