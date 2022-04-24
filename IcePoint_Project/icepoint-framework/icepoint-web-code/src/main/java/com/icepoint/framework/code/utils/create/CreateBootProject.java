package com.icepoint.framework.code.utils.create;

import com.icepoint.framework.code.constant.CommonConstant;
import com.icepoint.framework.code.xml.entity.Input;
import com.icepoint.framework.code.xml.entity.Output;
import com.icepoint.framework.code.xml.entity.Process;
import com.icepoint.framework.code.xml.service.impl.XmlObjectTransformImpl;
import com.icepoint.framework.core.util.CaseUtils;
import com.icepoint.framework.web.system.dao.TableServiceProcessMapper;
import com.icepoint.framework.web.system.entity.FieldMetadata;
import com.icepoint.framework.web.system.entity.Parameter;
import com.icepoint.framework.web.system.entity.TableServiceProcess;
import com.icepoint.framework.web.system.model.Code;
import com.icepoint.framework.web.system.service.FieldMetadataService;
import com.icepoint.framework.web.system.service.ParameterService;
import com.icepoint.framework.web.system.util.CreateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 创建springboot代码
 *
 * @author ck
 * @version 1.0
 * @date 2021/5/25 14:33
 */
@Component
@Slf4j
public class CreateBootProject extends AbsCreateProjectTemplate {

    /**
     * 创建springboot 项目
     */
    @Resource
    private ParameterService parameterService;

    @Resource
    private FieldMetadataService fieldMetadataService;

    @Resource
    private TableServiceProcessMapper tableServiceProcessMapper;

    /**
     * 文件路径  模板路径 +(命名空间)nameSpace +(java源路径)PREFIX + package
     */
    public static final String PREFIX = "src" + File.separator + "main" + File.separator + "java";

    /**
     * controller 包路径
     */
    public static final String CONTROLLER_PACKAGE = File.separator + "web";

    /**
     * service接口包路径
     */
    public static final String SERVICE_PACKAGE = File.separator + "service";

    /**
     * service实现类包路径
     */
    public static final String SERVICE_IMP_PACKAGE = File.separator + "service" + File.separator + "impl";

    /**
     * dao包路径
     */
    public static final String DAO_PACKAGE = File.separator + "dao";

    /**
     * entity路径
     */
    public static final String ENTITY_PACKAGE = File.separator + "entity";

    @Resource
    private CreateCode createCodeUtil;

    private static final String DEFAULT_FUN_TYPE = "projectFun";

    @Resource
    private XmlObjectTransformImpl xmlObjectTransform;


    @Override
    public void createProject() {
        log.info("生成springboot代码.......", super.project.getName());
        //获取模板
        Parameter tParameter = parameterService.queryTemplate();
        if (ObjectUtils.isEmpty(tParameter) || ObjectUtils.isEmpty(tParameter.getCval())) {
            throw new IllegalStateException("模板路径不存在  请重新设置路径");
        }
        //判断文件夹是否存在
        File file = new File(tParameter.getCval());
        if (!file.exists()) {
            throw new IllegalStateException("模板不存在");
        }
        //把模板复制到命名空间下
        try {
            FileUtils.copyDirectory(new File(tParameter.getCval()),
                    new File(super.project.getNamespace()));
        } catch (IOException e) {
            log.error("复制工程时失败{}", e.getMessage());
            e.printStackTrace();
        }
        log.info("创建{}工程完毕.......", super.project.getName());

    }

