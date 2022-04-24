package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.TPushInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * t_push_information 信息推送列表
 *
 * @author jy
 * @since 2020/11/03
 */
@Mapper
public interface TPushInformationBaseMapper extends CommonDaoMapper<TPushInformation> {
}
