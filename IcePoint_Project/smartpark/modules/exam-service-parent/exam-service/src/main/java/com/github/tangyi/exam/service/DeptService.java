package com.github.tangyi.exam.service;

import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.exam.mapper.DeptMapper;
import com.github.tangyi.user.api.module.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门service
 *
 * @author tangyi
 * @date 2018/8/26 22:46
 */
@Service
public class DeptService extends CrudService<DeptMapper, Dept> {

    /**
     * 根据用户ID查询部门集合
     * @param userId 用户ID
     * @return 部门集合
     */
    public List<Dept> getListByUserId(Long userId) {
        return dao.getListByUser(userId);
    }

}
