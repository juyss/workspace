package com.icepoint.framework.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.web.system.dao.TableMetadataMapper;
import com.icepoint.framework.web.system.dao.TableServiceMapper;
import com.icepoint.framework.web.system.dao.TableServiceProcessMapper;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.entity.TableService;
import com.icepoint.framework.web.system.entity.TableServiceProcess;
import com.icepoint.framework.web.system.service.TableServiceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (SysTabService)表服务实现类
 *
 * @author makejava
 * @since 2021-06-04 16:12:29
 */
@Service("sysTabServiceService")
public class TableServiceServiceImpl implements TableServiceService {

    @Resource
    private TableServiceMapper mapper;

    @Resource
    private TableMetadataMapper tableMetadataMapper;

    @Resource
    private TableServiceProcessMapper tableServiceProcessMapper;


    /**
     * 创建工程
     *
     * @param entity 数据封装
     * @return 是否创建成功
     */
    @Override
    public boolean save(TableService entity){
        return mapper.insert(entity)==1;
    }

    /**
     * 更新
     *
     * @param entity 对象封装
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean update(TableService entity) {
        return mapper.update(entity)==1;
    }

    /**
     * 根据主键逻辑删除
     *
     * @param id 主键
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean delete(Long id) {
        return mapper.deleteById(id)==1;
    }

    /**
     * 批量删除
     *
     * @param ids 主键集合
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer deleteAll(List<Long> ids) {
        return mapper.deleteInBatchIds(ids);
    }

    /**
     * 根据主键查询单条数据
     *
     * @param id 主键
     * @return SysProject
     * @author Juyss
     */
    @Override
    public TableService findOne(Long id) {
        LambdaQueryWrapper<TableService> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TableService::getId,id);
        return mapper.findOne(wrapper).orElse(null);
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
    public Page<TableService> findAll(Map<String, Object> columnMap, Pageable pageable) {
        QueryWrapper<TableService> wrapper = new QueryWrapper<>();
        wrapper.allEq(columnMap);
        return mapper.findAll(wrapper, pageable);
    }

    /**
     * 根据资源id查询表服务
     *
     * @param resourceId 资源id
     * @return Page<TableService>
     * @author Juyss
     */
    @Override
    public Page<TableService> findAllByResourceId(Long resourceId, Pageable pageable) {
        LambdaQueryWrapper<TableMetadata> tableWrapper = new LambdaQueryWrapper<>();
        tableWrapper.eq(TableMetadata::getResourceId,resourceId); //根据ResourceId进行匹配
        List<TableMetadata> tableList = tableMetadataMapper.findAll(tableWrapper); //查到所有数据
        List<Long> idList = tableList.stream().map(TableMetadata::getId).collect(Collectors.toList()); //获取tableId集合

        LambdaQueryWrapper<TableService> tableServiceWrapper = new LambdaQueryWrapper<>();
        tableServiceWrapper.in(TableService::getTableId,idList); //where tableId in (idList)

        return mapper.findAll(tableServiceWrapper, pageable);
    }

    /**
     * 通过数据表ID获取Process信息
     *
     * @return List<TableServiceProcess>
     * @author Juyss
     */
    @Override
    public List<TableServiceProcess> getProcessByTableId(Long tableId) {

        //查表服务
        LambdaQueryWrapper<TableService> tableServiceWrapper = new LambdaQueryWrapper<>();
        tableServiceWrapper.eq(TableService::getTableId,tableId);
        Optional<TableService> tableServiceOptional = mapper.findOne(tableServiceWrapper);
        if (!tableServiceOptional.isPresent()){
            throw new IllegalArgumentException("此表未关联表服务");
        }
        TableService tableService = tableServiceOptional.get();

        //查process
        LambdaQueryWrapper<TableServiceProcess> tableServiceProcessWrapper = new LambdaQueryWrapper<>();
        tableServiceProcessWrapper.eq(TableServiceProcess::getTableServiceId,tableService.getId());
        List<TableServiceProcess> tableServiceProcessList = tableServiceProcessMapper.findAll(tableServiceProcessWrapper);
        if (tableServiceProcessList.isEmpty()){
            throw new IllegalArgumentException("未生成process文件");
        }
        return tableServiceProcessList;
    }
}
