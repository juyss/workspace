package com.juyss.mapper;

import com.juyss.bean.InnodbFtDeleted;
import com.juyss.bean.InnodbFtDeletedExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbFtDeletedMapper {
    int countByExample(InnodbFtDeletedExample example);

    int deleteByExample(InnodbFtDeletedExample example);

    int insert(InnodbFtDeleted record);

    int insertSelective(InnodbFtDeleted record);

    List<InnodbFtDeleted> selectByExample(InnodbFtDeletedExample example);

    int updateByExampleSelective(@Param("record") InnodbFtDeleted record, @Param("example") InnodbFtDeletedExample example);

    int updateByExample(@Param("record") InnodbFtDeleted record, @Param("example") InnodbFtDeletedExample example);
}