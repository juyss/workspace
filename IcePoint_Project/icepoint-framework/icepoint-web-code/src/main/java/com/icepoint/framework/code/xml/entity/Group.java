package com.icepoint.framework.code.xml.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-09 14:19
 */
@Data
public class Group {

    @JacksonXmlProperty(isAttribute = true, localName = "abstract")
    private String abstracts;

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private String title;

    @JacksonXmlElementWrapper(localName = "process", useWrapping = false)
    @JacksonXmlProperty(localName = "process")
    private List<Process> processes;
}
