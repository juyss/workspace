package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.SysUserDept;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

/**
 * sys_user_dept 
 *
 * @author xh
 * @since 2020/12/28
 */
public class SysUserDeptExample extends AbstractExample<SysUserDept> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public SysUserDeptExample() {
        super(SysUserDept.class);
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
         * order by user_id
         *
         * @return
         */
        public OrderBy userId() {
            this.orderBy("userId");
            return this;
        }
        
        /**
         * order by dept_id
         *
         * @return
         */
        public OrderBy deptId() {
            this.orderBy("deptId");
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
         * and user_id = value
         *
         * @return
         */
        public Criteria andUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("userId", value);
            return this;
        }

        /**
         * and user_id != value
         *
         * @return
         */
        public Criteria andUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("userId", value);
            return this;
        }

        /**
         * and user_id > value
         *
         * @return
         */
        public Criteria andUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("userId", value);
            return this;
        }

        /**
         * and user_id >= value
         *
         * @return
         */
        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("userId", value);
            return this;
        }

        /**
         * and user_id < value
         *
         * @return
         */
        public Criteria andUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("userId", value);
            return this;
        }

        /**
         * and user_id <= value
         *
         * @return
         */
        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("userId", value);
            return this;
        }

        /**
         * and user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userId", values);
            return this;
        }

        /**
         * and user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userId", values);
            return this;
        }

        /**
         * and user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_id between value1 and value2
         *
         * @return
         */
        public Criteria andUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("userId", value1, value2);
            return this;
        }

        /**
         * and user_id not between value1 and value2
         *
         * @return
         */
        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("userId", value1, value2);
            return this;
        }

        /**
         * and user_id like value
         *
         * @return
         */
        public Criteria andUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("userId", value);
            return this;
        }

        /**
         * and user_id not like value
         *
         * @return
         */
        public Criteria andUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("userId", value);
            return this;
        }

        /**
         * or user_id = value
         *
         * @return
         */
        public Criteria orUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("userId", value);
            return this;
        }

        /**
         * or user_id != value
         *
         * @return
         */
        public Criteria orUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("userId", value);
            return this;
        }

        /**
         * or user_id > value
         *
         * @return
         */
        public Criteria orUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("userId", value);
            return this;
        }

        /**
         * or user_id >= value
         *
         * @return
         */
        public Criteria orUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("userId", value);
            return this;
        }

        /**
         * or user_id < value
         *
         * @return
         */
        public Criteria orUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("userId", value);
            return this;
        }

        /**
         * or user_id <= value
         *
         * @return
         */
        public Criteria orUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("userId", value);
            return this;
        }

        /**
         * or user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userId", values);
            return this;
        }

        /**
         * or user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userId", values);
            return this;
        }

        /**
         * or user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_id between value1 and value2
         *
         * @return
         */
        public Criteria orUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("userId", value1, value2);
            return this;
        }

        /**
         * or user_id not between value1 and value2
         *
         * @return
         */
        public Criteria orUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("userId", value1, value2);
            return this;
        }

        /**
         * or user_id like value
         *
         * @return
         */
        public Criteria orUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("userId", value);
            return this;
        }

        /**
         * or user_id not like value
         *
         * @return
         */
        public Criteria orUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("userId", value);
            return this;
        }

        /**
         * and dept_id = value
         *
         * @return
         */
        public Criteria andDeptIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("deptId", value);
            return this;
        }

        /**
         * and dept_id != value
         *
         * @return
         */
        public Criteria andDeptIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("deptId", value);
            return this;
        }

        /**
         * and dept_id > value
         *
         * @return
         */
        public Criteria andDeptIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("deptId", value);
            return this;
        }

        /**
         * and dept_id >= value
         *
         * @return
         */
        public Criteria andDeptIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("deptId", value);
            return this;
        }

        /**
         * and dept_id < value
         *
         * @return
         */
        public Criteria andDeptIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("deptId", value);
            return this;
        }

        /**
         * and dept_id <= value
         *
         * @return
         */
        public Criteria andDeptIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("deptId", value);
            return this;
        }

        /**
         * and dept_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andDeptIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("deptId", values);
            return this;
        }

        /**
         * and dept_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andDeptIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("deptId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and dept_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andDeptIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("deptId", values);
            return this;
        }

        /**
         * and dept_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andDeptIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("deptId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and dept_id between value1 and value2
         *
         * @return
         */
        public Criteria andDeptIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("deptId", value1, value2);
            return this;
        }

        /**
         * and dept_id not between value1 and value2
         *
         * @return
         */
        public Criteria andDeptIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("deptId", value1, value2);
            return this;
        }

        /**
         * and dept_id like value
         *
         * @return
         */
        public Criteria andDeptIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("deptId", value);
            return this;
        }

        /**
         * and dept_id not like value
         *
         * @return
         */
        public Criteria andDeptIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("deptId", value);
            return this;
        }

        /**
         * or dept_id = value
         *
         * @return
         */
        public Criteria orDeptIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("deptId", value);
            return this;
        }

        /**
         * or dept_id != value
         *
         * @return
         */
        public Criteria orDeptIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("deptId", value);
            return this;
        }

        /**
         * or dept_id > value
         *
         * @return
         */
        public Criteria orDeptIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("deptId", value);
            return this;
        }

        /**
         * or dept_id >= value
         *
         * @return
         */
        public Criteria orDeptIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("deptId", value);
            return this;
        }

        /**
         * or dept_id < value
         *
         * @return
         */
        public Criteria orDeptIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("deptId", value);
            return this;
        }

        /**
         * or dept_id <= value
         *
         * @return
         */
        public Criteria orDeptIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("deptId", value);
            return this;
        }

        /**
         * or dept_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orDeptIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("deptId", values);
            return this;
        }

        /**
         * or dept_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orDeptIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("deptId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or dept_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orDeptIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("deptId", values);
            return this;
        }

        /**
         * or dept_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orDeptIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("deptId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or dept_id between value1 and value2
         *
         * @return
         */
        public Criteria orDeptIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("deptId", value1, value2);
            return this;
        }

        /**
         * or dept_id not between value1 and value2
         *
         * @return
         */
        public Criteria orDeptIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("deptId", value1, value2);
            return this;
        }

        /**
         * or dept_id like value
         *
         * @return
         */
        public Criteria orDeptIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("deptId", value);
            return this;
        }

        /**
         * or dept_id not like value
         *
         * @return
         */
        public Criteria orDeptIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("deptId", value);
            return this;
        }


    }

}
