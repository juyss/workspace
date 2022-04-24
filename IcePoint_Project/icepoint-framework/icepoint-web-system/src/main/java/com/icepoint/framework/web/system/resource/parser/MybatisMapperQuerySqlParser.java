package com.icepoint.framework.web.system.resource.parser;

import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.domain.ColumnConstants;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModelMetadata;
import com.icepoint.framework.web.system.resource.query.Operation;
import com.icepoint.framework.web.system.resource.query.Parameter;
import com.icepoint.framework.web.system.resource.query.Query;
import com.icepoint.framework.web.system.resource.query.ConditionCollectingVisitor;
import com.icepoint.framework.web.system.util.SystemConstants;
import com.icepoint.framework.web.system.util.SystemResourceUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.JDBCType;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 基于{@link Parameter}对象生成通用表查询sql的工具
 *
 * @author Jiawei Zhao
 */
@Setter
@Slf4j
public class MybatisMapperQuerySqlParser extends SqlParserSupport implements QuerySqlParser {

    /**
     * 生成的sql的样式，不包含排序的，排序用的是另外一个逻辑，在parseGenericTableSql方法中的最后可以看到
     */
    private static final String SQL_FORMAT =
            "SELECT main.no AS no FROM " +
                    "(SELECT #{selectList} FROM #{from} #{innerJoinList} #{seqLeftJoin}" +
                    "#{whereList}) AS main";

    /**
     * 各种小段sql的样式
     */
    private static final String INNER_JOIN_FORMAT = "INNER JOIN %s AS %s USING(no, resource_id)";
    private static final String SELECT_FORMAT = "%s.value AS %s";

    private static final Pattern SELECT_FUNCTION_REPLACE_PATTERN = Pattern.compile("^.*\\.value");

    /**
     * 通用数据表表名
     */
    private String tableName = SystemConstants.GENERIC_DATA_TABLE_NAME;

    /**
     * 通用数据Sequence表
     */
    private String seqTableName = SystemConstants.GENERIC_DATA_TABLE_SEQUENCE_NAME;

    private final OperationSqlParser operationSqlParser;

    public MybatisMapperQuerySqlParser(OperationSqlParser operationSqlParser) {
        this.operationSqlParser = operationSqlParser;
    }

