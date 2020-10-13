package com.juyss.mapper;

import com.juyss.bean.InnodbFtConfig;
import com.juyss.bean.InnodbFtConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbFtConfigMapper {
    int countByExample(InnodbFtConfigExample example);

    int deleteByExample(InnodbFtConfigExample example);

    int insert(InnodbFtConfig record);

    int insertSelective(InnodbFtConfig record);

    List<InnodbFtConfig> selectByExample(InnodbFtConfigExample example);

    int updateByExampleSelective(@Param("record") InnodbFtConfig record, @Param("example") InnodbFtConfigExample example);

    int updateByExample(@Param("record") InnodbFtConfig record, @Param("example") InnodbFtConfigExample example);
}