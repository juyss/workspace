package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.PageClickLog;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.Date;

/**
 * page_click_log 页点点击日志表
 *
 * @author xh
 * @since 2020/11/13
 */
public class PageClickLogExample extends AbstractExample<PageClickLog> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public PageClickLogExample() {
        super(PageClickLog.class);
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
         * order by click_id
         *
         * @return
         */
        public OrderBy clickId() {
            this.orderBy("clickId");
            return this;
        }
        
        /**
         * order by click_type
         *
         * @return
         */
        public OrderBy clickType() {
            this.orderBy("clickType");
            return this;
        }
        
        /**
         * order by click_ip
         *
         * @return
         */
        public OrderBy clickIp() {
            this.orderBy("clickIp");
            return this;
        }
        
        /**
         * order by click_date
         *
         * @return
         */
        public OrderBy clickDate() {
            this.orderBy("clickDate");
            return this;
        }
        
        /**
         * order by click_count
         *
         * @return
         */
        public OrderBy clickCount() {
            this.orderBy("clickCount");
            return this;
        }
        
        /**
         * order by identifier
         *
         * @return
         */
        public OrderBy identifier() {
            this.orderBy("identifier");
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
         * and click_id = ''
         *
         * @return
         */
        public Criteria andClickIdIsEmpty() {
            this.andEqualTo("clickId", "");
            return this;
        }

        /**
         * and click_id != ''
         *
         * @return
         */
        public Criteria andClickIdIsNotEmpty() {
            this.andNotEqualTo("clickId", "");
            return this;
        }

        /**
         * and click_id = value
         *
         * @return
         */
        public Criteria andClickIdEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("clickId", value);
            return this;
        }

