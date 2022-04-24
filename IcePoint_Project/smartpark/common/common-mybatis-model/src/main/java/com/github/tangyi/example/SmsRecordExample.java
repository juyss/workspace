package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.SmsRecord;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.Date;

/**
 * sms_record 短信发送记录
 *
 * @author xh
 * @since 2020/12/03
 */
public class SmsRecordExample extends AbstractExample<SmsRecord> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public SmsRecordExample() {
        super(SmsRecord.class);
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
         * order by content
         *
         * @return
         */
        public OrderBy content() {
            this.orderBy("content");
            return this;
        }
        
        /**
         * order by mobile
         *
         * @return
         */
        public OrderBy mobile() {
            this.orderBy("mobile");
            return this;
        }
        
        /**
         * order by send_by
         *
         * @return
         */
        public OrderBy sendBy() {
            this.orderBy("sendBy");
            return this;
        }
        
        /**
         * order by success
         *
         * @return
         */
        public OrderBy success() {
            this.orderBy("success");
            return this;
        }
        
        /**
         * order by res_json
         *
         * @return
         */
        public OrderBy resJson() {
            this.orderBy("resJson");
            return this;
        }
        
        /**
         * order by templetid
         *
         * @return
         */
        public OrderBy templetid() {
            this.orderBy("templetid");
            return this;
        }
        
        /**
         * order by templet_name
         *
         * @return
         */
        public OrderBy templetName() {
            this.orderBy("templetName");
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
         * and mobile = ''
         *
         * @return
         */
        public Criteria andMobileIsEmpty() {
            this.andEqualTo("mobile", "");
            return this;
        }

        /**
         * and mobile != ''
         *
         * @return
         */
        public Criteria andMobileIsNotEmpty() {
            this.andNotEqualTo("mobile", "");
            return this;
        }

        /**
         * and mobile = value
         *
         * @return
         */
        public Criteria andMobileEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("mobile", value);
            return this;
        }

