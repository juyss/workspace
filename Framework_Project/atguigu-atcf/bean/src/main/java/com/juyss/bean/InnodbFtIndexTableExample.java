package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbFtIndexTableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbFtIndexTableExample() {
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

        public Criteria andWordIsNull() {
            addCriterion("WORD is null");
            return (Criteria) this;
        }

        public Criteria andWordIsNotNull() {
            addCriterion("WORD is not null");
            return (Criteria) this;
        }

        public Criteria andWordEqualTo(String value) {
            addCriterion("WORD =", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotEqualTo(String value) {
            addCriterion("WORD <>", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThan(String value) {
            addCriterion("WORD >", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThanOrEqualTo(String value) {
            addCriterion("WORD >=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThan(String value) {
            addCriterion("WORD <", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThanOrEqualTo(String value) {
            addCriterion("WORD <=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLike(String value) {
            addCriterion("WORD like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotLike(String value) {
            addCriterion("WORD not like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordIn(List<String> values) {
            addCriterion("WORD in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotIn(List<String> values) {
            addCriterion("WORD not in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordBetween(String value1, String value2) {
            addCriterion("WORD between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotBetween(String value1, String value2) {
            addCriterion("WORD not between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdIsNull() {
            addCriterion("FIRST_DOC_ID is null");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdIsNotNull() {
            addCriterion("FIRST_DOC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdEqualTo(Long value) {
            addCriterion("FIRST_DOC_ID =", value, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdNotEqualTo(Long value) {
            addCriterion("FIRST_DOC_ID <>", value, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdGreaterThan(Long value) {
            addCriterion("FIRST_DOC_ID >", value, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdGreaterThanOrEqualTo(Long value) {
            addCriterion("FIRST_DOC_ID >=", value, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdLessThan(Long value) {
            addCriterion("FIRST_DOC_ID <", value, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdLessThanOrEqualTo(Long value) {
            addCriterion("FIRST_DOC_ID <=", value, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdIn(List<Long> values) {
            addCriterion("FIRST_DOC_ID in", values, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdNotIn(List<Long> values) {
            addCriterion("FIRST_DOC_ID not in", values, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdBetween(Long value1, Long value2) {
            addCriterion("FIRST_DOC_ID between", value1, value2, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andFirstDocIdNotBetween(Long value1, Long value2) {
            addCriterion("FIRST_DOC_ID not between", value1, value2, "firstDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdIsNull() {
            addCriterion("LAST_DOC_ID is null");
            return (Criteria) this;
        }

        public Criteria andLastDocIdIsNotNull() {
            addCriterion("LAST_DOC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLastDocIdEqualTo(Long value) {
            addCriterion("LAST_DOC_ID =", value, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdNotEqualTo(Long value) {
            addCriterion("LAST_DOC_ID <>", value, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdGreaterThan(Long value) {
            addCriterion("LAST_DOC_ID >", value, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdGreaterThanOrEqualTo(Long value) {
            addCriterion("LAST_DOC_ID >=", value, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdLessThan(Long value) {
            addCriterion("LAST_DOC_ID <", value, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdLessThanOrEqualTo(Long value) {
            addCriterion("LAST_DOC_ID <=", value, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdIn(List<Long> values) {
            addCriterion("LAST_DOC_ID in", values, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdNotIn(List<Long> values) {
            addCriterion("LAST_DOC_ID not in", values, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdBetween(Long value1, Long value2) {
            addCriterion("LAST_DOC_ID between", value1, value2, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andLastDocIdNotBetween(Long value1, Long value2) {
            addCriterion("LAST_DOC_ID not between", value1, value2, "lastDocId");
            return (Criteria) this;
        }

        public Criteria andDocCountIsNull() {
            addCriterion("DOC_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDocCountIsNotNull() {
            addCriterion("DOC_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDocCountEqualTo(Long value) {
            addCriterion("DOC_COUNT =", value, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocCountNotEqualTo(Long value) {
            addCriterion("DOC_COUNT <>", value, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocCountGreaterThan(Long value) {
            addCriterion("DOC_COUNT >", value, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocCountGreaterThanOrEqualTo(Long value) {
            addCriterion("DOC_COUNT >=", value, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocCountLessThan(Long value) {
            addCriterion("DOC_COUNT <", value, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocCountLessThanOrEqualTo(Long value) {
            addCriterion("DOC_COUNT <=", value, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocCountIn(List<Long> values) {
            addCriterion("DOC_COUNT in", values, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocCountNotIn(List<Long> values) {
            addCriterion("DOC_COUNT not in", values, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocCountBetween(Long value1, Long value2) {
            addCriterion("DOC_COUNT between", value1, value2, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocCountNotBetween(Long value1, Long value2) {
            addCriterion("DOC_COUNT not between", value1, value2, "docCount");
            return (Criteria) this;
        }

        public Criteria andDocIdIsNull() {
            addCriterion("DOC_ID is null");
            return (Criteria) this;
        }

        public Criteria andDocIdIsNotNull() {
            addCriterion("DOC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDocIdEqualTo(Long value) {
            addCriterion("DOC_ID =", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdNotEqualTo(Long value) {
            addCriterion("DOC_ID <>", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdGreaterThan(Long value) {
            addCriterion("DOC_ID >", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DOC_ID >=", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdLessThan(Long value) {
            addCriterion("DOC_ID <", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdLessThanOrEqualTo(Long value) {
            addCriterion("DOC_ID <=", value, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdIn(List<Long> values) {
            addCriterion("DOC_ID in", values, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdNotIn(List<Long> values) {
            addCriterion("DOC_ID not in", values, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdBetween(Long value1, Long value2) {
            addCriterion("DOC_ID between", value1, value2, "docId");
            return (Criteria) this;
        }

        public Criteria andDocIdNotBetween(Long value1, Long value2) {
            addCriterion("DOC_ID not between", value1, value2, "docId");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("POSITION is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(Long value) {
            addCriterion("POSITION =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(Long value) {
            addCriterion("POSITION <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(Long value) {
            addCriterion("POSITION >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(Long value) {
            addCriterion("POSITION >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(Long value) {
            addCriterion("POSITION <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(Long value) {
            addCriterion("POSITION <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<Long> values) {
            addCriterion("POSITION in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<Long> values) {
            addCriterion("POSITION not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(Long value1, Long value2) {
            addCriterion("POSITION between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(Long value1, Long value2) {
            addCriterion("POSITION not between", value1, value2, "position");
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