        /**
         * and click_id != value
         *
         * @return
         */
        public Criteria andClickIdNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("clickId", value);
            return this;
        }

        /**
         * and click_id > value
         *
         * @return
         */
        public Criteria andClickIdGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("clickId", value);
            return this;
        }

        /**
         * and click_id >= value
         *
         * @return
         */
        public Criteria andClickIdGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("clickId", value);
            return this;
        }

        /**
         * and click_id < value
         *
         * @return
         */
        public Criteria andClickIdLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("clickId", value);
            return this;
        }

        /**
         * and click_id <= value
         *
         * @return
         */
        public Criteria andClickIdLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("clickId", value);
            return this;
        }

        /**
         * and click_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickIdIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickId", values);
            return this;
        }

        /**
         * and click_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickIdIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickIdNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickId", values);
            return this;
        }

        /**
         * and click_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickIdNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_id between value1 and value2
         *
         * @return
         */
        public Criteria andClickIdBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("clickId", value1, value2);
            return this;
        }

        /**
         * and click_id not between value1 and value2
         *
         * @return
         */
        public Criteria andClickIdNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("clickId", value1, value2);
            return this;
        }

        /**
         * and click_id like value
         *
         * @return
         */
        public Criteria andClickIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("clickId", value);
            return this;
        }

        /**
         * and click_id not like value
         *
         * @return
         */
        public Criteria andClickIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("clickId", value);
            return this;
        }

        /**
         * or click_id = ''
         *
         * @return
         */
        public Criteria orClickIdIsEmpty() {
            this.orEqualTo("clickId", "");
            return this;
        }

        /**
         * or click_id != ''
         *
         * @return
         */
        public Criteria orClickIdIsNotEmpty() {
            this.orNotEqualTo("clickId", "");
            return this;
        }

        /**
         * or click_id = value
         *
         * @return
         */
        public Criteria orClickIdEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("clickId", value);
            return this;
        }

        /**
         * or click_id != value
         *
         * @return
         */
        public Criteria orClickIdNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("clickId", value);
            return this;
        }

        /**
         * or click_id > value
         *
         * @return
         */
        public Criteria orClickIdGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("clickId", value);
            return this;
        }

        /**
         * or click_id >= value
         *
         * @return
         */
        public Criteria orClickIdGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("clickId", value);
            return this;
        }

        /**
         * or click_id < value
         *
         * @return
         */
        public Criteria orClickIdLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("clickId", value);
            return this;
        }

        /**
         * or click_id <= value
         *
         * @return
         */
        public Criteria orClickIdLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("clickId", value);
            return this;
        }

        /**
         * or click_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickIdIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickId", values);
            return this;
        }

        /**
         * or click_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickIdIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickIdNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickId", values);
            return this;
        }

        /**
         * or click_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickIdNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_id between value1 and value2
         *
         * @return
         */
        public Criteria orClickIdBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("clickId", value1, value2);
            return this;
        }

        /**
         * or click_id not between value1 and value2
         *
         * @return
         */
        public Criteria orClickIdNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("clickId", value1, value2);
            return this;
        }

        /**
         * or click_id like value
         *
         * @return
         */
        public Criteria orClickIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("clickId", value);
            return this;
        }

        /**
         * or click_id not like value
         *
         * @return
         */
        public Criteria orClickIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("clickId", value);
            return this;
        }

        /**
         * and click_type = ''
         *
         * @return
         */
        public Criteria andClickTypeIsEmpty() {
            this.andEqualTo("clickType", "");
            return this;
        }

        /**
         * and click_type != ''
         *
         * @return
         */
        public Criteria andClickTypeIsNotEmpty() {
            this.andNotEqualTo("clickType", "");
            return this;
        }

        /**
         * and click_type = value
         *
         * @return
         */
        public Criteria andClickTypeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("clickType", value);
            return this;
        }

        /**
         * and click_type != value
         *
         * @return
         */
        public Criteria andClickTypeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("clickType", value);
            return this;
        }

        /**
         * and click_type > value
         *
         * @return
         */
        public Criteria andClickTypeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("clickType", value);
            return this;
        }

        /**
         * and click_type >= value
         *
         * @return
         */
        public Criteria andClickTypeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("clickType", value);
            return this;
        }

        /**
         * and click_type < value
         *
         * @return
         */
        public Criteria andClickTypeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("clickType", value);
            return this;
        }

        /**
         * and click_type <= value
         *
         * @return
         */
        public Criteria andClickTypeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("clickType", value);
            return this;
        }

        /**
         * and click_type in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickTypeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickType", values);
            return this;
        }

        /**
         * and click_type in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickTypeIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickType", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_type not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickTypeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickType", values);
            return this;
        }

        /**
         * and click_type not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickTypeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickType", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_type between value1 and value2
         *
         * @return
         */
        public Criteria andClickTypeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("clickType", value1, value2);
            return this;
        }

        /**
         * and click_type not between value1 and value2
         *
         * @return
         */
        public Criteria andClickTypeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("clickType", value1, value2);
            return this;
        }

        /**
         * and click_type like value
         *
         * @return
         */
        public Criteria andClickTypeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("clickType", value);
            return this;
        }

        /**
         * and click_type not like value
         *
         * @return
         */
        public Criteria andClickTypeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("clickType", value);
            return this;
        }

        /**
         * or click_type = ''
         *
         * @return
         */
        public Criteria orClickTypeIsEmpty() {
            this.orEqualTo("clickType", "");
            return this;
        }

        /**
         * or click_type != ''
         *
         * @return
         */
        public Criteria orClickTypeIsNotEmpty() {
            this.orNotEqualTo("clickType", "");
            return this;
        }

        /**
         * or click_type = value
         *
         * @return
         */
        public Criteria orClickTypeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("clickType", value);
            return this;
        }

        /**
         * or click_type != value
         *
         * @return
         */
        public Criteria orClickTypeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("clickType", value);
            return this;
        }

        /**
         * or click_type > value
         *
         * @return
         */
        public Criteria orClickTypeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("clickType", value);
            return this;
        }

        /**
         * or click_type >= value
         *
         * @return
         */
        public Criteria orClickTypeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("clickType", value);
            return this;
        }

        /**
         * or click_type < value
         *
         * @return
         */
        public Criteria orClickTypeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("clickType", value);
            return this;
        }

        /**
         * or click_type <= value
         *
         * @return
         */
        public Criteria orClickTypeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("clickType", value);
            return this;
        }

        /**
         * or click_type in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickTypeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickType", values);
            return this;
        }

        /**
         * or click_type in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickTypeIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickType", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_type not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickTypeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickType", values);
            return this;
        }

        /**
         * or click_type not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickTypeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickType", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_type between value1 and value2
         *
         * @return
         */
        public Criteria orClickTypeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("clickType", value1, value2);
            return this;
        }

        /**
         * or click_type not between value1 and value2
         *
         * @return
         */
        public Criteria orClickTypeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("clickType", value1, value2);
            return this;
        }

        /**
         * or click_type like value
         *
         * @return
         */
        public Criteria orClickTypeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("clickType", value);
            return this;
        }

        /**
         * or click_type not like value
         *
         * @return
         */
        public Criteria orClickTypeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("clickType", value);
            return this;
        }

        /**
         * and click_ip = ''
         *
         * @return
         */
        public Criteria andClickIpIsEmpty() {
            this.andEqualTo("clickIp", "");
            return this;
        }

        /**
         * and click_ip != ''
         *
         * @return
         */
        public Criteria andClickIpIsNotEmpty() {
            this.andNotEqualTo("clickIp", "");
            return this;
        }

        /**
         * and click_ip = value
         *
         * @return
         */
        public Criteria andClickIpEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("clickIp", value);
            return this;
        }

        /**
         * and click_ip != value
         *
         * @return
         */
        public Criteria andClickIpNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("clickIp", value);
            return this;
        }

        /**
         * and click_ip > value
         *
         * @return
         */
        public Criteria andClickIpGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("clickIp", value);
            return this;
        }

        /**
         * and click_ip >= value
         *
         * @return
         */
        public Criteria andClickIpGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("clickIp", value);
            return this;
        }

        /**
         * and click_ip < value
         *
         * @return
         */
        public Criteria andClickIpLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("clickIp", value);
            return this;
        }

        /**
         * and click_ip <= value
         *
         * @return
         */
        public Criteria andClickIpLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("clickIp", value);
            return this;
        }

        /**
         * and click_ip in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickIpIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickIp", values);
            return this;
        }

        /**
         * and click_ip in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickIpIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickIp", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_ip not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickIpNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickIp", values);
            return this;
        }

        /**
         * and click_ip not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickIpNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickIp", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_ip between value1 and value2
         *
         * @return
         */
        public Criteria andClickIpBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("clickIp", value1, value2);
            return this;
        }

        /**
         * and click_ip not between value1 and value2
         *
         * @return
         */
        public Criteria andClickIpNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("clickIp", value1, value2);
            return this;
        }

        /**
         * and click_ip like value
         *
         * @return
         */
        public Criteria andClickIpLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("clickIp", value);
            return this;
        }

        /**
         * and click_ip not like value
         *
         * @return
         */
        public Criteria andClickIpNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("clickIp", value);
            return this;
        }

        /**
         * or click_ip = ''
         *
         * @return
         */
        public Criteria orClickIpIsEmpty() {
            this.orEqualTo("clickIp", "");
            return this;
        }

        /**
         * or click_ip != ''
         *
         * @return
         */
        public Criteria orClickIpIsNotEmpty() {
            this.orNotEqualTo("clickIp", "");
            return this;
        }

        /**
         * or click_ip = value
         *
         * @return
         */
        public Criteria orClickIpEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("clickIp", value);
            return this;
        }

        /**
         * or click_ip != value
         *
         * @return
         */
        public Criteria orClickIpNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("clickIp", value);
            return this;
        }

        /**
         * or click_ip > value
         *
         * @return
         */
        public Criteria orClickIpGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("clickIp", value);
            return this;
        }

        /**
         * or click_ip >= value
         *
         * @return
         */
        public Criteria orClickIpGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("clickIp", value);
            return this;
        }

        /**
         * or click_ip < value
         *
         * @return
         */
        public Criteria orClickIpLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("clickIp", value);
            return this;
        }

        /**
         * or click_ip <= value
         *
         * @return
         */
        public Criteria orClickIpLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("clickIp", value);
            return this;
        }

        /**
         * or click_ip in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickIpIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickIp", values);
            return this;
        }

        /**
         * or click_ip in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickIpIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickIp", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_ip not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickIpNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickIp", values);
            return this;
        }

        /**
         * or click_ip not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickIpNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickIp", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_ip between value1 and value2
         *
         * @return
         */
        public Criteria orClickIpBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("clickIp", value1, value2);
            return this;
        }

        /**
         * or click_ip not between value1 and value2
         *
         * @return
         */
        public Criteria orClickIpNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("clickIp", value1, value2);
            return this;
        }

        /**
         * or click_ip like value
         *
         * @return
         */
        public Criteria orClickIpLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("clickIp", value);
            return this;
        }

        /**
         * or click_ip not like value
         *
         * @return
         */
        public Criteria orClickIpNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("clickIp", value);
            return this;
        }

        /**
         * and click_date = value
         *
         * @return
         */
        public Criteria andClickDateEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("clickDate", value);
            return this;
        }

        /**
         * and click_date != value
         *
         * @return
         */
        public Criteria andClickDateNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("clickDate", value);
            return this;
        }

        /**
         * and click_date > value
         *
         * @return
         */
        public Criteria andClickDateGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("clickDate", value);
            return this;
        }

        /**
         * and click_date >= value
         *
         * @return
         */
        public Criteria andClickDateGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("clickDate", value);
            return this;
        }

        /**
         * and click_date < value
         *
         * @return
         */
        public Criteria andClickDateLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("clickDate", value);
            return this;
        }

        /**
         * and click_date <= value
         *
         * @return
         */
        public Criteria andClickDateLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("clickDate", value);
            return this;
        }

        /**
         * and click_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickDateIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickDate", values);
            return this;
        }

        /**
         * and click_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickDateIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickDateNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickDate", values);
            return this;
        }

        /**
         * and click_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickDateNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_date between value1 and value2
         *
         * @return
         */
        public Criteria andClickDateBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("clickDate", value1, value2);
            return this;
        }

        /**
         * and click_date not between value1 and value2
         *
         * @return
         */
        public Criteria andClickDateNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("clickDate", value1, value2);
            return this;
        }

        /**
         * and click_date like value
         *
         * @return
         */
        public Criteria andClickDateLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("clickDate", value);
            return this;
        }

        /**
         * and click_date not like value
         *
         * @return
         */
        public Criteria andClickDateNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("clickDate", value);
            return this;
        }

        /**
         * or click_date = value
         *
         * @return
         */
        public Criteria orClickDateEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("clickDate", value);
            return this;
        }

        /**
         * or click_date != value
         *
         * @return
         */
        public Criteria orClickDateNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("clickDate", value);
            return this;
        }

        /**
         * or click_date > value
         *
         * @return
         */
        public Criteria orClickDateGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("clickDate", value);
            return this;
        }

        /**
         * or click_date >= value
         *
         * @return
         */
        public Criteria orClickDateGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("clickDate", value);
            return this;
        }

        /**
         * or click_date < value
         *
         * @return
         */
        public Criteria orClickDateLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("clickDate", value);
            return this;
        }

        /**
         * or click_date <= value
         *
         * @return
         */
        public Criteria orClickDateLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("clickDate", value);
            return this;
        }

        /**
         * or click_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickDateIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickDate", values);
            return this;
        }

        /**
         * or click_date in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickDateIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickDateNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickDate", values);
            return this;
        }

        /**
         * or click_date not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickDateNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickDate", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_date between value1 and value2
         *
         * @return
         */
        public Criteria orClickDateBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("clickDate", value1, value2);
            return this;
        }

        /**
         * or click_date not between value1 and value2
         *
         * @return
         */
        public Criteria orClickDateNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("clickDate", value1, value2);
            return this;
        }

        /**
         * or click_date like value
         *
         * @return
         */
        public Criteria orClickDateLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("clickDate", value);
            return this;
        }

        /**
         * or click_date not like value
         *
         * @return
         */
        public Criteria orClickDateNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("clickDate", value);
            return this;
        }

        /**
         * and click_count = value
         *
         * @return
         */
        public Criteria andClickCountEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("clickCount", value);
            return this;
        }

        /**
         * and click_count != value
         *
         * @return
         */
        public Criteria andClickCountNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("clickCount", value);
            return this;
        }

        /**
         * and click_count > value
         *
         * @return
         */
        public Criteria andClickCountGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("clickCount", value);
            return this;
        }

        /**
         * and click_count >= value
         *
         * @return
         */
        public Criteria andClickCountGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("clickCount", value);
            return this;
        }

        /**
         * and click_count < value
         *
         * @return
         */
        public Criteria andClickCountLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("clickCount", value);
            return this;
        }

        /**
         * and click_count <= value
         *
         * @return
         */
        public Criteria andClickCountLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("clickCount", value);
            return this;
        }

        /**
         * and click_count in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickCountIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickCount", values);
            return this;
        }

        /**
         * and click_count in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickCountIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("clickCount", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_count not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickCountNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickCount", values);
            return this;
        }

        /**
         * and click_count not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andClickCountNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("clickCount", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and click_count between value1 and value2
         *
         * @return
         */
        public Criteria andClickCountBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("clickCount", value1, value2);
            return this;
        }

        /**
         * and click_count not between value1 and value2
         *
         * @return
         */
        public Criteria andClickCountNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("clickCount", value1, value2);
            return this;
        }

        /**
         * and click_count like value
         *
         * @return
         */
        public Criteria andClickCountLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("clickCount", value);
            return this;
        }

        /**
         * and click_count not like value
         *
         * @return
         */
        public Criteria andClickCountNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("clickCount", value);
            return this;
        }

        /**
         * or click_count = value
         *
         * @return
         */
        public Criteria orClickCountEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("clickCount", value);
            return this;
        }

        /**
         * or click_count != value
         *
         * @return
         */
        public Criteria orClickCountNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("clickCount", value);
            return this;
        }

        /**
         * or click_count > value
         *
         * @return
         */
        public Criteria orClickCountGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("clickCount", value);
            return this;
        }

        /**
         * or click_count >= value
         *
         * @return
         */
        public Criteria orClickCountGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("clickCount", value);
            return this;
        }

        /**
         * or click_count < value
         *
         * @return
         */
        public Criteria orClickCountLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("clickCount", value);
            return this;
        }

        /**
         * or click_count <= value
         *
         * @return
         */
        public Criteria orClickCountLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("clickCount", value);
            return this;
        }

        /**
         * or click_count in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickCountIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickCount", values);
            return this;
        }

        /**
         * or click_count in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickCountIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("clickCount", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_count not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickCountNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickCount", values);
            return this;
        }

        /**
         * or click_count not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orClickCountNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("clickCount", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or click_count between value1 and value2
         *
         * @return
         */
        public Criteria orClickCountBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("clickCount", value1, value2);
            return this;
        }

        /**
         * or click_count not between value1 and value2
         *
         * @return
         */
        public Criteria orClickCountNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("clickCount", value1, value2);
            return this;
        }

        /**
         * or click_count like value
         *
         * @return
         */
        public Criteria orClickCountLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("clickCount", value);
            return this;
        }

        /**
         * or click_count not like value
         *
         * @return
         */
        public Criteria orClickCountNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("clickCount", value);
            return this;
        }

        /**
         * and identifier = ''
         *
         * @return
         */
        public Criteria andIdentifierIsEmpty() {
            this.andEqualTo("identifier", "");
            return this;
        }

        /**
         * and identifier != ''
         *
         * @return
         */
        public Criteria andIdentifierIsNotEmpty() {
            this.andNotEqualTo("identifier", "");
            return this;
        }

        /**
         * and identifier = value
         *
         * @return
         */
        public Criteria andIdentifierEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("identifier", value);
            return this;
        }

        /**
         * and identifier != value
         *
         * @return
         */
        public Criteria andIdentifierNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("identifier", value);
            return this;
        }

        /**
         * and identifier > value
         *
         * @return
         */
        public Criteria andIdentifierGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("identifier", value);
            return this;
        }

        /**
         * and identifier >= value
         *
         * @return
         */
        public Criteria andIdentifierGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("identifier", value);
            return this;
        }

        /**
         * and identifier < value
         *
         * @return
         */
        public Criteria andIdentifierLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("identifier", value);
            return this;
        }

        /**
         * and identifier <= value
         *
         * @return
         */
        public Criteria andIdentifierLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("identifier", value);
            return this;
        }

        /**
         * and identifier in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIdentifierIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("identifier", values);
            return this;
        }

        /**
         * and identifier in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIdentifierIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("identifier", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and identifier not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIdentifierNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("identifier", values);
            return this;
        }

        /**
         * and identifier not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIdentifierNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("identifier", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and identifier between value1 and value2
         *
         * @return
         */
        public Criteria andIdentifierBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("identifier", value1, value2);
            return this;
        }

        /**
         * and identifier not between value1 and value2
         *
         * @return
         */
        public Criteria andIdentifierNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("identifier", value1, value2);
            return this;
        }

        /**
         * and identifier like value
         *
         * @return
         */
        public Criteria andIdentifierLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("identifier", value);
            return this;
        }

        /**
         * and identifier not like value
         *
         * @return
         */
        public Criteria andIdentifierNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("identifier", value);
            return this;
        }

        /**
         * or identifier = ''
         *
         * @return
         */
        public Criteria orIdentifierIsEmpty() {
            this.orEqualTo("identifier", "");
            return this;
        }

        /**
         * or identifier != ''
         *
         * @return
         */
        public Criteria orIdentifierIsNotEmpty() {
            this.orNotEqualTo("identifier", "");
            return this;
        }

        /**
         * or identifier = value
         *
         * @return
         */
        public Criteria orIdentifierEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("identifier", value);
            return this;
        }

        /**
         * or identifier != value
         *
         * @return
         */
        public Criteria orIdentifierNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("identifier", value);
            return this;
        }

        /**
         * or identifier > value
         *
         * @return
         */
        public Criteria orIdentifierGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("identifier", value);
            return this;
        }

        /**
         * or identifier >= value
         *
         * @return
         */
        public Criteria orIdentifierGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("identifier", value);
            return this;
        }

        /**
         * or identifier < value
         *
         * @return
         */
        public Criteria orIdentifierLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("identifier", value);
            return this;
        }

        /**
         * or identifier <= value
         *
         * @return
         */
        public Criteria orIdentifierLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("identifier", value);
            return this;
        }

        /**
         * or identifier in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIdentifierIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("identifier", values);
            return this;
        }

        /**
         * or identifier in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIdentifierIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("identifier", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or identifier not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIdentifierNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("identifier", values);
            return this;
        }

        /**
         * or identifier not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIdentifierNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("identifier", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or identifier between value1 and value2
         *
         * @return
         */
        public Criteria orIdentifierBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("identifier", value1, value2);
            return this;
        }

        /**
         * or identifier not between value1 and value2
         *
         * @return
         */
        public Criteria orIdentifierNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("identifier", value1, value2);
            return this;
        }

        /**
         * or identifier like value
         *
         * @return
         */
        public Criteria orIdentifierLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("identifier", value);
            return this;
        }

        /**
         * or identifier not like value
         *
         * @return
         */
        public Criteria orIdentifierNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("identifier", value);
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
