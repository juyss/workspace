package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class StSpatialReferenceSystemsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StSpatialReferenceSystemsExample() {
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

        public Criteria andSrsNameIsNull() {
            addCriterion("SRS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSrsNameIsNotNull() {
            addCriterion("SRS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSrsNameEqualTo(String value) {
            addCriterion("SRS_NAME =", value, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameNotEqualTo(String value) {
            addCriterion("SRS_NAME <>", value, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameGreaterThan(String value) {
            addCriterion("SRS_NAME >", value, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameGreaterThanOrEqualTo(String value) {
            addCriterion("SRS_NAME >=", value, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameLessThan(String value) {
            addCriterion("SRS_NAME <", value, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameLessThanOrEqualTo(String value) {
            addCriterion("SRS_NAME <=", value, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameLike(String value) {
            addCriterion("SRS_NAME like", value, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameNotLike(String value) {
            addCriterion("SRS_NAME not like", value, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameIn(List<String> values) {
            addCriterion("SRS_NAME in", values, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameNotIn(List<String> values) {
            addCriterion("SRS_NAME not in", values, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameBetween(String value1, String value2) {
            addCriterion("SRS_NAME between", value1, value2, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsNameNotBetween(String value1, String value2) {
            addCriterion("SRS_NAME not between", value1, value2, "srsName");
            return (Criteria) this;
        }

        public Criteria andSrsIdIsNull() {
            addCriterion("SRS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSrsIdIsNotNull() {
            addCriterion("SRS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSrsIdEqualTo(Integer value) {
            addCriterion("SRS_ID =", value, "srsId");
            return (Criteria) this;
        }

        public Criteria andSrsIdNotEqualTo(Integer value) {
            addCriterion("SRS_ID <>", value, "srsId");
            return (Criteria) this;
        }

        public Criteria andSrsIdGreaterThan(Integer value) {
            addCriterion("SRS_ID >", value, "srsId");
            return (Criteria) this;
        }

        public Criteria andSrsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SRS_ID >=", value, "srsId");
            return (Criteria) this;
        }

        public Criteria andSrsIdLessThan(Integer value) {
            addCriterion("SRS_ID <", value, "srsId");
            return (Criteria) this;
        }

        public Criteria andSrsIdLessThanOrEqualTo(Integer value) {
            addCriterion("SRS_ID <=", value, "srsId");
            return (Criteria) this;
        }

        public Criteria andSrsIdIn(List<Integer> values) {
            addCriterion("SRS_ID in", values, "srsId");
            return (Criteria) this;
        }

        public Criteria andSrsIdNotIn(List<Integer> values) {
            addCriterion("SRS_ID not in", values, "srsId");
            return (Criteria) this;
        }

        public Criteria andSrsIdBetween(Integer value1, Integer value2) {
            addCriterion("SRS_ID between", value1, value2, "srsId");
            return (Criteria) this;
        }

        public Criteria andSrsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SRS_ID not between", value1, value2, "srsId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIsNull() {
            addCriterion("ORGANIZATION is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIsNotNull() {
            addCriterion("ORGANIZATION is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationEqualTo(String value) {
            addCriterion("ORGANIZATION =", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotEqualTo(String value) {
            addCriterion("ORGANIZATION <>", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationGreaterThan(String value) {
            addCriterion("ORGANIZATION >", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationGreaterThanOrEqualTo(String value) {
            addCriterion("ORGANIZATION >=", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationLessThan(String value) {
            addCriterion("ORGANIZATION <", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationLessThanOrEqualTo(String value) {
            addCriterion("ORGANIZATION <=", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationLike(String value) {
            addCriterion("ORGANIZATION like", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotLike(String value) {
            addCriterion("ORGANIZATION not like", value, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationIn(List<String> values) {
            addCriterion("ORGANIZATION in", values, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotIn(List<String> values) {
            addCriterion("ORGANIZATION not in", values, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationBetween(String value1, String value2) {
            addCriterion("ORGANIZATION between", value1, value2, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationNotBetween(String value1, String value2) {
            addCriterion("ORGANIZATION not between", value1, value2, "organization");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdIsNull() {
            addCriterion("ORGANIZATION_COORDSYS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdIsNotNull() {
            addCriterion("ORGANIZATION_COORDSYS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdEqualTo(Integer value) {
            addCriterion("ORGANIZATION_COORDSYS_ID =", value, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdNotEqualTo(Integer value) {
            addCriterion("ORGANIZATION_COORDSYS_ID <>", value, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdGreaterThan(Integer value) {
            addCriterion("ORGANIZATION_COORDSYS_ID >", value, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORGANIZATION_COORDSYS_ID >=", value, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdLessThan(Integer value) {
            addCriterion("ORGANIZATION_COORDSYS_ID <", value, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdLessThanOrEqualTo(Integer value) {
            addCriterion("ORGANIZATION_COORDSYS_ID <=", value, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdIn(List<Integer> values) {
            addCriterion("ORGANIZATION_COORDSYS_ID in", values, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdNotIn(List<Integer> values) {
            addCriterion("ORGANIZATION_COORDSYS_ID not in", values, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdBetween(Integer value1, Integer value2) {
            addCriterion("ORGANIZATION_COORDSYS_ID between", value1, value2, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andOrganizationCoordsysIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ORGANIZATION_COORDSYS_ID not between", value1, value2, "organizationCoordsysId");
            return (Criteria) this;
        }

        public Criteria andDefinitionIsNull() {
            addCriterion("DEFINITION is null");
            return (Criteria) this;
        }

        public Criteria andDefinitionIsNotNull() {
            addCriterion("DEFINITION is not null");
            return (Criteria) this;
        }

        public Criteria andDefinitionEqualTo(String value) {
            addCriterion("DEFINITION =", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionNotEqualTo(String value) {
            addCriterion("DEFINITION <>", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionGreaterThan(String value) {
            addCriterion("DEFINITION >", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionGreaterThanOrEqualTo(String value) {
            addCriterion("DEFINITION >=", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionLessThan(String value) {
            addCriterion("DEFINITION <", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionLessThanOrEqualTo(String value) {
            addCriterion("DEFINITION <=", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionLike(String value) {
            addCriterion("DEFINITION like", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionNotLike(String value) {
            addCriterion("DEFINITION not like", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionIn(List<String> values) {
            addCriterion("DEFINITION in", values, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionNotIn(List<String> values) {
            addCriterion("DEFINITION not in", values, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionBetween(String value1, String value2) {
            addCriterion("DEFINITION between", value1, value2, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionNotBetween(String value1, String value2) {
            addCriterion("DEFINITION not between", value1, value2, "definition");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
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