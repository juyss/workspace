package com.icepoint.framework.code.xml.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-18 15:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "flow")
public class FunctionFlow {

    @JacksonXmlProperty(localName = "node")
    @JacksonXmlElementWrapper(localName = "nodes")
    private List<Node> nodes;

    @JacksonXmlProperty(localName = "edge")
    @JacksonXmlElementWrapper(localName = "edges")
    private List<Edge> edges;

}
