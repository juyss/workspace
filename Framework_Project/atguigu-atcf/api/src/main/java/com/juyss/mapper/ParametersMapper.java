package com.juyss.mapper;

import com.juyss.bean.Parameters;
import com.juyss.bean.ParametersExample;
import com.juyss.bean.ParametersWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParametersMapper {
    int countByExample(ParametersExample example);

    int deleteByExample(ParametersExample example);

    int insert(ParametersWithBLOBs record);

    int insertSelective(ParametersWithBLOBs record);

    List<ParametersWithBLOBs> selectByExampleWithBLOBs(ParametersExample example);

    List<Parameters> selectByExample(ParametersExample example);

    int updateByExampleSelective(@Param("record") ParametersWithBLOBs record, @Param("example") ParametersExample example);

    int updateByExampleWithBLOBs(@Param("record") ParametersWithBLOBs record, @Param("example") ParametersExample example);

    int updateByExample(@Param("record") Parameters record, @Param("example") ParametersExample example);
}