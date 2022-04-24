package com.icepoint.base.web.resource.util;

import com.icepoint.base.config.mybatis.pageable.dialect.MysqlPageableSqlParser;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.query.FieldOperation;
import com.icepoint.base.web.resource.component.query.GenericTableMatch;
import com.icepoint.base.web.resource.component.query.MatchOption;
import com.icepoint.base.web.resource.component.query.Operation;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.entity.MetaTab;
import com.mysql.cj.MysqlType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 基于Match对象生成sql的工具
 *
 * @author Jiawei Zhao
 */
@Slf4j
public class MatchSqlAssistants {

    private static final String TABLE_NAME = "t_tab_head";
    private static final String BIG_TABLE_NAME = "t_tab_head_big";
    private static final String SEQ_TABLE_NAME = "t_tab_seq";

    /**
     * 生成的sql的样式，不包含排序的，排序用的是另外一个逻辑，在parseGenericTableSql方法中的最后可以看到
     */
    private static final String SQL_FORMAT =
            "SELECT main.docNo AS docNo FROM " +
                    "(SELECT #{selectList} FROM #{from} #{innerJoinList} #{seqLeftJoin}" +
                    "#{whereList}) AS main";

    /**
     * 各种小段sql的样式
     */
    private static final String INNER_JOIN_FORMAT = "INNER JOIN %s AS %s USING(docNo, appId, ownerId, docType)";
    private static final String SELECT_FORMAT = "%s.value AS %s";

    private static final Pattern SELECT_FUNCTION_REPLACE_PATTERN = Pattern.compile("^.*\\.value");

