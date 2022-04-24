package com.juyss.mapper;

import com.juyss.bean.CheckConstraints;
import com.juyss.bean.CheckConstraintsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckConstraintsMapper {
    int countByExample(CheckConstraintsExample example);

    int deleteByExample(CheckConstraintsExample example);

    int insert(CheckConstraints record);

    int insertSelective(CheckConstraints record);

    List<CheckConstraints> selectByExampleWithBLOBs(CheckConstraintsExample example);

    List<CheckConstraints> selectByExample(CheckConstraintsExample example);

    int updateByExampleSelective(@Param("record") CheckConstraints record, @Param("example") CheckConstraintsExample example);

    int updateByExampleWithBLOBs(@Param("record") CheckConstraints record, @Param("example") CheckConstraintsExample example);

    int updateByExample(@Param("record") CheckConstraints record, @Param("example") CheckConstraintsExample example);
}