package com.juyss.mapper;

import com.juyss.bean.Collations;
import com.juyss.bean.CollationsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollationsMapper {
    int countByExample(CollationsExample example);

    int deleteByExample(CollationsExample example);

    int insert(Collations record);

    int insertSelective(Collations record);

    List<Collations> selectByExample(CollationsExample example);

    int updateByExampleSelective(@Param("record") Collations record, @Param("example") CollationsExample example);

    int updateByExample(@Param("record") Collations record, @Param("example") CollationsExample example);
}