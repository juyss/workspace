package com.juyss.mapper;

import com.juyss.bean.Tables;
import com.juyss.bean.TablesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TablesMapper {
    int countByExample(TablesExample example);

    int deleteByExample(TablesExample example);

    int insert(Tables record);

    int insertSelective(Tables record);

    List<Tables> selectByExampleWithBLOBs(TablesExample example);

    List<Tables> selectByExample(TablesExample example);

    int updateByExampleSelective(@Param("record") Tables record, @Param("example") TablesExample example);

    int updateByExampleWithBLOBs(@Param("record") Tables record, @Param("example") TablesExample example);

    int updateByExample(@Param("record") Tables record, @Param("example") TablesExample example);
}