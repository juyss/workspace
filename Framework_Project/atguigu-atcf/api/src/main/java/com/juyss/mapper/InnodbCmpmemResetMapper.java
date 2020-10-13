package com.juyss.mapper;

import com.juyss.bean.InnodbCmpmemReset;
import com.juyss.bean.InnodbCmpmemResetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbCmpmemResetMapper {
    int countByExample(InnodbCmpmemResetExample example);

    int deleteByExample(InnodbCmpmemResetExample example);

    int insert(InnodbCmpmemReset record);

    int insertSelective(InnodbCmpmemReset record);

    List<InnodbCmpmemReset> selectByExample(InnodbCmpmemResetExample example);

    int updateByExampleSelective(@Param("record") InnodbCmpmemReset record, @Param("example") InnodbCmpmemResetExample example);

    int updateByExample(@Param("record") InnodbCmpmemReset record, @Param("example") InnodbCmpmemResetExample example);
}