package com.juyss.mapper;

import com.juyss.bean.StSpatialReferenceSystems;
import com.juyss.bean.StSpatialReferenceSystemsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StSpatialReferenceSystemsMapper {
    int countByExample(StSpatialReferenceSystemsExample example);

    int deleteByExample(StSpatialReferenceSystemsExample example);

    int insert(StSpatialReferenceSystems record);

    int insertSelective(StSpatialReferenceSystems record);

    List<StSpatialReferenceSystems> selectByExample(StSpatialReferenceSystemsExample example);

    int updateByExampleSelective(@Param("record") StSpatialReferenceSystems record, @Param("example") StSpatialReferenceSystemsExample example);

    int updateByExample(@Param("record") StSpatialReferenceSystems record, @Param("example") StSpatialReferenceSystemsExample example);
}