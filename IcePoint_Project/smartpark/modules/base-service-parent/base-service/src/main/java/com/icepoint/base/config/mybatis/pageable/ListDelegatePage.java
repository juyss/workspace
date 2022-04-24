package com.icepoint.base.config.mybatis.pageable;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

/**
 * 因为Spring-Mybatis的框架里，当被判定为查询多条记录，则会调用SqlSessionTemplate的selectList()方法，
 * 这个方法的返回值是List类型，而Spring的Page接口则没有继承List，会导致转型异常。
 *
 * 所以这个类完全就是为了兼容Page和List而存在的。里面关于List的接口方法都是由{@link #getContent()}来代理的。
 *
 * @see org.apache.ibatis.binding.MapperMethod#execute
 *
 * @author Jiawei Zhao
 * @param <T>
 */
public class ListDelegatePage<T> extends PageImpl<T> implements List<T> {

    public ListDelegatePage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public ListDelegatePage(List<T> content) {
        super(content);
    }


    @Override
    public int size() {
        return getContent().size();
    }

    @Override
    public boolean contains(Object o) {
        return getContent().contains(o);
    }

    @Override
    public Object[] toArray() {
        return getContent().toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public <E> E[] toArray(E[] a) {
        return getContent().toArray(a);
    }

    @Override
    public boolean add(T t) {
        return getContent().add(t);
    }

    @Override
    public boolean remove(Object o) {
        return getContent().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getContent().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return getContent().addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return getContent().addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return getContent().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return getContent().retainAll(c);
    }

    @Override
    public void clear() {
        getContent().clear();
    }

    @Override
    public T get(int index) {
        return getContent().get(index);
    }

    @Override
    public T set(int index, T element) {
        return getContent().set(index, element);
    }

    @Override
    public void add(int index, T element) {
        getContent().add(index, element);
    }

    @Override
    public T remove(int index) {
        return getContent().remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return getContent().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return getContent().lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return getContent().listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return getContent().listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return getContent().subList(fromIndex, toIndex);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public Stream<T> stream() {
        return super.stream();
    }
}
