package com.icepoint.framework.code.xml.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-18 15:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node {

    @JacksonXmlProperty(isAttribute = true)
    private int id;

    @JacksonXmlProperty(isAttribute = true)
    private String label;

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private int offsetX;

    @JacksonXmlProperty(isAttribute = true)
    private int offsetY;

    @JacksonXmlProperty(localName = "inPoint")
    @JacksonXmlElementWrapper(localName = "inPoints")
    private List<Double> inPoints;

    @JacksonXmlProperty(localName = "outPoint")
    @JacksonXmlElementWrapper(localName = "outPoints")
    private List<Double> outPoints;

    @JacksonXmlProperty(isAttribute = true)
    private Boolean isDoingEnd;

    @JacksonXmlProperty(isAttribute = true)
    private String type;

    @JacksonXmlProperty(isAttribute = true)
    private String shape;

    @JacksonXmlProperty(localName = "size")
    @JacksonXmlElementWrapper(localName = "sizes")
    private List<String> size;

    @JacksonXmlProperty(isAttribute = true)
    private int x;

    @JacksonXmlProperty(isAttribute = true)
    private int y;

}
