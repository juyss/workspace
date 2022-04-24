package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.TrainCourse;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.Date;

/**
 * train_course 培训表
 *
 * @author xh
 * @since 2020/10/20
 */
public class TrainCourseExample extends AbstractExample<TrainCourse> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public TrainCourseExample() {
        super(TrainCourse.class);
        this.ORDER_BY = new OrderBy(this, propertyMap);
    }

    /**
     * and
     *
     * @return
     */
    @Override
    public Criteria and() {
        Criteria criteria = new Criteria(propertyMap, exists, notNull);
        criteria.setAndOr("and");
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * order by
     *
     * @return
     */
    public OrderBy orderBy() {
        return ORDER_BY;
    }

    /**
     * order by
     */
    public static class OrderBy extends Example.OrderBy {

        public OrderBy(Example example, Map<String, EntityColumn> propertyMap) {
            super(example, propertyMap);
        }

        /**
         * desc
         *
         * @return
         */
        @Override
        public OrderBy desc() {
            super.desc();
            return this;
        }

        /**
         * asc
         *
         * @return
         */
        @Override
        public OrderBy asc() {
            super.asc();
            return this;
        }
        
        /**
         * order by id
         *
         * @return
         */
        public OrderBy id() {
            this.orderBy("id");
            return this;
        }
        
        /**
         * order by course_name
         *
         * @return
         */
        public OrderBy courseName() {
            this.orderBy("courseName");
            return this;
        }
        
        /**
         * order by teacher
         *
         * @return
         */
        public OrderBy teacher() {
            this.orderBy("teacher");
            return this;
        }
        
        /**
         * order by start_time
         *
         * @return
         */
        public OrderBy startTime() {
            this.orderBy("startTime");
            return this;
        }
        
        /**
         * order by end_time
         *
         * @return
         */
        public OrderBy endTime() {
            this.orderBy("endTime");
            return this;
        }
        
        /**
         * order by sign_up_start_time
         *
         * @return
         */
        public OrderBy signUpStartTime() {
            this.orderBy("signUpStartTime");
            return this;
        }
        
        /**
         * order by place
         *
         * @return
         */
        public OrderBy place() {
            this.orderBy("place");
            return this;
        }
        
        /**
         * order by sign_up_end_time
         *
         * @return
         */
        public OrderBy signUpEndTime() {
            this.orderBy("signUpEndTime");
            return this;
        }
        
        /**
         * order by audit_user_id
         *
         * @return
         */
        public OrderBy auditUserId() {
            this.orderBy("auditUserId");
            return this;
        }
        
        /**
         * order by audit_user_name
         *
         * @return
         */
        public OrderBy auditUserName() {
            this.orderBy("auditUserName");
            return this;
        }
        
        /**
         * order by creator
         *
         * @return
         */
        public OrderBy creator() {
            this.orderBy("creator");
            return this;
        }
        
        /**
         * order by create_date
         *
         * @return
         */
        public OrderBy createDate() {
            this.orderBy("createDate");
            return this;
        }
        
        /**
         * order by modifier
         *
         * @return
         */
        public OrderBy modifier() {
            this.orderBy("modifier");
            return this;
        }
        
        /**
         * order by modify_date
         *
         * @return
         */
        public OrderBy modifyDate() {
            this.orderBy("modifyDate");
            return this;
        }
        
        /**
         * order by del_flag
         *
         * @return
         */
        public OrderBy delFlag() {
            this.orderBy("delFlag");
            return this;
        }
        
        /**
         * order by application_code
         *
         * @return
         */
        public OrderBy applicationCode() {
            this.orderBy("applicationCode");
            return this;
        }
        
        /**
         * order by course_introduce
         *
         * @return
         */
        public OrderBy courseIntroduce() {
            this.orderBy("courseIntroduce");
            return this;
        }
        
        /**
         * order by tenant_code
         *
         * @return
         */
        public OrderBy tenantCode() {
            this.orderBy("tenantCode");
            return this;
        }
        
        /**
         * order by status
         *
         * @return
         */
        public OrderBy status() {
            this.orderBy("status");
            return this;
        }
        
    }

    /**
     * where
     */
    public static class Criteria extends Example.Criteria {

        public Criteria(Map<String, EntityColumn> propertyMap, boolean exists, boolean notNull) {
            super(propertyMap, exists, notNull);
        }

        /**
         * and id = value
         *
         * @return
         */
        public Criteria andIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("id", value);
            return this;
        }

        /**
         * and id != value
         *
         * @return
         */
        public Criteria andIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("id", value);
            return this;
        }

        /**
         * and id > value
         *
         * @return
         */
        public Criteria andIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("id", value);
            return this;
        }

        /**
         * and id >= value
         *
         * @return
         */
        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("id", value);
            return this;
        }

        /**
         * and id < value
         *
         * @return
         */
        public Criteria andIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("id", value);
            return this;
        }

        /**
         * and id <= value
         *
         * @return
         */
        public Criteria andIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("id", value);
            return this;
        }

        /**
         * and id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("id", values);
            return this;
        }

        /**
         * and id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("id", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("id", values);
            return this;
        }

        /**
         * and id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("id", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and id between value1 and value2
         *
         * @return
         */
        public Criteria andIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("id", value1, value2);
            return this;
        }

        /**
         * and id not between value1 and value2
         *
         * @return
         */
        public Criteria andIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("id", value1, value2);
            return this;
        }

        /**
         * and id like value
         *
         * @return
         */
        public Criteria andIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("id", value);
            return this;
        }

        /**
         * and id not like value
         *
         * @return
         */
        public Criteria andIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("id", value);
            return this;
        }

        /**
         * or id = value
         *
         * @return
         */
        public Criteria orIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("id", value);
            return this;
        }

        /**
         * or id != value
         *
         * @return
         */
        public Criteria orIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("id", value);
            return this;
        }

        /**
         * or id > value
         *
         * @return
         */
        public Criteria orIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("id", value);
            return this;
        }

        /**
         * or id >= value
         *
         * @return
         */
        public Criteria orIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("id", value);
            return this;
        }

        /**
         * or id < value
         *
         * @return
         */
        public Criteria orIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("id", value);
            return this;
        }

        /**
         * or id <= value
         *
         * @return
         */
        public Criteria orIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("id", value);
            return this;
        }

        /**
         * or id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("id", values);
            return this;
        }

        /**
         * or id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("id", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("id", values);
            return this;
        }

        /**
         * or id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("id", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or id between value1 and value2
         *
         * @return
         */
        public Criteria orIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("id", value1, value2);
            return this;
        }

        /**
         * or id not between value1 and value2
         *
         * @return
         */
        public Criteria orIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("id", value1, value2);
            return this;
        }

        /**
         * or id like value
         *
         * @return
         */
        public Criteria orIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("id", value);
            return this;
        }

        /**
         * or id not like value
         *
         * @return
         */
        public Criteria orIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("id", value);
            return this;
        }

        /**
         * and course_name = ''
         *
         * @return
         */
        public Criteria andCourseNameIsEmpty() {
            this.andEqualTo("courseName", "");
            return this;
        }

        /**
         * and course_name != ''
         *
         * @return
         */
        public Criteria andCourseNameIsNotEmpty() {
            this.andNotEqualTo("courseName", "");
            return this;
        }

        /**
         * and course_name = value
         *
         * @return
         */
        public Criteria andCourseNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("courseName", value);
            return this;
        }

        /**
         * and course_name != value
         *
         * @return
         */
        public Criteria andCourseNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("courseName", value);
            return this;
        }

        /**
         * and course_name > value
         *
         * @return
         */
        public Criteria andCourseNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("courseName", value);
            return this;
        }

        /**
         * and course_name >= value
         *
         * @return
         */
        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("courseName", value);
            return this;
        }

        /**
         * and course_name < value
         *
         * @return
         */
        public Criteria andCourseNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("courseName", value);
            return this;
        }

        /**
         * and course_name <= value
         *
         * @return
         */
        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("courseName", value);
            return this;
        }

        /**
         * and course_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseName", values);
            return this;
        }

        /**
         * and course_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseName", values);
            return this;
        }

        /**
         * and course_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_name between value1 and value2
         *
         * @return
         */
        public Criteria andCourseNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("courseName", value1, value2);
            return this;
        }

        /**
         * and course_name not between value1 and value2
         *
         * @return
         */
        public Criteria andCourseNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("courseName", value1, value2);
            return this;
        }

        /**
         * and course_name like value
         *
         * @return
         */
        public Criteria andCourseNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("courseName", value);
            return this;
        }

        /**
         * and course_name not like value
         *
         * @return
         */
        public Criteria andCourseNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("courseName", value);
            return this;
        }

        /**
         * or course_name = ''
         *
         * @return
         */
        public Criteria orCourseNameIsEmpty() {
            this.orEqualTo("courseName", "");
            return this;
        }

        /**
         * or course_name != ''
         *
         * @return
         */
        public Criteria orCourseNameIsNotEmpty() {
            this.orNotEqualTo("courseName", "");
            return this;
        }

        /**
         * or course_name = value
         *
         * @return
         */
        public Criteria orCourseNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("courseName", value);
            return this;
        }

        /**
         * or course_name != value
         *
         * @return
         */
        public Criteria orCourseNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("courseName", value);
            return this;
        }

        /**
         * or course_name > value
         *
         * @return
         */
        public Criteria orCourseNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("courseName", value);
            return this;
        }

        /**
         * or course_name >= value
         *
         * @return
         */
        public Criteria orCourseNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("courseName", value);
            return this;
        }

        /**
         * or course_name < value
         *
         * @return
         */
        public Criteria orCourseNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("courseName", value);
            return this;
        }

        /**
         * or course_name <= value
         *
         * @return
         */
        public Criteria orCourseNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("courseName", value);
            return this;
        }

        /**
         * or course_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseName", values);
            return this;
        }

        /**
         * or course_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseName", values);
            return this;
        }

        /**
         * or course_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_name between value1 and value2
         *
         * @return
         */
        public Criteria orCourseNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("courseName", value1, value2);
            return this;
        }

        /**
         * or course_name not between value1 and value2
         *
         * @return
         */
        public Criteria orCourseNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("courseName", value1, value2);
            return this;
        }

        /**
         * or course_name like value
         *
         * @return
         */
        public Criteria orCourseNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("courseName", value);
            return this;
        }

        /**
         * or course_name not like value
         *
         * @return
         */
        public Criteria orCourseNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("courseName", value);
            return this;
        }

        /**
         * and teacher = ''
         *
         * @return
         */
        public Criteria andTeacherIsEmpty() {
            this.andEqualTo("teacher", "");
            return this;
        }

        /**
         * and teacher != ''
         *
         * @return
         */
        public Criteria andTeacherIsNotEmpty() {
            this.andNotEqualTo("teacher", "");
            return this;
        }

        /**
         * and teacher = value
         *
         * @return
         */
        public Criteria andTeacherEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("teacher", value);
            return this;
        }

        /**
         * and teacher != value
         *
         * @return
         */
        public Criteria andTeacherNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("teacher", value);
            return this;
        }

        /**
         * and teacher > value
         *
         * @return
         */
        public Criteria andTeacherGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("teacher", value);
            return this;
        }

        /**
         * and teacher >= value
         *
         * @return
         */
        public Criteria andTeacherGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("teacher", value);
            return this;
        }

        /**
         * and teacher < value
         *
         * @return
         */
        public Criteria andTeacherLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("teacher", value);
            return this;
        }

        /**
         * and teacher <= value
         *
         * @return
         */
        public Criteria andTeacherLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("teacher", value);
            return this;
        }

        /**
         * and teacher in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTeacherIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("teacher", values);
            return this;
        }

        /**
         * and teacher in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTeacherIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("teacher", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and teacher not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTeacherNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("teacher", values);
            return this;
        }

        /**
         * and teacher not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTeacherNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("teacher", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and teacher between value1 and value2
         *
         * @return
         */
        public Criteria andTeacherBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("teacher", value1, value2);
            return this;
        }

        /**
         * and teacher not between value1 and value2
         *
         * @return
         */
        public Criteria andTeacherNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("teacher", value1, value2);
            return this;
        }

        /**
         * and teacher like value
         *
         * @return
         */
        public Criteria andTeacherLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("teacher", value);
            return this;
        }

        /**
         * and teacher not like value
         *
         * @return
         */
        public Criteria andTeacherNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("teacher", value);
            return this;
        }

        /**
         * or teacher = ''
         *
         * @return
         */
        public Criteria orTeacherIsEmpty() {
            this.orEqualTo("teacher", "");
            return this;
        }

        /**
         * or teacher != ''
         *
         * @return
         */
        public Criteria orTeacherIsNotEmpty() {
            this.orNotEqualTo("teacher", "");
            return this;
        }

        /**
         * or teacher = value
         *
         * @return
         */
        public Criteria orTeacherEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("teacher", value);
            return this;
        }

        /**
         * or teacher != value
         *
         * @return
         */
        public Criteria orTeacherNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("teacher", value);
            return this;
        }

        /**
         * or teacher > value
         *
         * @return
         */
        public Criteria orTeacherGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("teacher", value);
            return this;
        }

        /**
         * or teacher >= value
         *
         * @return
         */
        public Criteria orTeacherGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("teacher", value);
            return this;
        }

        /**
         * or teacher < value
         *
         * @return
         */
        public Criteria orTeacherLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("teacher", value);
            return this;
        }

        /**
         * or teacher <= value
         *
         * @return
         */
        public Criteria orTeacherLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("teacher", value);
            return this;
        }

        /**
         * or teacher in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTeacherIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("teacher", values);
            return this;
        }

        /**
         * or teacher in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTeacherIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("teacher", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or teacher not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTeacherNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("teacher", values);
            return this;
        }

        /**
         * or teacher not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTeacherNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("teacher", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or teacher between value1 and value2
         *
         * @return
         */
        public Criteria orTeacherBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("teacher", value1, value2);
            return this;
        }

        /**
         * or teacher not between value1 and value2
         *
         * @return
         */
        public Criteria orTeacherNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("teacher", value1, value2);
            return this;
        }

        /**
         * or teacher like value
         *
         * @return
         */
        public Criteria orTeacherLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("teacher", value);
            return this;
        }

        /**
         * or teacher not like value
         *
         * @return
         */
        public Criteria orTeacherNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("teacher", value);
            return this;
        }

        /**
         * and start_time = value
         *
         * @return
         */
        public Criteria andStartTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("startTime", value);
            return this;
        }

        /**
         * and start_time != value
         *
         * @return
         */
        public Criteria andStartTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("startTime", value);
            return this;
        }

        /**
         * and start_time > value
         *
         * @return
         */
        public Criteria andStartTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("startTime", value);
            return this;
        }

        /**
         * and start_time >= value
         *
         * @return
         */
        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("startTime", value);
            return this;
        }

        /**
         * and start_time < value
         *
         * @return
         */
        public Criteria andStartTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("startTime", value);
            return this;
        }

        /**
         * and start_time <= value
         *
         * @return
         */
        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("startTime", value);
            return this;
        }

        /**
         * and start_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStartTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("startTime", values);
            return this;
        }

        /**
         * and start_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStartTimeIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("startTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and start_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStartTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("startTime", values);
            return this;
        }

        /**
         * and start_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStartTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("startTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and start_time between value1 and value2
         *
         * @return
         */
        public Criteria andStartTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("startTime", value1, value2);
            return this;
        }

        /**
         * and start_time not between value1 and value2
         *
         * @return
         */
        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("startTime", value1, value2);
            return this;
        }

        /**
         * and start_time like value
         *
         * @return
         */
        public Criteria andStartTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("startTime", value);
            return this;
        }

        /**
         * and start_time not like value
         *
         * @return
         */
        public Criteria andStartTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("startTime", value);
            return this;
        }

        /**
         * or start_time = value
         *
         * @return
         */
        public Criteria orStartTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("startTime", value);
            return this;
        }

        /**
         * or start_time != value
         *
         * @return
         */
        public Criteria orStartTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("startTime", value);
            return this;
        }

        /**
         * or start_time > value
         *
         * @return
         */
        public Criteria orStartTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("startTime", value);
            return this;
        }

        /**
         * or start_time >= value
         *
         * @return
         */
        public Criteria orStartTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("startTime", value);
            return this;
        }

        /**
         * or start_time < value
         *
         * @return
         */
        public Criteria orStartTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("startTime", value);
            return this;
        }

        /**
         * or start_time <= value
         *
         * @return
         */
        public Criteria orStartTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("startTime", value);
            return this;
        }

        /**
         * or start_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStartTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("startTime", values);
            return this;
        }

        /**
         * or start_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStartTimeIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("startTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or start_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStartTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("startTime", values);
            return this;
        }

        /**
         * or start_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStartTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("startTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or start_time between value1 and value2
         *
         * @return
         */
        public Criteria orStartTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("startTime", value1, value2);
            return this;
        }

        /**
         * or start_time not between value1 and value2
         *
         * @return
         */
        public Criteria orStartTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("startTime", value1, value2);
            return this;
        }

        /**
         * or start_time like value
         *
         * @return
         */
        public Criteria orStartTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("startTime", value);
            return this;
        }

        /**
         * or start_time not like value
         *
         * @return
         */
        public Criteria orStartTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("startTime", value);
            return this;
        }

        /**
         * and end_time = value
         *
         * @return
         */
        public Criteria andEndTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("endTime", value);
            return this;
        }

        /**
         * and end_time != value
         *
         * @return
         */
        public Criteria andEndTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("endTime", value);
            return this;
        }

        /**
         * and end_time > value
         *
         * @return
         */
        public Criteria andEndTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("endTime", value);
            return this;
        }

        /**
         * and end_time >= value
         *
         * @return
         */
        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("endTime", value);
            return this;
        }

        /**
         * and end_time < value
         *
         * @return
         */
        public Criteria andEndTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("endTime", value);
            return this;
        }

        /**
         * and end_time <= value
         *
         * @return
         */
        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("endTime", value);
            return this;
        }

        /**
         * and end_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andEndTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("endTime", values);
            return this;
        }

        /**
         * and end_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andEndTimeIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("endTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and end_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andEndTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("endTime", values);
            return this;
        }

        /**
         * and end_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andEndTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("endTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and end_time between value1 and value2
         *
         * @return
         */
        public Criteria andEndTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("endTime", value1, value2);
            return this;
        }

        /**
         * and end_time not between value1 and value2
         *
         * @return
         */
        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("endTime", value1, value2);
            return this;
        }

        /**
         * and end_time like value
         *
         * @return
         */
        public Criteria andEndTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("endTime", value);
            return this;
        }

        /**
         * and end_time not like value
         *
         * @return
         */
        public Criteria andEndTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("endTime", value);
            return this;
        }

        /**
         * or end_time = value
         *
         * @return
         */
        public Criteria orEndTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("endTime", value);
            return this;
        }

        /**
         * or end_time != value
         *
         * @return
         */
        public Criteria orEndTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("endTime", value);
            return this;
        }

        /**
         * or end_time > value
         *
         * @return
         */
        public Criteria orEndTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("endTime", value);
            return this;
        }

        /**
         * or end_time >= value
         *
         * @return
         */
        public Criteria orEndTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("endTime", value);
            return this;
        }

        /**
         * or end_time < value
         *
         * @return
         */
        public Criteria orEndTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("endTime", value);
            return this;
        }

        /**
         * or end_time <= value
         *
         * @return
         */
        public Criteria orEndTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("endTime", value);
            return this;
        }

        /**
         * or end_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orEndTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("endTime", values);
            return this;
        }

        /**
         * or end_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orEndTimeIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("endTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or end_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orEndTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("endTime", values);
            return this;
        }

        /**
         * or end_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orEndTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("endTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or end_time between value1 and value2
         *
         * @return
         */
        public Criteria orEndTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("endTime", value1, value2);
            return this;
        }

        /**
         * or end_time not between value1 and value2
         *
         * @return
         */
        public Criteria orEndTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("endTime", value1, value2);
            return this;
        }

        /**
         * or end_time like value
         *
         * @return
         */
        public Criteria orEndTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("endTime", value);
            return this;
        }

        /**
         * or end_time not like value
         *
         * @return
         */
        public Criteria orEndTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("endTime", value);
            return this;
        }

        /**
         * and sign_up_start_time = value
         *
         * @return
         */
        public Criteria andSignUpStartTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("signUpStartTime", value);
            return this;
        }

        /**
         * and sign_up_start_time != value
         *
         * @return
         */
        public Criteria andSignUpStartTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("signUpStartTime", value);
            return this;
        }

        /**
         * and sign_up_start_time > value
         *
         * @return
         */
        public Criteria andSignUpStartTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("signUpStartTime", value);
            return this;
        }

        /**
         * and sign_up_start_time >= value
         *
         * @return
         */
        public Criteria andSignUpStartTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("signUpStartTime", value);
            return this;
        }

        /**
         * and sign_up_start_time < value
         *
         * @return
         */
        public Criteria andSignUpStartTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("signUpStartTime", value);
            return this;
        }

        /**
         * and sign_up_start_time <= value
         *
         * @return
         */
        public Criteria andSignUpStartTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("signUpStartTime", value);
            return this;
        }

        /**
         * and sign_up_start_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSignUpStartTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("signUpStartTime", values);
            return this;
        }

        /**
         * and sign_up_start_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSignUpStartTimeIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("signUpStartTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and sign_up_start_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSignUpStartTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("signUpStartTime", values);
            return this;
        }

        /**
         * and sign_up_start_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSignUpStartTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("signUpStartTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and sign_up_start_time between value1 and value2
         *
         * @return
         */
        public Criteria andSignUpStartTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("signUpStartTime", value1, value2);
            return this;
        }

        /**
         * and sign_up_start_time not between value1 and value2
         *
         * @return
         */
        public Criteria andSignUpStartTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("signUpStartTime", value1, value2);
            return this;
        }

        /**
         * and sign_up_start_time like value
         *
         * @return
         */
        public Criteria andSignUpStartTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("signUpStartTime", value);
            return this;
        }

        /**
         * and sign_up_start_time not like value
         *
         * @return
         */
        public Criteria andSignUpStartTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("signUpStartTime", value);
            return this;
        }

        /**
         * or sign_up_start_time = value
         *
         * @return
         */
        public Criteria orSignUpStartTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("signUpStartTime", value);
            return this;
        }

        /**
         * or sign_up_start_time != value
         *
         * @return
         */
        public Criteria orSignUpStartTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("signUpStartTime", value);
            return this;
        }

        /**
         * or sign_up_start_time > value
         *
         * @return
         */
        public Criteria orSignUpStartTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("signUpStartTime", value);
            return this;
        }

        /**
         * or sign_up_start_time >= value
         *
         * @return
         */
        public Criteria orSignUpStartTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("signUpStartTime", value);
            return this;
        }

        /**
         * or sign_up_start_time < value
         *
         * @return
         */
        public Criteria orSignUpStartTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("signUpStartTime", value);
            return this;
        }

        /**
         * or sign_up_start_time <= value
         *
         * @return
         */
        public Criteria orSignUpStartTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("signUpStartTime", value);
            return this;
        }

        /**
         * or sign_up_start_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSignUpStartTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("signUpStartTime", values);
            return this;
        }

        /**
         * or sign_up_start_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSignUpStartTimeIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("signUpStartTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or sign_up_start_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSignUpStartTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("signUpStartTime", values);
            return this;
        }

        /**
         * or sign_up_start_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSignUpStartTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("signUpStartTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or sign_up_start_time between value1 and value2
         *
         * @return
         */
        public Criteria orSignUpStartTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("signUpStartTime", value1, value2);
            return this;
        }

        /**
         * or sign_up_start_time not between value1 and value2
         *
         * @return
         */
        public Criteria orSignUpStartTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("signUpStartTime", value1, value2);
            return this;
        }

        /**
         * or sign_up_start_time like value
         *
         * @return
         */
        public Criteria orSignUpStartTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("signUpStartTime", value);
            return this;
        }

        /**
         * or sign_up_start_time not like value
         *
         * @return
         */
        public Criteria orSignUpStartTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("signUpStartTime", value);
            return this;
        }

        /**
         * and place = ''
         *
         * @return
         */
        public Criteria andPlaceIsEmpty() {
            this.andEqualTo("place", "");
            return this;
        }

        /**
         * and place != ''
         *
         * @return
         */
        public Criteria andPlaceIsNotEmpty() {
            this.andNotEqualTo("place", "");
            return this;
        }

        /**
         * and place = value
         *
         * @return
         */
        public Criteria andPlaceEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("place", value);
            return this;
        }

        /**
         * and place != value
         *
         * @return
         */
        public Criteria andPlaceNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("place", value);
            return this;
        }

        /**
         * and place > value
         *
         * @return
         */
        public Criteria andPlaceGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("place", value);
            return this;
        }

        /**
         * and place >= value
         *
         * @return
         */
        public Criteria andPlaceGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("place", value);
            return this;
        }

        /**
         * and place < value
         *
         * @return
         */
        public Criteria andPlaceLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("place", value);
            return this;
        }

        /**
         * and place <= value
         *
         * @return
         */
        public Criteria andPlaceLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("place", value);
            return this;
        }

        /**
         * and place in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlaceIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("place", values);
            return this;
        }

        /**
         * and place in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlaceIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("place", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and place not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlaceNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("place", values);
            return this;
        }

        /**
         * and place not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlaceNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("place", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and place between value1 and value2
         *
         * @return
         */
        public Criteria andPlaceBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("place", value1, value2);
            return this;
        }

        /**
         * and place not between value1 and value2
         *
         * @return
         */
        public Criteria andPlaceNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("place", value1, value2);
            return this;
        }

        /**
         * and place like value
         *
         * @return
         */
        public Criteria andPlaceLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("place", value);
            return this;
        }

        /**
         * and place not like value
         *
         * @return
         */
        public Criteria andPlaceNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("place", value);
            return this;
        }

        /**
         * or place = ''
         *
         * @return
         */
        public Criteria orPlaceIsEmpty() {
            this.orEqualTo("place", "");
            return this;
        }

        /**
         * or place != ''
         *
         * @return
         */
        public Criteria orPlaceIsNotEmpty() {
            this.orNotEqualTo("place", "");
            return this;
        }

        /**
         * or place = value
         *
         * @return
         */
        public Criteria orPlaceEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("place", value);
            return this;
        }

        /**
         * or place != value
         *
         * @return
         */
        public Criteria orPlaceNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("place", value);
            return this;
        }

        /**
         * or place > value
         *
         * @return
         */
        public Criteria orPlaceGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("place", value);
            return this;
        }

        /**
         * or place >= value
         *
         * @return
         */
        public Criteria orPlaceGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("place", value);
            return this;
        }

        /**
         * or place < value
         *
         * @return
         */
        public Criteria orPlaceLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("place", value);
            return this;
        }

        /**
         * or place <= value
         *
         * @return
         */
        public Criteria orPlaceLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("place", value);
            return this;
        }

        /**
         * or place in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlaceIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("place", values);
            return this;
        }

        /**
         * or place in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlaceIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("place", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or place not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlaceNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("place", values);
            return this;
        }

        /**
         * or place not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlaceNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("place", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or place between value1 and value2
         *
         * @return
         */
        public Criteria orPlaceBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("place", value1, value2);
            return this;
        }

        /**
         * or place not between value1 and value2
         *
         * @return
         */
        public Criteria orPlaceNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("place", value1, value2);
            return this;
        }

        /**
         * or place like value
         *
         * @return
         */
        public Criteria orPlaceLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("place", value);
            return this;
        }

        /**
         * or place not like value
         *
         * @return
         */
        public Criteria orPlaceNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("place", value);
            return this;
        }

        /**
         * and sign_up_end_time = value
         *
         * @return
         */
        public Criteria andSignUpEndTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("signUpEndTime", value);
            return this;
        }

        /**
         * and sign_up_end_time != value
         *
         * @return
         */
        public Criteria andSignUpEndTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("signUpEndTime", value);
            return this;
        }

        /**
         * and sign_up_end_time > value
         *
         * @return
         */
        public Criteria andSignUpEndTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("signUpEndTime", value);
            return this;
        }

        /**
         * and sign_up_end_time >= value
         *
         * @return
         */
        public Criteria andSignUpEndTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("signUpEndTime", value);
            return this;
        }

        /**
         * and sign_up_end_time < value
         *
         * @return
         */
        public Criteria andSignUpEndTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("signUpEndTime", value);
            return this;
        }

        /**
         * and sign_up_end_time <= value
         *
         * @return
         */
        public Criteria andSignUpEndTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("signUpEndTime", value);
            return this;
        }

        /**
         * and sign_up_end_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSignUpEndTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("signUpEndTime", values);
            return this;
        }

        /**
         * and sign_up_end_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSignUpEndTimeIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("signUpEndTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and sign_up_end_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSignUpEndTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("signUpEndTime", values);
            return this;
        }

        /**
         * and sign_up_end_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSignUpEndTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("signUpEndTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and sign_up_end_time between value1 and value2
         *
         * @return
         */
        public Criteria andSignUpEndTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("signUpEndTime", value1, value2);
            return this;
        }

        /**
         * and sign_up_end_time not between value1 and value2
         *
         * @return
         */
        public Criteria andSignUpEndTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("signUpEndTime", value1, value2);
            return this;
        }

        /**
         * and sign_up_end_time like value
         *
         * @return
         */
        public Criteria andSignUpEndTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("signUpEndTime", value);
            return this;
        }

        /**
         * and sign_up_end_time not like value
         *
         * @return
         */
        public Criteria andSignUpEndTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("signUpEndTime", value);
            return this;
        }

        /**
         * or sign_up_end_time = value
         *
         * @return
         */
        public Criteria orSignUpEndTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("signUpEndTime", value);
            return this;
        }

        /**
         * or sign_up_end_time != value
         *
         * @return
         */
        public Criteria orSignUpEndTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("signUpEndTime", value);
            return this;
        }

        /**
         * or sign_up_end_time > value
         *
         * @return
         */
        public Criteria orSignUpEndTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("signUpEndTime", value);
            return this;
        }

        /**
         * or sign_up_end_time >= value
         *
         * @return
         */
        public Criteria orSignUpEndTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("signUpEndTime", value);
            return this;
        }

        /**
         * or sign_up_end_time < value
         *
         * @return
         */
        public Criteria orSignUpEndTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("signUpEndTime", value);
            return this;
        }

        /**
         * or sign_up_end_time <= value
         *
         * @return
         */
        public Criteria orSignUpEndTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("signUpEndTime", value);
            return this;
        }

        /**
         * or sign_up_end_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSignUpEndTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("signUpEndTime", values);
            return this;
        }

        /**
         * or sign_up_end_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSignUpEndTimeIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("signUpEndTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or sign_up_end_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSignUpEndTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("signUpEndTime", values);
            return this;
        }

        /**
         * or sign_up_end_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSignUpEndTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("signUpEndTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or sign_up_end_time between value1 and value2
         *
         * @return
         */
        public Criteria orSignUpEndTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("signUpEndTime", value1, value2);
            return this;
        }

        /**
         * or sign_up_end_time not between value1 and value2
         *
         * @return
         */
        public Criteria orSignUpEndTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("signUpEndTime", value1, value2);
            return this;
        }

        /**
         * or sign_up_end_time like value
         *
         * @return
         */
        public Criteria orSignUpEndTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("signUpEndTime", value);
            return this;
        }

        /**
         * or sign_up_end_time not like value
         *
         * @return
         */
        public Criteria orSignUpEndTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("signUpEndTime", value);
            return this;
        }

        /**
         * and audit_user_id = value
         *
         * @return
         */
        public Criteria andAuditUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("auditUserId", value);
            return this;
        }

        /**
         * and audit_user_id != value
         *
         * @return
         */
        public Criteria andAuditUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("auditUserId", value);
            return this;
        }

        /**
         * and audit_user_id > value
         *
         * @return
         */
        public Criteria andAuditUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("auditUserId", value);
            return this;
        }

        /**
         * and audit_user_id >= value
         *
         * @return
         */
        public Criteria andAuditUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("auditUserId", value);
            return this;
        }

        /**
         * and audit_user_id < value
         *
         * @return
         */
        public Criteria andAuditUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("auditUserId", value);
            return this;
        }

        /**
         * and audit_user_id <= value
         *
         * @return
         */
        public Criteria andAuditUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("auditUserId", value);
            return this;
        }

        /**
         * and audit_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAuditUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("auditUserId", values);
            return this;
        }

        /**
         * and audit_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAuditUserIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("auditUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and audit_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAuditUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("auditUserId", values);
            return this;
        }

        /**
         * and audit_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAuditUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("auditUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and audit_user_id between value1 and value2
         *
         * @return
         */
        public Criteria andAuditUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("auditUserId", value1, value2);
            return this;
        }

        /**
         * and audit_user_id not between value1 and value2
         *
         * @return
         */
        public Criteria andAuditUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("auditUserId", value1, value2);
            return this;
        }

        /**
         * and audit_user_id like value
         *
         * @return
         */
        public Criteria andAuditUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("auditUserId", value);
            return this;
        }

        /**
         * and audit_user_id not like value
         *
         * @return
         */
        public Criteria andAuditUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("auditUserId", value);
            return this;
        }

        /**
         * or audit_user_id = value
         *
         * @return
         */
        public Criteria orAuditUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("auditUserId", value);
            return this;
        }

        /**
         * or audit_user_id != value
         *
         * @return
         */
        public Criteria orAuditUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("auditUserId", value);
            return this;
        }

        /**
         * or audit_user_id > value
         *
         * @return
         */
        public Criteria orAuditUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("auditUserId", value);
            return this;
        }

        /**
         * or audit_user_id >= value
         *
         * @return
         */
        public Criteria orAuditUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("auditUserId", value);
            return this;
        }

        /**
         * or audit_user_id < value
         *
         * @return
         */
        public Criteria orAuditUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("auditUserId", value);
            return this;
        }

        /**
         * or audit_user_id <= value
         *
         * @return
         */
        public Criteria orAuditUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("auditUserId", value);
            return this;
        }

        /**
         * or audit_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAuditUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("auditUserId", values);
            return this;
        }

        /**
         * or audit_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAuditUserIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("auditUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or audit_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAuditUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("auditUserId", values);
            return this;
        }

        /**
         * or audit_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAuditUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("auditUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or audit_user_id between value1 and value2
         *
         * @return
         */
        public Criteria orAuditUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("auditUserId", value1, value2);
            return this;
        }

        /**
         * or audit_user_id not between value1 and value2
         *
         * @return
         */
        public Criteria orAuditUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("auditUserId", value1, value2);
            return this;
        }

        /**
         * or audit_user_id like value
         *
         * @return
         */
        public Criteria orAuditUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("auditUserId", value);
            return this;
        }

        /**
         * or audit_user_id not like value
         *
         * @return
         */
        public Criteria orAuditUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("auditUserId", value);
            return this;
        }

        /**
         * and audit_user_name = ''
         *
         * @return
         */
        public Criteria andAuditUserNameIsEmpty() {
            this.andEqualTo("auditUserName", "");
            return this;
        }

        /**
         * and audit_user_name != ''
         *
         * @return
         */
        public Criteria andAuditUserNameIsNotEmpty() {
            this.andNotEqualTo("auditUserName", "");
            return this;
        }

        /**
         * and audit_user_name = value
         *
         * @return
         */
        public Criteria andAuditUserNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("auditUserName", value);
            return this;
        }

        /**
         * and audit_user_name != value
         *
         * @return
         */
        public Criteria andAuditUserNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("auditUserName", value);
            return this;
        }

        /**
         * and audit_user_name > value
         *
         * @return
         */
        public Criteria andAuditUserNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("auditUserName", value);
            return this;
        }

        /**
         * and audit_user_name >= value
         *
         * @return
         */
        public Criteria andAuditUserNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("auditUserName", value);
            return this;
        }

        /**
         * and audit_user_name < value
         *
         * @return
         */
        public Criteria andAuditUserNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("auditUserName", value);
            return this;
        }

        /**
         * and audit_user_name <= value
         *
         * @return
         */
        public Criteria andAuditUserNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("auditUserName", value);
            return this;
        }

        /**
         * and audit_user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAuditUserNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("auditUserName", values);
            return this;
        }

        /**
         * and audit_user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAuditUserNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("auditUserName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and audit_user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAuditUserNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("auditUserName", values);
            return this;
        }

        /**
         * and audit_user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAuditUserNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("auditUserName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and audit_user_name between value1 and value2
         *
         * @return
         */
        public Criteria andAuditUserNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("auditUserName", value1, value2);
            return this;
        }

        /**
         * and audit_user_name not between value1 and value2
         *
         * @return
         */
        public Criteria andAuditUserNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("auditUserName", value1, value2);
            return this;
        }

        /**
         * and audit_user_name like value
         *
         * @return
         */
        public Criteria andAuditUserNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("auditUserName", value);
            return this;
        }

        /**
         * and audit_user_name not like value
         *
         * @return
         */
        public Criteria andAuditUserNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("auditUserName", value);
            return this;
        }

        /**
         * or audit_user_name = ''
         *
         * @return
         */
        public Criteria orAuditUserNameIsEmpty() {
            this.orEqualTo("auditUserName", "");
            return this;
        }

        /**
         * or audit_user_name != ''
         *
         * @return
         */
        public Criteria orAuditUserNameIsNotEmpty() {
            this.orNotEqualTo("auditUserName", "");
            return this;
        }

        /**
         * or audit_user_name = value
         *
         * @return
         */
        public Criteria orAuditUserNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("auditUserName", value);
            return this;
        }

        /**
         * or audit_user_name != value
         *
         * @return
         */
        public Criteria orAuditUserNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("auditUserName", value);
            return this;
        }

        /**
         * or audit_user_name > value
         *
         * @return
         */
        public Criteria orAuditUserNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("auditUserName", value);
            return this;
        }

        /**
         * or audit_user_name >= value
         *
         * @return
         */
        public Criteria orAuditUserNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("auditUserName", value);
            return this;
        }

        /**
         * or audit_user_name < value
         *
         * @return
         */
        public Criteria orAuditUserNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("auditUserName", value);
            return this;
        }

        /**
         * or audit_user_name <= value
         *
         * @return
         */
        public Criteria orAuditUserNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("auditUserName", value);
            return this;
        }

        /**
         * or audit_user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAuditUserNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("auditUserName", values);
            return this;
        }

        /**
         * or audit_user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAuditUserNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("auditUserName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or audit_user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAuditUserNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("auditUserName", values);
            return this;
        }

        /**
         * or audit_user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAuditUserNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("auditUserName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or audit_user_name between value1 and value2
         *
         * @return
         */
        public Criteria orAuditUserNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("auditUserName", value1, value2);
            return this;
        }

        /**
         * or audit_user_name not between value1 and value2
         *
         * @return
         */
        public Criteria orAuditUserNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("auditUserName", value1, value2);
            return this;
        }

        /**
         * or audit_user_name like value
         *
         * @return
         */
        public Criteria orAuditUserNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("auditUserName", value);
            return this;
        }

        /**
         * or audit_user_name not like value
         *
         * @return
         */
        public Criteria orAuditUserNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("auditUserName", value);
            return this;
        }

        /**
         * and creator = ''
         *
         * @return
         */
        public Criteria andCreatorIsEmpty() {
            this.andEqualTo("creator", "");
            return this;
        }

        /**
         * and creator != ''
         *
         * @return
         */
        public Criteria andCreatorIsNotEmpty() {
            this.andNotEqualTo("creator", "");
            return this;
        }

        /**
         * and creator = value
         *
         * @return
         */
        public Criteria andCreatorEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("creator", value);
            return this;
        }

        /**
         * and creator != value
         *
         * @return
         */
        public Criteria andCreatorNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("creator", value);
            return this;
        }

        /**
         * and creator > value
         *
         * @return
         */
        public Criteria andCreatorGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("creator", value);
            return this;
        }

        /**
         * and creator >= value
         *
         * @return
         */
        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("creator", value);
            return this;
        }

        /**
         * and creator < value
         *
         * @return
         */
        public Criteria andCreatorLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("creator", value);
            return this;
        }

        /**
         * and creator <= value
         *
         * @return
         */
        public Criteria andCreatorLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("creator", value);
            return this;
        }

        /**
         * and creator in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreatorIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("creator", values);
            return this;
        }

        /**
         * and creator in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreatorIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("creator", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and creator not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreatorNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("creator", values);
            return this;
        }

        /**
         * and creator not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreatorNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("creator", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and creator between value1 and value2
         *
         * @return
         */
        public Criteria andCreatorBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("creator", value1, value2);
            return this;
        }

        /**
         * and creator not between value1 and value2
         *
         * @return
         */
        public Criteria andCreatorNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("creator", value1, value2);
            return this;
        }

        /**
         * and creator like value
         *
         * @return
         */
        public Criteria andCreatorLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("creator", value);
            return this;
        }

        /**
         * and creator not like value
         *
         * @return
         */
        public Criteria andCreatorNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("creator", value);
            return this;
        }

        /**
         * or creator = ''
         *
         * @return
         */
        public Criteria orCreatorIsEmpty() {
            this.orEqualTo("creator", "");
            return this;
        }

        /**
         * or creator != ''
         *
         * @return
         */
        public Criteria orCreatorIsNotEmpty() {
            this.orNotEqualTo("creator", "");
            return this;
        }

        /**
         * or creator = value
         *
         * @return
         */
        public Criteria orCreatorEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("creator", value);
            return this;
        }

        /**
         * or creator != value
         *
         * @return
         */
        public Criteria orCreatorNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("creator", value);
            return this;
        }

        /**
         * or creator > value
         *
         * @return
         */
        public Criteria orCreatorGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("creator", value);
            return this;
        }

        /**
         * or creator >= value
         *
         * @return
         */
        public Criteria orCreatorGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("creator", value);
            return this;
        }

        /**
         * or creator < value
         *
         * @return
         */
        public Criteria orCreatorLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("creator", value);
            return this;
        }

        /**
         * or creator <= value
         *
         * @return
         */
        public Criteria orCreatorLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("creator", value);
            return this;
        }

        /**
         * or creator in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreatorIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("creator", values);
            return this;
        }

        /**
         * or creator in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreatorIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("creator", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or creator not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreatorNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("creator", values);
            return this;
        }

        /**
         * or creator not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreatorNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("creator", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or creator between value1 and value2
         *
         * @return
         */
        public Criteria orCreatorBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("creator", value1, value2);
            return this;
        }

        /**
         * or creator not between value1 and value2
         *
         * @return
         */
        public Criteria orCreatorNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("creator", value1, value2);
            return this;
        }

        /**
         * or creator like value
         *
         * @return
         */
        public Criteria orCreatorLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("creator", value);
            return this;
        }

        /**
         * or creator not like value
         *
         * @return
         */
        public Criteria orCreatorNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("creator", value);
            return this;
        }

        /**
         * and create_date = value
         *
         * @return
         */
        public Criteria andCreateDateEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("createDate", value);
            return this;
        }

        /**
         * and create_date != value
         *
         * @return
         */
        public Criteria andCreateDateNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("createDate", value);
            return this;
        }

        /**
         * and create_date > value
         *
         * @return
         */
        public Criteria andCreateDateGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("createDate", value);
            return this;
        }

        /**
         * and create_date >= value
         *
         * @return
         */
        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("createDate", value);
            return this;
        }

        /**
         * and create_date < value
         *
         * @return
         */
        public Criteria andCreateDateLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("createDate", value);
            return this;
        }

        /**
         * and create_date <= value
         *
         * @return
         */
        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("createDate", value);
            return this;
        }

        /**
         * and create_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateDateIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("createDate", values);
            return this;
        }

        /**
         * and create_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateDateIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("createDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and create_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateDateNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("createDate", values);
            return this;
        }

        /**
         * and create_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateDateNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("createDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and create_date between value1 and value2
         *
         * @return
         */
        public Criteria andCreateDateBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("createDate", value1, value2);
            return this;
        }

        /**
         * and create_date not between value1 and value2
         *
         * @return
         */
        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("createDate", value1, value2);
            return this;
        }

        /**
         * and create_date like value
         *
         * @return
         */
        public Criteria andCreateDateLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("createDate", value);
            return this;
        }

        /**
         * and create_date not like value
         *
         * @return
         */
        public Criteria andCreateDateNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("createDate", value);
            return this;
        }

        /**
         * or create_date = value
         *
         * @return
         */
        public Criteria orCreateDateEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("createDate", value);
            return this;
        }

        /**
         * or create_date != value
         *
         * @return
         */
        public Criteria orCreateDateNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("createDate", value);
            return this;
        }

        /**
         * or create_date > value
         *
         * @return
         */
        public Criteria orCreateDateGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("createDate", value);
            return this;
        }

        /**
         * or create_date >= value
         *
         * @return
         */
        public Criteria orCreateDateGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("createDate", value);
            return this;
        }

        /**
         * or create_date < value
         *
         * @return
         */
        public Criteria orCreateDateLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("createDate", value);
            return this;
        }

        /**
         * or create_date <= value
         *
         * @return
         */
        public Criteria orCreateDateLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("createDate", value);
            return this;
        }

        /**
         * or create_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateDateIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("createDate", values);
            return this;
        }

        /**
         * or create_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateDateIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("createDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or create_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateDateNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("createDate", values);
            return this;
        }

        /**
         * or create_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateDateNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("createDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or create_date between value1 and value2
         *
         * @return
         */
        public Criteria orCreateDateBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("createDate", value1, value2);
            return this;
        }

        /**
         * or create_date not between value1 and value2
         *
         * @return
         */
        public Criteria orCreateDateNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("createDate", value1, value2);
            return this;
        }

        /**
         * or create_date like value
         *
         * @return
         */
        public Criteria orCreateDateLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("createDate", value);
            return this;
        }

        /**
         * or create_date not like value
         *
         * @return
         */
        public Criteria orCreateDateNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("createDate", value);
            return this;
        }

        /**
         * and modifier = ''
         *
         * @return
         */
        public Criteria andModifierIsEmpty() {
            this.andEqualTo("modifier", "");
            return this;
        }

        /**
         * and modifier != ''
         *
         * @return
         */
        public Criteria andModifierIsNotEmpty() {
            this.andNotEqualTo("modifier", "");
            return this;
        }

        /**
         * and modifier = value
         *
         * @return
         */
        public Criteria andModifierEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("modifier", value);
            return this;
        }

        /**
         * and modifier != value
         *
         * @return
         */
        public Criteria andModifierNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("modifier", value);
            return this;
        }

        /**
         * and modifier > value
         *
         * @return
         */
        public Criteria andModifierGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("modifier", value);
            return this;
        }

        /**
         * and modifier >= value
         *
         * @return
         */
        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("modifier", value);
            return this;
        }

        /**
         * and modifier < value
         *
         * @return
         */
        public Criteria andModifierLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("modifier", value);
            return this;
        }

        /**
         * and modifier <= value
         *
         * @return
         */
        public Criteria andModifierLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("modifier", value);
            return this;
        }

        /**
         * and modifier in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andModifierIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("modifier", values);
            return this;
        }

        /**
         * and modifier in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andModifierIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("modifier", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and modifier not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andModifierNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("modifier", values);
            return this;
        }

        /**
         * and modifier not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andModifierNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("modifier", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and modifier between value1 and value2
         *
         * @return
         */
        public Criteria andModifierBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("modifier", value1, value2);
            return this;
        }

        /**
         * and modifier not between value1 and value2
         *
         * @return
         */
        public Criteria andModifierNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("modifier", value1, value2);
            return this;
        }

        /**
         * and modifier like value
         *
         * @return
         */
        public Criteria andModifierLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("modifier", value);
            return this;
        }

        /**
         * and modifier not like value
         *
         * @return
         */
        public Criteria andModifierNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("modifier", value);
            return this;
        }

        /**
         * or modifier = ''
         *
         * @return
         */
        public Criteria orModifierIsEmpty() {
            this.orEqualTo("modifier", "");
            return this;
        }

        /**
         * or modifier != ''
         *
         * @return
         */
        public Criteria orModifierIsNotEmpty() {
            this.orNotEqualTo("modifier", "");
            return this;
        }

        /**
         * or modifier = value
         *
         * @return
         */
        public Criteria orModifierEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("modifier", value);
            return this;
        }

        /**
         * or modifier != value
         *
         * @return
         */
        public Criteria orModifierNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("modifier", value);
            return this;
        }

        /**
         * or modifier > value
         *
         * @return
         */
        public Criteria orModifierGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("modifier", value);
            return this;
        }

        /**
         * or modifier >= value
         *
         * @return
         */
        public Criteria orModifierGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("modifier", value);
            return this;
        }

        /**
         * or modifier < value
         *
         * @return
         */
        public Criteria orModifierLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("modifier", value);
            return this;
        }

        /**
         * or modifier <= value
         *
         * @return
         */
        public Criteria orModifierLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("modifier", value);
            return this;
        }

        /**
         * or modifier in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orModifierIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("modifier", values);
            return this;
        }

        /**
         * or modifier in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orModifierIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("modifier", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or modifier not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orModifierNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("modifier", values);
            return this;
        }

        /**
         * or modifier not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orModifierNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("modifier", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or modifier between value1 and value2
         *
         * @return
         */
        public Criteria orModifierBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("modifier", value1, value2);
            return this;
        }

        /**
         * or modifier not between value1 and value2
         *
         * @return
         */
        public Criteria orModifierNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("modifier", value1, value2);
            return this;
        }

        /**
         * or modifier like value
         *
         * @return
         */
        public Criteria orModifierLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("modifier", value);
            return this;
        }

        /**
         * or modifier not like value
         *
         * @return
         */
        public Criteria orModifierNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("modifier", value);
            return this;
        }

        /**
         * and modify_date = value
         *
         * @return
         */
        public Criteria andModifyDateEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("modifyDate", value);
            return this;
        }

        /**
         * and modify_date != value
         *
         * @return
         */
        public Criteria andModifyDateNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("modifyDate", value);
            return this;
        }

        /**
         * and modify_date > value
         *
         * @return
         */
        public Criteria andModifyDateGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("modifyDate", value);
            return this;
        }

        /**
         * and modify_date >= value
         *
         * @return
         */
        public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("modifyDate", value);
            return this;
        }

        /**
         * and modify_date < value
         *
         * @return
         */
        public Criteria andModifyDateLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("modifyDate", value);
            return this;
        }

        /**
         * and modify_date <= value
         *
         * @return
         */
        public Criteria andModifyDateLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("modifyDate", value);
            return this;
        }

        /**
         * and modify_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andModifyDateIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("modifyDate", values);
            return this;
        }

        /**
         * and modify_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andModifyDateIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("modifyDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and modify_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andModifyDateNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("modifyDate", values);
            return this;
        }

        /**
         * and modify_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andModifyDateNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("modifyDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and modify_date between value1 and value2
         *
         * @return
         */
        public Criteria andModifyDateBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("modifyDate", value1, value2);
            return this;
        }

        /**
         * and modify_date not between value1 and value2
         *
         * @return
         */
        public Criteria andModifyDateNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("modifyDate", value1, value2);
            return this;
        }

        /**
         * and modify_date like value
         *
         * @return
         */
        public Criteria andModifyDateLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("modifyDate", value);
            return this;
        }

        /**
         * and modify_date not like value
         *
         * @return
         */
        public Criteria andModifyDateNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("modifyDate", value);
            return this;
        }

        /**
         * or modify_date = value
         *
         * @return
         */
        public Criteria orModifyDateEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("modifyDate", value);
            return this;
        }

        /**
         * or modify_date != value
         *
         * @return
         */
        public Criteria orModifyDateNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("modifyDate", value);
            return this;
        }

        /**
         * or modify_date > value
         *
         * @return
         */
        public Criteria orModifyDateGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("modifyDate", value);
            return this;
        }

        /**
         * or modify_date >= value
         *
         * @return
         */
        public Criteria orModifyDateGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("modifyDate", value);
            return this;
        }

        /**
         * or modify_date < value
         *
         * @return
         */
        public Criteria orModifyDateLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("modifyDate", value);
            return this;
        }

        /**
         * or modify_date <= value
         *
         * @return
         */
        public Criteria orModifyDateLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("modifyDate", value);
            return this;
        }

        /**
         * or modify_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orModifyDateIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("modifyDate", values);
            return this;
        }

        /**
         * or modify_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orModifyDateIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("modifyDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or modify_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orModifyDateNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("modifyDate", values);
            return this;
        }

        /**
         * or modify_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orModifyDateNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("modifyDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or modify_date between value1 and value2
         *
         * @return
         */
        public Criteria orModifyDateBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("modifyDate", value1, value2);
            return this;
        }

        /**
         * or modify_date not between value1 and value2
         *
         * @return
         */
        public Criteria orModifyDateNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("modifyDate", value1, value2);
            return this;
        }

        /**
         * or modify_date like value
         *
         * @return
         */
        public Criteria orModifyDateLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("modifyDate", value);
            return this;
        }

        /**
         * or modify_date not like value
         *
         * @return
         */
        public Criteria orModifyDateNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("modifyDate", value);
            return this;
        }

        /**
         * and del_flag = value
         *
         * @return
         */
        public Criteria andDelFlagEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("delFlag", value);
            return this;
        }

        /**
         * and del_flag != value
         *
         * @return
         */
        public Criteria andDelFlagNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("delFlag", value);
            return this;
        }

        /**
         * and del_flag > value
         *
         * @return
         */
        public Criteria andDelFlagGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("delFlag", value);
            return this;
        }

        /**
         * and del_flag >= value
         *
         * @return
         */
        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("delFlag", value);
            return this;
        }

        /**
         * and del_flag < value
         *
         * @return
         */
        public Criteria andDelFlagLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("delFlag", value);
            return this;
        }

        /**
         * and del_flag <= value
         *
         * @return
         */
        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("delFlag", value);
            return this;
        }

        /**
         * and del_flag in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andDelFlagIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("delFlag", values);
            return this;
        }

        /**
         * and del_flag in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andDelFlagIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("delFlag", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and del_flag not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andDelFlagNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("delFlag", values);
            return this;
        }

        /**
         * and del_flag not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andDelFlagNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("delFlag", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and del_flag between value1 and value2
         *
         * @return
         */
        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("delFlag", value1, value2);
            return this;
        }

        /**
         * and del_flag not between value1 and value2
         *
         * @return
         */
        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("delFlag", value1, value2);
            return this;
        }

        /**
         * and del_flag like value
         *
         * @return
         */
        public Criteria andDelFlagLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("delFlag", value);
            return this;
        }

        /**
         * and del_flag not like value
         *
         * @return
         */
        public Criteria andDelFlagNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("delFlag", value);
            return this;
        }

        /**
         * or del_flag = value
         *
         * @return
         */
        public Criteria orDelFlagEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("delFlag", value);
            return this;
        }

        /**
         * or del_flag != value
         *
         * @return
         */
        public Criteria orDelFlagNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("delFlag", value);
            return this;
        }

        /**
         * or del_flag > value
         *
         * @return
         */
        public Criteria orDelFlagGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("delFlag", value);
            return this;
        }

        /**
         * or del_flag >= value
         *
         * @return
         */
        public Criteria orDelFlagGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("delFlag", value);
            return this;
        }

        /**
         * or del_flag < value
         *
         * @return
         */
        public Criteria orDelFlagLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("delFlag", value);
            return this;
        }

        /**
         * or del_flag <= value
         *
         * @return
         */
        public Criteria orDelFlagLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("delFlag", value);
            return this;
        }

        /**
         * or del_flag in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orDelFlagIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("delFlag", values);
            return this;
        }

        /**
         * or del_flag in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orDelFlagIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("delFlag", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or del_flag not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orDelFlagNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("delFlag", values);
            return this;
        }

        /**
         * or del_flag not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orDelFlagNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("delFlag", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or del_flag between value1 and value2
         *
         * @return
         */
        public Criteria orDelFlagBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("delFlag", value1, value2);
            return this;
        }

        /**
         * or del_flag not between value1 and value2
         *
         * @return
         */
        public Criteria orDelFlagNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("delFlag", value1, value2);
            return this;
        }

        /**
         * or del_flag like value
         *
         * @return
         */
        public Criteria orDelFlagLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("delFlag", value);
            return this;
        }

        /**
         * or del_flag not like value
         *
         * @return
         */
        public Criteria orDelFlagNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("delFlag", value);
            return this;
        }

        /**
         * and application_code = ''
         *
         * @return
         */
        public Criteria andApplicationCodeIsEmpty() {
            this.andEqualTo("applicationCode", "");
            return this;
        }

        /**
         * and application_code != ''
         *
         * @return
         */
        public Criteria andApplicationCodeIsNotEmpty() {
            this.andNotEqualTo("applicationCode", "");
            return this;
        }

        /**
         * and application_code = value
         *
         * @return
         */
        public Criteria andApplicationCodeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("applicationCode", value);
            return this;
        }

        /**
         * and application_code != value
         *
         * @return
         */
        public Criteria andApplicationCodeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("applicationCode", value);
            return this;
        }

        /**
         * and application_code > value
         *
         * @return
         */
        public Criteria andApplicationCodeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("applicationCode", value);
            return this;
        }

        /**
         * and application_code >= value
         *
         * @return
         */
        public Criteria andApplicationCodeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("applicationCode", value);
            return this;
        }

        /**
         * and application_code < value
         *
         * @return
         */
        public Criteria andApplicationCodeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("applicationCode", value);
            return this;
        }

        /**
         * and application_code <= value
         *
         * @return
         */
        public Criteria andApplicationCodeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("applicationCode", value);
            return this;
        }

        /**
         * and application_code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andApplicationCodeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("applicationCode", values);
            return this;
        }

        /**
         * and application_code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andApplicationCodeIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("applicationCode", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and application_code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andApplicationCodeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("applicationCode", values);
            return this;
        }

        /**
         * and application_code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andApplicationCodeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("applicationCode", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and application_code between value1 and value2
         *
         * @return
         */
        public Criteria andApplicationCodeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("applicationCode", value1, value2);
            return this;
        }

        /**
         * and application_code not between value1 and value2
         *
         * @return
         */
        public Criteria andApplicationCodeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("applicationCode", value1, value2);
            return this;
        }

        /**
         * and application_code like value
         *
         * @return
         */
        public Criteria andApplicationCodeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("applicationCode", value);
            return this;
        }

        /**
         * and application_code not like value
         *
         * @return
         */
        public Criteria andApplicationCodeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("applicationCode", value);
            return this;
        }

        /**
         * or application_code = ''
         *
         * @return
         */
        public Criteria orApplicationCodeIsEmpty() {
            this.orEqualTo("applicationCode", "");
            return this;
        }

        /**
         * or application_code != ''
         *
         * @return
         */
        public Criteria orApplicationCodeIsNotEmpty() {
            this.orNotEqualTo("applicationCode", "");
            return this;
        }

        /**
         * or application_code = value
         *
         * @return
         */
        public Criteria orApplicationCodeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("applicationCode", value);
            return this;
        }

        /**
         * or application_code != value
         *
         * @return
         */
        public Criteria orApplicationCodeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("applicationCode", value);
            return this;
        }

        /**
         * or application_code > value
         *
         * @return
         */
        public Criteria orApplicationCodeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("applicationCode", value);
            return this;
        }

        /**
         * or application_code >= value
         *
         * @return
         */
        public Criteria orApplicationCodeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("applicationCode", value);
            return this;
        }

        /**
         * or application_code < value
         *
         * @return
         */
        public Criteria orApplicationCodeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("applicationCode", value);
            return this;
        }

        /**
         * or application_code <= value
         *
         * @return
         */
        public Criteria orApplicationCodeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("applicationCode", value);
            return this;
        }

        /**
         * or application_code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orApplicationCodeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("applicationCode", values);
            return this;
        }

        /**
         * or application_code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orApplicationCodeIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("applicationCode", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or application_code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orApplicationCodeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("applicationCode", values);
            return this;
        }

        /**
         * or application_code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orApplicationCodeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("applicationCode", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or application_code between value1 and value2
         *
         * @return
         */
        public Criteria orApplicationCodeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("applicationCode", value1, value2);
            return this;
        }

        /**
         * or application_code not between value1 and value2
         *
         * @return
         */
        public Criteria orApplicationCodeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("applicationCode", value1, value2);
            return this;
        }

        /**
         * or application_code like value
         *
         * @return
         */
        public Criteria orApplicationCodeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("applicationCode", value);
            return this;
        }

        /**
         * or application_code not like value
         *
         * @return
         */
        public Criteria orApplicationCodeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("applicationCode", value);
            return this;
        }

        /**
         * and course_introduce = ''
         *
         * @return
         */
        public Criteria andCourseIntroduceIsEmpty() {
            this.andEqualTo("courseIntroduce", "");
            return this;
        }

        /**
         * and course_introduce != ''
         *
         * @return
         */
        public Criteria andCourseIntroduceIsNotEmpty() {
            this.andNotEqualTo("courseIntroduce", "");
            return this;
        }

        /**
         * and course_introduce = value
         *
         * @return
         */
        public Criteria andCourseIntroduceEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("courseIntroduce", value);
            return this;
        }

        /**
         * and course_introduce != value
         *
         * @return
         */
        public Criteria andCourseIntroduceNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("courseIntroduce", value);
            return this;
        }

        /**
         * and course_introduce > value
         *
         * @return
         */
        public Criteria andCourseIntroduceGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("courseIntroduce", value);
            return this;
        }

        /**
         * and course_introduce >= value
         *
         * @return
         */
        public Criteria andCourseIntroduceGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("courseIntroduce", value);
            return this;
        }

        /**
         * and course_introduce < value
         *
         * @return
         */
        public Criteria andCourseIntroduceLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("courseIntroduce", value);
            return this;
        }

        /**
         * and course_introduce <= value
         *
         * @return
         */
        public Criteria andCourseIntroduceLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("courseIntroduce", value);
            return this;
        }

        /**
         * and course_introduce in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseIntroduceIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseIntroduce", values);
            return this;
        }

        /**
         * and course_introduce in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseIntroduceIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseIntroduce", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_introduce not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseIntroduceNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseIntroduce", values);
            return this;
        }

        /**
         * and course_introduce not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseIntroduceNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseIntroduce", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_introduce between value1 and value2
         *
         * @return
         */
        public Criteria andCourseIntroduceBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("courseIntroduce", value1, value2);
            return this;
        }

        /**
         * and course_introduce not between value1 and value2
         *
         * @return
         */
        public Criteria andCourseIntroduceNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("courseIntroduce", value1, value2);
            return this;
        }

        /**
         * and course_introduce like value
         *
         * @return
         */
        public Criteria andCourseIntroduceLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("courseIntroduce", value);
            return this;
        }

        /**
         * and course_introduce not like value
         *
         * @return
         */
        public Criteria andCourseIntroduceNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("courseIntroduce", value);
            return this;
        }

        /**
         * or course_introduce = ''
         *
         * @return
         */
        public Criteria orCourseIntroduceIsEmpty() {
            this.orEqualTo("courseIntroduce", "");
            return this;
        }

        /**
         * or course_introduce != ''
         *
         * @return
         */
        public Criteria orCourseIntroduceIsNotEmpty() {
            this.orNotEqualTo("courseIntroduce", "");
            return this;
        }

        /**
         * or course_introduce = value
         *
         * @return
         */
        public Criteria orCourseIntroduceEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("courseIntroduce", value);
            return this;
        }

        /**
         * or course_introduce != value
         *
         * @return
         */
        public Criteria orCourseIntroduceNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("courseIntroduce", value);
            return this;
        }

        /**
         * or course_introduce > value
         *
         * @return
         */
        public Criteria orCourseIntroduceGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("courseIntroduce", value);
            return this;
        }

        /**
         * or course_introduce >= value
         *
         * @return
         */
        public Criteria orCourseIntroduceGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("courseIntroduce", value);
            return this;
        }

        /**
         * or course_introduce < value
         *
         * @return
         */
        public Criteria orCourseIntroduceLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("courseIntroduce", value);
            return this;
        }

        /**
         * or course_introduce <= value
         *
         * @return
         */
        public Criteria orCourseIntroduceLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("courseIntroduce", value);
            return this;
        }

        /**
         * or course_introduce in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseIntroduceIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseIntroduce", values);
            return this;
        }

        /**
         * or course_introduce in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseIntroduceIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseIntroduce", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_introduce not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseIntroduceNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseIntroduce", values);
            return this;
        }

        /**
         * or course_introduce not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseIntroduceNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseIntroduce", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_introduce between value1 and value2
         *
         * @return
         */
        public Criteria orCourseIntroduceBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("courseIntroduce", value1, value2);
            return this;
        }

        /**
         * or course_introduce not between value1 and value2
         *
         * @return
         */
        public Criteria orCourseIntroduceNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("courseIntroduce", value1, value2);
            return this;
        }

        /**
         * or course_introduce like value
         *
         * @return
         */
        public Criteria orCourseIntroduceLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("courseIntroduce", value);
            return this;
        }

        /**
         * or course_introduce not like value
         *
         * @return
         */
        public Criteria orCourseIntroduceNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("courseIntroduce", value);
            return this;
        }

        /**
         * and tenant_code = ''
         *
         * @return
         */
        public Criteria andTenantCodeIsEmpty() {
            this.andEqualTo("tenantCode", "");
            return this;
        }

        /**
         * and tenant_code != ''
         *
         * @return
         */
        public Criteria andTenantCodeIsNotEmpty() {
            this.andNotEqualTo("tenantCode", "");
            return this;
        }

        /**
         * and tenant_code = value
         *
         * @return
         */
        public Criteria andTenantCodeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("tenantCode", value);
            return this;
        }

        /**
         * and tenant_code != value
         *
         * @return
         */
        public Criteria andTenantCodeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("tenantCode", value);
            return this;
        }

        /**
         * and tenant_code > value
         *
         * @return
         */
        public Criteria andTenantCodeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("tenantCode", value);
            return this;
        }

        /**
         * and tenant_code >= value
         *
         * @return
         */
        public Criteria andTenantCodeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("tenantCode", value);
            return this;
        }

        /**
         * and tenant_code < value
         *
         * @return
         */
        public Criteria andTenantCodeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("tenantCode", value);
            return this;
        }

        /**
         * and tenant_code <= value
         *
         * @return
         */
        public Criteria andTenantCodeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("tenantCode", value);
            return this;
        }

        /**
         * and tenant_code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTenantCodeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("tenantCode", values);
            return this;
        }

        /**
         * and tenant_code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTenantCodeIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("tenantCode", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and tenant_code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTenantCodeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("tenantCode", values);
            return this;
        }

        /**
         * and tenant_code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTenantCodeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("tenantCode", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and tenant_code between value1 and value2
         *
         * @return
         */
        public Criteria andTenantCodeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("tenantCode", value1, value2);
            return this;
        }

        /**
         * and tenant_code not between value1 and value2
         *
         * @return
         */
        public Criteria andTenantCodeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("tenantCode", value1, value2);
            return this;
        }

        /**
         * and tenant_code like value
         *
         * @return
         */
        public Criteria andTenantCodeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("tenantCode", value);
            return this;
        }

        /**
         * and tenant_code not like value
         *
         * @return
         */
        public Criteria andTenantCodeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("tenantCode", value);
            return this;
        }

        /**
         * or tenant_code = ''
         *
         * @return
         */
        public Criteria orTenantCodeIsEmpty() {
            this.orEqualTo("tenantCode", "");
            return this;
        }

        /**
         * or tenant_code != ''
         *
         * @return
         */
        public Criteria orTenantCodeIsNotEmpty() {
            this.orNotEqualTo("tenantCode", "");
            return this;
        }

        /**
         * or tenant_code = value
         *
         * @return
         */
        public Criteria orTenantCodeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("tenantCode", value);
            return this;
        }

        /**
         * or tenant_code != value
         *
         * @return
         */
        public Criteria orTenantCodeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("tenantCode", value);
            return this;
        }

        /**
         * or tenant_code > value
         *
         * @return
         */
        public Criteria orTenantCodeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("tenantCode", value);
            return this;
        }

        /**
         * or tenant_code >= value
         *
         * @return
         */
        public Criteria orTenantCodeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("tenantCode", value);
            return this;
        }

        /**
         * or tenant_code < value
         *
         * @return
         */
        public Criteria orTenantCodeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("tenantCode", value);
            return this;
        }

        /**
         * or tenant_code <= value
         *
         * @return
         */
        public Criteria orTenantCodeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("tenantCode", value);
            return this;
        }

        /**
         * or tenant_code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTenantCodeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("tenantCode", values);
            return this;
        }

        /**
         * or tenant_code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTenantCodeIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("tenantCode", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or tenant_code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTenantCodeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("tenantCode", values);
            return this;
        }

        /**
         * or tenant_code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTenantCodeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("tenantCode", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or tenant_code between value1 and value2
         *
         * @return
         */
        public Criteria orTenantCodeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("tenantCode", value1, value2);
            return this;
        }

        /**
         * or tenant_code not between value1 and value2
         *
         * @return
         */
        public Criteria orTenantCodeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("tenantCode", value1, value2);
            return this;
        }

        /**
         * or tenant_code like value
         *
         * @return
         */
        public Criteria orTenantCodeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("tenantCode", value);
            return this;
        }

        /**
         * or tenant_code not like value
         *
         * @return
         */
        public Criteria orTenantCodeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("tenantCode", value);
            return this;
        }

        /**
         * and status = value
         *
         * @return
         */
        public Criteria andStatusEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("status", value);
            return this;
        }

        /**
         * and status != value
         *
         * @return
         */
        public Criteria andStatusNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("status", value);
            return this;
        }

        /**
         * and status > value
         *
         * @return
         */
        public Criteria andStatusGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("status", value);
            return this;
        }

        /**
         * and status >= value
         *
         * @return
         */
        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("status", value);
            return this;
        }

        /**
         * and status < value
         *
         * @return
         */
        public Criteria andStatusLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("status", value);
            return this;
        }

        /**
         * and status <= value
         *
         * @return
         */
        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("status", value);
            return this;
        }

        /**
         * and status in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("status", values);
            return this;
        }

        /**
         * and status in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("status", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and status not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("status", values);
            return this;
        }

        /**
         * and status not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("status", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and status between value1 and value2
         *
         * @return
         */
        public Criteria andStatusBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("status", value1, value2);
            return this;
        }

        /**
         * and status not between value1 and value2
         *
         * @return
         */
        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("status", value1, value2);
            return this;
        }

        /**
         * and status like value
         *
         * @return
         */
        public Criteria andStatusLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("status", value);
            return this;
        }

        /**
         * and status not like value
         *
         * @return
         */
        public Criteria andStatusNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("status", value);
            return this;
        }

        /**
         * or status = value
         *
         * @return
         */
        public Criteria orStatusEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("status", value);
            return this;
        }

        /**
         * or status != value
         *
         * @return
         */
        public Criteria orStatusNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("status", value);
            return this;
        }

        /**
         * or status > value
         *
         * @return
         */
        public Criteria orStatusGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("status", value);
            return this;
        }

        /**
         * or status >= value
         *
         * @return
         */
        public Criteria orStatusGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("status", value);
            return this;
        }

        /**
         * or status < value
         *
         * @return
         */
        public Criteria orStatusLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("status", value);
            return this;
        }

        /**
         * or status <= value
         *
         * @return
         */
        public Criteria orStatusLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("status", value);
            return this;
        }

        /**
         * or status in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("status", values);
            return this;
        }

        /**
         * or status in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("status", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or status not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("status", values);
            return this;
        }

        /**
         * or status not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("status", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or status between value1 and value2
         *
         * @return
         */
        public Criteria orStatusBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("status", value1, value2);
            return this;
        }

        /**
         * or status not between value1 and value2
         *
         * @return
         */
        public Criteria orStatusNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("status", value1, value2);
            return this;
        }

        /**
         * or status like value
         *
         * @return
         */
        public Criteria orStatusLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("status", value);
            return this;
        }

        /**
         * or status not like value
         *
         * @return
         */
        public Criteria orStatusNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("status", value);
            return this;
        }

        /**
         * and deleted = 0
         *
         * @return
         */
        public Criteria andValid() {
            this.andEqualTo("delFlag", 0);
            return this;
        }

        /**
         * and deleted = 1
         *
         * @return
         */
        public Criteria andNotValid() {
            this.andEqualTo("delFlag", 1);
            return this;
        }

        /**
         * or deleted = 0
         *
         * @return
         */
        public Criteria orValid() {
            this.orEqualTo("delFlag", 0);
            return this;
        }

        /**
         * or deleted = 1
         *
         * @return
         */
        public Criteria orNotValid() {
            this.orEqualTo("delFlag", 1);
            return this;
        }

    }

}
