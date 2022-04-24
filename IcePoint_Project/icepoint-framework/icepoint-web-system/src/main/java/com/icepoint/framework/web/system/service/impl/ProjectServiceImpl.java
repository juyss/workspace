package com.icepoint.framework.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.web.system.dao.ProjectMapper;
import com.icepoint.framework.web.system.entity.Project;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.entity.Parameter;
import com.icepoint.framework.web.system.service.ProjectService;
import com.icepoint.framework.web.system.service.ResourceService;
import com.icepoint.framework.web.system.service.ParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * (SysProject)表服务实现类
 *
 * @author makejava
 * @since 2021-05-24 16:57:37
 */
@Service("sysProjectService")
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @javax.annotation.Resource
    private ProjectMapper mapper;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ParameterService parameterService;

    /**
     * 创建工程
     *
     * @param project 数据封装
     * @return 是否创建成功
     */
    @Override
    public boolean saveSysProject(Project project) {
        //TODO
     //   final Long requiredUserId = SecurityUtils.getRequiredUserId();
     //   project.setCustId(requiredUserId);
        int insert = mapper.insert(project);
        if (insert == 1) {
            //工程的命名空间
            Parameter tParameter = parameterService.queryProjectPath();
            StringBuilder stringBuilder = new StringBuilder(tParameter.getCval());
            stringBuilder.append(File.separator);
            Assert.isTrue(!ObjectUtils.isEmpty(project.getMasterId()), "没有用户id");
            stringBuilder.append(project.getMasterId());
            project.setNamespace(stringBuilder.toString());
            int update = mapper.update(project);
            Assert.isTrue(update < 0, "保存工程失败");
            return true;
        }
        return false;
    }

    /**
     * 更新
     *
     * @param project 对象封装
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean updateProject(Project project) {
        return mapper.update(project) == 1;
    }

    /**
     * 根据主键逻辑删除
     *
     * @param id 主键
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean deleteProject(Long id) {

        //先根据id查询
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Project::getId, id);
        Optional<Project> sysProjectOptional = mapper.findOne(wrapper);
        List<Resource> resources = resourceService.findOneByProjId(id);
        if (resources != null && resources.size() != 0) {
            return false;
        } else {
            //如果存在数据，在进行逻辑删除
            if (sysProjectOptional.isPresent()) {
                Project project = sysProjectOptional.get();
                assert project.getId() != null;
                int delete = mapper.deleteById(project.getId());
                return delete == 1;
            }
        }
        return false;
    }

    /**
     * 根据主键查询单条数据
     *
     * @param id 主键
     * @return SysProject
     * @author Juyss
     */
    @Override
    public Project findOne(Long id) {
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Project::getId, id);
        Optional<Project> project = mapper.findOne(wrapper);
        return project.orElse(null);
    }

    /**
     * 条件查询并分页
     *
     * @param columnMap 条件
     * @param pageable  分页参数
     * @return Page<SysProject>
     * @author Juyss
     */
    @Override
    public Page<Project> findAll(Map<String, Object> columnMap, Pageable pageable) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.allEq(columnMap);
        return mapper.findAll(wrapper, pageable);
    }

}
