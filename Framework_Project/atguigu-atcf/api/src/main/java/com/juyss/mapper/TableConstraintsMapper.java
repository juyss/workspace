package com.juyss.mapper;

import com.juyss.bean.TableConstraints;
import com.juyss.bean.TableConstraintsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TableConstraintsMapper {
    int countByExample(TableConstraintsExample example);

    int deleteByExample(TableConstraintsExample example);

    int insert(TableConstraints record);

    int insertSelective(TableConstraints record);

    List<TableConstraints> selectByExample(TableConstraintsExample example);

    int updateByExampleSelective(@Param("record") TableConstraints record, @Param("example") TableConstraintsExample example);

    int updateByExample(@Param("record") TableConstraints record, @Param("example") TableConstraintsExample example);
}