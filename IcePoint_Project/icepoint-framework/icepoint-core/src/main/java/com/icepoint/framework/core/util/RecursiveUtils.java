package com.icepoint.framework.core.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 进行递归逻辑的工具类
 *
 * @author Jiawei Zhao
 */
public final class RecursiveUtils {

    private RecursiveUtils() {
    }

    /**
     * 执行递归逻辑
     *
     * @param source 原始数据
     * @param runner 传入当前数据，提取下一个数据
     * @param condition 传入当前数据，决定是否继续递归执行，返回false会中断递归，类似for循环中的break操作
     * @param collector 将递归结果收集起来的收集器
     * @param <T> 递归的数据类型
     */
    public static <T> void execute(T source, UnaryOperator<T> runner, Function<T, Boolean> condition, Consumer<T> collector) {

        if (Boolean.FALSE.equals(condition.apply(source))) {
            return;
        }

        // 第一次执行
        collector.accept(source);
        T extracted = runner.apply(source);

        // 递归执行
        executeRecursive(extracted, runner, condition, collector);
    }

    private static <T> void executeRecursive(T source, UnaryOperator<T> runner, Function<T, Boolean> condition, Consumer<T> collector) {

        // 是否执行递归
        if (Boolean.TRUE.equals(condition.apply(source))) {

            // 收集数据
            collector.accept(source);

            // 提取数据，并执行下一次递归
            T extracted = runner.apply(source);
            executeRecursive(extracted, runner, condition, collector);
        }
    }
}
