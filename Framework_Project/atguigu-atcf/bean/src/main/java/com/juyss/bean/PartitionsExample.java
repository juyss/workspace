package com.juyss.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartitionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PartitionsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTableCatalogIsNull() {
            addCriterion("TABLE_CATALOG is null");
            return (Criteria) this;
        }

        public Criteria andTableCatalogIsNotNull() {
            addCriterion("TABLE_CATALOG is not null");
            return (Criteria) this;
        }

        public Criteria andTableCatalogEqualTo(String value) {
            addCriterion("TABLE_CATALOG =", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotEqualTo(String value) {
            addCriterion("TABLE_CATALOG <>", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogGreaterThan(String value) {
            addCriterion("TABLE_CATALOG >", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_CATALOG >=", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogLessThan(String value) {
            addCriterion("TABLE_CATALOG <", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogLessThanOrEqualTo(String value) {
            addCriterion("TABLE_CATALOG <=", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogLike(String value) {
            addCriterion("TABLE_CATALOG like", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotLike(String value) {
            addCriterion("TABLE_CATALOG not like", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogIn(List<String> values) {
            addCriterion("TABLE_CATALOG in", values, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotIn(List<String> values) {
            addCriterion("TABLE_CATALOG not in", values, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogBetween(String value1, String value2) {
            addCriterion("TABLE_CATALOG between", value1, value2, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotBetween(String value1, String value2) {
            addCriterion("TABLE_CATALOG not between", value1, value2, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableSchemaIsNull() {
            addCriterion("TABLE_SCHEMA is null");
            return (Criteria) this;
        }

        public Criteria andTableSchemaIsNotNull() {
            addCriterion("TABLE_SCHEMA is not null");
            return (Criteria) this;
        }

        public Criteria andTableSchemaEqualTo(String value) {
            addCriterion("TABLE_SCHEMA =", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaNotEqualTo(String value) {
            addCriterion("TABLE_SCHEMA <>", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaGreaterThan(String value) {
            addCriterion("TABLE_SCHEMA >", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_SCHEMA >=", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaLessThan(String value) {
            addCriterion("TABLE_SCHEMA <", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaLessThanOrEqualTo(String value) {
            addCriterion("TABLE_SCHEMA <=", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaLike(String value) {
            addCriterion("TABLE_SCHEMA like", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaNotLike(String value) {
            addCriterion("TABLE_SCHEMA not like", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaIn(List<String> values) {
            addCriterion("TABLE_SCHEMA in", values, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaNotIn(List<String> values) {
            addCriterion("TABLE_SCHEMA not in", values, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaBetween(String value1, String value2) {
            addCriterion("TABLE_SCHEMA between", value1, value2, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaNotBetween(String value1, String value2) {
            addCriterion("TABLE_SCHEMA not between", value1, value2, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("TABLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("TABLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("TABLE_NAME =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("TABLE_NAME <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("TABLE_NAME >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("TABLE_NAME <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("TABLE_NAME like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("TABLE_NAME not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("TABLE_NAME in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("TABLE_NAME not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("TABLE_NAME between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("TABLE_NAME not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameIsNull() {
            addCriterion("PARTITION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPartitionNameIsNotNull() {
            addCriterion("PARTITION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPartitionNameEqualTo(String value) {
            addCriterion("PARTITION_NAME =", value, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameNotEqualTo(String value) {
            addCriterion("PARTITION_NAME <>", value, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameGreaterThan(String value) {
            addCriterion("PARTITION_NAME >", value, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARTITION_NAME >=", value, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameLessThan(String value) {
            addCriterion("PARTITION_NAME <", value, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameLessThanOrEqualTo(String value) {
            addCriterion("PARTITION_NAME <=", value, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameLike(String value) {
            addCriterion("PARTITION_NAME like", value, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameNotLike(String value) {
            addCriterion("PARTITION_NAME not like", value, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameIn(List<String> values) {
            addCriterion("PARTITION_NAME in", values, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameNotIn(List<String> values) {
            addCriterion("PARTITION_NAME not in", values, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameBetween(String value1, String value2) {
            addCriterion("PARTITION_NAME between", value1, value2, "partitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionNameNotBetween(String value1, String value2) {
            addCriterion("PARTITION_NAME not between", value1, value2, "partitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameIsNull() {
            addCriterion("SUBPARTITION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameIsNotNull() {
            addCriterion("SUBPARTITION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameEqualTo(String value) {
            addCriterion("SUBPARTITION_NAME =", value, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameNotEqualTo(String value) {
            addCriterion("SUBPARTITION_NAME <>", value, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameGreaterThan(String value) {
            addCriterion("SUBPARTITION_NAME >", value, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBPARTITION_NAME >=", value, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameLessThan(String value) {
            addCriterion("SUBPARTITION_NAME <", value, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameLessThanOrEqualTo(String value) {
            addCriterion("SUBPARTITION_NAME <=", value, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameLike(String value) {
            addCriterion("SUBPARTITION_NAME like", value, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameNotLike(String value) {
            addCriterion("SUBPARTITION_NAME not like", value, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameIn(List<String> values) {
            addCriterion("SUBPARTITION_NAME in", values, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameNotIn(List<String> values) {
            addCriterion("SUBPARTITION_NAME not in", values, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameBetween(String value1, String value2) {
            addCriterion("SUBPARTITION_NAME between", value1, value2, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andSubpartitionNameNotBetween(String value1, String value2) {
            addCriterion("SUBPARTITION_NAME not between", value1, value2, "subpartitionName");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionIsNull() {
            addCriterion("PARTITION_ORDINAL_POSITION is null");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionIsNotNull() {
            addCriterion("PARTITION_ORDINAL_POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionEqualTo(Integer value) {
            addCriterion("PARTITION_ORDINAL_POSITION =", value, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionNotEqualTo(Integer value) {
            addCriterion("PARTITION_ORDINAL_POSITION <>", value, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionGreaterThan(Integer value) {
            addCriterion("PARTITION_ORDINAL_POSITION >", value, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARTITION_ORDINAL_POSITION >=", value, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionLessThan(Integer value) {
            addCriterion("PARTITION_ORDINAL_POSITION <", value, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionLessThanOrEqualTo(Integer value) {
            addCriterion("PARTITION_ORDINAL_POSITION <=", value, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionIn(List<Integer> values) {
            addCriterion("PARTITION_ORDINAL_POSITION in", values, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionNotIn(List<Integer> values) {
            addCriterion("PARTITION_ORDINAL_POSITION not in", values, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionBetween(Integer value1, Integer value2) {
            addCriterion("PARTITION_ORDINAL_POSITION between", value1, value2, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionOrdinalPositionNotBetween(Integer value1, Integer value2) {
            addCriterion("PARTITION_ORDINAL_POSITION not between", value1, value2, "partitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionIsNull() {
            addCriterion("SUBPARTITION_ORDINAL_POSITION is null");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionIsNotNull() {
            addCriterion("SUBPARTITION_ORDINAL_POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionEqualTo(Integer value) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION =", value, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionNotEqualTo(Integer value) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION <>", value, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionGreaterThan(Integer value) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION >", value, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION >=", value, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionLessThan(Integer value) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION <", value, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionLessThanOrEqualTo(Integer value) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION <=", value, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionIn(List<Integer> values) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION in", values, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionNotIn(List<Integer> values) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION not in", values, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionBetween(Integer value1, Integer value2) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION between", value1, value2, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andSubpartitionOrdinalPositionNotBetween(Integer value1, Integer value2) {
            addCriterion("SUBPARTITION_ORDINAL_POSITION not between", value1, value2, "subpartitionOrdinalPosition");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodIsNull() {
            addCriterion("PARTITION_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodIsNotNull() {
            addCriterion("PARTITION_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodEqualTo(String value) {
            addCriterion("PARTITION_METHOD =", value, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodNotEqualTo(String value) {
            addCriterion("PARTITION_METHOD <>", value, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodGreaterThan(String value) {
            addCriterion("PARTITION_METHOD >", value, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodGreaterThanOrEqualTo(String value) {
            addCriterion("PARTITION_METHOD >=", value, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodLessThan(String value) {
            addCriterion("PARTITION_METHOD <", value, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodLessThanOrEqualTo(String value) {
            addCriterion("PARTITION_METHOD <=", value, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodLike(String value) {
            addCriterion("PARTITION_METHOD like", value, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodNotLike(String value) {
            addCriterion("PARTITION_METHOD not like", value, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodIn(List<String> values) {
            addCriterion("PARTITION_METHOD in", values, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodNotIn(List<String> values) {
            addCriterion("PARTITION_METHOD not in", values, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodBetween(String value1, String value2) {
            addCriterion("PARTITION_METHOD between", value1, value2, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionMethodNotBetween(String value1, String value2) {
            addCriterion("PARTITION_METHOD not between", value1, value2, "partitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodIsNull() {
            addCriterion("SUBPARTITION_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodIsNotNull() {
            addCriterion("SUBPARTITION_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodEqualTo(String value) {
            addCriterion("SUBPARTITION_METHOD =", value, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodNotEqualTo(String value) {
            addCriterion("SUBPARTITION_METHOD <>", value, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodGreaterThan(String value) {
            addCriterion("SUBPARTITION_METHOD >", value, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodGreaterThanOrEqualTo(String value) {
            addCriterion("SUBPARTITION_METHOD >=", value, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodLessThan(String value) {
            addCriterion("SUBPARTITION_METHOD <", value, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodLessThanOrEqualTo(String value) {
            addCriterion("SUBPARTITION_METHOD <=", value, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodLike(String value) {
            addCriterion("SUBPARTITION_METHOD like", value, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodNotLike(String value) {
            addCriterion("SUBPARTITION_METHOD not like", value, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodIn(List<String> values) {
            addCriterion("SUBPARTITION_METHOD in", values, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodNotIn(List<String> values) {
            addCriterion("SUBPARTITION_METHOD not in", values, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodBetween(String value1, String value2) {
            addCriterion("SUBPARTITION_METHOD between", value1, value2, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andSubpartitionMethodNotBetween(String value1, String value2) {
            addCriterion("SUBPARTITION_METHOD not between", value1, value2, "subpartitionMethod");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionIsNull() {
            addCriterion("PARTITION_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionIsNotNull() {
            addCriterion("PARTITION_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionEqualTo(String value) {
            addCriterion("PARTITION_EXPRESSION =", value, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionNotEqualTo(String value) {
            addCriterion("PARTITION_EXPRESSION <>", value, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionGreaterThan(String value) {
            addCriterion("PARTITION_EXPRESSION >", value, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("PARTITION_EXPRESSION >=", value, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionLessThan(String value) {
            addCriterion("PARTITION_EXPRESSION <", value, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionLessThanOrEqualTo(String value) {
            addCriterion("PARTITION_EXPRESSION <=", value, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionLike(String value) {
            addCriterion("PARTITION_EXPRESSION like", value, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionNotLike(String value) {
            addCriterion("PARTITION_EXPRESSION not like", value, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionIn(List<String> values) {
            addCriterion("PARTITION_EXPRESSION in", values, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionNotIn(List<String> values) {
            addCriterion("PARTITION_EXPRESSION not in", values, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionBetween(String value1, String value2) {
            addCriterion("PARTITION_EXPRESSION between", value1, value2, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andPartitionExpressionNotBetween(String value1, String value2) {
            addCriterion("PARTITION_EXPRESSION not between", value1, value2, "partitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionIsNull() {
            addCriterion("SUBPARTITION_EXPRESSION is null");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionIsNotNull() {
            addCriterion("SUBPARTITION_EXPRESSION is not null");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionEqualTo(String value) {
            addCriterion("SUBPARTITION_EXPRESSION =", value, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionNotEqualTo(String value) {
            addCriterion("SUBPARTITION_EXPRESSION <>", value, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionGreaterThan(String value) {
            addCriterion("SUBPARTITION_EXPRESSION >", value, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionGreaterThanOrEqualTo(String value) {
            addCriterion("SUBPARTITION_EXPRESSION >=", value, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionLessThan(String value) {
            addCriterion("SUBPARTITION_EXPRESSION <", value, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionLessThanOrEqualTo(String value) {
            addCriterion("SUBPARTITION_EXPRESSION <=", value, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionLike(String value) {
            addCriterion("SUBPARTITION_EXPRESSION like", value, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionNotLike(String value) {
            addCriterion("SUBPARTITION_EXPRESSION not like", value, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionIn(List<String> values) {
            addCriterion("SUBPARTITION_EXPRESSION in", values, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionNotIn(List<String> values) {
            addCriterion("SUBPARTITION_EXPRESSION not in", values, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionBetween(String value1, String value2) {
            addCriterion("SUBPARTITION_EXPRESSION between", value1, value2, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andSubpartitionExpressionNotBetween(String value1, String value2) {
            addCriterion("SUBPARTITION_EXPRESSION not between", value1, value2, "subpartitionExpression");
            return (Criteria) this;
        }

        public Criteria andTableRowsIsNull() {
            addCriterion("TABLE_ROWS is null");
            return (Criteria) this;
        }

        public Criteria andTableRowsIsNotNull() {
            addCriterion("TABLE_ROWS is not null");
            return (Criteria) this;
        }

        public Criteria andTableRowsEqualTo(Long value) {
            addCriterion("TABLE_ROWS =", value, "tableRows");
            return (Criteria) this;
        }

        public Criteria andTableRowsNotEqualTo(Long value) {
            addCriterion("TABLE_ROWS <>", value, "tableRows");
            return (Criteria) this;
        }

        public Criteria andTableRowsGreaterThan(Long value) {
            addCriterion("TABLE_ROWS >", value, "tableRows");
            return (Criteria) this;
        }

        public Criteria andTableRowsGreaterThanOrEqualTo(Long value) {
            addCriterion("TABLE_ROWS >=", value, "tableRows");
            return (Criteria) this;
        }

        public Criteria andTableRowsLessThan(Long value) {
            addCriterion("TABLE_ROWS <", value, "tableRows");
            return (Criteria) this;
        }

        public Criteria andTableRowsLessThanOrEqualTo(Long value) {
            addCriterion("TABLE_ROWS <=", value, "tableRows");
            return (Criteria) this;
        }

        public Criteria andTableRowsIn(List<Long> values) {
            addCriterion("TABLE_ROWS in", values, "tableRows");
            return (Criteria) this;
        }

        public Criteria andTableRowsNotIn(List<Long> values) {
            addCriterion("TABLE_ROWS not in", values, "tableRows");
            return (Criteria) this;
        }

        public Criteria andTableRowsBetween(Long value1, Long value2) {
            addCriterion("TABLE_ROWS between", value1, value2, "tableRows");
            return (Criteria) this;
        }

        public Criteria andTableRowsNotBetween(Long value1, Long value2) {
            addCriterion("TABLE_ROWS not between", value1, value2, "tableRows");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthIsNull() {
            addCriterion("AVG_ROW_LENGTH is null");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthIsNotNull() {
            addCriterion("AVG_ROW_LENGTH is not null");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthEqualTo(Long value) {
            addCriterion("AVG_ROW_LENGTH =", value, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthNotEqualTo(Long value) {
            addCriterion("AVG_ROW_LENGTH <>", value, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthGreaterThan(Long value) {
            addCriterion("AVG_ROW_LENGTH >", value, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthGreaterThanOrEqualTo(Long value) {
            addCriterion("AVG_ROW_LENGTH >=", value, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthLessThan(Long value) {
            addCriterion("AVG_ROW_LENGTH <", value, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthLessThanOrEqualTo(Long value) {
            addCriterion("AVG_ROW_LENGTH <=", value, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthIn(List<Long> values) {
            addCriterion("AVG_ROW_LENGTH in", values, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthNotIn(List<Long> values) {
            addCriterion("AVG_ROW_LENGTH not in", values, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthBetween(Long value1, Long value2) {
            addCriterion("AVG_ROW_LENGTH between", value1, value2, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andAvgRowLengthNotBetween(Long value1, Long value2) {
            addCriterion("AVG_ROW_LENGTH not between", value1, value2, "avgRowLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNull() {
            addCriterion("DATA_LENGTH is null");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNotNull() {
            addCriterion("DATA_LENGTH is not null");
            return (Criteria) this;
        }

        public Criteria andDataLengthEqualTo(Long value) {
            addCriterion("DATA_LENGTH =", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotEqualTo(Long value) {
            addCriterion("DATA_LENGTH <>", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThan(Long value) {
            addCriterion("DATA_LENGTH >", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThanOrEqualTo(Long value) {
            addCriterion("DATA_LENGTH >=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThan(Long value) {
            addCriterion("DATA_LENGTH <", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThanOrEqualTo(Long value) {
            addCriterion("DATA_LENGTH <=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthIn(List<Long> values) {
            addCriterion("DATA_LENGTH in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotIn(List<Long> values) {
            addCriterion("DATA_LENGTH not in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthBetween(Long value1, Long value2) {
            addCriterion("DATA_LENGTH between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotBetween(Long value1, Long value2) {
            addCriterion("DATA_LENGTH not between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthIsNull() {
            addCriterion("MAX_DATA_LENGTH is null");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthIsNotNull() {
            addCriterion("MAX_DATA_LENGTH is not null");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthEqualTo(Long value) {
            addCriterion("MAX_DATA_LENGTH =", value, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthNotEqualTo(Long value) {
            addCriterion("MAX_DATA_LENGTH <>", value, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthGreaterThan(Long value) {
            addCriterion("MAX_DATA_LENGTH >", value, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthGreaterThanOrEqualTo(Long value) {
            addCriterion("MAX_DATA_LENGTH >=", value, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthLessThan(Long value) {
            addCriterion("MAX_DATA_LENGTH <", value, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthLessThanOrEqualTo(Long value) {
            addCriterion("MAX_DATA_LENGTH <=", value, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthIn(List<Long> values) {
            addCriterion("MAX_DATA_LENGTH in", values, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthNotIn(List<Long> values) {
            addCriterion("MAX_DATA_LENGTH not in", values, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthBetween(Long value1, Long value2) {
            addCriterion("MAX_DATA_LENGTH between", value1, value2, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andMaxDataLengthNotBetween(Long value1, Long value2) {
            addCriterion("MAX_DATA_LENGTH not between", value1, value2, "maxDataLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthIsNull() {
            addCriterion("INDEX_LENGTH is null");
            return (Criteria) this;
        }

        public Criteria andIndexLengthIsNotNull() {
            addCriterion("INDEX_LENGTH is not null");
            return (Criteria) this;
        }

        public Criteria andIndexLengthEqualTo(Long value) {
            addCriterion("INDEX_LENGTH =", value, "indexLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthNotEqualTo(Long value) {
            addCriterion("INDEX_LENGTH <>", value, "indexLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthGreaterThan(Long value) {
            addCriterion("INDEX_LENGTH >", value, "indexLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthGreaterThanOrEqualTo(Long value) {
            addCriterion("INDEX_LENGTH >=", value, "indexLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthLessThan(Long value) {
            addCriterion("INDEX_LENGTH <", value, "indexLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthLessThanOrEqualTo(Long value) {
            addCriterion("INDEX_LENGTH <=", value, "indexLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthIn(List<Long> values) {
            addCriterion("INDEX_LENGTH in", values, "indexLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthNotIn(List<Long> values) {
            addCriterion("INDEX_LENGTH not in", values, "indexLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthBetween(Long value1, Long value2) {
            addCriterion("INDEX_LENGTH between", value1, value2, "indexLength");
            return (Criteria) this;
        }

        public Criteria andIndexLengthNotBetween(Long value1, Long value2) {
            addCriterion("INDEX_LENGTH not between", value1, value2, "indexLength");
            return (Criteria) this;
        }

        public Criteria andDataFreeIsNull() {
            addCriterion("DATA_FREE is null");
            return (Criteria) this;
        }

        public Criteria andDataFreeIsNotNull() {
            addCriterion("DATA_FREE is not null");
            return (Criteria) this;
        }

        public Criteria andDataFreeEqualTo(Long value) {
            addCriterion("DATA_FREE =", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeNotEqualTo(Long value) {
            addCriterion("DATA_FREE <>", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeGreaterThan(Long value) {
            addCriterion("DATA_FREE >", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeGreaterThanOrEqualTo(Long value) {
            addCriterion("DATA_FREE >=", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeLessThan(Long value) {
            addCriterion("DATA_FREE <", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeLessThanOrEqualTo(Long value) {
            addCriterion("DATA_FREE <=", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeIn(List<Long> values) {
            addCriterion("DATA_FREE in", values, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeNotIn(List<Long> values) {
            addCriterion("DATA_FREE not in", values, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeBetween(Long value1, Long value2) {
            addCriterion("DATA_FREE between", value1, value2, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeNotBetween(Long value1, Long value2) {
            addCriterion("DATA_FREE not between", value1, value2, "dataFree");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("CHECK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("CHECK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("CHECK_TIME =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("CHECK_TIME <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("CHECK_TIME >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CHECK_TIME >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("CHECK_TIME <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("CHECK_TIME <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("CHECK_TIME in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("CHECK_TIME not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("CHECK_TIME between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("CHECK_TIME not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andChecksumIsNull() {
            addCriterion("CHECKSUM is null");
            return (Criteria) this;
        }

        public Criteria andChecksumIsNotNull() {
            addCriterion("CHECKSUM is not null");
            return (Criteria) this;
        }

        public Criteria andChecksumEqualTo(Long value) {
            addCriterion("CHECKSUM =", value, "checksum");
            return (Criteria) this;
        }

        public Criteria andChecksumNotEqualTo(Long value) {
            addCriterion("CHECKSUM <>", value, "checksum");
            return (Criteria) this;
        }

        public Criteria andChecksumGreaterThan(Long value) {
            addCriterion("CHECKSUM >", value, "checksum");
            return (Criteria) this;
        }

        public Criteria andChecksumGreaterThanOrEqualTo(Long value) {
            addCriterion("CHECKSUM >=", value, "checksum");
            return (Criteria) this;
        }

        public Criteria andChecksumLessThan(Long value) {
            addCriterion("CHECKSUM <", value, "checksum");
            return (Criteria) this;
        }

        public Criteria andChecksumLessThanOrEqualTo(Long value) {
            addCriterion("CHECKSUM <=", value, "checksum");
            return (Criteria) this;
        }

        public Criteria andChecksumIn(List<Long> values) {
            addCriterion("CHECKSUM in", values, "checksum");
            return (Criteria) this;
        }

        public Criteria andChecksumNotIn(List<Long> values) {
            addCriterion("CHECKSUM not in", values, "checksum");
            return (Criteria) this;
        }

        public Criteria andChecksumBetween(Long value1, Long value2) {
            addCriterion("CHECKSUM between", value1, value2, "checksum");
            return (Criteria) this;
        }

        public Criteria andChecksumNotBetween(Long value1, Long value2) {
            addCriterion("CHECKSUM not between", value1, value2, "checksum");
            return (Criteria) this;
        }

        public Criteria andNodegroupIsNull() {
            addCriterion("NODEGROUP is null");
            return (Criteria) this;
        }

        public Criteria andNodegroupIsNotNull() {
            addCriterion("NODEGROUP is not null");
            return (Criteria) this;
        }

        public Criteria andNodegroupEqualTo(String value) {
            addCriterion("NODEGROUP =", value, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupNotEqualTo(String value) {
            addCriterion("NODEGROUP <>", value, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupGreaterThan(String value) {
            addCriterion("NODEGROUP >", value, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupGreaterThanOrEqualTo(String value) {
            addCriterion("NODEGROUP >=", value, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupLessThan(String value) {
            addCriterion("NODEGROUP <", value, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupLessThanOrEqualTo(String value) {
            addCriterion("NODEGROUP <=", value, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupLike(String value) {
            addCriterion("NODEGROUP like", value, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupNotLike(String value) {
            addCriterion("NODEGROUP not like", value, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupIn(List<String> values) {
            addCriterion("NODEGROUP in", values, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupNotIn(List<String> values) {
            addCriterion("NODEGROUP not in", values, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupBetween(String value1, String value2) {
            addCriterion("NODEGROUP between", value1, value2, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andNodegroupNotBetween(String value1, String value2) {
            addCriterion("NODEGROUP not between", value1, value2, "nodegroup");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameIsNull() {
            addCriterion("TABLESPACE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameIsNotNull() {
            addCriterion("TABLESPACE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameEqualTo(String value) {
            addCriterion("TABLESPACE_NAME =", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameNotEqualTo(String value) {
            addCriterion("TABLESPACE_NAME <>", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameGreaterThan(String value) {
            addCriterion("TABLESPACE_NAME >", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("TABLESPACE_NAME >=", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameLessThan(String value) {
            addCriterion("TABLESPACE_NAME <", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameLessThanOrEqualTo(String value) {
            addCriterion("TABLESPACE_NAME <=", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameLike(String value) {
            addCriterion("TABLESPACE_NAME like", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameNotLike(String value) {
            addCriterion("TABLESPACE_NAME not like", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameIn(List<String> values) {
            addCriterion("TABLESPACE_NAME in", values, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameNotIn(List<String> values) {
            addCriterion("TABLESPACE_NAME not in", values, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameBetween(String value1, String value2) {
            addCriterion("TABLESPACE_NAME between", value1, value2, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameNotBetween(String value1, String value2) {
            addCriterion("TABLESPACE_NAME not between", value1, value2, "tablespaceName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}