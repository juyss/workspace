package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.MainUser;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.Date;

/**
 * main_user 
 *
 * @author xh
 * @since 2020/11/05
 */
public class MainUserExample extends AbstractExample<MainUser> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public MainUserExample() {
        super(MainUser.class);
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
         * order by gender
         *
         * @return
         */
        public OrderBy gender() {
            this.orderBy("gender");
            return this;
        }
        
        /**
         * order by login_name
         *
         * @return
         */
        public OrderBy loginName() {
            this.orderBy("loginName");
            return this;
        }
        
        /**
         * order by user_name
         *
         * @return
         */
        public OrderBy userName() {
            this.orderBy("userName");
            return this;
        }
        
        /**
         * order by user_mobile
         *
         * @return
         */
        public OrderBy userMobile() {
            this.orderBy("userMobile");
            return this;
        }
        
        /**
         * order by user_mail
         *
         * @return
         */
        public OrderBy userMail() {
            this.orderBy("userMail");
            return this;
        }
        
        /**
         * order by user_oph
         *
         * @return
         */
        public OrderBy userOph() {
            this.orderBy("userOph");
            return this;
        }
        
        /**
         * order by note
         *
         * @return
         */
        public OrderBy note() {
            this.orderBy("note");
            return this;
        }
        
        /**
         * order by sub_user_id
         *
         * @return
         */
        public OrderBy subUserId() {
            this.orderBy("subUserId");
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
        public Criteria andUserIdEqualTo(Integer value) {
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
        public Criteria andUserIdNotEqualTo(Integer value) {
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
        public Criteria andUserIdGreaterThan(Integer value) {
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
        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
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
        public Criteria andUserIdLessThan(Integer value) {
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
        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
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
        public Criteria andUserIdIn(Iterable<Integer> values) {
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
        public Criteria andUserIdIn(Integer... values) {
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
        public Criteria andUserIdNotIn(Iterable<Integer> values) {
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
        public Criteria andUserIdNotIn(Integer[] values) {
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
        public Criteria andUserIdBetween(Integer value1, Integer value2) {
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
        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
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
        public Criteria orUserIdEqualTo(Integer value) {
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
        public Criteria orUserIdNotEqualTo(Integer value) {
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
        public Criteria orUserIdGreaterThan(Integer value) {
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
        public Criteria orUserIdGreaterThanOrEqualTo(Integer value) {
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
        public Criteria orUserIdLessThan(Integer value) {
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
        public Criteria orUserIdLessThanOrEqualTo(Integer value) {
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
        public Criteria orUserIdIn(Iterable<Integer> values) {
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
        public Criteria orUserIdIn(Integer[] values) {
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
        public Criteria orUserIdNotIn(Iterable<Integer> values) {
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
        public Criteria orUserIdNotIn(Integer[] values) {
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
        public Criteria orUserIdBetween(Integer value1, Integer value2) {
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
        public Criteria orUserIdNotBetween(Integer value1, Integer value2) {
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
         * and gender = value
         *
         * @return
         */
        public Criteria andGenderEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("gender", value);
            return this;
        }

        /**
         * and gender != value
         *
         * @return
         */
        public Criteria andGenderNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("gender", value);
            return this;
        }

        /**
         * and gender > value
         *
         * @return
         */
        public Criteria andGenderGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("gender", value);
            return this;
        }

        /**
         * and gender >= value
         *
         * @return
         */
        public Criteria andGenderGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("gender", value);
            return this;
        }

        /**
         * and gender < value
         *
         * @return
         */
        public Criteria andGenderLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("gender", value);
            return this;
        }

        /**
         * and gender <= value
         *
         * @return
         */
        public Criteria andGenderLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("gender", value);
            return this;
        }

        /**
         * and gender in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andGenderIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("gender", values);
            return this;
        }

        /**
         * and gender in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andGenderIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("gender", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and gender not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andGenderNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("gender", values);
            return this;
        }

        /**
         * and gender not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andGenderNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("gender", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and gender between value1 and value2
         *
         * @return
         */
        public Criteria andGenderBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("gender", value1, value2);
            return this;
        }

        /**
         * and gender not between value1 and value2
         *
         * @return
         */
        public Criteria andGenderNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("gender", value1, value2);
            return this;
        }

        /**
         * and gender like value
         *
         * @return
         */
        public Criteria andGenderLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("gender", value);
            return this;
        }

        /**
         * and gender not like value
         *
         * @return
         */
        public Criteria andGenderNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("gender", value);
            return this;
        }

        /**
         * or gender = value
         *
         * @return
         */
        public Criteria orGenderEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("gender", value);
            return this;
        }

        /**
         * or gender != value
         *
         * @return
         */
        public Criteria orGenderNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("gender", value);
            return this;
        }

        /**
         * or gender > value
         *
         * @return
         */
        public Criteria orGenderGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("gender", value);
            return this;
        }

        /**
         * or gender >= value
         *
         * @return
         */
        public Criteria orGenderGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("gender", value);
            return this;
        }

        /**
         * or gender < value
         *
         * @return
         */
        public Criteria orGenderLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("gender", value);
            return this;
        }

        /**
         * or gender <= value
         *
         * @return
         */
        public Criteria orGenderLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("gender", value);
            return this;
        }

        /**
         * or gender in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orGenderIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("gender", values);
            return this;
        }

        /**
         * or gender in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orGenderIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("gender", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or gender not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orGenderNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("gender", values);
            return this;
        }

        /**
         * or gender not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orGenderNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("gender", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or gender between value1 and value2
         *
         * @return
         */
        public Criteria orGenderBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("gender", value1, value2);
            return this;
        }

        /**
         * or gender not between value1 and value2
         *
         * @return
         */
        public Criteria orGenderNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("gender", value1, value2);
            return this;
        }

        /**
         * or gender like value
         *
         * @return
         */
        public Criteria orGenderLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("gender", value);
            return this;
        }

        /**
         * or gender not like value
         *
         * @return
         */
        public Criteria orGenderNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("gender", value);
            return this;
        }

        /**
         * and login_name = ''
         *
         * @return
         */
        public Criteria andLoginNameIsEmpty() {
            this.andEqualTo("loginName", "");
            return this;
        }

        /**
         * and login_name != ''
         *
         * @return
         */
        public Criteria andLoginNameIsNotEmpty() {
            this.andNotEqualTo("loginName", "");
            return this;
        }

        /**
         * and login_name = value
         *
         * @return
         */
        public Criteria andLoginNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("loginName", value);
            return this;
        }

        /**
         * and login_name != value
         *
         * @return
         */
        public Criteria andLoginNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("loginName", value);
            return this;
        }

