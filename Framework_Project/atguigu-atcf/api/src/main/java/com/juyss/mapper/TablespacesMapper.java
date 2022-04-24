package com.juyss.mapper;

import com.juyss.bean.Tablespaces;
import com.juyss.bean.TablespacesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TablespacesMapper {
    int countByExample(TablespacesExample example);

    int deleteByExample(TablespacesExample example);

    int insert(Tablespaces record);

    int insertSelective(Tablespaces record);

    List<Tablespaces> selectByExample(TablespacesExample example);

    int updateByExampleSelective(@Param("record") Tablespaces record, @Param("example") TablespacesExample example);

    int updateByExample(@Param("record") Tablespaces record, @Param("example") TablespacesExample example);
}