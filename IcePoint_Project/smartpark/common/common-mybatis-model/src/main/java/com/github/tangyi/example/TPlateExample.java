package com.github.tangyi.example;

import com.github.tangyi.core.common.util.CheckUtils;
import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.mybatis.example.AbstractExample;
import com.github.tangyi.model.TPlate;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;
import java.util.Date;

/**
 * t_plate 板块
 *
 * @author jy
 * @since 2020/11/03
 */
public class TPlateExample extends AbstractExample<TPlate> {

    /**
     * 排序
     */
    protected OrderBy ORDER_BY;

    public TPlateExample() {
        super(TPlate.class);
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
         * order by name
         *
         * @return
         */
        public OrderBy name() {
            this.orderBy("name");
            return this;
        }
        
        /**
         * order by code
         *
         * @return
         */
        public OrderBy code() {
            this.orderBy("code");
            return this;
        }
        
        /**
         * order by create_time
         *
         * @return
         */
        public OrderBy createTime() {
            this.orderBy("createTime");
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
         * and name = ''
         *
         * @return
         */
        public Criteria andNameIsEmpty() {
            this.andEqualTo("name", "");
            return this;
        }

        /**
         * and name != ''
         *
         * @return
         */
        public Criteria andNameIsNotEmpty() {
            this.andNotEqualTo("name", "");
            return this;
        }

        /**
         * and name = value
         *
         * @return
         */
        public Criteria andNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("name", value);
            return this;
        }

        /**
         * and name != value
         *
         * @return
         */
        public Criteria andNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("name", value);
            return this;
        }

