package com.juyss.mapper;

import com.juyss.bean.InnodbTempTableInfo;
import com.juyss.bean.InnodbTempTableInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbTempTableInfoMapper {
    int countByExample(InnodbTempTableInfoExample example);

    int deleteByExample(InnodbTempTableInfoExample example);

    int insert(InnodbTempTableInfo record);

    int insertSelective(InnodbTempTableInfo record);

    List<InnodbTempTableInfo> selectByExample(InnodbTempTableInfoExample example);

    int updateByExampleSelective(@Param("record") InnodbTempTableInfo record, @Param("example") InnodbTempTableInfoExample example);

    int updateByExample(@Param("record") InnodbTempTableInfo record, @Param("example") InnodbTempTableInfoExample example);
}