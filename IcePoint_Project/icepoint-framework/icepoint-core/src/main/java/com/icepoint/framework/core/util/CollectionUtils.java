package com.icepoint.framework.core.util;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @author Jiawei Zhao
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * 获取{@link Iterable}中的第一个元素
     *
     * @param iterable Iterable
     * @param <T>      元素类型
     * @return 第一个元素
     */
    public static <T> Optional<T> getFirst(@Nullable Iterable<T> iterable) {
        if (iterable instanceof List){
            return Optional.of(iterable)
                    .map(List.class::cast)
                    .filter(list -> !list.isEmpty())
                    .map(list -> CastUtils.cast(list.get(0)));
        }
        return Optional.ofNullable(iterable)
                .map(Iterable::iterator)
                .filter(Iterator::hasNext)
                .map(Iterator::next);
    }

    /**
     * 返回{@link Iterable}中唯一一个元素，允许不存在任何元素，此时返回null，但是不允许有1个以上的元素，否则会抛出异常<br/>
     * 当唯一一个元素的值为{@code null}时也会抛出异常
     *
     * @param iterable Iterable
     * @param <V>      返回值类型
     * @return 返回唯一一个元素，如果不存在任何元素返回null
     * @throws IncorrectResultSizeDataAccessException Iterable存在一个以上元素时
     */
    @Nullable
    public static <V> V getNullableSingleElement(Iterable<V> iterable) throws IncorrectResultSizeDataAccessException {
        Assert.notNull(iterable, "Iterable must not be null");
        Iterator<V> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        V next = iterator.next();
        Assert.notNull(next, "Iterable中唯一一个元素为空");
        if (iterator.hasNext()) {
            throw new IncorrectResultSizeDataAccessException(1);
        }
        return next;
    }

    /**
     * 返回{@link Iterable}中唯一一个元素，必须有也仅有一个元素，否则会抛出异常<br/>
     * 当唯一一个元素的值为{@code null}时也会抛出异常
     *
     * @param iterable Iterable
     * @param <V>      返回值类型
     * @return 返回唯一一个元素
     * @throws IncorrectResultSizeDataAccessException Iterable存在0个或者1个以上元素时
     */
    public static <V> V getRequiredSingleElement(Iterable<V> iterable) throws IncorrectResultSizeDataAccessException {
        Assert.notNull(iterable, "Iterable must not be null");
        Iterator<V> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            throw new IncorrectResultSizeDataAccessException(1, 0);
        }
        V next = iterator.next();
        Assert.notNull(next, "Iterable中唯一一个元素为空");
        if (iterator.hasNext()) {
            throw new IncorrectResultSizeDataAccessException(1);
        }
        return next;
    }

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static List<?> arrayToList(Object source) {
        return Arrays.asList(ObjectUtils.toObjectArray(source));
    }

    public static <E> void mergeArrayIntoCollection(Object array, Collection<E> collection) {

        Object[] arr = ObjectUtils.toObjectArray(array);
        Collections.addAll(CastUtils.cast(collection), arr);
    }

    public static void mergePropertiesIntoMap(@Nullable Properties props, Map<String, Object> map) {
        Assert.notNull(map, "Map must not be null");
        String key;
        Object value;
        if (props != null) {
            for (Enumeration<String> en = CastUtils.cast(props.propertyNames()); en.hasMoreElements(); map.put(key, value)) {
                key = en.nextElement();
                value = props.get(key);
                if (value == null) {
                    value = props.getProperty(key);
                }
            }
        }
    }

    public static boolean contains(@Nullable Iterator<?> iterator, @Nullable Object element) {
        if (iterator != null) {
            while (iterator.hasNext()) {
                Object candidate = iterator.next();
                if (ObjectUtils.nullSafeEquals(candidate, element)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean contains(@Nullable Enumeration<?> enumeration, @Nullable Object element) {
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                Object candidate = enumeration.nextElement();
                if (ObjectUtils.nullSafeEquals(candidate, element)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean containsInstance(@Nullable Collection<?> collection, @Nullable Object element) {
        if (collection != null) {

            for (Object candidate : collection) {
                if (candidate == element) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean containsAny(@Nullable Collection<?> source, @Nullable Collection<?> candidates) {
        if (!isEmpty(source) && !isEmpty(candidates)) {
            Iterator<?> var2 = candidates.iterator();

            Object candidate;
            do {
                if (!var2.hasNext()) {
                    return false;
                }

                candidate = var2.next();
            } while (!source.contains(candidate));

            return true;
        } else {
            return false;
        }
    }

    @Nullable
    public static <E> E findFirstMatch(@Nullable Collection<?> source, @Nullable Collection<E> candidates) {
        if (!isEmpty(source) && !isEmpty(candidates)) {
            Iterator<E> var2 = candidates.iterator();

            E candidate;
            do {
                if (!var2.hasNext()) {
                    return null;
                }

                candidate = var2.next();
            } while (!source.contains(candidate));

            return candidate;
        } else {
            return null;
        }
    }

    @Nullable
    public static <T> T findValueOfType(@Nullable Collection<?> collection, @Nullable Class<T> type) {
        if (isEmpty(collection)) {
            return null;
        } else {
            T value = null;
            Iterator<?> var3 = collection.iterator();

            while (true) {
                Object element;
                do {
                    if (!var3.hasNext()) {
                        return value;
                    }

                    element = var3.next();
                } while (type != null && !type.isInstance(element));

                if (value != null) {
                    return null;
                }

                value = CastUtils.cast(element);
            }
        }
    }

    @Nullable
    public static Object findValueOfType(@Nullable Collection<?> collection, @Nullable Class<?>[] types) {
        if (!isEmpty(collection) && !ObjectUtils.isEmpty(types)) {

            for (Class<?> type : types) {
                Object value = findValueOfType(collection, type);
                if (value != null) {
                    return value;
                }
            }
        }
        return null;
    }

    public static boolean hasUniqueObject(@Nullable Collection<?> collection) {
        if (isEmpty(collection)) {
            return false;
        } else {
            boolean hasCandidate = false;
            Object candidate = null;

            for (Object elem : collection) {
                if (!hasCandidate) {
                    hasCandidate = true;
                    candidate = elem;
                } else if (candidate != elem) {
                    return false;
                }
            }

            return true;
        }
    }

    @Nullable
    public static Class<?> findCommonElementType(@Nullable Collection<?> collection) {
        if (isEmpty(collection)) {
            return null;
        } else {
            Class<?> candidate = null;

            for (Object val : collection) {
                if (val != null) {
                    if (candidate == null) {
                        candidate = val.getClass();
                    } else if (candidate != val.getClass()) {
                        return null;
                    }
                }
            }

            return candidate;
        }
    }

    public static <A, E extends A> A[] toArray(Enumeration<E> enumeration, A[] array) {
        ArrayList<E> elements = new ArrayList<>();

        while (enumeration.hasMoreElements()) {
            elements.add(enumeration.nextElement());
        }

        return elements.toArray(array);
    }

    public static <E> Iterator<E> toIterator(Enumeration<E> enumeration) {
        return new EnumerationIterator<>(enumeration);
    }

    public static <K, V> MultiValueMap<K, V> toMultiValueMap(Map<K, List<V>> map) {
        return new MultiValueMapAdapter<>(map);
    }

    public static <K, V> MultiValueMap<K, V> unmodifiableMultiValueMap(MultiValueMap<? extends K, ? extends V> map) {
        Assert.notNull(map, "'map' must not be null");
        Map<K, List<V>> result = new LinkedHashMap<>(map.size());

        for (Map.Entry<? extends K, ? extends List<? extends V>> entry : map.entrySet()) {

            List<? extends V> values = Collections.unmodifiableList(entry.getValue());
            result.put(entry.getKey(), CastUtils.cast(values));
        }

        Map<K, List<V>> unmodifiableMap = Collections.unmodifiableMap(result);
        return toMultiValueMap(unmodifiableMap);
    }

    private static class MultiValueMapAdapter<K, V> implements MultiValueMap<K, V>, Serializable {

        private final Map<K, List<V>> map;

        public MultiValueMapAdapter(Map<K, List<V>> map) {
            Assert.notNull(map, "'map' must not be null");
            this.map = map;
        }

        @Override
        public void add(K key, @Nullable V value) {
            List<V> values = this.map.computeIfAbsent(key, k -> new LinkedList<>());
            values.add(value);
        }

        @Override
        public void addAll(K key, List<? extends V> values) {
            List<V> v = this.map.computeIfAbsent(key, k -> new LinkedList<>());
            v.addAll(values);
        }

        @Override
        public void addAll(MultiValueMap<K, V> values) {
            values.forEach(this::addAll);
        }

        @Override
        public V getFirst(K key) {
            List<V> values = this.map.get(key);
            return values != null ? values.get(0) : null;
        }

        @Override
        public void set(K key, @Nullable V value) {
            List<V> values = new LinkedList<>();
            values.add(value);
            this.map.put(key, values);
        }

        @Override
        public void setAll(Map<K, V> values) {
            values.forEach(this::set);
        }

        @Override
        public Map<K, V> toSingleValueMap() {
            LinkedHashMap<K, V> singleValueMap = new LinkedHashMap<>(this.map.size());
            this.map.forEach((k, v) -> singleValueMap.put(k, v.get(0)));
            return singleValueMap;
        }

        @Override
        public int size() {
            return this.map.size();
        }

        @Override
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            return this.map.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return this.map.containsValue(value);
        }

        @Override
        public List<V> get(Object key) {
            return this.map.get(key);
        }

        @Override
        public List<V> put(K key, List<V> value) {
            return this.map.put(key, value);
        }

        @Override
        public List<V> remove(Object key) {
            return this.map.remove(key);
        }

        @Override
        public void putAll(Map<? extends K, ? extends List<V>> map) {
            this.map.putAll(map);
        }

        @Override
        public void clear() {
            this.map.clear();
        }

        @Override
        public Set<K> keySet() {
            return this.map.keySet();
        }

        @Override
        public Collection<List<V>> values() {
            return this.map.values();
        }

        @Override
        public Set<Map.Entry<K, List<V>>> entrySet() {
            return this.map.entrySet();
        }

        @Override
        public boolean equals(@Nullable Object other) {
            return this == other || this.map.equals(other);
        }

        @Override
        public int hashCode() {
            return this.map.hashCode();
        }

        @Override
        public String toString() {
            return this.map.toString();
        }
    }

    private static class EnumerationIterator<E> implements Iterator<E> {

        private final Enumeration<E> enumeration;

        public EnumerationIterator(Enumeration<E> enumeration) {
            this.enumeration = enumeration;
        }

        @Override
        public boolean hasNext() {
            return this.enumeration.hasMoreElements();
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return this.enumeration.nextElement();
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported");
        }
    }
}
