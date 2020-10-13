package com.juyss.mapper;

import com.juyss.bean.InnodbTables;
import com.juyss.bean.InnodbTablesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbTablesMapper {
    int countByExample(InnodbTablesExample example);

    int deleteByExample(InnodbTablesExample example);

    int insert(InnodbTables record);

    int insertSelective(InnodbTables record);

    List<InnodbTables> selectByExample(InnodbTablesExample example);

    int updateByExampleSelective(@Param("record") InnodbTables record, @Param("example") InnodbTablesExample example);

    int updateByExample(@Param("record") InnodbTables record, @Param("example") InnodbTablesExample example);
}