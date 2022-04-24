package com.icepoint.framework.code.sysfunction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.icepoint.framework.code.response.dto.FunctionDTO;
import com.icepoint.framework.code.response.dto.JavaFunctionDTO;
import com.icepoint.framework.code.sysfunction.dao.SysFunctionDao;
import com.icepoint.framework.code.sysfunction.entity.SysFunction;
import com.icepoint.framework.code.sysfunction.service.SysFunctionService;
import com.icepoint.framework.code.sysgroup.dao.SysGroupDao;
import com.icepoint.framework.code.sysgroup.entity.SysGroup;
import com.icepoint.framework.code.xml.entity.Definitions;
import com.icepoint.framework.code.xml.entity.Group;
import com.icepoint.framework.code.xml.entity.Process;
import com.icepoint.framework.code.xml.service.XmlObjectTransform;
import com.icepoint.framework.core.io.Serializers;
import com.icepoint.framework.web.security.util.SecurityUtils;
import com.icepoint.framework.web.system.dao.ProjectMapper;
import com.icepoint.framework.web.system.dao.ParameterMapper;
import com.icepoint.framework.web.system.dao.TableMetadataMapper;
import com.icepoint.framework.web.system.dao.TableServiceProcessMapper;
import com.icepoint.framework.web.system.entity.Project;
import com.icepoint.framework.web.system.entity.Parameter;
import com.icepoint.framework.web.system.entity.TableMetadata;
import com.icepoint.framework.web.system.entity.TableServiceProcess;
import com.icepoint.framework.web.system.enums.HttpQueryTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (SysFunction)表服务实现类
 *
 * @author makejava
 * @since 2021-06-07 10:05:59
 */
@Service("sysFunctionService")
@Slf4j
public class SysFunctionServiceImpl implements SysFunctionService {

    @Resource
    private SysFunctionDao mapper;

    @Resource
    private ParameterMapper parameterMapper;

    @Resource
    private XmlObjectTransform xmlObjectTransform;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private TableMetadataMapper tableMetadataMapper;

    @Resource
    private SysGroupDao sysGroupDao;

    @Resource
    private TableServiceProcessMapper tableServiceProcessMapper;


    /**
     * 添加函数
     *
     * @param sysFunction 数据封装
     * @return 是否创建成功
     */
    @Override
    public boolean insert(SysFunction sysFunction) {
        return mapper.insert(sysFunction) == 1;
    }

    /**
     * 更新
     *
     * @param sysFunction 对象封装
     * @return boolean
     * @author Juyss
     */
    @Override
    public boolean update(SysFunction sysFunction) {
        return mapper.update(sysFunction) == 1;
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
        return mapper.deleteById(id) == 1;
    }

    /**
     * 根据主键查询单条数据
     *
     * @param id 主键
     * @return SysFunction
     * @author Juyss
     */
    @Override
    public SysFunction findOne(Long id) {
        return mapper.findById(id).orElse(null);
    }

    /**
     * 条件查询并分页
     *
     * @param columnMap 条件
     * @param pageable  分页参数
     * @return Page<SysFunction>
     * @author Juyss
     */
    @Override
    public Page<SysFunction> findAll(Map<String, Object> columnMap, Pageable pageable) {
        QueryWrapper<SysFunction> wrapper = new QueryWrapper<>();
        wrapper.allEq(columnMap);
        return mapper.findAll(wrapper, pageable);
    }

