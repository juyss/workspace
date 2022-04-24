package com.juyss.mapper;

import com.juyss.bean.InnodbBufferPageLru;
import com.juyss.bean.InnodbBufferPageLruExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbBufferPageLruMapper {
    int countByExample(InnodbBufferPageLruExample example);

    int deleteByExample(InnodbBufferPageLruExample example);

    int insert(InnodbBufferPageLru record);

    int insertSelective(InnodbBufferPageLru record);

    List<InnodbBufferPageLru> selectByExample(InnodbBufferPageLruExample example);

    int updateByExampleSelective(@Param("record") InnodbBufferPageLru record, @Param("example") InnodbBufferPageLruExample example);

    int updateByExample(@Param("record") InnodbBufferPageLru record, @Param("example") InnodbBufferPageLruExample example);
}