        /**
         * and mobile != value
         *
         * @return
         */
        public Criteria andMobileNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("mobile", value);
            return this;
        }

        /**
         * and mobile > value
         *
         * @return
         */
        public Criteria andMobileGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("mobile", value);
            return this;
        }

        /**
         * and mobile >= value
         *
         * @return
         */
        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("mobile", value);
            return this;
        }

        /**
         * and mobile < value
         *
         * @return
         */
        public Criteria andMobileLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("mobile", value);
            return this;
        }

        /**
         * and mobile <= value
         *
         * @return
         */
        public Criteria andMobileLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("mobile", value);
            return this;
        }

        /**
         * and mobile in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andMobileIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("mobile", values);
            return this;
        }

        /**
         * and mobile in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andMobileIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("mobile", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and mobile not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andMobileNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("mobile", values);
            return this;
        }

        /**
         * and mobile not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andMobileNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("mobile", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and mobile between value1 and value2
         *
         * @return
         */
        public Criteria andMobileBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("mobile", value1, value2);
            return this;
        }

        /**
         * and mobile not between value1 and value2
         *
         * @return
         */
        public Criteria andMobileNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("mobile", value1, value2);
            return this;
        }

        /**
         * and mobile like value
         *
         * @return
         */
        public Criteria andMobileLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("mobile", value);
            return this;
        }

        /**
         * and mobile not like value
         *
         * @return
         */
        public Criteria andMobileNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("mobile", value);
            return this;
        }

        /**
         * or mobile = ''
         *
         * @return
         */
        public Criteria orMobileIsEmpty() {
            this.orEqualTo("mobile", "");
            return this;
        }

        /**
         * or mobile != ''
         *
         * @return
         */
        public Criteria orMobileIsNotEmpty() {
            this.orNotEqualTo("mobile", "");
            return this;
        }

        /**
         * or mobile = value
         *
         * @return
         */
        public Criteria orMobileEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("mobile", value);
            return this;
        }

        /**
         * or mobile != value
         *
         * @return
         */
        public Criteria orMobileNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("mobile", value);
            return this;
        }

        /**
         * or mobile > value
         *
         * @return
         */
        public Criteria orMobileGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("mobile", value);
            return this;
        }

        /**
         * or mobile >= value
         *
         * @return
         */
        public Criteria orMobileGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("mobile", value);
            return this;
        }

        /**
         * or mobile < value
         *
         * @return
         */
        public Criteria orMobileLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("mobile", value);
            return this;
        }

        /**
         * or mobile <= value
         *
         * @return
         */
        public Criteria orMobileLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("mobile", value);
            return this;
        }

        /**
         * or mobile in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orMobileIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("mobile", values);
            return this;
        }

        /**
         * or mobile in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orMobileIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("mobile", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or mobile not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orMobileNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("mobile", values);
            return this;
        }

        /**
         * or mobile not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orMobileNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("mobile", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or mobile between value1 and value2
         *
         * @return
         */
        public Criteria orMobileBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("mobile", value1, value2);
            return this;
        }

        /**
         * or mobile not between value1 and value2
         *
         * @return
         */
        public Criteria orMobileNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("mobile", value1, value2);
            return this;
        }

        /**
         * or mobile like value
         *
         * @return
         */
        public Criteria orMobileLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("mobile", value);
            return this;
        }

        /**
         * or mobile not like value
         *
         * @return
         */
        public Criteria orMobileNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("mobile", value);
            return this;
        }

        /**
         * and send_by = ''
         *
         * @return
         */
        public Criteria andSendByIsEmpty() {
            this.andEqualTo("sendBy", "");
            return this;
        }

        /**
         * and send_by != ''
         *
         * @return
         */
        public Criteria andSendByIsNotEmpty() {
            this.andNotEqualTo("sendBy", "");
            return this;
        }

        /**
         * and send_by = value
         *
         * @return
         */
        public Criteria andSendByEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("sendBy", value);
            return this;
        }

        /**
         * and send_by != value
         *
         * @return
         */
        public Criteria andSendByNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("sendBy", value);
            return this;
        }

        /**
         * and send_by > value
         *
         * @return
         */
        public Criteria andSendByGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("sendBy", value);
            return this;
        }

        /**
         * and send_by >= value
         *
         * @return
         */
        public Criteria andSendByGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("sendBy", value);
            return this;
        }

        /**
         * and send_by < value
         *
         * @return
         */
        public Criteria andSendByLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("sendBy", value);
            return this;
        }

        /**
         * and send_by <= value
         *
         * @return
         */
        public Criteria andSendByLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("sendBy", value);
            return this;
        }

        /**
         * and send_by in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSendByIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("sendBy", values);
            return this;
        }

        /**
         * and send_by in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSendByIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("sendBy", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and send_by not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSendByNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("sendBy", values);
            return this;
        }

        /**
         * and send_by not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSendByNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("sendBy", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and send_by between value1 and value2
         *
         * @return
         */
        public Criteria andSendByBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("sendBy", value1, value2);
            return this;
        }

        /**
         * and send_by not between value1 and value2
         *
         * @return
         */
        public Criteria andSendByNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("sendBy", value1, value2);
            return this;
        }

        /**
         * and send_by like value
         *
         * @return
         */
        public Criteria andSendByLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("sendBy", value);
            return this;
        }

        /**
         * and send_by not like value
         *
         * @return
         */
        public Criteria andSendByNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("sendBy", value);
            return this;
        }

        /**
         * or send_by = ''
         *
         * @return
         */
        public Criteria orSendByIsEmpty() {
            this.orEqualTo("sendBy", "");
            return this;
        }

        /**
         * or send_by != ''
         *
         * @return
         */
        public Criteria orSendByIsNotEmpty() {
            this.orNotEqualTo("sendBy", "");
            return this;
        }

        /**
         * or send_by = value
         *
         * @return
         */
        public Criteria orSendByEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("sendBy", value);
            return this;
        }

        /**
         * or send_by != value
         *
         * @return
         */
        public Criteria orSendByNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("sendBy", value);
            return this;
        }

        /**
         * or send_by > value
         *
         * @return
         */
        public Criteria orSendByGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("sendBy", value);
            return this;
        }

        /**
         * or send_by >= value
         *
         * @return
         */
        public Criteria orSendByGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("sendBy", value);
            return this;
        }

        /**
         * or send_by < value
         *
         * @return
         */
        public Criteria orSendByLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("sendBy", value);
            return this;
        }

        /**
         * or send_by <= value
         *
         * @return
         */
        public Criteria orSendByLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("sendBy", value);
            return this;
        }

        /**
         * or send_by in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSendByIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("sendBy", values);
            return this;
        }

        /**
         * or send_by in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSendByIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("sendBy", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or send_by not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSendByNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("sendBy", values);
            return this;
        }

        /**
         * or send_by not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSendByNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("sendBy", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or send_by between value1 and value2
         *
         * @return
         */
        public Criteria orSendByBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("sendBy", value1, value2);
            return this;
        }

        /**
         * or send_by not between value1 and value2
         *
         * @return
         */
        public Criteria orSendByNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("sendBy", value1, value2);
            return this;
        }

        /**
         * or send_by like value
         *
         * @return
         */
        public Criteria orSendByLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("sendBy", value);
            return this;
        }

        /**
         * or send_by not like value
         *
         * @return
         */
        public Criteria orSendByNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("sendBy", value);
            return this;
        }

        /**
         * and success = value
         *
         * @return
         */
        public Criteria andSuccessEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("success", value);
            return this;
        }

        /**
         * and success != value
         *
         * @return
         */
        public Criteria andSuccessNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("success", value);
            return this;
        }

        /**
         * and success > value
         *
         * @return
         */
        public Criteria andSuccessGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("success", value);
            return this;
        }

        /**
         * and success >= value
         *
         * @return
         */
        public Criteria andSuccessGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("success", value);
            return this;
        }

        /**
         * and success < value
         *
         * @return
         */
        public Criteria andSuccessLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("success", value);
            return this;
        }

        /**
         * and success <= value
         *
         * @return
         */
        public Criteria andSuccessLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("success", value);
            return this;
        }

        /**
         * and success in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSuccessIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("success", values);
            return this;
        }

        /**
         * and success in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSuccessIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("success", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and success not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSuccessNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("success", values);
            return this;
        }

        /**
         * and success not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andSuccessNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("success", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and success between value1 and value2
         *
         * @return
         */
        public Criteria andSuccessBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("success", value1, value2);
            return this;
        }

        /**
         * and success not between value1 and value2
         *
         * @return
         */
        public Criteria andSuccessNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("success", value1, value2);
            return this;
        }

        /**
         * and success like value
         *
         * @return
         */
        public Criteria andSuccessLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("success", value);
            return this;
        }

        /**
         * and success not like value
         *
         * @return
         */
        public Criteria andSuccessNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("success", value);
            return this;
        }

        /**
         * or success = value
         *
         * @return
         */
        public Criteria orSuccessEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("success", value);
            return this;
        }

        /**
         * or success != value
         *
         * @return
         */
        public Criteria orSuccessNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("success", value);
            return this;
        }

        /**
         * or success > value
         *
         * @return
         */
        public Criteria orSuccessGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("success", value);
            return this;
        }

        /**
         * or success >= value
         *
         * @return
         */
        public Criteria orSuccessGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("success", value);
            return this;
        }

        /**
         * or success < value
         *
         * @return
         */
        public Criteria orSuccessLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("success", value);
            return this;
        }

        /**
         * or success <= value
         *
         * @return
         */
        public Criteria orSuccessLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("success", value);
            return this;
        }

        /**
         * or success in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSuccessIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("success", values);
            return this;
        }

        /**
         * or success in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSuccessIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("success", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or success not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSuccessNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("success", values);
            return this;
        }

        /**
         * or success not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orSuccessNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("success", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or success between value1 and value2
         *
         * @return
         */
        public Criteria orSuccessBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("success", value1, value2);
            return this;
        }

        /**
         * or success not between value1 and value2
         *
         * @return
         */
        public Criteria orSuccessNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("success", value1, value2);
            return this;
        }

        /**
         * or success like value
         *
         * @return
         */
        public Criteria orSuccessLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("success", value);
            return this;
        }

        /**
         * or success not like value
         *
         * @return
         */
        public Criteria orSuccessNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("success", value);
            return this;
        }

        /**
         * and res_json = ''
         *
         * @return
         */
        public Criteria andResJsonIsEmpty() {
            this.andEqualTo("resJson", "");
            return this;
        }

        /**
         * and res_json != ''
         *
         * @return
         */
        public Criteria andResJsonIsNotEmpty() {
            this.andNotEqualTo("resJson", "");
            return this;
        }

        /**
         * and res_json = value
         *
         * @return
         */
        public Criteria andResJsonEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("resJson", value);
            return this;
        }

        /**
         * and res_json != value
         *
         * @return
         */
        public Criteria andResJsonNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("resJson", value);
            return this;
        }

        /**
         * and res_json > value
         *
         * @return
         */
        public Criteria andResJsonGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("resJson", value);
            return this;
        }

        /**
         * and res_json >= value
         *
         * @return
         */
        public Criteria andResJsonGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("resJson", value);
            return this;
        }

        /**
         * and res_json < value
         *
         * @return
         */
        public Criteria andResJsonLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("resJson", value);
            return this;
        }

        /**
         * and res_json <= value
         *
         * @return
         */
        public Criteria andResJsonLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("resJson", value);
            return this;
        }

        /**
         * and res_json in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andResJsonIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("resJson", values);
            return this;
        }

        /**
         * and res_json in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andResJsonIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("resJson", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and res_json not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andResJsonNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("resJson", values);
            return this;
        }

        /**
         * and res_json not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andResJsonNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("resJson", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and res_json between value1 and value2
         *
         * @return
         */
        public Criteria andResJsonBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("resJson", value1, value2);
            return this;
        }

        /**
         * and res_json not between value1 and value2
         *
         * @return
         */
        public Criteria andResJsonNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("resJson", value1, value2);
            return this;
        }

        /**
         * and res_json like value
         *
         * @return
         */
        public Criteria andResJsonLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("resJson", value);
            return this;
        }

        /**
         * and res_json not like value
         *
         * @return
         */
        public Criteria andResJsonNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("resJson", value);
            return this;
        }

        /**
         * or res_json = ''
         *
         * @return
         */
        public Criteria orResJsonIsEmpty() {
            this.orEqualTo("resJson", "");
            return this;
        }

        /**
         * or res_json != ''
         *
         * @return
         */
        public Criteria orResJsonIsNotEmpty() {
            this.orNotEqualTo("resJson", "");
            return this;
        }

        /**
         * or res_json = value
         *
         * @return
         */
        public Criteria orResJsonEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("resJson", value);
            return this;
        }

        /**
         * or res_json != value
         *
         * @return
         */
        public Criteria orResJsonNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("resJson", value);
            return this;
        }

        /**
         * or res_json > value
         *
         * @return
         */
        public Criteria orResJsonGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("resJson", value);
            return this;
        }

        /**
         * or res_json >= value
         *
         * @return
         */
        public Criteria orResJsonGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("resJson", value);
            return this;
        }

        /**
         * or res_json < value
         *
         * @return
         */
        public Criteria orResJsonLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("resJson", value);
            return this;
        }

        /**
         * or res_json <= value
         *
         * @return
         */
        public Criteria orResJsonLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("resJson", value);
            return this;
        }

        /**
         * or res_json in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orResJsonIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("resJson", values);
            return this;
        }

        /**
         * or res_json in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orResJsonIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("resJson", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or res_json not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orResJsonNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("resJson", values);
            return this;
        }

        /**
         * or res_json not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orResJsonNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("resJson", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or res_json between value1 and value2
         *
         * @return
         */
        public Criteria orResJsonBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("resJson", value1, value2);
            return this;
        }

        /**
         * or res_json not between value1 and value2
         *
         * @return
         */
        public Criteria orResJsonNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("resJson", value1, value2);
            return this;
        }

        /**
         * or res_json like value
         *
         * @return
         */
        public Criteria orResJsonLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("resJson", value);
            return this;
        }

        /**
         * or res_json not like value
         *
         * @return
         */
        public Criteria orResJsonNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("resJson", value);
            return this;
        }

        /**
         * and templetid = ''
         *
         * @return
         */
        public Criteria andTempletidIsEmpty() {
            this.andEqualTo("templetid", "");
            return this;
        }

        /**
         * and templetid != ''
         *
         * @return
         */
        public Criteria andTempletidIsNotEmpty() {
            this.andNotEqualTo("templetid", "");
            return this;
        }

        /**
         * and templetid = value
         *
         * @return
         */
        public Criteria andTempletidEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("templetid", value);
            return this;
        }

        /**
         * and templetid != value
         *
         * @return
         */
        public Criteria andTempletidNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("templetid", value);
            return this;
        }

        /**
         * and templetid > value
         *
         * @return
         */
        public Criteria andTempletidGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("templetid", value);
            return this;
        }

        /**
         * and templetid >= value
         *
         * @return
         */
        public Criteria andTempletidGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("templetid", value);
            return this;
        }

        /**
         * and templetid < value
         *
         * @return
         */
        public Criteria andTempletidLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("templetid", value);
            return this;
        }

        /**
         * and templetid <= value
         *
         * @return
         */
        public Criteria andTempletidLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("templetid", value);
            return this;
        }

        /**
         * and templetid in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTempletidIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("templetid", values);
            return this;
        }

        /**
         * and templetid in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTempletidIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("templetid", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and templetid not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTempletidNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("templetid", values);
            return this;
        }

        /**
         * and templetid not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTempletidNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("templetid", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and templetid between value1 and value2
         *
         * @return
         */
        public Criteria andTempletidBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("templetid", value1, value2);
            return this;
        }

        /**
         * and templetid not between value1 and value2
         *
         * @return
         */
        public Criteria andTempletidNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("templetid", value1, value2);
            return this;
        }

        /**
         * and templetid like value
         *
         * @return
         */
        public Criteria andTempletidLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("templetid", value);
            return this;
        }

        /**
         * and templetid not like value
         *
         * @return
         */
        public Criteria andTempletidNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("templetid", value);
            return this;
        }

        /**
         * or templetid = ''
         *
         * @return
         */
        public Criteria orTempletidIsEmpty() {
            this.orEqualTo("templetid", "");
            return this;
        }

        /**
         * or templetid != ''
         *
         * @return
         */
        public Criteria orTempletidIsNotEmpty() {
            this.orNotEqualTo("templetid", "");
            return this;
        }

        /**
         * or templetid = value
         *
         * @return
         */
        public Criteria orTempletidEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("templetid", value);
            return this;
        }

        /**
         * or templetid != value
         *
         * @return
         */
        public Criteria orTempletidNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("templetid", value);
            return this;
        }

        /**
         * or templetid > value
         *
         * @return
         */
        public Criteria orTempletidGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("templetid", value);
            return this;
        }

        /**
         * or templetid >= value
         *
         * @return
         */
        public Criteria orTempletidGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("templetid", value);
            return this;
        }

        /**
         * or templetid < value
         *
         * @return
         */
        public Criteria orTempletidLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("templetid", value);
            return this;
        }

        /**
         * or templetid <= value
         *
         * @return
         */
        public Criteria orTempletidLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("templetid", value);
            return this;
        }

        /**
         * or templetid in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTempletidIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("templetid", values);
            return this;
        }

        /**
         * or templetid in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTempletidIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("templetid", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or templetid not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTempletidNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("templetid", values);
            return this;
        }

        /**
         * or templetid not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTempletidNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("templetid", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or templetid between value1 and value2
         *
         * @return
         */
        public Criteria orTempletidBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("templetid", value1, value2);
            return this;
        }

        /**
         * or templetid not between value1 and value2
         *
         * @return
         */
        public Criteria orTempletidNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("templetid", value1, value2);
            return this;
        }

        /**
         * or templetid like value
         *
         * @return
         */
        public Criteria orTempletidLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("templetid", value);
            return this;
        }

        /**
         * or templetid not like value
         *
         * @return
         */
        public Criteria orTempletidNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("templetid", value);
            return this;
        }

        /**
         * and templet_name = ''
         *
         * @return
         */
        public Criteria andTempletNameIsEmpty() {
            this.andEqualTo("templetName", "");
            return this;
        }

        /**
         * and templet_name != ''
         *
         * @return
         */
        public Criteria andTempletNameIsNotEmpty() {
            this.andNotEqualTo("templetName", "");
            return this;
        }

        /**
         * and templet_name = value
         *
         * @return
         */
        public Criteria andTempletNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("templetName", value);
            return this;
        }

        /**
         * and templet_name != value
         *
         * @return
         */
        public Criteria andTempletNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("templetName", value);
            return this;
        }

        /**
         * and templet_name > value
         *
         * @return
         */
        public Criteria andTempletNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("templetName", value);
            return this;
        }

        /**
         * and templet_name >= value
         *
         * @return
         */
        public Criteria andTempletNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("templetName", value);
            return this;
        }

        /**
         * and templet_name < value
         *
         * @return
         */
        public Criteria andTempletNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("templetName", value);
            return this;
        }

        /**
         * and templet_name <= value
         *
         * @return
         */
        public Criteria andTempletNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("templetName", value);
            return this;
        }

        /**
         * and templet_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTempletNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("templetName", values);
            return this;
        }

        /**
         * and templet_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTempletNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("templetName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and templet_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTempletNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("templetName", values);
            return this;
        }

        /**
         * and templet_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTempletNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("templetName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and templet_name between value1 and value2
         *
         * @return
         */
        public Criteria andTempletNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("templetName", value1, value2);
            return this;
        }

        /**
         * and templet_name not between value1 and value2
         *
         * @return
         */
        public Criteria andTempletNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("templetName", value1, value2);
            return this;
        }

        /**
         * and templet_name like value
         *
         * @return
         */
        public Criteria andTempletNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("templetName", value);
            return this;
        }

        /**
         * and templet_name not like value
         *
         * @return
         */
        public Criteria andTempletNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("templetName", value);
            return this;
        }

        /**
         * or templet_name = ''
         *
         * @return
         */
        public Criteria orTempletNameIsEmpty() {
            this.orEqualTo("templetName", "");
            return this;
        }

        /**
         * or templet_name != ''
         *
         * @return
         */
        public Criteria orTempletNameIsNotEmpty() {
            this.orNotEqualTo("templetName", "");
            return this;
        }

        /**
         * or templet_name = value
         *
         * @return
         */
        public Criteria orTempletNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("templetName", value);
            return this;
        }

        /**
         * or templet_name != value
         *
         * @return
         */
        public Criteria orTempletNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("templetName", value);
            return this;
        }

        /**
         * or templet_name > value
         *
         * @return
         */
        public Criteria orTempletNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("templetName", value);
            return this;
        }

        /**
         * or templet_name >= value
         *
         * @return
         */
        public Criteria orTempletNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("templetName", value);
            return this;
        }

        /**
         * or templet_name < value
         *
         * @return
         */
        public Criteria orTempletNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("templetName", value);
            return this;
        }

        /**
         * or templet_name <= value
         *
         * @return
         */
        public Criteria orTempletNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("templetName", value);
            return this;
        }

        /**
         * or templet_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTempletNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("templetName", values);
            return this;
        }

        /**
         * or templet_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTempletNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("templetName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or templet_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTempletNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("templetName", values);
            return this;
        }

        /**
         * or templet_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTempletNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("templetName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or templet_name between value1 and value2
         *
         * @return
         */
        public Criteria orTempletNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("templetName", value1, value2);
            return this;
        }

        /**
         * or templet_name not between value1 and value2
         *
         * @return
         */
        public Criteria orTempletNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("templetName", value1, value2);
            return this;
        }

        /**
         * or templet_name like value
         *
         * @return
         */
        public Criteria orTempletNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("templetName", value);
            return this;
        }

        /**
         * or templet_name not like value
         *
         * @return
         */
        public Criteria orTempletNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("templetName", value);
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
