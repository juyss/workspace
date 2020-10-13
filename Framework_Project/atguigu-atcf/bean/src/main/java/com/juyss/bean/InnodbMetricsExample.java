package com.juyss.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InnodbMetricsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbMetricsExample() {
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

        public Criteria andSubsystemIsNull() {
            addCriterion("SUBSYSTEM is null");
            return (Criteria) this;
        }

        public Criteria andSubsystemIsNotNull() {
            addCriterion("SUBSYSTEM is not null");
            return (Criteria) this;
        }

        public Criteria andSubsystemEqualTo(String value) {
            addCriterion("SUBSYSTEM =", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemNotEqualTo(String value) {
            addCriterion("SUBSYSTEM <>", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemGreaterThan(String value) {
            addCriterion("SUBSYSTEM >", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSYSTEM >=", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemLessThan(String value) {
            addCriterion("SUBSYSTEM <", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemLessThanOrEqualTo(String value) {
            addCriterion("SUBSYSTEM <=", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemLike(String value) {
            addCriterion("SUBSYSTEM like", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemNotLike(String value) {
            addCriterion("SUBSYSTEM not like", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemIn(List<String> values) {
            addCriterion("SUBSYSTEM in", values, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemNotIn(List<String> values) {
            addCriterion("SUBSYSTEM not in", values, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemBetween(String value1, String value2) {
            addCriterion("SUBSYSTEM between", value1, value2, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemNotBetween(String value1, String value2) {
            addCriterion("SUBSYSTEM not between", value1, value2, "subsystem");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Long value) {
            addCriterion("COUNT =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Long value) {
            addCriterion("COUNT <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Long value) {
            addCriterion("COUNT >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Long value) {
            addCriterion("COUNT >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Long value) {
            addCriterion("COUNT <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Long value) {
            addCriterion("COUNT <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Long> values) {
            addCriterion("COUNT in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Long> values) {
            addCriterion("COUNT not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Long value1, Long value2) {
            addCriterion("COUNT between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Long value1, Long value2) {
            addCriterion("COUNT not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andMaxCountIsNull() {
            addCriterion("MAX_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andMaxCountIsNotNull() {
            addCriterion("MAX_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCountEqualTo(Long value) {
            addCriterion("MAX_COUNT =", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotEqualTo(Long value) {
            addCriterion("MAX_COUNT <>", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThan(Long value) {
            addCriterion("MAX_COUNT >", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThanOrEqualTo(Long value) {
            addCriterion("MAX_COUNT >=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThan(Long value) {
            addCriterion("MAX_COUNT <", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThanOrEqualTo(Long value) {
            addCriterion("MAX_COUNT <=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountIn(List<Long> values) {
            addCriterion("MAX_COUNT in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotIn(List<Long> values) {
            addCriterion("MAX_COUNT not in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountBetween(Long value1, Long value2) {
            addCriterion("MAX_COUNT between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotBetween(Long value1, Long value2) {
            addCriterion("MAX_COUNT not between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMinCountIsNull() {
            addCriterion("MIN_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andMinCountIsNotNull() {
            addCriterion("MIN_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andMinCountEqualTo(Long value) {
            addCriterion("MIN_COUNT =", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountNotEqualTo(Long value) {
            addCriterion("MIN_COUNT <>", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountGreaterThan(Long value) {
            addCriterion("MIN_COUNT >", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountGreaterThanOrEqualTo(Long value) {
            addCriterion("MIN_COUNT >=", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountLessThan(Long value) {
            addCriterion("MIN_COUNT <", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountLessThanOrEqualTo(Long value) {
            addCriterion("MIN_COUNT <=", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountIn(List<Long> values) {
            addCriterion("MIN_COUNT in", values, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountNotIn(List<Long> values) {
            addCriterion("MIN_COUNT not in", values, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountBetween(Long value1, Long value2) {
            addCriterion("MIN_COUNT between", value1, value2, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountNotBetween(Long value1, Long value2) {
            addCriterion("MIN_COUNT not between", value1, value2, "minCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountIsNull() {
            addCriterion("AVG_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAvgCountIsNotNull() {
            addCriterion("AVG_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCountEqualTo(Float value) {
            addCriterion("AVG_COUNT =", value, "avgCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountNotEqualTo(Float value) {
            addCriterion("AVG_COUNT <>", value, "avgCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountGreaterThan(Float value) {
            addCriterion("AVG_COUNT >", value, "avgCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountGreaterThanOrEqualTo(Float value) {
            addCriterion("AVG_COUNT >=", value, "avgCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountLessThan(Float value) {
            addCriterion("AVG_COUNT <", value, "avgCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountLessThanOrEqualTo(Float value) {
            addCriterion("AVG_COUNT <=", value, "avgCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountIn(List<Float> values) {
            addCriterion("AVG_COUNT in", values, "avgCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountNotIn(List<Float> values) {
            addCriterion("AVG_COUNT not in", values, "avgCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountBetween(Float value1, Float value2) {
            addCriterion("AVG_COUNT between", value1, value2, "avgCount");
            return (Criteria) this;
        }

        public Criteria andAvgCountNotBetween(Float value1, Float value2) {
            addCriterion("AVG_COUNT not between", value1, value2, "avgCount");
            return (Criteria) this;
        }

        public Criteria andCountResetIsNull() {
            addCriterion("COUNT_RESET is null");
            return (Criteria) this;
        }

        public Criteria andCountResetIsNotNull() {
            addCriterion("COUNT_RESET is not null");
            return (Criteria) this;
        }

        public Criteria andCountResetEqualTo(Long value) {
            addCriterion("COUNT_RESET =", value, "countReset");
            return (Criteria) this;
        }

        public Criteria andCountResetNotEqualTo(Long value) {
            addCriterion("COUNT_RESET <>", value, "countReset");
            return (Criteria) this;
        }

        public Criteria andCountResetGreaterThan(Long value) {
            addCriterion("COUNT_RESET >", value, "countReset");
            return (Criteria) this;
        }

        public Criteria andCountResetGreaterThanOrEqualTo(Long value) {
            addCriterion("COUNT_RESET >=", value, "countReset");
            return (Criteria) this;
        }

        public Criteria andCountResetLessThan(Long value) {
            addCriterion("COUNT_RESET <", value, "countReset");
            return (Criteria) this;
        }

        public Criteria andCountResetLessThanOrEqualTo(Long value) {
            addCriterion("COUNT_RESET <=", value, "countReset");
            return (Criteria) this;
        }

        public Criteria andCountResetIn(List<Long> values) {
            addCriterion("COUNT_RESET in", values, "countReset");
            return (Criteria) this;
        }

        public Criteria andCountResetNotIn(List<Long> values) {
            addCriterion("COUNT_RESET not in", values, "countReset");
            return (Criteria) this;
        }

        public Criteria andCountResetBetween(Long value1, Long value2) {
            addCriterion("COUNT_RESET between", value1, value2, "countReset");
            return (Criteria) this;
        }

        public Criteria andCountResetNotBetween(Long value1, Long value2) {
            addCriterion("COUNT_RESET not between", value1, value2, "countReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetIsNull() {
            addCriterion("MAX_COUNT_RESET is null");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetIsNotNull() {
            addCriterion("MAX_COUNT_RESET is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetEqualTo(Long value) {
            addCriterion("MAX_COUNT_RESET =", value, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetNotEqualTo(Long value) {
            addCriterion("MAX_COUNT_RESET <>", value, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetGreaterThan(Long value) {
            addCriterion("MAX_COUNT_RESET >", value, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetGreaterThanOrEqualTo(Long value) {
            addCriterion("MAX_COUNT_RESET >=", value, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetLessThan(Long value) {
            addCriterion("MAX_COUNT_RESET <", value, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetLessThanOrEqualTo(Long value) {
            addCriterion("MAX_COUNT_RESET <=", value, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetIn(List<Long> values) {
            addCriterion("MAX_COUNT_RESET in", values, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetNotIn(List<Long> values) {
            addCriterion("MAX_COUNT_RESET not in", values, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetBetween(Long value1, Long value2) {
            addCriterion("MAX_COUNT_RESET between", value1, value2, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMaxCountResetNotBetween(Long value1, Long value2) {
            addCriterion("MAX_COUNT_RESET not between", value1, value2, "maxCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetIsNull() {
            addCriterion("MIN_COUNT_RESET is null");
            return (Criteria) this;
        }

        public Criteria andMinCountResetIsNotNull() {
            addCriterion("MIN_COUNT_RESET is not null");
            return (Criteria) this;
        }

        public Criteria andMinCountResetEqualTo(Long value) {
            addCriterion("MIN_COUNT_RESET =", value, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetNotEqualTo(Long value) {
            addCriterion("MIN_COUNT_RESET <>", value, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetGreaterThan(Long value) {
            addCriterion("MIN_COUNT_RESET >", value, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetGreaterThanOrEqualTo(Long value) {
            addCriterion("MIN_COUNT_RESET >=", value, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetLessThan(Long value) {
            addCriterion("MIN_COUNT_RESET <", value, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetLessThanOrEqualTo(Long value) {
            addCriterion("MIN_COUNT_RESET <=", value, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetIn(List<Long> values) {
            addCriterion("MIN_COUNT_RESET in", values, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetNotIn(List<Long> values) {
            addCriterion("MIN_COUNT_RESET not in", values, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetBetween(Long value1, Long value2) {
            addCriterion("MIN_COUNT_RESET between", value1, value2, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andMinCountResetNotBetween(Long value1, Long value2) {
            addCriterion("MIN_COUNT_RESET not between", value1, value2, "minCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetIsNull() {
            addCriterion("AVG_COUNT_RESET is null");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetIsNotNull() {
            addCriterion("AVG_COUNT_RESET is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetEqualTo(Float value) {
            addCriterion("AVG_COUNT_RESET =", value, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetNotEqualTo(Float value) {
            addCriterion("AVG_COUNT_RESET <>", value, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetGreaterThan(Float value) {
            addCriterion("AVG_COUNT_RESET >", value, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetGreaterThanOrEqualTo(Float value) {
            addCriterion("AVG_COUNT_RESET >=", value, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetLessThan(Float value) {
            addCriterion("AVG_COUNT_RESET <", value, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetLessThanOrEqualTo(Float value) {
            addCriterion("AVG_COUNT_RESET <=", value, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetIn(List<Float> values) {
            addCriterion("AVG_COUNT_RESET in", values, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetNotIn(List<Float> values) {
            addCriterion("AVG_COUNT_RESET not in", values, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetBetween(Float value1, Float value2) {
            addCriterion("AVG_COUNT_RESET between", value1, value2, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andAvgCountResetNotBetween(Float value1, Float value2) {
            addCriterion("AVG_COUNT_RESET not between", value1, value2, "avgCountReset");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledIsNull() {
            addCriterion("TIME_ENABLED is null");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledIsNotNull() {
            addCriterion("TIME_ENABLED is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledEqualTo(Date value) {
            addCriterion("TIME_ENABLED =", value, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledNotEqualTo(Date value) {
            addCriterion("TIME_ENABLED <>", value, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledGreaterThan(Date value) {
            addCriterion("TIME_ENABLED >", value, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledGreaterThanOrEqualTo(Date value) {
            addCriterion("TIME_ENABLED >=", value, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledLessThan(Date value) {
            addCriterion("TIME_ENABLED <", value, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledLessThanOrEqualTo(Date value) {
            addCriterion("TIME_ENABLED <=", value, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledIn(List<Date> values) {
            addCriterion("TIME_ENABLED in", values, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledNotIn(List<Date> values) {
            addCriterion("TIME_ENABLED not in", values, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledBetween(Date value1, Date value2) {
            addCriterion("TIME_ENABLED between", value1, value2, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeEnabledNotBetween(Date value1, Date value2) {
            addCriterion("TIME_ENABLED not between", value1, value2, "timeEnabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledIsNull() {
            addCriterion("TIME_DISABLED is null");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledIsNotNull() {
            addCriterion("TIME_DISABLED is not null");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledEqualTo(Date value) {
            addCriterion("TIME_DISABLED =", value, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledNotEqualTo(Date value) {
            addCriterion("TIME_DISABLED <>", value, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledGreaterThan(Date value) {
            addCriterion("TIME_DISABLED >", value, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledGreaterThanOrEqualTo(Date value) {
            addCriterion("TIME_DISABLED >=", value, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledLessThan(Date value) {
            addCriterion("TIME_DISABLED <", value, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledLessThanOrEqualTo(Date value) {
            addCriterion("TIME_DISABLED <=", value, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledIn(List<Date> values) {
            addCriterion("TIME_DISABLED in", values, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledNotIn(List<Date> values) {
            addCriterion("TIME_DISABLED not in", values, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledBetween(Date value1, Date value2) {
            addCriterion("TIME_DISABLED between", value1, value2, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeDisabledNotBetween(Date value1, Date value2) {
            addCriterion("TIME_DISABLED not between", value1, value2, "timeDisabled");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedIsNull() {
            addCriterion("TIME_ELAPSED is null");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedIsNotNull() {
            addCriterion("TIME_ELAPSED is not null");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedEqualTo(Long value) {
            addCriterion("TIME_ELAPSED =", value, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedNotEqualTo(Long value) {
            addCriterion("TIME_ELAPSED <>", value, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedGreaterThan(Long value) {
            addCriterion("TIME_ELAPSED >", value, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedGreaterThanOrEqualTo(Long value) {
            addCriterion("TIME_ELAPSED >=", value, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedLessThan(Long value) {
            addCriterion("TIME_ELAPSED <", value, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedLessThanOrEqualTo(Long value) {
            addCriterion("TIME_ELAPSED <=", value, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedIn(List<Long> values) {
            addCriterion("TIME_ELAPSED in", values, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedNotIn(List<Long> values) {
            addCriterion("TIME_ELAPSED not in", values, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedBetween(Long value1, Long value2) {
            addCriterion("TIME_ELAPSED between", value1, value2, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeElapsedNotBetween(Long value1, Long value2) {
            addCriterion("TIME_ELAPSED not between", value1, value2, "timeElapsed");
            return (Criteria) this;
        }

        public Criteria andTimeResetIsNull() {
            addCriterion("TIME_RESET is null");
            return (Criteria) this;
        }

        public Criteria andTimeResetIsNotNull() {
            addCriterion("TIME_RESET is not null");
            return (Criteria) this;
        }

        public Criteria andTimeResetEqualTo(Date value) {
            addCriterion("TIME_RESET =", value, "timeReset");
            return (Criteria) this;
        }

        public Criteria andTimeResetNotEqualTo(Date value) {
            addCriterion("TIME_RESET <>", value, "timeReset");
            return (Criteria) this;
        }

        public Criteria andTimeResetGreaterThan(Date value) {
            addCriterion("TIME_RESET >", value, "timeReset");
            return (Criteria) this;
        }

        public Criteria andTimeResetGreaterThanOrEqualTo(Date value) {
            addCriterion("TIME_RESET >=", value, "timeReset");
            return (Criteria) this;
        }

        public Criteria andTimeResetLessThan(Date value) {
            addCriterion("TIME_RESET <", value, "timeReset");
            return (Criteria) this;
        }

        public Criteria andTimeResetLessThanOrEqualTo(Date value) {
            addCriterion("TIME_RESET <=", value, "timeReset");
            return (Criteria) this;
        }

        public Criteria andTimeResetIn(List<Date> values) {
            addCriterion("TIME_RESET in", values, "timeReset");
            return (Criteria) this;
        }

        public Criteria andTimeResetNotIn(List<Date> values) {
            addCriterion("TIME_RESET not in", values, "timeReset");
            return (Criteria) this;
        }

        public Criteria andTimeResetBetween(Date value1, Date value2) {
            addCriterion("TIME_RESET between", value1, value2, "timeReset");
            return (Criteria) this;
        }

        public Criteria andTimeResetNotBetween(Date value1, Date value2) {
            addCriterion("TIME_RESET not between", value1, value2, "timeReset");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("COMMENT =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("COMMENT <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("COMMENT >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("COMMENT >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("COMMENT <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("COMMENT <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("COMMENT like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("COMMENT not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("COMMENT in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("COMMENT not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("COMMENT between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("COMMENT not between", value1, value2, "comment");
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