        /**
         * and name > value
         *
         * @return
         */
        public Criteria andNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("name", value);
            return this;
        }

        /**
         * and name >= value
         *
         * @return
         */
        public Criteria andNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("name", value);
            return this;
        }

        /**
         * and name < value
         *
         * @return
         */
        public Criteria andNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("name", value);
            return this;
        }

        /**
         * and name <= value
         *
         * @return
         */
        public Criteria andNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("name", value);
            return this;
        }

        /**
         * and name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("name", values);
            return this;
        }

        /**
         * and name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andNameIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("name", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("name", values);
            return this;
        }

        /**
         * and name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("name", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and name between value1 and value2
         *
         * @return
         */
        public Criteria andNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("name", value1, value2);
            return this;
        }

        /**
         * and name not between value1 and value2
         *
         * @return
         */
        public Criteria andNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("name", value1, value2);
            return this;
        }

        /**
         * and name like value
         *
         * @return
         */
        public Criteria andNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("name", value);
            return this;
        }

        /**
         * and name not like value
         *
         * @return
         */
        public Criteria andNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("name", value);
            return this;
        }

        /**
         * or name = ''
         *
         * @return
         */
        public Criteria orNameIsEmpty() {
            this.orEqualTo("name", "");
            return this;
        }

        /**
         * or name != ''
         *
         * @return
         */
        public Criteria orNameIsNotEmpty() {
            this.orNotEqualTo("name", "");
            return this;
        }

        /**
         * or name = value
         *
         * @return
         */
        public Criteria orNameEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("name", value);
            return this;
        }

        /**
         * or name != value
         *
         * @return
         */
        public Criteria orNameNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("name", value);
            return this;
        }

        /**
         * or name > value
         *
         * @return
         */
        public Criteria orNameGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("name", value);
            return this;
        }

        /**
         * or name >= value
         *
         * @return
         */
        public Criteria orNameGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("name", value);
            return this;
        }

        /**
         * or name < value
         *
         * @return
         */
        public Criteria orNameLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("name", value);
            return this;
        }

        /**
         * or name <= value
         *
         * @return
         */
        public Criteria orNameLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("name", value);
            return this;
        }

        /**
         * or name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orNameIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("name", values);
            return this;
        }

        /**
         * or name in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orNameIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("name", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orNameNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("name", values);
            return this;
        }

        /**
         * or name not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orNameNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("name", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or name between value1 and value2
         *
         * @return
         */
        public Criteria orNameBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("name", value1, value2);
            return this;
        }

        /**
         * or name not between value1 and value2
         *
         * @return
         */
        public Criteria orNameNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("name", value1, value2);
            return this;
        }

        /**
         * or name like value
         *
         * @return
         */
        public Criteria orNameLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("name", value);
            return this;
        }

        /**
         * or name not like value
         *
         * @return
         */
        public Criteria orNameNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("name", value);
            return this;
        }

        /**
         * and code = ''
         *
         * @return
         */
        public Criteria andCodeIsEmpty() {
            this.andEqualTo("code", "");
            return this;
        }

        /**
         * and code != ''
         *
         * @return
         */
        public Criteria andCodeIsNotEmpty() {
            this.andNotEqualTo("code", "");
            return this;
        }

        /**
         * and code = value
         *
         * @return
         */
        public Criteria andCodeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("code", value);
            return this;
        }

        /**
         * and code != value
         *
         * @return
         */
        public Criteria andCodeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("code", value);
            return this;
        }

        /**
         * and code > value
         *
         * @return
         */
        public Criteria andCodeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("code", value);
            return this;
        }

        /**
         * and code >= value
         *
         * @return
         */
        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("code", value);
            return this;
        }

        /**
         * and code < value
         *
         * @return
         */
        public Criteria andCodeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("code", value);
            return this;
        }

        /**
         * and code <= value
         *
         * @return
         */
        public Criteria andCodeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("code", value);
            return this;
        }

        /**
         * and code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCodeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("code", values);
            return this;
        }

        /**
         * and code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCodeIn(String... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("code", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCodeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("code", values);
            return this;
        }

        /**
         * and code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCodeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("code", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and code between value1 and value2
         *
         * @return
         */
        public Criteria andCodeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("code", value1, value2);
            return this;
        }

        /**
         * and code not between value1 and value2
         *
         * @return
         */
        public Criteria andCodeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("code", value1, value2);
            return this;
        }

        /**
         * and code like value
         *
         * @return
         */
        public Criteria andCodeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("code", value);
            return this;
        }

        /**
         * and code not like value
         *
         * @return
         */
        public Criteria andCodeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("code", value);
            return this;
        }

        /**
         * or code = ''
         *
         * @return
         */
        public Criteria orCodeIsEmpty() {
            this.orEqualTo("code", "");
            return this;
        }

        /**
         * or code != ''
         *
         * @return
         */
        public Criteria orCodeIsNotEmpty() {
            this.orNotEqualTo("code", "");
            return this;
        }

        /**
         * or code = value
         *
         * @return
         */
        public Criteria orCodeEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("code", value);
            return this;
        }

        /**
         * or code != value
         *
         * @return
         */
        public Criteria orCodeNotEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("code", value);
            return this;
        }

        /**
         * or code > value
         *
         * @return
         */
        public Criteria orCodeGreaterThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("code", value);
            return this;
        }

        /**
         * or code >= value
         *
         * @return
         */
        public Criteria orCodeGreaterThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("code", value);
            return this;
        }

        /**
         * or code < value
         *
         * @return
         */
        public Criteria orCodeLessThan(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("code", value);
            return this;
        }

        /**
         * or code <= value
         *
         * @return
         */
        public Criteria orCodeLessThanOrEqualTo(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("code", value);
            return this;
        }

        /**
         * or code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCodeIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("code", values);
            return this;
        }

        /**
         * or code in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCodeIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("code", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCodeNotIn(Iterable<String> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("code", values);
            return this;
        }

        /**
         * or code not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCodeNotIn(String[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("code", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or code between value1 and value2
         *
         * @return
         */
        public Criteria orCodeBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("code", value1, value2);
            return this;
        }

        /**
         * or code not between value1 and value2
         *
         * @return
         */
        public Criteria orCodeNotBetween(String value1, String value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("code", value1, value2);
            return this;
        }

        /**
         * or code like value
         *
         * @return
         */
        public Criteria orCodeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("code", value);
            return this;
        }

        /**
         * or code not like value
         *
         * @return
         */
        public Criteria orCodeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("code", value);
            return this;
        }

        /**
         * and create_time = value
         *
         * @return
         */
        public Criteria andCreateTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andEqualTo("createTime", value);
            return this;
        }

        /**
         * and create_time != value
         *
         * @return
         */
        public Criteria andCreateTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.andNotEqualTo("createTime", value);
            return this;
        }

        /**
         * and create_time > value
         *
         * @return
         */
        public Criteria andCreateTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThan("createTime", value);
            return this;
        }

        /**
         * and create_time >= value
         *
         * @return
         */
        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andGreaterThanOrEqualTo("createTime", value);
            return this;
        }

        /**
         * and create_time < value
         *
         * @return
         */
        public Criteria andCreateTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThan("createTime", value);
            return this;
        }

        /**
         * and create_time <= value
         *
         * @return
         */
        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLessThanOrEqualTo("createTime", value);
            return this;
        }

        /**
         * and create_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("createTime", values);
            return this;
        }

        /**
         * and create_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateTimeIn(Date... values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andIn("createTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and create_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("createTime", values);
            return this;
        }

        /**
         * and create_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria andCreateTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            super.andNotIn("createTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * and create_time between value1 and value2
         *
         * @return
         */
        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andBetween("createTime", value1, value2);
            return this;
        }

        /**
         * and create_time not between value1 and value2
         *
         * @return
         */
        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            super.andNotBetween("createTime", value1, value2);
            return this;
        }

        /**
         * and create_time like value
         *
         * @return
         */
        public Criteria andCreateTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andLike("createTime", value);
            return this;
        }

        /**
         * and create_time not like value
         *
         * @return
         */
        public Criteria andCreateTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            super.andNotLike("createTime", value);
            return this;
        }

        /**
         * or create_time = value
         *
         * @return
         */
        public Criteria orCreateTimeEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orEqualTo("createTime", value);
            return this;
        }

        /**
         * or create_time != value
         *
         * @return
         */
        public Criteria orCreateTimeNotEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotEqualTo("createTime", value);
            return this;
        }

        /**
         * or create_time > value
         *
         * @return
         */
        public Criteria orCreateTimeGreaterThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThan("createTime", value);
            return this;
        }

        /**
         * or create_time >= value
         *
         * @return
         */
        public Criteria orCreateTimeGreaterThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orGreaterThanOrEqualTo("createTime", value);
            return this;
        }

        /**
         * or create_time < value
         *
         * @return
         */
        public Criteria orCreateTimeLessThan(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThan("createTime", value);
            return this;
        }

        /**
         * or create_time <= value
         *
         * @return
         */
        public Criteria orCreateTimeLessThanOrEqualTo(Date value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLessThanOrEqualTo("createTime", value);
            return this;
        }

        /**
         * or create_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateTimeIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("createTime", values);
            return this;
        }

        /**
         * or create_time in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateTimeIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orIn("createTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or create_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateTimeNotIn(Iterable<Date> values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("createTime", values);
            return this;
        }

        /**
         * or create_time not in (value1, value2, ...)
         *
         * @return
         */
        public Criteria orCreateTimeNotIn(Date[] values) {
            if (CheckUtils.isEmpty(values)) {
                return this;
            }
            this.orNotIn("createTime", CollectionUtils.initList(values));
            return this;
        }

        /**
         * or create_time between value1 and value2
         *
         * @return
         */
        public Criteria orCreateTimeBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orBetween("createTime", value1, value2);
            return this;
        }

        /**
         * or create_time not between value1 and value2
         *
         * @return
         */
        public Criteria orCreateTimeNotBetween(Date value1, Date value2) {
            if (CheckUtils.isAnyEmpty(value1, value2)) {
                return this;
            }
            this.orNotBetween("createTime", value1, value2);
            return this;
        }

        /**
         * or create_time like value
         *
         * @return
         */
        public Criteria orCreateTimeLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orLike("createTime", value);
            return this;
        }

        /**
         * or create_time not like value
         *
         * @return
         */
        public Criteria orCreateTimeNotLike(String value) {
            if (CheckUtils.isEmpty(value)) {
                return this;
            }
            this.orNotLike("createTime", value);
            return this;
        }


    }

}
