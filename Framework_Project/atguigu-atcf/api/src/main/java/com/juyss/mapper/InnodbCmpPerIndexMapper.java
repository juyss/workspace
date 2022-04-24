package com.juyss.mapper;

import com.juyss.bean.InnodbCmpPerIndex;
import com.juyss.bean.InnodbCmpPerIndexExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbCmpPerIndexMapper {
    int countByExample(InnodbCmpPerIndexExample example);

    int deleteByExample(InnodbCmpPerIndexExample example);

    int insert(InnodbCmpPerIndex record);

    int insertSelective(InnodbCmpPerIndex record);

    List<InnodbCmpPerIndex> selectByExample(InnodbCmpPerIndexExample example);

    int updateByExampleSelective(@Param("record") InnodbCmpPerIndex record, @Param("example") InnodbCmpPerIndexExample example);

    int updateByExample(@Param("record") InnodbCmpPerIndex record, @Param("example") InnodbCmpPerIndexExample example);
}