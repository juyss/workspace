package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbIndexesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbIndexesExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andNFieldsIsNull() {
            addCriterion("N_FIELDS is null");
            return (Criteria) this;
        }

        public Criteria andNFieldsIsNotNull() {
            addCriterion("N_FIELDS is not null");
            return (Criteria) this;
        }

        public Criteria andNFieldsEqualTo(Integer value) {
            addCriterion("N_FIELDS =", value, "nFields");
            return (Criteria) this;
        }

        public Criteria andNFieldsNotEqualTo(Integer value) {
            addCriterion("N_FIELDS <>", value, "nFields");
            return (Criteria) this;
        }

        public Criteria andNFieldsGreaterThan(Integer value) {
            addCriterion("N_FIELDS >", value, "nFields");
            return (Criteria) this;
        }

        public Criteria andNFieldsGreaterThanOrEqualTo(Integer value) {
            addCriterion("N_FIELDS >=", value, "nFields");
            return (Criteria) this;
        }

        public Criteria andNFieldsLessThan(Integer value) {
            addCriterion("N_FIELDS <", value, "nFields");
            return (Criteria) this;
        }

        public Criteria andNFieldsLessThanOrEqualTo(Integer value) {
            addCriterion("N_FIELDS <=", value, "nFields");
            return (Criteria) this;
        }

        public Criteria andNFieldsIn(List<Integer> values) {
            addCriterion("N_FIELDS in", values, "nFields");
            return (Criteria) this;
        }

        public Criteria andNFieldsNotIn(List<Integer> values) {
            addCriterion("N_FIELDS not in", values, "nFields");
            return (Criteria) this;
        }

        public Criteria andNFieldsBetween(Integer value1, Integer value2) {
            addCriterion("N_FIELDS between", value1, value2, "nFields");
            return (Criteria) this;
        }

        public Criteria andNFieldsNotBetween(Integer value1, Integer value2) {
            addCriterion("N_FIELDS not between", value1, value2, "nFields");
            return (Criteria) this;
        }

        public Criteria andPageNoIsNull() {
            addCriterion("PAGE_NO is null");
            return (Criteria) this;
        }

        public Criteria andPageNoIsNotNull() {
            addCriterion("PAGE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPageNoEqualTo(Integer value) {
            addCriterion("PAGE_NO =", value, "pageNo");
            return (Criteria) this;
        }

        public Criteria andPageNoNotEqualTo(Integer value) {
            addCriterion("PAGE_NO <>", value, "pageNo");
            return (Criteria) this;
        }

        public Criteria andPageNoGreaterThan(Integer value) {
            addCriterion("PAGE_NO >", value, "pageNo");
            return (Criteria) this;
        }

        public Criteria andPageNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAGE_NO >=", value, "pageNo");
            return (Criteria) this;
        }

        public Criteria andPageNoLessThan(Integer value) {
            addCriterion("PAGE_NO <", value, "pageNo");
            return (Criteria) this;
        }

        public Criteria andPageNoLessThanOrEqualTo(Integer value) {
            addCriterion("PAGE_NO <=", value, "pageNo");
            return (Criteria) this;
        }

        public Criteria andPageNoIn(List<Integer> values) {
            addCriterion("PAGE_NO in", values, "pageNo");
            return (Criteria) this;
        }

        public Criteria andPageNoNotIn(List<Integer> values) {
            addCriterion("PAGE_NO not in", values, "pageNo");
            return (Criteria) this;
        }

        public Criteria andPageNoBetween(Integer value1, Integer value2) {
            addCriterion("PAGE_NO between", value1, value2, "pageNo");
            return (Criteria) this;
        }

        public Criteria andPageNoNotBetween(Integer value1, Integer value2) {
            addCriterion("PAGE_NO not between", value1, value2, "pageNo");
            return (Criteria) this;
        }

        public Criteria andSpaceIsNull() {
            addCriterion("SPACE is null");
            return (Criteria) this;
        }

        public Criteria andSpaceIsNotNull() {
            addCriterion("SPACE is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceEqualTo(Integer value) {
            addCriterion("SPACE =", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotEqualTo(Integer value) {
            addCriterion("SPACE <>", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceGreaterThan(Integer value) {
            addCriterion("SPACE >", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPACE >=", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceLessThan(Integer value) {
            addCriterion("SPACE <", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceLessThanOrEqualTo(Integer value) {
            addCriterion("SPACE <=", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceIn(List<Integer> values) {
            addCriterion("SPACE in", values, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotIn(List<Integer> values) {
            addCriterion("SPACE not in", values, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceBetween(Integer value1, Integer value2) {
            addCriterion("SPACE between", value1, value2, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotBetween(Integer value1, Integer value2) {
            addCriterion("SPACE not between", value1, value2, "space");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdIsNull() {
            addCriterion("MERGE_THRESHOLD is null");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdIsNotNull() {
            addCriterion("MERGE_THRESHOLD is not null");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdEqualTo(Integer value) {
            addCriterion("MERGE_THRESHOLD =", value, "mergeThreshold");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdNotEqualTo(Integer value) {
            addCriterion("MERGE_THRESHOLD <>", value, "mergeThreshold");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdGreaterThan(Integer value) {
            addCriterion("MERGE_THRESHOLD >", value, "mergeThreshold");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdGreaterThanOrEqualTo(Integer value) {
            addCriterion("MERGE_THRESHOLD >=", value, "mergeThreshold");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdLessThan(Integer value) {
            addCriterion("MERGE_THRESHOLD <", value, "mergeThreshold");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdLessThanOrEqualTo(Integer value) {
            addCriterion("MERGE_THRESHOLD <=", value, "mergeThreshold");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdIn(List<Integer> values) {
            addCriterion("MERGE_THRESHOLD in", values, "mergeThreshold");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdNotIn(List<Integer> values) {
            addCriterion("MERGE_THRESHOLD not in", values, "mergeThreshold");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdBetween(Integer value1, Integer value2) {
            addCriterion("MERGE_THRESHOLD between", value1, value2, "mergeThreshold");
            return (Criteria) this;
        }

        public Criteria andMergeThresholdNotBetween(Integer value1, Integer value2) {
            addCriterion("MERGE_THRESHOLD not between", value1, value2, "mergeThreshold");
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