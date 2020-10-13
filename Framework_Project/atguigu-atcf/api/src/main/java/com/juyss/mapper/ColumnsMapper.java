package com.juyss.mapper;

import com.juyss.bean.Columns;
import com.juyss.bean.ColumnsExample;
import com.juyss.bean.ColumnsWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColumnsMapper {
    int countByExample(ColumnsExample example);

    int deleteByExample(ColumnsExample example);

    int insert(ColumnsWithBLOBs record);

    int insertSelective(ColumnsWithBLOBs record);

    List<ColumnsWithBLOBs> selectByExampleWithBLOBs(ColumnsExample example);

    List<Columns> selectByExample(ColumnsExample example);

    int updateByExampleSelective(@Param("record") ColumnsWithBLOBs record, @Param("example") ColumnsExample example);

    int updateByExampleWithBLOBs(@Param("record") ColumnsWithBLOBs record, @Param("example") ColumnsExample example);

    int updateByExample(@Param("record") Columns record, @Param("example") ColumnsExample example);
}