package com.icepoint.framework.web.system.resource.parser;

import com.icepoint.framework.core.util.CollectionUtils;
import com.icepoint.framework.core.util.SimpleTypeUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.ResourceModelMetadata;
import com.icepoint.framework.web.system.resource.query.*;
import com.icepoint.framework.web.system.resource.source.SpecificTableSqlParseException;
import com.icepoint.framework.web.system.util.SystemResourceUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.SelectUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.icepoint.framework.core.util.SimpleTypeUtils.*;

/**
 * @author Jiawei Zhao
 */
public class MysqlSpecificTableSqlParser implements SpecificTableSqlParser {

    @Override
    public String findById(Lookup lookup, Object id) {

        LongValue longId = new LongValue(SimpleTypeUtils.parseLong(id));

        EqualsTo idCondition = parseIdCondition(lookup, longId);

        Select select = parseSelect(lookup);
        Expression where = parseWhere(lookup);

        if (where != null) {
            where = new AndExpression(where, idCondition);
        } else {
            where = idCondition;
        }

        PlainSelect selectBody = (PlainSelect) select.getSelectBody();
        assert selectBody != null;
        selectBody.setWhere(where);

        return select.toString();
    }

    @Override
    public String findOne(Lookup lookup) {
        return findAll(lookup);
    }

    @Override
    public String findAll(Lookup lookup) {

        Select select = parseSelect(lookup);
        Expression where = parseWhere(lookup);

        PlainSelect selectBody = (PlainSelect) select.getSelectBody();
        assert selectBody != null;
        selectBody.setWhere(where);

        return select.toString();
    }

    @Override
    public String add(ResourceModel model) {
        return addAll(Collections.singleton(model));
    }

    @Override
    public String addAll(Iterable<ResourceModel> models) {

        ResourceModel temp = CollectionUtils.getFirst(models)
                .orElseThrow(() -> new IllegalArgumentException("批量新增的集合不能为空"));

        Lookup lookup = temp.getLookup();

        boolean sameTable = Streams.streamable(models)
                .map(ResourceModel::getLookup)
                .map(Lookup::getMetadata)
                .map(ResourceModelMetadata::getTable)
                .map(TableMetadata::getId)
                .toSet()
                .size() == 1;

        // 批量新增的是同一个表的情况
        if (sameTable) {
            String[] columns = parseColumns(lookup);
            MultiExpressionList expressionList = new MultiExpressionList();

            Streams.stream(models)
                    .map(model -> {

                        List<Expression> values = Streams.stream(columns)
                                .map(model::getProperty)
                                .map(this::parseValue)
                                .map(Expression.class::cast)
                                .collect(Collectors.toList());

                        return new ExpressionList(values);
                    })
                    .forEach(expressionList::addExpressionList);


            Table table = parseTable(lookup);
            Insert insert = new Insert();
            insert.setColumns(Streams.streamable(columns).map(Column::new).toList());
            insert.setTable(table);
            insert.setItemsList(expressionList);

            return insert.toString();
        }

        // 批量新增的不是同一个表的情况
        else {
            throw new IllegalArgumentException("物理表批量新增暂不支持不是同一个表");
        }
    }

    @Override
    public String update(ResourceModel model) {

        Lookup lookup = model.getLookup();
        String[] columns = parseColumns(lookup);
        Table table = parseTable(lookup);

        List<Expression> expressions = Streams.stream(columns)
                .map(model::getProperty)
                .map(this::parseValue)
                .map(Expression.class::cast)
                .collect(Collectors.toList());

        Update update = new Update();
        update.setTables(Collections.singletonList(table));
        update.setColumns(Streams.streamable(columns).map(Column::new).toList());
        update.setExpressions(expressions);

        return update.toString();
    }

    @Override
    public String deleteById(Lookup lookup, Object id) {

        LongValue longId = new LongValue(SimpleTypeUtils.parseLong(id));
        Table table = parseTable(lookup);
        EqualsTo idCondition = parseIdCondition(lookup, longId);

        Delete delete = new Delete();
        delete.setTable(table);
        delete.setWhere(idCondition);
        return delete.toString();
    }

