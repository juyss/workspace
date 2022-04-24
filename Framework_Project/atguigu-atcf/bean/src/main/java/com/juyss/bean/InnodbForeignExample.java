package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbForeignExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbForeignExample() {
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

        public Criteria andForNameIsNull() {
            addCriterion("FOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andForNameIsNotNull() {
            addCriterion("FOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andForNameEqualTo(String value) {
            addCriterion("FOR_NAME =", value, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameNotEqualTo(String value) {
            addCriterion("FOR_NAME <>", value, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameGreaterThan(String value) {
            addCriterion("FOR_NAME >", value, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameGreaterThanOrEqualTo(String value) {
            addCriterion("FOR_NAME >=", value, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameLessThan(String value) {
            addCriterion("FOR_NAME <", value, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameLessThanOrEqualTo(String value) {
            addCriterion("FOR_NAME <=", value, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameLike(String value) {
            addCriterion("FOR_NAME like", value, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameNotLike(String value) {
            addCriterion("FOR_NAME not like", value, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameIn(List<String> values) {
            addCriterion("FOR_NAME in", values, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameNotIn(List<String> values) {
            addCriterion("FOR_NAME not in", values, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameBetween(String value1, String value2) {
            addCriterion("FOR_NAME between", value1, value2, "forName");
            return (Criteria) this;
        }

        public Criteria andForNameNotBetween(String value1, String value2) {
            addCriterion("FOR_NAME not between", value1, value2, "forName");
            return (Criteria) this;
        }

        public Criteria andRefNameIsNull() {
            addCriterion("REF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRefNameIsNotNull() {
            addCriterion("REF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRefNameEqualTo(String value) {
            addCriterion("REF_NAME =", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotEqualTo(String value) {
            addCriterion("REF_NAME <>", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameGreaterThan(String value) {
            addCriterion("REF_NAME >", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameGreaterThanOrEqualTo(String value) {
            addCriterion("REF_NAME >=", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameLessThan(String value) {
            addCriterion("REF_NAME <", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameLessThanOrEqualTo(String value) {
            addCriterion("REF_NAME <=", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameLike(String value) {
            addCriterion("REF_NAME like", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotLike(String value) {
            addCriterion("REF_NAME not like", value, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameIn(List<String> values) {
            addCriterion("REF_NAME in", values, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotIn(List<String> values) {
            addCriterion("REF_NAME not in", values, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameBetween(String value1, String value2) {
            addCriterion("REF_NAME between", value1, value2, "refName");
            return (Criteria) this;
        }

        public Criteria andRefNameNotBetween(String value1, String value2) {
            addCriterion("REF_NAME not between", value1, value2, "refName");
            return (Criteria) this;
        }

        public Criteria andNColsIsNull() {
            addCriterion("N_COLS is null");
            return (Criteria) this;
        }

        public Criteria andNColsIsNotNull() {
            addCriterion("N_COLS is not null");
            return (Criteria) this;
        }

        public Criteria andNColsEqualTo(Long value) {
            addCriterion("N_COLS =", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsNotEqualTo(Long value) {
            addCriterion("N_COLS <>", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsGreaterThan(Long value) {
            addCriterion("N_COLS >", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsGreaterThanOrEqualTo(Long value) {
            addCriterion("N_COLS >=", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsLessThan(Long value) {
            addCriterion("N_COLS <", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsLessThanOrEqualTo(Long value) {
            addCriterion("N_COLS <=", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsIn(List<Long> values) {
            addCriterion("N_COLS in", values, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsNotIn(List<Long> values) {
            addCriterion("N_COLS not in", values, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsBetween(Long value1, Long value2) {
            addCriterion("N_COLS between", value1, value2, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsNotBetween(Long value1, Long value2) {
            addCriterion("N_COLS not between", value1, value2, "nCols");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Long value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Long value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Long value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Long value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Long value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Long> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Long> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Long value1, Long value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Long value1, Long value2) {
            addCriterion("TYPE not between", value1, value2, "type");
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