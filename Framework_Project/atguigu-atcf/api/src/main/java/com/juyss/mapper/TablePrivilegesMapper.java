package com.juyss.mapper;

import com.juyss.bean.TablePrivileges;
import com.juyss.bean.TablePrivilegesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TablePrivilegesMapper {
    int countByExample(TablePrivilegesExample example);

    int deleteByExample(TablePrivilegesExample example);

    int insert(TablePrivileges record);

    int insertSelective(TablePrivileges record);

    List<TablePrivileges> selectByExample(TablePrivilegesExample example);

    int updateByExampleSelective(@Param("record") TablePrivileges record, @Param("example") TablePrivilegesExample example);

    int updateByExample(@Param("record") TablePrivileges record, @Param("example") TablePrivilegesExample example);
}