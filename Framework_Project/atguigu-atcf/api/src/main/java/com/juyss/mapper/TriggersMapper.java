package com.juyss.mapper;

import com.juyss.bean.Triggers;
import com.juyss.bean.TriggersExample;
import com.juyss.bean.TriggersWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TriggersMapper {
    int countByExample(TriggersExample example);

    int deleteByExample(TriggersExample example);

    int insert(TriggersWithBLOBs record);

    int insertSelective(TriggersWithBLOBs record);

    List<TriggersWithBLOBs> selectByExampleWithBLOBs(TriggersExample example);

    List<Triggers> selectByExample(TriggersExample example);

    int updateByExampleSelective(@Param("record") TriggersWithBLOBs record, @Param("example") TriggersExample example);

    int updateByExampleWithBLOBs(@Param("record") TriggersWithBLOBs record, @Param("example") TriggersExample example);

    int updateByExample(@Param("record") Triggers record, @Param("example") TriggersExample example);
}