    @Override
    public void createCode() {
        log.info("表数据构建中.....................");
        Map<String, Object> tableInfo = createCodeUtil.getDataInfo(super.tableMetadata, super.packagePath, super.uri);
        Code code = (Code) tableInfo.get("code");
        String packageName = code.getPackageName();
        String[] split = packageName.split("\\.");
        StringBuilder packagePath = new StringBuilder(super.project.getNamespace());
        packagePath.append(File.separator);
        packagePath.append(PREFIX);
        for (String s : split) {
            packagePath.append(File.separator)
                    .append(s);
        }
        File file = new File(packagePath.toString());
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            //生成 controller层代码
            createCodeUtil.generate(tableInfo, "controller.ftl",
                    packagePath + CONTROLLER_PACKAGE, code.getEntityName() + "Controller.java");
            //生成 service层代码
            createCodeUtil.generate(tableInfo, "service.ftl",
                    packagePath + SERVICE_PACKAGE, code.getEntityName() + "Service.java");
            //生成 serviceImp层代码
            createCodeUtil.generate(tableInfo, "serviceImpl.ftl",
                    packagePath + SERVICE_IMP_PACKAGE, code.getEntityName() + "ServiceImpl.java");
            //生成dao层代码
            createCodeUtil.generate(tableInfo, "dao.ftl",
                    packagePath + DAO_PACKAGE, code.getEntityName() + "Repository.java");
            //生成entity层代码
            createCodeUtil.generate(tableInfo, "entity.ftl",
                    packagePath + ENTITY_PACKAGE, code.getEntityName() + "Entity.java");
            log.info("{}---生成代码成功", code.getDataTable());
        } catch (Exception e) {
            log.error("创建代码失败--{}", e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     *
     */
    @Override
    protected void createFunction() {
        //查询所有的字段
        List<FieldMetadata> fieldMetadata =
                fieldMetadataService.findByTableId(super.tableMetadata.getId());

        if (ObjectUtils.isEmpty(fieldMetadata)) {
            throw new IllegalArgumentException("缺少字段");
        }

        // 创建默认函数
        Process add = createProcess(CommonConstant.METHOD_ADD, fieldMetadata);
        Process update = createProcess(CommonConstant.METHOD_UPDATE, fieldMetadata);
        Process deleted = createProcess(CommonConstant.METHOD_DELETE, fieldMetadata);
        Process get = createProcess(CommonConstant.METHOD_GET, fieldMetadata);
        try {
            File addFile = TestCaseTemplate.creatFile(functionPath + CommonConstant.ADD_XML);
            File updateFile = TestCaseTemplate.creatFile(functionPath + CommonConstant.UPDATE_XML);
            File deletedFile = TestCaseTemplate.creatFile(functionPath + CommonConstant.DELETE_XML);
            File getFile = TestCaseTemplate.creatFile(functionPath + CommonConstant.GET_XML);
            FileUtils.write(addFile, "", true);
            FileUtils.write(updateFile, "", true);
            FileUtils.write(deletedFile, "", true);
            FileUtils.write(getFile, "", true);

            //***********************添加服务****************************
            xmlObjectTransform.process2Xml(addFile.getAbsolutePath(), add);

            //将数据添加到数据库
            TableServiceProcess addTableServiceProcess = new TableServiceProcess();
            addTableServiceProcess.setTableServiceId(super.tableMetadata.getId());
            addTableServiceProcess.setName(super.tableMetadata.getNameEn() + "添加服务"); //设置服务名,格式:模块名+"添加服务"
            addTableServiceProcess.setNameEn(addFile.getName()
                    .substring(0, addFile.getName().lastIndexOf("."))); //获取文件名,不含后缀
            addTableServiceProcess.setFilePath(addFile.getAbsolutePath()); //获取文件绝对路径
            addTableServiceProcess.setDescription(super.tableMetadata.getName() + "添加服务");
            tableServiceProcessMapper.insert(addTableServiceProcess);

            //***********************更新服务****************************
            xmlObjectTransform.process2Xml(updateFile.getAbsolutePath(), update);

            //将数据添加到数据库
            TableServiceProcess updateTableServiceProcess = new TableServiceProcess();
            updateTableServiceProcess.setTableServiceId(super.tableMetadata.getId());
            updateTableServiceProcess.setName(super.tableMetadata.getNameEn() + "更新服务"); //设置服务名,格式:模块名+"更新服务"
            updateTableServiceProcess.setNameEn(updateFile.getName()
                    .substring(0, updateFile.getName().lastIndexOf("."))); //获取文件名,不含后缀
            updateTableServiceProcess.setFilePath(updateFile.getAbsolutePath()); //获取文件绝对路径
            updateTableServiceProcess.setDescription(super.tableMetadata.getName() + "更新服务");
            tableServiceProcessMapper.insert(updateTableServiceProcess);

            //***********************删除服务****************************
            xmlObjectTransform.process2Xml(deletedFile.getAbsolutePath(), deleted);

            //将数据添加到数据库
            TableServiceProcess deleteTableService = new TableServiceProcess();
            deleteTableService.setTableServiceId(super.tableMetadata.getId());
            deleteTableService.setName(super.tableMetadata.getNameEn() + "删除服务"); //设置服务名,格式:模块名+"删除服务"
            deleteTableService.setNameEn(deletedFile.getName()
                    .substring(0, deletedFile.getName().lastIndexOf("."))); //获取文件名,不含后缀
            deleteTableService.setFilePath(deletedFile.getAbsolutePath()); //获取文件绝对路径
            deleteTableService.setDescription(super.tableMetadata.getName() + "删除服务");
            tableServiceProcessMapper.insert(deleteTableService);

            //***********************查询服务****************************
            xmlObjectTransform.process2Xml(getFile.getAbsolutePath(), get);

            //将数据添加到数据库
            TableServiceProcess getTableServiceProcess = new TableServiceProcess();
            getTableServiceProcess.setTableServiceId(super.tableMetadata.getId());
            //设置服务名,格式:模块名+"查询服务"
            getTableServiceProcess.setName(super.tableMetadata.getNameEn() + "查询服务");
            //获取文件名,不含后缀
            getTableServiceProcess.setNameEn(getFile.getName().substring(0, getFile.getName().lastIndexOf(".")));
            //获取文件绝对路径
            getTableServiceProcess.setFilePath(getFile.getAbsolutePath());
            //描述信息
            getTableServiceProcess.setDescription(super.tableMetadata.getName() + "查询服务");
            tableServiceProcessMapper.insert(getTableServiceProcess);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Process createProcessHead(String type) {
        String controller = super.packagePath + ".controller." + CaseUtils.toUpperCamel(super.tableMetadata.getNameEn()) + "Controller";
        String service = super.packagePath + ".service." + CaseUtils.toUpperCamel(super.tableMetadata.getNameEn()) + "Service";
        Process process = new Process();
        process.setController(controller);
        process.setModule(super.module.getName());
        process.setAbstracts("");
        process.setType(DEFAULT_FUN_TYPE);
        process.setService(service);
        if (CommonConstant.METHOD_ADD.equals(type)) {
            process.setPicture("");
            process.setHttpMode("Post");
            process.setName(super.packagePath + ".add");
            process.setTitle("添加" + tableMetadata.getName());
            return process;
        }
        if (CommonConstant.METHOD_DELETE.equals(type)) {
            process.setPicture("");
            process.setHttpMode("Delete");
            process.setName(super.packagePath + ".delete");
            process.setTitle("删除" + tableMetadata.getName() + "数据");
            return process;
        }
        if (CommonConstant.METHOD_UPDATE.equals(type)) {
            process.setPicture("");
            process.setHttpMode("Put");
            process.setName(super.packagePath + ".update");
            process.setTitle("修改" + tableMetadata.getName() + "数据");
            return process;
        }
        if (CommonConstant.METHOD_GET.equals(type)) {
            process.setPicture("");
            process.setHttpMode("Get");
            process.setName(super.packagePath + ".get");
            process.setTitle("查询" + tableMetadata.getName() + "数据");
            return process;
        }
        return null;

    }

    public Process createProcess(String type, List<FieldMetadata> fieldMetadata) {
        Process process = createProcessHead(type);
        List<FieldMetadata> collect = fieldMetadata.stream()
                .filter(a -> Boolean.TRUE.equals(a.getPrimaryKey()))
                .collect(Collectors.toList());
        if (collect.size() != 1) {
            throw new IllegalArgumentException("主键个数有误");
        }
        FieldMetadata primaryKey = collect.get(0);
        List<Input> inputs = new ArrayList<>();
        List<Output> outputs = new ArrayList<>();
        if (CommonConstant.METHOD_ADD.equals(type)) {
            List<Input> inputs1 = allField(fieldMetadata);
            inputs.addAll(inputs1);
            Output output = outPrimaryKey(primaryKey);
            outputs.add(output);
        }
        if (CommonConstant.METHOD_DELETE.equals(type)) {
            Input input = inPrimaryKey(primaryKey);
            Output output = outPrimaryKey(primaryKey);
            outputs.add(output);
            inputs.add(input);
        }
        if (CommonConstant.METHOD_UPDATE.equals(type)) {
            List<Input> inputs1 = allField(fieldMetadata);
            inputs.addAll(inputs1);
            Output output = outPrimaryKey(primaryKey);
            outputs.add(output);
        }
        if (CommonConstant.METHOD_UPDATE.equals(type)) {
            List<Input> inputs1 = allField(fieldMetadata);
            inputs.addAll(inputs1);
            Output output = outPrimaryKey(primaryKey);
            outputs.add(output);
        }
        process.setInputs(inputs);
        process.setOutputs(outputs);
        return process;


    }

    public Output outPrimaryKey(FieldMetadata fieldMetadata) {
        Output output = Output.builder()
                .abstracts("")
                .name(CaseUtils.toLowerCamel(fieldMetadata.getNameEn()))
                .title(fieldMetadata.getNameEn())
                .type(CreateCode.dataTypeMap.get(fieldMetadata.getNativeType()))
                .build();
        return output;
    }

    public Input inPrimaryKey(FieldMetadata fieldMetadata) {
        Input input = Input.builder()
                .name(CaseUtils.toLowerCamel(fieldMetadata.getNameEn()))
                .option(true)
                .title(fieldMetadata.getNameEn())
                .type(CreateCode.dataTypeMap.get(fieldMetadata.getNativeType()))
                .build();
        return input;
    }

    public List<Input> allField(List<FieldMetadata> fieldMetadata) {
        List<Input> inputs = new ArrayList<>();
        for (FieldMetadata fields : fieldMetadata) {
            Boolean option = fields.getOptional();
            //输入参数
            Input input = Input.builder()
                    .name(CaseUtils.toLowerCamel(fields.getNameEn()))
                    .option(option)
                    .title(fields.getNameEn())
                    .type(CreateCode.dataTypeMap.get(fields.getNativeType()))
                    .build();
            inputs.add(input);
        }
        return inputs;
    }

}