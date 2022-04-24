package com.juyss.mapper;

import com.juyss.bean.InnodbIndexes;
import com.juyss.bean.InnodbIndexesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbIndexesMapper {
    int countByExample(InnodbIndexesExample example);

    int deleteByExample(InnodbIndexesExample example);

    int insert(InnodbIndexes record);

    int insertSelective(InnodbIndexes record);

    List<InnodbIndexes> selectByExample(InnodbIndexesExample example);

    int updateByExampleSelective(@Param("record") InnodbIndexes record, @Param("example") InnodbIndexesExample example);

    int updateByExample(@Param("record") InnodbIndexes record, @Param("example") InnodbIndexesExample example);
}