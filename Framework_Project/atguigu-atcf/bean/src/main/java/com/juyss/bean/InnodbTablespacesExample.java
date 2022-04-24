package com.juyss.bean;

import java.util.ArrayList;
import java.util.List;

public class InnodbTablespacesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InnodbTablespacesExample() {
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

        public Criteria andSpaceIsNull() {
            addCriterion("SPACE is null");
            return (Criteria) this;
        }

        public Criteria andSpaceIsNotNull() {
            addCriterion("SPACE is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceEqualTo(Integer value) {
            addCriterion("SPACE =", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotEqualTo(Integer value) {
            addCriterion("SPACE <>", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceGreaterThan(Integer value) {
            addCriterion("SPACE >", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPACE >=", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceLessThan(Integer value) {
            addCriterion("SPACE <", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceLessThanOrEqualTo(Integer value) {
            addCriterion("SPACE <=", value, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceIn(List<Integer> values) {
            addCriterion("SPACE in", values, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotIn(List<Integer> values) {
            addCriterion("SPACE not in", values, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceBetween(Integer value1, Integer value2) {
            addCriterion("SPACE between", value1, value2, "space");
            return (Criteria) this;
        }

        public Criteria andSpaceNotBetween(Integer value1, Integer value2) {
            addCriterion("SPACE not between", value1, value2, "space");
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

        public Criteria andPageSizeIsNull() {
            addCriterion("PAGE_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andPageSizeIsNotNull() {
            addCriterion("PAGE_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andPageSizeEqualTo(Integer value) {
            addCriterion("PAGE_SIZE =", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotEqualTo(Integer value) {
            addCriterion("PAGE_SIZE <>", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThan(Integer value) {
            addCriterion("PAGE_SIZE >", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAGE_SIZE >=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThan(Integer value) {
            addCriterion("PAGE_SIZE <", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThanOrEqualTo(Integer value) {
            addCriterion("PAGE_SIZE <=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeIn(List<Integer> values) {
            addCriterion("PAGE_SIZE in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotIn(List<Integer> values) {
            addCriterion("PAGE_SIZE not in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeBetween(Integer value1, Integer value2) {
            addCriterion("PAGE_SIZE between", value1, value2, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("PAGE_SIZE not between", value1, value2, "pageSize");
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

        public Criteria andFsBlockSizeIsNull() {
            addCriterion("FS_BLOCK_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeIsNotNull() {
            addCriterion("FS_BLOCK_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeEqualTo(Integer value) {
            addCriterion("FS_BLOCK_SIZE =", value, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeNotEqualTo(Integer value) {
            addCriterion("FS_BLOCK_SIZE <>", value, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeGreaterThan(Integer value) {
            addCriterion("FS_BLOCK_SIZE >", value, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FS_BLOCK_SIZE >=", value, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeLessThan(Integer value) {
            addCriterion("FS_BLOCK_SIZE <", value, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeLessThanOrEqualTo(Integer value) {
            addCriterion("FS_BLOCK_SIZE <=", value, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeIn(List<Integer> values) {
            addCriterion("FS_BLOCK_SIZE in", values, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeNotIn(List<Integer> values) {
            addCriterion("FS_BLOCK_SIZE not in", values, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeBetween(Integer value1, Integer value2) {
            addCriterion("FS_BLOCK_SIZE between", value1, value2, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFsBlockSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("FS_BLOCK_SIZE not between", value1, value2, "fsBlockSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("FILE_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("FILE_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(Long value) {
            addCriterion("FILE_SIZE =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(Long value) {
            addCriterion("FILE_SIZE <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(Long value) {
            addCriterion("FILE_SIZE >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("FILE_SIZE >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(Long value) {
            addCriterion("FILE_SIZE <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(Long value) {
            addCriterion("FILE_SIZE <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<Long> values) {
            addCriterion("FILE_SIZE in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<Long> values) {
            addCriterion("FILE_SIZE not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(Long value1, Long value2) {
            addCriterion("FILE_SIZE between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(Long value1, Long value2) {
            addCriterion("FILE_SIZE not between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeIsNull() {
            addCriterion("ALLOCATED_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeIsNotNull() {
            addCriterion("ALLOCATED_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeEqualTo(Long value) {
            addCriterion("ALLOCATED_SIZE =", value, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeNotEqualTo(Long value) {
            addCriterion("ALLOCATED_SIZE <>", value, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeGreaterThan(Long value) {
            addCriterion("ALLOCATED_SIZE >", value, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("ALLOCATED_SIZE >=", value, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeLessThan(Long value) {
            addCriterion("ALLOCATED_SIZE <", value, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeLessThanOrEqualTo(Long value) {
            addCriterion("ALLOCATED_SIZE <=", value, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeIn(List<Long> values) {
            addCriterion("ALLOCATED_SIZE in", values, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeNotIn(List<Long> values) {
            addCriterion("ALLOCATED_SIZE not in", values, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeBetween(Long value1, Long value2) {
            addCriterion("ALLOCATED_SIZE between", value1, value2, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andAllocatedSizeNotBetween(Long value1, Long value2) {
            addCriterion("ALLOCATED_SIZE not between", value1, value2, "allocatedSize");
            return (Criteria) this;
        }

        public Criteria andServerVersionIsNull() {
            addCriterion("SERVER_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andServerVersionIsNotNull() {
            addCriterion("SERVER_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andServerVersionEqualTo(String value) {
            addCriterion("SERVER_VERSION =", value, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionNotEqualTo(String value) {
            addCriterion("SERVER_VERSION <>", value, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionGreaterThan(String value) {
            addCriterion("SERVER_VERSION >", value, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionGreaterThanOrEqualTo(String value) {
            addCriterion("SERVER_VERSION >=", value, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionLessThan(String value) {
            addCriterion("SERVER_VERSION <", value, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionLessThanOrEqualTo(String value) {
            addCriterion("SERVER_VERSION <=", value, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionLike(String value) {
            addCriterion("SERVER_VERSION like", value, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionNotLike(String value) {
            addCriterion("SERVER_VERSION not like", value, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionIn(List<String> values) {
            addCriterion("SERVER_VERSION in", values, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionNotIn(List<String> values) {
            addCriterion("SERVER_VERSION not in", values, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionBetween(String value1, String value2) {
            addCriterion("SERVER_VERSION between", value1, value2, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andServerVersionNotBetween(String value1, String value2) {
            addCriterion("SERVER_VERSION not between", value1, value2, "serverVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionIsNull() {
            addCriterion("SPACE_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionIsNotNull() {
            addCriterion("SPACE_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionEqualTo(Integer value) {
            addCriterion("SPACE_VERSION =", value, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionNotEqualTo(Integer value) {
            addCriterion("SPACE_VERSION <>", value, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionGreaterThan(Integer value) {
            addCriterion("SPACE_VERSION >", value, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPACE_VERSION >=", value, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionLessThan(Integer value) {
            addCriterion("SPACE_VERSION <", value, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionLessThanOrEqualTo(Integer value) {
            addCriterion("SPACE_VERSION <=", value, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionIn(List<Integer> values) {
            addCriterion("SPACE_VERSION in", values, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionNotIn(List<Integer> values) {
            addCriterion("SPACE_VERSION not in", values, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionBetween(Integer value1, Integer value2) {
            addCriterion("SPACE_VERSION between", value1, value2, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andSpaceVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("SPACE_VERSION not between", value1, value2, "spaceVersion");
            return (Criteria) this;
        }

        public Criteria andEncryptionIsNull() {
            addCriterion("ENCRYPTION is null");
            return (Criteria) this;
        }

        public Criteria andEncryptionIsNotNull() {
            addCriterion("ENCRYPTION is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptionEqualTo(String value) {
            addCriterion("ENCRYPTION =", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionNotEqualTo(String value) {
            addCriterion("ENCRYPTION <>", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionGreaterThan(String value) {
            addCriterion("ENCRYPTION >", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionGreaterThanOrEqualTo(String value) {
            addCriterion("ENCRYPTION >=", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionLessThan(String value) {
            addCriterion("ENCRYPTION <", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionLessThanOrEqualTo(String value) {
            addCriterion("ENCRYPTION <=", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionLike(String value) {
            addCriterion("ENCRYPTION like", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionNotLike(String value) {
            addCriterion("ENCRYPTION not like", value, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionIn(List<String> values) {
            addCriterion("ENCRYPTION in", values, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionNotIn(List<String> values) {
            addCriterion("ENCRYPTION not in", values, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionBetween(String value1, String value2) {
            addCriterion("ENCRYPTION between", value1, value2, "encryption");
            return (Criteria) this;
        }

        public Criteria andEncryptionNotBetween(String value1, String value2) {
            addCriterion("ENCRYPTION not between", value1, value2, "encryption");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
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