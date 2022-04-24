package com.juyss.mapper;

import com.juyss.bean.ColumnPrivileges;
import com.juyss.bean.ColumnPrivilegesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColumnPrivilegesMapper {
    int countByExample(ColumnPrivilegesExample example);

    int deleteByExample(ColumnPrivilegesExample example);

    int insert(ColumnPrivileges record);

    int insertSelective(ColumnPrivileges record);

    List<ColumnPrivileges> selectByExample(ColumnPrivilegesExample example);

    int updateByExampleSelective(@Param("record") ColumnPrivileges record, @Param("example") ColumnPrivilegesExample example);

    int updateByExample(@Param("record") ColumnPrivileges record, @Param("example") ColumnPrivilegesExample example);
}