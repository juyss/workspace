package com.juyss.mapper;

import com.juyss.bean.StUnitsOfMeasure;
import com.juyss.bean.StUnitsOfMeasureExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StUnitsOfMeasureMapper {
    int countByExample(StUnitsOfMeasureExample example);

    int deleteByExample(StUnitsOfMeasureExample example);

    int insert(StUnitsOfMeasure record);

    int insertSelective(StUnitsOfMeasure record);

    List<StUnitsOfMeasure> selectByExample(StUnitsOfMeasureExample example);

    int updateByExampleSelective(@Param("record") StUnitsOfMeasure record, @Param("example") StUnitsOfMeasureExample example);

    int updateByExample(@Param("record") StUnitsOfMeasure record, @Param("example") StUnitsOfMeasureExample example);
}