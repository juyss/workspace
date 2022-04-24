package com.icepoint.framework.core.util;

import org.springframework.core.Ordered;
import org.springframework.util.Assert;
import org.springframework.util.comparator.Comparators;

import java.util.Comparator;

/**
 * 基于{@link Ordered}接口的比较器
 *
 * @author Jiawei Zhao
 */
public final class OrderedComparator implements Comparator<Object> {

    public static final OrderedComparator INSTANCE = new OrderedComparator();

    @Override
    public int compare(Object o1, Object o2) {
        Assert.isTrue(o1 instanceof Ordered && o2 instanceof Ordered, "比较对象必须为Ordered的实现类");

        return Comparator.comparing(
                (Ordered ordered) -> ordered == null ? null : ordered.getOrder(),
                Comparators.nullsLow()
        ).compare((Ordered) o1, (Ordered) o2);
    }
}
