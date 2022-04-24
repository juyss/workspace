package com.icepoint.framework.code.xml.service;

import com.icepoint.framework.code.xml.entity.Definitions;
import com.icepoint.framework.code.xml.entity.Process;

import java.io.FileNotFoundException;

/**
 * xml 和 JavaBean 互相转换
 *
 * @author Juyss
 * @version 1.0
 * @since 2021-06-09 11:52
 */
public interface XmlObjectTransform {

    /**
     * Process 对象转化为xml
     *
     * @param path    xml输出路径
     * @param process 实体类
     */
    void process2Xml(String path, Process process) throws FileNotFoundException;

    /**
     * Definitions对象转化为xml
     *
     * @param path        xml输出路径
     * @param definitions 实体类
     * @author Juyss
     */
    void definitions2Xml(String path, Definitions definitions) throws FileNotFoundException;

    /**
     * 根据用户ID获取到ProcessList文件对象
     *
     * @param userId 用户ID
     * @return Definitions
     * @throws IllegalArgumentException 当文件不存在时 抛出异常
     * @author Juyss
     */
    Definitions getProcessList(Long userId) throws IllegalArgumentException;

    /**
     * 根据用户ID和文件名，获取Process文件对象
     *
     * @param userId   用户ID
     * @param path     文件的部分路径
     * @param fileName 文件名
     * @return Process
     * @author Juyss
     */
    Process getProcess(Long userId, String path, String fileName);

}
