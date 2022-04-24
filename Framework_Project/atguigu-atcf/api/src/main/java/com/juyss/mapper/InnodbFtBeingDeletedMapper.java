package com.juyss.mapper;

import com.juyss.bean.InnodbFtBeingDeleted;
import com.juyss.bean.InnodbFtBeingDeletedExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbFtBeingDeletedMapper {
    int countByExample(InnodbFtBeingDeletedExample example);

    int deleteByExample(InnodbFtBeingDeletedExample example);

    int insert(InnodbFtBeingDeleted record);

    int insertSelective(InnodbFtBeingDeleted record);

    List<InnodbFtBeingDeleted> selectByExample(InnodbFtBeingDeletedExample example);

    int updateByExampleSelective(@Param("record") InnodbFtBeingDeleted record, @Param("example") InnodbFtBeingDeletedExample example);

    int updateByExample(@Param("record") InnodbFtBeingDeleted record, @Param("example") InnodbFtBeingDeletedExample example);
}