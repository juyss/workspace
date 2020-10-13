package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbTablestatsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbTablestatsExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedIsNull() {
            addCriterion("STATS_INITIALIZED is null");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedIsNotNull() {
            addCriterion("STATS_INITIALIZED is not null");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedEqualTo(String value) {
            addCriterion("STATS_INITIALIZED =", value, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedNotEqualTo(String value) {
            addCriterion("STATS_INITIALIZED <>", value, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedGreaterThan(String value) {
            addCriterion("STATS_INITIALIZED >", value, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedGreaterThanOrEqualTo(String value) {
            addCriterion("STATS_INITIALIZED >=", value, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedLessThan(String value) {
            addCriterion("STATS_INITIALIZED <", value, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedLessThanOrEqualTo(String value) {
            addCriterion("STATS_INITIALIZED <=", value, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedLike(String value) {
            addCriterion("STATS_INITIALIZED like", value, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedNotLike(String value) {
            addCriterion("STATS_INITIALIZED not like", value, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedIn(List<String> values) {
            addCriterion("STATS_INITIALIZED in", values, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedNotIn(List<String> values) {
            addCriterion("STATS_INITIALIZED not in", values, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedBetween(String value1, String value2) {
            addCriterion("STATS_INITIALIZED between", value1, value2, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andStatsInitializedNotBetween(String value1, String value2) {
            addCriterion("STATS_INITIALIZED not between", value1, value2, "statsInitialized");
            return (Criteria) this;
        }

        public Criteria andNumRowsIsNull() {
            addCriterion("NUM_ROWS is null");
            return (Criteria) this;
        }

        public Criteria andNumRowsIsNotNull() {
            addCriterion("NUM_ROWS is not null");
            return (Criteria) this;
        }

        public Criteria andNumRowsEqualTo(Long value) {
            addCriterion("NUM_ROWS =", value, "numRows");
            return (Criteria) this;
        }

        public Criteria andNumRowsNotEqualTo(Long value) {
            addCriterion("NUM_ROWS <>", value, "numRows");
            return (Criteria) this;
        }

        public Criteria andNumRowsGreaterThan(Long value) {
            addCriterion("NUM_ROWS >", value, "numRows");
            return (Criteria) this;
        }

        public Criteria andNumRowsGreaterThanOrEqualTo(Long value) {
            addCriterion("NUM_ROWS >=", value, "numRows");
            return (Criteria) this;
        }

        public Criteria andNumRowsLessThan(Long value) {
            addCriterion("NUM_ROWS <", value, "numRows");
            return (Criteria) this;
        }

        public Criteria andNumRowsLessThanOrEqualTo(Long value) {
            addCriterion("NUM_ROWS <=", value, "numRows");
            return (Criteria) this;
        }

        public Criteria andNumRowsIn(List<Long> values) {
            addCriterion("NUM_ROWS in", values, "numRows");
            return (Criteria) this;
        }

        public Criteria andNumRowsNotIn(List<Long> values) {
            addCriterion("NUM_ROWS not in", values, "numRows");
            return (Criteria) this;
        }

        public Criteria andNumRowsBetween(Long value1, Long value2) {
            addCriterion("NUM_ROWS between", value1, value2, "numRows");
            return (Criteria) this;
        }

        public Criteria andNumRowsNotBetween(Long value1, Long value2) {
            addCriterion("NUM_ROWS not between", value1, value2, "numRows");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeIsNull() {
            addCriterion("CLUST_INDEX_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeIsNotNull() {
            addCriterion("CLUST_INDEX_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeEqualTo(Long value) {
            addCriterion("CLUST_INDEX_SIZE =", value, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeNotEqualTo(Long value) {
            addCriterion("CLUST_INDEX_SIZE <>", value, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeGreaterThan(Long value) {
            addCriterion("CLUST_INDEX_SIZE >", value, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("CLUST_INDEX_SIZE >=", value, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeLessThan(Long value) {
            addCriterion("CLUST_INDEX_SIZE <", value, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeLessThanOrEqualTo(Long value) {
            addCriterion("CLUST_INDEX_SIZE <=", value, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeIn(List<Long> values) {
            addCriterion("CLUST_INDEX_SIZE in", values, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeNotIn(List<Long> values) {
            addCriterion("CLUST_INDEX_SIZE not in", values, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeBetween(Long value1, Long value2) {
            addCriterion("CLUST_INDEX_SIZE between", value1, value2, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andClustIndexSizeNotBetween(Long value1, Long value2) {
            addCriterion("CLUST_INDEX_SIZE not between", value1, value2, "clustIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeIsNull() {
            addCriterion("OTHER_INDEX_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeIsNotNull() {
            addCriterion("OTHER_INDEX_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeEqualTo(Long value) {
            addCriterion("OTHER_INDEX_SIZE =", value, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeNotEqualTo(Long value) {
            addCriterion("OTHER_INDEX_SIZE <>", value, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeGreaterThan(Long value) {
            addCriterion("OTHER_INDEX_SIZE >", value, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("OTHER_INDEX_SIZE >=", value, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeLessThan(Long value) {
            addCriterion("OTHER_INDEX_SIZE <", value, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeLessThanOrEqualTo(Long value) {
            addCriterion("OTHER_INDEX_SIZE <=", value, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeIn(List<Long> values) {
            addCriterion("OTHER_INDEX_SIZE in", values, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeNotIn(List<Long> values) {
            addCriterion("OTHER_INDEX_SIZE not in", values, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeBetween(Long value1, Long value2) {
            addCriterion("OTHER_INDEX_SIZE between", value1, value2, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andOtherIndexSizeNotBetween(Long value1, Long value2) {
            addCriterion("OTHER_INDEX_SIZE not between", value1, value2, "otherIndexSize");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterIsNull() {
            addCriterion("MODIFIED_COUNTER is null");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterIsNotNull() {
            addCriterion("MODIFIED_COUNTER is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterEqualTo(Long value) {
            addCriterion("MODIFIED_COUNTER =", value, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterNotEqualTo(Long value) {
            addCriterion("MODIFIED_COUNTER <>", value, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterGreaterThan(Long value) {
            addCriterion("MODIFIED_COUNTER >", value, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterGreaterThanOrEqualTo(Long value) {
            addCriterion("MODIFIED_COUNTER >=", value, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterLessThan(Long value) {
            addCriterion("MODIFIED_COUNTER <", value, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterLessThanOrEqualTo(Long value) {
            addCriterion("MODIFIED_COUNTER <=", value, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterIn(List<Long> values) {
            addCriterion("MODIFIED_COUNTER in", values, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterNotIn(List<Long> values) {
            addCriterion("MODIFIED_COUNTER not in", values, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterBetween(Long value1, Long value2) {
            addCriterion("MODIFIED_COUNTER between", value1, value2, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andModifiedCounterNotBetween(Long value1, Long value2) {
            addCriterion("MODIFIED_COUNTER not between", value1, value2, "modifiedCounter");
            return (Criteria) this;
        }

        public Criteria andAutoincIsNull() {
            addCriterion("AUTOINC is null");
            return (Criteria) this;
        }

        public Criteria andAutoincIsNotNull() {
            addCriterion("AUTOINC is not null");
            return (Criteria) this;
        }

        public Criteria andAutoincEqualTo(Long value) {
            addCriterion("AUTOINC =", value, "autoinc");
            return (Criteria) this;
        }

        public Criteria andAutoincNotEqualTo(Long value) {
            addCriterion("AUTOINC <>", value, "autoinc");
            return (Criteria) this;
        }

        public Criteria andAutoincGreaterThan(Long value) {
            addCriterion("AUTOINC >", value, "autoinc");
            return (Criteria) this;
        }

        public Criteria andAutoincGreaterThanOrEqualTo(Long value) {
            addCriterion("AUTOINC >=", value, "autoinc");
            return (Criteria) this;
        }

        public Criteria andAutoincLessThan(Long value) {
            addCriterion("AUTOINC <", value, "autoinc");
            return (Criteria) this;
        }

        public Criteria andAutoincLessThanOrEqualTo(Long value) {
            addCriterion("AUTOINC <=", value, "autoinc");
            return (Criteria) this;
        }

        public Criteria andAutoincIn(List<Long> values) {
            addCriterion("AUTOINC in", values, "autoinc");
            return (Criteria) this;
        }

        public Criteria andAutoincNotIn(List<Long> values) {
            addCriterion("AUTOINC not in", values, "autoinc");
            return (Criteria) this;
        }

        public Criteria andAutoincBetween(Long value1, Long value2) {
            addCriterion("AUTOINC between", value1, value2, "autoinc");
            return (Criteria) this;
        }

        public Criteria andAutoincNotBetween(Long value1, Long value2) {
            addCriterion("AUTOINC not between", value1, value2, "autoinc");
            return (Criteria) this;
        }

        public Criteria andRefCountIsNull() {
            addCriterion("REF_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andRefCountIsNotNull() {
            addCriterion("REF_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRefCountEqualTo(Integer value) {
            addCriterion("REF_COUNT =", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountNotEqualTo(Integer value) {
            addCriterion("REF_COUNT <>", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountGreaterThan(Integer value) {
            addCriterion("REF_COUNT >", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("REF_COUNT >=", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountLessThan(Integer value) {
            addCriterion("REF_COUNT <", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountLessThanOrEqualTo(Integer value) {
            addCriterion("REF_COUNT <=", value, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountIn(List<Integer> values) {
            addCriterion("REF_COUNT in", values, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountNotIn(List<Integer> values) {
            addCriterion("REF_COUNT not in", values, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountBetween(Integer value1, Integer value2) {
            addCriterion("REF_COUNT between", value1, value2, "refCount");
            return (Criteria) this;
        }

        public Criteria andRefCountNotBetween(Integer value1, Integer value2) {
            addCriterion("REF_COUNT not between", value1, value2, "refCount");
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