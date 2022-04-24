package com.juyss.mapper;

import com.juyss.bean.InnodbForeign;
import com.juyss.bean.InnodbForeignExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbForeignMapper {
    int countByExample(InnodbForeignExample example);

    int deleteByExample(InnodbForeignExample example);

    int insert(InnodbForeign record);

    int insertSelective(InnodbForeign record);

    List<InnodbForeign> selectByExample(InnodbForeignExample example);

    int updateByExampleSelective(@Param("record") InnodbForeign record, @Param("example") InnodbForeignExample example);

    int updateByExample(@Param("record") InnodbForeign record, @Param("example") InnodbForeignExample example);
}