package com.icepoint.framework.core;

import com.icepoint.framework.core.support.JsonAnyProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestUser extends JsonAnyProperties<Object> {
    private String userName;
    private Integer num;


}
