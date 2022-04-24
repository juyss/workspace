package com.juyss.mapper;

import com.juyss.bean.Processlist;
import com.juyss.bean.ProcesslistExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProcesslistMapper {
    int countByExample(ProcesslistExample example);

    int deleteByExample(ProcesslistExample example);

    int insert(Processlist record);

    int insertSelective(Processlist record);

    List<Processlist> selectByExample(ProcesslistExample example);

    int updateByExampleSelective(@Param("record") Processlist record, @Param("example") ProcesslistExample example);

    int updateByExample(@Param("record") Processlist record, @Param("example") ProcesslistExample example);
}