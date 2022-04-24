package com.github.tangyi.exam.mapper;


import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门mapper
 *
 * @author tangyi
 * @date 2018/8/26 22:34
 */
@Mapper
public interface DeptMapper extends CrudMapper<Dept> {

    List<Dept> getListByUser(@Param("userId")Long userId);
}
