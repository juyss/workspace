/*
 * smart-doc https://github.com/shalousun/smart-doc
 *
 * Copyright (C) 2018-2021 smart-doc
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.icepoint.framework.restdoc.builder;

import com.google.gson.Gson;
import com.icepoint.framework.restdoc.constants.TornaConstants;
import com.icepoint.framework.restdoc.factory.BuildTemplateFactory;
import com.icepoint.framework.restdoc.model.ApiConfig;
import com.icepoint.framework.restdoc.model.ApiDoc;
import com.icepoint.framework.restdoc.model.torna.Apis;
import com.icepoint.framework.restdoc.model.torna.TornaApi;
import com.icepoint.framework.restdoc.model.torna.TornaDic;
import com.icepoint.framework.restdoc.template.IDocBuildTemplate;
import com.icepoint.framework.restdoc.util.DocUtil;
import com.icepoint.framework.restdoc.util.TornaUtil;
import com.power.common.util.CollectionUtil;
import com.power.common.util.OkHttp3Util;
import com.power.common.util.StringUtil;
import com.thoughtworks.qdox.JavaProjectBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.icepoint.framework.restdoc.util.TornaUtil.buildApis;
import static com.icepoint.framework.restdoc.util.TornaUtil.buildErrorCode;


/**
 * @author xingzi 2021/2/2 18:05
 **/
public class TornaBuilder {

    /**
     * build controller api
     *
     * @param config config
     */
    public static void buildApiDoc(ApiConfig config) {
        JavaProjectBuilder javaProjectBuilder = new JavaProjectBuilder();
        buildApiDoc(config, javaProjectBuilder);
    }


    /**
     * Only for smart-doc maven plugin and gradle plugin.
     *
     * @param config             ApiConfig
     * @param javaProjectBuilder ProjectDocConfigBuilder
     */
    public static void buildApiDoc(ApiConfig config, JavaProjectBuilder javaProjectBuilder) {
        config.setParamsDataToTree(true);
        DocBuilderTemplate builderTemplate = new DocBuilderTemplate();
        builderTemplate.checkAndInit(config);
        ProjectDocConfigBuilder configBuilder = new ProjectDocConfigBuilder(config, javaProjectBuilder);
        IDocBuildTemplate docBuildTemplate = BuildTemplateFactory.getDocBuildTemplate(config.getFramework());
        List<ApiDoc> apiDocList = docBuildTemplate.getApiData(configBuilder);
        buildTorna(apiDocList, config, javaProjectBuilder);
    }

    /**
     * build torna Data
     *
     * @param apiDocs   apiData
     * @param apiConfig ApiConfig
     * @param builder   JavaProjectBuilder
     */
    public static void buildTorna(List<ApiDoc> apiDocs, ApiConfig apiConfig, JavaProjectBuilder builder) {
        TornaApi tornaApi = new TornaApi();
        tornaApi.setAuthor(StringUtil.isEmpty(apiConfig.getAuthor()) ? System.getProperty("user.name") : apiConfig.getAuthor());
        Apis api;
        List<Apis> apisList = new ArrayList<>();
        //添加接口数据
        for (ApiDoc a : apiDocs) {
            api = new Apis();
            api.setName(StringUtils.isBlank(a.getDesc()) ? a.getName() : a.getDesc());
            api.setItems(buildApis(a.getList(), TornaUtil.setDebugEnv(apiConfig, tornaApi)));
            api.setIsFolder(TornaConstants.YES);
            api.setAuthor(a.getAuthor());
            api.setOrderIndex(a.getOrder());
            apisList.add(api);
        }
        tornaApi.setCommonErrorCodes(buildErrorCode(apiConfig));
        tornaApi.setApis(apisList);
        //推送文档信息
        Map<String, String> requestJson = TornaConstants.buildParams(TornaConstants.PUSH, new Gson().toJson(tornaApi), apiConfig);
        //推送字典信息
        Map<String, Object> dicMap = new HashMap<>(2);
        List<TornaDic> docDicts = TornaUtil.buildTornaDic(DocUtil.buildDictionary(apiConfig, builder));
        if (CollectionUtil.isNotEmpty(docDicts)) {
            dicMap.put("enums", docDicts);
            Map<String, String> dicRequestJson = TornaConstants.buildParams(TornaConstants.ENUM_PUSH, new Gson().toJson(dicMap), apiConfig);
            String dicResponseMsg = OkHttp3Util.syncPostJson(apiConfig.getOpenUrl(), new Gson().toJson(dicRequestJson));
            TornaUtil.printDebugInfo(apiConfig, dicResponseMsg, dicRequestJson, TornaConstants.ENUM_PUSH);
        }
        //获取返回结果
        String responseMsg = OkHttp3Util.syncPostJson(apiConfig.getOpenUrl(), new Gson().toJson(requestJson));

        //开启调试时打印请求信息
        TornaUtil.printDebugInfo(apiConfig, responseMsg, requestJson, TornaConstants.PUSH);

    }
}

