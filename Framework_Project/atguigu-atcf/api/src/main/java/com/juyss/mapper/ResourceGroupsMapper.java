package com.juyss.mapper;

import com.juyss.bean.ResourceGroups;
import com.juyss.bean.ResourceGroupsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceGroupsMapper {
    int countByExample(ResourceGroupsExample example);

    int deleteByExample(ResourceGroupsExample example);

    int insert(ResourceGroups record);

    int insertSelective(ResourceGroups record);

    List<ResourceGroups> selectByExampleWithBLOBs(ResourceGroupsExample example);

    List<ResourceGroups> selectByExample(ResourceGroupsExample example);

    int updateByExampleSelective(@Param("record") ResourceGroups record, @Param("example") ResourceGroupsExample example);

    int updateByExampleWithBLOBs(@Param("record") ResourceGroups record, @Param("example") ResourceGroupsExample example);

    int updateByExample(@Param("record") ResourceGroups record, @Param("example") ResourceGroupsExample example);
}