    /**
     * 根据传入的lookup对象和pageable组装sql
     *
     * @param lookup   查询对象
     * @param pageable 分页对象
     * @return 返回生成的sql
     */
    @Override
    public String parse(Lookup lookup, Pageable pageable) {

        Assert.notNull(lookup, "lookup不能为空");

        Query query = lookup.getQuery();
        ResourceModelMetadata metadata = lookup.getMetadata();

        // 排序相关的属性
        Sort sort = pageable.getSort();
        List<Sort.Order> orderList = sort.toList();
        final boolean sorted = sort.isSorted();

        ConditionCollectingVisitor queryVisitor = new ConditionCollectingVisitor();
        queryVisitor.visit(query);

        Assert.isTrue(queryVisitor.hasParameters() || sorted, "查询和排序条件都为空，不应该调用这个方法");

        if (sorted) {
            orderList.stream().filter(order -> metadata.getFields().stream()
                    .noneMatch(field -> Objects.equals(SystemResourceUtils.getFieldName(field), order.getProperty())) // 过滤出没有匹配的字段元数据的排序字段
            ).findAny().ifPresent(order -> { // 如果存在，抛出异常
                throw new IllegalArgumentException("Unknown sort property: " + order.getProperty());
            });
        }

        final List<String> selectList = new ArrayList<>();
        final StringBuilder from = new StringBuilder();
        final List<String> innerJoinList = new ArrayList<>();
        final List<String> whereList = new ArrayList<>();

        // 最低限度的SELECT字段
        selectList.add("no AS no");

        Map<String, List<Parameter>> parametersByName = Streams.stream(queryVisitor)
                .collect(Collectors.groupingBy(Parameter::getName));

        AtomicBoolean emptySearch = new AtomicBoolean(true);
        parametersByName.forEach((name, parameters) -> {

            // 目前无视字段元数据中不存在的查询字段
            FieldMetadata fieldMetadata = findMetaField(metadata, name);
            if (fieldMetadata == null) {
                return;
            }

            emptySearch.set(false);
            final String tableAlias = getTableAlias(name);

            // 排序的字段需要加在selectList上
            if (orderList.stream().anyMatch(order -> Objects.equals(name, order.getProperty())) && sorted) {
                selectList.add(addFunctionIfNecessary(fieldMetadata, String.format(SELECT_FORMAT, tableAlias, name)));
            }

            // 第一个查询的表作为FROM的主表，其他都是INNER JOIN的联表
            if (from.length() == 0) {
                from.append(String.format("%s AS %s", tableName, tableAlias));
            } else {
                innerJoinList.add(String.format(INNER_JOIN_FORMAT, tableName, tableAlias));
            }

            // name字段的where条件
            whereList.add(withAlias(tableAlias, "name = " + quotes(name)));


            parameters.forEach(parameter -> {
                Operation operation = parameter.getOperation();
                Object value = parameter.getValue();

                // 按照op的类型拼接查询条件sql
                String conditionSql = operationSqlParser.parse(withAlias(tableAlias, "value"), operation, value);
                // 拼接好的查询条件sql加到whereList里
                whereList.add(conditionSql);
            });

            // 增加一些通用的where条件
            addCommonCondition(metadata, whereList, tableAlias);

        });

        // 选择了作为排序字段却不是查询字段的List
        List<String> orderByWithoutConditionList = Collections.emptyList();
        if (sorted) {
            orderByWithoutConditionList = sort.stream()
                    .map(Sort.Order::getProperty)
                    .filter(property -> Objects.isNull(parametersByName.get(property)))
                    .collect(Collectors.toList());
        }

        // 没有作为查询条件，但是作为排序字段的，也需要加到selectList和innerJoinList里，whereList要加上name字段的查询条件
        if (!CollectionUtils.isEmpty(orderByWithoutConditionList)) {
            orderByWithoutConditionList.forEach(field -> {
                String tableAlias = getTableAlias(field);
                if (from.length() == 0) {
                    from.append(String.format("%s AS %s", tableName, tableAlias));
                } else {
                    innerJoinList.add(String.format(INNER_JOIN_FORMAT, tableName, tableAlias));
                }

                FieldMetadata metaField = findMetaField(metadata, field);
                if (metaField == null) {
                    return;
                }

                selectList.add(addFunctionIfNecessary(metaField, String.format(SELECT_FORMAT, tableAlias, field)));
                whereList.add(String.format("%s.name = %s", tableAlias, quotes(field)));
                addCommonCondition(metadata, whereList, tableAlias);
            });
        }

        // 关联seq表
        String seqTableAlias = "`seq_" + Math.random() + "`";
        String seqLeftJoin = String.format(" LEFT JOIN %s AS %s USING(no, resource_id)", seqTableName, seqTableAlias);


        // 如果from没有和emptySearch是true，代表传入的是一个空查询，这时候用seq表作为主表
        if (from.length() == 0 && emptySearch.get()) {
            seqLeftJoin = "";
            from.append(String.format("%s AS %s", seqTableName, seqTableAlias));

            Long resourceId = metadata.getResource().getId();
            int deleted = 0;

            whereList.add(withAlias(seqTableAlias, "resource_id = " + resourceId));
            whereList.add(withAlias(seqTableAlias, "deleted = " + deleted));
        } else {
            String seqWhere = withAlias(seqTableAlias, "deleted = 0");
            whereList.add(seqWhere);
        }

        // 如果到这里from还没值，属于异常情况，下面判断异常的可能原因
        // 如果查询条件不为空，那说明传入的字段都不存在字段元数据中
        if (from.length() == 0 && !CollectionUtils.isEmpty(parametersByName)) {
            throw new IllegalArgumentException("Unknown query parameters: " + parametersByName.keySet());
        }

        // 将收集好的各种sql列表替换到SQL_PATTERN中对应的位置
        return replaceSql(selectList, from, innerJoinList, whereList, seqLeftJoin);

        // TODO: 确认没问题后删除代码
        /*
        暂时不需要这里排序
        if (sorted) {
            List<Sort.Order> newOrderList = orderList.stream()
                    .map(order -> {
                        String newOrderExp = String.format("-main.%s", order.getProperty());
                        return new Sort.Order(Sort.Direction.ASC, newOrderExp);
                    }).collect(Collectors.toList());

            MysqlPageableSqlParser parser = MysqlPageableSqlParser.INSTANCE;
            sql = parser.replaceAsOrderedSql(sql, Sort.by(newOrderList));
        }


        return sql;

         */
    }

