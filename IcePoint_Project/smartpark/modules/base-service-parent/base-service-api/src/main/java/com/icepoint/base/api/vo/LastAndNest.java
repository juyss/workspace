package com.icepoint.base.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastAndNest {

    private String lastId;
    private String lastTitle = "没有了";
    private String lastDate;
    private String nestId;
    private String nestTitle = "没有了";
    private String nestDate;

}
