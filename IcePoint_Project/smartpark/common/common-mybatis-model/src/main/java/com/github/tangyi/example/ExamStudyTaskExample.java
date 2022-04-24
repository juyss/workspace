package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.ExamStudyTask;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.Date;

/**
 * exam_study_task 学习任务表
 *
 * @author xh
 * @since 2020/12/12
 */
public class ExamStudyTaskExample extends AbstractExample<ExamStudyTask> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public ExamStudyTaskExample() {
        super(ExamStudyTask.class);
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
         * order by task_name
         *
         * @return
         */
        public OrderBy taskName() {
            this.orderBy("taskName");
            return this;
        }
        
        /**
         * order by task_description
         *
         * @return
         */
        public OrderBy taskDescription() {
            this.orderBy("taskDescription");
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
         * order by status
         *
         * @return
         */
        public OrderBy status() {
            this.orderBy("status");
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
         * order by audit_user_id
         *
         * @return
         */
        public OrderBy auditUserId() {
            this.orderBy("auditUserId");
            return this;
        }
        
        /**
         * order by course_num
         *
         * @return
         */
        public OrderBy courseNum() {
            this.orderBy("courseNum");
            return this;
        }
        
        /**
         * order by user_num
         *
         * @return
         */
        public OrderBy userNum() {
            this.orderBy("userNum");
            return this;
        }
        
        /**
         * order by is_public
         *
         * @return
         */
        public OrderBy isPublic() {
            this.orderBy("isPublic");
            return this;
        }
        
        /**
         * order by total_study_time
         *
         * @return
         */
        public OrderBy totalStudyTime() {
            this.orderBy("totalStudyTime");
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
         * order by pic
         *
         * @return
         */
        public OrderBy pic() {
            this.orderBy("pic");
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
         * and task_name = ''
         *
         * @return
         */
        public Criteria andTaskNameIsEmpty() {
            this.andEqualTo("taskName", "");
            return this;
        }

        /**
         * and task_name != ''
         *
         * @return
         */
        public Criteria andTaskNameIsNotEmpty() {
            this.andNotEqualTo("taskName", "");
            return this;
        }

        /**
         * and task_name = value
         *
         * @return
         */
        public Criteria andTaskNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("taskName", value);
            return this;
        }

        /**
         * and task_name != value
         *
         * @return
         */
        public Criteria andTaskNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("taskName", value);
            return this;
        }

        /**
         * and task_name > value
         *
         * @return
         */
        public Criteria andTaskNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("taskName", value);
            return this;
        }

        /**
         * and task_name >= value
         *
         * @return
         */
        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("taskName", value);
            return this;
        }