        /**
         * and login_name > value
         *
         * @return
         */
        public Criteria andLoginNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("loginName", value);
            return this;
        }

        /**
         * and login_name >= value
         *
         * @return
         */
        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("loginName", value);
            return this;
        }

        /**
         * and login_name < value
         *
         * @return
         */
        public Criteria andLoginNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("loginName", value);
            return this;
        }

        /**
         * and login_name <= value
         *
         * @return
         */
        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("loginName", value);
            return this;
        }

        /**
         * and login_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andLoginNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("loginName", values);
            return this;
        }

        /**
         * and login_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andLoginNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("loginName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and login_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andLoginNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("loginName", values);
            return this;
        }

        /**
         * and login_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andLoginNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("loginName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and login_name between value1 and value2
         *
         * @return
         */
        public Criteria andLoginNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("loginName", value1, value2);
            return this;
        }

        /**
         * and login_name not between value1 and value2
         *
         * @return
         */
        public Criteria andLoginNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("loginName", value1, value2);
            return this;
        }

        /**
         * and login_name like value
         *
         * @return
         */
        public Criteria andLoginNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("loginName", value);
            return this;
        }

        /**
         * and login_name not like value
         *
         * @return
         */
        public Criteria andLoginNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("loginName", value);
            return this;
        }

        /**
         * or login_name = ''
         *
         * @return
         */
        public Criteria orLoginNameIsEmpty() {
            this.orEqualTo("loginName", "");
            return this;
        }

        /**
         * or login_name != ''
         *
         * @return
         */
        public Criteria orLoginNameIsNotEmpty() {
            this.orNotEqualTo("loginName", "");
            return this;
        }

        /**
         * or login_name = value
         *
         * @return
         */
        public Criteria orLoginNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("loginName", value);
            return this;
        }

        /**
         * or login_name != value
         *
         * @return
         */
        public Criteria orLoginNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("loginName", value);
            return this;
        }

        /**
         * or login_name > value
         *
         * @return
         */
        public Criteria orLoginNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("loginName", value);
            return this;
        }

        /**
         * or login_name >= value
         *
         * @return
         */
        public Criteria orLoginNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("loginName", value);
            return this;
        }

        /**
         * or login_name < value
         *
         * @return
         */
        public Criteria orLoginNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("loginName", value);
            return this;
        }

        /**
         * or login_name <= value
         *
         * @return
         */
        public Criteria orLoginNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("loginName", value);
            return this;
        }

        /**
         * or login_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orLoginNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("loginName", values);
            return this;
        }

        /**
         * or login_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orLoginNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("loginName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or login_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orLoginNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("loginName", values);
            return this;
        }

        /**
         * or login_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orLoginNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("loginName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or login_name between value1 and value2
         *
         * @return
         */
        public Criteria orLoginNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("loginName", value1, value2);
            return this;
        }

        /**
         * or login_name not between value1 and value2
         *
         * @return
         */
        public Criteria orLoginNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("loginName", value1, value2);
            return this;
        }

        /**
         * or login_name like value
         *
         * @return
         */
        public Criteria orLoginNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("loginName", value);
            return this;
        }

        /**
         * or login_name not like value
         *
         * @return
         */
        public Criteria orLoginNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("loginName", value);
            return this;
        }

        /**
         * and user_name = ''
         *
         * @return
         */
        public Criteria andUserNameIsEmpty() {
            this.andEqualTo("userName", "");
            return this;
        }

        /**
         * and user_name != ''
         *
         * @return
         */
        public Criteria andUserNameIsNotEmpty() {
            this.andNotEqualTo("userName", "");
            return this;
        }

        /**
         * and user_name = value
         *
         * @return
         */
        public Criteria andUserNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("userName", value);
            return this;
        }

        /**
         * and user_name != value
         *
         * @return
         */
        public Criteria andUserNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("userName", value);
            return this;
        }

        /**
         * and user_name > value
         *
         * @return
         */
        public Criteria andUserNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("userName", value);
            return this;
        }

        /**
         * and user_name >= value
         *
         * @return
         */
        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("userName", value);
            return this;
        }

        /**
         * and user_name < value
         *
         * @return
         */
        public Criteria andUserNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("userName", value);
            return this;
        }

        /**
         * and user_name <= value
         *
         * @return
         */
        public Criteria andUserNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("userName", value);
            return this;
        }

        /**
         * and user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userName", values);
            return this;
        }

        /**
         * and user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userName", values);
            return this;
        }

        /**
         * and user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_name between value1 and value2
         *
         * @return
         */
        public Criteria andUserNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("userName", value1, value2);
            return this;
        }

        /**
         * and user_name not between value1 and value2
         *
         * @return
         */
        public Criteria andUserNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("userName", value1, value2);
            return this;
        }

        /**
         * and user_name like value
         *
         * @return
         */
        public Criteria andUserNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("userName", value);
            return this;
        }

        /**
         * and user_name not like value
         *
         * @return
         */
        public Criteria andUserNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("userName", value);
            return this;
        }

        /**
         * or user_name = ''
         *
         * @return
         */
        public Criteria orUserNameIsEmpty() {
            this.orEqualTo("userName", "");
            return this;
        }

        /**
         * or user_name != ''
         *
         * @return
         */
        public Criteria orUserNameIsNotEmpty() {
            this.orNotEqualTo("userName", "");
            return this;
        }

        /**
         * or user_name = value
         *
         * @return
         */
        public Criteria orUserNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("userName", value);
            return this;
        }

        /**
         * or user_name != value
         *
         * @return
         */
        public Criteria orUserNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("userName", value);
            return this;
        }

        /**
         * or user_name > value
         *
         * @return
         */
        public Criteria orUserNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("userName", value);
            return this;
        }

        /**
         * or user_name >= value
         *
         * @return
         */
        public Criteria orUserNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("userName", value);
            return this;
        }

        /**
         * or user_name < value
         *
         * @return
         */
        public Criteria orUserNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("userName", value);
            return this;
        }

        /**
         * or user_name <= value
         *
         * @return
         */
        public Criteria orUserNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("userName", value);
            return this;
        }

        /**
         * or user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userName", values);
            return this;
        }

        /**
         * or user_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userName", values);
            return this;
        }

        /**
         * or user_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_name between value1 and value2
         *
         * @return
         */
        public Criteria orUserNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("userName", value1, value2);
            return this;
        }

        /**
         * or user_name not between value1 and value2
         *
         * @return
         */
        public Criteria orUserNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("userName", value1, value2);
            return this;
        }

        /**
         * or user_name like value
         *
         * @return
         */
        public Criteria orUserNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("userName", value);
            return this;
        }

        /**
         * or user_name not like value
         *
         * @return
         */
        public Criteria orUserNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("userName", value);
            return this;
        }

        /**
         * and user_mobile = ''
         *
         * @return
         */
        public Criteria andUserMobileIsEmpty() {
            this.andEqualTo("userMobile", "");
            return this;
        }

        /**
         * and user_mobile != ''
         *
         * @return
         */
        public Criteria andUserMobileIsNotEmpty() {
            this.andNotEqualTo("userMobile", "");
            return this;
        }

        /**
         * and user_mobile = value
         *
         * @return
         */
        public Criteria andUserMobileEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("userMobile", value);
            return this;
        }

        /**
         * and user_mobile != value
         *
         * @return
         */
        public Criteria andUserMobileNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("userMobile", value);
            return this;
        }

        /**
         * and user_mobile > value
         *
         * @return
         */
        public Criteria andUserMobileGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("userMobile", value);
            return this;
        }

        /**
         * and user_mobile >= value
         *
         * @return
         */
        public Criteria andUserMobileGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("userMobile", value);
            return this;
        }

        /**
         * and user_mobile < value
         *
         * @return
         */
        public Criteria andUserMobileLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("userMobile", value);
            return this;
        }

        /**
         * and user_mobile <= value
         *
         * @return
         */
        public Criteria andUserMobileLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("userMobile", value);
            return this;
        }

        /**
         * and user_mobile in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserMobileIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userMobile", values);
            return this;
        }

        /**
         * and user_mobile in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserMobileIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userMobile", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_mobile not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserMobileNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userMobile", values);
            return this;
        }

        /**
         * and user_mobile not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserMobileNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userMobile", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_mobile between value1 and value2
         *
         * @return
         */
        public Criteria andUserMobileBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("userMobile", value1, value2);
            return this;
        }

        /**
         * and user_mobile not between value1 and value2
         *
         * @return
         */
        public Criteria andUserMobileNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("userMobile", value1, value2);
            return this;
        }

        /**
         * and user_mobile like value
         *
         * @return
         */
        public Criteria andUserMobileLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("userMobile", value);
            return this;
        }

        /**
         * and user_mobile not like value
         *
         * @return
         */
        public Criteria andUserMobileNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("userMobile", value);
            return this;
        }

        /**
         * or user_mobile = ''
         *
         * @return
         */
        public Criteria orUserMobileIsEmpty() {
            this.orEqualTo("userMobile", "");
            return this;
        }

        /**
         * or user_mobile != ''
         *
         * @return
         */
        public Criteria orUserMobileIsNotEmpty() {
            this.orNotEqualTo("userMobile", "");
            return this;
        }

        /**
         * or user_mobile = value
         *
         * @return
         */
        public Criteria orUserMobileEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("userMobile", value);
            return this;
        }

        /**
         * or user_mobile != value
         *
         * @return
         */
        public Criteria orUserMobileNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("userMobile", value);
            return this;
        }

        /**
         * or user_mobile > value
         *
         * @return
         */
        public Criteria orUserMobileGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("userMobile", value);
            return this;
        }

        /**
         * or user_mobile >= value
         *
         * @return
         */
        public Criteria orUserMobileGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("userMobile", value);
            return this;
        }

        /**
         * or user_mobile < value
         *
         * @return
         */
        public Criteria orUserMobileLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("userMobile", value);
            return this;
        }

        /**
         * or user_mobile <= value
         *
         * @return
         */
        public Criteria orUserMobileLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("userMobile", value);
            return this;
        }

        /**
         * or user_mobile in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserMobileIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userMobile", values);
            return this;
        }

        /**
         * or user_mobile in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserMobileIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userMobile", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_mobile not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserMobileNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userMobile", values);
            return this;
        }

        /**
         * or user_mobile not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserMobileNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userMobile", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_mobile between value1 and value2
         *
         * @return
         */
        public Criteria orUserMobileBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("userMobile", value1, value2);
            return this;
        }

        /**
         * or user_mobile not between value1 and value2
         *
         * @return
         */
        public Criteria orUserMobileNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("userMobile", value1, value2);
            return this;
        }

        /**
         * or user_mobile like value
         *
         * @return
         */
        public Criteria orUserMobileLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("userMobile", value);
            return this;
        }

        /**
         * or user_mobile not like value
         *
         * @return
         */
        public Criteria orUserMobileNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("userMobile", value);
            return this;
        }

        /**
         * and user_mail = ''
         *
         * @return
         */
        public Criteria andUserMailIsEmpty() {
            this.andEqualTo("userMail", "");
            return this;
        }

        /**
         * and user_mail != ''
         *
         * @return
         */
        public Criteria andUserMailIsNotEmpty() {
            this.andNotEqualTo("userMail", "");
            return this;
        }

        /**
         * and user_mail = value
         *
         * @return
         */
        public Criteria andUserMailEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("userMail", value);
            return this;
        }

        /**
         * and user_mail != value
         *
         * @return
         */
        public Criteria andUserMailNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("userMail", value);
            return this;
        }

        /**
         * and user_mail > value
         *
         * @return
         */
        public Criteria andUserMailGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("userMail", value);
            return this;
        }

        /**
         * and user_mail >= value
         *
         * @return
         */
        public Criteria andUserMailGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("userMail", value);
            return this;
        }

        /**
         * and user_mail < value
         *
         * @return
         */
        public Criteria andUserMailLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("userMail", value);
            return this;
        }

        /**
         * and user_mail <= value
         *
         * @return
         */
        public Criteria andUserMailLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("userMail", value);
            return this;
        }

        /**
         * and user_mail in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserMailIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userMail", values);
            return this;
        }

        /**
         * and user_mail in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserMailIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userMail", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_mail not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserMailNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userMail", values);
            return this;
        }

        /**
         * and user_mail not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserMailNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userMail", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_mail between value1 and value2
         *
         * @return
         */
        public Criteria andUserMailBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("userMail", value1, value2);
            return this;
        }

        /**
         * and user_mail not between value1 and value2
         *
         * @return
         */
        public Criteria andUserMailNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("userMail", value1, value2);
            return this;
        }

        /**
         * and user_mail like value
         *
         * @return
         */
        public Criteria andUserMailLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("userMail", value);
            return this;
        }

        /**
         * and user_mail not like value
         *
         * @return
         */
        public Criteria andUserMailNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("userMail", value);
            return this;
        }

        /**
         * or user_mail = ''
         *
         * @return
         */
        public Criteria orUserMailIsEmpty() {
            this.orEqualTo("userMail", "");
            return this;
        }

        /**
         * or user_mail != ''
         *
         * @return
         */
        public Criteria orUserMailIsNotEmpty() {
            this.orNotEqualTo("userMail", "");
            return this;
        }

        /**
         * or user_mail = value
         *
         * @return
         */
        public Criteria orUserMailEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("userMail", value);
            return this;
        }

        /**
         * or user_mail != value
         *
         * @return
         */
        public Criteria orUserMailNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("userMail", value);
            return this;
        }

        /**
         * or user_mail > value
         *
         * @return
         */
        public Criteria orUserMailGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("userMail", value);
            return this;
        }

        /**
         * or user_mail >= value
         *
         * @return
         */
        public Criteria orUserMailGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("userMail", value);
            return this;
        }

        /**
         * or user_mail < value
         *
         * @return
         */
        public Criteria orUserMailLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("userMail", value);
            return this;
        }

        /**
         * or user_mail <= value
         *
         * @return
         */
        public Criteria orUserMailLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("userMail", value);
            return this;
        }

        /**
         * or user_mail in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserMailIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userMail", values);
            return this;
        }

        /**
         * or user_mail in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserMailIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userMail", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_mail not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserMailNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userMail", values);
            return this;
        }

        /**
         * or user_mail not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserMailNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userMail", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_mail between value1 and value2
         *
         * @return
         */
        public Criteria orUserMailBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("userMail", value1, value2);
            return this;
        }

        /**
         * or user_mail not between value1 and value2
         *
         * @return
         */
        public Criteria orUserMailNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("userMail", value1, value2);
            return this;
        }

        /**
         * or user_mail like value
         *
         * @return
         */
        public Criteria orUserMailLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("userMail", value);
            return this;
        }

        /**
         * or user_mail not like value
         *
         * @return
         */
        public Criteria orUserMailNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("userMail", value);
            return this;
        }

        /**
         * and user_oph = ''
         *
         * @return
         */
        public Criteria andUserOphIsEmpty() {
            this.andEqualTo("userOph", "");
            return this;
        }

        /**
         * and user_oph != ''
         *
         * @return
         */
        public Criteria andUserOphIsNotEmpty() {
            this.andNotEqualTo("userOph", "");
            return this;
        }

        /**
         * and user_oph = value
         *
         * @return
         */
        public Criteria andUserOphEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("userOph", value);
            return this;
        }

        /**
         * and user_oph != value
         *
         * @return
         */
        public Criteria andUserOphNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("userOph", value);
            return this;
        }

        /**
         * and user_oph > value
         *
         * @return
         */
        public Criteria andUserOphGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("userOph", value);
            return this;
        }

        /**
         * and user_oph >= value
         *
         * @return
         */
        public Criteria andUserOphGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("userOph", value);
            return this;
        }

        /**
         * and user_oph < value
         *
         * @return
         */
        public Criteria andUserOphLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("userOph", value);
            return this;
        }

        /**
         * and user_oph <= value
         *
         * @return
         */
        public Criteria andUserOphLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("userOph", value);
            return this;
        }

        /**
         * and user_oph in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserOphIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userOph", values);
            return this;
        }

        /**
         * and user_oph in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserOphIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("userOph", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_oph not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserOphNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userOph", values);
            return this;
        }

        /**
         * and user_oph not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andUserOphNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("userOph", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and user_oph between value1 and value2
         *
         * @return
         */
        public Criteria andUserOphBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("userOph", value1, value2);
            return this;
        }

        /**
         * and user_oph not between value1 and value2
         *
         * @return
         */
        public Criteria andUserOphNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("userOph", value1, value2);
            return this;
        }

        /**
         * and user_oph like value
         *
         * @return
         */
        public Criteria andUserOphLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("userOph", value);
            return this;
        }

        /**
         * and user_oph not like value
         *
         * @return
         */
        public Criteria andUserOphNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("userOph", value);
            return this;
        }

        /**
         * or user_oph = ''
         *
         * @return
         */
        public Criteria orUserOphIsEmpty() {
            this.orEqualTo("userOph", "");
            return this;
        }

        /**
         * or user_oph != ''
         *
         * @return
         */
        public Criteria orUserOphIsNotEmpty() {
            this.orNotEqualTo("userOph", "");
            return this;
        }

        /**
         * or user_oph = value
         *
         * @return
         */
        public Criteria orUserOphEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("userOph", value);
            return this;
        }

        /**
         * or user_oph != value
         *
         * @return
         */
        public Criteria orUserOphNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("userOph", value);
            return this;
        }

        /**
         * or user_oph > value
         *
         * @return
         */
        public Criteria orUserOphGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("userOph", value);
            return this;
        }

        /**
         * or user_oph >= value
         *
         * @return
         */
        public Criteria orUserOphGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("userOph", value);
            return this;
        }

        /**
         * or user_oph < value
         *
         * @return
         */
        public Criteria orUserOphLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("userOph", value);
            return this;
        }

        /**
         * or user_oph <= value
         *
         * @return
         */
        public Criteria orUserOphLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("userOph", value);
            return this;
        }

        /**
         * or user_oph in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserOphIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userOph", values);
            return this;
        }

        /**
         * or user_oph in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserOphIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("userOph", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_oph not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserOphNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userOph", values);
            return this;
        }

        /**
         * or user_oph not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orUserOphNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("userOph", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or user_oph between value1 and value2
         *
         * @return
         */
        public Criteria orUserOphBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("userOph", value1, value2);
            return this;
        }

        /**
         * or user_oph not between value1 and value2
         *
         * @return
         */
        public Criteria orUserOphNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("userOph", value1, value2);
            return this;
        }

        /**
         * or user_oph like value
         *
         * @return
         */
        public Criteria orUserOphLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("userOph", value);
            return this;
        }

        /**
         * or user_oph not like value
         *
         * @return
         */
        public Criteria orUserOphNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("userOph", value);
            return this;
        }

        /**
         * and note = ''
         *
         * @return
         */
        public Criteria andNoteIsEmpty() {
            this.andEqualTo("note", "");
            return this;
        }

        /**
         * and note != ''
         *
         * @return
         */
        public Criteria andNoteIsNotEmpty() {
            this.andNotEqualTo("note", "");
            return this;
        }

        /**
         * and note = value
         *
         * @return
         */
        public Criteria andNoteEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("note", value);
            return this;
        }

        /**
         * and note != value
         *
         * @return
         */
        public Criteria andNoteNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("note", value);
            return this;
        }

        /**
         * and note > value
         *
         * @return
         */
        public Criteria andNoteGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("note", value);
            return this;
        }

        /**
         * and note >= value
         *
         * @return
         */
        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("note", value);
            return this;
        }

        /**
         * and note < value
         *
         * @return
         */
        public Criteria andNoteLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("note", value);
            return this;
        }

        /**
         * and note <= value
         *
         * @return
         */
        public Criteria andNoteLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("note", value);
            return this;
        }

        /**
         * and note in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andNoteIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("note", values);
            return this;
        }

        /**
         * and note in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andNoteIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("note", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and note not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andNoteNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("note", values);
            return this;
        }

        /**
         * and note not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andNoteNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("note", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and note between value1 and value2
         *
         * @return
         */
        public Criteria andNoteBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("note", value1, value2);
            return this;
        }

        /**
         * and note not between value1 and value2
         *
         * @return
         */
        public Criteria andNoteNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("note", value1, value2);
            return this;
        }

        /**
         * and note like value
         *
         * @return
         */
        public Criteria andNoteLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("note", value);
            return this;
        }

        /**
         * and note not like value
         *
         * @return
         */
        public Criteria andNoteNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("note", value);
            return this;
        }

        /**
         * or note = ''
         *
         * @return
         */
        public Criteria orNoteIsEmpty() {
            this.orEqualTo("note", "");
            return this;
        }

        /**
         * or note != ''
         *
         * @return
         */
        public Criteria orNoteIsNotEmpty() {
            this.orNotEqualTo("note", "");
            return this;
        }

        /**
         * or note = value
         *
         * @return
         */
        public Criteria orNoteEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("note", value);
            return this;
        }

        /**
         * or note != value
         *
         * @return
         */
        public Criteria orNoteNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("note", value);
            return this;
        }

        /**
         * or note > value
         *
         * @return
         */
        public Criteria orNoteGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("note", value);
            return this;
        }

        /**
         * or note >= value
         *
         * @return
         */
        public Criteria orNoteGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("note", value);
            return this;
        }

        /**
         * or note < value
         *
         * @return
         */
        public Criteria orNoteLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("note", value);
            return this;
        }

        /**
         * or note <= value
         *
         * @return
         */
        public Criteria orNoteLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("note", value);
            return this;
        }

        /**
         * or note in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orNoteIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("note", values);
            return this;
        }

        /**
         * or note in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orNoteIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("note", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or note not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orNoteNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("note", values);
            return this;
        }

        /**
         * or note not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orNoteNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("note", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or note between value1 and value2
         *
         * @return
         */
        public Criteria orNoteBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("note", value1, value2);
            return this;
        }

        /**
         * or note not between value1 and value2
         *
         * @return
         */
        public Criteria orNoteNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("note", value1, value2);
            return this;
        }

        /**
         * or note like value
         *
         * @return
         */
        public Criteria orNoteLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("note", value);
            return this;
        }

        /**
         * or note not like value
         *
         * @return
         */
        public Criteria orNoteNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("note", value);
            return this;
        }

        /**
         * and sub_user_id = value
         *
         * @return
         */
        public Criteria andSubUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("subUserId", value);
            return this;
        }

        /**
         * and sub_user_id != value
         *
         * @return
         */
        public Criteria andSubUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("subUserId", value);
            return this;
        }

        /**
         * and sub_user_id > value
         *
         * @return
         */
        public Criteria andSubUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("subUserId", value);
            return this;
        }

        /**
         * and sub_user_id >= value
         *
         * @return
         */
        public Criteria andSubUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("subUserId", value);
            return this;
        }

        /**
         * and sub_user_id < value
         *
         * @return
         */
        public Criteria andSubUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("subUserId", value);
            return this;
        }

        /**
         * and sub_user_id <= value
         *
         * @return
         */
        public Criteria andSubUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("subUserId", value);
            return this;
        }

        /**
         * and sub_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSubUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("subUserId", values);
            return this;
        }

        /**
         * and sub_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSubUserIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("subUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and sub_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSubUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("subUserId", values);
            return this;
        }

        /**
         * and sub_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSubUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("subUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and sub_user_id between value1 and value2
         *
         * @return
         */
        public Criteria andSubUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("subUserId", value1, value2);
            return this;
        }

        /**
         * and sub_user_id not between value1 and value2
         *
         * @return
         */
        public Criteria andSubUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("subUserId", value1, value2);
            return this;
        }

        /**
         * and sub_user_id like value
         *
         * @return
         */
        public Criteria andSubUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("subUserId", value);
            return this;
        }

        /**
         * and sub_user_id not like value
         *
         * @return
         */
        public Criteria andSubUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("subUserId", value);
            return this;
        }

        /**
         * or sub_user_id = value
         *
         * @return
         */
        public Criteria orSubUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("subUserId", value);
            return this;
        }

        /**
         * or sub_user_id != value
         *
         * @return
         */
        public Criteria orSubUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("subUserId", value);
            return this;
        }

        /**
         * or sub_user_id > value
         *
         * @return
         */
        public Criteria orSubUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("subUserId", value);
            return this;
        }

        /**
         * or sub_user_id >= value
         *
         * @return
         */
        public Criteria orSubUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("subUserId", value);
            return this;
        }

        /**
         * or sub_user_id < value
         *
         * @return
         */
        public Criteria orSubUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("subUserId", value);
            return this;
        }

        /**
         * or sub_user_id <= value
         *
         * @return
         */
        public Criteria orSubUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("subUserId", value);
            return this;
        }

        /**
         * or sub_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSubUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("subUserId", values);
            return this;
        }

        /**
         * or sub_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSubUserIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("subUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or sub_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSubUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("subUserId", values);
            return this;
        }

        /**
         * or sub_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSubUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("subUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or sub_user_id between value1 and value2
         *
         * @return
         */
        public Criteria orSubUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("subUserId", value1, value2);
            return this;
        }

        /**
         * or sub_user_id not between value1 and value2
         *
         * @return
         */
        public Criteria orSubUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("subUserId", value1, value2);
            return this;
        }

        /**
         * or sub_user_id like value
         *
         * @return
         */
        public Criteria orSubUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("subUserId", value);
            return this;
        }

        /**
         * or sub_user_id not like value
         *
         * @return
         */
        public Criteria orSubUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("subUserId", value);
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
