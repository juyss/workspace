package com.juyss.mapper;

import com.juyss.bean.RoleColumnGrants;
import com.juyss.bean.RoleColumnGrantsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleColumnGrantsMapper {
    int countByExample(RoleColumnGrantsExample example);

    int deleteByExample(RoleColumnGrantsExample example);

    int insert(RoleColumnGrants record);

    int insertSelective(RoleColumnGrants record);

    List<RoleColumnGrants> selectByExample(RoleColumnGrantsExample example);

    int updateByExampleSelective(@Param("record") RoleColumnGrants record, @Param("example") RoleColumnGrantsExample example);

    int updateByExample(@Param("record") RoleColumnGrants record, @Param("example") RoleColumnGrantsExample example);
}