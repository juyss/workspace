package com.juyss.mapper;

import com.juyss.bean.InnodbForeignCols;
import com.juyss.bean.InnodbForeignColsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbForeignColsMapper {
    int countByExample(InnodbForeignColsExample example);

    int deleteByExample(InnodbForeignColsExample example);

    int insert(InnodbForeignCols record);

    int insertSelective(InnodbForeignCols record);

    List<InnodbForeignCols> selectByExample(InnodbForeignColsExample example);

    int updateByExampleSelective(@Param("record") InnodbForeignCols record, @Param("example") InnodbForeignColsExample example);

    int updateByExample(@Param("record") InnodbForeignCols record, @Param("example") InnodbForeignColsExample example);
}