package com.juyss.mapper;

import com.juyss.bean.SchemaPrivileges;
import com.juyss.bean.SchemaPrivilegesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchemaPrivilegesMapper {
    int countByExample(SchemaPrivilegesExample example);

    int deleteByExample(SchemaPrivilegesExample example);

    int insert(SchemaPrivileges record);

    int insertSelective(SchemaPrivileges record);

    List<SchemaPrivileges> selectByExample(SchemaPrivilegesExample example);

    int updateByExampleSelective(@Param("record") SchemaPrivileges record, @Param("example") SchemaPrivilegesExample example);

    int updateByExample(@Param("record") SchemaPrivileges record, @Param("example") SchemaPrivilegesExample example);
}