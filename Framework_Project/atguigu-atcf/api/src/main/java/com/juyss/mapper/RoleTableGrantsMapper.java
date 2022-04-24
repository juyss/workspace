package com.juyss.mapper;

import com.juyss.bean.RoleTableGrants;
import com.juyss.bean.RoleTableGrantsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleTableGrantsMapper {
    int countByExample(RoleTableGrantsExample example);

    int deleteByExample(RoleTableGrantsExample example);

    int insert(RoleTableGrants record);

    int insertSelective(RoleTableGrants record);

    List<RoleTableGrants> selectByExample(RoleTableGrantsExample example);

    int updateByExampleSelective(@Param("record") RoleTableGrants record, @Param("example") RoleTableGrantsExample example);

    int updateByExample(@Param("record") RoleTableGrants record, @Param("example") RoleTableGrantsExample example);
}