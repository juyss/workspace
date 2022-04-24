package com.juyss.mapper;

import com.juyss.bean.Schemata;
import com.juyss.bean.SchemataExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchemataMapper {
    int countByExample(SchemataExample example);

    int deleteByExample(SchemataExample example);

    int insert(Schemata record);

    int insertSelective(Schemata record);

    List<Schemata> selectByExampleWithBLOBs(SchemataExample example);

    List<Schemata> selectByExample(SchemataExample example);

    int updateByExampleSelective(@Param("record") Schemata record, @Param("example") SchemataExample example);

    int updateByExampleWithBLOBs(@Param("record") Schemata record, @Param("example") SchemataExample example);

    int updateByExample(@Param("record") Schemata record, @Param("example") SchemataExample example);
}