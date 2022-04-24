package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class AdministrableRoleAuthorizationsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdministrableRoleAuthorizationsExample() {
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

        public Criteria andUserIsNull() {
            addCriterion("USER is null");
            return (Criteria) this;
        }

        public Criteria andUserIsNotNull() {
            addCriterion("USER is not null");
            return (Criteria) this;
        }

        public Criteria andUserEqualTo(String value) {
            addCriterion("USER =", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotEqualTo(String value) {
            addCriterion("USER <>", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThan(String value) {
            addCriterion("USER >", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThanOrEqualTo(String value) {
            addCriterion("USER >=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThan(String value) {
            addCriterion("USER <", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThanOrEqualTo(String value) {
            addCriterion("USER <=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLike(String value) {
            addCriterion("USER like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotLike(String value) {
            addCriterion("USER not like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserIn(List<String> values) {
            addCriterion("USER in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotIn(List<String> values) {
            addCriterion("USER not in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserBetween(String value1, String value2) {
            addCriterion("USER between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotBetween(String value1, String value2) {
            addCriterion("USER not between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andHostIsNull() {
            addCriterion("HOST is null");
            return (Criteria) this;
        }

        public Criteria andHostIsNotNull() {
            addCriterion("HOST is not null");
            return (Criteria) this;
        }

        public Criteria andHostEqualTo(String value) {
            addCriterion("HOST =", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotEqualTo(String value) {
            addCriterion("HOST <>", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThan(String value) {
            addCriterion("HOST >", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThanOrEqualTo(String value) {
            addCriterion("HOST >=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThan(String value) {
            addCriterion("HOST <", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThanOrEqualTo(String value) {
            addCriterion("HOST <=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLike(String value) {
            addCriterion("HOST like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotLike(String value) {
            addCriterion("HOST not like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostIn(List<String> values) {
            addCriterion("HOST in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotIn(List<String> values) {
            addCriterion("HOST not in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostBetween(String value1, String value2) {
            addCriterion("HOST between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotBetween(String value1, String value2) {
            addCriterion("HOST not between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andGranteeIsNull() {
            addCriterion("GRANTEE is null");
            return (Criteria) this;
        }

        public Criteria andGranteeIsNotNull() {
            addCriterion("GRANTEE is not null");
            return (Criteria) this;
        }

        public Criteria andGranteeEqualTo(String value) {
            addCriterion("GRANTEE =", value, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeNotEqualTo(String value) {
            addCriterion("GRANTEE <>", value, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeGreaterThan(String value) {
            addCriterion("GRANTEE >", value, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeGreaterThanOrEqualTo(String value) {
            addCriterion("GRANTEE >=", value, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeLessThan(String value) {
            addCriterion("GRANTEE <", value, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeLessThanOrEqualTo(String value) {
            addCriterion("GRANTEE <=", value, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeLike(String value) {
            addCriterion("GRANTEE like", value, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeNotLike(String value) {
            addCriterion("GRANTEE not like", value, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeIn(List<String> values) {
            addCriterion("GRANTEE in", values, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeNotIn(List<String> values) {
            addCriterion("GRANTEE not in", values, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeBetween(String value1, String value2) {
            addCriterion("GRANTEE between", value1, value2, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeNotBetween(String value1, String value2) {
            addCriterion("GRANTEE not between", value1, value2, "grantee");
            return (Criteria) this;
        }

        public Criteria andGranteeHostIsNull() {
            addCriterion("GRANTEE_HOST is null");
            return (Criteria) this;
        }

        public Criteria andGranteeHostIsNotNull() {
            addCriterion("GRANTEE_HOST is not null");
            return (Criteria) this;
        }

        public Criteria andGranteeHostEqualTo(String value) {
            addCriterion("GRANTEE_HOST =", value, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostNotEqualTo(String value) {
            addCriterion("GRANTEE_HOST <>", value, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostGreaterThan(String value) {
            addCriterion("GRANTEE_HOST >", value, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostGreaterThanOrEqualTo(String value) {
            addCriterion("GRANTEE_HOST >=", value, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostLessThan(String value) {
            addCriterion("GRANTEE_HOST <", value, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostLessThanOrEqualTo(String value) {
            addCriterion("GRANTEE_HOST <=", value, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostLike(String value) {
            addCriterion("GRANTEE_HOST like", value, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostNotLike(String value) {
            addCriterion("GRANTEE_HOST not like", value, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostIn(List<String> values) {
            addCriterion("GRANTEE_HOST in", values, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostNotIn(List<String> values) {
            addCriterion("GRANTEE_HOST not in", values, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostBetween(String value1, String value2) {
            addCriterion("GRANTEE_HOST between", value1, value2, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andGranteeHostNotBetween(String value1, String value2) {
            addCriterion("GRANTEE_HOST not between", value1, value2, "granteeHost");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("ROLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("ROLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("ROLE_NAME =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("ROLE_NAME <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("ROLE_NAME >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_NAME >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("ROLE_NAME <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("ROLE_NAME <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("ROLE_NAME like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("ROLE_NAME not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("ROLE_NAME in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("ROLE_NAME not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("ROLE_NAME between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("ROLE_NAME not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleHostIsNull() {
            addCriterion("ROLE_HOST is null");
            return (Criteria) this;
        }

        public Criteria andRoleHostIsNotNull() {
            addCriterion("ROLE_HOST is not null");
            return (Criteria) this;
        }

        public Criteria andRoleHostEqualTo(String value) {
            addCriterion("ROLE_HOST =", value, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostNotEqualTo(String value) {
            addCriterion("ROLE_HOST <>", value, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostGreaterThan(String value) {
            addCriterion("ROLE_HOST >", value, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_HOST >=", value, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostLessThan(String value) {
            addCriterion("ROLE_HOST <", value, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostLessThanOrEqualTo(String value) {
            addCriterion("ROLE_HOST <=", value, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostLike(String value) {
            addCriterion("ROLE_HOST like", value, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostNotLike(String value) {
            addCriterion("ROLE_HOST not like", value, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostIn(List<String> values) {
            addCriterion("ROLE_HOST in", values, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostNotIn(List<String> values) {
            addCriterion("ROLE_HOST not in", values, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostBetween(String value1, String value2) {
            addCriterion("ROLE_HOST between", value1, value2, "roleHost");
            return (Criteria) this;
        }

        public Criteria andRoleHostNotBetween(String value1, String value2) {
            addCriterion("ROLE_HOST not between", value1, value2, "roleHost");
            return (Criteria) this;
        }

        public Criteria andIsGrantableIsNull() {
            addCriterion("IS_GRANTABLE is null");
            return (Criteria) this;
        }

        public Criteria andIsGrantableIsNotNull() {
            addCriterion("IS_GRANTABLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsGrantableEqualTo(String value) {
            addCriterion("IS_GRANTABLE =", value, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableNotEqualTo(String value) {
            addCriterion("IS_GRANTABLE <>", value, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableGreaterThan(String value) {
            addCriterion("IS_GRANTABLE >", value, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableGreaterThanOrEqualTo(String value) {
            addCriterion("IS_GRANTABLE >=", value, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableLessThan(String value) {
            addCriterion("IS_GRANTABLE <", value, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableLessThanOrEqualTo(String value) {
            addCriterion("IS_GRANTABLE <=", value, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableLike(String value) {
            addCriterion("IS_GRANTABLE like", value, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableNotLike(String value) {
            addCriterion("IS_GRANTABLE not like", value, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableIn(List<String> values) {
            addCriterion("IS_GRANTABLE in", values, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableNotIn(List<String> values) {
            addCriterion("IS_GRANTABLE not in", values, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableBetween(String value1, String value2) {
            addCriterion("IS_GRANTABLE between", value1, value2, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsGrantableNotBetween(String value1, String value2) {
            addCriterion("IS_GRANTABLE not between", value1, value2, "isGrantable");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNull() {
            addCriterion("IS_DEFAULT is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("IS_DEFAULT is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(String value) {
            addCriterion("IS_DEFAULT =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(String value) {
            addCriterion("IS_DEFAULT <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(String value) {
            addCriterion("IS_DEFAULT >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DEFAULT >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(String value) {
            addCriterion("IS_DEFAULT <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(String value) {
            addCriterion("IS_DEFAULT <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLike(String value) {
            addCriterion("IS_DEFAULT like", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotLike(String value) {
            addCriterion("IS_DEFAULT not like", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<String> values) {
            addCriterion("IS_DEFAULT in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<String> values) {
            addCriterion("IS_DEFAULT not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(String value1, String value2) {
            addCriterion("IS_DEFAULT between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(String value1, String value2) {
            addCriterion("IS_DEFAULT not between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryIsNull() {
            addCriterion("IS_MANDATORY is null");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryIsNotNull() {
            addCriterion("IS_MANDATORY is not null");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryEqualTo(String value) {
            addCriterion("IS_MANDATORY =", value, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryNotEqualTo(String value) {
            addCriterion("IS_MANDATORY <>", value, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryGreaterThan(String value) {
            addCriterion("IS_MANDATORY >", value, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryGreaterThanOrEqualTo(String value) {
            addCriterion("IS_MANDATORY >=", value, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryLessThan(String value) {
            addCriterion("IS_MANDATORY <", value, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryLessThanOrEqualTo(String value) {
            addCriterion("IS_MANDATORY <=", value, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryLike(String value) {
            addCriterion("IS_MANDATORY like", value, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryNotLike(String value) {
            addCriterion("IS_MANDATORY not like", value, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryIn(List<String> values) {
            addCriterion("IS_MANDATORY in", values, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryNotIn(List<String> values) {
            addCriterion("IS_MANDATORY not in", values, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryBetween(String value1, String value2) {
            addCriterion("IS_MANDATORY between", value1, value2, "isMandatory");
            return (Criteria) this;
        }

        public Criteria andIsMandatoryNotBetween(String value1, String value2) {
            addCriterion("IS_MANDATORY not between", value1, value2, "isMandatory");
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