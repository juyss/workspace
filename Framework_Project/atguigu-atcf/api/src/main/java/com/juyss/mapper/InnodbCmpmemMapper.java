package com.juyss.mapper;

import com.juyss.bean.InnodbCmpmem;
import com.juyss.bean.InnodbCmpmemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbCmpmemMapper {
    int countByExample(InnodbCmpmemExample example);

    int deleteByExample(InnodbCmpmemExample example);

    int insert(InnodbCmpmem record);

    int insertSelective(InnodbCmpmem record);

    List<InnodbCmpmem> selectByExample(InnodbCmpmemExample example);

    int updateByExampleSelective(@Param("record") InnodbCmpmem record, @Param("example") InnodbCmpmemExample example);

    int updateByExample(@Param("record") InnodbCmpmem record, @Param("example") InnodbCmpmemExample example);
}