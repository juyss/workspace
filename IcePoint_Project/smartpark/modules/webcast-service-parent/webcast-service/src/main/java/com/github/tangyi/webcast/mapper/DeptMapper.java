package com.github.tangyi.webcast.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.webcast.api.model.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface DeptMapper extends CrudMapper<Dept> {

    List<Long> deptParentList(@Param("depetId") Long depetId);

    List<Dept> selectDeptByid(@Param("id") Long id);

    @Select("select * from sys_dept where id =#{id}")
    Dept selectSysDeptByid(@Param("id") Long deptId);
}
