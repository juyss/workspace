package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.TPushInformation;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.Date;

/**
 * t_push_information 信息推送列表
 *
 * @author jy
 * @since 2020/11/03
 */
public class TPushInformationExample extends AbstractExample<TPushInformation> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public TPushInformationExample() {
        super(TPushInformation.class);
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
         * order by title
         *
         * @return
         */
        public OrderBy title() {
            this.orderBy("title");
            return this;
        }
        
        /**
         * order by plate_id
         *
         * @return
         */
        public OrderBy plateId() {
            this.orderBy("plateId");
            return this;
        }
        
        /**
         * order by plate_name
         *
         * @return
         */
        public OrderBy plateName() {
            this.orderBy("plateName");
            return this;
        }
        
        /**
         * order by push_channel_id
         *
         * @return
         */
        public OrderBy pushChannelId() {
            this.orderBy("pushChannelId");
            return this;
        }
        
        /**
         * order by push_channel_name
         *
         * @return
         */
        public OrderBy pushChannelName() {
            this.orderBy("pushChannelName");
            return this;
        }
        
        /**
         * order by is_push
         *
         * @return
         */
        public OrderBy isPush() {
            this.orderBy("isPush");
            return this;
        }
        
        /**
         * order by regular_time
         *
         * @return
         */
        public OrderBy regularTime() {
            this.orderBy("regularTime");
            return this;
        }
        
        /**
         * order by push_time
         *
         * @return
         */
        public OrderBy pushTime() {
            this.orderBy("pushTime");
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
         * order by push_content
         *
         * @return
         */
        public OrderBy pushContent() {
            this.orderBy("pushContent");
            return this;
        }
        
        /**
         * order by policy_push_department
         *
         * @return
         */
        public OrderBy policyPushDepartment() {
            this.orderBy("policyPushDepartment");
            return this;
        }
        
        /**
         * order by policy_number
         *
         * @return
         */
        public OrderBy policyNumber() {
            this.orderBy("policyNumber");
            return this;
        }
        
        /**
         * order by policy_title
         *
         * @return
         */
        public OrderBy policyTitle() {
            this.orderBy("policyTitle");
            return this;
        }
        
        /**
         * order by theme_image
         *
         * @return
         */
        public OrderBy themeImage() {
            this.orderBy("themeImage");
            return this;
        }
        
        /**
         * order by create_data
         *
         * @return
         */
        public OrderBy createData() {
            this.orderBy("createData");
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
         * and title = ''
         *
         * @return
         */
        public Criteria andTitleIsEmpty() {
            this.andEqualTo("title", "");
            return this;
        }

        /**
         * and title != ''
         *
         * @return
         */
        public Criteria andTitleIsNotEmpty() {
            this.andNotEqualTo("title", "");
            return this;
        }

        /**
         * and title = value
         *
         * @return
         */
        public Criteria andTitleEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("title", value);
            return this;
        }

        /**
         * and title != value
         *
         * @return
         */
        public Criteria andTitleNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("title", value);
            return this;
        }

