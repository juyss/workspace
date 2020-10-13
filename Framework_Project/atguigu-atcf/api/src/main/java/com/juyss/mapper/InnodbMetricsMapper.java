package com.juyss.mapper;

import com.juyss.bean.InnodbMetrics;
import com.juyss.bean.InnodbMetricsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbMetricsMapper {
    int countByExample(InnodbMetricsExample example);

    int deleteByExample(InnodbMetricsExample example);

    int insert(InnodbMetrics record);

    int insertSelective(InnodbMetrics record);

    List<InnodbMetrics> selectByExample(InnodbMetricsExample example);

    int updateByExampleSelective(@Param("record") InnodbMetrics record, @Param("example") InnodbMetricsExample example);

    int updateByExample(@Param("record") InnodbMetrics record, @Param("example") InnodbMetricsExample example);
}