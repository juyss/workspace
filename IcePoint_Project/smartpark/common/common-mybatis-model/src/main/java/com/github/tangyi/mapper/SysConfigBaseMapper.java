package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.SysConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * sys_config 系统配置
 *
 * @author xh
 * @since 2020/10/30
 */
@Mapper
public interface SysConfigBaseMapper extends CommonDaoMapper<SysConfig> {
}
