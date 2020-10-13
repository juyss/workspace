package com.juyss.mapper;

import com.juyss.bean.InnodbSessionTempTablespaces;
import com.juyss.bean.InnodbSessionTempTablespacesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbSessionTempTablespacesMapper {
    int countByExample(InnodbSessionTempTablespacesExample example);

    int deleteByExample(InnodbSessionTempTablespacesExample example);

    int insert(InnodbSessionTempTablespaces record);

    int insertSelective(InnodbSessionTempTablespaces record);

    List<InnodbSessionTempTablespaces> selectByExample(InnodbSessionTempTablespacesExample example);

    int updateByExampleSelective(@Param("record") InnodbSessionTempTablespaces record, @Param("example") InnodbSessionTempTablespacesExample example);

    int updateByExample(@Param("record") InnodbSessionTempTablespaces record, @Param("example") InnodbSessionTempTablespacesExample example);
}