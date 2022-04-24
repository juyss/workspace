package com.icepoint.base.config.mybatis.pageable.dialect;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Mysql的分页sql生成器
 *
 * @author Jiawei Zhao
 */
public class MysqlPageableSqlParser implements PageableSqlParser {

    private static final Pattern PATTERN_FROM;
    private static final Pattern PATTERN_COUNT_SELECT;
    private static final Pattern PATTERN_COUNT_AS;
    private static final Pattern PATTERN_LIMIT;
    private static final Pattern PATTERN_ORDER_BY;
    private static final Pattern PATTERN_FOR_UPDATE;

    static {
        PATTERN_FROM = Pattern.compile("from", Pattern.CASE_INSENSITIVE);
        PATTERN_COUNT_SELECT = Pattern.compile("(count)\\s*\\(\\s*[0-9]*\\s*\\)\\s*(as)*\\s*[\\w]*\\s*,",
                Pattern.CASE_INSENSITIVE);
        PATTERN_COUNT_AS = Pattern.compile("(as)\\s*[\\w]*", Pattern.CASE_INSENSITIVE);
        PATTERN_LIMIT = Pattern.compile("(limit)\\s*[0-9]+\\s*,*\\s*[0-9]*\\s*$", Pattern.CASE_INSENSITIVE);
        PATTERN_ORDER_BY = Pattern.compile(
                "(order)\\s*(by)\\s*[^\\s]+\\s*(asc|desc)*(.|\\s)*(,\\s*[^\\s]+\\s*(asc|desc)*(.|\\s)*)$",
                Pattern.CASE_INSENSITIVE);
        PATTERN_FOR_UPDATE = Pattern.compile("(for)\\s*(update)\\s*$", Pattern.CASE_INSENSITIVE);
    }

    @Override
    public String replaceAsPagedSql(String sql, Pageable pageable) {
        String forUpdateSql = "";
        Matcher forUpdateMatcher = PATTERN_FOR_UPDATE.matcher(sql);
        if (forUpdateMatcher.find()) {
            int forUpdateSplitIndex = forUpdateMatcher.start();
            sql = sql.substring(0, forUpdateSplitIndex).trim();
            forUpdateSql = sql.substring(forUpdateSplitIndex).trim();
        }

        StringBuilder sb = new StringBuilder(sql.length() + 14);
        sb.append(sql);
        if (pageable.getPageNumber() == 0) {
            sb.append(String.format(" LIMIT %s", pageable.getPageSize()));
        } else {
            sb.append(String.format(" LIMIT %s, %s", pageable.getOffset(), pageable.getPageSize()));
        }
        return (sb.toString() + " " + forUpdateSql).trim();
    }

    @Override
    public String replaceAsOrderedSql(String sql, Sort sort) {
        Assert.isTrue(sort.isSorted(), "缺少排序参数");

        String forUpdateSql = "";
        Matcher forUpdateMatcher = PATTERN_FOR_UPDATE.matcher(sql);
        if (forUpdateMatcher.find()) {
            int forUpdateSplitIndex = forUpdateMatcher.start();
            sql = sql.substring(0, forUpdateSplitIndex).trim();
            forUpdateSql = sql.substring(forUpdateSplitIndex).trim();
        }

        String limitExcludedSql = sql;
        String limitSql = "";
        Matcher limitMatcher = PATTERN_LIMIT.matcher(sql);
        if (limitMatcher.find()) {
            int limitSplitIndex = limitMatcher.start();
            limitExcludedSql = sql.substring(0, limitSplitIndex).trim();
            limitSql = sql.substring(limitSplitIndex).trim();
        }

        String orderBySeparator = ", ";
        String orderBySql = sort.stream()
                .map(order -> order.getProperty() + " " + order.getDirection())
                .collect(Collectors.joining(orderBySeparator));

        Matcher orderByMatcher = PATTERN_ORDER_BY.matcher(limitExcludedSql);
        if (orderByMatcher.find())
            return String.format("%s, %s %s %s", limitExcludedSql, orderBySql, limitSql, forUpdateSql);
        else
            return String.format("%s ORDER BY %s %s %s", limitExcludedSql, orderBySql, limitSql, forUpdateSql);
    }

    @Override
    public String replaceAsCountSql(String sql, Pageable pageable) {
        String[] splitedFromSqls = PATTERN_FROM.split(sql);
        StringBuilder countSql = new StringBuilder("SELECT COUNT(0) AS count");
        for (int i = 1; i < splitedFromSqls.length; i++) {
            countSql.append(" FROM ");
            countSql.append(splitedFromSqls[i].trim());
            countSql.append(" ");
        }
        return countSql.toString();
    }

    @Nullable
    @Override
    public String getCountFieldNameIfExists(String sql, Pageable pageable) {
        String selectList = PATTERN_FROM.split(sql)[0];
        Matcher countMatcher = PATTERN_COUNT_SELECT.matcher(selectList);
        if (countMatcher.find()) {
            int startIndex = countMatcher.start();
            int endIndex = countMatcher.end();
            String countAsSql = selectList.substring(startIndex, endIndex - 1);

            Matcher asMatcher = PATTERN_COUNT_AS.matcher(countAsSql);
            if (asMatcher.find()) {
                startIndex = asMatcher.start();
                endIndex = asMatcher.end();
                return countAsSql.substring(startIndex + 2, endIndex).trim();
            } else {
                return countAsSql.trim();
            }
        }
        return null;
    }

}