    @Override
    public String deleteAllByIds(Lookup lookup, Iterable<Object> ids) {

        Table table = parseTable(lookup);

        FieldMetadata primaryKey = SystemResourceUtils
                .getRequiredUniquePrimaryKey(lookup.getMetadata().getFields());

        InExpression idInCondition = new InExpression();
        idInCondition.setLeftExpression(new Column(SystemResourceUtils.getColumnName(primaryKey)));
        idInCondition.setRightItemsList(new ExpressionList(Streams.stream(ids)
                .mapToLong(SimpleTypeUtils::parseLong)
                .mapToObj(LongValue::new)
                .collect(Collectors.toList())));

        Delete delete = new Delete();
        delete.setTable(table);
        delete.setWhere(idInCondition);
        return delete.toString();
    }

    private Select parseSelect(Lookup lookup) {

        Table table = parseTable(lookup);
        String[] selectColumns = parseColumns(lookup);

        try {
            return SelectUtils.buildSelectFromTableAndExpressions(table, selectColumns);
        } catch (JSQLParserException e) {
            throw new SpecificTableSqlParseException("解析\"SELECT...FROM\"语句失败", e);
        }
    }

    private String[] parseColumns(Lookup lookup) {
        return Streams.stream(lookup.getMetadata().getFields())
                .map(SystemResourceUtils::getColumnName)
                .map(column -> Identifier.toIdentifier(column, true).render())
                .toArray(String[]::new);
    }

    private Table parseTable(Lookup lookup) {
        return new Table(lookup.getMetadata().getTable().getNameEn());
    }

    @Nullable
    private Expression parseWhere(Lookup lookup) { // NOSONAR

        Query query = lookup.getQuery();
        if (query.isEmpty()) {
            return null;
        }

        ConditionCollectingVisitor visitor = new ConditionCollectingVisitor();
        query.accept(visitor);

        Expression where = null;

        for (Parameter parameter : visitor) {

            String name = Streams.stream(lookup.getMetadata().getFields())
                    .filter(field -> SystemResourceUtils.getFieldName(field).equals(parameter.getName()))
                    .findAny()
                    .map(SystemResourceUtils::getColumnName)
                    .orElse(null);

            // 不对应的查询条件忽略
            if (name == null) {
                continue;
            }

            Object value = parseValue(parameter.getValue());
            Operation op = parameter.getOperation();
            LogicCondition condition = parameter.getCondition();

            Column column = new Column(name);

            Expression queryExpr;
            switch (op) {
                case EQ:
                    if (value instanceof NullValue) {
                        IsNullExpression expr = new IsNullExpression();
                        expr.setLeftExpression(column);
                        queryExpr = expr;
                    } else {
                        EqualsTo expr = new EqualsTo();
                        expr.setLeftExpression(column);
                        expr.setRightExpression((Expression) value);
                        queryExpr = expr;
                    }
                    break;
                case NE:
                    if (value instanceof NullValue) {
                        IsNullExpression isNull = new IsNullExpression();
                        isNull.setLeftExpression(column);
                        isNull.setNot(true);
                        queryExpr = isNull;
                    } else {
                        EqualsTo equalsTo = new EqualsTo();
                        equalsTo.setLeftExpression(column);
                        equalsTo.setRightExpression((Expression) value);
                        equalsTo.setNot();
                        queryExpr = equalsTo;
                    }
                    break;
                case GT:
                    GreaterThan greaterThan = new GreaterThan();
                    greaterThan.setLeftExpression(column);
                    greaterThan.setRightExpression((Expression) value);
                    queryExpr = greaterThan;
                    break;
                case GE:
                    GreaterThanEquals greaterThanEquals = new GreaterThanEquals();
                    greaterThanEquals.setLeftExpression(column);
                    greaterThanEquals.setRightExpression((Expression) value);
                    queryExpr = greaterThanEquals;
                    break;
                case LT:
                    MinorThan minorThan = new MinorThan();
                    minorThan.setLeftExpression(column);
                    minorThan.setRightExpression((Expression) value);
                    queryExpr = minorThan;
                    break;
                case LE:
                    MinorThanEquals minorThanEquals = new MinorThanEquals();
                    minorThanEquals.setLeftExpression(column);
                    minorThanEquals.setRightExpression((Expression) value);
                    queryExpr = minorThanEquals;
                    break;
                case LIKE:
                    LikeExpression like = new LikeExpression();
                    like.setLeftExpression(column);
                    like.setRightExpression((Expression) value);
                    queryExpr = like;
                    break;
                case IN:
                    InExpression in = new InExpression();
                    in.setLeftExpression(column);
                    in.setRightItemsList((ItemsList) value);
                    queryExpr = in;
                    break;
                case NOT_IN:
                    InExpression notIn = new InExpression();
                    notIn.setLeftExpression(column);
                    notIn.setRightItemsList((ItemsList) value);
                    notIn.setNot(true);
                    queryExpr = notIn;
                    break;
                case BETWEEN:
                    List<Expression> expressionList = ((ExpressionList) value).getExpressions();
                    Assert.isTrue(expressionList.size() == 2, "Between的查询参数必须是2个");

                    Between between = new Between();
                    between.setLeftExpression(column);
                    between.setBetweenExpressionStart(expressionList.get(0));
                    between.setBetweenExpressionEnd(expressionList.get(1));
                    queryExpr = between;
                    break;
                default:
                    throw new SpecificTableSqlParseException("还不支持的条件查询: " + op);
            }

            if (where == null) {
                where = queryExpr;
            } else {
                switch (condition) {
                    case AND:
                        where = new AndExpression(where, queryExpr);
                        break;
                    case OR:
                        where = new OrExpression(where, queryExpr);
                        break;
                    default:
                        throw new SpecificTableSqlParseException("未能识别的查询条件连接符: " + condition);
                }
            }
        }

        return where;
    }

