package com.github.tangyi.user.service;

import cn.hutool.core.collection.CollUtil;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.common.core.utils.TreeUtil;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.user.api.dto.DeptDto;
import com.github.tangyi.user.api.module.Dept;
import com.github.tangyi.user.api.module.User;
import com.github.tangyi.user.mapper.DeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 部门service
 *
 * @author tangyi
 * @date 2018/8/26 22:46
 */
@Service
public class DeptService extends CrudService<DeptMapper, Dept> {

    /**
     * 删除部门
     *
     * @param dept dept
     * @return int
     */
    @Transactional
    @Override
    public int delete(Dept dept) {
        // 删除部门
        return super.delete(dept);
    }

    /**
     * 根据用户批量查询
     *
     * @param userList userList
     * @return List
     * @author tangyi
     * @date 2019/07/03 22:06:50
     */
    public List<Dept> getListByUsers(List<User> userList) {
        return this.findListById(userList.stream().filter(tempUser -> tempUser.getDeptId() != null).map(User::getDeptId).distinct().toArray(Long[]::new));
    }

    public List<Dept> getListByUserId(Long userId) {
        return dao.getListByUser(userId);
    }

    /**
     * 查询树形部门集合
     *
     * @return List
     * @author tangyi
     * @date 2018/10/25 12:57
     */
    public List<DeptDto> depts(String type) {
        Dept dept = new Dept();
        dept.setApplicationCode(SysUtil.getSysCode());
        dept.setTenantCode(SysUtil.getTenantCode());
        dept.setType(type);
        // 查询部门集合
        Stream<Dept> deptStream = findList(dept).stream();
        if (Optional.ofNullable(deptStream).isPresent()) {
            List<DeptDto> deptTreeList = deptStream.map(DeptDto::new).collect(Collectors.toList());
            // 排序、构建树形结构
            return TreeUtil
                    .buildTree(CollUtil.sort(deptTreeList, Comparator.comparingInt(item -> item.getSort() == null ? 0 : item.getSort())), CommonConstant.ROOT);
        }
        return new ArrayList<>();
    }

    public List<Dept> depts(Long parentId, String type) {
        Dept dept = new Dept();
        dept.setParentId(parentId);
        dept.setType(type);
        dept.setApplicationCode(SysUtil.getSysCode());
        dept.setTenantCode(SysUtil.getTenantCode());
        // 查询部门集合
        List<Dept> list = findList(dept);

        return list;
    }

    public List<HashMap<String, String>> listEnterprise() {
        return dao.listEnterprise();
    }

    @Transactional
    public void updateEnterprise(List<HashMap<String, String>> toUpdateList) {
        toUpdateList.forEach(element -> {
            dao.updateEnterprise(element);
        });

    }

    @Transactional
    public void saveEnterprise(List<HashMap<String, String>> toInsertList) {
        if (!toInsertList.isEmpty()) {
            dao.saveEnterprise(toInsertList);
        }
    }

}
