package com.icepoint.framework.code.xml.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-09 14:17
 */
@Data
@JacksonXmlRootElement(localName = "definitions")
public class Definitions {

    @JacksonXmlProperty(localName = "group")
    @JacksonXmlElementWrapper(localName = "groups")
    private List<Group> groups;
}
