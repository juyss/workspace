package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class ViewTableUsageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ViewTableUsageExample() {
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

        public Criteria andViewCatalogIsNull() {
            addCriterion("VIEW_CATALOG is null");
            return (Criteria) this;
        }

        public Criteria andViewCatalogIsNotNull() {
            addCriterion("VIEW_CATALOG is not null");
            return (Criteria) this;
        }

        public Criteria andViewCatalogEqualTo(String value) {
            addCriterion("VIEW_CATALOG =", value, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogNotEqualTo(String value) {
            addCriterion("VIEW_CATALOG <>", value, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogGreaterThan(String value) {
            addCriterion("VIEW_CATALOG >", value, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("VIEW_CATALOG >=", value, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogLessThan(String value) {
            addCriterion("VIEW_CATALOG <", value, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogLessThanOrEqualTo(String value) {
            addCriterion("VIEW_CATALOG <=", value, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogLike(String value) {
            addCriterion("VIEW_CATALOG like", value, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogNotLike(String value) {
            addCriterion("VIEW_CATALOG not like", value, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogIn(List<String> values) {
            addCriterion("VIEW_CATALOG in", values, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogNotIn(List<String> values) {
            addCriterion("VIEW_CATALOG not in", values, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogBetween(String value1, String value2) {
            addCriterion("VIEW_CATALOG between", value1, value2, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewCatalogNotBetween(String value1, String value2) {
            addCriterion("VIEW_CATALOG not between", value1, value2, "viewCatalog");
            return (Criteria) this;
        }

        public Criteria andViewSchemaIsNull() {
            addCriterion("VIEW_SCHEMA is null");
            return (Criteria) this;
        }

        public Criteria andViewSchemaIsNotNull() {
            addCriterion("VIEW_SCHEMA is not null");
            return (Criteria) this;
        }

        public Criteria andViewSchemaEqualTo(String value) {
            addCriterion("VIEW_SCHEMA =", value, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaNotEqualTo(String value) {
            addCriterion("VIEW_SCHEMA <>", value, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaGreaterThan(String value) {
            addCriterion("VIEW_SCHEMA >", value, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaGreaterThanOrEqualTo(String value) {
            addCriterion("VIEW_SCHEMA >=", value, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaLessThan(String value) {
            addCriterion("VIEW_SCHEMA <", value, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaLessThanOrEqualTo(String value) {
            addCriterion("VIEW_SCHEMA <=", value, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaLike(String value) {
            addCriterion("VIEW_SCHEMA like", value, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaNotLike(String value) {
            addCriterion("VIEW_SCHEMA not like", value, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaIn(List<String> values) {
            addCriterion("VIEW_SCHEMA in", values, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaNotIn(List<String> values) {
            addCriterion("VIEW_SCHEMA not in", values, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaBetween(String value1, String value2) {
            addCriterion("VIEW_SCHEMA between", value1, value2, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewSchemaNotBetween(String value1, String value2) {
            addCriterion("VIEW_SCHEMA not between", value1, value2, "viewSchema");
            return (Criteria) this;
        }

        public Criteria andViewNameIsNull() {
            addCriterion("VIEW_NAME is null");
            return (Criteria) this;
        }

        public Criteria andViewNameIsNotNull() {
            addCriterion("VIEW_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andViewNameEqualTo(String value) {
            addCriterion("VIEW_NAME =", value, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameNotEqualTo(String value) {
            addCriterion("VIEW_NAME <>", value, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameGreaterThan(String value) {
            addCriterion("VIEW_NAME >", value, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameGreaterThanOrEqualTo(String value) {
            addCriterion("VIEW_NAME >=", value, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameLessThan(String value) {
            addCriterion("VIEW_NAME <", value, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameLessThanOrEqualTo(String value) {
            addCriterion("VIEW_NAME <=", value, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameLike(String value) {
            addCriterion("VIEW_NAME like", value, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameNotLike(String value) {
            addCriterion("VIEW_NAME not like", value, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameIn(List<String> values) {
            addCriterion("VIEW_NAME in", values, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameNotIn(List<String> values) {
            addCriterion("VIEW_NAME not in", values, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameBetween(String value1, String value2) {
            addCriterion("VIEW_NAME between", value1, value2, "viewName");
            return (Criteria) this;
        }

        public Criteria andViewNameNotBetween(String value1, String value2) {
            addCriterion("VIEW_NAME not between", value1, value2, "viewName");
            return (Criteria) this;
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