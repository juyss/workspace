package com.juyss.mapper;

import com.juyss.bean.InnodbDatafiles;
import com.juyss.bean.InnodbDatafilesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbDatafilesMapper {
    int countByExample(InnodbDatafilesExample example);

    int deleteByExample(InnodbDatafilesExample example);

    int insert(InnodbDatafiles record);

    int insertSelective(InnodbDatafiles record);

    List<InnodbDatafiles> selectByExampleWithBLOBs(InnodbDatafilesExample example);

    List<InnodbDatafiles> selectByExample(InnodbDatafilesExample example);

    int updateByExampleSelective(@Param("record") InnodbDatafiles record, @Param("example") InnodbDatafilesExample example);

    int updateByExampleWithBLOBs(@Param("record") InnodbDatafiles record, @Param("example") InnodbDatafilesExample example);

    int updateByExample(@Param("record") InnodbDatafiles record, @Param("example") InnodbDatafilesExample example);
}