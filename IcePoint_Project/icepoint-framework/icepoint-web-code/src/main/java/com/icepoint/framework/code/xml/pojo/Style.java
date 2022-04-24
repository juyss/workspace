package com.icepoint.framework.code.xml.pojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-18 15:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Style {

    @JacksonXmlProperty(isAttribute = true)
    private int lineWidth;

    @JacksonXmlProperty(isAttribute = true)
    private String stroke;

}
