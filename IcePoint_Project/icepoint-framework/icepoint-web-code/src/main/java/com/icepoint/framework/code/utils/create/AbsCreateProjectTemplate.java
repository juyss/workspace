package com.icepoint.framework.code.utils.create;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icepoint.framework.code.constant.CommonConstant;
import com.icepoint.framework.web.system.dao.ModuleMapper;
import com.icepoint.framework.web.system.entity.*;
import com.icepoint.framework.web.system.service.ProjectService;
import com.icepoint.framework.web.system.service.ParameterService;
import com.icepoint.framework.web.system.service.TableMetadataService;
import com.icepoint.framework.web.system.service.TableServiceService;
import com.icepoint.framework.web.system.util.CreateCode;
import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.model.ApiConfig;
import com.power.doc.model.SourceCodePath;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * 模板方法
 *
 * @author ck
 */
@Component
@Slf4j
public abstract class AbsCreateProjectTemplate {

    protected Project project;

    @Resource
    private ProjectService projectService;

    @Resource
    private ParameterService parameterService;

    @Resource
    private TableServiceService tableServiceService;

    @Resource
    private TableMetadataService tableMetadataService;

    protected Parameter parameter;

    protected TableService tableService;

    protected TableMetadata tableMetadata;

    protected Module module;

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private CreateCode createCode;

    @Resource
    private TestCaseTemplate template;

    protected String packagePath;

    protected final String packagePathPrefix = "com.icepoint.framework.moudle.";

    protected String uri;

    protected String functionPath = "";

    protected String processListPath = "";

    /**
     * 创建工程 根据tableService表进行生成
     */
    public final synchronized void creat(Long tableServiceId) {
        examine(tableServiceId);
        createNameSpace();
        createProject();
        createCode();
        createTestCase(project, tableMetadata);
        // createDoc();
        createFunction();
    }


    /**
     * 检验参数 初始化参数 TODO
     */
    public void examine(Long tableServiceId) {
        try {
            //查询服务
            this.tableService = tableServiceService.findOne(tableServiceId);
            if (ObjectUtils.isEmpty(tableService)) {
                throw new IllegalArgumentException("表服务不在");
            }
            LambdaQueryWrapper<TableMetadata> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TableMetadata::getId, tableService.getTableId());
            //tableMetadata
            this.tableMetadata = tableMetadataService.selectTable(queryWrapper);
            if (ObjectUtils.isEmpty(tableMetadata)) {
                throw new IllegalArgumentException("表不存在");
            }
            Optional<Module> byId = moduleMapper.findById(tableMetadata.getModuleId());
            if (!byId.isPresent()) {
                throw new IllegalArgumentException("模块不存在");
            }
            this.module = byId.get();
            //project
            this.project = projectService.findOne(this.module.getProjectId());
            if (ObjectUtils.isEmpty(project)) {
                throw new IllegalArgumentException("工程不存在");
            }
            if (ObjectUtils.isEmpty(project.getCustId())) {
                throw new IllegalArgumentException("创建工程的用户不存在");
            }
            //获取工程存放位置
            this.parameter = parameterService.queryProjectPath();
            //根据服务实体 生成工程路径 服务路径 服务请求路径 测试用例路径 文档路径 TODO
            if (ObjectUtils.isEmpty(parameter) || ObjectUtils.isEmpty(parameter.getCval())) {
                throw new IllegalArgumentException("模板路径不存在");
            }
            log.info("初始化参数数完毕.............................");
            //初始化包名
            StringBuilder stringBuilder = new StringBuilder(packagePathPrefix);
            stringBuilder.append(createCode.getEntityNameLower(tableService.getNameEn()));
            this.packagePath = stringBuilder.toString();
            //初始化请求路径
            StringBuilder stringUri = new StringBuilder(this.module.getName());
            stringUri.append("/");
            stringUri.append(this.tableService.getNameEn());
            this.uri = stringUri.toString();
            //查询processList文件是否存在
            Long masterId = project.getMasterId();
            processListPath = parameter.getCval() + File.separator + masterId + File.separator + CommonConstant.PROCESSLIST_XML;
            File file = new File(processListPath);
            if (!file.exists()) {
                FileUtils.write(file, "", true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 创建用户命名空间
     */
    public void createNameSpace() {
        log.info("创建用户命名空间中.........................");
        StringBuilder projectPath = new StringBuilder(parameter.getCval())
                .append(File.separator)
                .append(project.getMasterId())
                .append(File.separator)
                .append(project.getName())
                .append(File.separator)
                .append(module.getName())
                .append(File.separator)
                .append(tableService.getNameEn());
        File file = new File(projectPath.toString());
        log.info("用户命名空间为{}", file.getAbsolutePath());
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
            log.info("创建用户命名空间成功..................");
            //获取命名空间路径
        }
        functionPath = project.getNamespace() + File.separator + module.getName()
                + File.separator + "function" + File.separator + tableService.getNameEn();
        project.setNamespace(projectPath.toString());
    }

    /**
     * 创建工程
     */
    public abstract void createProject();


    /**
     * 生成代码
     */
    public abstract void createCode();

    /**
     * 创建测试用例
     */
    public void createTestCase(Project project, TableMetadata tableMetadata) {
        template.creatTestCase(project, tableMetadata);
    }

    /**
     * 创建文档
     */
    public void createDoc() {
        ApiConfig config = new ApiConfig();
        // 接口地址
        config.setServerUrl("http://localhost:8080");
        // 严格模式
        config.setStrict(false);
        // 是否生成到一个文档中
        config.setAllInOne(true);
        // 文档输出地址
        String namespace = project.getNamespace();
        int lastIndexOf = namespace.lastIndexOf(File.separator);
        namespace = namespace.substring(0, lastIndexOf - 1);
        File docFile = new File(namespace);
        if (docFile.exists()) {
            log.info("文件已存在");
        }
        //自己项目过滤
        config.setPackageFilters("com.icepoint.framework.code");
        config.setOutPath(docFile.getAbsolutePath());
        // 覆盖文件
        config.setCoverOld(true);
        // 读取项目源码地址
        StringBuilder packagePath = new StringBuilder(project.getNamespace());
        packagePath.append(File.separator);
        packagePath.append(CreateBootProject.PREFIX);
        config.setSourceCodePaths(
                // smart-doc对路径自动会做处理，无论是window合适linux系统路径，直接拷贝贴入即可。可以把该生成方法添加到具体项目中生成，也可以作为一个单独项目。
                // SourceCodePath.path().setDesc("本项目代码").setPath("src/main/java"),
                SourceCodePath.path().setPath(packagePath.toString())
        );
        config.setProjectName(project.getName());
        long start = System.currentTimeMillis();
        HtmlApiDocBuilder.builderControllersApi(config);
        long end = System.currentTimeMillis();
        log.info("生成doc文档用时{}:", (end - start) / 1000.00 + "秒");
    }

    /**
     * 生成单表增删改查函数
     */
    protected abstract void createFunction();


}
