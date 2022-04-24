package com.juyss.mapper;

import com.juyss.bean.InnodbCmpReset;
import com.juyss.bean.InnodbCmpResetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbCmpResetMapper {
    int countByExample(InnodbCmpResetExample example);

    int deleteByExample(InnodbCmpResetExample example);

    int insert(InnodbCmpReset record);

    int insertSelective(InnodbCmpReset record);

    List<InnodbCmpReset> selectByExample(InnodbCmpResetExample example);

    int updateByExampleSelective(@Param("record") InnodbCmpReset record, @Param("example") InnodbCmpResetExample example);

    int updateByExample(@Param("record") InnodbCmpReset record, @Param("example") InnodbCmpResetExample example);
}