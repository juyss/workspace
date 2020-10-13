package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbBufferPoolStatsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbBufferPoolStatsExample() {
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

        public Criteria andPoolSizeIsNull() {
            addCriterion("POOL_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andPoolSizeIsNotNull() {
            addCriterion("POOL_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andPoolSizeEqualTo(Long value) {
            addCriterion("POOL_SIZE =", value, "poolSize");
            return (Criteria) this;
        }

        public Criteria andPoolSizeNotEqualTo(Long value) {
            addCriterion("POOL_SIZE <>", value, "poolSize");
            return (Criteria) this;
        }

        public Criteria andPoolSizeGreaterThan(Long value) {
            addCriterion("POOL_SIZE >", value, "poolSize");
            return (Criteria) this;
        }

        public Criteria andPoolSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("POOL_SIZE >=", value, "poolSize");
            return (Criteria) this;
        }

        public Criteria andPoolSizeLessThan(Long value) {
            addCriterion("POOL_SIZE <", value, "poolSize");
            return (Criteria) this;
        }

        public Criteria andPoolSizeLessThanOrEqualTo(Long value) {
            addCriterion("POOL_SIZE <=", value, "poolSize");
            return (Criteria) this;
        }

        public Criteria andPoolSizeIn(List<Long> values) {
            addCriterion("POOL_SIZE in", values, "poolSize");
            return (Criteria) this;
        }

        public Criteria andPoolSizeNotIn(List<Long> values) {
            addCriterion("POOL_SIZE not in", values, "poolSize");
            return (Criteria) this;
        }

        public Criteria andPoolSizeBetween(Long value1, Long value2) {
            addCriterion("POOL_SIZE between", value1, value2, "poolSize");
            return (Criteria) this;
        }

        public Criteria andPoolSizeNotBetween(Long value1, Long value2) {
            addCriterion("POOL_SIZE not between", value1, value2, "poolSize");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersIsNull() {
            addCriterion("FREE_BUFFERS is null");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersIsNotNull() {
            addCriterion("FREE_BUFFERS is not null");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersEqualTo(Long value) {
            addCriterion("FREE_BUFFERS =", value, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersNotEqualTo(Long value) {
            addCriterion("FREE_BUFFERS <>", value, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersGreaterThan(Long value) {
            addCriterion("FREE_BUFFERS >", value, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersGreaterThanOrEqualTo(Long value) {
            addCriterion("FREE_BUFFERS >=", value, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersLessThan(Long value) {
            addCriterion("FREE_BUFFERS <", value, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersLessThanOrEqualTo(Long value) {
            addCriterion("FREE_BUFFERS <=", value, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersIn(List<Long> values) {
            addCriterion("FREE_BUFFERS in", values, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersNotIn(List<Long> values) {
            addCriterion("FREE_BUFFERS not in", values, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersBetween(Long value1, Long value2) {
            addCriterion("FREE_BUFFERS between", value1, value2, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andFreeBuffersNotBetween(Long value1, Long value2) {
            addCriterion("FREE_BUFFERS not between", value1, value2, "freeBuffers");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesIsNull() {
            addCriterion("DATABASE_PAGES is null");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesIsNotNull() {
            addCriterion("DATABASE_PAGES is not null");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesEqualTo(Long value) {
            addCriterion("DATABASE_PAGES =", value, "databasePages");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesNotEqualTo(Long value) {
            addCriterion("DATABASE_PAGES <>", value, "databasePages");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesGreaterThan(Long value) {
            addCriterion("DATABASE_PAGES >", value, "databasePages");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesGreaterThanOrEqualTo(Long value) {
            addCriterion("DATABASE_PAGES >=", value, "databasePages");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesLessThan(Long value) {
            addCriterion("DATABASE_PAGES <", value, "databasePages");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesLessThanOrEqualTo(Long value) {
            addCriterion("DATABASE_PAGES <=", value, "databasePages");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesIn(List<Long> values) {
            addCriterion("DATABASE_PAGES in", values, "databasePages");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesNotIn(List<Long> values) {
            addCriterion("DATABASE_PAGES not in", values, "databasePages");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesBetween(Long value1, Long value2) {
            addCriterion("DATABASE_PAGES between", value1, value2, "databasePages");
            return (Criteria) this;
        }

        public Criteria andDatabasePagesNotBetween(Long value1, Long value2) {
            addCriterion("DATABASE_PAGES not between", value1, value2, "databasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesIsNull() {
            addCriterion("OLD_DATABASE_PAGES is null");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesIsNotNull() {
            addCriterion("OLD_DATABASE_PAGES is not null");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesEqualTo(Long value) {
            addCriterion("OLD_DATABASE_PAGES =", value, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesNotEqualTo(Long value) {
            addCriterion("OLD_DATABASE_PAGES <>", value, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesGreaterThan(Long value) {
            addCriterion("OLD_DATABASE_PAGES >", value, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesGreaterThanOrEqualTo(Long value) {
            addCriterion("OLD_DATABASE_PAGES >=", value, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesLessThan(Long value) {
            addCriterion("OLD_DATABASE_PAGES <", value, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesLessThanOrEqualTo(Long value) {
            addCriterion("OLD_DATABASE_PAGES <=", value, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesIn(List<Long> values) {
            addCriterion("OLD_DATABASE_PAGES in", values, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesNotIn(List<Long> values) {
            addCriterion("OLD_DATABASE_PAGES not in", values, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesBetween(Long value1, Long value2) {
            addCriterion("OLD_DATABASE_PAGES between", value1, value2, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andOldDatabasePagesNotBetween(Long value1, Long value2) {
            addCriterion("OLD_DATABASE_PAGES not between", value1, value2, "oldDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesIsNull() {
            addCriterion("MODIFIED_DATABASE_PAGES is null");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesIsNotNull() {
            addCriterion("MODIFIED_DATABASE_PAGES is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesEqualTo(Long value) {
            addCriterion("MODIFIED_DATABASE_PAGES =", value, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesNotEqualTo(Long value) {
            addCriterion("MODIFIED_DATABASE_PAGES <>", value, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesGreaterThan(Long value) {
            addCriterion("MODIFIED_DATABASE_PAGES >", value, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesGreaterThanOrEqualTo(Long value) {
            addCriterion("MODIFIED_DATABASE_PAGES >=", value, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesLessThan(Long value) {
            addCriterion("MODIFIED_DATABASE_PAGES <", value, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesLessThanOrEqualTo(Long value) {
            addCriterion("MODIFIED_DATABASE_PAGES <=", value, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesIn(List<Long> values) {
            addCriterion("MODIFIED_DATABASE_PAGES in", values, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesNotIn(List<Long> values) {
            addCriterion("MODIFIED_DATABASE_PAGES not in", values, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesBetween(Long value1, Long value2) {
            addCriterion("MODIFIED_DATABASE_PAGES between", value1, value2, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andModifiedDatabasePagesNotBetween(Long value1, Long value2) {
            addCriterion("MODIFIED_DATABASE_PAGES not between", value1, value2, "modifiedDatabasePages");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressIsNull() {
            addCriterion("PENDING_DECOMPRESS is null");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressIsNotNull() {
            addCriterion("PENDING_DECOMPRESS is not null");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressEqualTo(Long value) {
            addCriterion("PENDING_DECOMPRESS =", value, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressNotEqualTo(Long value) {
            addCriterion("PENDING_DECOMPRESS <>", value, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressGreaterThan(Long value) {
            addCriterion("PENDING_DECOMPRESS >", value, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressGreaterThanOrEqualTo(Long value) {
            addCriterion("PENDING_DECOMPRESS >=", value, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressLessThan(Long value) {
            addCriterion("PENDING_DECOMPRESS <", value, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressLessThanOrEqualTo(Long value) {
            addCriterion("PENDING_DECOMPRESS <=", value, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressIn(List<Long> values) {
            addCriterion("PENDING_DECOMPRESS in", values, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressNotIn(List<Long> values) {
            addCriterion("PENDING_DECOMPRESS not in", values, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressBetween(Long value1, Long value2) {
            addCriterion("PENDING_DECOMPRESS between", value1, value2, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingDecompressNotBetween(Long value1, Long value2) {
            addCriterion("PENDING_DECOMPRESS not between", value1, value2, "pendingDecompress");
            return (Criteria) this;
        }

        public Criteria andPendingReadsIsNull() {
            addCriterion("PENDING_READS is null");
            return (Criteria) this;
        }

        public Criteria andPendingReadsIsNotNull() {
            addCriterion("PENDING_READS is not null");
            return (Criteria) this;
        }

        public Criteria andPendingReadsEqualTo(Long value) {
            addCriterion("PENDING_READS =", value, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingReadsNotEqualTo(Long value) {
            addCriterion("PENDING_READS <>", value, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingReadsGreaterThan(Long value) {
            addCriterion("PENDING_READS >", value, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingReadsGreaterThanOrEqualTo(Long value) {
            addCriterion("PENDING_READS >=", value, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingReadsLessThan(Long value) {
            addCriterion("PENDING_READS <", value, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingReadsLessThanOrEqualTo(Long value) {
            addCriterion("PENDING_READS <=", value, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingReadsIn(List<Long> values) {
            addCriterion("PENDING_READS in", values, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingReadsNotIn(List<Long> values) {
            addCriterion("PENDING_READS not in", values, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingReadsBetween(Long value1, Long value2) {
            addCriterion("PENDING_READS between", value1, value2, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingReadsNotBetween(Long value1, Long value2) {
            addCriterion("PENDING_READS not between", value1, value2, "pendingReads");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruIsNull() {
            addCriterion("PENDING_FLUSH_LRU is null");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruIsNotNull() {
            addCriterion("PENDING_FLUSH_LRU is not null");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruEqualTo(Long value) {
            addCriterion("PENDING_FLUSH_LRU =", value, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruNotEqualTo(Long value) {
            addCriterion("PENDING_FLUSH_LRU <>", value, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruGreaterThan(Long value) {
            addCriterion("PENDING_FLUSH_LRU >", value, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruGreaterThanOrEqualTo(Long value) {
            addCriterion("PENDING_FLUSH_LRU >=", value, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruLessThan(Long value) {
            addCriterion("PENDING_FLUSH_LRU <", value, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruLessThanOrEqualTo(Long value) {
            addCriterion("PENDING_FLUSH_LRU <=", value, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruIn(List<Long> values) {
            addCriterion("PENDING_FLUSH_LRU in", values, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruNotIn(List<Long> values) {
            addCriterion("PENDING_FLUSH_LRU not in", values, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruBetween(Long value1, Long value2) {
            addCriterion("PENDING_FLUSH_LRU between", value1, value2, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushLruNotBetween(Long value1, Long value2) {
            addCriterion("PENDING_FLUSH_LRU not between", value1, value2, "pendingFlushLru");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListIsNull() {
            addCriterion("PENDING_FLUSH_LIST is null");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListIsNotNull() {
            addCriterion("PENDING_FLUSH_LIST is not null");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListEqualTo(Long value) {
            addCriterion("PENDING_FLUSH_LIST =", value, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListNotEqualTo(Long value) {
            addCriterion("PENDING_FLUSH_LIST <>", value, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListGreaterThan(Long value) {
            addCriterion("PENDING_FLUSH_LIST >", value, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListGreaterThanOrEqualTo(Long value) {
            addCriterion("PENDING_FLUSH_LIST >=", value, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListLessThan(Long value) {
            addCriterion("PENDING_FLUSH_LIST <", value, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListLessThanOrEqualTo(Long value) {
            addCriterion("PENDING_FLUSH_LIST <=", value, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListIn(List<Long> values) {
            addCriterion("PENDING_FLUSH_LIST in", values, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListNotIn(List<Long> values) {
            addCriterion("PENDING_FLUSH_LIST not in", values, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListBetween(Long value1, Long value2) {
            addCriterion("PENDING_FLUSH_LIST between", value1, value2, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPendingFlushListNotBetween(Long value1, Long value2) {
            addCriterion("PENDING_FLUSH_LIST not between", value1, value2, "pendingFlushList");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungIsNull() {
            addCriterion("PAGES_MADE_YOUNG is null");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungIsNotNull() {
            addCriterion("PAGES_MADE_YOUNG is not null");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungEqualTo(Long value) {
            addCriterion("PAGES_MADE_YOUNG =", value, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungNotEqualTo(Long value) {
            addCriterion("PAGES_MADE_YOUNG <>", value, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungGreaterThan(Long value) {
            addCriterion("PAGES_MADE_YOUNG >", value, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungGreaterThanOrEqualTo(Long value) {
            addCriterion("PAGES_MADE_YOUNG >=", value, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungLessThan(Long value) {
            addCriterion("PAGES_MADE_YOUNG <", value, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungLessThanOrEqualTo(Long value) {
            addCriterion("PAGES_MADE_YOUNG <=", value, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungIn(List<Long> values) {
            addCriterion("PAGES_MADE_YOUNG in", values, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungNotIn(List<Long> values) {
            addCriterion("PAGES_MADE_YOUNG not in", values, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungBetween(Long value1, Long value2) {
            addCriterion("PAGES_MADE_YOUNG between", value1, value2, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungNotBetween(Long value1, Long value2) {
            addCriterion("PAGES_MADE_YOUNG not between", value1, value2, "pagesMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungIsNull() {
            addCriterion("PAGES_NOT_MADE_YOUNG is null");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungIsNotNull() {
            addCriterion("PAGES_NOT_MADE_YOUNG is not null");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungEqualTo(Long value) {
            addCriterion("PAGES_NOT_MADE_YOUNG =", value, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungNotEqualTo(Long value) {
            addCriterion("PAGES_NOT_MADE_YOUNG <>", value, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungGreaterThan(Long value) {
            addCriterion("PAGES_NOT_MADE_YOUNG >", value, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungGreaterThanOrEqualTo(Long value) {
            addCriterion("PAGES_NOT_MADE_YOUNG >=", value, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungLessThan(Long value) {
            addCriterion("PAGES_NOT_MADE_YOUNG <", value, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungLessThanOrEqualTo(Long value) {
            addCriterion("PAGES_NOT_MADE_YOUNG <=", value, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungIn(List<Long> values) {
            addCriterion("PAGES_NOT_MADE_YOUNG in", values, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungNotIn(List<Long> values) {
            addCriterion("PAGES_NOT_MADE_YOUNG not in", values, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungBetween(Long value1, Long value2) {
            addCriterion("PAGES_NOT_MADE_YOUNG between", value1, value2, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesNotMadeYoungNotBetween(Long value1, Long value2) {
            addCriterion("PAGES_NOT_MADE_YOUNG not between", value1, value2, "pagesNotMadeYoung");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateIsNull() {
            addCriterion("PAGES_MADE_YOUNG_RATE is null");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateIsNotNull() {
            addCriterion("PAGES_MADE_YOUNG_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateEqualTo(Float value) {
            addCriterion("PAGES_MADE_YOUNG_RATE =", value, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateNotEqualTo(Float value) {
            addCriterion("PAGES_MADE_YOUNG_RATE <>", value, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateGreaterThan(Float value) {
            addCriterion("PAGES_MADE_YOUNG_RATE >", value, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateGreaterThanOrEqualTo(Float value) {
            addCriterion("PAGES_MADE_YOUNG_RATE >=", value, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateLessThan(Float value) {
            addCriterion("PAGES_MADE_YOUNG_RATE <", value, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateLessThanOrEqualTo(Float value) {
            addCriterion("PAGES_MADE_YOUNG_RATE <=", value, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateIn(List<Float> values) {
            addCriterion("PAGES_MADE_YOUNG_RATE in", values, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateNotIn(List<Float> values) {
            addCriterion("PAGES_MADE_YOUNG_RATE not in", values, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateBetween(Float value1, Float value2) {
            addCriterion("PAGES_MADE_YOUNG_RATE between", value1, value2, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeYoungRateNotBetween(Float value1, Float value2) {
            addCriterion("PAGES_MADE_YOUNG_RATE not between", value1, value2, "pagesMadeYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateIsNull() {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE is null");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateIsNotNull() {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateEqualTo(Float value) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE =", value, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateNotEqualTo(Float value) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE <>", value, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateGreaterThan(Float value) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE >", value, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateGreaterThanOrEqualTo(Float value) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE >=", value, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateLessThan(Float value) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE <", value, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateLessThanOrEqualTo(Float value) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE <=", value, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateIn(List<Float> values) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE in", values, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateNotIn(List<Float> values) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE not in", values, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateBetween(Float value1, Float value2) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE between", value1, value2, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andPagesMadeNotYoungRateNotBetween(Float value1, Float value2) {
            addCriterion("PAGES_MADE_NOT_YOUNG_RATE not between", value1, value2, "pagesMadeNotYoungRate");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadIsNull() {
            addCriterion("NUMBER_PAGES_READ is null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadIsNotNull() {
            addCriterion("NUMBER_PAGES_READ is not null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_READ =", value, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadNotEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_READ <>", value, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadGreaterThan(Long value) {
            addCriterion("NUMBER_PAGES_READ >", value, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadGreaterThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_READ >=", value, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadLessThan(Long value) {
            addCriterion("NUMBER_PAGES_READ <", value, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadLessThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_READ <=", value, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_READ in", values, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadNotIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_READ not in", values, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_READ between", value1, value2, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadNotBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_READ not between", value1, value2, "numberPagesRead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedIsNull() {
            addCriterion("NUMBER_PAGES_CREATED is null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedIsNotNull() {
            addCriterion("NUMBER_PAGES_CREATED is not null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_CREATED =", value, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedNotEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_CREATED <>", value, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedGreaterThan(Long value) {
            addCriterion("NUMBER_PAGES_CREATED >", value, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedGreaterThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_CREATED >=", value, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedLessThan(Long value) {
            addCriterion("NUMBER_PAGES_CREATED <", value, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedLessThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_CREATED <=", value, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_CREATED in", values, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedNotIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_CREATED not in", values, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_CREATED between", value1, value2, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesCreatedNotBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_CREATED not between", value1, value2, "numberPagesCreated");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenIsNull() {
            addCriterion("NUMBER_PAGES_WRITTEN is null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenIsNotNull() {
            addCriterion("NUMBER_PAGES_WRITTEN is not null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_WRITTEN =", value, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenNotEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_WRITTEN <>", value, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenGreaterThan(Long value) {
            addCriterion("NUMBER_PAGES_WRITTEN >", value, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenGreaterThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_WRITTEN >=", value, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenLessThan(Long value) {
            addCriterion("NUMBER_PAGES_WRITTEN <", value, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenLessThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_WRITTEN <=", value, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_WRITTEN in", values, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenNotIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_WRITTEN not in", values, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_WRITTEN between", value1, value2, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andNumberPagesWrittenNotBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_WRITTEN not between", value1, value2, "numberPagesWritten");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateIsNull() {
            addCriterion("PAGES_READ_RATE is null");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateIsNotNull() {
            addCriterion("PAGES_READ_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateEqualTo(Float value) {
            addCriterion("PAGES_READ_RATE =", value, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateNotEqualTo(Float value) {
            addCriterion("PAGES_READ_RATE <>", value, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateGreaterThan(Float value) {
            addCriterion("PAGES_READ_RATE >", value, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateGreaterThanOrEqualTo(Float value) {
            addCriterion("PAGES_READ_RATE >=", value, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateLessThan(Float value) {
            addCriterion("PAGES_READ_RATE <", value, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateLessThanOrEqualTo(Float value) {
            addCriterion("PAGES_READ_RATE <=", value, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateIn(List<Float> values) {
            addCriterion("PAGES_READ_RATE in", values, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateNotIn(List<Float> values) {
            addCriterion("PAGES_READ_RATE not in", values, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateBetween(Float value1, Float value2) {
            addCriterion("PAGES_READ_RATE between", value1, value2, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesReadRateNotBetween(Float value1, Float value2) {
            addCriterion("PAGES_READ_RATE not between", value1, value2, "pagesReadRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateIsNull() {
            addCriterion("PAGES_CREATE_RATE is null");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateIsNotNull() {
            addCriterion("PAGES_CREATE_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateEqualTo(Float value) {
            addCriterion("PAGES_CREATE_RATE =", value, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateNotEqualTo(Float value) {
            addCriterion("PAGES_CREATE_RATE <>", value, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateGreaterThan(Float value) {
            addCriterion("PAGES_CREATE_RATE >", value, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateGreaterThanOrEqualTo(Float value) {
            addCriterion("PAGES_CREATE_RATE >=", value, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateLessThan(Float value) {
            addCriterion("PAGES_CREATE_RATE <", value, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateLessThanOrEqualTo(Float value) {
            addCriterion("PAGES_CREATE_RATE <=", value, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateIn(List<Float> values) {
            addCriterion("PAGES_CREATE_RATE in", values, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateNotIn(List<Float> values) {
            addCriterion("PAGES_CREATE_RATE not in", values, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateBetween(Float value1, Float value2) {
            addCriterion("PAGES_CREATE_RATE between", value1, value2, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesCreateRateNotBetween(Float value1, Float value2) {
            addCriterion("PAGES_CREATE_RATE not between", value1, value2, "pagesCreateRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateIsNull() {
            addCriterion("PAGES_WRITTEN_RATE is null");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateIsNotNull() {
            addCriterion("PAGES_WRITTEN_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateEqualTo(Float value) {
            addCriterion("PAGES_WRITTEN_RATE =", value, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateNotEqualTo(Float value) {
            addCriterion("PAGES_WRITTEN_RATE <>", value, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateGreaterThan(Float value) {
            addCriterion("PAGES_WRITTEN_RATE >", value, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateGreaterThanOrEqualTo(Float value) {
            addCriterion("PAGES_WRITTEN_RATE >=", value, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateLessThan(Float value) {
            addCriterion("PAGES_WRITTEN_RATE <", value, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateLessThanOrEqualTo(Float value) {
            addCriterion("PAGES_WRITTEN_RATE <=", value, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateIn(List<Float> values) {
            addCriterion("PAGES_WRITTEN_RATE in", values, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateNotIn(List<Float> values) {
            addCriterion("PAGES_WRITTEN_RATE not in", values, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateBetween(Float value1, Float value2) {
            addCriterion("PAGES_WRITTEN_RATE between", value1, value2, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andPagesWrittenRateNotBetween(Float value1, Float value2) {
            addCriterion("PAGES_WRITTEN_RATE not between", value1, value2, "pagesWrittenRate");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetIsNull() {
            addCriterion("NUMBER_PAGES_GET is null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetIsNotNull() {
            addCriterion("NUMBER_PAGES_GET is not null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_GET =", value, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetNotEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_GET <>", value, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetGreaterThan(Long value) {
            addCriterion("NUMBER_PAGES_GET >", value, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetGreaterThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_GET >=", value, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetLessThan(Long value) {
            addCriterion("NUMBER_PAGES_GET <", value, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetLessThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_GET <=", value, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_GET in", values, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetNotIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_GET not in", values, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_GET between", value1, value2, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andNumberPagesGetNotBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_GET not between", value1, value2, "numberPagesGet");
            return (Criteria) this;
        }

        public Criteria andHitRateIsNull() {
            addCriterion("HIT_RATE is null");
            return (Criteria) this;
        }

        public Criteria andHitRateIsNotNull() {
            addCriterion("HIT_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andHitRateEqualTo(Long value) {
            addCriterion("HIT_RATE =", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotEqualTo(Long value) {
            addCriterion("HIT_RATE <>", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThan(Long value) {
            addCriterion("HIT_RATE >", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateGreaterThanOrEqualTo(Long value) {
            addCriterion("HIT_RATE >=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThan(Long value) {
            addCriterion("HIT_RATE <", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateLessThanOrEqualTo(Long value) {
            addCriterion("HIT_RATE <=", value, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateIn(List<Long> values) {
            addCriterion("HIT_RATE in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotIn(List<Long> values) {
            addCriterion("HIT_RATE not in", values, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateBetween(Long value1, Long value2) {
            addCriterion("HIT_RATE between", value1, value2, "hitRate");
            return (Criteria) this;
        }

        public Criteria andHitRateNotBetween(Long value1, Long value2) {
            addCriterion("HIT_RATE not between", value1, value2, "hitRate");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsIsNull() {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS is null");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsIsNotNull() {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS is not null");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsEqualTo(Long value) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS =", value, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsNotEqualTo(Long value) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS <>", value, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsGreaterThan(Long value) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS >", value, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsGreaterThanOrEqualTo(Long value) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS >=", value, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsLessThan(Long value) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS <", value, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsLessThanOrEqualTo(Long value) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS <=", value, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsIn(List<Long> values) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS in", values, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsNotIn(List<Long> values) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS not in", values, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsBetween(Long value1, Long value2) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS between", value1, value2, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andYoungMakePerThousandGetsNotBetween(Long value1, Long value2) {
            addCriterion("YOUNG_MAKE_PER_THOUSAND_GETS not between", value1, value2, "youngMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsIsNull() {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS is null");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsIsNotNull() {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS is not null");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsEqualTo(Long value) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS =", value, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsNotEqualTo(Long value) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS <>", value, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsGreaterThan(Long value) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS >", value, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsGreaterThanOrEqualTo(Long value) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS >=", value, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsLessThan(Long value) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS <", value, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsLessThanOrEqualTo(Long value) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS <=", value, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsIn(List<Long> values) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS in", values, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsNotIn(List<Long> values) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS not in", values, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsBetween(Long value1, Long value2) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS between", value1, value2, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNotYoungMakePerThousandGetsNotBetween(Long value1, Long value2) {
            addCriterion("NOT_YOUNG_MAKE_PER_THOUSAND_GETS not between", value1, value2, "notYoungMakePerThousandGets");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadIsNull() {
            addCriterion("NUMBER_PAGES_READ_AHEAD is null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadIsNotNull() {
            addCriterion("NUMBER_PAGES_READ_AHEAD is not null");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_READ_AHEAD =", value, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadNotEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_READ_AHEAD <>", value, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadGreaterThan(Long value) {
            addCriterion("NUMBER_PAGES_READ_AHEAD >", value, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadGreaterThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_READ_AHEAD >=", value, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadLessThan(Long value) {
            addCriterion("NUMBER_PAGES_READ_AHEAD <", value, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadLessThanOrEqualTo(Long value) {
            addCriterion("NUMBER_PAGES_READ_AHEAD <=", value, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_READ_AHEAD in", values, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadNotIn(List<Long> values) {
            addCriterion("NUMBER_PAGES_READ_AHEAD not in", values, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_READ_AHEAD between", value1, value2, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberPagesReadAheadNotBetween(Long value1, Long value2) {
            addCriterion("NUMBER_PAGES_READ_AHEAD not between", value1, value2, "numberPagesReadAhead");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedIsNull() {
            addCriterion("NUMBER_READ_AHEAD_EVICTED is null");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedIsNotNull() {
            addCriterion("NUMBER_READ_AHEAD_EVICTED is not null");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedEqualTo(Long value) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED =", value, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedNotEqualTo(Long value) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED <>", value, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedGreaterThan(Long value) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED >", value, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedGreaterThanOrEqualTo(Long value) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED >=", value, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedLessThan(Long value) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED <", value, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedLessThanOrEqualTo(Long value) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED <=", value, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedIn(List<Long> values) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED in", values, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedNotIn(List<Long> values) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED not in", values, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedBetween(Long value1, Long value2) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED between", value1, value2, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andNumberReadAheadEvictedNotBetween(Long value1, Long value2) {
            addCriterion("NUMBER_READ_AHEAD_EVICTED not between", value1, value2, "numberReadAheadEvicted");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateIsNull() {
            addCriterion("READ_AHEAD_RATE is null");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateIsNotNull() {
            addCriterion("READ_AHEAD_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateEqualTo(Float value) {
            addCriterion("READ_AHEAD_RATE =", value, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateNotEqualTo(Float value) {
            addCriterion("READ_AHEAD_RATE <>", value, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateGreaterThan(Float value) {
            addCriterion("READ_AHEAD_RATE >", value, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateGreaterThanOrEqualTo(Float value) {
            addCriterion("READ_AHEAD_RATE >=", value, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateLessThan(Float value) {
            addCriterion("READ_AHEAD_RATE <", value, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateLessThanOrEqualTo(Float value) {
            addCriterion("READ_AHEAD_RATE <=", value, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateIn(List<Float> values) {
            addCriterion("READ_AHEAD_RATE in", values, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateNotIn(List<Float> values) {
            addCriterion("READ_AHEAD_RATE not in", values, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateBetween(Float value1, Float value2) {
            addCriterion("READ_AHEAD_RATE between", value1, value2, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadRateNotBetween(Float value1, Float value2) {
            addCriterion("READ_AHEAD_RATE not between", value1, value2, "readAheadRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateIsNull() {
            addCriterion("READ_AHEAD_EVICTED_RATE is null");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateIsNotNull() {
            addCriterion("READ_AHEAD_EVICTED_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateEqualTo(Float value) {
            addCriterion("READ_AHEAD_EVICTED_RATE =", value, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateNotEqualTo(Float value) {
            addCriterion("READ_AHEAD_EVICTED_RATE <>", value, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateGreaterThan(Float value) {
            addCriterion("READ_AHEAD_EVICTED_RATE >", value, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateGreaterThanOrEqualTo(Float value) {
            addCriterion("READ_AHEAD_EVICTED_RATE >=", value, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateLessThan(Float value) {
            addCriterion("READ_AHEAD_EVICTED_RATE <", value, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateLessThanOrEqualTo(Float value) {
            addCriterion("READ_AHEAD_EVICTED_RATE <=", value, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateIn(List<Float> values) {
            addCriterion("READ_AHEAD_EVICTED_RATE in", values, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateNotIn(List<Float> values) {
            addCriterion("READ_AHEAD_EVICTED_RATE not in", values, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateBetween(Float value1, Float value2) {
            addCriterion("READ_AHEAD_EVICTED_RATE between", value1, value2, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andReadAheadEvictedRateNotBetween(Float value1, Float value2) {
            addCriterion("READ_AHEAD_EVICTED_RATE not between", value1, value2, "readAheadEvictedRate");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalIsNull() {
            addCriterion("LRU_IO_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalIsNotNull() {
            addCriterion("LRU_IO_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalEqualTo(Long value) {
            addCriterion("LRU_IO_TOTAL =", value, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalNotEqualTo(Long value) {
            addCriterion("LRU_IO_TOTAL <>", value, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalGreaterThan(Long value) {
            addCriterion("LRU_IO_TOTAL >", value, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("LRU_IO_TOTAL >=", value, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalLessThan(Long value) {
            addCriterion("LRU_IO_TOTAL <", value, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalLessThanOrEqualTo(Long value) {
            addCriterion("LRU_IO_TOTAL <=", value, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalIn(List<Long> values) {
            addCriterion("LRU_IO_TOTAL in", values, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalNotIn(List<Long> values) {
            addCriterion("LRU_IO_TOTAL not in", values, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalBetween(Long value1, Long value2) {
            addCriterion("LRU_IO_TOTAL between", value1, value2, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoTotalNotBetween(Long value1, Long value2) {
            addCriterion("LRU_IO_TOTAL not between", value1, value2, "lruIoTotal");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentIsNull() {
            addCriterion("LRU_IO_CURRENT is null");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentIsNotNull() {
            addCriterion("LRU_IO_CURRENT is not null");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentEqualTo(Long value) {
            addCriterion("LRU_IO_CURRENT =", value, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentNotEqualTo(Long value) {
            addCriterion("LRU_IO_CURRENT <>", value, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentGreaterThan(Long value) {
            addCriterion("LRU_IO_CURRENT >", value, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentGreaterThanOrEqualTo(Long value) {
            addCriterion("LRU_IO_CURRENT >=", value, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentLessThan(Long value) {
            addCriterion("LRU_IO_CURRENT <", value, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentLessThanOrEqualTo(Long value) {
            addCriterion("LRU_IO_CURRENT <=", value, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentIn(List<Long> values) {
            addCriterion("LRU_IO_CURRENT in", values, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentNotIn(List<Long> values) {
            addCriterion("LRU_IO_CURRENT not in", values, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentBetween(Long value1, Long value2) {
            addCriterion("LRU_IO_CURRENT between", value1, value2, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andLruIoCurrentNotBetween(Long value1, Long value2) {
            addCriterion("LRU_IO_CURRENT not between", value1, value2, "lruIoCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalIsNull() {
            addCriterion("UNCOMPRESS_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalIsNotNull() {
            addCriterion("UNCOMPRESS_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalEqualTo(Long value) {
            addCriterion("UNCOMPRESS_TOTAL =", value, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalNotEqualTo(Long value) {
            addCriterion("UNCOMPRESS_TOTAL <>", value, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalGreaterThan(Long value) {
            addCriterion("UNCOMPRESS_TOTAL >", value, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("UNCOMPRESS_TOTAL >=", value, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalLessThan(Long value) {
            addCriterion("UNCOMPRESS_TOTAL <", value, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalLessThanOrEqualTo(Long value) {
            addCriterion("UNCOMPRESS_TOTAL <=", value, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalIn(List<Long> values) {
            addCriterion("UNCOMPRESS_TOTAL in", values, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalNotIn(List<Long> values) {
            addCriterion("UNCOMPRESS_TOTAL not in", values, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalBetween(Long value1, Long value2) {
            addCriterion("UNCOMPRESS_TOTAL between", value1, value2, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressTotalNotBetween(Long value1, Long value2) {
            addCriterion("UNCOMPRESS_TOTAL not between", value1, value2, "uncompressTotal");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentIsNull() {
            addCriterion("UNCOMPRESS_CURRENT is null");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentIsNotNull() {
            addCriterion("UNCOMPRESS_CURRENT is not null");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentEqualTo(Long value) {
            addCriterion("UNCOMPRESS_CURRENT =", value, "uncompressCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentNotEqualTo(Long value) {
            addCriterion("UNCOMPRESS_CURRENT <>", value, "uncompressCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentGreaterThan(Long value) {
            addCriterion("UNCOMPRESS_CURRENT >", value, "uncompressCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentGreaterThanOrEqualTo(Long value) {
            addCriterion("UNCOMPRESS_CURRENT >=", value, "uncompressCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentLessThan(Long value) {
            addCriterion("UNCOMPRESS_CURRENT <", value, "uncompressCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentLessThanOrEqualTo(Long value) {
            addCriterion("UNCOMPRESS_CURRENT <=", value, "uncompressCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentIn(List<Long> values) {
            addCriterion("UNCOMPRESS_CURRENT in", values, "uncompressCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentNotIn(List<Long> values) {
            addCriterion("UNCOMPRESS_CURRENT not in", values, "uncompressCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentBetween(Long value1, Long value2) {
            addCriterion("UNCOMPRESS_CURRENT between", value1, value2, "uncompressCurrent");
            return (Criteria) this;
        }

        public Criteria andUncompressCurrentNotBetween(Long value1, Long value2) {
            addCriterion("UNCOMPRESS_CURRENT not between", value1, value2, "uncompressCurrent");
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