    private String replaceSql(
            List<String> selectList, StringBuilder from,
            List<String> innerJoinList, List<String> whereList, String seqLeftJoin) {

        String sql = SQL_FORMAT;

        // SELECT
        sql = replaceSingleFragment(sql, "#{selectList}",
                String.join(", ", selectList), true);
        // FROM
        sql = replaceSingleFragment(sql, "#{from}", from.toString(), true);
        // INNER JOIN(s)
        sql = replaceSingleFragment(sql, "#{innerJoinList}",
                String.join(" ", innerJoinList), !CollectionUtils.isEmpty(innerJoinList));
        // LEFT JOIN
        sql = replaceSingleFragment(sql, "#{seqLeftJoin}", seqLeftJoin, true);
        // WHERE
        sql = replaceSingleFragment(sql, "#{whereList}",
                "WHERE " + String.join(" AND ", whereList), !CollectionUtils.isEmpty(whereList));

        return sql;
    }

    private String replaceSingleFragment(String sql, String fragment, String replacement,
            boolean replaceCondition) {
        return replaceCondition
                ? sql.replace(fragment, replacement)
                : sql.replace(fragment, "");
    }

    @Nullable
    private static FieldMetadata findMetaField(ResourceModelMetadata metadata, String field) {
        return metadata.getFields()
                .stream()
                .filter(f -> Objects.equals(SystemResourceUtils.getFieldName(f), field))
                .findAny()
                .orElse(null);
    }

    private static String addFunctionIfNecessary(FieldMetadata fieldMetadata, String selectFieldSql) {
        String sql = selectFieldSql;
        Matcher matcher = SELECT_FUNCTION_REPLACE_PATTERN.matcher(selectFieldSql);

        String nativeType = fieldMetadata.getNativeType();
        if (StringUtils.hasText(nativeType) && matcher.find()) {
            JDBCType type = JDBCType.valueOf(nativeType.toUpperCase());
            String castFormat = "CAST(%s AS %s)";
            String fieldSql = matcher.group().trim();
            switch (type) {
                case SMALLINT:
                case TINYINT:
                case INTEGER:
                case BIGINT:
                    sql = matcher.replaceAll(String.format(castFormat, fieldSql, "SIGNED"));
                    break;
                case NUMERIC:
                case FLOAT:
                case DOUBLE:
                case DECIMAL:
                    sql = matcher.replaceAll(String.format(castFormat, fieldSql, "DECIMAL(10, 2)"));
                    break;
                default:
                    break;
            }
        }

        return sql;
    }

    private String getTableAlias(String field) {
        return "`" + field + "_" + Math.random() + "`";
    }

    protected void addCommonCondition(ResourceModelMetadata metadata, List<String> whereList, String tableAlias) {

        Long resourceId = metadata.getResource().getId();
        int deleted = 0;

        String eqPattern = "%s = %s";

        whereList.add(withAlias(tableAlias, String.format(eqPattern, "resource_id", resourceId)));
        whereList.add(withAlias(tableAlias, String.format(eqPattern, ColumnConstants.DELETED, deleted)));
    }

}
