package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class RoleRoutineGrantsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoleRoutineGrantsExample() {
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

        public Criteria andGrantorIsNull() {
            addCriterion("GRANTOR is null");
            return (Criteria) this;
        }

        public Criteria andGrantorIsNotNull() {
            addCriterion("GRANTOR is not null");
            return (Criteria) this;
        }

        public Criteria andGrantorEqualTo(String value) {
            addCriterion("GRANTOR =", value, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorNotEqualTo(String value) {
            addCriterion("GRANTOR <>", value, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorGreaterThan(String value) {
            addCriterion("GRANTOR >", value, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorGreaterThanOrEqualTo(String value) {
            addCriterion("GRANTOR >=", value, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorLessThan(String value) {
            addCriterion("GRANTOR <", value, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorLessThanOrEqualTo(String value) {
            addCriterion("GRANTOR <=", value, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorLike(String value) {
            addCriterion("GRANTOR like", value, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorNotLike(String value) {
            addCriterion("GRANTOR not like", value, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorIn(List<String> values) {
            addCriterion("GRANTOR in", values, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorNotIn(List<String> values) {
            addCriterion("GRANTOR not in", values, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorBetween(String value1, String value2) {
            addCriterion("GRANTOR between", value1, value2, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorNotBetween(String value1, String value2) {
            addCriterion("GRANTOR not between", value1, value2, "grantor");
            return (Criteria) this;
        }

        public Criteria andGrantorHostIsNull() {
            addCriterion("GRANTOR_HOST is null");
            return (Criteria) this;
        }

        public Criteria andGrantorHostIsNotNull() {
            addCriterion("GRANTOR_HOST is not null");
            return (Criteria) this;
        }

        public Criteria andGrantorHostEqualTo(String value) {
            addCriterion("GRANTOR_HOST =", value, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostNotEqualTo(String value) {
            addCriterion("GRANTOR_HOST <>", value, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostGreaterThan(String value) {
            addCriterion("GRANTOR_HOST >", value, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostGreaterThanOrEqualTo(String value) {
            addCriterion("GRANTOR_HOST >=", value, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostLessThan(String value) {
            addCriterion("GRANTOR_HOST <", value, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostLessThanOrEqualTo(String value) {
            addCriterion("GRANTOR_HOST <=", value, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostLike(String value) {
            addCriterion("GRANTOR_HOST like", value, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostNotLike(String value) {
            addCriterion("GRANTOR_HOST not like", value, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostIn(List<String> values) {
            addCriterion("GRANTOR_HOST in", values, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostNotIn(List<String> values) {
            addCriterion("GRANTOR_HOST not in", values, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostBetween(String value1, String value2) {
            addCriterion("GRANTOR_HOST between", value1, value2, "grantorHost");
            return (Criteria) this;
        }

        public Criteria andGrantorHostNotBetween(String value1, String value2) {
            addCriterion("GRANTOR_HOST not between", value1, value2, "grantorHost");
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

        public Criteria andSpecificCatalogIsNull() {
            addCriterion("SPECIFIC_CATALOG is null");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogIsNotNull() {
            addCriterion("SPECIFIC_CATALOG is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogEqualTo(String value) {
            addCriterion("SPECIFIC_CATALOG =", value, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogNotEqualTo(String value) {
            addCriterion("SPECIFIC_CATALOG <>", value, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogGreaterThan(String value) {
            addCriterion("SPECIFIC_CATALOG >", value, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIFIC_CATALOG >=", value, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogLessThan(String value) {
            addCriterion("SPECIFIC_CATALOG <", value, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogLessThanOrEqualTo(String value) {
            addCriterion("SPECIFIC_CATALOG <=", value, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogLike(String value) {
            addCriterion("SPECIFIC_CATALOG like", value, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogNotLike(String value) {
            addCriterion("SPECIFIC_CATALOG not like", value, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogIn(List<String> values) {
            addCriterion("SPECIFIC_CATALOG in", values, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogNotIn(List<String> values) {
            addCriterion("SPECIFIC_CATALOG not in", values, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogBetween(String value1, String value2) {
            addCriterion("SPECIFIC_CATALOG between", value1, value2, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificCatalogNotBetween(String value1, String value2) {
            addCriterion("SPECIFIC_CATALOG not between", value1, value2, "specificCatalog");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaIsNull() {
            addCriterion("SPECIFIC_SCHEMA is null");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaIsNotNull() {
            addCriterion("SPECIFIC_SCHEMA is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaEqualTo(String value) {
            addCriterion("SPECIFIC_SCHEMA =", value, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaNotEqualTo(String value) {
            addCriterion("SPECIFIC_SCHEMA <>", value, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaGreaterThan(String value) {
            addCriterion("SPECIFIC_SCHEMA >", value, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIFIC_SCHEMA >=", value, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaLessThan(String value) {
            addCriterion("SPECIFIC_SCHEMA <", value, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaLessThanOrEqualTo(String value) {
            addCriterion("SPECIFIC_SCHEMA <=", value, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaLike(String value) {
            addCriterion("SPECIFIC_SCHEMA like", value, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaNotLike(String value) {
            addCriterion("SPECIFIC_SCHEMA not like", value, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaIn(List<String> values) {
            addCriterion("SPECIFIC_SCHEMA in", values, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaNotIn(List<String> values) {
            addCriterion("SPECIFIC_SCHEMA not in", values, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaBetween(String value1, String value2) {
            addCriterion("SPECIFIC_SCHEMA between", value1, value2, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificSchemaNotBetween(String value1, String value2) {
            addCriterion("SPECIFIC_SCHEMA not between", value1, value2, "specificSchema");
            return (Criteria) this;
        }

        public Criteria andSpecificNameIsNull() {
            addCriterion("SPECIFIC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpecificNameIsNotNull() {
            addCriterion("SPECIFIC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificNameEqualTo(String value) {
            addCriterion("SPECIFIC_NAME =", value, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameNotEqualTo(String value) {
            addCriterion("SPECIFIC_NAME <>", value, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameGreaterThan(String value) {
            addCriterion("SPECIFIC_NAME >", value, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIFIC_NAME >=", value, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameLessThan(String value) {
            addCriterion("SPECIFIC_NAME <", value, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameLessThanOrEqualTo(String value) {
            addCriterion("SPECIFIC_NAME <=", value, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameLike(String value) {
            addCriterion("SPECIFIC_NAME like", value, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameNotLike(String value) {
            addCriterion("SPECIFIC_NAME not like", value, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameIn(List<String> values) {
            addCriterion("SPECIFIC_NAME in", values, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameNotIn(List<String> values) {
            addCriterion("SPECIFIC_NAME not in", values, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameBetween(String value1, String value2) {
            addCriterion("SPECIFIC_NAME between", value1, value2, "specificName");
            return (Criteria) this;
        }

        public Criteria andSpecificNameNotBetween(String value1, String value2) {
            addCriterion("SPECIFIC_NAME not between", value1, value2, "specificName");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogIsNull() {
            addCriterion("ROUTINE_CATALOG is null");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogIsNotNull() {
            addCriterion("ROUTINE_CATALOG is not null");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogEqualTo(String value) {
            addCriterion("ROUTINE_CATALOG =", value, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogNotEqualTo(String value) {
            addCriterion("ROUTINE_CATALOG <>", value, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogGreaterThan(String value) {
            addCriterion("ROUTINE_CATALOG >", value, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("ROUTINE_CATALOG >=", value, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogLessThan(String value) {
            addCriterion("ROUTINE_CATALOG <", value, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogLessThanOrEqualTo(String value) {
            addCriterion("ROUTINE_CATALOG <=", value, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogLike(String value) {
            addCriterion("ROUTINE_CATALOG like", value, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogNotLike(String value) {
            addCriterion("ROUTINE_CATALOG not like", value, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogIn(List<String> values) {
            addCriterion("ROUTINE_CATALOG in", values, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogNotIn(List<String> values) {
            addCriterion("ROUTINE_CATALOG not in", values, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogBetween(String value1, String value2) {
            addCriterion("ROUTINE_CATALOG between", value1, value2, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineCatalogNotBetween(String value1, String value2) {
            addCriterion("ROUTINE_CATALOG not between", value1, value2, "routineCatalog");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaIsNull() {
            addCriterion("ROUTINE_SCHEMA is null");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaIsNotNull() {
            addCriterion("ROUTINE_SCHEMA is not null");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaEqualTo(String value) {
            addCriterion("ROUTINE_SCHEMA =", value, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaNotEqualTo(String value) {
            addCriterion("ROUTINE_SCHEMA <>", value, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaGreaterThan(String value) {
            addCriterion("ROUTINE_SCHEMA >", value, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaGreaterThanOrEqualTo(String value) {
            addCriterion("ROUTINE_SCHEMA >=", value, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaLessThan(String value) {
            addCriterion("ROUTINE_SCHEMA <", value, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaLessThanOrEqualTo(String value) {
            addCriterion("ROUTINE_SCHEMA <=", value, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaLike(String value) {
            addCriterion("ROUTINE_SCHEMA like", value, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaNotLike(String value) {
            addCriterion("ROUTINE_SCHEMA not like", value, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaIn(List<String> values) {
            addCriterion("ROUTINE_SCHEMA in", values, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaNotIn(List<String> values) {
            addCriterion("ROUTINE_SCHEMA not in", values, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaBetween(String value1, String value2) {
            addCriterion("ROUTINE_SCHEMA between", value1, value2, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineSchemaNotBetween(String value1, String value2) {
            addCriterion("ROUTINE_SCHEMA not between", value1, value2, "routineSchema");
            return (Criteria) this;
        }

        public Criteria andRoutineNameIsNull() {
            addCriterion("ROUTINE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRoutineNameIsNotNull() {
            addCriterion("ROUTINE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRoutineNameEqualTo(String value) {
            addCriterion("ROUTINE_NAME =", value, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameNotEqualTo(String value) {
            addCriterion("ROUTINE_NAME <>", value, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameGreaterThan(String value) {
            addCriterion("ROUTINE_NAME >", value, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROUTINE_NAME >=", value, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameLessThan(String value) {
            addCriterion("ROUTINE_NAME <", value, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameLessThanOrEqualTo(String value) {
            addCriterion("ROUTINE_NAME <=", value, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameLike(String value) {
            addCriterion("ROUTINE_NAME like", value, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameNotLike(String value) {
            addCriterion("ROUTINE_NAME not like", value, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameIn(List<String> values) {
            addCriterion("ROUTINE_NAME in", values, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameNotIn(List<String> values) {
            addCriterion("ROUTINE_NAME not in", values, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameBetween(String value1, String value2) {
            addCriterion("ROUTINE_NAME between", value1, value2, "routineName");
            return (Criteria) this;
        }

        public Criteria andRoutineNameNotBetween(String value1, String value2) {
            addCriterion("ROUTINE_NAME not between", value1, value2, "routineName");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeIsNull() {
            addCriterion("PRIVILEGE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeIsNotNull() {
            addCriterion("PRIVILEGE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeEqualTo(String value) {
            addCriterion("PRIVILEGE_TYPE =", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeNotEqualTo(String value) {
            addCriterion("PRIVILEGE_TYPE <>", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeGreaterThan(String value) {
            addCriterion("PRIVILEGE_TYPE >", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRIVILEGE_TYPE >=", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeLessThan(String value) {
            addCriterion("PRIVILEGE_TYPE <", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeLessThanOrEqualTo(String value) {
            addCriterion("PRIVILEGE_TYPE <=", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeLike(String value) {
            addCriterion("PRIVILEGE_TYPE like", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeNotLike(String value) {
            addCriterion("PRIVILEGE_TYPE not like", value, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeIn(List<String> values) {
            addCriterion("PRIVILEGE_TYPE in", values, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeNotIn(List<String> values) {
            addCriterion("PRIVILEGE_TYPE not in", values, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeBetween(String value1, String value2) {
            addCriterion("PRIVILEGE_TYPE between", value1, value2, "privilegeType");
            return (Criteria) this;
        }

        public Criteria andPrivilegeTypeNotBetween(String value1, String value2) {
            addCriterion("PRIVILEGE_TYPE not between", value1, value2, "privilegeType");
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