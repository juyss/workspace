package com.juyss.mapper;

import com.juyss.bean.InnodbVirtual;
import com.juyss.bean.InnodbVirtualExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbVirtualMapper {
    int countByExample(InnodbVirtualExample example);

    int deleteByExample(InnodbVirtualExample example);

    int insert(InnodbVirtual record);

    int insertSelective(InnodbVirtual record);

    List<InnodbVirtual> selectByExample(InnodbVirtualExample example);

    int updateByExampleSelective(@Param("record") InnodbVirtual record, @Param("example") InnodbVirtualExample example);

    int updateByExample(@Param("record") InnodbVirtual record, @Param("example") InnodbVirtualExample example);
}