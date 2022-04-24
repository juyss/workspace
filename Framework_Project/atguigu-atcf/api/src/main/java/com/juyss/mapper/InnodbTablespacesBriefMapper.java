package com.juyss.mapper;

import com.juyss.bean.InnodbTablespacesBrief;
import com.juyss.bean.InnodbTablespacesBriefExample;
import com.juyss.bean.InnodbTablespacesBriefWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbTablespacesBriefMapper {
    int countByExample(InnodbTablespacesBriefExample example);

    int deleteByExample(InnodbTablespacesBriefExample example);

    int insert(InnodbTablespacesBriefWithBLOBs record);

    int insertSelective(InnodbTablespacesBriefWithBLOBs record);

    List<InnodbTablespacesBriefWithBLOBs> selectByExampleWithBLOBs(InnodbTablespacesBriefExample example);

    List<InnodbTablespacesBrief> selectByExample(InnodbTablespacesBriefExample example);

    int updateByExampleSelective(@Param("record") InnodbTablespacesBriefWithBLOBs record, @Param("example") InnodbTablespacesBriefExample example);

    int updateByExampleWithBLOBs(@Param("record") InnodbTablespacesBriefWithBLOBs record, @Param("example") InnodbTablespacesBriefExample example);

    int updateByExample(@Param("record") InnodbTablespacesBrief record, @Param("example") InnodbTablespacesBriefExample example);
}