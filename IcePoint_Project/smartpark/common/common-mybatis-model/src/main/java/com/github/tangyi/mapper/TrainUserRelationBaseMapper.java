package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.TrainUserRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * train_user_relation 培训报名用户表
 *
 * @author xh
 * @since 2020/10/20
 */
@Mapper
public interface TrainUserRelationBaseMapper extends CommonDaoMapper<TrainUserRelation> {
}
