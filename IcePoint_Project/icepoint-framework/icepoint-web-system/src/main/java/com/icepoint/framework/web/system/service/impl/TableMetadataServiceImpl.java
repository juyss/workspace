package com.icepoint.framework.web.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.web.core.support.StdEntityServiceImpl;
import com.icepoint.framework.web.system.dao.TableMetadataMapper;
import com.icepoint.framework.web.system.dao.TableMetadataRepository;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.QTableMetadata;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.model.Column;
import com.icepoint.framework.web.system.model.Table;
import com.icepoint.framework.web.system.service.FieldMetadataService;
import com.icepoint.framework.web.system.service.TableMetadataService;
import com.icepoint.framework.web.system.util.CreateCode;
import com.icepoint.framework.web.system.util.PdmUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (TableMetadata)表服务实现类
 *
 * @author makejava
 * @since 2021-06-01 16:38:09
 */
@Service
@Slf4j
public class TableMetadataServiceImpl extends StdEntityServiceImpl<TableMetadata, Long> implements TableMetadataService {

    @Resource
    private TableMetadataMapper mapper;

    @Resource
    private TableMetadataRepository repository;

    @Resource
    private FieldMetadataService sysFieldService;

    /**
     * 查询一条数据
     *
     * @param id 主键
     * @return TableMetadata
     * @author Juyss
     */
    @Override
    public TableMetadata findOne(Long id) {
        return mapper.findById(id).orElse(null);
    }

    /**
     * 分页查询
     *
     * @param entity   属性用于构造条件查询
     * @param pageable 分页参数
     * @return Page<TableMetadata>
     * @author Juyss
     */
    @Override
    public Page<TableMetadata> page(TableMetadata entity, Pageable pageable) {
        Page<TableMetadata> page = null;
        //对象转map,属性名驼峰转下划线
        Map<String, Object> columnMap = MapUtils.objectToLineMap(entity);

        QueryWrapper<TableMetadata> wrapper = new QueryWrapper<>();
        wrapper.allEq(columnMap);
        page = mapper.findAll(wrapper, pageable);

        return page;
    }

    /**
     * 添加
     *
     * @param entity 数据对象
     * @return Boolean
     * @author Juyss
     */
    @Override
    public Boolean insert(TableMetadata entity) {
        List<TableMetadata> all = mapper.findAll();
        List<TableMetadata> collect = all.stream().
                filter(a -> a.getNameEn().equals(entity.getNameEn())).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(collect)) {
            return mapper.insert(entity) == 1;
        }
        return mapper.insert(entity) == 0;
    }

    /**
     * 更新
     *
     * @param entity 数据对象
     * @return Boolean
     * @author Juyss
     */
    @Override
    public Boolean update(TableMetadata entity) {
        return mapper.update(entity) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer delete(List<Long> ids) {
        return mapper.deleteInBatchIds(ids);
    }

    @Override
    public List<TableMetadata> findByProjectId(Long projId) {
        return mapper.findAllByProjectId(getEntityMetadata().getTableInfo(), projId);
    }

    @Override
    public Optional<TableMetadata> findByResourceId(Long resourceId) {
        QTableMetadata q = QTableMetadata.tableMetadata;
        return repository.findOne(q.resourceId.eq(resourceId), false);
    }

    @Override
    public Boolean upLoadPdm(MultipartFile file) {
        if (file.getSize() <= 0) {
            return false;
        }
        String originalFilename = file.getOriginalFilename();
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String fileSuffix = originalFilename.substring(lastIndexOf);
        if (!".pdm".equals(fileSuffix)) {
            return false;
        }
        //先把文件读取到工程目录下  解析
        try {
            byte[] bytes = file.getBytes();
            File toFile = new File(originalFilename);
            FileUtils.writeByteArrayToFile(toFile, bytes);
            Table[] tables = PdmUtils.parsePDM_VO(toFile.getAbsolutePath());
            //需要过滤的表
            List<Table> tables1 = Arrays.asList(tables);
            //查询所有的表
            List<TableMetadata> tableAll = mapper.findAll();

            boolean duplicated = Streams.stream(tables1)
                    .map(Table::getNameEn)
                    .anyMatch(nameEn -> Streams.stream(tableAll)
                            .map(TableMetadata::getNameEn)
                            .anyMatch(nameEn::equals));
            Assert.isTrue(!duplicated, "表名重复");
            if (tables.length != 0) {
                for (Table table : tables) {
                    TableMetadata sysTable = new TableMetadata();
                    BeanUtils.copyProperties(table, sysTable);
                    sysTable.setStatus(1);
                    mapper.insert(sysTable);
                    Column[] cols = table.getCols();
                    //插入列
                    if (cols.length != 0) {
                        for (Column col : cols) {
                            Assert.isTrue(!ObjectUtils.isEmpty(col.getType()), "字段类型不对");
                            FieldMetadata sysField = FieldMetadata.builder()
                                    .nativeType(col.getType())
                                    .status(0)
                                    .tableId(sysTable.getId())
                                    .javaType(CreateCode.dataTypeMap.get(col.getType()))
                                    .primaryKey(col.isPrimaryKey())
                                    .build();
                            sysField.setAppId(1000L);
                            BeanUtils.copyProperties(col, sysField);
                            sysFieldService.saveSysFiled(sysField);
                        }
                    }
                }
            }
            log.info("解析的文件为{}", Arrays.toString(tables));
            toFile.delete();
        } catch (IOException e) {
            log.error("解析失败{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public TableMetadata selectTable(LambdaQueryWrapper<TableMetadata> queryWrapper) {
        Optional<TableMetadata> one = this.mapper.findOne(queryWrapper);
        return one.orElse(null);
    }

}