        /**
         * and task_name < value
         *
         * @return
         */
        public Criteria andTaskNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("taskName", value);
            return this;
        }

        /**
         * and task_name <= value
         *
         * @return
         */
        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("taskName", value);
            return this;
        }

        /**
         * and task_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTaskNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("taskName", values);
            return this;
        }

        /**
         * and task_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTaskNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("taskName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and task_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTaskNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("taskName", values);
            return this;
        }

        /**
         * and task_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTaskNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("taskName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and task_name between value1 and value2
         *
         * @return
         */
        public Criteria andTaskNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("taskName", value1, value2);
            return this;
        }

        /**
         * and task_name not between value1 and value2
         *
         * @return
         */
        public Criteria andTaskNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("taskName", value1, value2);
            return this;
        }

        /**
         * and task_name like value
         *
         * @return
         */
        public Criteria andTaskNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("taskName", value);
            return this;
        }

        /**
         * and task_name not like value
         *
         * @return
         */
        public Criteria andTaskNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("taskName", value);
            return this;
        }

        /**
         * or task_name = ''
         *
         * @return
         */
        public Criteria orTaskNameIsEmpty() {
            this.orEqualTo("taskName", "");
            return this;
        }

        /**
         * or task_name != ''
         *
         * @return
         */
        public Criteria orTaskNameIsNotEmpty() {
            this.orNotEqualTo("taskName", "");
            return this;
        }

        /**
         * or task_name = value
         *
         * @return
         */
        public Criteria orTaskNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("taskName", value);
            return this;
        }

        /**
         * or task_name != value
         *
         * @return
         */
        public Criteria orTaskNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("taskName", value);
            return this;
        }

        /**
         * or task_name > value
         *
         * @return
         */
        public Criteria orTaskNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("taskName", value);
            return this;
        }

        /**
         * or task_name >= value
         *
         * @return
         */
        public Criteria orTaskNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("taskName", value);
            return this;
        }

        /**
         * or task_name < value
         *
         * @return
         */
        public Criteria orTaskNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("taskName", value);
            return this;
        }

        /**
         * or task_name <= value
         *
         * @return
         */
        public Criteria orTaskNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("taskName", value);
            return this;
        }

        /**
         * or task_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTaskNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("taskName", values);
            return this;
        }

        /**
         * or task_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTaskNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("taskName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or task_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTaskNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("taskName", values);
            return this;
        }

        /**
         * or task_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTaskNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("taskName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or task_name between value1 and value2
         *
         * @return
         */
        public Criteria orTaskNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("taskName", value1, value2);
            return this;
        }

        /**
         * or task_name not between value1 and value2
         *
         * @return
         */
        public Criteria orTaskNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("taskName", value1, value2);
            return this;
        }

        /**
         * or task_name like value
         *
         * @return
         */
        public Criteria orTaskNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("taskName", value);
            return this;
        }

        /**
         * or task_name not like value
         *
         * @return
         */
        public Criteria orTaskNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("taskName", value);
            return this;
        }

        /**
         * and task_description = ''
         *
         * @return
         */
        public Criteria andTaskDescriptionIsEmpty() {
            this.andEqualTo("taskDescription", "");
            return this;
        }

        /**
         * and task_description != ''
         *
         * @return
         */
        public Criteria andTaskDescriptionIsNotEmpty() {
            this.andNotEqualTo("taskDescription", "");
            return this;
        }

        /**
         * and task_description = value
         *
         * @return
         */
        public Criteria andTaskDescriptionEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("taskDescription", value);
            return this;
        }

        /**
         * and task_description != value
         *
         * @return
         */
        public Criteria andTaskDescriptionNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("taskDescription", value);
            return this;
        }

        /**
         * and task_description > value
         *
         * @return
         */
        public Criteria andTaskDescriptionGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("taskDescription", value);
            return this;
        }

        /**
         * and task_description >= value
         *
         * @return
         */
        public Criteria andTaskDescriptionGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("taskDescription", value);
            return this;
        }

        /**
         * and task_description < value
         *
         * @return
         */
        public Criteria andTaskDescriptionLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("taskDescription", value);
            return this;
        }

        /**
         * and task_description <= value
         *
         * @return
         */
        public Criteria andTaskDescriptionLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("taskDescription", value);
            return this;
        }

        /**
         * and task_description in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTaskDescriptionIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("taskDescription", values);
            return this;
        }

        /**
         * and task_description in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTaskDescriptionIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("taskDescription", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and task_description not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTaskDescriptionNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("taskDescription", values);
            return this;
        }

        /**
         * and task_description not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTaskDescriptionNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("taskDescription", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and task_description between value1 and value2
         *
         * @return
         */
        public Criteria andTaskDescriptionBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("taskDescription", value1, value2);
            return this;
        }

        /**
         * and task_description not between value1 and value2
         *
         * @return
         */
        public Criteria andTaskDescriptionNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("taskDescription", value1, value2);
            return this;
        }

        /**
         * and task_description like value
         *
         * @return
         */
        public Criteria andTaskDescriptionLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("taskDescription", value);
            return this;
        }

        /**
         * and task_description not like value
         *
         * @return
         */
        public Criteria andTaskDescriptionNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("taskDescription", value);
            return this;
        }

        /**
         * or task_description = ''
         *
         * @return
         */
        public Criteria orTaskDescriptionIsEmpty() {
            this.orEqualTo("taskDescription", "");
            return this;
        }

        /**
         * or task_description != ''
         *
         * @return
         */
        public Criteria orTaskDescriptionIsNotEmpty() {
            this.orNotEqualTo("taskDescription", "");
            return this;
        }

        /**
         * or task_description = value
         *
         * @return
         */
        public Criteria orTaskDescriptionEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("taskDescription", value);
            return this;
        }

        /**
         * or task_description != value
         *
         * @return
         */
        public Criteria orTaskDescriptionNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("taskDescription", value);
            return this;
        }

        /**
         * or task_description > value
         *
         * @return
         */
        public Criteria orTaskDescriptionGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("taskDescription", value);
            return this;
        }

        /**
         * or task_description >= value
         *
         * @return
         */
        public Criteria orTaskDescriptionGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("taskDescription", value);
            return this;
        }

        /**
         * or task_description < value
         *
         * @return
         */
        public Criteria orTaskDescriptionLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("taskDescription", value);
            return this;
        }

        /**
         * or task_description <= value
         *
         * @return
         */
        public Criteria orTaskDescriptionLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("taskDescription", value);
            return this;
        }

        /**
         * or task_description in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTaskDescriptionIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("taskDescription", values);
            return this;
        }

        /**
         * or task_description in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTaskDescriptionIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("taskDescription", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or task_description not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTaskDescriptionNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("taskDescription", values);
            return this;
        }

        /**
         * or task_description not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTaskDescriptionNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("taskDescription", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or task_description between value1 and value2
         *
         * @return
         */
        public Criteria orTaskDescriptionBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("taskDescription", value1, value2);
            return this;
        }

        /**
         * or task_description not between value1 and value2
         *
         * @return
         */
        public Criteria orTaskDescriptionNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("taskDescription", value1, value2);
            return this;
        }

        /**
         * or task_description like value
         *
         * @return
         */
        public Criteria orTaskDescriptionLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("taskDescription", value);
            return this;
        }

        /**
         * or task_description not like value
         *
         * @return
         */
        public Criteria orTaskDescriptionNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("taskDescription", value);
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
         * and course_num = value
         *
         * @return
         */
        public Criteria andCourseNumEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("courseNum", value);
            return this;
        }

        /**
         * and course_num != value
         *
         * @return
         */
        public Criteria andCourseNumNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("courseNum", value);
            return this;
        }

        /**
         * and course_num > value
         *
         * @return
         */
        public Criteria andCourseNumGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("courseNum", value);
            return this;
        }

        /**
         * and course_num >= value
         *
         * @return
         */
        public Criteria andCourseNumGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("courseNum", value);
            return this;
        }

        /**
         * and course_num < value
         *
         * @return
         */
        public Criteria andCourseNumLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("courseNum", value);
            return this;
        }

        /**
         * and course_num <= value
         *
         * @return
         */
        public Criteria andCourseNumLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("courseNum", value);
            return this;
        }

        /**
         * and course_num in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseNumIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseNum", values);
            return this;
        }

        /**
         * and course_num in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseNumIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("courseNum", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_num not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseNumNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseNum", values);
            return this;
        }

        /**
         * and course_num not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCourseNumNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("courseNum", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and course_num between value1 and value2
         *
         * @return
         */
        public Criteria andCourseNumBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("courseNum", value1, value2);
            return this;
        }

        /**
         * and course_num not between value1 and value2
         *
         * @return
         */
        public Criteria andCourseNumNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("courseNum", value1, value2);
            return this;
        }

        /**
         * and course_num like value
         *
         * @return
         */
        public Criteria andCourseNumLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("courseNum", value);
            return this;
        }

        /**
         * and course_num not like value
         *
         * @return
         */
        public Criteria andCourseNumNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("courseNum", value);
            return this;
        }

        /**
         * or course_num = value
         *
         * @return
         */
        public Criteria orCourseNumEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("courseNum", value);
            return this;
        }

        /**
         * or course_num != value
         *
         * @return
         */
        public Criteria orCourseNumNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("courseNum", value);
            return this;
        }

        /**
         * or course_num > value
         *
         * @return
         */
        public Criteria orCourseNumGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("courseNum", value);
            return this;
        }

        /**
         * or course_num >= value
         *
         * @return
         */
        public Criteria orCourseNumGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("courseNum", value);
            return this;
        }

        /**
         * or course_num < value
         *
         * @return
         */
        public Criteria orCourseNumLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("courseNum", value);
            return this;
        }

        /**
         * or course_num <= value
         *
         * @return
         */
        public Criteria orCourseNumLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("courseNum", value);
            return this;
        }

        /**
         * or course_num in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseNumIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseNum", values);
            return this;
        }

        /**
         * or course_num in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseNumIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("courseNum", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_num not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseNumNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseNum", values);
            return this;
        }

        /**
         * or course_num not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCourseNumNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("courseNum", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or course_num between value1 and value2
         *
         * @return
         */
        public Criteria orCourseNumBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("courseNum", value1, value2);
            return this;
        }

        /**
         * or course_num not between value1 and value2
         *
         * @return
         */
        public Criteria orCourseNumNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("courseNum", value1, value2);
            return this;
        }

        /**
         * or course_num like value
         *
         * @return
         */
        public Criteria orCourseNumLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("courseNum", value);
            return this;
        }

        /**
         * or course_num not like value
         *
         * @return
         */
        public Criteria orCourseNumNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("courseNum", value);
            return this;
        }

        /**
         * and user_num = value
         *
         * @return
         */
        public Criteria andUserNumEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("userNum", value);
            return this;
        }

        /**
         * and user_num != value
         *
         * @return
         */
        public Criteria andUserNumNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("userNum", value);
            return this;
        }

        /**
         * and user_num > value
         *
         * @return
         */
        public Criteria andUserNumGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("userNum", value);
            return this;
        }

        /**
         * and user_num >= value
         *
         * @return
         */
        public Criteria andUserNumGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("userNum", value);
            return this;
        }

        /**
         * and user_num < value
         *
         * @return
         */
        public Criteria andUserNumLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("userNum", value);
            return this;
        }

        /**
         * and user_num <= value
         *
         * @return
         */
        public Criteria andUserNumLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("userNum", value);
            return this;
        }

        /**
         * and user_num in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserNumIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userNum", values);
            return this;
        }

        /**
         * and user_num in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserNumIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userNum", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_num not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserNumNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userNum", values);
            return this;
        }

        /**
         * and user_num not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserNumNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userNum", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_num between value1 and value2
         *
         * @return
         */
        public Criteria andUserNumBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("userNum", value1, value2);
            return this;
        }

        /**
         * and user_num not between value1 and value2
         *
         * @return
         */
        public Criteria andUserNumNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("userNum", value1, value2);
            return this;
        }

        /**
         * and user_num like value
         *
         * @return
         */
        public Criteria andUserNumLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("userNum", value);
            return this;
        }

        /**
         * and user_num not like value
         *
         * @return
         */
        public Criteria andUserNumNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("userNum", value);
            return this;
        }

        /**
         * or user_num = value
         *
         * @return
         */
        public Criteria orUserNumEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("userNum", value);
            return this;
        }

        /**
         * or user_num != value
         *
         * @return
         */
        public Criteria orUserNumNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("userNum", value);
            return this;
        }

        /**
         * or user_num > value
         *
         * @return
         */
        public Criteria orUserNumGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("userNum", value);
            return this;
        }

        /**
         * or user_num >= value
         *
         * @return
         */
        public Criteria orUserNumGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("userNum", value);
            return this;
        }

        /**
         * or user_num < value
         *
         * @return
         */
        public Criteria orUserNumLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("userNum", value);
            return this;
        }

        /**
         * or user_num <= value
         *
         * @return
         */
        public Criteria orUserNumLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("userNum", value);
            return this;
        }

        /**
         * or user_num in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserNumIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userNum", values);
            return this;
        }

        /**
         * or user_num in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserNumIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userNum", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_num not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserNumNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userNum", values);
            return this;
        }

        /**
         * or user_num not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserNumNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userNum", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_num between value1 and value2
         *
         * @return
         */
        public Criteria orUserNumBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("userNum", value1, value2);
            return this;
        }

        /**
         * or user_num not between value1 and value2
         *
         * @return
         */
        public Criteria orUserNumNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("userNum", value1, value2);
            return this;
        }

        /**
         * or user_num like value
         *
         * @return
         */
        public Criteria orUserNumLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("userNum", value);
            return this;
        }

        /**
         * or user_num not like value
         *
         * @return
         */
        public Criteria orUserNumNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("userNum", value);
            return this;
        }

        /**
         * and is_public = value
         *
         * @return
         */
        public Criteria andIsPublicEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("isPublic", value);
            return this;
        }

        /**
         * and is_public != value
         *
         * @return
         */
        public Criteria andIsPublicNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("isPublic", value);
            return this;
        }

        /**
         * and is_public > value
         *
         * @return
         */
        public Criteria andIsPublicGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("isPublic", value);
            return this;
        }

        /**
         * and is_public >= value
         *
         * @return
         */
        public Criteria andIsPublicGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("isPublic", value);
            return this;
        }

        /**
         * and is_public < value
         *
         * @return
         */
        public Criteria andIsPublicLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("isPublic", value);
            return this;
        }

        /**
         * and is_public <= value
         *
         * @return
         */
        public Criteria andIsPublicLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("isPublic", value);
            return this;
        }

        /**
         * and is_public in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIsPublicIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("isPublic", values);
            return this;
        }

        /**
         * and is_public in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIsPublicIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("isPublic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and is_public not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIsPublicNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("isPublic", values);
            return this;
        }

        /**
         * and is_public not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIsPublicNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("isPublic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and is_public between value1 and value2
         *
         * @return
         */
        public Criteria andIsPublicBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("isPublic", value1, value2);
            return this;
        }

        /**
         * and is_public not between value1 and value2
         *
         * @return
         */
        public Criteria andIsPublicNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("isPublic", value1, value2);
            return this;
        }

        /**
         * and is_public like value
         *
         * @return
         */
        public Criteria andIsPublicLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("isPublic", value);
            return this;
        }

        /**
         * and is_public not like value
         *
         * @return
         */
        public Criteria andIsPublicNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("isPublic", value);
            return this;
        }

        /**
         * or is_public = value
         *
         * @return
         */
        public Criteria orIsPublicEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("isPublic", value);
            return this;
        }

        /**
         * or is_public != value
         *
         * @return
         */
        public Criteria orIsPublicNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("isPublic", value);
            return this;
        }

        /**
         * or is_public > value
         *
         * @return
         */
        public Criteria orIsPublicGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("isPublic", value);
            return this;
        }

        /**
         * or is_public >= value
         *
         * @return
         */
        public Criteria orIsPublicGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("isPublic", value);
            return this;
        }

        /**
         * or is_public < value
         *
         * @return
         */
        public Criteria orIsPublicLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("isPublic", value);
            return this;
        }

        /**
         * or is_public <= value
         *
         * @return
         */
        public Criteria orIsPublicLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("isPublic", value);
            return this;
        }

        /**
         * or is_public in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIsPublicIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("isPublic", values);
            return this;
        }

        /**
         * or is_public in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIsPublicIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("isPublic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or is_public not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIsPublicNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("isPublic", values);
            return this;
        }

        /**
         * or is_public not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIsPublicNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("isPublic", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or is_public between value1 and value2
         *
         * @return
         */
        public Criteria orIsPublicBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("isPublic", value1, value2);
            return this;
        }

        /**
         * or is_public not between value1 and value2
         *
         * @return
         */
        public Criteria orIsPublicNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("isPublic", value1, value2);
            return this;
        }

        /**
         * or is_public like value
         *
         * @return
         */
        public Criteria orIsPublicLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("isPublic", value);
            return this;
        }

        /**
         * or is_public not like value
         *
         * @return
         */
        public Criteria orIsPublicNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("isPublic", value);
            return this;
        }

        /**
         * and total_study_time = value
         *
         * @return
         */
        public Criteria andTotalStudyTimeEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("totalStudyTime", value);
            return this;
        }

        /**
         * and total_study_time != value
         *
         * @return
         */
        public Criteria andTotalStudyTimeNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("totalStudyTime", value);
            return this;
        }

        /**
         * and total_study_time > value
         *
         * @return
         */
        public Criteria andTotalStudyTimeGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("totalStudyTime", value);
            return this;
        }

        /**
         * and total_study_time >= value
         *
         * @return
         */
        public Criteria andTotalStudyTimeGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("totalStudyTime", value);
            return this;
        }

        /**
         * and total_study_time < value
         *
         * @return
         */
        public Criteria andTotalStudyTimeLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("totalStudyTime", value);
            return this;
        }

        /**
         * and total_study_time <= value
         *
         * @return
         */
        public Criteria andTotalStudyTimeLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("totalStudyTime", value);
            return this;
        }

        /**
         * and total_study_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTotalStudyTimeIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("totalStudyTime", values);
            return this;
        }

        /**
         * and total_study_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTotalStudyTimeIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("totalStudyTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and total_study_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTotalStudyTimeNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("totalStudyTime", values);
            return this;
        }

        /**
         * and total_study_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTotalStudyTimeNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("totalStudyTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and total_study_time between value1 and value2
         *
         * @return
         */
        public Criteria andTotalStudyTimeBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("totalStudyTime", value1, value2);
            return this;
        }

        /**
         * and total_study_time not between value1 and value2
         *
         * @return
         */
        public Criteria andTotalStudyTimeNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("totalStudyTime", value1, value2);
            return this;
        }

        /**
         * and total_study_time like value
         *
         * @return
         */
        public Criteria andTotalStudyTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("totalStudyTime", value);
            return this;
        }

        /**
         * and total_study_time not like value
         *
         * @return
         */
        public Criteria andTotalStudyTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("totalStudyTime", value);
            return this;
        }

        /**
         * or total_study_time = value
         *
         * @return
         */
        public Criteria orTotalStudyTimeEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("totalStudyTime", value);
            return this;
        }

        /**
         * or total_study_time != value
         *
         * @return
         */
        public Criteria orTotalStudyTimeNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("totalStudyTime", value);
            return this;
        }

        /**
         * or total_study_time > value
         *
         * @return
         */
        public Criteria orTotalStudyTimeGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("totalStudyTime", value);
            return this;
        }

        /**
         * or total_study_time >= value
         *
         * @return
         */
        public Criteria orTotalStudyTimeGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("totalStudyTime", value);
            return this;
        }

        /**
         * or total_study_time < value
         *
         * @return
         */
        public Criteria orTotalStudyTimeLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("totalStudyTime", value);
            return this;
        }

        /**
         * or total_study_time <= value
         *
         * @return
         */
        public Criteria orTotalStudyTimeLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("totalStudyTime", value);
            return this;
        }

        /**
         * or total_study_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTotalStudyTimeIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("totalStudyTime", values);
            return this;
        }

        /**
         * or total_study_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTotalStudyTimeIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("totalStudyTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or total_study_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTotalStudyTimeNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("totalStudyTime", values);
            return this;
        }

        /**
         * or total_study_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTotalStudyTimeNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("totalStudyTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or total_study_time between value1 and value2
         *
         * @return
         */
        public Criteria orTotalStudyTimeBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("totalStudyTime", value1, value2);
            return this;
        }

        /**
         * or total_study_time not between value1 and value2
         *
         * @return
         */
        public Criteria orTotalStudyTimeNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("totalStudyTime", value1, value2);
            return this;
        }

        /**
         * or total_study_time like value
         *
         * @return
         */
        public Criteria orTotalStudyTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("totalStudyTime", value);
            return this;
        }

        /**
         * or total_study_time not like value
         *
         * @return
         */
        public Criteria orTotalStudyTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("totalStudyTime", value);
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
