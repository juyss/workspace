package com.juyss.mapper;

import com.juyss.bean.InnodbColumns;
import com.juyss.bean.InnodbColumnsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbColumnsMapper {
    int countByExample(InnodbColumnsExample example);

    int deleteByExample(InnodbColumnsExample example);

    int insert(InnodbColumns record);

    int insertSelective(InnodbColumns record);

    List<InnodbColumns> selectByExampleWithBLOBs(InnodbColumnsExample example);

    List<InnodbColumns> selectByExample(InnodbColumnsExample example);

    int updateByExampleSelective(@Param("record") InnodbColumns record, @Param("example") InnodbColumnsExample example);

    int updateByExampleWithBLOBs(@Param("record") InnodbColumns record, @Param("example") InnodbColumnsExample example);

    int updateByExample(@Param("record") InnodbColumns record, @Param("example") InnodbColumnsExample example);
}