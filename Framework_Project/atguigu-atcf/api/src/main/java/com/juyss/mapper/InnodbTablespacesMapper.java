package com.juyss.mapper;

import com.juyss.bean.InnodbTablespaces;
import com.juyss.bean.InnodbTablespacesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbTablespacesMapper {
    int countByExample(InnodbTablespacesExample example);

    int deleteByExample(InnodbTablespacesExample example);

    int insert(InnodbTablespaces record);

    int insertSelective(InnodbTablespaces record);

    List<InnodbTablespaces> selectByExample(InnodbTablespacesExample example);

    int updateByExampleSelective(@Param("record") InnodbTablespaces record, @Param("example") InnodbTablespacesExample example);

    int updateByExample(@Param("record") InnodbTablespaces record, @Param("example") InnodbTablespacesExample example);
}