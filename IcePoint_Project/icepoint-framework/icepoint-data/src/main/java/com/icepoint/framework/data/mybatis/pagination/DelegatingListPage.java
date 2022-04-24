package com.icepoint.framework.data.mybatis.pagination;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @author Jiawei Zhao
 */
public class DelegatingListPage<T> extends PageImpl<T> implements List<T> {

    private final List<T> content; // NOSONAR

    public DelegatingListPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
        this.content = content;
    }

    @Override
    public int size() {
        return this.content.size();
    }

    @Override
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.content.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return this.content.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.content.toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public <E> E[] toArray(E[] a) {
        return this.content.toArray(a);
    }

    @Override
    public boolean add(T e) {
        return this.content.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return this.content.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.content.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return this.content.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return this.content.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.content.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.content.retainAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        this.content.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        this.content.sort(c);
    }

    @Override
    public void clear() {
        this.content.clear();
    }

    @Override
    public T get(int index) {
        return this.content.get(index);
    }

    @Override
    public T set(int index, T element) {
        return this.content.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        this.content.add(index, element);
    }

    @Override
    public T remove(int index) {
        return this.content.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.content.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.content.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.content.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return this.content.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return this.content.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<T> spliterator() {
        return this.content.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return this.content.removeIf(filter);
    }

    @Override
    public Stream<T> stream() {
        return this.content.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return this.content.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        this.content.forEach(action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DelegatingListPage<?>)) {
            return false;
        }

        DelegatingListPage<?> that = (DelegatingListPage<?>) o;

        return this.content.equals(that.content) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), content);
    }
}
