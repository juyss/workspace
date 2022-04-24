package com.juyss.mapper;

import com.juyss.bean.Files;
import com.juyss.bean.FilesExample;
import com.juyss.bean.FilesWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesMapper {
    int countByExample(FilesExample example);

    int deleteByExample(FilesExample example);

    int insert(FilesWithBLOBs record);

    int insertSelective(FilesWithBLOBs record);

    List<FilesWithBLOBs> selectByExampleWithBLOBs(FilesExample example);

    List<Files> selectByExample(FilesExample example);

    int updateByExampleSelective(@Param("record") FilesWithBLOBs record, @Param("example") FilesExample example);

    int updateByExampleWithBLOBs(@Param("record") FilesWithBLOBs record, @Param("example") FilesExample example);

    int updateByExample(@Param("record") Files record, @Param("example") FilesExample example);
}