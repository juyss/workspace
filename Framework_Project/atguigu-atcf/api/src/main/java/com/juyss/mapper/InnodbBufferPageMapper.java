package com.juyss.mapper;

import com.juyss.bean.InnodbBufferPage;
import com.juyss.bean.InnodbBufferPageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbBufferPageMapper {
    int countByExample(InnodbBufferPageExample example);

    int deleteByExample(InnodbBufferPageExample example);

    int insert(InnodbBufferPage record);

    int insertSelective(InnodbBufferPage record);

    List<InnodbBufferPage> selectByExample(InnodbBufferPageExample example);

    int updateByExampleSelective(@Param("record") InnodbBufferPage record, @Param("example") InnodbBufferPageExample example);

    int updateByExample(@Param("record") InnodbBufferPage record, @Param("example") InnodbBufferPageExample example);
}