        /**
         * and title > value
         *
         * @return
         */
        public Criteria andTitleGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("title", value);
            return this;
        }

        /**
         * and title >= value
         *
         * @return
         */
        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("title", value);
            return this;
        }

        /**
         * and title < value
         *
         * @return
         */
        public Criteria andTitleLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("title", value);
            return this;
        }

        /**
         * and title <= value
         *
         * @return
         */
        public Criteria andTitleLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("title", value);
            return this;
        }

        /**
         * and title in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTitleIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("title", values);
            return this;
        }

        /**
         * and title in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTitleIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("title", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and title not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTitleNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("title", values);
            return this;
        }

        /**
         * and title not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andTitleNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("title", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and title between value1 and value2
         *
         * @return
         */
        public Criteria andTitleBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("title", value1, value2);
            return this;
        }

        /**
         * and title not between value1 and value2
         *
         * @return
         */
        public Criteria andTitleNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("title", value1, value2);
            return this;
        }

        /**
         * and title like value
         *
         * @return
         */
        public Criteria andTitleLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("title", value);
            return this;
        }

        /**
         * and title not like value
         *
         * @return
         */
        public Criteria andTitleNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("title", value);
            return this;
        }

        /**
         * or title = ''
         *
         * @return
         */
        public Criteria orTitleIsEmpty() {
            this.orEqualTo("title", "");
            return this;
        }

        /**
         * or title != ''
         *
         * @return
         */
        public Criteria orTitleIsNotEmpty() {
            this.orNotEqualTo("title", "");
            return this;
        }

        /**
         * or title = value
         *
         * @return
         */
        public Criteria orTitleEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("title", value);
            return this;
        }

        /**
         * or title != value
         *
         * @return
         */
        public Criteria orTitleNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("title", value);
            return this;
        }

        /**
         * or title > value
         *
         * @return
         */
        public Criteria orTitleGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("title", value);
            return this;
        }

        /**
         * or title >= value
         *
         * @return
         */
        public Criteria orTitleGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("title", value);
            return this;
        }

        /**
         * or title < value
         *
         * @return
         */
        public Criteria orTitleLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("title", value);
            return this;
        }

        /**
         * or title <= value
         *
         * @return
         */
        public Criteria orTitleLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("title", value);
            return this;
        }

        /**
         * or title in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTitleIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("title", values);
            return this;
        }

        /**
         * or title in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTitleIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("title", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or title not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTitleNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("title", values);
            return this;
        }

        /**
         * or title not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orTitleNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("title", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or title between value1 and value2
         *
         * @return
         */
        public Criteria orTitleBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("title", value1, value2);
            return this;
        }

        /**
         * or title not between value1 and value2
         *
         * @return
         */
        public Criteria orTitleNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("title", value1, value2);
            return this;
        }

        /**
         * or title like value
         *
         * @return
         */
        public Criteria orTitleLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("title", value);
            return this;
        }

        /**
         * or title not like value
         *
         * @return
         */
        public Criteria orTitleNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("title", value);
            return this;
        }

        /**
         * and plate_id = value
         *
         * @return
         */
        public Criteria andPlateIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("plateId", value);
            return this;
        }

        /**
         * and plate_id != value
         *
         * @return
         */
        public Criteria andPlateIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("plateId", value);
            return this;
        }

        /**
         * and plate_id > value
         *
         * @return
         */
        public Criteria andPlateIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("plateId", value);
            return this;
        }

        /**
         * and plate_id >= value
         *
         * @return
         */
        public Criteria andPlateIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("plateId", value);
            return this;
        }

        /**
         * and plate_id < value
         *
         * @return
         */
        public Criteria andPlateIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("plateId", value);
            return this;
        }

        /**
         * and plate_id <= value
         *
         * @return
         */
        public Criteria andPlateIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("plateId", value);
            return this;
        }

        /**
         * and plate_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlateIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("plateId", values);
            return this;
        }

        /**
         * and plate_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlateIdIn(Long... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("plateId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and plate_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlateIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("plateId", values);
            return this;
        }

        /**
         * and plate_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlateIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("plateId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and plate_id between value1 and value2
         *
         * @return
         */
        public Criteria andPlateIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("plateId", value1, value2);
            return this;
        }

        /**
         * and plate_id not between value1 and value2
         *
         * @return
         */
        public Criteria andPlateIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("plateId", value1, value2);
            return this;
        }

        /**
         * and plate_id like value
         *
         * @return
         */
        public Criteria andPlateIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("plateId", value);
            return this;
        }

        /**
         * and plate_id not like value
         *
         * @return
         */
        public Criteria andPlateIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("plateId", value);
            return this;
        }

        /**
         * or plate_id = value
         *
         * @return
         */
        public Criteria orPlateIdEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("plateId", value);
            return this;
        }

        /**
         * or plate_id != value
         *
         * @return
         */
        public Criteria orPlateIdNotEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("plateId", value);
            return this;
        }

        /**
         * or plate_id > value
         *
         * @return
         */
        public Criteria orPlateIdGreaterThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("plateId", value);
            return this;
        }

        /**
         * or plate_id >= value
         *
         * @return
         */
        public Criteria orPlateIdGreaterThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("plateId", value);
            return this;
        }

        /**
         * or plate_id < value
         *
         * @return
         */
        public Criteria orPlateIdLessThan(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("plateId", value);
            return this;
        }

        /**
         * or plate_id <= value
         *
         * @return
         */
        public Criteria orPlateIdLessThanOrEqualTo(Long value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("plateId", value);
            return this;
        }

        /**
         * or plate_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlateIdIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("plateId", values);
            return this;
        }

        /**
         * or plate_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlateIdIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("plateId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or plate_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlateIdNotIn(Iterable<Long> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("plateId", values);
            return this;
        }

        /**
         * or plate_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlateIdNotIn(Long[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("plateId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or plate_id between value1 and value2
         *
         * @return
         */
        public Criteria orPlateIdBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("plateId", value1, value2);
            return this;
        }

        /**
         * or plate_id not between value1 and value2
         *
         * @return
         */
        public Criteria orPlateIdNotBetween(Long value1, Long value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("plateId", value1, value2);
            return this;
        }

        /**
         * or plate_id like value
         *
         * @return
         */
        public Criteria orPlateIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("plateId", value);
            return this;
        }

        /**
         * or plate_id not like value
         *
         * @return
         */
        public Criteria orPlateIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("plateId", value);
            return this;
        }

        /**
         * and plate_name = ''
         *
         * @return
         */
        public Criteria andPlateNameIsEmpty() {
            this.andEqualTo("plateName", "");
            return this;
        }

        /**
         * and plate_name != ''
         *
         * @return
         */
        public Criteria andPlateNameIsNotEmpty() {
            this.andNotEqualTo("plateName", "");
            return this;
        }

        /**
         * and plate_name = value
         *
         * @return
         */
        public Criteria andPlateNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("plateName", value);
            return this;
        }

        /**
         * and plate_name != value
         *
         * @return
         */
        public Criteria andPlateNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("plateName", value);
            return this;
        }

        /**
         * and plate_name > value
         *
         * @return
         */
        public Criteria andPlateNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("plateName", value);
            return this;
        }

        /**
         * and plate_name >= value
         *
         * @return
         */
        public Criteria andPlateNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("plateName", value);
            return this;
        }

        /**
         * and plate_name < value
         *
         * @return
         */
        public Criteria andPlateNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("plateName", value);
            return this;
        }

        /**
         * and plate_name <= value
         *
         * @return
         */
        public Criteria andPlateNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("plateName", value);
            return this;
        }

        /**
         * and plate_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlateNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("plateName", values);
            return this;
        }

        /**
         * and plate_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlateNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("plateName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and plate_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlateNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("plateName", values);
            return this;
        }

        /**
         * and plate_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPlateNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("plateName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and plate_name between value1 and value2
         *
         * @return
         */
        public Criteria andPlateNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("plateName", value1, value2);
            return this;
        }

        /**
         * and plate_name not between value1 and value2
         *
         * @return
         */
        public Criteria andPlateNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("plateName", value1, value2);
            return this;
        }

        /**
         * and plate_name like value
         *
         * @return
         */
        public Criteria andPlateNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("plateName", value);
            return this;
        }

        /**
         * and plate_name not like value
         *
         * @return
         */
        public Criteria andPlateNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("plateName", value);
            return this;
        }

        /**
         * or plate_name = ''
         *
         * @return
         */
        public Criteria orPlateNameIsEmpty() {
            this.orEqualTo("plateName", "");
            return this;
        }

        /**
         * or plate_name != ''
         *
         * @return
         */
        public Criteria orPlateNameIsNotEmpty() {
            this.orNotEqualTo("plateName", "");
            return this;
        }

        /**
         * or plate_name = value
         *
         * @return
         */
        public Criteria orPlateNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("plateName", value);
            return this;
        }

        /**
         * or plate_name != value
         *
         * @return
         */
        public Criteria orPlateNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("plateName", value);
            return this;
        }

        /**
         * or plate_name > value
         *
         * @return
         */
        public Criteria orPlateNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("plateName", value);
            return this;
        }

        /**
         * or plate_name >= value
         *
         * @return
         */
        public Criteria orPlateNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("plateName", value);
            return this;
        }

        /**
         * or plate_name < value
         *
         * @return
         */
        public Criteria orPlateNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("plateName", value);
            return this;
        }

        /**
         * or plate_name <= value
         *
         * @return
         */
        public Criteria orPlateNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("plateName", value);
            return this;
        }

        /**
         * or plate_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlateNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("plateName", values);
            return this;
        }

        /**
         * or plate_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlateNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("plateName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or plate_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlateNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("plateName", values);
            return this;
        }

        /**
         * or plate_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPlateNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("plateName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or plate_name between value1 and value2
         *
         * @return
         */
        public Criteria orPlateNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("plateName", value1, value2);
            return this;
        }

        /**
         * or plate_name not between value1 and value2
         *
         * @return
         */
        public Criteria orPlateNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("plateName", value1, value2);
            return this;
        }

        /**
         * or plate_name like value
         *
         * @return
         */
        public Criteria orPlateNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("plateName", value);
            return this;
        }

        /**
         * or plate_name not like value
         *
         * @return
         */
        public Criteria orPlateNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("plateName", value);
            return this;
        }

        /**
         * and push_channel_id = ''
         *
         * @return
         */
        public Criteria andPushChannelIdIsEmpty() {
            this.andEqualTo("pushChannelId", "");
            return this;
        }

        /**
         * and push_channel_id != ''
         *
         * @return
         */
        public Criteria andPushChannelIdIsNotEmpty() {
            this.andNotEqualTo("pushChannelId", "");
            return this;
        }

        /**
         * and push_channel_id = value
         *
         * @return
         */
        public Criteria andPushChannelIdEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("pushChannelId", value);
            return this;
        }

        /**
         * and push_channel_id != value
         *
         * @return
         */
        public Criteria andPushChannelIdNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("pushChannelId", value);
            return this;
        }

        /**
         * and push_channel_id > value
         *
         * @return
         */
        public Criteria andPushChannelIdGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("pushChannelId", value);
            return this;
        }

        /**
         * and push_channel_id >= value
         *
         * @return
         */
        public Criteria andPushChannelIdGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("pushChannelId", value);
            return this;
        }

        /**
         * and push_channel_id < value
         *
         * @return
         */
        public Criteria andPushChannelIdLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("pushChannelId", value);
            return this;
        }

        /**
         * and push_channel_id <= value
         *
         * @return
         */
        public Criteria andPushChannelIdLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("pushChannelId", value);
            return this;
        }

        /**
         * and push_channel_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushChannelIdIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pushChannelId", values);
            return this;
        }

        /**
         * and push_channel_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushChannelIdIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pushChannelId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and push_channel_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushChannelIdNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pushChannelId", values);
            return this;
        }

        /**
         * and push_channel_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushChannelIdNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pushChannelId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and push_channel_id between value1 and value2
         *
         * @return
         */
        public Criteria andPushChannelIdBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("pushChannelId", value1, value2);
            return this;
        }

        /**
         * and push_channel_id not between value1 and value2
         *
         * @return
         */
        public Criteria andPushChannelIdNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("pushChannelId", value1, value2);
            return this;
        }

        /**
         * and push_channel_id like value
         *
         * @return
         */
        public Criteria andPushChannelIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("pushChannelId", value);
            return this;
        }

        /**
         * and push_channel_id not like value
         *
         * @return
         */
        public Criteria andPushChannelIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("pushChannelId", value);
            return this;
        }

        /**
         * or push_channel_id = ''
         *
         * @return
         */
        public Criteria orPushChannelIdIsEmpty() {
            this.orEqualTo("pushChannelId", "");
            return this;
        }

        /**
         * or push_channel_id != ''
         *
         * @return
         */
        public Criteria orPushChannelIdIsNotEmpty() {
            this.orNotEqualTo("pushChannelId", "");
            return this;
        }

        /**
         * or push_channel_id = value
         *
         * @return
         */
        public Criteria orPushChannelIdEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("pushChannelId", value);
            return this;
        }

        /**
         * or push_channel_id != value
         *
         * @return
         */
        public Criteria orPushChannelIdNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("pushChannelId", value);
            return this;
        }

        /**
         * or push_channel_id > value
         *
         * @return
         */
        public Criteria orPushChannelIdGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("pushChannelId", value);
            return this;
        }

        /**
         * or push_channel_id >= value
         *
         * @return
         */
        public Criteria orPushChannelIdGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("pushChannelId", value);
            return this;
        }

        /**
         * or push_channel_id < value
         *
         * @return
         */
        public Criteria orPushChannelIdLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("pushChannelId", value);
            return this;
        }

        /**
         * or push_channel_id <= value
         *
         * @return
         */
        public Criteria orPushChannelIdLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("pushChannelId", value);
            return this;
        }

        /**
         * or push_channel_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushChannelIdIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pushChannelId", values);
            return this;
        }

        /**
         * or push_channel_id in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushChannelIdIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pushChannelId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or push_channel_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushChannelIdNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pushChannelId", values);
            return this;
        }

        /**
         * or push_channel_id not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushChannelIdNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pushChannelId", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or push_channel_id between value1 and value2
         *
         * @return
         */
        public Criteria orPushChannelIdBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("pushChannelId", value1, value2);
            return this;
        }

        /**
         * or push_channel_id not between value1 and value2
         *
         * @return
         */
        public Criteria orPushChannelIdNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("pushChannelId", value1, value2);
            return this;
        }

        /**
         * or push_channel_id like value
         *
         * @return
         */
        public Criteria orPushChannelIdLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("pushChannelId", value);
            return this;
        }

        /**
         * or push_channel_id not like value
         *
         * @return
         */
        public Criteria orPushChannelIdNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("pushChannelId", value);
            return this;
        }

        /**
         * and push_channel_name = ''
         *
         * @return
         */
        public Criteria andPushChannelNameIsEmpty() {
            this.andEqualTo("pushChannelName", "");
            return this;
        }

        /**
         * and push_channel_name != ''
         *
         * @return
         */
        public Criteria andPushChannelNameIsNotEmpty() {
            this.andNotEqualTo("pushChannelName", "");
            return this;
        }

        /**
         * and push_channel_name = value
         *
         * @return
         */
        public Criteria andPushChannelNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("pushChannelName", value);
            return this;
        }

        /**
         * and push_channel_name != value
         *
         * @return
         */
        public Criteria andPushChannelNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("pushChannelName", value);
            return this;
        }

        /**
         * and push_channel_name > value
         *
         * @return
         */
        public Criteria andPushChannelNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("pushChannelName", value);
            return this;
        }

        /**
         * and push_channel_name >= value
         *
         * @return
         */
        public Criteria andPushChannelNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("pushChannelName", value);
            return this;
        }

        /**
         * and push_channel_name < value
         *
         * @return
         */
        public Criteria andPushChannelNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("pushChannelName", value);
            return this;
        }

        /**
         * and push_channel_name <= value
         *
         * @return
         */
        public Criteria andPushChannelNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("pushChannelName", value);
            return this;
        }

        /**
         * and push_channel_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushChannelNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pushChannelName", values);
            return this;
        }

        /**
         * and push_channel_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushChannelNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pushChannelName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and push_channel_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushChannelNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pushChannelName", values);
            return this;
        }

        /**
         * and push_channel_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushChannelNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pushChannelName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and push_channel_name between value1 and value2
         *
         * @return
         */
        public Criteria andPushChannelNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("pushChannelName", value1, value2);
            return this;
        }

        /**
         * and push_channel_name not between value1 and value2
         *
         * @return
         */
        public Criteria andPushChannelNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("pushChannelName", value1, value2);
            return this;
        }

        /**
         * and push_channel_name like value
         *
         * @return
         */
        public Criteria andPushChannelNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("pushChannelName", value);
            return this;
        }

        /**
         * and push_channel_name not like value
         *
         * @return
         */
        public Criteria andPushChannelNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("pushChannelName", value);
            return this;
        }

        /**
         * or push_channel_name = ''
         *
         * @return
         */
        public Criteria orPushChannelNameIsEmpty() {
            this.orEqualTo("pushChannelName", "");
            return this;
        }

        /**
         * or push_channel_name != ''
         *
         * @return
         */
        public Criteria orPushChannelNameIsNotEmpty() {
            this.orNotEqualTo("pushChannelName", "");
            return this;
        }

        /**
         * or push_channel_name = value
         *
         * @return
         */
        public Criteria orPushChannelNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("pushChannelName", value);
            return this;
        }

        /**
         * or push_channel_name != value
         *
         * @return
         */
        public Criteria orPushChannelNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("pushChannelName", value);
            return this;
        }

        /**
         * or push_channel_name > value
         *
         * @return
         */
        public Criteria orPushChannelNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("pushChannelName", value);
            return this;
        }

        /**
         * or push_channel_name >= value
         *
         * @return
         */
        public Criteria orPushChannelNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("pushChannelName", value);
            return this;
        }

        /**
         * or push_channel_name < value
         *
         * @return
         */
        public Criteria orPushChannelNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("pushChannelName", value);
            return this;
        }

        /**
         * or push_channel_name <= value
         *
         * @return
         */
        public Criteria orPushChannelNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("pushChannelName", value);
            return this;
        }

        /**
         * or push_channel_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushChannelNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pushChannelName", values);
            return this;
        }

        /**
         * or push_channel_name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushChannelNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pushChannelName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or push_channel_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushChannelNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pushChannelName", values);
            return this;
        }

        /**
         * or push_channel_name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushChannelNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pushChannelName", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or push_channel_name between value1 and value2
         *
         * @return
         */
        public Criteria orPushChannelNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("pushChannelName", value1, value2);
            return this;
        }

        /**
         * or push_channel_name not between value1 and value2
         *
         * @return
         */
        public Criteria orPushChannelNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("pushChannelName", value1, value2);
            return this;
        }

        /**
         * or push_channel_name like value
         *
         * @return
         */
        public Criteria orPushChannelNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("pushChannelName", value);
            return this;
        }

        /**
         * or push_channel_name not like value
         *
         * @return
         */
        public Criteria orPushChannelNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("pushChannelName", value);
            return this;
        }

        /**
         * and is_push = value
         *
         * @return
         */
        public Criteria andIsPushEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("isPush", value);
            return this;
        }

        /**
         * and is_push != value
         *
         * @return
         */
        public Criteria andIsPushNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("isPush", value);
            return this;
        }

        /**
         * and is_push > value
         *
         * @return
         */
        public Criteria andIsPushGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("isPush", value);
            return this;
        }

        /**
         * and is_push >= value
         *
         * @return
         */
        public Criteria andIsPushGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("isPush", value);
            return this;
        }

        /**
         * and is_push < value
         *
         * @return
         */
        public Criteria andIsPushLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("isPush", value);
            return this;
        }

        /**
         * and is_push <= value
         *
         * @return
         */
        public Criteria andIsPushLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("isPush", value);
            return this;
        }

        /**
         * and is_push in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIsPushIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("isPush", values);
            return this;
        }

        /**
         * and is_push in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIsPushIn(Integer... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("isPush", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and is_push not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIsPushNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("isPush", values);
            return this;
        }

        /**
         * and is_push not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andIsPushNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("isPush", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and is_push between value1 and value2
         *
         * @return
         */
        public Criteria andIsPushBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("isPush", value1, value2);
            return this;
        }

        /**
         * and is_push not between value1 and value2
         *
         * @return
         */
        public Criteria andIsPushNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("isPush", value1, value2);
            return this;
        }

        /**
         * and is_push like value
         *
         * @return
         */
        public Criteria andIsPushLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("isPush", value);
            return this;
        }

        /**
         * and is_push not like value
         *
         * @return
         */
        public Criteria andIsPushNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("isPush", value);
            return this;
        }

        /**
         * or is_push = value
         *
         * @return
         */
        public Criteria orIsPushEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("isPush", value);
            return this;
        }

        /**
         * or is_push != value
         *
         * @return
         */
        public Criteria orIsPushNotEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("isPush", value);
            return this;
        }

        /**
         * or is_push > value
         *
         * @return
         */
        public Criteria orIsPushGreaterThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("isPush", value);
            return this;
        }

        /**
         * or is_push >= value
         *
         * @return
         */
        public Criteria orIsPushGreaterThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("isPush", value);
            return this;
        }

        /**
         * or is_push < value
         *
         * @return
         */
        public Criteria orIsPushLessThan(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("isPush", value);
            return this;
        }

        /**
         * or is_push <= value
         *
         * @return
         */
        public Criteria orIsPushLessThanOrEqualTo(Integer value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("isPush", value);
            return this;
        }

        /**
         * or is_push in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIsPushIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("isPush", values);
            return this;
        }

        /**
         * or is_push in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIsPushIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("isPush", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or is_push not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIsPushNotIn(Iterable<Integer> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("isPush", values);
            return this;
        }

        /**
         * or is_push not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orIsPushNotIn(Integer[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("isPush", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or is_push between value1 and value2
         *
         * @return
         */
        public Criteria orIsPushBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("isPush", value1, value2);
            return this;
        }

        /**
         * or is_push not between value1 and value2
         *
         * @return
         */
        public Criteria orIsPushNotBetween(Integer value1, Integer value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("isPush", value1, value2);
            return this;
        }

        /**
         * or is_push like value
         *
         * @return
         */
        public Criteria orIsPushLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("isPush", value);
            return this;
        }

        /**
         * or is_push not like value
         *
         * @return
         */
        public Criteria orIsPushNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("isPush", value);
            return this;
        }

        /**
         * and regular_time = value
         *
         * @return
         */
        public Criteria andRegularTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("regularTime", value);
            return this;
        }

        /**
         * and regular_time != value
         *
         * @return
         */
        public Criteria andRegularTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("regularTime", value);
            return this;
        }

        /**
         * and regular_time > value
         *
         * @return
         */
        public Criteria andRegularTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("regularTime", value);
            return this;
        }

        /**
         * and regular_time >= value
         *
         * @return
         */
        public Criteria andRegularTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("regularTime", value);
            return this;
        }

        /**
         * and regular_time < value
         *
         * @return
         */
        public Criteria andRegularTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("regularTime", value);
            return this;
        }

        /**
         * and regular_time <= value
         *
         * @return
         */
        public Criteria andRegularTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("regularTime", value);
            return this;
        }

        /**
         * and regular_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andRegularTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("regularTime", values);
            return this;
        }

        /**
         * and regular_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andRegularTimeIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("regularTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and regular_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andRegularTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("regularTime", values);
            return this;
        }

        /**
         * and regular_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andRegularTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("regularTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and regular_time between value1 and value2
         *
         * @return
         */
        public Criteria andRegularTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("regularTime", value1, value2);
            return this;
        }

        /**
         * and regular_time not between value1 and value2
         *
         * @return
         */
        public Criteria andRegularTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("regularTime", value1, value2);
            return this;
        }

        /**
         * and regular_time like value
         *
         * @return
         */
        public Criteria andRegularTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("regularTime", value);
            return this;
        }

        /**
         * and regular_time not like value
         *
         * @return
         */
        public Criteria andRegularTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("regularTime", value);
            return this;
        }

        /**
         * or regular_time = value
         *
         * @return
         */
        public Criteria orRegularTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("regularTime", value);
            return this;
        }

        /**
         * or regular_time != value
         *
         * @return
         */
        public Criteria orRegularTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("regularTime", value);
            return this;
        }

        /**
         * or regular_time > value
         *
         * @return
         */
        public Criteria orRegularTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("regularTime", value);
            return this;
        }

        /**
         * or regular_time >= value
         *
         * @return
         */
        public Criteria orRegularTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("regularTime", value);
            return this;
        }

        /**
         * or regular_time < value
         *
         * @return
         */
        public Criteria orRegularTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("regularTime", value);
            return this;
        }

        /**
         * or regular_time <= value
         *
         * @return
         */
        public Criteria orRegularTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("regularTime", value);
            return this;
        }

        /**
         * or regular_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orRegularTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("regularTime", values);
            return this;
        }

        /**
         * or regular_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orRegularTimeIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("regularTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or regular_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orRegularTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("regularTime", values);
            return this;
        }

        /**
         * or regular_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orRegularTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("regularTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or regular_time between value1 and value2
         *
         * @return
         */
        public Criteria orRegularTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("regularTime", value1, value2);
            return this;
        }

        /**
         * or regular_time not between value1 and value2
         *
         * @return
         */
        public Criteria orRegularTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("regularTime", value1, value2);
            return this;
        }

        /**
         * or regular_time like value
         *
         * @return
         */
        public Criteria orRegularTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("regularTime", value);
            return this;
        }

        /**
         * or regular_time not like value
         *
         * @return
         */
        public Criteria orRegularTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("regularTime", value);
            return this;
        }

        /**
         * and push_time = value
         *
         * @return
         */
        public Criteria andPushTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("pushTime", value);
            return this;
        }

        /**
         * and push_time != value
         *
         * @return
         */
        public Criteria andPushTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("pushTime", value);
            return this;
        }

        /**
         * and push_time > value
         *
         * @return
         */
        public Criteria andPushTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("pushTime", value);
            return this;
        }

        /**
         * and push_time >= value
         *
         * @return
         */
        public Criteria andPushTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("pushTime", value);
            return this;
        }

        /**
         * and push_time < value
         *
         * @return
         */
        public Criteria andPushTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("pushTime", value);
            return this;
        }

        /**
         * and push_time <= value
         *
         * @return
         */
        public Criteria andPushTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("pushTime", value);
            return this;
        }

        /**
         * and push_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pushTime", values);
            return this;
        }

        /**
         * and push_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushTimeIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pushTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and push_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pushTime", values);
            return this;
        }

        /**
         * and push_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pushTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and push_time between value1 and value2
         *
         * @return
         */
        public Criteria andPushTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("pushTime", value1, value2);
            return this;
        }

        /**
         * and push_time not between value1 and value2
         *
         * @return
         */
        public Criteria andPushTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("pushTime", value1, value2);
            return this;
        }

        /**
         * and push_time like value
         *
         * @return
         */
        public Criteria andPushTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("pushTime", value);
            return this;
        }

        /**
         * and push_time not like value
         *
         * @return
         */
        public Criteria andPushTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("pushTime", value);
            return this;
        }

        /**
         * or push_time = value
         *
         * @return
         */
        public Criteria orPushTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("pushTime", value);
            return this;
        }

        /**
         * or push_time != value
         *
         * @return
         */
        public Criteria orPushTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("pushTime", value);
            return this;
        }

        /**
         * or push_time > value
         *
         * @return
         */
        public Criteria orPushTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("pushTime", value);
            return this;
        }

        /**
         * or push_time >= value
         *
         * @return
         */
        public Criteria orPushTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("pushTime", value);
            return this;
        }

        /**
         * or push_time < value
         *
         * @return
         */
        public Criteria orPushTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("pushTime", value);
            return this;
        }

        /**
         * or push_time <= value
         *
         * @return
         */
        public Criteria orPushTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("pushTime", value);
            return this;
        }

        /**
         * or push_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pushTime", values);
            return this;
        }

        /**
         * or push_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushTimeIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pushTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or push_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pushTime", values);
            return this;
        }

        /**
         * or push_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pushTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or push_time between value1 and value2
         *
         * @return
         */
        public Criteria orPushTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("pushTime", value1, value2);
            return this;
        }

        /**
         * or push_time not between value1 and value2
         *
         * @return
         */
        public Criteria orPushTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("pushTime", value1, value2);
            return this;
        }

        /**
         * or push_time like value
         *
         * @return
         */
        public Criteria orPushTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("pushTime", value);
            return this;
        }

        /**
         * or push_time not like value
         *
         * @return
         */
        public Criteria orPushTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("pushTime", value);
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
         * and push_content = ''
         *
         * @return
         */
        public Criteria andPushContentIsEmpty() {
            this.andEqualTo("pushContent", "");
            return this;
        }

        /**
         * and push_content != ''
         *
         * @return
         */
        public Criteria andPushContentIsNotEmpty() {
            this.andNotEqualTo("pushContent", "");
            return this;
        }

        /**
         * and push_content = value
         *
         * @return
         */
        public Criteria andPushContentEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("pushContent", value);
            return this;
        }

        /**
         * and push_content != value
         *
         * @return
         */
        public Criteria andPushContentNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("pushContent", value);
            return this;
        }

        /**
         * and push_content > value
         *
         * @return
         */
        public Criteria andPushContentGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("pushContent", value);
            return this;
        }

        /**
         * and push_content >= value
         *
         * @return
         */
        public Criteria andPushContentGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("pushContent", value);
            return this;
        }

        /**
         * and push_content < value
         *
         * @return
         */
        public Criteria andPushContentLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("pushContent", value);
            return this;
        }

        /**
         * and push_content <= value
         *
         * @return
         */
        public Criteria andPushContentLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("pushContent", value);
            return this;
        }

        /**
         * and push_content in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushContentIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pushContent", values);
            return this;
        }

        /**
         * and push_content in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushContentIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("pushContent", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and push_content not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushContentNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pushContent", values);
            return this;
        }

        /**
         * and push_content not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPushContentNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("pushContent", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and push_content between value1 and value2
         *
         * @return
         */
        public Criteria andPushContentBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("pushContent", value1, value2);
            return this;
        }

        /**
         * and push_content not between value1 and value2
         *
         * @return
         */
        public Criteria andPushContentNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("pushContent", value1, value2);
            return this;
        }

        /**
         * and push_content like value
         *
         * @return
         */
        public Criteria andPushContentLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("pushContent", value);
            return this;
        }

        /**
         * and push_content not like value
         *
         * @return
         */
        public Criteria andPushContentNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("pushContent", value);
            return this;
        }

        /**
         * or push_content = ''
         *
         * @return
         */
        public Criteria orPushContentIsEmpty() {
            this.orEqualTo("pushContent", "");
            return this;
        }

        /**
         * or push_content != ''
         *
         * @return
         */
        public Criteria orPushContentIsNotEmpty() {
            this.orNotEqualTo("pushContent", "");
            return this;
        }

        /**
         * or push_content = value
         *
         * @return
         */
        public Criteria orPushContentEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("pushContent", value);
            return this;
        }

        /**
         * or push_content != value
         *
         * @return
         */
        public Criteria orPushContentNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("pushContent", value);
            return this;
        }

        /**
         * or push_content > value
         *
         * @return
         */
        public Criteria orPushContentGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("pushContent", value);
            return this;
        }

        /**
         * or push_content >= value
         *
         * @return
         */
        public Criteria orPushContentGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("pushContent", value);
            return this;
        }

        /**
         * or push_content < value
         *
         * @return
         */
        public Criteria orPushContentLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("pushContent", value);
            return this;
        }

        /**
         * or push_content <= value
         *
         * @return
         */
        public Criteria orPushContentLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("pushContent", value);
            return this;
        }

        /**
         * or push_content in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushContentIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pushContent", values);
            return this;
        }

        /**
         * or push_content in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushContentIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("pushContent", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or push_content not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushContentNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pushContent", values);
            return this;
        }

        /**
         * or push_content not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPushContentNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("pushContent", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or push_content between value1 and value2
         *
         * @return
         */
        public Criteria orPushContentBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("pushContent", value1, value2);
            return this;
        }

        /**
         * or push_content not between value1 and value2
         *
         * @return
         */
        public Criteria orPushContentNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("pushContent", value1, value2);
            return this;
        }

        /**
         * or push_content like value
         *
         * @return
         */
        public Criteria orPushContentLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("pushContent", value);
            return this;
        }

        /**
         * or push_content not like value
         *
         * @return
         */
        public Criteria orPushContentNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("pushContent", value);
            return this;
        }

        /**
         * and policy_push_department = ''
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentIsEmpty() {
            this.andEqualTo("policyPushDepartment", "");
            return this;
        }

        /**
         * and policy_push_department != ''
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentIsNotEmpty() {
            this.andNotEqualTo("policyPushDepartment", "");
            return this;
        }

        /**
         * and policy_push_department = value
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("policyPushDepartment", value);
            return this;
        }

        /**
         * and policy_push_department != value
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("policyPushDepartment", value);
            return this;
        }

        /**
         * and policy_push_department > value
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("policyPushDepartment", value);
            return this;
        }

        /**
         * and policy_push_department >= value
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("policyPushDepartment", value);
            return this;
        }

        /**
         * and policy_push_department < value
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("policyPushDepartment", value);
            return this;
        }

        /**
         * and policy_push_department <= value
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("policyPushDepartment", value);
            return this;
        }

        /**
         * and policy_push_department in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("policyPushDepartment", values);
            return this;
        }

        /**
         * and policy_push_department in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("policyPushDepartment", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and policy_push_department not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("policyPushDepartment", values);
            return this;
        }

        /**
         * and policy_push_department not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("policyPushDepartment", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and policy_push_department between value1 and value2
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("policyPushDepartment", value1, value2);
            return this;
        }

        /**
         * and policy_push_department not between value1 and value2
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("policyPushDepartment", value1, value2);
            return this;
        }

        /**
         * and policy_push_department like value
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("policyPushDepartment", value);
            return this;
        }

        /**
         * and policy_push_department not like value
         *
         * @return
         */
        public Criteria andPolicyPushDepartmentNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("policyPushDepartment", value);
            return this;
        }

        /**
         * or policy_push_department = ''
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentIsEmpty() {
            this.orEqualTo("policyPushDepartment", "");
            return this;
        }

        /**
         * or policy_push_department != ''
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentIsNotEmpty() {
            this.orNotEqualTo("policyPushDepartment", "");
            return this;
        }

        /**
         * or policy_push_department = value
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("policyPushDepartment", value);
            return this;
        }

        /**
         * or policy_push_department != value
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("policyPushDepartment", value);
            return this;
        }

        /**
         * or policy_push_department > value
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("policyPushDepartment", value);
            return this;
        }

        /**
         * or policy_push_department >= value
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("policyPushDepartment", value);
            return this;
        }

        /**
         * or policy_push_department < value
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("policyPushDepartment", value);
            return this;
        }

        /**
         * or policy_push_department <= value
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("policyPushDepartment", value);
            return this;
        }

        /**
         * or policy_push_department in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("policyPushDepartment", values);
            return this;
        }

        /**
         * or policy_push_department in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("policyPushDepartment", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or policy_push_department not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("policyPushDepartment", values);
            return this;
        }

        /**
         * or policy_push_department not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("policyPushDepartment", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or policy_push_department between value1 and value2
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("policyPushDepartment", value1, value2);
            return this;
        }

        /**
         * or policy_push_department not between value1 and value2
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("policyPushDepartment", value1, value2);
            return this;
        }

        /**
         * or policy_push_department like value
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("policyPushDepartment", value);
            return this;
        }

        /**
         * or policy_push_department not like value
         *
         * @return
         */
        public Criteria orPolicyPushDepartmentNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("policyPushDepartment", value);
            return this;
        }

        /**
         * and policy_number = ''
         *
         * @return
         */
        public Criteria andPolicyNumberIsEmpty() {
            this.andEqualTo("policyNumber", "");
            return this;
        }

        /**
         * and policy_number != ''
         *
         * @return
         */
        public Criteria andPolicyNumberIsNotEmpty() {
            this.andNotEqualTo("policyNumber", "");
            return this;
        }

        /**
         * and policy_number = value
         *
         * @return
         */
        public Criteria andPolicyNumberEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("policyNumber", value);
            return this;
        }

        /**
         * and policy_number != value
         *
         * @return
         */
        public Criteria andPolicyNumberNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("policyNumber", value);
            return this;
        }

        /**
         * and policy_number > value
         *
         * @return
         */
        public Criteria andPolicyNumberGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("policyNumber", value);
            return this;
        }

        /**
         * and policy_number >= value
         *
         * @return
         */
        public Criteria andPolicyNumberGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("policyNumber", value);
            return this;
        }

        /**
         * and policy_number < value
         *
         * @return
         */
        public Criteria andPolicyNumberLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("policyNumber", value);
            return this;
        }

        /**
         * and policy_number <= value
         *
         * @return
         */
        public Criteria andPolicyNumberLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("policyNumber", value);
            return this;
        }

        /**
         * and policy_number in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyNumberIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("policyNumber", values);
            return this;
        }

        /**
         * and policy_number in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyNumberIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("policyNumber", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and policy_number not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyNumberNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("policyNumber", values);
            return this;
        }

        /**
         * and policy_number not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyNumberNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("policyNumber", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and policy_number between value1 and value2
         *
         * @return
         */
        public Criteria andPolicyNumberBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("policyNumber", value1, value2);
            return this;
        }

        /**
         * and policy_number not between value1 and value2
         *
         * @return
         */
        public Criteria andPolicyNumberNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("policyNumber", value1, value2);
            return this;
        }

        /**
         * and policy_number like value
         *
         * @return
         */
        public Criteria andPolicyNumberLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("policyNumber", value);
            return this;
        }

        /**
         * and policy_number not like value
         *
         * @return
         */
        public Criteria andPolicyNumberNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("policyNumber", value);
            return this;
        }

        /**
         * or policy_number = ''
         *
         * @return
         */
        public Criteria orPolicyNumberIsEmpty() {
            this.orEqualTo("policyNumber", "");
            return this;
        }

        /**
         * or policy_number != ''
         *
         * @return
         */
        public Criteria orPolicyNumberIsNotEmpty() {
            this.orNotEqualTo("policyNumber", "");
            return this;
        }

        /**
         * or policy_number = value
         *
         * @return
         */
        public Criteria orPolicyNumberEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("policyNumber", value);
            return this;
        }

        /**
         * or policy_number != value
         *
         * @return
         */
        public Criteria orPolicyNumberNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("policyNumber", value);
            return this;
        }

        /**
         * or policy_number > value
         *
         * @return
         */
        public Criteria orPolicyNumberGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("policyNumber", value);
            return this;
        }

        /**
         * or policy_number >= value
         *
         * @return
         */
        public Criteria orPolicyNumberGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("policyNumber", value);
            return this;
        }

        /**
         * or policy_number < value
         *
         * @return
         */
        public Criteria orPolicyNumberLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("policyNumber", value);
            return this;
        }

        /**
         * or policy_number <= value
         *
         * @return
         */
        public Criteria orPolicyNumberLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("policyNumber", value);
            return this;
        }

        /**
         * or policy_number in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyNumberIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("policyNumber", values);
            return this;
        }

        /**
         * or policy_number in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyNumberIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("policyNumber", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or policy_number not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyNumberNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("policyNumber", values);
            return this;
        }

        /**
         * or policy_number not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyNumberNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("policyNumber", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or policy_number between value1 and value2
         *
         * @return
         */
        public Criteria orPolicyNumberBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("policyNumber", value1, value2);
            return this;
        }

        /**
         * or policy_number not between value1 and value2
         *
         * @return
         */
        public Criteria orPolicyNumberNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("policyNumber", value1, value2);
            return this;
        }

        /**
         * or policy_number like value
         *
         * @return
         */
        public Criteria orPolicyNumberLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("policyNumber", value);
            return this;
        }

        /**
         * or policy_number not like value
         *
         * @return
         */
        public Criteria orPolicyNumberNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("policyNumber", value);
            return this;
        }

        /**
         * and policy_title = ''
         *
         * @return
         */
        public Criteria andPolicyTitleIsEmpty() {
            this.andEqualTo("policyTitle", "");
            return this;
        }

        /**
         * and policy_title != ''
         *
         * @return
         */
        public Criteria andPolicyTitleIsNotEmpty() {
            this.andNotEqualTo("policyTitle", "");
            return this;
        }

        /**
         * and policy_title = value
         *
         * @return
         */
        public Criteria andPolicyTitleEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("policyTitle", value);
            return this;
        }

        /**
         * and policy_title != value
         *
         * @return
         */
        public Criteria andPolicyTitleNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("policyTitle", value);
            return this;
        }

        /**
         * and policy_title > value
         *
         * @return
         */
        public Criteria andPolicyTitleGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("policyTitle", value);
            return this;
        }

        /**
         * and policy_title >= value
         *
         * @return
         */
        public Criteria andPolicyTitleGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("policyTitle", value);
            return this;
        }

        /**
         * and policy_title < value
         *
         * @return
         */
        public Criteria andPolicyTitleLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("policyTitle", value);
            return this;
        }

        /**
         * and policy_title <= value
         *
         * @return
         */
        public Criteria andPolicyTitleLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("policyTitle", value);
            return this;
        }

        /**
         * and policy_title in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyTitleIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("policyTitle", values);
            return this;
        }

        /**
         * and policy_title in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyTitleIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("policyTitle", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and policy_title not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyTitleNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("policyTitle", values);
            return this;
        }

        /**
         * and policy_title not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andPolicyTitleNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("policyTitle", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and policy_title between value1 and value2
         *
         * @return
         */
        public Criteria andPolicyTitleBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("policyTitle", value1, value2);
            return this;
        }

        /**
         * and policy_title not between value1 and value2
         *
         * @return
         */
        public Criteria andPolicyTitleNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("policyTitle", value1, value2);
            return this;
        }

        /**
         * and policy_title like value
         *
         * @return
         */
        public Criteria andPolicyTitleLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("policyTitle", value);
            return this;
        }

        /**
         * and policy_title not like value
         *
         * @return
         */
        public Criteria andPolicyTitleNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("policyTitle", value);
            return this;
        }

        /**
         * or policy_title = ''
         *
         * @return
         */
        public Criteria orPolicyTitleIsEmpty() {
            this.orEqualTo("policyTitle", "");
            return this;
        }

        /**
         * or policy_title != ''
         *
         * @return
         */
        public Criteria orPolicyTitleIsNotEmpty() {
            this.orNotEqualTo("policyTitle", "");
            return this;
        }

        /**
         * or policy_title = value
         *
         * @return
         */
        public Criteria orPolicyTitleEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("policyTitle", value);
            return this;
        }

        /**
         * or policy_title != value
         *
         * @return
         */
        public Criteria orPolicyTitleNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("policyTitle", value);
            return this;
        }

        /**
         * or policy_title > value
         *
         * @return
         */
        public Criteria orPolicyTitleGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("policyTitle", value);
            return this;
        }

        /**
         * or policy_title >= value
         *
         * @return
         */
        public Criteria orPolicyTitleGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("policyTitle", value);
            return this;
        }

        /**
         * or policy_title < value
         *
         * @return
         */
        public Criteria orPolicyTitleLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("policyTitle", value);
            return this;
        }

        /**
         * or policy_title <= value
         *
         * @return
         */
        public Criteria orPolicyTitleLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("policyTitle", value);
            return this;
        }

        /**
         * or policy_title in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyTitleIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("policyTitle", values);
            return this;
        }

        /**
         * or policy_title in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyTitleIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("policyTitle", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or policy_title not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyTitleNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("policyTitle", values);
            return this;
        }

        /**
         * or policy_title not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orPolicyTitleNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("policyTitle", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or policy_title between value1 and value2
         *
         * @return
         */
        public Criteria orPolicyTitleBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("policyTitle", value1, value2);
            return this;
        }

        /**
         * or policy_title not between value1 and value2
         *
         * @return
         */
        public Criteria orPolicyTitleNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("policyTitle", value1, value2);
            return this;
        }

        /**
         * or policy_title like value
         *
         * @return
         */
        public Criteria orPolicyTitleLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("policyTitle", value);
            return this;
        }

        /**
         * or policy_title not like value
         *
         * @return
         */
        public Criteria orPolicyTitleNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("policyTitle", value);
            return this;
        }

        /**
         * and theme_image = ''
         *
         * @return
         */
        public Criteria andThemeImageIsEmpty() {
            this.andEqualTo("themeImage", "");
            return this;
        }

        /**
         * and theme_image != ''
         *
         * @return
         */
        public Criteria andThemeImageIsNotEmpty() {
            this.andNotEqualTo("themeImage", "");
            return this;
        }

        /**
         * and theme_image = value
         *
         * @return
         */
        public Criteria andThemeImageEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("themeImage", value);
            return this;
        }

        /**
         * and theme_image != value
         *
         * @return
         */
        public Criteria andThemeImageNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("themeImage", value);
            return this;
        }

        /**
         * and theme_image > value
         *
         * @return
         */
        public Criteria andThemeImageGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("themeImage", value);
            return this;
        }

        /**
         * and theme_image >= value
         *
         * @return
         */
        public Criteria andThemeImageGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("themeImage", value);
            return this;
        }

        /**
         * and theme_image < value
         *
         * @return
         */
        public Criteria andThemeImageLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("themeImage", value);
            return this;
        }

        /**
         * and theme_image <= value
         *
         * @return
         */
        public Criteria andThemeImageLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("themeImage", value);
            return this;
        }

        /**
         * and theme_image in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andThemeImageIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("themeImage", values);
            return this;
        }

        /**
         * and theme_image in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andThemeImageIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("themeImage", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and theme_image not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andThemeImageNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("themeImage", values);
            return this;
        }

        /**
         * and theme_image not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andThemeImageNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("themeImage", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and theme_image between value1 and value2
         *
         * @return
         */
        public Criteria andThemeImageBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("themeImage", value1, value2);
            return this;
        }

        /**
         * and theme_image not between value1 and value2
         *
         * @return
         */
        public Criteria andThemeImageNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("themeImage", value1, value2);
            return this;
        }

        /**
         * and theme_image like value
         *
         * @return
         */
        public Criteria andThemeImageLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("themeImage", value);
            return this;
        }

        /**
         * and theme_image not like value
         *
         * @return
         */
        public Criteria andThemeImageNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("themeImage", value);
            return this;
        }

        /**
         * or theme_image = ''
         *
         * @return
         */
        public Criteria orThemeImageIsEmpty() {
            this.orEqualTo("themeImage", "");
            return this;
        }

        /**
         * or theme_image != ''
         *
         * @return
         */
        public Criteria orThemeImageIsNotEmpty() {
            this.orNotEqualTo("themeImage", "");
            return this;
        }

        /**
         * or theme_image = value
         *
         * @return
         */
        public Criteria orThemeImageEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("themeImage", value);
            return this;
        }

        /**
         * or theme_image != value
         *
         * @return
         */
        public Criteria orThemeImageNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("themeImage", value);
            return this;
        }

        /**
         * or theme_image > value
         *
         * @return
         */
        public Criteria orThemeImageGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("themeImage", value);
            return this;
        }

        /**
         * or theme_image >= value
         *
         * @return
         */
        public Criteria orThemeImageGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("themeImage", value);
            return this;
        }

        /**
         * or theme_image < value
         *
         * @return
         */
        public Criteria orThemeImageLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("themeImage", value);
            return this;
        }

        /**
         * or theme_image <= value
         *
         * @return
         */
        public Criteria orThemeImageLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("themeImage", value);
            return this;
        }

        /**
         * or theme_image in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orThemeImageIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("themeImage", values);
            return this;
        }

        /**
         * or theme_image in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orThemeImageIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("themeImage", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or theme_image not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orThemeImageNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("themeImage", values);
            return this;
        }

        /**
         * or theme_image not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orThemeImageNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("themeImage", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or theme_image between value1 and value2
         *
         * @return
         */
        public Criteria orThemeImageBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("themeImage", value1, value2);
            return this;
        }

        /**
         * or theme_image not between value1 and value2
         *
         * @return
         */
        public Criteria orThemeImageNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("themeImage", value1, value2);
            return this;
        }

        /**
         * or theme_image like value
         *
         * @return
         */
        public Criteria orThemeImageLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("themeImage", value);
            return this;
        }

        /**
         * or theme_image not like value
         *
         * @return
         */
        public Criteria orThemeImageNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("themeImage", value);
            return this;
        }

        /**
         * and create_data = value
         *
         * @return
         */
        public Criteria andCreateDataEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("createData", value);
            return this;
        }

        /**
         * and create_data != value
         *
         * @return
         */
        public Criteria andCreateDataNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("createData", value);
            return this;
        }

        /**
         * and create_data > value
         *
         * @return
         */
        public Criteria andCreateDataGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("createData", value);
            return this;
        }

        /**
         * and create_data >= value
         *
         * @return
         */
        public Criteria andCreateDataGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("createData", value);
            return this;
        }

        /**
         * and create_data < value
         *
         * @return
         */
        public Criteria andCreateDataLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("createData", value);
            return this;
        }

        /**
         * and create_data <= value
         *
         * @return
         */
        public Criteria andCreateDataLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("createData", value);
            return this;
        }

        /**
         * and create_data in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateDataIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("createData", values);
            return this;
        }

        /**
         * and create_data in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateDataIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("createData", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and create_data not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateDataNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("createData", values);
            return this;
        }

        /**
         * and create_data not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateDataNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("createData", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and create_data between value1 and value2
         *
         * @return
         */
        public Criteria andCreateDataBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("createData", value1, value2);
            return this;
        }

        /**
         * and create_data not between value1 and value2
         *
         * @return
         */
        public Criteria andCreateDataNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("createData", value1, value2);
            return this;
        }

        /**
         * and create_data like value
         *
         * @return
         */
        public Criteria andCreateDataLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("createData", value);
            return this;
        }

        /**
         * and create_data not like value
         *
         * @return
         */
        public Criteria andCreateDataNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("createData", value);
            return this;
        }

        /**
         * or create_data = value
         *
         * @return
         */
        public Criteria orCreateDataEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("createData", value);
            return this;
        }

        /**
         * or create_data != value
         *
         * @return
         */
        public Criteria orCreateDataNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("createData", value);
            return this;
        }

        /**
         * or create_data > value
         *
         * @return
         */
        public Criteria orCreateDataGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("createData", value);
            return this;
        }

        /**
         * or create_data >= value
         *
         * @return
         */
        public Criteria orCreateDataGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("createData", value);
            return this;
        }

        /**
         * or create_data < value
         *
         * @return
         */
        public Criteria orCreateDataLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("createData", value);
            return this;
        }

        /**
         * or create_data <= value
         *
         * @return
         */
        public Criteria orCreateDataLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("createData", value);
            return this;
        }

        /**
         * or create_data in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateDataIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("createData", values);
            return this;
        }

        /**
         * or create_data in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateDataIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("createData", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or create_data not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateDataNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("createData", values);
            return this;
        }

        /**
         * or create_data not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateDataNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("createData", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or create_data between value1 and value2
         *
         * @return
         */
        public Criteria orCreateDataBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("createData", value1, value2);
            return this;
        }

        /**
         * or create_data not between value1 and value2
         *
         * @return
         */
        public Criteria orCreateDataNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("createData", value1, value2);
            return this;
        }

        /**
         * or create_data like value
         *
         * @return
         */
        public Criteria orCreateDataLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("createData", value);
            return this;
        }

        /**
         * or create_data not like value
         *
         * @return
         */
        public Criteria orCreateDataNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("createData", value);
            return this;
        }


    }

}
