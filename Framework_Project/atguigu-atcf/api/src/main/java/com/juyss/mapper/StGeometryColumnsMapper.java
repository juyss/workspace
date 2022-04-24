package com.juyss.mapper;

import com.juyss.bean.StGeometryColumns;
import com.juyss.bean.StGeometryColumnsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StGeometryColumnsMapper {
    int countByExample(StGeometryColumnsExample example);

    int deleteByExample(StGeometryColumnsExample example);

    int insert(StGeometryColumns record);

    int insertSelective(StGeometryColumns record);

    List<StGeometryColumns> selectByExampleWithBLOBs(StGeometryColumnsExample example);

    List<StGeometryColumns> selectByExample(StGeometryColumnsExample example);

    int updateByExampleSelective(@Param("record") StGeometryColumns record, @Param("example") StGeometryColumnsExample example);

    int updateByExampleWithBLOBs(@Param("record") StGeometryColumns record, @Param("example") StGeometryColumnsExample example);

    int updateByExample(@Param("record") StGeometryColumns record, @Param("example") StGeometryColumnsExample example);
}