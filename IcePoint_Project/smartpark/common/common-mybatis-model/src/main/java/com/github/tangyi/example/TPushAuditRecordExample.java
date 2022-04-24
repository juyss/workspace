package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.TPushAuditRecord;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.Date;

/**
 * t_push_audit_record 信息推送审核记录表
 *
 * @author jy
 * @since 2020/11/03
 */
public class TPushAuditRecordExample extends AbstractExample<TPushAuditRecord> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public TPushAuditRecordExample() {
        super(TPushAuditRecord.class);
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
         * order by information_push_id
         *
         * @return
         */
        public OrderBy informationPushId() {
            this.orderBy("informationPushId");
            return this;
        }
        
        /**
         * order by content
         *
         * @return
         */
        public OrderBy content() {
            this.orderBy("content");
            return this;
        }
        
        /**
         * order by operation_time
         *
         * @return
         */
        public OrderBy operationTime() {
            this.orderBy("operationTime");
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
         * order by operation_user_id
         *
         * @return
         */
        public OrderBy operationUserId() {
            this.orderBy("operationUserId");
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
         * and information_push_id = value
         *
         * @return
         */
        public Criteria andInformationPushIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("informationPushId", value);
            return this;
        }

        /**
         * and information_push_id != value
         *
         * @return
         */
        public Criteria andInformationPushIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("informationPushId", value);
            return this;
        }

