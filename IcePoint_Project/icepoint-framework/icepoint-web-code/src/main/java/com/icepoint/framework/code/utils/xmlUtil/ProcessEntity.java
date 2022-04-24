package com.icepoint.framework.code.utils.xmlUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ck
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessEntity {

    private String abstracted;

    private String controller;

    private String httpMode;

    private String module;

    private String moduleAlias;

    private String name;

    private String service;

    private String title;

    private List<InputEntity> inPuts;

    private OutPutsEntity outPutsEntity;


}
