package com.juyss.mapper;

import com.juyss.bean.InnodbCmpPerIndexReset;
import com.juyss.bean.InnodbCmpPerIndexResetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbCmpPerIndexResetMapper {
    int countByExample(InnodbCmpPerIndexResetExample example);

    int deleteByExample(InnodbCmpPerIndexResetExample example);

    int insert(InnodbCmpPerIndexReset record);

    int insertSelective(InnodbCmpPerIndexReset record);

    List<InnodbCmpPerIndexReset> selectByExample(InnodbCmpPerIndexResetExample example);

    int updateByExampleSelective(@Param("record") InnodbCmpPerIndexReset record, @Param("example") InnodbCmpPerIndexResetExample example);

    int updateByExample(@Param("record") InnodbCmpPerIndexReset record, @Param("example") InnodbCmpPerIndexResetExample example);
}