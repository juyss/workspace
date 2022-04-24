package com.juyss.mapper;

import com.juyss.bean.Plugins;
import com.juyss.bean.PluginsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PluginsMapper {
    int countByExample(PluginsExample example);

    int deleteByExample(PluginsExample example);

    int insert(Plugins record);

    int insertSelective(Plugins record);

    List<Plugins> selectByExample(PluginsExample example);

    int updateByExampleSelective(@Param("record") Plugins record, @Param("example") PluginsExample example);

    int updateByExample(@Param("record") Plugins record, @Param("example") PluginsExample example);
}