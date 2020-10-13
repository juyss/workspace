package com.juyss.mapper;

import com.juyss.bean.InnodbCmp;
import com.juyss.bean.InnodbCmpExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbCmpMapper {
    int countByExample(InnodbCmpExample example);

    int deleteByExample(InnodbCmpExample example);

    int insert(InnodbCmp record);

    int insertSelective(InnodbCmp record);

    List<InnodbCmp> selectByExample(InnodbCmpExample example);

    int updateByExampleSelective(@Param("record") InnodbCmp record, @Param("example") InnodbCmpExample example);

    int updateByExample(@Param("record") InnodbCmp record, @Param("example") InnodbCmpExample example);
}