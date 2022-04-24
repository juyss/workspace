package com.juyss.mapper;

import com.juyss.bean.KeyColumnUsage;
import com.juyss.bean.KeyColumnUsageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeyColumnUsageMapper {
    int countByExample(KeyColumnUsageExample example);

    int deleteByExample(KeyColumnUsageExample example);

    int insert(KeyColumnUsage record);

    int insertSelective(KeyColumnUsage record);

    List<KeyColumnUsage> selectByExample(KeyColumnUsageExample example);

    int updateByExampleSelective(@Param("record") KeyColumnUsage record, @Param("example") KeyColumnUsageExample example);

    int updateByExample(@Param("record") KeyColumnUsage record, @Param("example") KeyColumnUsageExample example);
}