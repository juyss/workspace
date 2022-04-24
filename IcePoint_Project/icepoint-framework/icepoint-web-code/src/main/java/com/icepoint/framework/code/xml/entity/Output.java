package com.icepoint.framework.code.xml.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * xml output 节点实体
 * @author Juyss
 * @version 1.0
 * @since 2021-06-09 9:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Output {

    @JacksonXmlProperty(isAttribute = true, localName = "abstract")
    private String abstracts;

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private String option;

    @JacksonXmlProperty(isAttribute = true)
    private String title;

    @JacksonXmlProperty(isAttribute = true)
    private String type;

}
