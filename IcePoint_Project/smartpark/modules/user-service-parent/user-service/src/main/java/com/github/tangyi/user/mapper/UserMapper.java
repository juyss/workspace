package com.github.tangyi.user.mapper;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.user.api.module.Dept;
import com.github.tangyi.user.api.module.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户mapper接口
 *
 * @author tangyi
 * @date 2018-08-25 15:27
 */
@Mapper
public interface UserMapper extends CrudMapper<User> {

    /**
     * 查询用户数量
     *
     * @param userVo userVo
     * @return Integer
     */
    Integer userCount(UserVo userVo);

    /**
     * 查询角色为普通用户和统一认证用户的用户列表
     * @param param
     * @return
     */
    List<Map<String,Object>> roleUserUserList(Map<String,Object> param);

    List<Map<String,Object>> commonUserList(Map<String,Object> param);

    List<Dept> getUserDeptList(Long userId);
}
