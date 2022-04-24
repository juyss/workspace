package com.icepoint.framework.code.xml.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-18 15:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Edge {

    @JacksonXmlProperty(isAttribute = true)
    private int source;

    @JacksonXmlProperty(isAttribute = true)
    private int target;

    @JacksonXmlProperty(isAttribute = true)
    private String shape;

    @JacksonXmlProperty(isAttribute = true)
    private String type;

    @JacksonXmlElementWrapper(localName = "style")
    private Style style;

    @JacksonXmlElementWrapper(localName = "startPoint")
    private StartPoint startPoint;

    @JacksonXmlElementWrapper(localName = "endPoint")
    private EndPoint endPoint;

    @JacksonXmlElementWrapper(localName = "start")
    private Start start;

    @JacksonXmlElementWrapper(localName = "end")
    private End end;

}
