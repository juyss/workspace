package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbForeignColsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbForeignColsExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andForColNameIsNull() {
            addCriterion("FOR_COL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andForColNameIsNotNull() {
            addCriterion("FOR_COL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andForColNameEqualTo(String value) {
            addCriterion("FOR_COL_NAME =", value, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameNotEqualTo(String value) {
            addCriterion("FOR_COL_NAME <>", value, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameGreaterThan(String value) {
            addCriterion("FOR_COL_NAME >", value, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameGreaterThanOrEqualTo(String value) {
            addCriterion("FOR_COL_NAME >=", value, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameLessThan(String value) {
            addCriterion("FOR_COL_NAME <", value, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameLessThanOrEqualTo(String value) {
            addCriterion("FOR_COL_NAME <=", value, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameLike(String value) {
            addCriterion("FOR_COL_NAME like", value, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameNotLike(String value) {
            addCriterion("FOR_COL_NAME not like", value, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameIn(List<String> values) {
            addCriterion("FOR_COL_NAME in", values, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameNotIn(List<String> values) {
            addCriterion("FOR_COL_NAME not in", values, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameBetween(String value1, String value2) {
            addCriterion("FOR_COL_NAME between", value1, value2, "forColName");
            return (Criteria) this;
        }

        public Criteria andForColNameNotBetween(String value1, String value2) {
            addCriterion("FOR_COL_NAME not between", value1, value2, "forColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameIsNull() {
            addCriterion("REF_COL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRefColNameIsNotNull() {
            addCriterion("REF_COL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRefColNameEqualTo(String value) {
            addCriterion("REF_COL_NAME =", value, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameNotEqualTo(String value) {
            addCriterion("REF_COL_NAME <>", value, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameGreaterThan(String value) {
            addCriterion("REF_COL_NAME >", value, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameGreaterThanOrEqualTo(String value) {
            addCriterion("REF_COL_NAME >=", value, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameLessThan(String value) {
            addCriterion("REF_COL_NAME <", value, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameLessThanOrEqualTo(String value) {
            addCriterion("REF_COL_NAME <=", value, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameLike(String value) {
            addCriterion("REF_COL_NAME like", value, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameNotLike(String value) {
            addCriterion("REF_COL_NAME not like", value, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameIn(List<String> values) {
            addCriterion("REF_COL_NAME in", values, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameNotIn(List<String> values) {
            addCriterion("REF_COL_NAME not in", values, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameBetween(String value1, String value2) {
            addCriterion("REF_COL_NAME between", value1, value2, "refColName");
            return (Criteria) this;
        }

        public Criteria andRefColNameNotBetween(String value1, String value2) {
            addCriterion("REF_COL_NAME not between", value1, value2, "refColName");
            return (Criteria) this;
        }

        public Criteria andPosIsNull() {
            addCriterion("POS is null");
            return (Criteria) this;
        }

        public Criteria andPosIsNotNull() {
            addCriterion("POS is not null");
            return (Criteria) this;
        }

        public Criteria andPosEqualTo(Integer value) {
            addCriterion("POS =", value, "pos");
            return (Criteria) this;
        }

        public Criteria andPosNotEqualTo(Integer value) {
            addCriterion("POS <>", value, "pos");
            return (Criteria) this;
        }

        public Criteria andPosGreaterThan(Integer value) {
            addCriterion("POS >", value, "pos");
            return (Criteria) this;
        }

        public Criteria andPosGreaterThanOrEqualTo(Integer value) {
            addCriterion("POS >=", value, "pos");
            return (Criteria) this;
        }

        public Criteria andPosLessThan(Integer value) {
            addCriterion("POS <", value, "pos");
            return (Criteria) this;
        }

        public Criteria andPosLessThanOrEqualTo(Integer value) {
            addCriterion("POS <=", value, "pos");
            return (Criteria) this;
        }

        public Criteria andPosIn(List<Integer> values) {
            addCriterion("POS in", values, "pos");
            return (Criteria) this;
        }

        public Criteria andPosNotIn(List<Integer> values) {
            addCriterion("POS not in", values, "pos");
            return (Criteria) this;
        }

        public Criteria andPosBetween(Integer value1, Integer value2) {
            addCriterion("POS between", value1, value2, "pos");
            return (Criteria) this;
        }

        public Criteria andPosNotBetween(Integer value1, Integer value2) {
            addCriterion("POS not between", value1, value2, "pos");
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