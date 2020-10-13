package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbTablesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbTablesExample() {
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

        public Criteria andFlagIsNull() {
            addCriterion("FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("FLAG =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("FLAG <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("FLAG >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("FLAG >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("FLAG <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("FLAG <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("FLAG in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("FLAG not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("FLAG between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("FLAG not between", value1, value2, "flag");
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

        public Criteria andNColsEqualTo(Integer value) {
            addCriterion("N_COLS =", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsNotEqualTo(Integer value) {
            addCriterion("N_COLS <>", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsGreaterThan(Integer value) {
            addCriterion("N_COLS >", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsGreaterThanOrEqualTo(Integer value) {
            addCriterion("N_COLS >=", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsLessThan(Integer value) {
            addCriterion("N_COLS <", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsLessThanOrEqualTo(Integer value) {
            addCriterion("N_COLS <=", value, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsIn(List<Integer> values) {
            addCriterion("N_COLS in", values, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsNotIn(List<Integer> values) {
            addCriterion("N_COLS not in", values, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsBetween(Integer value1, Integer value2) {
            addCriterion("N_COLS between", value1, value2, "nCols");
            return (Criteria) this;
        }

        public Criteria andNColsNotBetween(Integer value1, Integer value2) {
            addCriterion("N_COLS not between", value1, value2, "nCols");
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

        public Criteria andSpaceEqualTo(Long value) {
            addCriterion("SPACE =", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotEqualTo(Long value) {
            addCriterion("SPACE <>", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceGreaterThan(Long value) {
            addCriterion("SPACE >", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceGreaterThanOrEqualTo(Long value) {
            addCriterion("SPACE >=", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceLessThan(Long value) {
            addCriterion("SPACE <", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceLessThanOrEqualTo(Long value) {
            addCriterion("SPACE <=", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceIn(List<Long> values) {
            addCriterion("SPACE in", values, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotIn(List<Long> values) {
            addCriterion("SPACE not in", values, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceBetween(Long value1, Long value2) {
            addCriterion("SPACE between", value1, value2, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotBetween(Long value1, Long value2) {
            addCriterion("SPACE not between", value1, value2, "space");
            return (Criteria) this;
        }

        public Criteria andRowFormatIsNull() {
            addCriterion("ROW_FORMAT is null");
            return (Criteria) this;
        }

        public Criteria andRowFormatIsNotNull() {
            addCriterion("ROW_FORMAT is not null");
            return (Criteria) this;
        }

        public Criteria andRowFormatEqualTo(String value) {
            addCriterion("ROW_FORMAT =", value, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatNotEqualTo(String value) {
            addCriterion("ROW_FORMAT <>", value, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatGreaterThan(String value) {
            addCriterion("ROW_FORMAT >", value, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatGreaterThanOrEqualTo(String value) {
            addCriterion("ROW_FORMAT >=", value, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatLessThan(String value) {
            addCriterion("ROW_FORMAT <", value, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatLessThanOrEqualTo(String value) {
            addCriterion("ROW_FORMAT <=", value, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatLike(String value) {
            addCriterion("ROW_FORMAT like", value, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatNotLike(String value) {
            addCriterion("ROW_FORMAT not like", value, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatIn(List<String> values) {
            addCriterion("ROW_FORMAT in", values, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatNotIn(List<String> values) {
            addCriterion("ROW_FORMAT not in", values, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatBetween(String value1, String value2) {
            addCriterion("ROW_FORMAT between", value1, value2, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andRowFormatNotBetween(String value1, String value2) {
            addCriterion("ROW_FORMAT not between", value1, value2, "rowFormat");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeIsNull() {
            addCriterion("ZIP_PAGE_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeIsNotNull() {
            addCriterion("ZIP_PAGE_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeEqualTo(Integer value) {
            addCriterion("ZIP_PAGE_SIZE =", value, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeNotEqualTo(Integer value) {
            addCriterion("ZIP_PAGE_SIZE <>", value, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeGreaterThan(Integer value) {
            addCriterion("ZIP_PAGE_SIZE >", value, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ZIP_PAGE_SIZE >=", value, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeLessThan(Integer value) {
            addCriterion("ZIP_PAGE_SIZE <", value, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeLessThanOrEqualTo(Integer value) {
            addCriterion("ZIP_PAGE_SIZE <=", value, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeIn(List<Integer> values) {
            addCriterion("ZIP_PAGE_SIZE in", values, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeNotIn(List<Integer> values) {
            addCriterion("ZIP_PAGE_SIZE not in", values, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeBetween(Integer value1, Integer value2) {
            addCriterion("ZIP_PAGE_SIZE between", value1, value2, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andZipPageSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("ZIP_PAGE_SIZE not between", value1, value2, "zipPageSize");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIsNull() {
            addCriterion("SPACE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIsNotNull() {
            addCriterion("SPACE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeEqualTo(String value) {
            addCriterion("SPACE_TYPE =", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotEqualTo(String value) {
            addCriterion("SPACE_TYPE <>", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeGreaterThan(String value) {
            addCriterion("SPACE_TYPE >", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SPACE_TYPE >=", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeLessThan(String value) {
            addCriterion("SPACE_TYPE <", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeLessThanOrEqualTo(String value) {
            addCriterion("SPACE_TYPE <=", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeLike(String value) {
            addCriterion("SPACE_TYPE like", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotLike(String value) {
            addCriterion("SPACE_TYPE not like", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIn(List<String> values) {
            addCriterion("SPACE_TYPE in", values, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotIn(List<String> values) {
            addCriterion("SPACE_TYPE not in", values, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeBetween(String value1, String value2) {
            addCriterion("SPACE_TYPE between", value1, value2, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotBetween(String value1, String value2) {
            addCriterion("SPACE_TYPE not between", value1, value2, "spaceType");
            return (Criteria) this;
        }

        public Criteria andInstantColsIsNull() {
            addCriterion("INSTANT_COLS is null");
            return (Criteria) this;
        }

        public Criteria andInstantColsIsNotNull() {
            addCriterion("INSTANT_COLS is not null");
            return (Criteria) this;
        }

        public Criteria andInstantColsEqualTo(Integer value) {
            addCriterion("INSTANT_COLS =", value, "instantCols");
            return (Criteria) this;
        }

        public Criteria andInstantColsNotEqualTo(Integer value) {
            addCriterion("INSTANT_COLS <>", value, "instantCols");
            return (Criteria) this;
        }

        public Criteria andInstantColsGreaterThan(Integer value) {
            addCriterion("INSTANT_COLS >", value, "instantCols");
            return (Criteria) this;
        }

        public Criteria andInstantColsGreaterThanOrEqualTo(Integer value) {
            addCriterion("INSTANT_COLS >=", value, "instantCols");
            return (Criteria) this;
        }

        public Criteria andInstantColsLessThan(Integer value) {
            addCriterion("INSTANT_COLS <", value, "instantCols");
            return (Criteria) this;
        }

        public Criteria andInstantColsLessThanOrEqualTo(Integer value) {
            addCriterion("INSTANT_COLS <=", value, "instantCols");
            return (Criteria) this;
        }

        public Criteria andInstantColsIn(List<Integer> values) {
            addCriterion("INSTANT_COLS in", values, "instantCols");
            return (Criteria) this;
        }

        public Criteria andInstantColsNotIn(List<Integer> values) {
            addCriterion("INSTANT_COLS not in", values, "instantCols");
            return (Criteria) this;
        }

        public Criteria andInstantColsBetween(Integer value1, Integer value2) {
            addCriterion("INSTANT_COLS between", value1, value2, "instantCols");
            return (Criteria) this;
        }

        public Criteria andInstantColsNotBetween(Integer value1, Integer value2) {
            addCriterion("INSTANT_COLS not between", value1, value2, "instantCols");
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