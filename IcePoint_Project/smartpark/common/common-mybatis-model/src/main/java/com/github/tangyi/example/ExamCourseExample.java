package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.ExamCourse;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.Date;

/**
 * exam_course 课程表
 *
 * @author xh
 * @since 2020/12/12
 */
public class ExamCourseExample extends AbstractExample<ExamCourse> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public ExamCourseExample() {
        super(ExamCourse.class);
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
         * order by college
         *
         * @return
         */
        public OrderBy college() {
            this.orderBy("college");
            return this;
        }
        
        /**
         * order by major
         *
         * @return
         */
        public OrderBy major() {
            this.orderBy("major");
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
         * order by course_introduce
         *
         * @return
         */
        public OrderBy courseIntroduce() {
            this.orderBy("courseIntroduce");
            return this;
        }
        
        /**
         * order by course_description
         *
         * @return
         */
        public OrderBy courseDescription() {
            this.orderBy("courseDescription");
            return this;
        }
        
        /**
         * order by course_type
         *
         * @return
         */
        public OrderBy courseType() {
            this.orderBy("courseType");
            return this;
        }
        
        /**
         * order by attachment_id
         *
         * @return
         */
        public OrderBy attachmentId() {
            this.orderBy("attachmentId");
            return this;
        }
        
        /**
         * order by course_time
         *
         * @return
         */
        public OrderBy courseTime() {
            this.orderBy("courseTime");
            return this;
        }
        
        /**
         * order by study_time
         *
         * @return
         */
        public OrderBy studyTime() {
            this.orderBy("studyTime");
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
         * order by status
         *
         * @return
         */
        public OrderBy status() {
            this.orderBy("status");
            return this;
        }
        
        /**
         * order by pic
         *
         * @return
         */
        public OrderBy pic() {
            this.orderBy("pic");
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
         * order by tenant_code
         *
         * @return
         */
        public OrderBy tenantCode() {
            this.orderBy("tenantCode");
            return this;
        }
        
        /**
         * order by video_pic
         *
         * @return
         */
        public OrderBy videoPic() {
            this.orderBy("videoPic");
            return this;
        }
        
        /**
         * order by video
         *
         * @return
         */
        public OrderBy video() {
            this.orderBy("video");
            return this;
        }
        
        /**
         * order by status_a
         *
         * @return
         */
        public OrderBy statusA() {
            this.orderBy("statusA");
            return this;
        }
        
        /**
         * order by status_a_user_id
         *
         * @return
         */
        public OrderBy statusAUserId() {
            this.orderBy("statusAUserId");
            return this;
        }
        
        /**
         * order by status_a_user_name
         *
         * @return
         */
        public OrderBy statusAUserName() {
            this.orderBy("statusAUserName");
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
         * and college = ''
         *
         * @return
         */
        public Criteria andCollegeIsEmpty() {
            this.andEqualTo("college", "");
            return this;
        }

        /**
         * and college != ''
         *
         * @return
         */
        public Criteria andCollegeIsNotEmpty() {
            this.andNotEqualTo("college", "");
            return this;
        }

        /**
         * and college = value
         *
         * @return
         */
        public Criteria andCollegeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("college", value);
            return this;
        }

        /**
         * and college != value
         *
         * @return
         */
        public Criteria andCollegeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("college", value);
            return this;
        }

        /**
         * and college > value
         *
         * @return
         */
        public Criteria andCollegeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("college", value);
            return this;
        }

        /**
         * and college >= value
         *
         * @return
         */
        public Criteria andCollegeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("college", value);
            return this;
        }

