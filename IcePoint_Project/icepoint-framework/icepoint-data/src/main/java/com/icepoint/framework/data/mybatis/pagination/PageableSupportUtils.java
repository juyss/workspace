package com.icepoint.framework.data.mybatis.pagination;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.icepoint.framework.core.util.CaseUtils;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.data.util.SortOnly;
import com.icepoint.framework.data.util.SqlUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.*;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public class PageableSupportUtils {

    private static final Method SET_LOCAL_PAGE;

    static {
        SET_LOCAL_PAGE = ReflectionUtils.findMethod(PageHelper.class, "setLocalPage", Page.class);
        Assert.notNull(SET_LOCAL_PAGE, "找不到setLocalPage方法");

        ReflectionUtils.makeAccessible(SET_LOCAL_PAGE);
    }

    private PageableSupportUtils() {
    }

    /**
     * 将Pageable转换成PageHelper的Page
     *
     * @param pageable Pageable
     * @param <T>      page元素类型
     * @return PageHelper的Page
     */
    public static <T> Page<T> toPageHelperPage(Pageable pageable) {

        Page<T> page = new Page<>();

        // 设置分页属性
        if (pageable.isPaged()) {
            page.setPageNum(pageable.getPageNumber() + 1);
            page.setPageSize(pageable.getPageSize());
        }

        // 设置排序属性
        Sort sort = pageable.getSort();
        if (sort.isSorted()) {

            String orderBy = sort.stream()
                    .map(order -> CaseUtils.toLowerUnderScore(order.getProperty() + " " + order.getDirection()))
                    .collect(Collectors.joining(","));

            if (StringUtils.hasText(orderBy)) {
                if (pageable.isUnpaged()) {
                    page.setOrderByOnly(true);
                }
                page.setOrderBy(orderBy);
            }
        }

        return page;
    }

    /**
     * 替换PageHelper的线程本地变量Page
     *
     * @param page 要替换的Page
     * @param <T>  page的元素类型
     */
    public static <T> void setPageHelperLocalPage(Page<T> page) {
        ReflectionUtils.invokeMethod(SET_LOCAL_PAGE, null, page);
    }

    /**
     * 将PageHelper的Page转换为Pageable
     *
     * @param page PageHelper的Page对象
     * @return Pageable
     */
    public static Pageable toPageable(Page<?> page) {

        // 处理排序Sort对象
        Sort sort;
        String orderBy = page.getOrderBy();
        if (StringUtils.hasText(orderBy)) {

            List<Sort.Order> orders = Arrays.stream(orderBy.split(","))
                    .map(orderString -> {

                        String[] split = orderString.split(" ");
                        Assert.isTrue(split.length == 2,
                                "排序格式必须是[排序字段 排序方式], 传入的参数是: " + orderString);

                        return new Sort.Order(Sort.Direction.fromString(split[1]), SqlUtils.toColumn(split[0]));

                    })
                    .collect(Collectors.toList());

            sort = Sort.by(orders);
        } else {
            sort = Sort.unsorted();
        }

        return page.isOrderByOnly()
                ? new SortOnly(sort)
                : PageRequest.of(page.getPageNum(), page.getPageSize());
    }

    /**
     * 将Mybatis Plus的IPage转换成Pageable
     *
     * @param page IPage对象
     * @return Pageable
     */
    public static Pageable toPageable(IPage<?> page) {

        Sort sort;
        List<OrderItem> orderItems = page.orders();
        if (!CollectionUtils.isEmpty(orderItems)) {

            List<Sort.Order> orders = orderItems.stream()
                    .map(orderItem -> {

                        String column = orderItem.getColumn();
                        boolean asc = orderItem.isAsc();

                        return new Sort.Order(asc ? Sort.Direction.ASC : Sort.Direction.DESC, SqlUtils.toColumn(column));
                    })
                    .collect(Collectors.toList());

            sort = Sort.by(orders);
        } else {
            sort = Sort.unsorted();
        }

        return PageRequest.of(Math.toIntExact(page.getCurrent()), Math.toIntExact(page.getSize()), sort);
    }

    @Nullable
    public static <T> T findArgument(Object paramObject, Class<T> argumentType) {

        Assert.notNull(argumentType, "查找的参数Class不能为空");
        if (argumentType.isInstance(paramObject)) {
            return CastUtils.cast(paramObject);
        } else if (paramObject instanceof Map) {
            return ((Map<?, ?>) paramObject).values().stream()
                    .filter(argumentType::isInstance)
                    .findAny()
                    .map(argumentType::cast)
                    .orElse(null);
        } else {
            return null;
        }

    }

    /**
     * 从参数列表中获取Pageable参数，找不到时返回null
     *
     * @param args 参数列表
     * @return Pageable, 可为空
     */
    @Nullable
    public static Pageable findPageableArgument(Object[] args) {

        if (ArrayUtils.isEmpty(args)) {
            return null;
        }

        Sort sort = null;
        Pageable pageable = null;

        for (Object arg : args) {
            if (arg instanceof Sort) {
                sort = (Sort) arg;
            }
            if (arg instanceof Pageable) {
                pageable = (Pageable) arg;
            }
        }

        Assert.state(!(sort != null && pageable != null), "参数中不能同时含有Sort和Pageable");

        if (pageable != null) {
            return pageable;
        }

        if (sort != null) {
            return new SortOnly(sort);
        }

        return null;
    }

    /**
     * 查询结果列表 + Pageable对象 + total属性, 构建DelegatingListPage对象
     *
     * @param content       查询结果List
     * @param pageable      Pageable
     * @param totalSupplier total属性的Supplier, 弄成回调函数的方式, 因为有可能不需要执行
     * @param <T>           page元素类型
     * @return DelegatingListPage
     */
    public static <T> DelegatingListPage<T> getPage(List<T> content, Pageable pageable, LongSupplier totalSupplier) {

        Assert.notNull(content, "Content must not be null!");
        Assert.notNull(pageable, "Pageable must not be null!");
        Assert.notNull(totalSupplier, "TotalSupplier must not be null!");

        if (pageable.isUnpaged() || pageable.getOffset() == 0) {

            if (pageable.isUnpaged() || pageable.getPageSize() > content.size()) {
                return new DelegatingListPage<>(content, pageable, content.size());
            }

            return new DelegatingListPage<>(content, pageable, totalSupplier.getAsLong());
        }

        if (!content.isEmpty() && pageable.getPageSize() > content.size()) {
            return new DelegatingListPage<>(content, pageable, pageable.getOffset() + content.size());
        }

        return new DelegatingListPage<>(content, pageable, totalSupplier.getAsLong());
    }

    /**
     * 返回空list
     *
     * @param pageable Pageable分页对象
     * @param <T>      page元素类型
     * @return DelegatingListPage
     */
    public static <T> DelegatingListPage<T> emptyPage(Pageable pageable) {
        return new DelegatingListPage<>(Collections.emptyList(), pageable, 0);
    }

    /**
     * 查询SQL拼接Order By
     *
     * @param originalSql 需要拼接的SQL
     * @param sort        排序对象
     * @return 返回拼接完成的sql
     */
    public static String concatOrderBy(String originalSql, Sort sort) throws JSQLParserException {

        if (sort.isUnsorted()) {
            return originalSql;
        }

        Select selectStatement = (Select) CCJSqlParserUtil.parse(originalSql);

        SelectVisitor visitor = new SelectVisitor() {

            @Override
            public void visit(PlainSelect plainSelect) {
                List<OrderByElement> orderByElements = plainSelect.getOrderByElements();
                List<OrderByElement> orderByElementsReturn = addOrderByElements(sort, orderByElements);
                plainSelect.setOrderByElements(orderByElementsReturn);
            }

            @Override
            public void visit(SetOperationList setOpList) {
                List<OrderByElement> orderByElements = setOpList.getOrderByElements();
                List<OrderByElement> orderByElementsReturn = addOrderByElements(sort, orderByElements);
                setOpList.setOrderByElements(orderByElementsReturn);
            }

            @Override
            public void visit(WithItem withItem) {
                throw new UnsupportedOperationException("don't known how to resolve");
            }
        };

        SelectBody selectBody = selectStatement.getSelectBody();
        selectBody.accept(visitor);
        return selectBody.toString();
    }

    private static List<OrderByElement> addOrderByElements(Sort sort, List<OrderByElement> orderByElements) {

        List<OrderByElement> orderByElementList = sort.stream()
                .map(order -> {

                    OrderByElement element = new OrderByElement();
                    element.setExpression(new Column(CaseUtils.toLowerUnderScore(order.getProperty())));
                    element.setAsc(order.isAscending());
                    element.setAscDescPresent(true);
                    return element;

                }).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(orderByElements)) {
            return orderByElementList;
        } else {
            orderByElements.addAll(orderByElementList);
            return orderByElements;
        }
    }

}