    private EqualsTo parseIdCondition(Lookup lookup, LongValue longId) {
        FieldMetadata primaryKey = SystemResourceUtils
                .getRequiredUniquePrimaryKey(lookup.getMetadata().getFields());

        EqualsTo idCondition = new EqualsTo();
        idCondition.setLeftExpression(new Column(SystemResourceUtils.getColumnName(primaryKey)));
        idCondition.setRightExpression(longId);
        return idCondition;
    }

    private Object parseValue(@Nullable Object value) {

        Class<?> clazz = value == null ? null : value.getClass();

        if (value == null) {
            return new NullValue();
        } else if (isBooleanOrBoxed(clazz) || isByteOrBoxed(clazz) || isShortOrBoxed(clazz)
                || isIntegerOrBoxed(clazz) || isLongOrBoxed(clazz)) {
            return new LongValue(parseLong(value));
        } else if (isFloatOrBoxed(clazz) || isDoubleOrBoxed(clazz) || isBigDecimal(clazz)) {
            return new DoubleValue(parseString(value));
        } else if (isString(value.getClass())) {
            return new StringValue(parseString(value));
        } else if (clazz.isArray()) {
            List<Expression> expressionList = Streams.streamable((Object[]) value)
                    .map(this::parseValue)
                    .map(Expression.class::cast)
                    .toList();
            return new ExpressionList(expressionList);
        } else {
            throw new SpecificTableSqlParseException("解析查询条件时出错, 未能识别的值类型: " + value.getClass());
        }
    }

    private Update parseLogicalDelete(Table table, FieldMetadata deleteMarker, EqualsTo idCondition) {
        Update update = new Update();
        update.setTables(Collections.singletonList(table));
        update.setColumns(Collections.singletonList(new Column(SystemResourceUtils.getColumnName(deleteMarker))));
        update.setExpressions(Collections.singletonList(new LongValue(1)));
        update.setWhere(idCondition);
        return update;
    }
}
