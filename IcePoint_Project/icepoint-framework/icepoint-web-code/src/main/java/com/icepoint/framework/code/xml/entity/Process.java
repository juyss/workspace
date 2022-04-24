package com.icepoint.framework.code.xml.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * xml根节点实体类
 *
 * @author Juyss
 * @version 1.0
 * @since 2021-06-09 9:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Process {

    @JacksonXmlProperty(isAttribute = true, localName = "abstract") //定义是否为标签属性及标签别名
    private String abstracts;

    @JacksonXmlProperty(isAttribute = true)
    private String controller;

    @JacksonXmlProperty(isAttribute = true)
    private String httpMode;

    @JacksonXmlProperty(isAttribute = true)
    private String module;

    @JacksonXmlProperty(isAttribute = true)
    private String moduleAlias;

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private String picture;

    @JacksonXmlProperty(isAttribute = true)
    private String service;

    @JacksonXmlProperty(isAttribute = true)
    private String title;

    @JacksonXmlProperty(isAttribute = true)
    private String type;

    @JacksonXmlProperty(localName = "input") //定义子节点标签名
    @JacksonXmlElementWrapper(localName = "inputs") //定义此节点为集合，同时设置标签名
    private List<Input> inputs;

    @JacksonXmlProperty(localName = "output")
    @JacksonXmlElementWrapper(localName = "outputs")
    private List<Output> outputs;

}
