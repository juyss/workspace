package com.icepoint.framework.web.system.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.system.entity.Attribute;
import com.icepoint.framework.web.system.entity.Label;
import com.icepoint.framework.web.system.entity.MultiAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性管理数据库访问层
 *
 * @since 2021-07-07 09:54:09
 */
@Mapper
public interface AttributeMapper extends RepositoryMapper<Attribute,Long> {
    /**
     * 对象属性表批量添加
     *
     * @param attrs 对象属性数组
     * @return 插入个数
     */
    Integer addAttrs(@Param("attrs") List<Attribute> attrs);

    /**
     * 对象多属性表批量添加
     *
     * @param multiAttrs 对象多属性数组
     * @return 插入个数
     */
    Integer addMultiAttrs(@Param("multiAttrs") List<MultiAttribute> multiAttrs);

    /**
     * 对象标签表批量添加
     *
     * @param labels 对象标签数组
     * @return 插入个数
     */
    Integer addObjLabels(@Param("labels") List<Label> labels);
}
