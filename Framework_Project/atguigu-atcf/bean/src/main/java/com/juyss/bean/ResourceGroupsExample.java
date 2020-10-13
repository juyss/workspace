package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class ResourceGroupsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResourceGroupsExample() {
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

        public Criteria andResourceGroupNameIsNull() {
            addCriterion("RESOURCE_GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameIsNotNull() {
            addCriterion("RESOURCE_GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameEqualTo(String value) {
            addCriterion("RESOURCE_GROUP_NAME =", value, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameNotEqualTo(String value) {
            addCriterion("RESOURCE_GROUP_NAME <>", value, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameGreaterThan(String value) {
            addCriterion("RESOURCE_GROUP_NAME >", value, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("RESOURCE_GROUP_NAME >=", value, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameLessThan(String value) {
            addCriterion("RESOURCE_GROUP_NAME <", value, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameLessThanOrEqualTo(String value) {
            addCriterion("RESOURCE_GROUP_NAME <=", value, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameLike(String value) {
            addCriterion("RESOURCE_GROUP_NAME like", value, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameNotLike(String value) {
            addCriterion("RESOURCE_GROUP_NAME not like", value, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameIn(List<String> values) {
            addCriterion("RESOURCE_GROUP_NAME in", values, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameNotIn(List<String> values) {
            addCriterion("RESOURCE_GROUP_NAME not in", values, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameBetween(String value1, String value2) {
            addCriterion("RESOURCE_GROUP_NAME between", value1, value2, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupNameNotBetween(String value1, String value2) {
            addCriterion("RESOURCE_GROUP_NAME not between", value1, value2, "resourceGroupName");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeIsNull() {
            addCriterion("RESOURCE_GROUP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeIsNotNull() {
            addCriterion("RESOURCE_GROUP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeEqualTo(String value) {
            addCriterion("RESOURCE_GROUP_TYPE =", value, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeNotEqualTo(String value) {
            addCriterion("RESOURCE_GROUP_TYPE <>", value, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeGreaterThan(String value) {
            addCriterion("RESOURCE_GROUP_TYPE >", value, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RESOURCE_GROUP_TYPE >=", value, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeLessThan(String value) {
            addCriterion("RESOURCE_GROUP_TYPE <", value, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeLessThanOrEqualTo(String value) {
            addCriterion("RESOURCE_GROUP_TYPE <=", value, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeLike(String value) {
            addCriterion("RESOURCE_GROUP_TYPE like", value, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeNotLike(String value) {
            addCriterion("RESOURCE_GROUP_TYPE not like", value, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeIn(List<String> values) {
            addCriterion("RESOURCE_GROUP_TYPE in", values, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeNotIn(List<String> values) {
            addCriterion("RESOURCE_GROUP_TYPE not in", values, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeBetween(String value1, String value2) {
            addCriterion("RESOURCE_GROUP_TYPE between", value1, value2, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupTypeNotBetween(String value1, String value2) {
            addCriterion("RESOURCE_GROUP_TYPE not between", value1, value2, "resourceGroupType");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledIsNull() {
            addCriterion("RESOURCE_GROUP_ENABLED is null");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledIsNotNull() {
            addCriterion("RESOURCE_GROUP_ENABLED is not null");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledEqualTo(Boolean value) {
            addCriterion("RESOURCE_GROUP_ENABLED =", value, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledNotEqualTo(Boolean value) {
            addCriterion("RESOURCE_GROUP_ENABLED <>", value, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledGreaterThan(Boolean value) {
            addCriterion("RESOURCE_GROUP_ENABLED >", value, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("RESOURCE_GROUP_ENABLED >=", value, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledLessThan(Boolean value) {
            addCriterion("RESOURCE_GROUP_ENABLED <", value, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledLessThanOrEqualTo(Boolean value) {
            addCriterion("RESOURCE_GROUP_ENABLED <=", value, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledIn(List<Boolean> values) {
            addCriterion("RESOURCE_GROUP_ENABLED in", values, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledNotIn(List<Boolean> values) {
            addCriterion("RESOURCE_GROUP_ENABLED not in", values, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledBetween(Boolean value1, Boolean value2) {
            addCriterion("RESOURCE_GROUP_ENABLED between", value1, value2, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andResourceGroupEnabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("RESOURCE_GROUP_ENABLED not between", value1, value2, "resourceGroupEnabled");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityIsNull() {
            addCriterion("THREAD_PRIORITY is null");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityIsNotNull() {
            addCriterion("THREAD_PRIORITY is not null");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityEqualTo(Integer value) {
            addCriterion("THREAD_PRIORITY =", value, "threadPriority");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityNotEqualTo(Integer value) {
            addCriterion("THREAD_PRIORITY <>", value, "threadPriority");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityGreaterThan(Integer value) {
            addCriterion("THREAD_PRIORITY >", value, "threadPriority");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("THREAD_PRIORITY >=", value, "threadPriority");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityLessThan(Integer value) {
            addCriterion("THREAD_PRIORITY <", value, "threadPriority");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("THREAD_PRIORITY <=", value, "threadPriority");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityIn(List<Integer> values) {
            addCriterion("THREAD_PRIORITY in", values, "threadPriority");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityNotIn(List<Integer> values) {
            addCriterion("THREAD_PRIORITY not in", values, "threadPriority");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityBetween(Integer value1, Integer value2) {
            addCriterion("THREAD_PRIORITY between", value1, value2, "threadPriority");
            return (Criteria) this;
        }

        public Criteria andThreadPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("THREAD_PRIORITY not between", value1, value2, "threadPriority");
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