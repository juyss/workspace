package com.juyss.mapper;

import com.juyss.bean.InnodbFtDefaultStopword;
import com.juyss.bean.InnodbFtDefaultStopwordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbFtDefaultStopwordMapper {
    int countByExample(InnodbFtDefaultStopwordExample example);

    int deleteByExample(InnodbFtDefaultStopwordExample example);

    int insert(InnodbFtDefaultStopword record);

    int insertSelective(InnodbFtDefaultStopword record);

    List<InnodbFtDefaultStopword> selectByExample(InnodbFtDefaultStopwordExample example);

    int updateByExampleSelective(@Param("record") InnodbFtDefaultStopword record, @Param("example") InnodbFtDefaultStopwordExample example);

    int updateByExample(@Param("record") InnodbFtDefaultStopword record, @Param("example") InnodbFtDefaultStopwordExample example);
}