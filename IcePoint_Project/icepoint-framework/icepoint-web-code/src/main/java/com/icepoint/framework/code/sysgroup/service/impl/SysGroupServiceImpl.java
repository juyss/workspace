package com.icepoint.framework.code.sysgroup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.code.sysgroup.dao.SysGroupDao;
import com.icepoint.framework.code.sysgroup.entity.SysGroup;
import com.icepoint.framework.code.sysgroup.service.SysGroupService;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.data.util.TreeUtils;
import com.icepoint.framework.web.system.service.ParameterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 分组表(SysGroup)表服务实现类
 *
 * @author makejava
 * @since 2021-06-05 11:04:11
 */
@Service("sysGroupService")
@Slf4j
public class SysGroupServiceImpl implements SysGroupService {

    @Resource
    private SysGroupDao mapper;

    @Resource
    private ParameterService parameterService;

    /**
     * 创建资源
     *
     * @param sysGroup 数据封装
     * @return 是否创建成功
     */
    @Override
    public boolean save(SysGroup sysGroup) {
        Long parentId = sysGroup.getParentId();
        //查询最大索引
        LambdaQueryWrapper<SysGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysGroup::getParentId,parentId).orderByAsc(SysGroup::getIdx).last("limit 1");
        Optional<SysGroup> optional = mapper.findOne(wrapper);
        if (optional.isPresent()){
            SysGroup group = optional.get();
            Long minIdx = group.getIdx();
            sysGroup.setIdx(minIdx-1);
            int insert = mapper.insert(sysGroup);
            return insert == 1;
        }
        return false;
    }

    /**
     * 更新
     *
     * @param sysGroup 对象封装
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean update(SysGroup sysGroup) {
        return mapper.update(sysGroup) == 1;
    }

    /**
     * 根据主键逻辑删除
     *
     * @param ids 主键集合
     * @return Long 删除的数量
     * @author Juyss
     */
    @Override
    public Integer delete(List<Long> ids) {

        int deleteCount = 0; //统计删除数量

        for (Long id : ids) {
            //现根据id查询
            LambdaQueryWrapper<SysGroup> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysGroup::getId, id);
            Optional<SysGroup> sysGroupOptional = mapper.findOne(wrapper);
            //如果存在数据，再进行逻辑删除
            if (sysGroupOptional.isPresent()) {

                wrapper.clear();
                //查询子节点
                wrapper.eq(SysGroup::getParentId, id);
                List<SysGroup> list = mapper.findAll(wrapper);
                int delete=0;
                if (list.size()!=0){
                    //删除当前节点的全部子节点
                    delete = mapper.deleteInBatchIds(list.stream().map(SysGroup::getId).collect(Collectors.toList()));
                }

                //删除当前节点
                SysGroup sysGroup = sysGroupOptional.get();
                assert sysGroup.getId() != null;
                delete += mapper.deleteById(sysGroup.getId());
                deleteCount += delete;
            }
        }
        return deleteCount;
    }

    /**
     * 根据主键查询单条数据
     *
     * @param id 主键
     * @return SysProject
     * @author Juyss
     */
    @Override
    public SysGroup findOne(Long id) {
        return mapper.findById(id).orElse(null);
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
    public Page<SysGroup> findAll(Map<String, Object> columnMap, Pageable pageable) {
        QueryWrapper<SysGroup> wrapper = new QueryWrapper<>();
        wrapper.allEq(columnMap).orderByAsc("idx");
        return mapper.findAll(wrapper, pageable);
    }

    /**
     * 获取树形结构
     *
     * @return List<TreeNode < SysGroup>>
     * @author Juyss
     */
    @Override
    public List<TreeNode<SysGroup>> getTreeList(Long rootId) {
        List<SysGroup> list = mapper.findAll();
        List<SysGroup> sysGroupList = list.stream().sorted(Comparator.comparing(SysGroup::getIdx)).collect(Collectors.toList());
        return TreeUtils.buildTreeStructure(sysGroupList, rootId);
    }

    /**
     * 移动节点，命令（置顶 :top  置底 :end 移动 :move）
     *
     * @param parentId  父节点ID
     * @param thisId    此节点ID
     * @param anotherId 另一个需要修改的节点ID
     * @param command   移动命令
     * @return Boolean
     * @author Juyss
     */
    @Override
    public Boolean updateIdxById(Long parentId, Long thisId, Long anotherId, String command) {
        //如果命令移动命令为空，返回false
        if (StringUtils.isEmpty(command)) {
            return false;
        }

        //更新数量
        boolean flag = false;

        //置顶，设置此节点 idx=MIN(idx)-0.1
        if ("top".equalsIgnoreCase(command)) {

            //查询最小索引
            LambdaQueryWrapper<SysGroup> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysGroup::getParentId, parentId).orderByAsc(SysGroup::getIdx).last("limit 1");
            Optional<SysGroup> optional = mapper.findOne(wrapper);
            if (optional.isPresent()) {
                SysGroup sysGroup = optional.get();

                //得到最小索引
                Long minOrder = sysGroup.getIdx();
                if (minOrder != null) {
                    wrapper.clear();
                    wrapper.eq(SysGroup::getId, thisId);
                    Optional<SysGroup> thisOptional = mapper.findOne(wrapper);
                    if (thisOptional.isPresent()) {
                        SysGroup group = thisOptional.get();
                        //更新节点索引值
                        group.setIdx(minOrder - 1);
                        int updated = mapper.update(group);
                        flag = updated == 1;
                    }
                }
            }

        }

        //置底，设置此节点 idx=MAX(idx)+0.1
        if ("end".equalsIgnoreCase(command)) {
            //查询最大索引
            LambdaQueryWrapper<SysGroup> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysGroup::getParentId, parentId).orderByDesc(SysGroup::getIdx).last("limit 1");
            Optional<SysGroup> optional = mapper.findOne(wrapper);
            if (optional.isPresent()) {
                SysGroup sysGroup = optional.get();

                //得到最大索引
                Long minOrder = sysGroup.getIdx();
                if (minOrder != null) {
                    wrapper.clear();
                    wrapper.eq(SysGroup::getId, thisId);
                    Optional<SysGroup> thisOptional = mapper.findOne(wrapper);
                    if (thisOptional.isPresent()) {
                        SysGroup group = thisOptional.get();
                        //更新节点索引值
                        group.setIdx(minOrder + 1);
                        int updated = mapper.update(group);
                        flag = updated == 1;
                    }
                }
            }
        }

        //移动，将两个节点idx数值进行交换
        if ("move".equalsIgnoreCase(command)) {
            //先查询是否有数据
            Optional<SysGroup> thisOptional = mapper.findById(thisId);
            Optional<SysGroup> anotherOptional = mapper.findById(anotherId);

            if (thisOptional.isPresent() && anotherOptional.isPresent()) {
                //获取节点数据
                SysGroup thisNode = thisOptional.get();
                SysGroup anotherNode = anotherOptional.get();
                Long thisOrder = thisNode.getIdx();
                Long anotherOrder = anotherNode.getIdx();
                //order值调换
                thisNode.setIdx(anotherOrder);
                anotherNode.setIdx(thisOrder);
                //更新数据
                int thisUpdated = mapper.update(thisNode);
                int anotherUpdated = mapper.update(anotherNode);

                flag = thisUpdated + anotherUpdated == 2;
            }
        }

        return flag;
    }

    @Override
    public boolean updateDescription(MultipartFile file, Long id) {
        //通过用户id 获取 函数分组描述文件路径
        String groupFilePath = parameterService.groupDescription(id);
        File groupFile = new File(groupFilePath);
        try {
            byte[] bytes = file.getBytes();
            FileUtils.writeByteArrayToFile(groupFile,bytes,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("函数分组文件名为{}",groupFilePath);
        return true;
    }
}
