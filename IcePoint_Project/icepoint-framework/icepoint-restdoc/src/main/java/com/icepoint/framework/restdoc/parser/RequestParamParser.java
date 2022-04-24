package com.icepoint.framework.restdoc.parser;

import com.icepoint.framework.restdoc.model.ApiParam;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public interface RequestParamParser {

    List<ApiParam> parse();
}
