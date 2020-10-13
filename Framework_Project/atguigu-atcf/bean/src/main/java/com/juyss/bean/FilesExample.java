package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class FilesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FilesExample() {
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

        public Criteria andFileIdIsNull() {
            addCriterion("FILE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("FILE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(Long value) {
            addCriterion("FILE_ID =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(Long value) {
            addCriterion("FILE_ID <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(Long value) {
            addCriterion("FILE_ID >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(Long value) {
            addCriterion("FILE_ID >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(Long value) {
            addCriterion("FILE_ID <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(Long value) {
            addCriterion("FILE_ID <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<Long> values) {
            addCriterion("FILE_ID in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<Long> values) {
            addCriterion("FILE_ID not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(Long value1, Long value2) {
            addCriterion("FILE_ID between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(Long value1, Long value2) {
            addCriterion("FILE_ID not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNull() {
            addCriterion("FILE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNotNull() {
            addCriterion("FILE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeEqualTo(String value) {
            addCriterion("FILE_TYPE =", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotEqualTo(String value) {
            addCriterion("FILE_TYPE <>", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThan(String value) {
            addCriterion("FILE_TYPE >", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_TYPE >=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThan(String value) {
            addCriterion("FILE_TYPE <", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThanOrEqualTo(String value) {
            addCriterion("FILE_TYPE <=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLike(String value) {
            addCriterion("FILE_TYPE like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotLike(String value) {
            addCriterion("FILE_TYPE not like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeIn(List<String> values) {
            addCriterion("FILE_TYPE in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotIn(List<String> values) {
            addCriterion("FILE_TYPE not in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeBetween(String value1, String value2) {
            addCriterion("FILE_TYPE between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotBetween(String value1, String value2) {
            addCriterion("FILE_TYPE not between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameIsNull() {
            addCriterion("TABLESPACE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameIsNotNull() {
            addCriterion("TABLESPACE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameEqualTo(String value) {
            addCriterion("TABLESPACE_NAME =", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameNotEqualTo(String value) {
            addCriterion("TABLESPACE_NAME <>", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameGreaterThan(String value) {
            addCriterion("TABLESPACE_NAME >", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("TABLESPACE_NAME >=", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameLessThan(String value) {
            addCriterion("TABLESPACE_NAME <", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameLessThanOrEqualTo(String value) {
            addCriterion("TABLESPACE_NAME <=", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameLike(String value) {
            addCriterion("TABLESPACE_NAME like", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameNotLike(String value) {
            addCriterion("TABLESPACE_NAME not like", value, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameIn(List<String> values) {
            addCriterion("TABLESPACE_NAME in", values, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameNotIn(List<String> values) {
            addCriterion("TABLESPACE_NAME not in", values, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameBetween(String value1, String value2) {
            addCriterion("TABLESPACE_NAME between", value1, value2, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTablespaceNameNotBetween(String value1, String value2) {
            addCriterion("TABLESPACE_NAME not between", value1, value2, "tablespaceName");
            return (Criteria) this;
        }

        public Criteria andTableCatalogIsNull() {
            addCriterion("TABLE_CATALOG is null");
            return (Criteria) this;
        }

        public Criteria andTableCatalogIsNotNull() {
            addCriterion("TABLE_CATALOG is not null");
            return (Criteria) this;
        }

        public Criteria andTableCatalogEqualTo(String value) {
            addCriterion("TABLE_CATALOG =", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotEqualTo(String value) {
            addCriterion("TABLE_CATALOG <>", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogGreaterThan(String value) {
            addCriterion("TABLE_CATALOG >", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_CATALOG >=", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogLessThan(String value) {
            addCriterion("TABLE_CATALOG <", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogLessThanOrEqualTo(String value) {
            addCriterion("TABLE_CATALOG <=", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogLike(String value) {
            addCriterion("TABLE_CATALOG like", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotLike(String value) {
            addCriterion("TABLE_CATALOG not like", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogIn(List<String> values) {
            addCriterion("TABLE_CATALOG in", values, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotIn(List<String> values) {
            addCriterion("TABLE_CATALOG not in", values, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogBetween(String value1, String value2) {
            addCriterion("TABLE_CATALOG between", value1, value2, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotBetween(String value1, String value2) {
            addCriterion("TABLE_CATALOG not between", value1, value2, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameIsNull() {
            addCriterion("LOGFILE_GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameIsNotNull() {
            addCriterion("LOGFILE_GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameEqualTo(String value) {
            addCriterion("LOGFILE_GROUP_NAME =", value, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameNotEqualTo(String value) {
            addCriterion("LOGFILE_GROUP_NAME <>", value, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameGreaterThan(String value) {
            addCriterion("LOGFILE_GROUP_NAME >", value, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOGFILE_GROUP_NAME >=", value, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameLessThan(String value) {
            addCriterion("LOGFILE_GROUP_NAME <", value, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameLessThanOrEqualTo(String value) {
            addCriterion("LOGFILE_GROUP_NAME <=", value, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameLike(String value) {
            addCriterion("LOGFILE_GROUP_NAME like", value, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameNotLike(String value) {
            addCriterion("LOGFILE_GROUP_NAME not like", value, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameIn(List<String> values) {
            addCriterion("LOGFILE_GROUP_NAME in", values, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameNotIn(List<String> values) {
            addCriterion("LOGFILE_GROUP_NAME not in", values, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameBetween(String value1, String value2) {
            addCriterion("LOGFILE_GROUP_NAME between", value1, value2, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNameNotBetween(String value1, String value2) {
            addCriterion("LOGFILE_GROUP_NAME not between", value1, value2, "logfileGroupName");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberIsNull() {
            addCriterion("LOGFILE_GROUP_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberIsNotNull() {
            addCriterion("LOGFILE_GROUP_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberEqualTo(Long value) {
            addCriterion("LOGFILE_GROUP_NUMBER =", value, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberNotEqualTo(Long value) {
            addCriterion("LOGFILE_GROUP_NUMBER <>", value, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberGreaterThan(Long value) {
            addCriterion("LOGFILE_GROUP_NUMBER >", value, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("LOGFILE_GROUP_NUMBER >=", value, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberLessThan(Long value) {
            addCriterion("LOGFILE_GROUP_NUMBER <", value, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberLessThanOrEqualTo(Long value) {
            addCriterion("LOGFILE_GROUP_NUMBER <=", value, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberIn(List<Long> values) {
            addCriterion("LOGFILE_GROUP_NUMBER in", values, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberNotIn(List<Long> values) {
            addCriterion("LOGFILE_GROUP_NUMBER not in", values, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberBetween(Long value1, Long value2) {
            addCriterion("LOGFILE_GROUP_NUMBER between", value1, value2, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andLogfileGroupNumberNotBetween(Long value1, Long value2) {
            addCriterion("LOGFILE_GROUP_NUMBER not between", value1, value2, "logfileGroupNumber");
            return (Criteria) this;
        }

        public Criteria andEngineIsNull() {
            addCriterion("ENGINE is null");
            return (Criteria) this;
        }

        public Criteria andEngineIsNotNull() {
            addCriterion("ENGINE is not null");
            return (Criteria) this;
        }

        public Criteria andEngineEqualTo(String value) {
            addCriterion("ENGINE =", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineNotEqualTo(String value) {
            addCriterion("ENGINE <>", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineGreaterThan(String value) {
            addCriterion("ENGINE >", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineGreaterThanOrEqualTo(String value) {
            addCriterion("ENGINE >=", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineLessThan(String value) {
            addCriterion("ENGINE <", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineLessThanOrEqualTo(String value) {
            addCriterion("ENGINE <=", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineLike(String value) {
            addCriterion("ENGINE like", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineNotLike(String value) {
            addCriterion("ENGINE not like", value, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineIn(List<String> values) {
            addCriterion("ENGINE in", values, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineNotIn(List<String> values) {
            addCriterion("ENGINE not in", values, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineBetween(String value1, String value2) {
            addCriterion("ENGINE between", value1, value2, "engine");
            return (Criteria) this;
        }

        public Criteria andEngineNotBetween(String value1, String value2) {
            addCriterion("ENGINE not between", value1, value2, "engine");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsIsNull() {
            addCriterion("FREE_EXTENTS is null");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsIsNotNull() {
            addCriterion("FREE_EXTENTS is not null");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsEqualTo(Long value) {
            addCriterion("FREE_EXTENTS =", value, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsNotEqualTo(Long value) {
            addCriterion("FREE_EXTENTS <>", value, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsGreaterThan(Long value) {
            addCriterion("FREE_EXTENTS >", value, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsGreaterThanOrEqualTo(Long value) {
            addCriterion("FREE_EXTENTS >=", value, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsLessThan(Long value) {
            addCriterion("FREE_EXTENTS <", value, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsLessThanOrEqualTo(Long value) {
            addCriterion("FREE_EXTENTS <=", value, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsIn(List<Long> values) {
            addCriterion("FREE_EXTENTS in", values, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsNotIn(List<Long> values) {
            addCriterion("FREE_EXTENTS not in", values, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsBetween(Long value1, Long value2) {
            addCriterion("FREE_EXTENTS between", value1, value2, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andFreeExtentsNotBetween(Long value1, Long value2) {
            addCriterion("FREE_EXTENTS not between", value1, value2, "freeExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsIsNull() {
            addCriterion("TOTAL_EXTENTS is null");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsIsNotNull() {
            addCriterion("TOTAL_EXTENTS is not null");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsEqualTo(Long value) {
            addCriterion("TOTAL_EXTENTS =", value, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsNotEqualTo(Long value) {
            addCriterion("TOTAL_EXTENTS <>", value, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsGreaterThan(Long value) {
            addCriterion("TOTAL_EXTENTS >", value, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_EXTENTS >=", value, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsLessThan(Long value) {
            addCriterion("TOTAL_EXTENTS <", value, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_EXTENTS <=", value, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsIn(List<Long> values) {
            addCriterion("TOTAL_EXTENTS in", values, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsNotIn(List<Long> values) {
            addCriterion("TOTAL_EXTENTS not in", values, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsBetween(Long value1, Long value2) {
            addCriterion("TOTAL_EXTENTS between", value1, value2, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andTotalExtentsNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_EXTENTS not between", value1, value2, "totalExtents");
            return (Criteria) this;
        }

        public Criteria andExtentSizeIsNull() {
            addCriterion("EXTENT_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andExtentSizeIsNotNull() {
            addCriterion("EXTENT_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andExtentSizeEqualTo(Long value) {
            addCriterion("EXTENT_SIZE =", value, "extentSize");
            return (Criteria) this;
        }

        public Criteria andExtentSizeNotEqualTo(Long value) {
            addCriterion("EXTENT_SIZE <>", value, "extentSize");
            return (Criteria) this;
        }

        public Criteria andExtentSizeGreaterThan(Long value) {
            addCriterion("EXTENT_SIZE >", value, "extentSize");
            return (Criteria) this;
        }

        public Criteria andExtentSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("EXTENT_SIZE >=", value, "extentSize");
            return (Criteria) this;
        }

        public Criteria andExtentSizeLessThan(Long value) {
            addCriterion("EXTENT_SIZE <", value, "extentSize");
            return (Criteria) this;
        }

        public Criteria andExtentSizeLessThanOrEqualTo(Long value) {
            addCriterion("EXTENT_SIZE <=", value, "extentSize");
            return (Criteria) this;
        }

        public Criteria andExtentSizeIn(List<Long> values) {
            addCriterion("EXTENT_SIZE in", values, "extentSize");
            return (Criteria) this;
        }

        public Criteria andExtentSizeNotIn(List<Long> values) {
            addCriterion("EXTENT_SIZE not in", values, "extentSize");
            return (Criteria) this;
        }

        public Criteria andExtentSizeBetween(Long value1, Long value2) {
            addCriterion("EXTENT_SIZE between", value1, value2, "extentSize");
            return (Criteria) this;
        }

        public Criteria andExtentSizeNotBetween(Long value1, Long value2) {
            addCriterion("EXTENT_SIZE not between", value1, value2, "extentSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeIsNull() {
            addCriterion("INITIAL_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andInitialSizeIsNotNull() {
            addCriterion("INITIAL_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andInitialSizeEqualTo(Long value) {
            addCriterion("INITIAL_SIZE =", value, "initialSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeNotEqualTo(Long value) {
            addCriterion("INITIAL_SIZE <>", value, "initialSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeGreaterThan(Long value) {
            addCriterion("INITIAL_SIZE >", value, "initialSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("INITIAL_SIZE >=", value, "initialSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeLessThan(Long value) {
            addCriterion("INITIAL_SIZE <", value, "initialSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeLessThanOrEqualTo(Long value) {
            addCriterion("INITIAL_SIZE <=", value, "initialSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeIn(List<Long> values) {
            addCriterion("INITIAL_SIZE in", values, "initialSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeNotIn(List<Long> values) {
            addCriterion("INITIAL_SIZE not in", values, "initialSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeBetween(Long value1, Long value2) {
            addCriterion("INITIAL_SIZE between", value1, value2, "initialSize");
            return (Criteria) this;
        }

        public Criteria andInitialSizeNotBetween(Long value1, Long value2) {
            addCriterion("INITIAL_SIZE not between", value1, value2, "initialSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeIsNull() {
            addCriterion("MAXIMUM_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeIsNotNull() {
            addCriterion("MAXIMUM_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeEqualTo(Long value) {
            addCriterion("MAXIMUM_SIZE =", value, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeNotEqualTo(Long value) {
            addCriterion("MAXIMUM_SIZE <>", value, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeGreaterThan(Long value) {
            addCriterion("MAXIMUM_SIZE >", value, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("MAXIMUM_SIZE >=", value, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeLessThan(Long value) {
            addCriterion("MAXIMUM_SIZE <", value, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeLessThanOrEqualTo(Long value) {
            addCriterion("MAXIMUM_SIZE <=", value, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeIn(List<Long> values) {
            addCriterion("MAXIMUM_SIZE in", values, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeNotIn(List<Long> values) {
            addCriterion("MAXIMUM_SIZE not in", values, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeBetween(Long value1, Long value2) {
            addCriterion("MAXIMUM_SIZE between", value1, value2, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andMaximumSizeNotBetween(Long value1, Long value2) {
            addCriterion("MAXIMUM_SIZE not between", value1, value2, "maximumSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeIsNull() {
            addCriterion("AUTOEXTEND_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeIsNotNull() {
            addCriterion("AUTOEXTEND_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeEqualTo(Long value) {
            addCriterion("AUTOEXTEND_SIZE =", value, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeNotEqualTo(Long value) {
            addCriterion("AUTOEXTEND_SIZE <>", value, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeGreaterThan(Long value) {
            addCriterion("AUTOEXTEND_SIZE >", value, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("AUTOEXTEND_SIZE >=", value, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeLessThan(Long value) {
            addCriterion("AUTOEXTEND_SIZE <", value, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeLessThanOrEqualTo(Long value) {
            addCriterion("AUTOEXTEND_SIZE <=", value, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeIn(List<Long> values) {
            addCriterion("AUTOEXTEND_SIZE in", values, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeNotIn(List<Long> values) {
            addCriterion("AUTOEXTEND_SIZE not in", values, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeBetween(Long value1, Long value2) {
            addCriterion("AUTOEXTEND_SIZE between", value1, value2, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andAutoextendSizeNotBetween(Long value1, Long value2) {
            addCriterion("AUTOEXTEND_SIZE not between", value1, value2, "autoextendSize");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Long value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Long value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Long value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Long value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Long value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Long> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Long> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Long value1, Long value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Long value1, Long value2) {
            addCriterion("VERSION not between", value1, value2, "version");
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

        public Criteria andDataFreeIsNull() {
            addCriterion("DATA_FREE is null");
            return (Criteria) this;
        }

        public Criteria andDataFreeIsNotNull() {
            addCriterion("DATA_FREE is not null");
            return (Criteria) this;
        }

        public Criteria andDataFreeEqualTo(Long value) {
            addCriterion("DATA_FREE =", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeNotEqualTo(Long value) {
            addCriterion("DATA_FREE <>", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeGreaterThan(Long value) {
            addCriterion("DATA_FREE >", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeGreaterThanOrEqualTo(Long value) {
            addCriterion("DATA_FREE >=", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeLessThan(Long value) {
            addCriterion("DATA_FREE <", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeLessThanOrEqualTo(Long value) {
            addCriterion("DATA_FREE <=", value, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeIn(List<Long> values) {
            addCriterion("DATA_FREE in", values, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeNotIn(List<Long> values) {
            addCriterion("DATA_FREE not in", values, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeBetween(Long value1, Long value2) {
            addCriterion("DATA_FREE between", value1, value2, "dataFree");
            return (Criteria) this;
        }

        public Criteria andDataFreeNotBetween(Long value1, Long value2) {
            addCriterion("DATA_FREE not between", value1, value2, "dataFree");
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

        public Criteria andExtraIsNull() {
            addCriterion("EXTRA is null");
            return (Criteria) this;
        }

        public Criteria andExtraIsNotNull() {
            addCriterion("EXTRA is not null");
            return (Criteria) this;
        }

        public Criteria andExtraEqualTo(String value) {
            addCriterion("EXTRA =", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotEqualTo(String value) {
            addCriterion("EXTRA <>", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThan(String value) {
            addCriterion("EXTRA >", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThanOrEqualTo(String value) {
            addCriterion("EXTRA >=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThan(String value) {
            addCriterion("EXTRA <", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThanOrEqualTo(String value) {
            addCriterion("EXTRA <=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLike(String value) {
            addCriterion("EXTRA like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotLike(String value) {
            addCriterion("EXTRA not like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraIn(List<String> values) {
            addCriterion("EXTRA in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotIn(List<String> values) {
            addCriterion("EXTRA not in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraBetween(String value1, String value2) {
            addCriterion("EXTRA between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotBetween(String value1, String value2) {
            addCriterion("EXTRA not between", value1, value2, "extra");
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