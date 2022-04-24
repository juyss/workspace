package com.icepoint.framework.core.util;

import java.util.function.*;

/**
 * @author Jiawei Zhao
 */
public final class Functions {

    private static final Consumer<Object> CONSUMER_EMPTY = t -> {
    };

    private static final Supplier<Object> SUPPLIER = () -> null;

    private static final UnaryOperator<Object> FUNCTION = t -> null;

    private static final Predicate<Object> PREDICATE_TRUE = t -> true;

    private static final Predicate<Object> PREDICATE_FALSE = t -> false;

    private Functions() {
    }

    public static <T> Consumer<T> consumerEmpty() {
        return CastUtils.cast(CONSUMER_EMPTY);
    }

    public static <T> Supplier<T> supplierNull() {
        return CastUtils.cast(SUPPLIER);
    }

    public static <T> Supplier<T> toSupplier(T t) {
        return () -> t;
    }

    public static <T, R> Function<T, R> functionNull() {
        return CastUtils.cast(FUNCTION);
    }

    public static <T> Predicate<T> predicateTrue() {
        return CastUtils.cast(PREDICATE_TRUE);
    }

    public static <T> Predicate<T> predicateFalse() {
        return CastUtils.cast(PREDICATE_FALSE);
    }

    public static <T, R> Function<T, R> toFunction(Consumer<T> consumer) {
        return t -> {
            consumer.accept(t);
            return null;
        };
    }

    public static <T, R> Function<T, R> toFunction(Supplier<R> supplier) {
        return t -> supplier.get();
    }
}
