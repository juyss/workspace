package com.icepoint.framework.code.xml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.icepoint.framework.code.xml.entity.Definitions;
import com.icepoint.framework.code.xml.entity.Process;
import com.icepoint.framework.code.xml.service.XmlObjectTransform;
import com.icepoint.framework.core.io.Serializers;
import com.icepoint.framework.web.system.dao.ParameterMapper;
import com.icepoint.framework.web.system.entity.Parameter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.Optional;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-09 12:00
 */
@Component
public class XmlObjectTransformImpl implements XmlObjectTransform {

    @Resource
    private ParameterMapper parameterMapper;

    /**
     * Process 对象转化为xml
     *
     * @param path    xml输出路径
     * @param process 实体类
     * @author Juyss
     */
    @Override
    public void process2Xml(String path, Process process) throws FileNotFoundException {
        File newFile = new File(path);

        Serializers.xml().serialize(process, new FileOutputStream(newFile), customizer -> customizer
                .withDefaultPrettyPrinter()
                .withRootName("process")
                .with(ToXmlGenerator.Feature.WRITE_XML_DECLARATION));

    }

    /**
     * Definitions对象转化为xml
     *
     * @param path        xml输出路径
     * @param definitions 实体类
     * @author Juyss
     */
    @Override
    public void definitions2Xml(String path, Definitions definitions) throws FileNotFoundException {
        File newFile = new File(path);

        Serializers.xml().serialize(definitions, new FileOutputStream(newFile), customizer -> customizer
                .withDefaultPrettyPrinter()
                .with(ToXmlGenerator.Feature.WRITE_XML_DECLARATION));

    }


    /**
     * 根据用户ID获取到ProcessList文件对象
     *
     * @param userId 用户ID
     * @return Definitions
     * @author Juyss
     */
    @Override
    public Definitions getProcessList(Long userId) {

        //构建文件路径字符串
        StringBuilder filename = new StringBuilder();

        //查询BASE_DIR
        LambdaQueryWrapper<Parameter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Parameter::getParamCode, "BASE_DIR");
        Optional<Parameter> baseDirOptional = parameterMapper.findOne(wrapper);
        if (baseDirOptional.isPresent()) {
            Parameter tParameter = baseDirOptional.get();
            filename.append(tParameter.getCval());
        }
        //添加用户ID
        filename.append(userId);

        wrapper.clear();
        //查询PROCESS_LIST_NAME
        wrapper.eq(Parameter::getParamCode, "PROCESS_LIST_NAME");
        Optional<Parameter> processListDirOptional = parameterMapper.findOne(wrapper);
        if (processListDirOptional.isPresent()) {
            Parameter tParameter = processListDirOptional.get();
            filename.append(tParameter.getCval());
        }


        //生成ProcessList.xml文件绝对路径
        String filePath = String.valueOf(filename);

        File file = new File(filePath);

        try (FileInputStream inputStream = new FileInputStream(file)) {
            return Serializers.xml().deserialize(inputStream, Definitions.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("文件不存在", e);
        }
    }

    /**
     * 根据用户ID和文件名，获取Process文件对象
     *
     * @param userId   用户ID
     * @param path     工程名加模块名 示例：project_name/module_name
     * @param fileName 文件名 示例：com.*.add.xml
     * @return Process
     * @author Juyss
     */
    @Override
    public Process getProcess(Long userId, String path, String fileName) {

        //构建文件路径字符串
        StringBuilder filename = new StringBuilder();

        //查询BASE_DIR
        LambdaQueryWrapper<Parameter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Parameter::getParamCode, "BASE_DIR");
        Optional<Parameter> baseDirOptional = parameterMapper.findOne(wrapper);
        if (baseDirOptional.isPresent()) {
            Parameter tParameter = baseDirOptional.get();
            filename.append(tParameter.getCval());
        }
        //添加用户ID
        filename.append(userId);

        //添加分隔符
        filename.append(File.separator);

        //添加模块名
        filename.append(path);

        //添加分隔符
        filename.append(File.separator);

        wrapper.clear();
        //查询PROCESS_DIR
        wrapper.eq(Parameter::getParamCode, "PROCESS_DIR");
        Optional<Parameter> processListDirOptional = parameterMapper.findOne(wrapper);
        if (processListDirOptional.isPresent()) {
            Parameter tParameter = processListDirOptional.get();
            filename.append(tParameter.getCval());
        }

        filename.append(fileName);

        //生成ProcessList.xml文件绝对路径
        String filePath = String.valueOf(filename);

        File file = new File(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return Serializers.xml().deserialize(fileInputStream, Process.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("文件不存在", e);
        }
    }

}
