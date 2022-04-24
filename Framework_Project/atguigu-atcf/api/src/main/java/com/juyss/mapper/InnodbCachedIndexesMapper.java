package com.juyss.mapper;

import com.juyss.bean.InnodbCachedIndexes;
import com.juyss.bean.InnodbCachedIndexesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbCachedIndexesMapper {
    int countByExample(InnodbCachedIndexesExample example);

    int deleteByExample(InnodbCachedIndexesExample example);

    int insert(InnodbCachedIndexes record);

    int insertSelective(InnodbCachedIndexes record);

    List<InnodbCachedIndexes> selectByExample(InnodbCachedIndexesExample example);

    int updateByExampleSelective(@Param("record") InnodbCachedIndexes record, @Param("example") InnodbCachedIndexesExample example);

    int updateByExample(@Param("record") InnodbCachedIndexes record, @Param("example") InnodbCachedIndexesExample example);
}