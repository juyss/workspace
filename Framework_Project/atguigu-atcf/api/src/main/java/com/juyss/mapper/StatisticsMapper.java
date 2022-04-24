package com.juyss.mapper;

import com.juyss.bean.Statistics;
import com.juyss.bean.StatisticsExample;
import com.juyss.bean.StatisticsWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatisticsMapper {
    int countByExample(StatisticsExample example);

    int deleteByExample(StatisticsExample example);

    int insert(StatisticsWithBLOBs record);

    int insertSelective(StatisticsWithBLOBs record);

    List<StatisticsWithBLOBs> selectByExampleWithBLOBs(StatisticsExample example);

    List<Statistics> selectByExample(StatisticsExample example);

    int updateByExampleSelective(@Param("record") StatisticsWithBLOBs record, @Param("example") StatisticsExample example);

    int updateByExampleWithBLOBs(@Param("record") StatisticsWithBLOBs record, @Param("example") StatisticsExample example);

    int updateByExample(@Param("record") Statistics record, @Param("example") StatisticsExample example);
}