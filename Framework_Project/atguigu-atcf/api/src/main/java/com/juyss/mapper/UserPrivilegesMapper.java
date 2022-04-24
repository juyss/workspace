package com.juyss.mapper;

import com.juyss.bean.UserPrivileges;
import com.juyss.bean.UserPrivilegesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPrivilegesMapper {
    int countByExample(UserPrivilegesExample example);

    int deleteByExample(UserPrivilegesExample example);

    int insert(UserPrivileges record);

    int insertSelective(UserPrivileges record);

    List<UserPrivileges> selectByExample(UserPrivilegesExample example);

    int updateByExampleSelective(@Param("record") UserPrivileges record, @Param("example") UserPrivilegesExample example);

    int updateByExample(@Param("record") UserPrivileges record, @Param("example") UserPrivilegesExample example);
}