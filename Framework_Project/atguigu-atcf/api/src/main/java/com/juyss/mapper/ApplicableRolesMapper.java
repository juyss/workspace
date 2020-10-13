package com.juyss.mapper;

import com.juyss.bean.ApplicableRoles;
import com.juyss.bean.ApplicableRolesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicableRolesMapper {
    int countByExample(ApplicableRolesExample example);

    int deleteByExample(ApplicableRolesExample example);

    int insert(ApplicableRoles record);

    int insertSelective(ApplicableRoles record);

    List<ApplicableRoles> selectByExample(ApplicableRolesExample example);

    int updateByExampleSelective(@Param("record") ApplicableRoles record, @Param("example") ApplicableRolesExample example);

    int updateByExample(@Param("record") ApplicableRoles record, @Param("example") ApplicableRolesExample example);
}