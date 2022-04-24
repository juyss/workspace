package com.juyss.mapper;

import com.juyss.bean.Views;
import com.juyss.bean.ViewsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewsMapper {
    int countByExample(ViewsExample example);

    int deleteByExample(ViewsExample example);

    int insert(Views record);

    int insertSelective(Views record);

    List<Views> selectByExampleWithBLOBs(ViewsExample example);

    List<Views> selectByExample(ViewsExample example);

    int updateByExampleSelective(@Param("record") Views record, @Param("example") ViewsExample example);

    int updateByExampleWithBLOBs(@Param("record") Views record, @Param("example") ViewsExample example);

    int updateByExample(@Param("record") Views record, @Param("example") ViewsExample example);
}