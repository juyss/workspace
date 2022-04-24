package com.juyss.mapper;

import com.juyss.bean.OptimizerTrace;
import com.juyss.bean.OptimizerTraceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptimizerTraceMapper {
    int countByExample(OptimizerTraceExample example);

    int deleteByExample(OptimizerTraceExample example);

    int insert(OptimizerTrace record);

    int insertSelective(OptimizerTrace record);

    List<OptimizerTrace> selectByExample(OptimizerTraceExample example);

    int updateByExampleSelective(@Param("record") OptimizerTrace record, @Param("example") OptimizerTraceExample example);

    int updateByExample(@Param("record") OptimizerTrace record, @Param("example") OptimizerTraceExample example);
}