        /**
         * and information_push_id > value
         *
         * @return
         */
        public Criteria andInformationPushIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("informationPushId", value);
            return this;
        }

        /**
         * and information_push_id >= value
         *
         * @return
         */
        public Criteria andInformationPushIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("informationPushId", value);
            return this;
        }

        /**
         * and information_push_id < value
         *
         * @return
         */
        public Criteria andInformationPushIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("informationPushId", value);
            return this;
        }

        /**
         * and information_push_id <= value
         *
         * @return
         */
        public Criteria andInformationPushIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("informationPushId", value);
            return this;
        }

        /**
         * and information_push_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andInformationPushIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("informationPushId", values);
            return this;
        }

        /**
         * and information_push_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andInformationPushIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("informationPushId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and information_push_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andInformationPushIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("informationPushId", values);
            return this;
        }

        /**
         * and information_push_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andInformationPushIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("informationPushId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and information_push_id between value1 and value2
         *
         * @return
         */
        public Criteria andInformationPushIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("informationPushId", value1, value2);
            return this;
        }

        /**
         * and information_push_id not between value1 and value2
         *
         * @return
         */
        public Criteria andInformationPushIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("informationPushId", value1, value2);
            return this;
        }

        /**
         * and information_push_id like value
         *
         * @return
         */
        public Criteria andInformationPushIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("informationPushId", value);
            return this;
        }

        /**
         * and information_push_id not like value
         *
         * @return
         */
        public Criteria andInformationPushIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("informationPushId", value);
            return this;
        }

        /**
         * or information_push_id = value
         *
         * @return
         */
        public Criteria orInformationPushIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("informationPushId", value);
            return this;
        }

        /**
         * or information_push_id != value
         *
         * @return
         */
        public Criteria orInformationPushIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("informationPushId", value);
            return this;
        }

        /**
         * or information_push_id > value
         *
         * @return
         */
        public Criteria orInformationPushIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("informationPushId", value);
            return this;
        }

        /**
         * or information_push_id >= value
         *
         * @return
         */
        public Criteria orInformationPushIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("informationPushId", value);
            return this;
        }

        /**
         * or information_push_id < value
         *
         * @return
         */
        public Criteria orInformationPushIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("informationPushId", value);
            return this;
        }

        /**
         * or information_push_id <= value
         *
         * @return
         */
        public Criteria orInformationPushIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("informationPushId", value);
            return this;
        }

        /**
         * or information_push_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orInformationPushIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("informationPushId", values);
            return this;
        }

        /**
         * or information_push_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orInformationPushIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("informationPushId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or information_push_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orInformationPushIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("informationPushId", values);
            return this;
        }

        /**
         * or information_push_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orInformationPushIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("informationPushId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or information_push_id between value1 and value2
         *
         * @return
         */
        public Criteria orInformationPushIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("informationPushId", value1, value2);
            return this;
        }

        /**
         * or information_push_id not between value1 and value2
         *
         * @return
         */
        public Criteria orInformationPushIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("informationPushId", value1, value2);
            return this;
        }

        /**
         * or information_push_id like value
         *
         * @return
         */
        public Criteria orInformationPushIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("informationPushId", value);
            return this;
        }

        /**
         * or information_push_id not like value
         *
         * @return
         */
        public Criteria orInformationPushIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("informationPushId", value);
            return this;
        }

        /**
         * and content = ''
         *
         * @return
         */
        public Criteria andContentIsEmpty() {
            this.andEqualTo("content", "");
            return this;
        }

        /**
         * and content != ''
         *
         * @return
         */
        public Criteria andContentIsNotEmpty() {
            this.andNotEqualTo("content", "");
            return this;
        }

        /**
         * and content = value
         *
         * @return
         */
        public Criteria andContentEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("content", value);
            return this;
        }

        /**
         * and content != value
         *
         * @return
         */
        public Criteria andContentNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("content", value);
            return this;
        }

        /**
         * and content > value
         *
         * @return
         */
        public Criteria andContentGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("content", value);
            return this;
        }

        /**
         * and content >= value
         *
         * @return
         */
        public Criteria andContentGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("content", value);
            return this;
        }

        /**
         * and content < value
         *
         * @return
         */
        public Criteria andContentLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("content", value);
            return this;
        }

        /**
         * and content <= value
         *
         * @return
         */
        public Criteria andContentLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("content", value);
            return this;
        }

        /**
         * and content in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andContentIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("content", values);
            return this;
        }

        /**
         * and content in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andContentIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("content", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and content not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andContentNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("content", values);
            return this;
        }

        /**
         * and content not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andContentNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("content", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and content between value1 and value2
         *
         * @return
         */
        public Criteria andContentBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("content", value1, value2);
            return this;
        }

        /**
         * and content not between value1 and value2
         *
         * @return
         */
        public Criteria andContentNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("content", value1, value2);
            return this;
        }

        /**
         * and content like value
         *
         * @return
         */
        public Criteria andContentLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("content", value);
            return this;
        }

        /**
         * and content not like value
         *
         * @return
         */
        public Criteria andContentNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("content", value);
            return this;
        }

        /**
         * or content = ''
         *
         * @return
         */
        public Criteria orContentIsEmpty() {
            this.orEqualTo("content", "");
            return this;
        }

        /**
         * or content != ''
         *
         * @return
         */
        public Criteria orContentIsNotEmpty() {
            this.orNotEqualTo("content", "");
            return this;
        }

        /**
         * or content = value
         *
         * @return
         */
        public Criteria orContentEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("content", value);
            return this;
        }

        /**
         * or content != value
         *
         * @return
         */
        public Criteria orContentNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("content", value);
            return this;
        }

        /**
         * or content > value
         *
         * @return
         */
        public Criteria orContentGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("content", value);
            return this;
        }

        /**
         * or content >= value
         *
         * @return
         */
        public Criteria orContentGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("content", value);
            return this;
        }

        /**
         * or content < value
         *
         * @return
         */
        public Criteria orContentLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("content", value);
            return this;
        }

        /**
         * or content <= value
         *
         * @return
         */
        public Criteria orContentLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("content", value);
            return this;
        }

        /**
         * or content in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orContentIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("content", values);
            return this;
        }

        /**
         * or content in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orContentIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("content", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or content not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orContentNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("content", values);
            return this;
        }

        /**
         * or content not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orContentNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("content", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or content between value1 and value2
         *
         * @return
         */
        public Criteria orContentBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("content", value1, value2);
            return this;
        }

        /**
         * or content not between value1 and value2
         *
         * @return
         */
        public Criteria orContentNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("content", value1, value2);
            return this;
        }

        /**
         * or content like value
         *
         * @return
         */
        public Criteria orContentLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("content", value);
            return this;
        }

        /**
         * or content not like value
         *
         * @return
         */
        public Criteria orContentNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("content", value);
            return this;
        }

        /**
         * and operation_time = value
         *
         * @return
         */
        public Criteria andOperationTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("operationTime", value);
            return this;
        }

        /**
         * and operation_time != value
         *
         * @return
         */
        public Criteria andOperationTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("operationTime", value);
            return this;
        }

        /**
         * and operation_time > value
         *
         * @return
         */
        public Criteria andOperationTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("operationTime", value);
            return this;
        }

        /**
         * and operation_time >= value
         *
         * @return
         */
        public Criteria andOperationTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("operationTime", value);
            return this;
        }

        /**
         * and operation_time < value
         *
         * @return
         */
        public Criteria andOperationTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("operationTime", value);
            return this;
        }

        /**
         * and operation_time <= value
         *
         * @return
         */
        public Criteria andOperationTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("operationTime", value);
            return this;
        }

        /**
         * and operation_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andOperationTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("operationTime", values);
            return this;
        }

        /**
         * and operation_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andOperationTimeIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("operationTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and operation_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andOperationTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("operationTime", values);
            return this;
        }

        /**
         * and operation_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andOperationTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("operationTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and operation_time between value1 and value2
         *
         * @return
         */
        public Criteria andOperationTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("operationTime", value1, value2);
            return this;
        }

        /**
         * and operation_time not between value1 and value2
         *
         * @return
         */
        public Criteria andOperationTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("operationTime", value1, value2);
            return this;
        }

        /**
         * and operation_time like value
         *
         * @return
         */
        public Criteria andOperationTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("operationTime", value);
            return this;
        }

        /**
         * and operation_time not like value
         *
         * @return
         */
        public Criteria andOperationTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("operationTime", value);
            return this;
        }

        /**
         * or operation_time = value
         *
         * @return
         */
        public Criteria orOperationTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("operationTime", value);
            return this;
        }

        /**
         * or operation_time != value
         *
         * @return
         */
        public Criteria orOperationTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("operationTime", value);
            return this;
        }

        /**
         * or operation_time > value
         *
         * @return
         */
        public Criteria orOperationTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("operationTime", value);
            return this;
        }

        /**
         * or operation_time >= value
         *
         * @return
         */
        public Criteria orOperationTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("operationTime", value);
            return this;
        }

        /**
         * or operation_time < value
         *
         * @return
         */
        public Criteria orOperationTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("operationTime", value);
            return this;
        }

        /**
         * or operation_time <= value
         *
         * @return
         */
        public Criteria orOperationTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("operationTime", value);
            return this;
        }

        /**
         * or operation_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orOperationTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("operationTime", values);
            return this;
        }

        /**
         * or operation_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orOperationTimeIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("operationTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or operation_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orOperationTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("operationTime", values);
            return this;
        }

        /**
         * or operation_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orOperationTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("operationTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or operation_time between value1 and value2
         *
         * @return
         */
        public Criteria orOperationTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("operationTime", value1, value2);
            return this;
        }

        /**
         * or operation_time not between value1 and value2
         *
         * @return
         */
        public Criteria orOperationTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("operationTime", value1, value2);
            return this;
        }

        /**
         * or operation_time like value
         *
         * @return
         */
        public Criteria orOperationTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("operationTime", value);
            return this;
        }

        /**
         * or operation_time not like value
         *
         * @return
         */
        public Criteria orOperationTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("operationTime", value);
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
         * and operation_user_id = value
         *
         * @return
         */
        public Criteria andOperationUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("operationUserId", value);
            return this;
        }

        /**
         * and operation_user_id != value
         *
         * @return
         */
        public Criteria andOperationUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("operationUserId", value);
            return this;
        }

        /**
         * and operation_user_id > value
         *
         * @return
         */
        public Criteria andOperationUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("operationUserId", value);
            return this;
        }

        /**
         * and operation_user_id >= value
         *
         * @return
         */
        public Criteria andOperationUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("operationUserId", value);
            return this;
        }

        /**
         * and operation_user_id < value
         *
         * @return
         */
        public Criteria andOperationUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("operationUserId", value);
            return this;
        }

        /**
         * and operation_user_id <= value
         *
         * @return
         */
        public Criteria andOperationUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("operationUserId", value);
            return this;
        }

        /**
         * and operation_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andOperationUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("operationUserId", values);
            return this;
        }

        /**
         * and operation_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andOperationUserIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("operationUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and operation_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andOperationUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("operationUserId", values);
            return this;
        }

        /**
         * and operation_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andOperationUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("operationUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and operation_user_id between value1 and value2
         *
         * @return
         */
        public Criteria andOperationUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("operationUserId", value1, value2);
            return this;
        }

        /**
         * and operation_user_id not between value1 and value2
         *
         * @return
         */
        public Criteria andOperationUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("operationUserId", value1, value2);
            return this;
        }

        /**
         * and operation_user_id like value
         *
         * @return
         */
        public Criteria andOperationUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("operationUserId", value);
            return this;
        }

        /**
         * and operation_user_id not like value
         *
         * @return
         */
        public Criteria andOperationUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("operationUserId", value);
            return this;
        }

        /**
         * or operation_user_id = value
         *
         * @return
         */
        public Criteria orOperationUserIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("operationUserId", value);
            return this;
        }

        /**
         * or operation_user_id != value
         *
         * @return
         */
        public Criteria orOperationUserIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("operationUserId", value);
            return this;
        }

        /**
         * or operation_user_id > value
         *
         * @return
         */
        public Criteria orOperationUserIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("operationUserId", value);
            return this;
        }

        /**
         * or operation_user_id >= value
         *
         * @return
         */
        public Criteria orOperationUserIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("operationUserId", value);
            return this;
        }

        /**
         * or operation_user_id < value
         *
         * @return
         */
        public Criteria orOperationUserIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("operationUserId", value);
            return this;
        }

        /**
         * or operation_user_id <= value
         *
         * @return
         */
        public Criteria orOperationUserIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("operationUserId", value);
            return this;
        }

        /**
         * or operation_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orOperationUserIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("operationUserId", values);
            return this;
        }

        /**
         * or operation_user_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orOperationUserIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("operationUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or operation_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orOperationUserIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("operationUserId", values);
            return this;
        }

        /**
         * or operation_user_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orOperationUserIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("operationUserId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or operation_user_id between value1 and value2
         *
         * @return
         */
        public Criteria orOperationUserIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("operationUserId", value1, value2);
            return this;
        }

        /**
         * or operation_user_id not between value1 and value2
         *
         * @return
         */
        public Criteria orOperationUserIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("operationUserId", value1, value2);
            return this;
        }

        /**
         * or operation_user_id like value
         *
         * @return
         */
        public Criteria orOperationUserIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("operationUserId", value);
            return this;
        }

        /**
         * or operation_user_id not like value
         *
         * @return
         */
        public Criteria orOperationUserIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("operationUserId", value);
            return this;
        }


    }

}
