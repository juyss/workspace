package com.icepoint.framework.web.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.data.util.TreeUtils;
import com.icepoint.framework.web.security.dao.DepartmentRepository;
import com.icepoint.framework.web.security.dao.DeptMapper;
import com.icepoint.framework.web.security.entity.Organization;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.web.security.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DeptMapper mapper;

    @Override
    public Organization add(Organization entity) {
        return departmentRepository.save(entity);
    }

    @Override
    public Organization edit(Organization entity) {
        return departmentRepository.saveAndFlush(entity);
    }

    @Override
    public Integer delete(List<Long> ids) {
        departmentRepository.deleteAllInId(ids);
        return ids.size();
    }

    @Override
    public Page<Organization> list(Organization entity, Pageable pageable) {
        if (!ObjectUtils.isEmpty(entity)) {
            Map<String, Object> map = MapUtils.objectToLineMap(entity);
            QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
            queryWrapper.allEq(map);
            return mapper.findAll(queryWrapper, pageable);
        }
        return mapper.findAll(pageable);
    }


    @Override
    public Organization findById(Long id) {
        // TODO Auto-generated method stub
        return departmentRepository.getOne(id);
    }

    @Override
    public List<TreeNode<Organization>> getTreeList(Long parentId) {
        List<Organization> list = departmentRepository.findAll(false);
        return TreeUtils.buildTreeStructure(list, parentId);
    }

    @Override
    public List<Organization> queryByParentId(Long parentId) {
        LambdaQueryWrapper<Organization> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Organization::getParentId,parentId);
        return mapper.findAll(lambdaQueryWrapper);
    }


}
