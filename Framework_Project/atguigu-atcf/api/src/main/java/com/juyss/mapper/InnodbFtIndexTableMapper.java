package com.juyss.mapper;

import com.juyss.bean.InnodbFtIndexTable;
import com.juyss.bean.InnodbFtIndexTableExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbFtIndexTableMapper {
    int countByExample(InnodbFtIndexTableExample example);

    int deleteByExample(InnodbFtIndexTableExample example);

    int insert(InnodbFtIndexTable record);

    int insertSelective(InnodbFtIndexTable record);

    List<InnodbFtIndexTable> selectByExample(InnodbFtIndexTableExample example);

    int updateByExampleSelective(@Param("record") InnodbFtIndexTable record, @Param("example") InnodbFtIndexTableExample example);

    int updateByExample(@Param("record") InnodbFtIndexTable record, @Param("example") InnodbFtIndexTableExample example);
}