package com.icepoint.framework.code.sysfunservice.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.icepoint.framework.code.sysfunservice.dao.SysFunServiceDao;
import com.icepoint.framework.code.sysfunservice.entity.SysFunService;
import com.icepoint.framework.code.sysfunservice.service.SysFunServiceService;
import com.icepoint.framework.code.utils.create.TestCaseTemplate;
import com.icepoint.framework.code.xml.entity.Definitions;
import com.icepoint.framework.code.xml.pojo.FunctionFlow;
import com.icepoint.framework.code.xml.service.XmlObjectTransform;
import com.icepoint.framework.core.io.Serializers;
import com.icepoint.framework.web.security.util.SecurityUtils;
import com.icepoint.framework.web.system.dao.ParameterMapper;
import com.icepoint.framework.web.system.entity.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * (SysFunService)表服务实现类
 *
 * @author ck
 * @since 2021-06-04 17:44:23
 */
@Service("sysFunServiceService")
public class SysFunServiceServiceImpl implements SysFunServiceService {
    @Resource
    private SysFunServiceDao mapper;

    @Resource
    private ParameterMapper parameterMapper;

    @Resource
    private XmlObjectTransform xmlObjectTransform;


    /**
     * 分页查询
     * @param queryWrapper 查询参数
     * @param pageable 分页参数
     */
    @Override
    public Page<SysFunService> pageList(QueryWrapper<SysFunService> queryWrapper, Pageable pageable) {
        return mapper.findAll(queryWrapper, pageable);
    }

