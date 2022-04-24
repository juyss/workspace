package com.icepoint.base.config.mybatis.pageable;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.LongSupplier;

/**
 * Myabtis对Pageable支持的拦截器的工具类
 *
 * @author Jiawei Zhao
 */
@UtilityClass
public class PageableSupportUtils {


    public static <T> List<T> createPage(List<T> content, Pageable pageable, LongSupplier totalSupplier) {
        Assert.notNull(content, "Content must not be null!");
        Assert.notNull(pageable, "Pageable must not be null!");
        Assert.notNull(totalSupplier, "TotalSupplier must not be null!");

        if (pageable.isUnpaged() || pageable.getOffset() == 0) {

            if (pageable.isUnpaged() || pageable.getPageSize() > content.size()) {
                return createPage(content, pageable, content.size());
            }

            return createPage(content, pageable, totalSupplier.getAsLong());
        }

        if (content.size() != 0 && pageable.getPageSize() > content.size()) {
            return createPage(content, pageable, pageable.getOffset() + content.size());
        }

        return createPage(content, pageable, totalSupplier.getAsLong());
    }

    private static <E> List<E> createPage(List<E> content, Pageable pageable, long total) {
        return new ListDelegatePage<>(content, pageable, total);
    }

    public static long getCountProperty(List<?> resultList, String countFieldName) {
        if (resultList.size() == 0) {
            return 0L;
        } else {
            Object resultElement = resultList.get(0);
            Class<?> resultElementClass = resultElement.getClass();
            Field countField = ReflectionUtils.findField(resultElementClass, countFieldName);
            Assert.notNull(countField, "can not find count field in class: " + resultElementClass);
            ReflectionUtils.makeAccessible(countField);
            Object countValue = ReflectionUtils.getField(countField, resultElement);
            return countValue == null ? 0L : NumberUtils.parseNumber(countValue.toString(), Long.class);
        }
    }

}
