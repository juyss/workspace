package com.github.tangyi.user.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单mapper
 *
 * @author tangyi
 * @date 2018/8/26 22:34
 */
@Mapper
public interface DeptMapper extends CrudMapper<Dept> {
    List<HashMap<String, String>> listEnterprise();

    void updateEnterprise(HashMap<String, String> enterprise);

    void saveEnterprise(@Param("list") List<HashMap<String, String>> toInsertList);

    List<Dept> getListByUser(@Param("userId")Long userId);
}