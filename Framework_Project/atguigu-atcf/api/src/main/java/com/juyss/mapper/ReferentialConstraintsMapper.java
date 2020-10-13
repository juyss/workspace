package com.juyss.mapper;

import com.juyss.bean.ReferentialConstraints;
import com.juyss.bean.ReferentialConstraintsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReferentialConstraintsMapper {
    int countByExample(ReferentialConstraintsExample example);

    int deleteByExample(ReferentialConstraintsExample example);

    int insert(ReferentialConstraints record);

    int insertSelective(ReferentialConstraints record);

    List<ReferentialConstraints> selectByExample(ReferentialConstraintsExample example);

    int updateByExampleSelective(@Param("record") ReferentialConstraints record, @Param("example") ReferentialConstraintsExample example);

    int updateByExample(@Param("record") ReferentialConstraints record, @Param("example") ReferentialConstraintsExample example);
}