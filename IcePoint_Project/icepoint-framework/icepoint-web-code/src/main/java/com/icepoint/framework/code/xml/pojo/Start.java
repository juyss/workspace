package com.icepoint.framework.code.xml.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-18 15:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Start {

    @JacksonXmlProperty(isAttribute = true)
    private double x;

    @JacksonXmlProperty(isAttribute = true)
    private double y;
}
