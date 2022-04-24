package com.icepoint.framework.code.utils.xmlUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OutPutsEntity {
    private String list;
    private String name;
    private List<OutPutEntity> outPutEntities;
}
