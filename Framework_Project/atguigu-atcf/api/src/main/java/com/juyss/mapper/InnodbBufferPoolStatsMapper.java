package com.juyss.mapper;

import com.juyss.bean.InnodbBufferPoolStats;
import com.juyss.bean.InnodbBufferPoolStatsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbBufferPoolStatsMapper {
    int countByExample(InnodbBufferPoolStatsExample example);

    int deleteByExample(InnodbBufferPoolStatsExample example);

    int insert(InnodbBufferPoolStats record);

    int insertSelective(InnodbBufferPoolStats record);

    List<InnodbBufferPoolStats> selectByExample(InnodbBufferPoolStatsExample example);

    int updateByExampleSelective(@Param("record") InnodbBufferPoolStats record, @Param("example") InnodbBufferPoolStatsExample example);

    int updateByExample(@Param("record") InnodbBufferPoolStats record, @Param("example") InnodbBufferPoolStatsExample example);
}