    @Override
    public boolean deleteProcessFunction(Long id) {
        //根据id查询sysFunction
        SysFunction sysFunction = mapper.findById(id).
                orElseThrow(() -> new IllegalArgumentException("需要修改的数据不存在"));
        mapper.deleteById(id);
        Long userId = SecurityUtils.getUserId();
        Optional<SysGroup> byId = sysGroupDao.findById(sysFunction.getGroupId());
        SysGroup sysGroup = byId.orElseThrow(() -> new IllegalArgumentException("分组不存在"));
        String functionNameEn = sysFunction.getNameEn();
        try {
            Definitions processList = xmlObjectTransform.getProcessList(userId);
            List<Group> groups = processList.getGroups();
            List<Group> collect = groups.stream().filter(group -> group.getName().equals(sysGroup.getNameEn())).collect(Collectors.toList());
            Assert.isTrue(!ObjectUtils.isEmpty(collect),"prossList.xml找不到分组"+sysGroup.getNameEn());
            Group thisGroup = collect.get(0);
            List<Process> oldProcess = thisGroup.getProcesses();
            List<Process> processes = thisGroup.getProcesses().
                    stream().filter(a -> a.getName().
                    equals(functionNameEn)).
                    collect(Collectors.toList());
            Process process = processes.get(0);
            boolean remove = oldProcess.remove(process);
            groups.remove(thisGroup);
            if (remove) {
                thisGroup.setProcesses(processes);
                groups.add(thisGroup);
                processList.setGroups(groups);
                //获取文件 processList文件
                File processListPath = getProcessListPath(userId);
                Serializers.xml().serialize(processList, new FileOutputStream(processListPath), customizer -> customizer
                        .withDefaultPrettyPrinter()
                        .with(ToXmlGenerator.Feature.WRITE_XML_DECLARATION));
                return true;
            }
        } catch (FileNotFoundException e) {
            log.error("用户{}下processList.xml 文件不存在", userId);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 新增函数,来自工程
     *
     */
    @Override
    public SysFunction fromProject(FunctionDTO functionDTO) {
        Long userId; //masterID

        //获取工程对象
        Long projectId = functionDTO.getProjId();
        Optional<Project> optionalProject = projectMapper.findById(projectId);
        if (!optionalProject.isPresent()) {
            throw new IllegalArgumentException("未定义工程");
        }
        Project project = optionalProject.get();
        userId = project.getMasterId();

        //获取模块对象(暂时用不到)
//        Long moduleId = functionDTO.getModuleId();
//        Optional<Module> moduleOptional = moduleMapper.findById(moduleId);
//
//        if (!moduleOptional.isPresent()) {
//            throw new IllegalArgumentException("未定义模块");
//        }
//        Module module = moduleOptional.get();

        //获取数据表对象
        Long tableId = functionDTO.getTableId();
        Optional<TableMetadata> tableMetadataOptional = tableMetadataMapper.findById(tableId);
        if (!tableMetadataOptional.isPresent()) {
            throw new IllegalArgumentException("未定义表数据");
        }
        TableMetadata tableMetadata = tableMetadataOptional.get();

        //获取表服务(暂时用不到)
        Long tableServiceId = functionDTO.getTabServiceId();
//        Optional<TableService> tableServiceOptional = tableServiceMapper.findById(tableServiceId);
//        if (!tableServiceOptional.isPresent()) {
//            throw new IllegalArgumentException("未定义表服务");
//        }
//        TableService tableService = tableServiceOptional.get();

        //获取表服务和process关联对象
        LambdaQueryWrapper<TableServiceProcess> tableServiceProcessWrapper = new LambdaQueryWrapper<>();
        tableServiceProcessWrapper.eq(TableServiceProcess::getTableServiceId, tableServiceId);
        tableServiceProcessWrapper.eq(TableServiceProcess::getNameEn, functionDTO.getNameEn());
        Optional<TableServiceProcess> tableServiceProcessOptional = tableServiceProcessMapper.findOne(tableServiceProcessWrapper);
        if (!tableServiceProcessOptional.isPresent()) {
            throw new IllegalArgumentException("表服务和process未关联");
        }
        TableServiceProcess tableServiceProcess = tableServiceProcessOptional.get();

        //获取分组对象
        Long groupId = functionDTO.getGroupId();
        LambdaQueryWrapper<SysGroup> groupWrapper = new LambdaQueryWrapper<>();
        groupWrapper.eq(SysGroup::getId, groupId);
        Optional<SysGroup> sysGroupOptional = sysGroupDao.findOne(groupWrapper);
        if (!sysGroupOptional.isPresent()) {
            throw new IllegalArgumentException("未定义分组");
        }
        SysGroup sysGroup = sysGroupOptional.get();

        Definitions processList = null;
        Process process = null;

        try {
            //获取到 ProcessList.xml 文件对象
            processList = xmlObjectTransform.getProcessList(userId);

            File file = new File(tableServiceProcess.getFilePath());
            if (!file.exists()) {
                throw new IllegalArgumentException("processList.xml 文件不存在");
            }
            //获取到 Process 对象
            process = Serializers.xml().deserialize(new FileInputStream(file), Process.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert processList != null;
        assert process != null;
        List<Group> groups = processList.getGroups();
        for (Group group : groups) {
            //Group的name 和 SysGroup的nameEn 匹配
            if (group.getName().equals(sysGroup.getNameEn())) {
                List<Process> processes = group.getProcesses();
                processes.add(process);
                break;
            }
        }
        //****************根据用户ID获取ProcessList.xml的路径*******************/
        //构建文件路径字符串
        StringBuilder filename = new StringBuilder();

        //查询BASE_DIR
        LambdaQueryWrapper<Parameter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Parameter::getParamCode, "BASE_DIR");
        Optional<Parameter> baseDirOptional = parameterMapper.findOne(wrapper);
        if (baseDirOptional.isPresent()) {
            Parameter parameter = baseDirOptional.get();
            filename.append(parameter.getCval());
        }
        //添加用户ID
        filename.append(userId);

        wrapper.clear();
        //查询PROCESS_LIST_NAME
        wrapper.eq(Parameter::getParamCode, "PROCESS_LIST_NAME");
        Optional<Parameter> processListDirOptional = parameterMapper.findOne(wrapper);
        if (processListDirOptional.isPresent()) {
            Parameter parameter = processListDirOptional.get();
            filename.append(parameter.getCval());
        }

        //生成ProcessList.xml文件绝对路径
        String processListFilePath = String.valueOf(filename);
        //****************根据用户ID获取ProcessList.xml的路径*******************/

        try {
            xmlObjectTransform.definitions2Xml(processListFilePath, processList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //删除tableServiceProcess数据
        assert tableServiceProcess.getId() != null;
        tableServiceProcessMapper.deleteById(tableServiceProcess.getId());

        //构建函数对象，设置数据
        SysFunction sysFunction = new SysFunction();
        sysFunction.setCustId(userId);
        sysFunction.setDescription(tableServiceProcess.getDescription());
        sysFunction.setFunType(1); //工程服务
        sysFunction.setGroupId(groupId);
        sysFunction.setHttpModel(HttpQueryTypeEnum.getCodeByName(process.getHttpMode()));
        sysFunction.setName(tableServiceProcess.getName());
        sysFunction.setNameEn(tableServiceProcess.getNameEn());
        sysFunction.setProjId(projectId);
        sysFunction.setTabServiceId(tableServiceId);
        sysFunction.setServiceNs(project.getNamespace());
        sysFunction.setStatus(1);
        return sysFunction;
    }

    /**
     * 新增函数来自用户自定义
     *
     * @return SysFunction
     * @author Juyss
     */
    @Override
    public SysFunction fromUserDefine(JavaFunctionDTO javaFunctionDTO) {

        Long userId = javaFunctionDTO.getUserId();

        //获取分组对象
        Long groupId = javaFunctionDTO.getGroupId();
        LambdaQueryWrapper<SysGroup> groupWrapper = new LambdaQueryWrapper<>();
        groupWrapper.eq(SysGroup::getId, groupId);
        Optional<SysGroup> sysGroupOptional = sysGroupDao.findOne(groupWrapper);
        if (!sysGroupOptional.isPresent()) {
            throw new IllegalArgumentException("未定义分组");
        }
        SysGroup sysGroup = sysGroupOptional.get();

        //创建函数对象
        Process process = new Process();
        process.setInputs(javaFunctionDTO.getInputs());
        process.setOutputs(javaFunctionDTO.getOutputs());
        process.setName(javaFunctionDTO.getNameEn());
        process.setHttpMode(HttpQueryTypeEnum.getNameByCode(javaFunctionDTO.getHttpModel()));
        process.setTitle(javaFunctionDTO.getName());

        Definitions processList = null;

        //获取到 ProcessList.xml 文件对象
        processList = xmlObjectTransform.getProcessList(userId);


        assert processList != null;

        //获取分组集合
        List<Group> groups = processList.getGroups();
        Optional<Group> optional = groups.stream().filter(a -> a.getName().equals(sysGroup.getNameEn())).findAny();
        List<Process> processes;
        if (optional.isPresent()) {
            //已经存在分组的情况,直接添加
            processes = optional.get().getProcesses();
            processes.add(process);
        } else {
            //不存在分组的情况,添加新的分组并将process添加进去
            Group newGroup = new Group();
            newGroup.setName(sysGroup.getNameEn());
            newGroup.setTitle(sysGroup.getName());

            processes = new ArrayList<>();
            processes.add(process);
            newGroup.setProcesses(processes);

            groups.add(newGroup);
        }

        //****************根据用户ID获取ProcessList.xml的路径*******************/
        //构建文件路径字符串
        StringBuilder filename = new StringBuilder();

        //查询BASE_DIR
        LambdaQueryWrapper<Parameter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Parameter::getParamCode, "BASE_DIR");
        Optional<Parameter> baseDirOptional = parameterMapper.findOne(wrapper);
        if (baseDirOptional.isPresent()) {
            Parameter parameter = baseDirOptional.get();
            filename.append(parameter.getCval());
        }
        //添加用户ID
        filename.append(userId);

        wrapper.clear();
        //查询PROCESS_LIST_NAME
        wrapper.eq(Parameter::getParamCode, "PROCESS_LIST_NAME");
        Optional<Parameter> processListDirOptional = parameterMapper.findOne(wrapper);
        if (processListDirOptional.isPresent()) {
            Parameter parameter = processListDirOptional.get();
            filename.append(parameter.getCval());
        }

        //生成ProcessList.xml文件绝对路径
        String processListFilePath = String.valueOf(filename);
        //****************根据用户ID获取ProcessList.xml的路径*******************/

        try {
            xmlObjectTransform.definitions2Xml(processListFilePath, processList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //构建函数对象，设置数据
        SysFunction sysFunction = new SysFunction();
        sysFunction.setCustId(userId);
        sysFunction.setDescription(javaFunctionDTO.getDescription());
        sysFunction.setFunType(2); //工程服务
        sysFunction.setGroupId(groupId);
        sysFunction.setName(javaFunctionDTO.getName());
        sysFunction.setNameEn(javaFunctionDTO.getNameEn());
        sysFunction.setHttpModel(javaFunctionDTO.getHttpModel());
        sysFunction.setServiceNs(javaFunctionDTO.getServiceNs());
        sysFunction.setStatus(1);
        return sysFunction;
    }

    @Override
    public SysFunction getFunction(Long id) {
        Optional<SysFunction> byId = mapper.findById(id);
        SysFunction sysFunction = byId.orElseThrow(() -> new IllegalArgumentException("函数不存在"));
        //解析xml
        Definitions processList = xmlObjectTransform.getProcessList(SecurityUtils.getRequiredUserId());
        List<Group> groups = processList.getGroups();
        Optional<Process> any = null;
        for (Group group : groups) {
            any = group.getProcesses().stream().filter(a -> a.getName().equals(sysFunction.getNameEn())).findAny();
            if (any.isPresent()) {
                break;
            }
        }
        Process process = any.orElseThrow(()->new IllegalArgumentException("函数名不存在"));
        sysFunction.setProcess(process);
        return sysFunction;
    }

    @Override
    public boolean updateFunction(JavaFunctionDTO javaFunctionDTO) {
        Assert.isTrue(!ObjectUtils.isEmpty(javaFunctionDTO), "参数不正确");
        //获取当前用户的
        LambdaQueryWrapper<SysFunction> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysFunction::getNameEn, javaFunctionDTO.getNameEn());
        List<SysFunction> all = mapper.findAll(lambdaQueryWrapper);
        Assert.isTrue(!ObjectUtils.isEmpty(all), "函数名不存在");
        SysFunction sysFunction = all.get(0);
        boolean b = deleteProcessFunction(sysFunction.getId());
        if (b) {
            SysFunction newFunction = fromUserDefine(javaFunctionDTO);
            newFunction.setId(sysFunction.getId());
            return mapper.update(newFunction) > 0;

        }
        return false;
    }

    public File getProcessListPath(Long userId) {
        //构建文件路径字符串
        StringBuilder filename = new StringBuilder();

        //查询BASE_DIR
        LambdaQueryWrapper<Parameter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Parameter::getParamCode, "BASE_DIR");
        Optional<Parameter> baseDirOptional = parameterMapper.findOne(wrapper);
        if (baseDirOptional.isPresent()) {
            Parameter parameter = baseDirOptional.get();
            filename.append(parameter.getCval());
        }
        //添加用户ID
        filename.append(userId);
        wrapper.clear();
        //查询PROCESS_LIST_DIR
        wrapper.eq(Parameter::getParamCode, "PROCESS_LIST_NAME");
        Optional<Parameter> processListDirOptional = parameterMapper.findOne(wrapper);
        if (processListDirOptional.isPresent()) {
            Parameter parameter = processListDirOptional.get();
            filename.append(parameter.getCval());
        }
        //生成ProcessList.xml文件绝对路径
        String filePath = String.valueOf(filename);
        File file = new File(filePath);
        return file;
    }

}
