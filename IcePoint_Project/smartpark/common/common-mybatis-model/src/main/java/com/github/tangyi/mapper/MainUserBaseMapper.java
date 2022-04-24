package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.MainUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * main_user 
 *
 * @author xh
 * @since 2020/11/05
 */
@Mapper
public interface MainUserBaseMapper extends CommonDaoMapper<MainUser> {
}
