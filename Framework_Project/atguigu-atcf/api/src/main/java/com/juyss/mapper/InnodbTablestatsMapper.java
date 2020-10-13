package com.juyss.mapper;

import com.juyss.bean.InnodbTablestats;
import com.juyss.bean.InnodbTablestatsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbTablestatsMapper {
    int countByExample(InnodbTablestatsExample example);

    int deleteByExample(InnodbTablestatsExample example);

    int insert(InnodbTablestats record);

    int insertSelective(InnodbTablestats record);

    List<InnodbTablestats> selectByExample(InnodbTablestatsExample example);

    int updateByExampleSelective(@Param("record") InnodbTablestats record, @Param("example") InnodbTablestatsExample example);

    int updateByExample(@Param("record") InnodbTablestats record, @Param("example") InnodbTablestatsExample example);
}