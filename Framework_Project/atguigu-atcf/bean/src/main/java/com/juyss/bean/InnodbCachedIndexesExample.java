package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbCachedIndexesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbCachedIndexesExample() {
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

        public Criteria andSpaceIdIsNull() {
            addCriterion("SPACE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpaceIdIsNotNull() {
            addCriterion("SPACE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceIdEqualTo(Integer value) {
            addCriterion("SPACE_ID =", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdNotEqualTo(Integer value) {
            addCriterion("SPACE_ID <>", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdGreaterThan(Integer value) {
            addCriterion("SPACE_ID >", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPACE_ID >=", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdLessThan(Integer value) {
            addCriterion("SPACE_ID <", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdLessThanOrEqualTo(Integer value) {
            addCriterion("SPACE_ID <=", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdIn(List<Integer> values) {
            addCriterion("SPACE_ID in", values, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdNotIn(List<Integer> values) {
            addCriterion("SPACE_ID not in", values, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdBetween(Integer value1, Integer value2) {
            addCriterion("SPACE_ID between", value1, value2, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SPACE_ID not between", value1, value2, "spaceId");
            return (Criteria) this;
        }

        public Criteria andIndexIdIsNull() {
            addCriterion("INDEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andIndexIdIsNotNull() {
            addCriterion("INDEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIndexIdEqualTo(Long value) {
            addCriterion("INDEX_ID =", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotEqualTo(Long value) {
            addCriterion("INDEX_ID <>", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdGreaterThan(Long value) {
            addCriterion("INDEX_ID >", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INDEX_ID >=", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdLessThan(Long value) {
            addCriterion("INDEX_ID <", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdLessThanOrEqualTo(Long value) {
            addCriterion("INDEX_ID <=", value, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdIn(List<Long> values) {
            addCriterion("INDEX_ID in", values, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotIn(List<Long> values) {
            addCriterion("INDEX_ID not in", values, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdBetween(Long value1, Long value2) {
            addCriterion("INDEX_ID between", value1, value2, "indexId");
            return (Criteria) this;
        }

        public Criteria andIndexIdNotBetween(Long value1, Long value2) {
            addCriterion("INDEX_ID not between", value1, value2, "indexId");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesIsNull() {
            addCriterion("N_CACHED_PAGES is null");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesIsNotNull() {
            addCriterion("N_CACHED_PAGES is not null");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesEqualTo(Long value) {
            addCriterion("N_CACHED_PAGES =", value, "nCachedPages");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesNotEqualTo(Long value) {
            addCriterion("N_CACHED_PAGES <>", value, "nCachedPages");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesGreaterThan(Long value) {
            addCriterion("N_CACHED_PAGES >", value, "nCachedPages");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesGreaterThanOrEqualTo(Long value) {
            addCriterion("N_CACHED_PAGES >=", value, "nCachedPages");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesLessThan(Long value) {
            addCriterion("N_CACHED_PAGES <", value, "nCachedPages");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesLessThanOrEqualTo(Long value) {
            addCriterion("N_CACHED_PAGES <=", value, "nCachedPages");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesIn(List<Long> values) {
            addCriterion("N_CACHED_PAGES in", values, "nCachedPages");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesNotIn(List<Long> values) {
            addCriterion("N_CACHED_PAGES not in", values, "nCachedPages");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesBetween(Long value1, Long value2) {
            addCriterion("N_CACHED_PAGES between", value1, value2, "nCachedPages");
            return (Criteria) this;
        }

        public Criteria andNCachedPagesNotBetween(Long value1, Long value2) {
            addCriterion("N_CACHED_PAGES not between", value1, value2, "nCachedPages");
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