package com.icepoint.base.api.util;

import com.github.tangyi.common.core.exceptions.ServiceException;
import lombok.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * 根据给定条件进行抛出或者执行回调函数。
 * TODO: jiawei: 感觉没有设计好，功能上非常类似{@link Assert}，待改进
 *
 * @author Jiawei Zhao
 */
public class Condition {

    private final List<Boolean> tests = new LinkedList<>();

    private Runnable successCallback;

    private Runnable failCallback;

    private String exceptionMessage;

    private Condition() {
    }

    public static Condition isTrue(boolean test) {
        Condition condition = new Condition();
        condition.tests.add(test);
        return condition;
    }

    public static Condition isTrue(@NonNull BooleanSupplier testSupplier) {
        return isTrue(testSupplier.getAsBoolean());
    }

    public static Condition isZero(Number number) {
        return isTrue(number.equals(0));
    }

    public static Condition isNull(Object object) {
        return isTrue(Objects.isNull(object));
    }

    public static Condition notNull(Object object) {
        return isTrue(Objects.nonNull(object));
    }

    public static Condition isEmpty(Collection<?> collection) {
        return isTrue(CollectionUtils.isEmpty(collection));
    }

    public static Condition isNotEmpty(Collection<?> collection) {
        return isTrue(!CollectionUtils.isEmpty(collection));
    }

    public static Condition isEmpty(Map<?, ?> map) {
        return isTrue(CollectionUtils.isEmpty(map));
    }

    public static Condition isNotEmpty(Map<?, ?> map) {
        return isTrue(!CollectionUtils.isEmpty(map));
    }

    public static Condition notExpectedSize(Collection<?> collection, int expectedSize) {
        Assert.notNull(collection, "collection must not be null");
        boolean result;
        Condition condition = new Condition();
        if (collection.size() != expectedSize) {
            result = true;
            condition.exceptionMessage =
                    String.format("集合中预计有%s条数据，但现在却有%s条数据", expectedSize, collection.size());
        } else {
            result = false;
        }
        condition.tests.add(result);
        return condition;
    }

    private void terminated() {
        tests.stream()
                .reduce((a, b) -> a && b)
                .ifPresent(testResult -> {
                    if (testResult && successCallback != null)
                        successCallback.run();
                    else if (!testResult && failCallback != null)
                        failCallback.run();
                });
    }

    public void throwException() {
        successCallback = this::throwException;
        terminated();
    }

    public void throwException(String message) {
        successCallback = () -> {
            throw new ServiceException(message);
        };
        terminated();
    }

    public void throwException(int code) {
        successCallback = () -> {
            throw new ServiceException(code);
        };
        terminated();
    }

    public <E extends RuntimeException> void throwException(Supplier<E> exceptionSupplier) {
        successCallback = () -> {
            throw exceptionSupplier.get();
        };
        terminated();
    }

    public void callback(Runnable successCallback, Runnable failCallback) {
        this.successCallback = successCallback;
        this.failCallback = failCallback;
        terminated();
    }


}
