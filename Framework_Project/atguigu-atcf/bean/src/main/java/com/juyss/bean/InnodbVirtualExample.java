package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbVirtualExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbVirtualExample() {
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

        public Criteria andTableIdIsNull() {
            addCriterion("TABLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNotNull() {
            addCriterion("TABLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdEqualTo(Long value) {
            addCriterion("TABLE_ID =", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotEqualTo(Long value) {
            addCriterion("TABLE_ID <>", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThan(Long value) {
            addCriterion("TABLE_ID >", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TABLE_ID >=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThan(Long value) {
            addCriterion("TABLE_ID <", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThanOrEqualTo(Long value) {
            addCriterion("TABLE_ID <=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdIn(List<Long> values) {
            addCriterion("TABLE_ID in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotIn(List<Long> values) {
            addCriterion("TABLE_ID not in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdBetween(Long value1, Long value2) {
            addCriterion("TABLE_ID between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotBetween(Long value1, Long value2) {
            addCriterion("TABLE_ID not between", value1, value2, "tableId");
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

        public Criteria andBasePosIsNull() {
            addCriterion("BASE_POS is null");
            return (Criteria) this;
        }

        public Criteria andBasePosIsNotNull() {
            addCriterion("BASE_POS is not null");
            return (Criteria) this;
        }

        public Criteria andBasePosEqualTo(Integer value) {
            addCriterion("BASE_POS =", value, "basePos");
            return (Criteria) this;
        }

        public Criteria andBasePosNotEqualTo(Integer value) {
            addCriterion("BASE_POS <>", value, "basePos");
            return (Criteria) this;
        }

        public Criteria andBasePosGreaterThan(Integer value) {
            addCriterion("BASE_POS >", value, "basePos");
            return (Criteria) this;
        }

        public Criteria andBasePosGreaterThanOrEqualTo(Integer value) {
            addCriterion("BASE_POS >=", value, "basePos");
            return (Criteria) this;
        }

        public Criteria andBasePosLessThan(Integer value) {
            addCriterion("BASE_POS <", value, "basePos");
            return (Criteria) this;
        }

        public Criteria andBasePosLessThanOrEqualTo(Integer value) {
            addCriterion("BASE_POS <=", value, "basePos");
            return (Criteria) this;
        }

        public Criteria andBasePosIn(List<Integer> values) {
            addCriterion("BASE_POS in", values, "basePos");
            return (Criteria) this;
        }

        public Criteria andBasePosNotIn(List<Integer> values) {
            addCriterion("BASE_POS not in", values, "basePos");
            return (Criteria) this;
        }

        public Criteria andBasePosBetween(Integer value1, Integer value2) {
            addCriterion("BASE_POS between", value1, value2, "basePos");
            return (Criteria) this;
        }

        public Criteria andBasePosNotBetween(Integer value1, Integer value2) {
            addCriterion("BASE_POS not between", value1, value2, "basePos");
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