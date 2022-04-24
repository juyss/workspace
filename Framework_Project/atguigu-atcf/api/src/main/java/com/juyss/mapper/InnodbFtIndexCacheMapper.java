package com.juyss.mapper;

import com.juyss.bean.InnodbFtIndexCache;
import com.juyss.bean.InnodbFtIndexCacheExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbFtIndexCacheMapper {
    int countByExample(InnodbFtIndexCacheExample example);

    int deleteByExample(InnodbFtIndexCacheExample example);

    int insert(InnodbFtIndexCache record);

    int insertSelective(InnodbFtIndexCache record);

    List<InnodbFtIndexCache> selectByExample(InnodbFtIndexCacheExample example);

    int updateByExampleSelective(@Param("record") InnodbFtIndexCache record, @Param("example") InnodbFtIndexCacheExample example);

    int updateByExample(@Param("record") InnodbFtIndexCache record, @Param("example") InnodbFtIndexCacheExample example);
}