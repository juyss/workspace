package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.MainRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * main_role 主系统角色
 *
 * @author xh
 * @since 2020/11/05
 */
@Mapper
public interface MainRoleBaseMapper extends CommonDaoMapper<MainRole> {
}
