package com.github.tangyi.dataQuarter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DataQuarterMapper {

    @Update("update pk_data_quarter set deleted=1 where id=#{id}")
    Integer delete(@Param("id") Long id);
}
