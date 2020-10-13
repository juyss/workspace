package com.juyss.mapper;

import com.juyss.bean.InnodbTrx;
import com.juyss.bean.InnodbTrxExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InnodbTrxMapper {
    int countByExample(InnodbTrxExample example);

    int deleteByExample(InnodbTrxExample example);

    int insert(InnodbTrx record);

    int insertSelective(InnodbTrx record);

    List<InnodbTrx> selectByExample(InnodbTrxExample example);

    int updateByExampleSelective(@Param("record") InnodbTrx record, @Param("example") InnodbTrxExample example);

    int updateByExample(@Param("record") InnodbTrx record, @Param("example") InnodbTrxExample example);
}