package com.icepoint.framework.code.utils.create;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.Project;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.service.FieldMetadataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * 测试用例模板
 *
 * @author ck
 */
@Component
@Slf4j
public abstract class TestCaseTemplate {

    protected Project project;

    protected String namespace;

    protected String addNameSpace;

    protected String updateNameSpace;

    protected String deleteNameSpace;

    protected String getNameSpace;

    public static final String TEST_CASE = "testcase";

    public static final String ADD = "add.txt";

    public static final String UPDATE = "update.txt";

    public static final String DELETE = "delete.txt";

    public static final String GET = "get.txt";

    protected TableMetadata tableMetadata;
    /**
     * add文件
     */
    protected File addFile;

    /**
     * update文件
     */
    protected File updateFile;

    /**
     * get文件
     */
    protected File getFile;

    /**
     * 删除文件
     */
    protected File deleteFile;

    @Resource
    private FieldMetadataService fieldMetadataService;

    protected List<FieldMetadata> fieldList;


    final synchronized void creatTestCase(Project project, TableMetadata tableMetadata) {
        this.project = project;
        this.namespace = project.getNamespace();
        this.tableMetadata = tableMetadata;
        LambdaQueryWrapper<FieldMetadata> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(FieldMetadata::getTableId, this.tableMetadata.getId());
        //获取所有的字段和值域
        this.fieldList = fieldMetadataService.list(lambdaQueryWrapper);
        Assert.isTrue(!ObjectUtils.isEmpty(fieldList), "表没有建立字段");
        init();
        createSuiteAdd();
        createSuiteUpdate();
        createSuiteGet();
        createSuiteDelete();
    }

    public void init() {
        addNameSpace = this.namespace + File.separator + TEST_CASE + File.separator + ADD;
        updateNameSpace = this.namespace + File.separator + TEST_CASE + File.separator + UPDATE;
        getNameSpace = this.namespace + File.separator + TEST_CASE + File.separator + GET;
        deleteNameSpace = this.namespace + File.separator + TEST_CASE + File.separator + DELETE;
        this.addFile = creatFile(addNameSpace);
        this.updateFile = creatFile(updateNameSpace);
        this.getFile = creatFile(getNameSpace);
        this.deleteFile = creatFile(deleteNameSpace);
    }


    /**
     * 生成新增方法测试用例
     */
    abstract boolean createSuiteAdd();

    /**
     * 生成修改方法测试用例
     */
    abstract boolean createSuiteUpdate();

    /**
     * 生成查询方法测试用例
     */
    abstract boolean createSuiteGet();

    /**
     * 生成删除方法测试用例
     */
    abstract boolean createSuiteDelete();

    /**
     * 创建文件
     */
    public static File creatFile(String namespace) {
        File file = null;
        try {
            file = new File(namespace);
            FileUtils.write(file, "", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
