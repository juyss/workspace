package com.icepoint.framework.core.flow.dsl;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class Aggregators {

    private Aggregators() {}

    public static Aggregator origin() {
        return results -> results;
    }

    public static Aggregator ignore() {
        return results -> null;
    }

    public static Aggregator first() {
        return results -> checkNotEmptyAndGet(results, 0);
    }

    public static Aggregator last() {
        return results -> checkNotEmptyAndGet(results, results.size() - 1);
    }

    public static Aggregator any() {
        return results -> CollectionUtils.isEmpty(results) ? null : results.get(0);
    }

    public static Aggregator one() {
        return results -> {
            Assert.isTrue(!CollectionUtils.isEmpty(results), "结果集为空");
            Assert.isTrue(results.size() == 1, "结果集的元素多于一个");
            return results.get(0);
        };
    }

    private static Object checkNotEmptyAndGet(List<Object> list, int index) {
        Assert.notEmpty(list, "结果集为空");
        Assert.isTrue(list.size() >= index -1, "结果集没有该下标的元素");
        return list.get(index);
    }
}
