package com.juyss.mapper;

import com.juyss.bean.Partitions;
import com.juyss.bean.PartitionsExample;
import com.juyss.bean.PartitionsWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartitionsMapper {
    int countByExample(PartitionsExample example);

    int deleteByExample(PartitionsExample example);

    int insert(PartitionsWithBLOBs record);

    int insertSelective(PartitionsWithBLOBs record);

    List<PartitionsWithBLOBs> selectByExampleWithBLOBs(PartitionsExample example);

    List<Partitions> selectByExample(PartitionsExample example);

    int updateByExampleSelective(@Param("record") PartitionsWithBLOBs record, @Param("example") PartitionsExample example);

    int updateByExampleWithBLOBs(@Param("record") PartitionsWithBLOBs record, @Param("example") PartitionsExample example);

    int updateByExample(@Param("record") Partitions record, @Param("example") PartitionsExample example);
}