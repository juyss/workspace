package com.juyss.mapper;

import com.juyss.bean.RoleRoutineGrants;
import com.juyss.bean.RoleRoutineGrantsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleRoutineGrantsMapper {
    int countByExample(RoleRoutineGrantsExample example);

    int deleteByExample(RoleRoutineGrantsExample example);

    int insert(RoleRoutineGrants record);

    int insertSelective(RoleRoutineGrants record);

    List<RoleRoutineGrants> selectByExample(RoleRoutineGrantsExample example);

    int updateByExampleSelective(@Param("record") RoleRoutineGrants record, @Param("example") RoleRoutineGrantsExample example);

    int updateByExample(@Param("record") RoleRoutineGrants record, @Param("example") RoleRoutineGrantsExample example);
}