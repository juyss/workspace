package com.juyss.mapper;

import com.juyss.bean.EnabledRoles;
import com.juyss.bean.EnabledRolesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnabledRolesMapper {
    int countByExample(EnabledRolesExample example);

    int deleteByExample(EnabledRolesExample example);

    int insert(EnabledRoles record);

    int insertSelective(EnabledRoles record);

    List<EnabledRoles> selectByExample(EnabledRolesExample example);

    int updateByExampleSelective(@Param("record") EnabledRoles record, @Param("example") EnabledRolesExample example);

    int updateByExample(@Param("record") EnabledRoles record, @Param("example") EnabledRolesExample example);
}