    /**
     * 根据传入的match对象和metadata组装sql
     *
     * @param metadata 资源元数据对象
     * @param match    前端传入的match对象
     * @return 返回生成的sql
     */
    public static String parseGenericTableSql(ResourceMetadata metadata, GenericTableMatch match) {
        Assert.notNull(match, "match不能为空");
        Assert.notNull(metadata, "metadata不能为空");

        // 排序相关的属性
        Sort sort = match.getPageable().getSort();
        List<Sort.Order> orderList = sort.toList();
        final boolean sorted = sort.isSorted();
        final MatchOption option = match.getOption();

        Map<String, FieldOperation> fieldOps = match.getFieldOps();
        Assert.isTrue(!(CollectionUtils.isEmpty(fieldOps) && !sorted),
                "查询和排序条件都为空，不应该调用这个方法");

        if (sorted) {
            orderList.stream().filter(order -> metadata.getMetaFields().stream()
                    .noneMatch(metaField -> Objects.equals(metaField.getNameEn(), order.getProperty())) // 过滤出没有匹配的字段元数据的排序字段
            ).findAny().ifPresent(order -> { // 如果存在，抛出异常
                throw new IllegalArgumentException("Unknown sort property: " + order.getProperty());
            });
        }

        final List<String> selectList = new ArrayList<>();
        final StringBuilder from = new StringBuilder();
        final List<String> innerJoinList = new ArrayList<>();
        final List<String> whereList = new ArrayList<>();

        // 最低限度的SELECT字段
        selectList.add("docNo AS docNo");

        AtomicBoolean emptySearch = new AtomicBoolean(true);
        fieldOps.forEach((field, fop) -> {
            Map<Operation, Object> ops = fop.getOps();

            ops = ops.entrySet().stream()
                    .filter(entry -> {
                        Object value = entry.getValue();
                        return !(option.isIgnoreNullValue() && value == null
                                || (option.isIgnoreEmptyString() && !StringUtils.hasText(value.toString().trim())));
                    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            if (ops.size() == 0) return;

            // 目前无视字段元数据中不存在的查询字段
            MetaField metaField = findMetaField(metadata, field);
            if (metaField == null)
                return;

            emptySearch.set(false);
            final String tableAlias = getTableAlias(field);

            // 排序的字段需要加在selectList上
            if (orderList.stream().anyMatch(order -> Objects.equals(field, order.getProperty())) && sorted) {
                selectList.add(addFunctionIfNecessary(metaField, String.format(SELECT_FORMAT, tableAlias, field)));
            }

            // 第一个查询的表作为FROM的主表，其他都是INNER JOIN的联表
            if (from.length() == 0) {
                from.append(String.format("%s AS %s", getSpecificTableName(metadata, field), tableAlias));
            } else {
                innerJoinList.add(String.format(INNER_JOIN_FORMAT, getSpecificTableName(metadata, field), tableAlias));
            }

            // name字段的where条件
            whereList.add(withAlias(tableAlias, "name = " + quotes(field)));

            ops.forEach((op, value) -> {
                // 按照op的类型拼接查询条件sql
                String conditionSql = parseConditionSql(op, value, withAlias(tableAlias, " value "));
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
                    .filter(order -> Objects.isNull(fieldOps.get(order.getProperty())))
                    .map(Sort.Order::getProperty)
                    .collect(Collectors.toList());
        }
        // 没有作为查询条件，但是作为排序字段的，也需要加到selectList和innerJoinList里，whereList要加上name字段的查询条件
        if (!CollectionUtils.isEmpty(orderByWithoutConditionList)) {
            orderByWithoutConditionList.forEach(field -> {
                String tableAlias = getTableAlias(field);
                if (from.length() == 0) {
                    from.append(String.format("%s AS %s", getSpecificTableName(metadata, field), tableAlias));
                } else {
                    innerJoinList.add(String.format(INNER_JOIN_FORMAT, getSpecificTableName(metadata, field), tableAlias));
                }

                MetaField metaField = findMetaField(metadata, field);
                if (metaField == null)
                    return;
                selectList.add(addFunctionIfNecessary(metaField, String.format(SELECT_FORMAT, tableAlias, field)));
                whereList.add(String.format("%s.name = %s", tableAlias, quotes(field)));
                addCommonCondition(metadata, whereList, tableAlias);
            });
        }

        // 关联seq表
        String seqTableAlias = "`seq_" + Math.random() + "`";
        String seqLeftJoin = String.format(" LEFT JOIN %s AS %s USING(docNo, docType)", SEQ_TABLE_NAME, seqTableAlias);


        // 如果from没有和emptySearch是true，代表传入的是一个空查询，这时候用seq表作为主表
        if (from.length() == 0 && emptySearch.get()) {
            seqLeftJoin = "";
            from.append(String.format("%s AS %s", SEQ_TABLE_NAME, seqTableAlias));

            String docType = metadata.getDict().getCval();
            int deleted = 0;

            whereList.add(withAlias(seqTableAlias, "docType = " + docType));
            whereList.add(withAlias(seqTableAlias, "deleted = " + deleted));
        } else {
            String seqWhere = withAlias(seqTableAlias, "deleted = 0");
            whereList.add(seqWhere);
        }

        // 如果到这里from还没值，属于异常情况，下面判断异常的可能原因
        if (from.length() == 0) {
            // 如果查询条件不为空，那说明传入的字段都不存在字段元数据中
            if (!CollectionUtils.isEmpty(fieldOps)) {
                throw new IllegalArgumentException("Unknown match properties: " + fieldOps.keySet().toString());
            }
        }

        // 将收集好的各种sql列表替换到SQL_PATTERN中对应的位置
        String sql = replaceSql(selectList, from, innerJoinList, whereList, seqLeftJoin);

        if (sorted) {
            List<Sort.Order> newOrderList = orderList.stream()
                    .map(order -> {
                        String newOrderExp = String.format("IF(ISNULL(main.%s), 1, 0)", order.getProperty());
                        return new Sort.Order(Sort.Direction.ASC, newOrderExp);
                    }).collect(Collectors.toList());

            MysqlPageableSqlParser parser = new MysqlPageableSqlParser();
            sql = parser.replaceAsOrderedSql(sql, Sort.by(newOrderList));
        }

        return sql;
    }

    private static String replaceSql(
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

    private static String replaceSingleFragment(String sql, String fragment, String replacement, boolean replaceCondition) {
        return replaceCondition
                ? sql.replace(fragment, replacement)
                : sql.replace(fragment, "");
    }

    public static String parseConditionSql(Operation op, Object value, String conditionSql) {
        switch (op) {
            case EQ:
            case NEQ:
            case GT:
            case GE:
            case LT:
            case LE:
            case REG:
            case IN:
                conditionSql += String.format(op.formatter(), getMostSpecificString(value));
                break;
            case STARTS_WITH:
            case ENDS_WITH:
            case CONTAINS:
                conditionSql += String.format(op.formatter(), value);
                break;
            case BTW:
                Object[] betweens = (Object[]) value;
                Assert.isTrue(betweens.length == 2,
                        "btw参数数量应该是要2个，但传入了" + betweens.length + "个");
                conditionSql += String.format(op.formatter(),
                        getMostSpecificString(betweens[0]), getMostSpecificString(betweens[1]));
                break;
        }
        return conditionSql;
    }

    @Nullable
    private static MetaField findMetaField(ResourceMetadata metadata, String field) {
        return metadata.getMetaFields()
                .stream()
                .filter(f -> Objects.equals(f.getNameEn(), field))
                .findAny()
                .orElse(null);
    }

    private static String addFunctionIfNecessary(MetaField metaField, String selectFieldSql) {
        String sql = selectFieldSql;
        Matcher matcher = SELECT_FUNCTION_REPLACE_PATTERN.matcher(selectFieldSql);

        String nativeType = metaField.getNativeType();
        if (StringUtils.hasText(nativeType) && matcher.find()) {
            MysqlType type = MysqlType.valueOf(nativeType);
            String castFormat = "CAST(%s AS %s)";
            String fieldSql = matcher.group().trim();
            switch (type) {
                case TINYINT:
                case INT:
                case BIGINT:
                    sql = matcher.replaceAll(String.format(castFormat, fieldSql, "SIGNED"));
                    break;
                case FLOAT:
                case DOUBLE:
                case DECIMAL:
                    sql = matcher.replaceAll(String.format(castFormat, fieldSql, "DECIMAL(10, 2)"));
                    break;
            }
        }

        return sql;
    }

    private static String getTableAlias(String field) {
        return "`" + field + "_" + Math.random() + "`";
    }

    private static String quotes(String str) {
        Assert.notNull(str, "str不能为空");
        str = str.trim();

        String prefix = "";
        String suffix = "";
        if (!str.startsWith("'"))
            prefix = "'";
        if (!str.endsWith("'"))
            suffix = "'";

        return prefix + str.trim() + suffix;
    }

    private static String getSpecificTableName(ResourceMetadata metadata, String field) {
        List<MetaField> metaFields = metadata.getMetaFields();
        String nativeType = metaFields.stream()
                .filter(metaField -> Objects.equals(metaField.getNameEn(), field))
                .findAny()
                .map(MetaField::getNativeType)
                .orElse(null);

        return nativeType != null && nativeType.equals("TEXT")
                ? BIG_TABLE_NAME : TABLE_NAME;
    }

    private static String getMostSpecificString(Object obj) {
        if (obj instanceof String) {
            return quotes((String) obj);
        } else if (obj instanceof Number) {
            return obj.toString();
        } else if (obj instanceof Object[]) {
            return Stream.of((Object[]) obj)
                    .map(MatchSqlAssistants::getMostSpecificString)
                    .collect(Collectors.joining(", "));
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private static String withAlias(String alias, String str) {
        return alias + "." + str;
    }

    private static void addCommonCondition(ResourceMetadata metadata, List<String> whereList, String tableAlias) {
        MetaTab metaTab = metadata.getMetaTab();
        Long appId = metaTab.getAppId();
        Long ownerId = metaTab.getOwnerId();
        String docType = metadata.getDict().getCval();
        int deleted = 0;

        whereList.add(withAlias(tableAlias, "appId = " + appId));
        whereList.add(withAlias(tableAlias, "ownerId = " + ownerId));
        whereList.add(withAlias(tableAlias, "docType = " + docType));
        whereList.add(withAlias(tableAlias, "deleted = " + deleted));
    }

}
