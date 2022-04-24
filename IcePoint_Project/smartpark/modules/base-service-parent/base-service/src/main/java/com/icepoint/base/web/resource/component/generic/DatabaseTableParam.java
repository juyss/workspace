package com.icepoint.base.web.resource.component.generic;

import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.api.util.Condition;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.util.StreamUtils;
import lombok.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用来构造通用的查询sql的类
 * TODO: 用了Builder模式，但是设计不够好，待改进
 *
 * @author Jiawei Zhao
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class DatabaseTableParam {

    private Long id;

    private String name;

    private List<SelectField> selectFields;

    private List<WhereField> whereFields;

    private List<Field> orderByFields;
    
    private List<Field> insertFields;

    private List<ValueField> valueFields;
    
    private ValuesField deleteField;
    
    private List<FilterField> filterFields;
    
    public void putFilter(List<Map<String,Object>> filter){
    	filterFields = StreamUtils.parallelStreamIfAvailable(filter)
    	        .filter(item -> item.containsKey("name") && item.containsKey("oper") && item.containsKey("value"))
    	        .map(item -> {
    	        	FilterField field = new FilterField();
    	        	field.setName(String.valueOf(item.get("name")));
    	        	field.setOper(String.valueOf(item.get("oper")));
    	        	field.setValue(item.get("value"));
    	        	return field;
    	        })
    	        .collect(Collectors.toList());
    }
    
    public static SelectBuilder selectFrom(Collection<MetaField> metaFields, String tableName) {
        Assert.notEmpty(metaFields, "SELECT字段不能为空");
        Assert.hasText(tableName, "table name不能为空");

        SelectBuilder builder = new SelectBuilder();
        builder.tableName = tableName;
        builder.selectFields = metaFields.stream()
                .map(fld -> {
                    SelectField field = new SelectField();
                    field.setName(fld.getStoreNameEn());
                    field.setAliasName(fld.getNameEn());
                    return field;
                }).collect(Collectors.toList());
        return builder;
    }

    public static SelectBuilder selectFrom(ResourceMetadata metadata) {
        Assert.notNull(metadata, "metadata must not be null");
        return selectFrom(metadata.getMetaFields(), metadata.getMetaTab().getNameEn());
    }
    
    public static InsertBuilder insertFrom(Collection<MetaField> metaFields, String tableName) {
        Assert.notEmpty(metaFields, "INSERT字段不能为空");
        Assert.hasText(tableName, "table name不能为空");

        InsertBuilder builder = new InsertBuilder();
        builder.tableName = tableName;
        builder.insertFields = metaFields.stream()
                .map(fld -> {
                	Field field = new Field();
                    field.setName(fld.getStoreNameEn());
                    return field;
                }).collect(Collectors.toList());
        return builder;
    }
    
    public static ValueBuilder valueFrom(Collection<ValueField> valueFields, String tableName) {
        Assert.notEmpty(valueFields, "值字段不能为空");
        Assert.hasText(tableName, "table name不能为空");

        ValueBuilder builder = new ValueBuilder();
        builder.tableName = tableName;
        builder.valueFields = valueFields.stream()
                .map(fld -> fld).collect(Collectors.toList());
        return builder;
    }
    
    public static DeleteBuilder deleteFrom(ValuesField valueField, String tableName) {
        Assert.hasText(tableName, "table name不能为空");

        DeleteBuilder builder = new DeleteBuilder();
        builder.tableName = tableName;
        builder.deleteField = valueField;
        return builder;
    }

    public interface Builder {

        DatabaseTableParam build();

    }

    private static abstract class HierarchyBuilder implements Builder {

        protected abstract void from(DatabaseTableParam param);
    }

    public static class SelectBuilder implements Builder {

        private List<SelectField> selectFields;

        private String tableName;

        private SelectBuilder() {
        }

        @Override
        public DatabaseTableParam build() {
            Assert.notEmpty(selectFields, "SELECT字段不能为空");

            DatabaseTableParam param = new DatabaseTableParam();
            param.setSelectFields(selectFields);
            param.setName(tableName);
            return param;
        }

        public WhereBuilder where() {
            WhereBuilder builder = new WhereBuilder();
            builder.from(build());
            return builder;
        }
    }

    public static class WhereBuilder extends HierarchyBuilder {

        private final List<WhereField> whereFields = new LinkedList<>();
        private DatabaseTableParam param;

        private WhereBuilder() {
        }

        @Override
        public DatabaseTableParam build() {
            if (param == null) {
                param = new DatabaseTableParam();
            }

            param.setWhereFields(whereFields);
            return param;
        }

        protected void from(DatabaseTableParam param) {
            this.param = param;
        }

        public WhereConditionBuilder field(String name, Object value) {
            Condition.isTrue(!StringUtils.hasText(name)).throwException("field name不能为空");

            WhereField field = new WhereField();
            field.setValue(value);
            if (param != null) {
                param.getSelectFields().stream()
                        .filter(selectField -> Objects.equals(selectField.getName(), name))
                        .findAny()
                        .map(SelectField::getAliasName)
                        .filter(StringUtils::hasText)
                        .ifPresent(field::setName);
            } else {
                field.setName(name);
            }
            whereFields.add(field);

            WhereConditionBuilder conditionBuilder = new WhereConditionBuilder();
            conditionBuilder.field = field;
            conditionBuilder.whereBuilder = this;
            return conditionBuilder;
        }
    }

    public static class WhereConditionBuilder implements Builder {

        private WhereField field;

        private WhereBuilder whereBuilder;

        private WhereConditionBuilder() {
        }

        @Override
        public DatabaseTableParam build() {
            return whereBuilder.build();
        }

        public WhereBuilder and() {
            field.setCondition("AND");
            return whereBuilder;
        }

        public WhereBuilder or() {
            field.setCondition("OR");
            return whereBuilder;
        }
    }

    public static class InsertBuilder implements Builder {

        private List<Field> insertFields;

        private String tableName;

        private InsertBuilder() {
        }

        @Override
        public DatabaseTableParam build() {
            Assert.notEmpty(insertFields, "INSERT字段不能为空");

            DatabaseTableParam param = new DatabaseTableParam();
            param.setInsertFields(insertFields);
            param.setName(tableName);
            return param;
        }

        public WhereBuilder where() {
            WhereBuilder builder = new WhereBuilder();
            builder.from(build());
            return builder;
        }
    }
    
    public static class ValueBuilder implements Builder {

        private List<ValueField> valueFields;

        private String tableName;

        private ValueBuilder() {
        }

        @Override
        public DatabaseTableParam build() {
            Assert.notEmpty(valueFields, "值字段不能为空");

            DatabaseTableParam param = new DatabaseTableParam();
            param.setValueFields(valueFields);
            param.setName(tableName);
            return param;
        }

        public WhereBuilder where() {
            WhereBuilder builder = new WhereBuilder();
            builder.from(build());
            return builder;
        }
    }

    public static class DeleteBuilder implements Builder {

        private ValuesField deleteField;

        private String tableName;

        private DeleteBuilder() {
        }

        @Override
        public DatabaseTableParam build() {
            //Assert.notEmpty(deleteField, "值字段不能为空");

            DatabaseTableParam param = new DatabaseTableParam();
            param.setDeleteField(deleteField);
            param.setName(tableName);
            return param;
        }

        public WhereBuilder where() {
            WhereBuilder builder = new WhereBuilder();
            builder.from(build());
            return builder;
        }
    }

    
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Field {

        private String name;

    }

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SelectField extends Field {

        private String aliasName;
    }

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class WhereField extends Field {

        private Object value;

        private String condition;
    }
    
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ValueField extends Field {
        private Object value;
    }
    
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ValuesField extends Field {
        private List<Object> values;
    }
    
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class FilterField extends Field {
        private String oper;
        private Object value;
    }
}
