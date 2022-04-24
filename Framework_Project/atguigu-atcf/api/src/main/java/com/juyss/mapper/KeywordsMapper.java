package com.juyss.mapper;

import com.juyss.bean.Keywords;
import com.juyss.bean.KeywordsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeywordsMapper {
    int countByExample(KeywordsExample example);

    int deleteByExample(KeywordsExample example);

    int insert(Keywords record);

    int insertSelective(Keywords record);

    List<Keywords> selectByExample(KeywordsExample example);

    int updateByExampleSelective(@Param("record") Keywords record, @Param("example") KeywordsExample example);

    int updateByExample(@Param("record") Keywords record, @Param("example") KeywordsExample example);
}