package com.icepoint.framework.web.security.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 */
@Mapper
public interface UserMapper extends RepositoryMapper<User,Long> {


}
