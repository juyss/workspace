package com.github.tangyi.dataYear.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DateYearMapper {

    @Update("update pk_data_year set deleted = 1 where id = #{id} ")
     Integer deleteByid(@Param("id") Long id) ;
}
