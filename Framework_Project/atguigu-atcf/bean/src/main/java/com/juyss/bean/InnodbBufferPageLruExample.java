package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbBufferPageLruExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbBufferPageLruExample() {
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

        public Criteria andPoolIdIsNull() {
            addCriterion("POOL_ID is null");
            return (Criteria) this;
        }

        public Criteria andPoolIdIsNotNull() {
            addCriterion("POOL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPoolIdEqualTo(Long value) {
            addCriterion("POOL_ID =", value, "poolId");
            return (Criteria) this;
        }

        public Criteria andPoolIdNotEqualTo(Long value) {
            addCriterion("POOL_ID <>", value, "poolId");
            return (Criteria) this;
        }

        public Criteria andPoolIdGreaterThan(Long value) {
            addCriterion("POOL_ID >", value, "poolId");
            return (Criteria) this;
        }

        public Criteria andPoolIdGreaterThanOrEqualTo(Long value) {
            addCriterion("POOL_ID >=", value, "poolId");
            return (Criteria) this;
        }

        public Criteria andPoolIdLessThan(Long value) {
            addCriterion("POOL_ID <", value, "poolId");
            return (Criteria) this;
        }

        public Criteria andPoolIdLessThanOrEqualTo(Long value) {
            addCriterion("POOL_ID <=", value, "poolId");
            return (Criteria) this;
        }

        public Criteria andPoolIdIn(List<Long> values) {
            addCriterion("POOL_ID in", values, "poolId");
            return (Criteria) this;
        }

        public Criteria andPoolIdNotIn(List<Long> values) {
            addCriterion("POOL_ID not in", values, "poolId");
            return (Criteria) this;
        }

        public Criteria andPoolIdBetween(Long value1, Long value2) {
            addCriterion("POOL_ID between", value1, value2, "poolId");
            return (Criteria) this;
        }

        public Criteria andPoolIdNotBetween(Long value1, Long value2) {
            addCriterion("POOL_ID not between", value1, value2, "poolId");
            return (Criteria) this;
        }

        public Criteria andLruPositionIsNull() {
            addCriterion("LRU_POSITION is null");
            return (Criteria) this;
        }

        public Criteria andLruPositionIsNotNull() {
            addCriterion("LRU_POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andLruPositionEqualTo(Long value) {
            addCriterion("LRU_POSITION =", value, "lruPosition");
            return (Criteria) this;
        }

        public Criteria andLruPositionNotEqualTo(Long value) {
            addCriterion("LRU_POSITION <>", value, "lruPosition");
            return (Criteria) this;
        }

        public Criteria andLruPositionGreaterThan(Long value) {
            addCriterion("LRU_POSITION >", value, "lruPosition");
            return (Criteria) this;
        }

        public Criteria andLruPositionGreaterThanOrEqualTo(Long value) {
            addCriterion("LRU_POSITION >=", value, "lruPosition");
            return (Criteria) this;
        }

        public Criteria andLruPositionLessThan(Long value) {
            addCriterion("LRU_POSITION <", value, "lruPosition");
            return (Criteria) this;
        }

        public Criteria andLruPositionLessThanOrEqualTo(Long value) {
            addCriterion("LRU_POSITION <=", value, "lruPosition");
            return (Criteria) this;
        }

        public Criteria andLruPositionIn(List<Long> values) {
            addCriterion("LRU_POSITION in", values, "lruPosition");
            return (Criteria) this;
        }

        public Criteria andLruPositionNotIn(List<Long> values) {
            addCriterion("LRU_POSITION not in", values, "lruPosition");
            return (Criteria) this;
        }

        public Criteria andLruPositionBetween(Long value1, Long value2) {
            addCriterion("LRU_POSITION between", value1, value2, "lruPosition");
            return (Criteria) this;
        }

        public Criteria andLruPositionNotBetween(Long value1, Long value2) {
            addCriterion("LRU_POSITION not between", value1, value2, "lruPosition");
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

        public Criteria andPageNumberIsNull() {
            addCriterion("PAGE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andPageNumberIsNotNull() {
            addCriterion("PAGE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andPageNumberEqualTo(Long value) {
            addCriterion("PAGE_NUMBER =", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberNotEqualTo(Long value) {
            addCriterion("PAGE_NUMBER <>", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberGreaterThan(Long value) {
            addCriterion("PAGE_NUMBER >", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("PAGE_NUMBER >=", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberLessThan(Long value) {
            addCriterion("PAGE_NUMBER <", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberLessThanOrEqualTo(Long value) {
            addCriterion("PAGE_NUMBER <=", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberIn(List<Long> values) {
            addCriterion("PAGE_NUMBER in", values, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberNotIn(List<Long> values) {
            addCriterion("PAGE_NUMBER not in", values, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberBetween(Long value1, Long value2) {
            addCriterion("PAGE_NUMBER between", value1, value2, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberNotBetween(Long value1, Long value2) {
            addCriterion("PAGE_NUMBER not between", value1, value2, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageTypeIsNull() {
            addCriterion("PAGE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPageTypeIsNotNull() {
            addCriterion("PAGE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPageTypeEqualTo(String value) {
            addCriterion("PAGE_TYPE =", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotEqualTo(String value) {
            addCriterion("PAGE_TYPE <>", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeGreaterThan(String value) {
            addCriterion("PAGE_TYPE >", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAGE_TYPE >=", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLessThan(String value) {
            addCriterion("PAGE_TYPE <", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLessThanOrEqualTo(String value) {
            addCriterion("PAGE_TYPE <=", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLike(String value) {
            addCriterion("PAGE_TYPE like", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotLike(String value) {
            addCriterion("PAGE_TYPE not like", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeIn(List<String> values) {
            addCriterion("PAGE_TYPE in", values, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotIn(List<String> values) {
            addCriterion("PAGE_TYPE not in", values, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeBetween(String value1, String value2) {
            addCriterion("PAGE_TYPE between", value1, value2, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotBetween(String value1, String value2) {
            addCriterion("PAGE_TYPE not between", value1, value2, "pageType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeIsNull() {
            addCriterion("FLUSH_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFlushTypeIsNotNull() {
            addCriterion("FLUSH_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFlushTypeEqualTo(Long value) {
            addCriterion("FLUSH_TYPE =", value, "flushType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeNotEqualTo(Long value) {
            addCriterion("FLUSH_TYPE <>", value, "flushType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeGreaterThan(Long value) {
            addCriterion("FLUSH_TYPE >", value, "flushType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("FLUSH_TYPE >=", value, "flushType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeLessThan(Long value) {
            addCriterion("FLUSH_TYPE <", value, "flushType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeLessThanOrEqualTo(Long value) {
            addCriterion("FLUSH_TYPE <=", value, "flushType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeIn(List<Long> values) {
            addCriterion("FLUSH_TYPE in", values, "flushType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeNotIn(List<Long> values) {
            addCriterion("FLUSH_TYPE not in", values, "flushType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeBetween(Long value1, Long value2) {
            addCriterion("FLUSH_TYPE between", value1, value2, "flushType");
            return (Criteria) this;
        }

        public Criteria andFlushTypeNotBetween(Long value1, Long value2) {
            addCriterion("FLUSH_TYPE not between", value1, value2, "flushType");
            return (Criteria) this;
        }

        public Criteria andFixCountIsNull() {
            addCriterion("FIX_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andFixCountIsNotNull() {
            addCriterion("FIX_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFixCountEqualTo(Long value) {
            addCriterion("FIX_COUNT =", value, "fixCount");
            return (Criteria) this;
        }

        public Criteria andFixCountNotEqualTo(Long value) {
            addCriterion("FIX_COUNT <>", value, "fixCount");
            return (Criteria) this;
        }

        public Criteria andFixCountGreaterThan(Long value) {
            addCriterion("FIX_COUNT >", value, "fixCount");
            return (Criteria) this;
        }

        public Criteria andFixCountGreaterThanOrEqualTo(Long value) {
            addCriterion("FIX_COUNT >=", value, "fixCount");
            return (Criteria) this;
        }

        public Criteria andFixCountLessThan(Long value) {
            addCriterion("FIX_COUNT <", value, "fixCount");
            return (Criteria) this;
        }

        public Criteria andFixCountLessThanOrEqualTo(Long value) {
            addCriterion("FIX_COUNT <=", value, "fixCount");
            return (Criteria) this;
        }

        public Criteria andFixCountIn(List<Long> values) {
            addCriterion("FIX_COUNT in", values, "fixCount");
            return (Criteria) this;
        }

        public Criteria andFixCountNotIn(List<Long> values) {
            addCriterion("FIX_COUNT not in", values, "fixCount");
            return (Criteria) this;
        }

        public Criteria andFixCountBetween(Long value1, Long value2) {
            addCriterion("FIX_COUNT between", value1, value2, "fixCount");
            return (Criteria) this;
        }

        public Criteria andFixCountNotBetween(Long value1, Long value2) {
            addCriterion("FIX_COUNT not between", value1, value2, "fixCount");
            return (Criteria) this;
        }

        public Criteria andIsHashedIsNull() {
            addCriterion("IS_HASHED is null");
            return (Criteria) this;
        }

        public Criteria andIsHashedIsNotNull() {
            addCriterion("IS_HASHED is not null");
            return (Criteria) this;
        }

        public Criteria andIsHashedEqualTo(String value) {
            addCriterion("IS_HASHED =", value, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedNotEqualTo(String value) {
            addCriterion("IS_HASHED <>", value, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedGreaterThan(String value) {
            addCriterion("IS_HASHED >", value, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_HASHED >=", value, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedLessThan(String value) {
            addCriterion("IS_HASHED <", value, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedLessThanOrEqualTo(String value) {
            addCriterion("IS_HASHED <=", value, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedLike(String value) {
            addCriterion("IS_HASHED like", value, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedNotLike(String value) {
            addCriterion("IS_HASHED not like", value, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedIn(List<String> values) {
            addCriterion("IS_HASHED in", values, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedNotIn(List<String> values) {
            addCriterion("IS_HASHED not in", values, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedBetween(String value1, String value2) {
            addCriterion("IS_HASHED between", value1, value2, "isHashed");
            return (Criteria) this;
        }

        public Criteria andIsHashedNotBetween(String value1, String value2) {
            addCriterion("IS_HASHED not between", value1, value2, "isHashed");
            return (Criteria) this;
        }

        public Criteria andNewestModificationIsNull() {
            addCriterion("NEWEST_MODIFICATION is null");
            return (Criteria) this;
        }

        public Criteria andNewestModificationIsNotNull() {
            addCriterion("NEWEST_MODIFICATION is not null");
            return (Criteria) this;
        }

        public Criteria andNewestModificationEqualTo(Long value) {
            addCriterion("NEWEST_MODIFICATION =", value, "newestModification");
            return (Criteria) this;
        }

        public Criteria andNewestModificationNotEqualTo(Long value) {
            addCriterion("NEWEST_MODIFICATION <>", value, "newestModification");
            return (Criteria) this;
        }

        public Criteria andNewestModificationGreaterThan(Long value) {
            addCriterion("NEWEST_MODIFICATION >", value, "newestModification");
            return (Criteria) this;
        }

        public Criteria andNewestModificationGreaterThanOrEqualTo(Long value) {
            addCriterion("NEWEST_MODIFICATION >=", value, "newestModification");
            return (Criteria) this;
        }

        public Criteria andNewestModificationLessThan(Long value) {
            addCriterion("NEWEST_MODIFICATION <", value, "newestModification");
            return (Criteria) this;
        }

        public Criteria andNewestModificationLessThanOrEqualTo(Long value) {
            addCriterion("NEWEST_MODIFICATION <=", value, "newestModification");
            return (Criteria) this;
        }

        public Criteria andNewestModificationIn(List<Long> values) {
            addCriterion("NEWEST_MODIFICATION in", values, "newestModification");
            return (Criteria) this;
        }

        public Criteria andNewestModificationNotIn(List<Long> values) {
            addCriterion("NEWEST_MODIFICATION not in", values, "newestModification");
            return (Criteria) this;
        }

        public Criteria andNewestModificationBetween(Long value1, Long value2) {
            addCriterion("NEWEST_MODIFICATION between", value1, value2, "newestModification");
            return (Criteria) this;
        }

        public Criteria andNewestModificationNotBetween(Long value1, Long value2) {
            addCriterion("NEWEST_MODIFICATION not between", value1, value2, "newestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationIsNull() {
            addCriterion("OLDEST_MODIFICATION is null");
            return (Criteria) this;
        }

        public Criteria andOldestModificationIsNotNull() {
            addCriterion("OLDEST_MODIFICATION is not null");
            return (Criteria) this;
        }

        public Criteria andOldestModificationEqualTo(Long value) {
            addCriterion("OLDEST_MODIFICATION =", value, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationNotEqualTo(Long value) {
            addCriterion("OLDEST_MODIFICATION <>", value, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationGreaterThan(Long value) {
            addCriterion("OLDEST_MODIFICATION >", value, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationGreaterThanOrEqualTo(Long value) {
            addCriterion("OLDEST_MODIFICATION >=", value, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationLessThan(Long value) {
            addCriterion("OLDEST_MODIFICATION <", value, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationLessThanOrEqualTo(Long value) {
            addCriterion("OLDEST_MODIFICATION <=", value, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationIn(List<Long> values) {
            addCriterion("OLDEST_MODIFICATION in", values, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationNotIn(List<Long> values) {
            addCriterion("OLDEST_MODIFICATION not in", values, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationBetween(Long value1, Long value2) {
            addCriterion("OLDEST_MODIFICATION between", value1, value2, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andOldestModificationNotBetween(Long value1, Long value2) {
            addCriterion("OLDEST_MODIFICATION not between", value1, value2, "oldestModification");
            return (Criteria) this;
        }

        public Criteria andAccessTimeIsNull() {
            addCriterion("ACCESS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAccessTimeIsNotNull() {
            addCriterion("ACCESS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTimeEqualTo(Long value) {
            addCriterion("ACCESS_TIME =", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeNotEqualTo(Long value) {
            addCriterion("ACCESS_TIME <>", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeGreaterThan(Long value) {
            addCriterion("ACCESS_TIME >", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("ACCESS_TIME >=", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeLessThan(Long value) {
            addCriterion("ACCESS_TIME <", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeLessThanOrEqualTo(Long value) {
            addCriterion("ACCESS_TIME <=", value, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeIn(List<Long> values) {
            addCriterion("ACCESS_TIME in", values, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeNotIn(List<Long> values) {
            addCriterion("ACCESS_TIME not in", values, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeBetween(Long value1, Long value2) {
            addCriterion("ACCESS_TIME between", value1, value2, "accessTime");
            return (Criteria) this;
        }

        public Criteria andAccessTimeNotBetween(Long value1, Long value2) {
            addCriterion("ACCESS_TIME not between", value1, value2, "accessTime");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("TABLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("TABLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("TABLE_NAME =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("TABLE_NAME <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("TABLE_NAME >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("TABLE_NAME <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("TABLE_NAME <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("TABLE_NAME like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("TABLE_NAME not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("TABLE_NAME in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("TABLE_NAME not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("TABLE_NAME between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("TABLE_NAME not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andIndexNameIsNull() {
            addCriterion("INDEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIndexNameIsNotNull() {
            addCriterion("INDEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIndexNameEqualTo(String value) {
            addCriterion("INDEX_NAME =", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameNotEqualTo(String value) {
            addCriterion("INDEX_NAME <>", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameGreaterThan(String value) {
            addCriterion("INDEX_NAME >", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameGreaterThanOrEqualTo(String value) {
            addCriterion("INDEX_NAME >=", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameLessThan(String value) {
            addCriterion("INDEX_NAME <", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameLessThanOrEqualTo(String value) {
            addCriterion("INDEX_NAME <=", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameLike(String value) {
            addCriterion("INDEX_NAME like", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameNotLike(String value) {
            addCriterion("INDEX_NAME not like", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameIn(List<String> values) {
            addCriterion("INDEX_NAME in", values, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameNotIn(List<String> values) {
            addCriterion("INDEX_NAME not in", values, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameBetween(String value1, String value2) {
            addCriterion("INDEX_NAME between", value1, value2, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameNotBetween(String value1, String value2) {
            addCriterion("INDEX_NAME not between", value1, value2, "indexName");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsIsNull() {
            addCriterion("NUMBER_RECORDS is null");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsIsNotNull() {
            addCriterion("NUMBER_RECORDS is not null");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsEqualTo(Long value) {
            addCriterion("NUMBER_RECORDS =", value, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsNotEqualTo(Long value) {
            addCriterion("NUMBER_RECORDS <>", value, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsGreaterThan(Long value) {
            addCriterion("NUMBER_RECORDS >", value, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsGreaterThanOrEqualTo(Long value) {
            addCriterion("NUMBER_RECORDS >=", value, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsLessThan(Long value) {
            addCriterion("NUMBER_RECORDS <", value, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsLessThanOrEqualTo(Long value) {
            addCriterion("NUMBER_RECORDS <=", value, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsIn(List<Long> values) {
            addCriterion("NUMBER_RECORDS in", values, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsNotIn(List<Long> values) {
            addCriterion("NUMBER_RECORDS not in", values, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsBetween(Long value1, Long value2) {
            addCriterion("NUMBER_RECORDS between", value1, value2, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andNumberRecordsNotBetween(Long value1, Long value2) {
            addCriterion("NUMBER_RECORDS not between", value1, value2, "numberRecords");
            return (Criteria) this;
        }

        public Criteria andDataSizeIsNull() {
            addCriterion("DATA_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andDataSizeIsNotNull() {
            addCriterion("DATA_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andDataSizeEqualTo(Long value) {
            addCriterion("DATA_SIZE =", value, "dataSize");
            return (Criteria) this;
        }

        public Criteria andDataSizeNotEqualTo(Long value) {
            addCriterion("DATA_SIZE <>", value, "dataSize");
            return (Criteria) this;
        }

        public Criteria andDataSizeGreaterThan(Long value) {
            addCriterion("DATA_SIZE >", value, "dataSize");
            return (Criteria) this;
        }

        public Criteria andDataSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("DATA_SIZE >=", value, "dataSize");
            return (Criteria) this;
        }

        public Criteria andDataSizeLessThan(Long value) {
            addCriterion("DATA_SIZE <", value, "dataSize");
            return (Criteria) this;
        }

        public Criteria andDataSizeLessThanOrEqualTo(Long value) {
            addCriterion("DATA_SIZE <=", value, "dataSize");
            return (Criteria) this;
        }

        public Criteria andDataSizeIn(List<Long> values) {
            addCriterion("DATA_SIZE in", values, "dataSize");
            return (Criteria) this;
        }

        public Criteria andDataSizeNotIn(List<Long> values) {
            addCriterion("DATA_SIZE not in", values, "dataSize");
            return (Criteria) this;
        }

        public Criteria andDataSizeBetween(Long value1, Long value2) {
            addCriterion("DATA_SIZE between", value1, value2, "dataSize");
            return (Criteria) this;
        }

        public Criteria andDataSizeNotBetween(Long value1, Long value2) {
            addCriterion("DATA_SIZE not between", value1, value2, "dataSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeIsNull() {
            addCriterion("COMPRESSED_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeIsNotNull() {
            addCriterion("COMPRESSED_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeEqualTo(Long value) {
            addCriterion("COMPRESSED_SIZE =", value, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeNotEqualTo(Long value) {
            addCriterion("COMPRESSED_SIZE <>", value, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeGreaterThan(Long value) {
            addCriterion("COMPRESSED_SIZE >", value, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("COMPRESSED_SIZE >=", value, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeLessThan(Long value) {
            addCriterion("COMPRESSED_SIZE <", value, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeLessThanOrEqualTo(Long value) {
            addCriterion("COMPRESSED_SIZE <=", value, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeIn(List<Long> values) {
            addCriterion("COMPRESSED_SIZE in", values, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeNotIn(List<Long> values) {
            addCriterion("COMPRESSED_SIZE not in", values, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeBetween(Long value1, Long value2) {
            addCriterion("COMPRESSED_SIZE between", value1, value2, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedSizeNotBetween(Long value1, Long value2) {
            addCriterion("COMPRESSED_SIZE not between", value1, value2, "compressedSize");
            return (Criteria) this;
        }

        public Criteria andCompressedIsNull() {
            addCriterion("COMPRESSED is null");
            return (Criteria) this;
        }

        public Criteria andCompressedIsNotNull() {
            addCriterion("COMPRESSED is not null");
            return (Criteria) this;
        }

        public Criteria andCompressedEqualTo(String value) {
            addCriterion("COMPRESSED =", value, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedNotEqualTo(String value) {
            addCriterion("COMPRESSED <>", value, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedGreaterThan(String value) {
            addCriterion("COMPRESSED >", value, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedGreaterThanOrEqualTo(String value) {
            addCriterion("COMPRESSED >=", value, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedLessThan(String value) {
            addCriterion("COMPRESSED <", value, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedLessThanOrEqualTo(String value) {
            addCriterion("COMPRESSED <=", value, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedLike(String value) {
            addCriterion("COMPRESSED like", value, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedNotLike(String value) {
            addCriterion("COMPRESSED not like", value, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedIn(List<String> values) {
            addCriterion("COMPRESSED in", values, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedNotIn(List<String> values) {
            addCriterion("COMPRESSED not in", values, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedBetween(String value1, String value2) {
            addCriterion("COMPRESSED between", value1, value2, "compressed");
            return (Criteria) this;
        }

        public Criteria andCompressedNotBetween(String value1, String value2) {
            addCriterion("COMPRESSED not between", value1, value2, "compressed");
            return (Criteria) this;
        }

        public Criteria andIoFixIsNull() {
            addCriterion("IO_FIX is null");
            return (Criteria) this;
        }

        public Criteria andIoFixIsNotNull() {
            addCriterion("IO_FIX is not null");
            return (Criteria) this;
        }

        public Criteria andIoFixEqualTo(String value) {
            addCriterion("IO_FIX =", value, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixNotEqualTo(String value) {
            addCriterion("IO_FIX <>", value, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixGreaterThan(String value) {
            addCriterion("IO_FIX >", value, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixGreaterThanOrEqualTo(String value) {
            addCriterion("IO_FIX >=", value, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixLessThan(String value) {
            addCriterion("IO_FIX <", value, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixLessThanOrEqualTo(String value) {
            addCriterion("IO_FIX <=", value, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixLike(String value) {
            addCriterion("IO_FIX like", value, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixNotLike(String value) {
            addCriterion("IO_FIX not like", value, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixIn(List<String> values) {
            addCriterion("IO_FIX in", values, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixNotIn(List<String> values) {
            addCriterion("IO_FIX not in", values, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixBetween(String value1, String value2) {
            addCriterion("IO_FIX between", value1, value2, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIoFixNotBetween(String value1, String value2) {
            addCriterion("IO_FIX not between", value1, value2, "ioFix");
            return (Criteria) this;
        }

        public Criteria andIsOldIsNull() {
            addCriterion("IS_OLD is null");
            return (Criteria) this;
        }

        public Criteria andIsOldIsNotNull() {
            addCriterion("IS_OLD is not null");
            return (Criteria) this;
        }

        public Criteria andIsOldEqualTo(String value) {
            addCriterion("IS_OLD =", value, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldNotEqualTo(String value) {
            addCriterion("IS_OLD <>", value, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldGreaterThan(String value) {
            addCriterion("IS_OLD >", value, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldGreaterThanOrEqualTo(String value) {
            addCriterion("IS_OLD >=", value, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldLessThan(String value) {
            addCriterion("IS_OLD <", value, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldLessThanOrEqualTo(String value) {
            addCriterion("IS_OLD <=", value, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldLike(String value) {
            addCriterion("IS_OLD like", value, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldNotLike(String value) {
            addCriterion("IS_OLD not like", value, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldIn(List<String> values) {
            addCriterion("IS_OLD in", values, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldNotIn(List<String> values) {
            addCriterion("IS_OLD not in", values, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldBetween(String value1, String value2) {
            addCriterion("IS_OLD between", value1, value2, "isOld");
            return (Criteria) this;
        }

        public Criteria andIsOldNotBetween(String value1, String value2) {
            addCriterion("IS_OLD not between", value1, value2, "isOld");
            return (Criteria) this;
        }

        public Criteria andFreePageClockIsNull() {
            addCriterion("FREE_PAGE_CLOCK is null");
            return (Criteria) this;
        }

        public Criteria andFreePageClockIsNotNull() {
            addCriterion("FREE_PAGE_CLOCK is not null");
            return (Criteria) this;
        }

        public Criteria andFreePageClockEqualTo(Long value) {
            addCriterion("FREE_PAGE_CLOCK =", value, "freePageClock");
            return (Criteria) this;
        }

        public Criteria andFreePageClockNotEqualTo(Long value) {
            addCriterion("FREE_PAGE_CLOCK <>", value, "freePageClock");
            return (Criteria) this;
        }

        public Criteria andFreePageClockGreaterThan(Long value) {
            addCriterion("FREE_PAGE_CLOCK >", value, "freePageClock");
            return (Criteria) this;
        }

        public Criteria andFreePageClockGreaterThanOrEqualTo(Long value) {
            addCriterion("FREE_PAGE_CLOCK >=", value, "freePageClock");
            return (Criteria) this;
        }

        public Criteria andFreePageClockLessThan(Long value) {
            addCriterion("FREE_PAGE_CLOCK <", value, "freePageClock");
            return (Criteria) this;
        }

        public Criteria andFreePageClockLessThanOrEqualTo(Long value) {
            addCriterion("FREE_PAGE_CLOCK <=", value, "freePageClock");
            return (Criteria) this;
        }

        public Criteria andFreePageClockIn(List<Long> values) {
            addCriterion("FREE_PAGE_CLOCK in", values, "freePageClock");
            return (Criteria) this;
        }

        public Criteria andFreePageClockNotIn(List<Long> values) {
            addCriterion("FREE_PAGE_CLOCK not in", values, "freePageClock");
            return (Criteria) this;
        }

        public Criteria andFreePageClockBetween(Long value1, Long value2) {
            addCriterion("FREE_PAGE_CLOCK between", value1, value2, "freePageClock");
            return (Criteria) this;
        }

        public Criteria andFreePageClockNotBetween(Long value1, Long value2) {
            addCriterion("FREE_PAGE_CLOCK not between", value1, value2, "freePageClock");
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