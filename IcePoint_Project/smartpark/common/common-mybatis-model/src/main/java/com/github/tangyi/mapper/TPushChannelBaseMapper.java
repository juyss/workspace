package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.TPushChannel;
import org.apache.ibatis.annotations.Mapper;

/**
 * t_push_channel 推送渠道表
 *
 * @author jy
 * @since 2020/11/03
 */
@Mapper
public interface TPushChannelBaseMapper extends CommonDaoMapper<TPushChannel> {
}
