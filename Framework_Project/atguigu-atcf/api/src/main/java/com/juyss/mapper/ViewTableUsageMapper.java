package com.juyss.mapper;

import com.juyss.bean.ViewTableUsage;
import com.juyss.bean.ViewTableUsageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewTableUsageMapper {
    int countByExample(ViewTableUsageExample example);

    int deleteByExample(ViewTableUsageExample example);

    int insert(ViewTableUsage record);

    int insertSelective(ViewTableUsage record);

    List<ViewTableUsage> selectByExample(ViewTableUsageExample example);

    int updateByExampleSelective(@Param("record") ViewTableUsage record, @Param("example") ViewTableUsageExample example);

    int updateByExample(@Param("record") ViewTableUsage record, @Param("example") ViewTableUsageExample example);
}