    /**
     * id查询单条数据
     * @param id 主键
     */
    @Override
    public SysFunService getById(Long id) {
        LambdaQueryWrapper<SysFunService> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysFunService::getId,id);
        return mapper.findOne(queryWrapper).orElse(null);
    }

    /**
     * 新增实体
     * @param sysFunService 实体对象
     */
    @Override
    public Integer save(SysFunService sysFunService) {
        return mapper.insert(sysFunService);
    }

    /**
     * 根据id修改实体属性
     * @param sysFunService 实体对象
     */
    @Override
    public Integer updateById(SysFunService sysFunService) {
        return mapper.update(sysFunService);
    }

    /**
     * 删除函数服务
     *
     * @param id 主键
     * @return Integer
     * @author Juyss
     */
    @Override
    public Integer delete(Long id) {
        return mapper.deleteById(id);
    }

    /**
     * 获取processList.xml
     *
     * @return Definitions
     * @author Juyss
     */
    @Override
    public Definitions getProcessList() {
        Long userId = SecurityUtils.getUserId();
        return xmlObjectTransform.getProcessList(userId);
    }

    /**
     * 保存函数流程图
     *
     * @param functionFlow 实体对象
     * @param id 主键(用作xml文件名)
     * @return Boolean
     * @author Juyss
     */
    @Override
    public Boolean putFunctionFlow(FunctionFlow functionFlow, Long id) {

        //****************根据用户ID获取流程图xml文件的路径*******************/
        //构建文件路径字符串
        StringBuilder filename = new StringBuilder();

        //查询BASE_DIR
        LambdaQueryWrapper<Parameter> tParameterWrapper = new LambdaQueryWrapper<>();
        tParameterWrapper.eq(Parameter::getParamCode, "BASE_DIR");
        Optional<Parameter> baseDirOptional = parameterMapper.findOne(tParameterWrapper);
        if (baseDirOptional.isPresent()) {
            Parameter parameter = baseDirOptional.get();
            filename.append(parameter.getCval());
        }
        //添加用户ID
        filename.append(SecurityUtils.getUserId());

        tParameterWrapper.clear();
        //查询FUNCTION_SERVICE_DIR
        tParameterWrapper.eq(Parameter::getParamCode, "FUNCTION_SERVICE_DIR");
        Optional<Parameter> processListDirOptional = parameterMapper.findOne(tParameterWrapper);
        if (processListDirOptional.isPresent()) {
            Parameter parameter = processListDirOptional.get();
            filename.append(parameter.getCval());
        }
        filename.append(id);
        filename.append(".xml");
        //生成ProcessList.xml文件绝对路径
        String functionFlowFilePath = String.valueOf(filename);
        //****************根据用户ID获取ProcessList.xml的路径*******************/

        //对象转为json字符串
        String jsonFlow = Serializers.json().serializeAsString(functionFlow);
        //查数据库
        Optional<SysFunService> sysFunServiceOptional = mapper.findById(id);

        //数据不存在时报错
        SysFunService sysFunService = sysFunServiceOptional.orElseThrow(() -> new IllegalArgumentException("id不存在"));

        //设置值
        sysFunService.setJsonFlow(jsonFlow);
        sysFunService.setFilePath(functionFlowFilePath);
        //更新数据
        mapper.update(sysFunService);

        File file = TestCaseTemplate.creatFile(functionFlowFilePath);

        try(FileOutputStream outputStream = new FileOutputStream(file)) {
            Serializers.xml().serialize(functionFlow, outputStream, customizer -> customizer
                    .withDefaultPrettyPrinter()
                    .with(ToXmlGenerator.Feature.WRITE_XML_DECLARATION));
        } catch (IOException e) {
            throw new IllegalArgumentException("文件不存在", e);
        }

        return null;
    }

    /**
     * 读取函数流程
     *
     * @param id 数据主键
     * @return SysFunService
     * @author Juyss
     */
    @Override
    public FunctionFlow getFunctionFlow(Long id) {
        Optional<SysFunService> sysFunServiceOptional = mapper.findById(id);

        SysFunService sysFunService = sysFunServiceOptional.orElseThrow(() -> new IllegalArgumentException("数据不存在"));

        String filePath = sysFunService.getFilePath();

        if (StringUtils.isEmpty(filePath)){
            TestCaseTemplate.creatFile(filePath);
            return null;
        }

        FunctionFlow functionFlow;
        File file = new File(filePath);
        try (FileInputStream fileInputStream=new FileInputStream(file)){
            functionFlow = Serializers.xml().deserialize(fileInputStream, FunctionFlow.class);
        }catch (IOException e){
            throw new IllegalArgumentException("文件不存在",e);
        }

        return functionFlow;
    }

    /**
     * 保存函数流程图
     *
     * @param json 函数流程图JSON文件
     * @param id   主键
     * @return Boolean
     * @author Juyss
     */
    @Override
    public Boolean saveFunctionFlow(String json, Long id) {

        //****************根据用户ID获取流程图xml文件的路径*******************/
        //构建文件路径字符串
        StringBuilder filename = new StringBuilder();

        //查询BASE_DIR
        LambdaQueryWrapper<Parameter> tParameterWrapper = new LambdaQueryWrapper<>();
        tParameterWrapper.eq(Parameter::getParamCode, "BASE_DIR");
        Optional<Parameter> baseDirOptional = parameterMapper.findOne(tParameterWrapper);
        if (baseDirOptional.isPresent()) {
            Parameter parameter = baseDirOptional.get();
            filename.append(parameter.getCval());
        }
        //添加用户ID
        filename.append(SecurityUtils.getUserId());

        tParameterWrapper.clear();
        //查询FUNCTION_SERVICE_DIR
        tParameterWrapper.eq(Parameter::getParamCode, "FUNCTION_SERVICE_DIR");
        Optional<Parameter> processListDirOptional = parameterMapper.findOne(tParameterWrapper);
        if (processListDirOptional.isPresent()) {
            Parameter tParameter = processListDirOptional.get();
            filename.append(tParameter.getCval());
        }
        filename.append(id);
        filename.append(".xml");
        //生成ProcessList.xml文件绝对路径
        String functionFlowFilePath = String.valueOf(filename);
        //****************根据用户ID获取ProcessList.xml的路径*******************/

        //查数据库
        Optional<SysFunService> sysFunServiceOptional = mapper.findById(id);

        //数据不存在时报错
        SysFunService sysFunService = sysFunServiceOptional.orElseThrow(() -> new IllegalArgumentException("id不存在"));

        //设置值
        sysFunService.setJsonFlow(json);
        sysFunService.setFilePath(functionFlowFilePath);
        //更新数据
        int update = mapper.update(sysFunService);

        return update==1;
    }

    /**
     * 读取函数流程图
     *
     * @param id 主键
     * @return String
     * @author Juyss
     */
    @Override
    public Object readFunctionFlow(Long id) {
        //查数据库
        Optional<SysFunService> sysFunServiceOptional = mapper.findById(id);

        //数据不存在时报错
        SysFunService sysFunService = sysFunServiceOptional.orElseThrow(() -> new IllegalArgumentException("id不存在"));

        //设置值
        String jsonFlow = sysFunService.getJsonFlow();
        return Serializers.json().deserialize(jsonFlow,Object.class);
    }
}