        /**
         * and college < value
         *
         * @return
         */
        public Criteria andCollegeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("college", value);
            return this;
        }

        /**
         * and college <= value
         *
         * @return
         */
        public Criteria andCollegeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("college", value);
            return this;
        }

        /**
         * and college in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCollegeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("college", values);
            return this;
        }

        /**
         * and college in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCollegeIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("college", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and college not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCollegeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("college", values);
            return this;
        }

        /**
         * and college not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCollegeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("college", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and college between value1 and value2
         *
         * @return
         */
        public Criteria andCollegeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("college", value1, value2);
            return this;
        }

        /**
         * and college not between value1 and value2
         *
         * @return
         */
        public Criteria andCollegeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("college", value1, value2);
            return this;
        }

        /**
         * and college like value
         *
         * @return
         */
        public Criteria andCollegeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("college", value);
            return this;
        }

        /**
         * and college not like value
         *
         * @return
         */
        public Criteria andCollegeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("college", value);
            return this;
        }

        /**
         * or college = ''
         *
         * @return
         */
        public Criteria orCollegeIsEmpty() {
            this.orEqualTo("college", "");
            return this;
        }

        /**
         * or college != ''
         *
         * @return
         */
        public Criteria orCollegeIsNotEmpty() {
            this.orNotEqualTo("college", "");
            return this;
        }

        /**
         * or college = value
         *
         * @return
         */
        public Criteria orCollegeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("college", value);
            return this;
        }

        /**
         * or college != value
         *
         * @return
         */
        public Criteria orCollegeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("college", value);
            return this;
        }

        /**
         * or college > value
         *
         * @return
         */
        public Criteria orCollegeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("college", value);
            return this;
        }

        /**
         * or college >= value
         *
         * @return
         */
        public Criteria orCollegeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("college", value);
            return this;
        }

        /**
         * or college < value
         *
         * @return
         */
        public Criteria orCollegeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("college", value);
            return this;
        }

        /**
         * or college <= value
         *
         * @return
         */
        public Criteria orCollegeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("college", value);
            return this;
        }

        /**
         * or college in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCollegeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("college", values);
            return this;
        }

        /**
         * or college in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCollegeIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("college", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or college not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCollegeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("college", values);
            return this;
        }

        /**
         * or college not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCollegeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("college", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or college between value1 and value2
         *
         * @return
         */
        public Criteria orCollegeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("college", value1, value2);
            return this;
        }

        /**
         * or college not between value1 and value2
         *
         * @return
         */
        public Criteria orCollegeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("college", value1, value2);
            return this;
        }

        /**
         * or college like value
         *
         * @return
         */
        public Criteria orCollegeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("college", value);
            return this;
        }

        /**
         * or college not like value
         *
         * @return
         */
        public Criteria orCollegeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("college", value);
            return this;
        }

        /**
         * and major = ''
         *
         * @return
         */
        public Criteria andMajorIsEmpty() {
            this.andEqualTo("major", "");
            return this;
        }

        /**
         * and major != ''
         *
         * @return
         */
        public Criteria andMajorIsNotEmpty() {
            this.andNotEqualTo("major", "");
            return this;
        }

        /**
         * and major = value
         *
         * @return
         */
        public Criteria andMajorEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("major", value);
            return this;
        }

        /**
         * and major != value
         *
         * @return
         */
        public Criteria andMajorNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("major", value);
            return this;
        }

        /**
         * and major > value
         *
         * @return
         */
        public Criteria andMajorGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("major", value);
            return this;
        }

        /**
         * and major >= value
         *
         * @return
         */
        public Criteria andMajorGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("major", value);
            return this;
        }

        /**
         * and major < value
         *
         * @return
         */
        public Criteria andMajorLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("major", value);
            return this;
        }

        /**
         * and major <= value
         *
         * @return
         */
        public Criteria andMajorLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("major", value);
            return this;
        }

        /**
         * and major in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andMajorIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("major", values);
            return this;
        }

        /**
         * and major in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andMajorIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("major", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and major not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andMajorNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("major", values);
            return this;
        }

        /**
         * and major not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andMajorNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("major", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and major between value1 and value2
         *
         * @return
         */
        public Criteria andMajorBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("major", value1, value2);
            return this;
        }

        /**
         * and major not between value1 and value2
         *
         * @return
         */
        public Criteria andMajorNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("major", value1, value2);
            return this;
        }

        /**
         * and major like value
         *
         * @return
         */
        public Criteria andMajorLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("major", value);
            return this;
        }

        /**
         * and major not like value
         *
         * @return
         */
        public Criteria andMajorNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("major", value);
            return this;
        }

        /**
         * or major = ''
         *
         * @return
         */
        public Criteria orMajorIsEmpty() {
            this.orEqualTo("major", "");
            return this;
        }

        /**
         * or major != ''
         *
         * @return
         */
        public Criteria orMajorIsNotEmpty() {
            this.orNotEqualTo("major", "");
            return this;
        }

        /**
         * or major = value
         *
         * @return
         */
        public Criteria orMajorEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("major", value);
            return this;
        }

        /**
         * or major != value
         *
         * @return
         */
        public Criteria orMajorNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("major", value);
            return this;
        }

        /**
         * or major > value
         *
         * @return
         */
        public Criteria orMajorGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("major", value);
            return this;
        }

        /**
         * or major >= value
         *
         * @return
         */
        public Criteria orMajorGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("major", value);
            return this;
        }

        /**
         * or major < value
         *
         * @return
         */
        public Criteria orMajorLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("major", value);
            return this;
        }

        /**
         * or major <= value
         *
         * @return
         */
        public Criteria orMajorLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("major", value);
            return this;
        }

        /**
         * or major in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orMajorIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("major", values);
            return this;
        }

        /**
         * or major in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orMajorIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("major", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or major not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orMajorNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("major", values);
            return this;
        }

        /**
         * or major not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orMajorNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("major", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or major between value1 and value2
         *
         * @return
         */
        public Criteria orMajorBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("major", value1, value2);
            return this;
        }

        /**
         * or major not between value1 and value2
         *
         * @return
         */
        public Criteria orMajorNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("major", value1, value2);
            return this;
        }

        /**
         * or major like value
         *
         * @return
         */
        public Criteria orMajorLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("major", value);
            return this;
        }

        /**
         * or major not like value
         *
         * @return
         */
        public Criteria orMajorNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("major", value);
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
         * and course_description = ''
         *
         * @return
         */
        public Criteria andCourseDescriptionIsEmpty() {
            this.andEqualTo("courseDescription", "");
            return this;
        }

        /**
         * and course_description != ''
         *
         * @return
         */
        public Criteria andCourseDescriptionIsNotEmpty() {
            this.andNotEqualTo("courseDescription", "");
            return this;
        }

        /**
         * and course_description = value
         *
         * @return
         */
        public Criteria andCourseDescriptionEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("courseDescription", value);
            return this;
        }

        /**
         * and course_description != value
         *
         * @return
         */
        public Criteria andCourseDescriptionNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("courseDescription", value);
            return this;
        }

        /**
         * and course_description > value
         *
         * @return
         */
        public Criteria andCourseDescriptionGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("courseDescription", value);
            return this;
        }

        /**
         * and course_description >= value
         *
         * @return
         */
        public Criteria andCourseDescriptionGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("courseDescription", value);
            return this;
        }

        /**
         * and course_description < value
         *
         * @return
         */
        public Criteria andCourseDescriptionLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("courseDescription", value);
            return this;
        }

        /**
         * and course_description <= value
         *
         * @return
         */
        public Criteria andCourseDescriptionLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("courseDescription", value);
            return this;
        }

        /**
         * and course_description in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseDescriptionIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseDescription", values);
            return this;
        }

        /**
         * and course_description in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseDescriptionIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseDescription", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_description not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseDescriptionNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseDescription", values);
            return this;
        }

        /**
         * and course_description not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseDescriptionNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseDescription", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_description between value1 and value2
         *
         * @return
         */
        public Criteria andCourseDescriptionBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("courseDescription", value1, value2);
            return this;
        }

        /**
         * and course_description not between value1 and value2
         *
         * @return
         */
        public Criteria andCourseDescriptionNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("courseDescription", value1, value2);
            return this;
        }

        /**
         * and course_description like value
         *
         * @return
         */
        public Criteria andCourseDescriptionLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("courseDescription", value);
            return this;
        }

        /**
         * and course_description not like value
         *
         * @return
         */
        public Criteria andCourseDescriptionNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("courseDescription", value);
            return this;
        }

        /**
         * or course_description = ''
         *
         * @return
         */
        public Criteria orCourseDescriptionIsEmpty() {
            this.orEqualTo("courseDescription", "");
            return this;
        }

        /**
         * or course_description != ''
         *
         * @return
         */
        public Criteria orCourseDescriptionIsNotEmpty() {
            this.orNotEqualTo("courseDescription", "");
            return this;
        }

        /**
         * or course_description = value
         *
         * @return
         */
        public Criteria orCourseDescriptionEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("courseDescription", value);
            return this;
        }

        /**
         * or course_description != value
         *
         * @return
         */
        public Criteria orCourseDescriptionNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("courseDescription", value);
            return this;
        }

        /**
         * or course_description > value
         *
         * @return
         */
        public Criteria orCourseDescriptionGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("courseDescription", value);
            return this;
        }

        /**
         * or course_description >= value
         *
         * @return
         */
        public Criteria orCourseDescriptionGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("courseDescription", value);
            return this;
        }

        /**
         * or course_description < value
         *
         * @return
         */
        public Criteria orCourseDescriptionLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("courseDescription", value);
            return this;
        }

        /**
         * or course_description <= value
         *
         * @return
         */
        public Criteria orCourseDescriptionLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("courseDescription", value);
            return this;
        }

        /**
         * or course_description in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseDescriptionIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseDescription", values);
            return this;
        }

        /**
         * or course_description in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseDescriptionIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseDescription", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_description not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseDescriptionNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseDescription", values);
            return this;
        }

        /**
         * or course_description not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseDescriptionNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseDescription", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_description between value1 and value2
         *
         * @return
         */
        public Criteria orCourseDescriptionBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("courseDescription", value1, value2);
            return this;
        }

        /**
         * or course_description not between value1 and value2
         *
         * @return
         */
        public Criteria orCourseDescriptionNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("courseDescription", value1, value2);
            return this;
        }

        /**
         * or course_description like value
         *
         * @return
         */
        public Criteria orCourseDescriptionLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("courseDescription", value);
            return this;
        }

        /**
         * or course_description not like value
         *
         * @return
         */
        public Criteria orCourseDescriptionNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("courseDescription", value);
            return this;
        }

        /**
         * and course_type = ''
         *
         * @return
         */
        public Criteria andCourseTypeIsEmpty() {
            this.andEqualTo("courseType", "");
            return this;
        }

        /**
         * and course_type != ''
         *
         * @return
         */
        public Criteria andCourseTypeIsNotEmpty() {
            this.andNotEqualTo("courseType", "");
            return this;
        }

        /**
         * and course_type = value
         *
         * @return
         */
        public Criteria andCourseTypeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("courseType", value);
            return this;
        }

        /**
         * and course_type != value
         *
         * @return
         */
        public Criteria andCourseTypeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("courseType", value);
            return this;
        }

        /**
         * and course_type > value
         *
         * @return
         */
        public Criteria andCourseTypeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("courseType", value);
            return this;
        }

        /**
         * and course_type >= value
         *
         * @return
         */
        public Criteria andCourseTypeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("courseType", value);
            return this;
        }

        /**
         * and course_type < value
         *
         * @return
         */
        public Criteria andCourseTypeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("courseType", value);
            return this;
        }

        /**
         * and course_type <= value
         *
         * @return
         */
        public Criteria andCourseTypeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("courseType", value);
            return this;
        }

        /**
         * and course_type in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseTypeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseType", values);
            return this;
        }

        /**
         * and course_type in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseTypeIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseType", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_type not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseTypeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseType", values);
            return this;
        }

        /**
         * and course_type not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseTypeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseType", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_type between value1 and value2
         *
         * @return
         */
        public Criteria andCourseTypeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("courseType", value1, value2);
            return this;
        }

        /**
         * and course_type not between value1 and value2
         *
         * @return
         */
        public Criteria andCourseTypeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("courseType", value1, value2);
            return this;
        }

        /**
         * and course_type like value
         *
         * @return
         */
        public Criteria andCourseTypeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("courseType", value);
            return this;
        }

        /**
         * and course_type not like value
         *
         * @return
         */
        public Criteria andCourseTypeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("courseType", value);
            return this;
        }

        /**
         * or course_type = ''
         *
         * @return
         */
        public Criteria orCourseTypeIsEmpty() {
            this.orEqualTo("courseType", "");
            return this;
        }

        /**
         * or course_type != ''
         *
         * @return
         */
        public Criteria orCourseTypeIsNotEmpty() {
            this.orNotEqualTo("courseType", "");
            return this;
        }

        /**
         * or course_type = value
         *
         * @return
         */
        public Criteria orCourseTypeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("courseType", value);
            return this;
        }

        /**
         * or course_type != value
         *
         * @return
         */
        public Criteria orCourseTypeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("courseType", value);
            return this;
        }

        /**
         * or course_type > value
         *
         * @return
         */
        public Criteria orCourseTypeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("courseType", value);
            return this;
        }

        /**
         * or course_type >= value
         *
         * @return
         */
        public Criteria orCourseTypeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("courseType", value);
            return this;
        }

        /**
         * or course_type < value
         *
         * @return
         */
        public Criteria orCourseTypeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("courseType", value);
            return this;
        }

        /**
         * or course_type <= value
         *
         * @return
         */
        public Criteria orCourseTypeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("courseType", value);
            return this;
        }

        /**
         * or course_type in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseTypeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseType", values);
            return this;
        }

        /**
         * or course_type in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseTypeIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseType", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_type not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseTypeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseType", values);
            return this;
        }

        /**
         * or course_type not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseTypeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseType", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_type between value1 and value2
         *
         * @return
         */
        public Criteria orCourseTypeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("courseType", value1, value2);
            return this;
        }

        /**
         * or course_type not between value1 and value2
         *
         * @return
         */
        public Criteria orCourseTypeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("courseType", value1, value2);
            return this;
        }

        /**
         * or course_type like value
         *
         * @return
         */
        public Criteria orCourseTypeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("courseType", value);
            return this;
        }

        /**
         * or course_type not like value
         *
         * @return
         */
        public Criteria orCourseTypeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("courseType", value);
            return this;
        }

        /**
         * and attachment_id = value
         *
         * @return
         */
        public Criteria andAttachmentIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("attachmentId", value);
            return this;
        }

        /**
         * and attachment_id != value
         *
         * @return
         */
        public Criteria andAttachmentIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("attachmentId", value);
            return this;
        }

        /**
         * and attachment_id > value
         *
         * @return
         */
        public Criteria andAttachmentIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("attachmentId", value);
            return this;
        }

        /**
         * and attachment_id >= value
         *
         * @return
         */
        public Criteria andAttachmentIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("attachmentId", value);
            return this;
        }

        /**
         * and attachment_id < value
         *
         * @return
         */
        public Criteria andAttachmentIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("attachmentId", value);
            return this;
        }

        /**
         * and attachment_id <= value
         *
         * @return
         */
        public Criteria andAttachmentIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("attachmentId", value);
            return this;
        }

        /**
         * and attachment_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAttachmentIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("attachmentId", values);
            return this;
        }

        /**
         * and attachment_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAttachmentIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("attachmentId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and attachment_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAttachmentIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("attachmentId", values);
            return this;
        }

        /**
         * and attachment_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andAttachmentIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("attachmentId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and attachment_id between value1 and value2
         *
         * @return
         */
        public Criteria andAttachmentIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("attachmentId", value1, value2);
            return this;
        }

        /**
         * and attachment_id not between value1 and value2
         *
         * @return
         */
        public Criteria andAttachmentIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("attachmentId", value1, value2);
            return this;
        }

        /**
         * and attachment_id like value
         *
         * @return
         */
        public Criteria andAttachmentIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("attachmentId", value);
            return this;
        }

        /**
         * and attachment_id not like value
         *
         * @return
         */
        public Criteria andAttachmentIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("attachmentId", value);
            return this;
        }

        /**
         * or attachment_id = value
         *
         * @return
         */
        public Criteria orAttachmentIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("attachmentId", value);
            return this;
        }

        /**
         * or attachment_id != value
         *
         * @return
         */
        public Criteria orAttachmentIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("attachmentId", value);
            return this;
        }

        /**
         * or attachment_id > value
         *
         * @return
         */
        public Criteria orAttachmentIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("attachmentId", value);
            return this;
        }

        /**
         * or attachment_id >= value
         *
         * @return
         */
        public Criteria orAttachmentIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("attachmentId", value);
            return this;
        }

        /**
         * or attachment_id < value
         *
         * @return
         */
        public Criteria orAttachmentIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("attachmentId", value);
            return this;
        }

        /**
         * or attachment_id <= value
         *
         * @return
         */
        public Criteria orAttachmentIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("attachmentId", value);
            return this;
        }

        /**
         * or attachment_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAttachmentIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("attachmentId", values);
            return this;
        }

        /**
         * or attachment_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAttachmentIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("attachmentId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or attachment_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAttachmentIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("attachmentId", values);
            return this;
        }

        /**
         * or attachment_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orAttachmentIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("attachmentId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or attachment_id between value1 and value2
         *
         * @return
         */
        public Criteria orAttachmentIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("attachmentId", value1, value2);
            return this;
        }

        /**
         * or attachment_id not between value1 and value2
         *
         * @return
         */
        public Criteria orAttachmentIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("attachmentId", value1, value2);
            return this;
        }

        /**
         * or attachment_id like value
         *
         * @return
         */
        public Criteria orAttachmentIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("attachmentId", value);
            return this;
        }

        /**
         * or attachment_id not like value
         *
         * @return
         */
        public Criteria orAttachmentIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("attachmentId", value);
            return this;
        }

        /**
         * and course_time = value
         *
         * @return
         */
        public Criteria andCourseTimeEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("courseTime", value);
            return this;
        }

        /**
         * and course_time != value
         *
         * @return
         */
        public Criteria andCourseTimeNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("courseTime", value);
            return this;
        }

        /**
         * and course_time > value
         *
         * @return
         */
        public Criteria andCourseTimeGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("courseTime", value);
            return this;
        }

        /**
         * and course_time >= value
         *
         * @return
         */
        public Criteria andCourseTimeGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("courseTime", value);
            return this;
        }

        /**
         * and course_time < value
         *
         * @return
         */
        public Criteria andCourseTimeLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("courseTime", value);
            return this;
        }

        /**
         * and course_time <= value
         *
         * @return
         */
        public Criteria andCourseTimeLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("courseTime", value);
            return this;
        }

        /**
         * and course_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseTimeIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseTime", values);
            return this;
        }

        /**
         * and course_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseTimeIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseTimeNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseTime", values);
            return this;
        }

        /**
         * and course_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseTimeNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_time between value1 and value2
         *
         * @return
         */
        public Criteria andCourseTimeBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("courseTime", value1, value2);
            return this;
        }

        /**
         * and course_time not between value1 and value2
         *
         * @return
         */
        public Criteria andCourseTimeNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("courseTime", value1, value2);
            return this;
        }

        /**
         * and course_time like value
         *
         * @return
         */
        public Criteria andCourseTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("courseTime", value);
            return this;
        }

        /**
         * and course_time not like value
         *
         * @return
         */
        public Criteria andCourseTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("courseTime", value);
            return this;
        }

        /**
         * or course_time = value
         *
         * @return
         */
        public Criteria orCourseTimeEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("courseTime", value);
            return this;
        }

        /**
         * or course_time != value
         *
         * @return
         */
        public Criteria orCourseTimeNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("courseTime", value);
            return this;
        }

        /**
         * or course_time > value
         *
         * @return
         */
        public Criteria orCourseTimeGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("courseTime", value);
            return this;
        }

        /**
         * or course_time >= value
         *
         * @return
         */
        public Criteria orCourseTimeGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("courseTime", value);
            return this;
        }

        /**
         * or course_time < value
         *
         * @return
         */
        public Criteria orCourseTimeLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("courseTime", value);
            return this;
        }

        /**
         * or course_time <= value
         *
         * @return
         */
        public Criteria orCourseTimeLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("courseTime", value);
            return this;
        }

        /**
         * or course_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseTimeIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseTime", values);
            return this;
        }

        /**
         * or course_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseTimeIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseTimeNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseTime", values);
            return this;
        }

        /**
         * or course_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseTimeNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_time between value1 and value2
         *
         * @return
         */
        public Criteria orCourseTimeBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("courseTime", value1, value2);
            return this;
        }

        /**
         * or course_time not between value1 and value2
         *
         * @return
         */
        public Criteria orCourseTimeNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("courseTime", value1, value2);
            return this;
        }

        /**
         * or course_time like value
         *
         * @return
         */
        public Criteria orCourseTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("courseTime", value);
            return this;
        }

        /**
         * or course_time not like value
         *
         * @return
         */
        public Criteria orCourseTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("courseTime", value);
            return this;
        }

        /**
         * and study_time = value
         *
         * @return
         */
        public Criteria andStudyTimeEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("studyTime", value);
            return this;
        }

        /**
         * and study_time != value
         *
         * @return
         */
        public Criteria andStudyTimeNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("studyTime", value);
            return this;
        }

        /**
         * and study_time > value
         *
         * @return
         */
        public Criteria andStudyTimeGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("studyTime", value);
            return this;
        }

        /**
         * and study_time >= value
         *
         * @return
         */
        public Criteria andStudyTimeGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("studyTime", value);
            return this;
        }

        /**
         * and study_time < value
         *
         * @return
         */
        public Criteria andStudyTimeLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("studyTime", value);
            return this;
        }

        /**
         * and study_time <= value
         *
         * @return
         */
        public Criteria andStudyTimeLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("studyTime", value);
            return this;
        }

        /**
         * and study_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStudyTimeIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("studyTime", values);
            return this;
        }

        /**
         * and study_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStudyTimeIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("studyTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and study_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStudyTimeNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("studyTime", values);
            return this;
        }

        /**
         * and study_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStudyTimeNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("studyTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and study_time between value1 and value2
         *
         * @return
         */
        public Criteria andStudyTimeBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("studyTime", value1, value2);
            return this;
        }

        /**
         * and study_time not between value1 and value2
         *
         * @return
         */
        public Criteria andStudyTimeNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("studyTime", value1, value2);
            return this;
        }

        /**
         * and study_time like value
         *
         * @return
         */
        public Criteria andStudyTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("studyTime", value);
            return this;
        }

        /**
         * and study_time not like value
         *
         * @return
         */
        public Criteria andStudyTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("studyTime", value);
            return this;
        }

        /**
         * or study_time = value
         *
         * @return
         */
        public Criteria orStudyTimeEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("studyTime", value);
            return this;
        }

        /**
         * or study_time != value
         *
         * @return
         */
        public Criteria orStudyTimeNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("studyTime", value);
            return this;
        }

        /**
         * or study_time > value
         *
         * @return
         */
        public Criteria orStudyTimeGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("studyTime", value);
            return this;
        }

        /**
         * or study_time >= value
         *
         * @return
         */
        public Criteria orStudyTimeGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("studyTime", value);
            return this;
        }

        /**
         * or study_time < value
         *
         * @return
         */
        public Criteria orStudyTimeLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("studyTime", value);
            return this;
        }

        /**
         * or study_time <= value
         *
         * @return
         */
        public Criteria orStudyTimeLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("studyTime", value);
            return this;
        }

        /**
         * or study_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStudyTimeIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("studyTime", values);
            return this;
        }

        /**
         * or study_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStudyTimeIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("studyTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or study_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStudyTimeNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("studyTime", values);
            return this;
        }

        /**
         * or study_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStudyTimeNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("studyTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or study_time between value1 and value2
         *
         * @return
         */
        public Criteria orStudyTimeBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("studyTime", value1, value2);
            return this;
        }

        /**
         * or study_time not between value1 and value2
         *
         * @return
         */
        public Criteria orStudyTimeNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("studyTime", value1, value2);
            return this;
        }

        /**
         * or study_time like value
         *
         * @return
         */
        public Criteria orStudyTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("studyTime", value);
            return this;
        }

        /**
         * or study_time not like value
         *
         * @return
         */
        public Criteria orStudyTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("studyTime", value);
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
         * and pic = ''
         *
         * @return
         */
        public Criteria andPicIsEmpty() {
            this.andEqualTo("pic", "");
            return this;
        }

        /**
         * and pic != ''
         *
         * @return
         */
        public Criteria andPicIsNotEmpty() {
            this.andNotEqualTo("pic", "");
            return this;
        }

        /**
         * and pic = value
         *
         * @return
         */
        public Criteria andPicEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("pic", value);
            return this;
        }

        /**
         * and pic != value
         *
         * @return
         */
        public Criteria andPicNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("pic", value);
            return this;
        }

        /**
         * and pic > value
         *
         * @return
         */
        public Criteria andPicGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("pic", value);
            return this;
        }

        /**
         * and pic >= value
         *
         * @return
         */
        public Criteria andPicGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("pic", value);
            return this;
        }

        /**
         * and pic < value
         *
         * @return
         */
        public Criteria andPicLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("pic", value);
            return this;
        }

        /**
         * and pic <= value
         *
         * @return
         */
        public Criteria andPicLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("pic", value);
            return this;
        }

        /**
         * and pic in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPicIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pic", values);
            return this;
        }

        /**
         * and pic in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPicIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and pic not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPicNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pic", values);
            return this;
        }

        /**
         * and pic not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPicNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and pic between value1 and value2
         *
         * @return
         */
        public Criteria andPicBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("pic", value1, value2);
            return this;
        }

        /**
         * and pic not between value1 and value2
         *
         * @return
         */
        public Criteria andPicNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("pic", value1, value2);
            return this;
        }

        /**
         * and pic like value
         *
         * @return
         */
        public Criteria andPicLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("pic", value);
            return this;
        }

        /**
         * and pic not like value
         *
         * @return
         */
        public Criteria andPicNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("pic", value);
            return this;
        }

        /**
         * or pic = ''
         *
         * @return
         */
        public Criteria orPicIsEmpty() {
            this.orEqualTo("pic", "");
            return this;
        }

        /**
         * or pic != ''
         *
         * @return
         */
        public Criteria orPicIsNotEmpty() {
            this.orNotEqualTo("pic", "");
            return this;
        }

        /**
         * or pic = value
         *
         * @return
         */
        public Criteria orPicEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("pic", value);
            return this;
        }

        /**
         * or pic != value
         *
         * @return
         */
        public Criteria orPicNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("pic", value);
            return this;
        }

        /**
         * or pic > value
         *
         * @return
         */
        public Criteria orPicGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("pic", value);
            return this;
        }

        /**
         * or pic >= value
         *
         * @return
         */
        public Criteria orPicGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("pic", value);
            return this;
        }

        /**
         * or pic < value
         *
         * @return
         */
        public Criteria orPicLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("pic", value);
            return this;
        }

        /**
         * or pic <= value
         *
         * @return
         */
        public Criteria orPicLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("pic", value);
            return this;
        }

        /**
         * or pic in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPicIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pic", values);
            return this;
        }

        /**
         * or pic in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPicIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or pic not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPicNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pic", values);
            return this;
        }

        /**
         * or pic not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPicNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or pic between value1 and value2
         *
         * @return
         */
        public Criteria orPicBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("pic", value1, value2);
            return this;
        }

        /**
         * or pic not between value1 and value2
         *
         * @return
         */
        public Criteria orPicNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("pic", value1, value2);
            return this;
        }

        /**
         * or pic like value
         *
         * @return
         */
        public Criteria orPicLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("pic", value);
            return this;
        }

        /**
         * or pic not like value
         *
         * @return
         */
        public Criteria orPicNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("pic", value);
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
         * and video_pic = ''
         *
         * @return
         */
        public Criteria andVideoPicIsEmpty() {
            this.andEqualTo("videoPic", "");
            return this;
        }

        /**
         * and video_pic != ''
         *
         * @return
         */
        public Criteria andVideoPicIsNotEmpty() {
            this.andNotEqualTo("videoPic", "");
            return this;
        }

        /**
         * and video_pic = value
         *
         * @return
         */
        public Criteria andVideoPicEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("videoPic", value);
            return this;
        }

        /**
         * and video_pic != value
         *
         * @return
         */
        public Criteria andVideoPicNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("videoPic", value);
            return this;
        }

        /**
         * and video_pic > value
         *
         * @return
         */
        public Criteria andVideoPicGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("videoPic", value);
            return this;
        }

        /**
         * and video_pic >= value
         *
         * @return
         */
        public Criteria andVideoPicGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("videoPic", value);
            return this;
        }

        /**
         * and video_pic < value
         *
         * @return
         */
        public Criteria andVideoPicLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("videoPic", value);
            return this;
        }

        /**
         * and video_pic <= value
         *
         * @return
         */
        public Criteria andVideoPicLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("videoPic", value);
            return this;
        }

        /**
         * and video_pic in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andVideoPicIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("videoPic", values);
            return this;
        }

        /**
         * and video_pic in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andVideoPicIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("videoPic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and video_pic not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andVideoPicNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("videoPic", values);
            return this;
        }

        /**
         * and video_pic not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andVideoPicNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("videoPic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and video_pic between value1 and value2
         *
         * @return
         */
        public Criteria andVideoPicBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("videoPic", value1, value2);
            return this;
        }

        /**
         * and video_pic not between value1 and value2
         *
         * @return
         */
        public Criteria andVideoPicNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("videoPic", value1, value2);
            return this;
        }

        /**
         * and video_pic like value
         *
         * @return
         */
        public Criteria andVideoPicLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("videoPic", value);
            return this;
        }

        /**
         * and video_pic not like value
         *
         * @return
         */
        public Criteria andVideoPicNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("videoPic", value);
            return this;
        }

        /**
         * or video_pic = ''
         *
         * @return
         */
        public Criteria orVideoPicIsEmpty() {
            this.orEqualTo("videoPic", "");
            return this;
        }

        /**
         * or video_pic != ''
         *
         * @return
         */
        public Criteria orVideoPicIsNotEmpty() {
            this.orNotEqualTo("videoPic", "");
            return this;
        }

        /**
         * or video_pic = value
         *
         * @return
         */
        public Criteria orVideoPicEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("videoPic", value);
            return this;
        }

        /**
         * or video_pic != value
         *
         * @return
         */
        public Criteria orVideoPicNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("videoPic", value);
            return this;
        }

        /**
         * or video_pic > value
         *
         * @return
         */
        public Criteria orVideoPicGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("videoPic", value);
            return this;
        }

        /**
         * or video_pic >= value
         *
         * @return
         */
        public Criteria orVideoPicGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("videoPic", value);
            return this;
        }

        /**
         * or video_pic < value
         *
         * @return
         */
        public Criteria orVideoPicLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("videoPic", value);
            return this;
        }

        /**
         * or video_pic <= value
         *
         * @return
         */
        public Criteria orVideoPicLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("videoPic", value);
            return this;
        }

        /**
         * or video_pic in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orVideoPicIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("videoPic", values);
            return this;
        }

        /**
         * or video_pic in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orVideoPicIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("videoPic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or video_pic not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orVideoPicNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("videoPic", values);
            return this;
        }

        /**
         * or video_pic not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orVideoPicNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("videoPic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or video_pic between value1 and value2
         *
         * @return
         */
        public Criteria orVideoPicBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("videoPic", value1, value2);
            return this;
        }

        /**
         * or video_pic not between value1 and value2
         *
         * @return
         */
        public Criteria orVideoPicNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("videoPic", value1, value2);
            return this;
        }

        /**
         * or video_pic like value
         *
         * @return
         */
        public Criteria orVideoPicLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("videoPic", value);
            return this;
        }

        /**
         * or video_pic not like value
         *
         * @return
         */
        public Criteria orVideoPicNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("videoPic", value);
            return this;
        }

        /**
         * and video = ''
         *
         * @return
         */
        public Criteria andVideoIsEmpty() {
            this.andEqualTo("video", "");
            return this;
        }

        /**
         * and video != ''
         *
         * @return
         */
        public Criteria andVideoIsNotEmpty() {
            this.andNotEqualTo("video", "");
            return this;
        }

        /**
         * and video = value
         *
         * @return
         */
        public Criteria andVideoEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("video", value);
            return this;
        }

        /**
         * and video != value
         *
         * @return
         */
        public Criteria andVideoNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("video", value);
            return this;
        }

        /**
         * and video > value
         *
         * @return
         */
        public Criteria andVideoGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("video", value);
            return this;
        }

        /**
         * and video >= value
         *
         * @return
         */
        public Criteria andVideoGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("video", value);
            return this;
        }

        /**
         * and video < value
         *
         * @return
         */
        public Criteria andVideoLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("video", value);
            return this;
        }

        /**
         * and video <= value
         *
         * @return
         */
        public Criteria andVideoLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("video", value);
            return this;
        }

        /**
         * and video in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andVideoIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("video", values);
            return this;
        }

        /**
         * and video in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andVideoIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("video", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and video not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andVideoNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("video", values);
            return this;
        }

        /**
         * and video not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andVideoNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("video", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and video between value1 and value2
         *
         * @return
         */
        public Criteria andVideoBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("video", value1, value2);
            return this;
        }

        /**
         * and video not between value1 and value2
         *
         * @return
         */
        public Criteria andVideoNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("video", value1, value2);
            return this;
        }

        /**
         * and video like value
         *
         * @return
         */
        public Criteria andVideoLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("video", value);
            return this;
        }

        /**
         * and video not like value
         *
         * @return
         */
        public Criteria andVideoNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("video", value);
            return this;
        }

        /**
         * or video = ''
         *
         * @return
         */
        public Criteria orVideoIsEmpty() {
            this.orEqualTo("video", "");
            return this;
        }

        /**
         * or video != ''
         *
         * @return
         */
        public Criteria orVideoIsNotEmpty() {
            this.orNotEqualTo("video", "");
            return this;
        }

        /**
         * or video = value
         *
         * @return
         */
        public Criteria orVideoEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("video", value);
            return this;
        }

        /**
         * or video != value
         *
         * @return
         */
        public Criteria orVideoNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("video", value);
            return this;
        }

        /**
         * or video > value
         *
         * @return
         */
        public Criteria orVideoGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("video", value);
            return this;
        }

        /**
         * or video >= value
         *
         * @return
         */
        public Criteria orVideoGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("video", value);
            return this;
        }

        /**
         * or video < value
         *
         * @return
         */
        public Criteria orVideoLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("video", value);
            return this;
        }

        /**
         * or video <= value
         *
         * @return
         */
        public Criteria orVideoLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("video", value);
            return this;
        }

        /**
         * or video in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orVideoIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("video", values);
            return this;
        }

        /**
         * or video in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orVideoIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("video", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or video not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orVideoNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("video", values);
            return this;
        }

        /**
         * or video not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orVideoNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("video", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or video between value1 and value2
         *
         * @return
         */
        public Criteria orVideoBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("video", value1, value2);
            return this;
        }

        /**
         * or video not between value1 and value2
         *
         * @return
         */
        public Criteria orVideoNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("video", value1, value2);
            return this;
        }

        /**
         * or video like value
         *
         * @return
         */
        public Criteria orVideoLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("video", value);
            return this;
        }

        /**
         * or video not like value
         *
         * @return
         */
        public Criteria orVideoNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("video", value);
            return this;
        }

        /**
         * and status_a = value
         *
         * @return
         */
        public Criteria andStatusAEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("statusA", value);
            return this;
        }

        /**
         * and status_a != value
         *
         * @return
         */
        public Criteria andStatusANotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("statusA", value);
            return this;
        }

        /**
         * and status_a > value
         *
         * @return
         */
        public Criteria andStatusAGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("statusA", value);
            return this;
        }

        /**
         * and status_a >= value
         *
         * @return
         */
        public Criteria andStatusAGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("statusA", value);
            return this;
        }

        /**
         * and status_a < value
         *
         * @return
         */
        public Criteria andStatusALessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("statusA", value);
            return this;
        }

        /**
         * and status_a <= value
         *
         * @return
         */
        public Criteria andStatusALessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("statusA", value);
            return this;
        }

        /**
         * and status_a in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("statusA", values);
            return this;
        }

        /**
         * and status_a in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("statusA", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and status_a not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusANotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("statusA", values);
            return this;
        }

        /**
         * and status_a not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusANotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("statusA", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and status_a between value1 and value2
         *
         * @return
         */
        public Criteria andStatusABetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("statusA", value1, value2);
            return this;
        }

        /**
         * and status_a not between value1 and value2
         *
         * @return
         */
        public Criteria andStatusANotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("statusA", value1, value2);
            return this;
        }

        /**
         * and status_a like value
         *
         * @return
         */
        public Criteria andStatusALike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("statusA", value);
            return this;
        }

        /**
         * and status_a not like value
         *
         * @return
         */
        public Criteria andStatusANotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("statusA", value);
            return this;
        }

        /**
         * or status_a = value
         *
         * @return
         */
        public Criteria orStatusAEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("statusA", value);
            return this;
        }

        /**
         * or status_a != value
         *
         * @return
         */
        public Criteria orStatusANotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("statusA", value);
            return this;
        }

        /**
         * or status_a > value
         *
         * @return
         */
        public Criteria orStatusAGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("statusA", value);
            return this;
        }

        /**
         * or status_a >= value
         *
         * @return
         */
        public Criteria orStatusAGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("statusA", value);
            return this;
        }

        /**
         * or status_a < value
         *
         * @return
         */
        public Criteria orStatusALessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("statusA", value);
            return this;
        }

        /**
         * or status_a <= value
         *
         * @return
         */
        public Criteria orStatusALessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("statusA", value);
            return this;
        }

        /**
         * or status_a in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("statusA", values);
            return this;
        }

        /**
         * or status_a in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("statusA", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or status_a not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusANotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("statusA", values);
            return this;
        }

        /**
         * or status_a not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusANotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("statusA", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or status_a between value1 and value2
         *
         * @return
         */
        public Criteria orStatusABetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("statusA", value1, value2);
            return this;
        }

        /**
         * or status_a not between value1 and value2
         *
         * @return
         */
        public Criteria orStatusANotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("statusA", value1, value2);
            return this;
        }

        /**
         * or status_a like value
         *
         * @return
         */
        public Criteria orStatusALike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("statusA", value);
            return this;
        }

        /**
         * or status_a not like value
         *
         * @return
         */
        public Criteria orStatusANotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("statusA", value);
            return this;
        }

        /**
         * and status_a_user_id = value
         *
         * @return
         */
        public Criteria andStatusAUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("statusAUserId", value);
            return this;
        }

        /**
         * and status_a_user_id != value
         *
         * @return
         */
        public Criteria andStatusAUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("statusAUserId", value);
            return this;
        }

        /**
         * and status_a_user_id > value
         *
         * @return
         */
        public Criteria andStatusAUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("statusAUserId", value);
            return this;
        }

        /**
         * and status_a_user_id >= value
         *
         * @return
         */
        public Criteria andStatusAUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("statusAUserId", value);
            return this;
        }

        /**
         * and status_a_user_id < value
         *
         * @return
         */
        public Criteria andStatusAUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("statusAUserId", value);
            return this;
        }

        /**
         * and status_a_user_id <= value
         *
         * @return
         */
        public Criteria andStatusAUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("statusAUserId", value);
            return this;
        }

        /**
         * and status_a_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("statusAUserId", values);
            return this;
        }

        /**
         * and status_a_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAUserIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("statusAUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and status_a_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("statusAUserId", values);
            return this;
        }

        /**
         * and status_a_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("statusAUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and status_a_user_id between value1 and value2
         *
         * @return
         */
        public Criteria andStatusAUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("statusAUserId", value1, value2);
            return this;
        }

        /**
         * and status_a_user_id not between value1 and value2
         *
         * @return
         */
        public Criteria andStatusAUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("statusAUserId", value1, value2);
            return this;
        }

        /**
         * and status_a_user_id like value
         *
         * @return
         */
        public Criteria andStatusAUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("statusAUserId", value);
            return this;
        }

        /**
         * and status_a_user_id not like value
         *
         * @return
         */
        public Criteria andStatusAUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("statusAUserId", value);
            return this;
        }

        /**
         * or status_a_user_id = value
         *
         * @return
         */
        public Criteria orStatusAUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("statusAUserId", value);
            return this;
        }

        /**
         * or status_a_user_id != value
         *
         * @return
         */
        public Criteria orStatusAUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("statusAUserId", value);
            return this;
        }

        /**
         * or status_a_user_id > value
         *
         * @return
         */
        public Criteria orStatusAUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("statusAUserId", value);
            return this;
        }

        /**
         * or status_a_user_id >= value
         *
         * @return
         */
        public Criteria orStatusAUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("statusAUserId", value);
            return this;
        }

        /**
         * or status_a_user_id < value
         *
         * @return
         */
        public Criteria orStatusAUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("statusAUserId", value);
            return this;
        }

        /**
         * or status_a_user_id <= value
         *
         * @return
         */
        public Criteria orStatusAUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("statusAUserId", value);
            return this;
        }

        /**
         * or status_a_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("statusAUserId", values);
            return this;
        }

        /**
         * or status_a_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAUserIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("statusAUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or status_a_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("statusAUserId", values);
            return this;
        }

        /**
         * or status_a_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("statusAUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or status_a_user_id between value1 and value2
         *
         * @return
         */
        public Criteria orStatusAUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("statusAUserId", value1, value2);
            return this;
        }

        /**
         * or status_a_user_id not between value1 and value2
         *
         * @return
         */
        public Criteria orStatusAUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("statusAUserId", value1, value2);
            return this;
        }

        /**
         * or status_a_user_id like value
         *
         * @return
         */
        public Criteria orStatusAUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("statusAUserId", value);
            return this;
        }

        /**
         * or status_a_user_id not like value
         *
         * @return
         */
        public Criteria orStatusAUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("statusAUserId", value);
            return this;
        }

        /**
         * and status_a_user_name = ''
         *
         * @return
         */
        public Criteria andStatusAUserNameIsEmpty() {
            this.andEqualTo("statusAUserName", "");
            return this;
        }

        /**
         * and status_a_user_name != ''
         *
         * @return
         */
        public Criteria andStatusAUserNameIsNotEmpty() {
            this.andNotEqualTo("statusAUserName", "");
            return this;
        }

        /**
         * and status_a_user_name = value
         *
         * @return
         */
        public Criteria andStatusAUserNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("statusAUserName", value);
            return this;
        }

        /**
         * and status_a_user_name != value
         *
         * @return
         */
        public Criteria andStatusAUserNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("statusAUserName", value);
            return this;
        }

        /**
         * and status_a_user_name > value
         *
         * @return
         */
        public Criteria andStatusAUserNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("statusAUserName", value);
            return this;
        }

        /**
         * and status_a_user_name >= value
         *
         * @return
         */
        public Criteria andStatusAUserNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("statusAUserName", value);
            return this;
        }

        /**
         * and status_a_user_name < value
         *
         * @return
         */
        public Criteria andStatusAUserNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("statusAUserName", value);
            return this;
        }

        /**
         * and status_a_user_name <= value
         *
         * @return
         */
        public Criteria andStatusAUserNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("statusAUserName", value);
            return this;
        }

        /**
         * and status_a_user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAUserNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("statusAUserName", values);
            return this;
        }

        /**
         * and status_a_user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAUserNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("statusAUserName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and status_a_user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAUserNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("statusAUserName", values);
            return this;
        }

        /**
         * and status_a_user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andStatusAUserNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("statusAUserName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and status_a_user_name between value1 and value2
         *
         * @return
         */
        public Criteria andStatusAUserNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("statusAUserName", value1, value2);
            return this;
        }

        /**
         * and status_a_user_name not between value1 and value2
         *
         * @return
         */
        public Criteria andStatusAUserNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("statusAUserName", value1, value2);
            return this;
        }

        /**
         * and status_a_user_name like value
         *
         * @return
         */
        public Criteria andStatusAUserNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("statusAUserName", value);
            return this;
        }

        /**
         * and status_a_user_name not like value
         *
         * @return
         */
        public Criteria andStatusAUserNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("statusAUserName", value);
            return this;
        }

        /**
         * or status_a_user_name = ''
         *
         * @return
         */
        public Criteria orStatusAUserNameIsEmpty() {
            this.orEqualTo("statusAUserName", "");
            return this;
        }

        /**
         * or status_a_user_name != ''
         *
         * @return
         */
        public Criteria orStatusAUserNameIsNotEmpty() {
            this.orNotEqualTo("statusAUserName", "");
            return this;
        }

        /**
         * or status_a_user_name = value
         *
         * @return
         */
        public Criteria orStatusAUserNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("statusAUserName", value);
            return this;
        }

        /**
         * or status_a_user_name != value
         *
         * @return
         */
        public Criteria orStatusAUserNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("statusAUserName", value);
            return this;
        }

        /**
         * or status_a_user_name > value
         *
         * @return
         */
        public Criteria orStatusAUserNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("statusAUserName", value);
            return this;
        }

        /**
         * or status_a_user_name >= value
         *
         * @return
         */
        public Criteria orStatusAUserNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("statusAUserName", value);
            return this;
        }

        /**
         * or status_a_user_name < value
         *
         * @return
         */
        public Criteria orStatusAUserNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("statusAUserName", value);
            return this;
        }

        /**
         * or status_a_user_name <= value
         *
         * @return
         */
        public Criteria orStatusAUserNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("statusAUserName", value);
            return this;
        }

        /**
         * or status_a_user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAUserNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("statusAUserName", values);
            return this;
        }

        /**
         * or status_a_user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAUserNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("statusAUserName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or status_a_user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAUserNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("statusAUserName", values);
            return this;
        }

        /**
         * or status_a_user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orStatusAUserNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("statusAUserName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or status_a_user_name between value1 and value2
         *
         * @return
         */
        public Criteria orStatusAUserNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("statusAUserName", value1, value2);
            return this;
        }

        /**
         * or status_a_user_name not between value1 and value2
         *
         * @return
         */
        public Criteria orStatusAUserNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("statusAUserName", value1, value2);
            return this;
        }

        /**
         * or status_a_user_name like value
         *
         * @return
         */
        public Criteria orStatusAUserNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("statusAUserName", value);
            return this;
        }

        /**
         * or status_a_user_name not like value
         *
         * @return
         */
        public Criteria orStatusAUserNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("statusAUserName", value);
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
