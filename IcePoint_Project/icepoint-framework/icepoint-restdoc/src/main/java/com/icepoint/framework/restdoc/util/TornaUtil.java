package com.icepoint.framework.restdoc.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.icepoint.framework.restdoc.constants.TornaConstants;
import com.icepoint.framework.restdoc.model.*;
import com.icepoint.framework.restdoc.model.rpc.RpcApiDependency;
import com.icepoint.framework.restdoc.model.torna.*;
import com.power.common.model.EnumDictionary;
import com.power.common.util.CollectionUtil;
import com.power.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xingzi 2021/4/28 16:15
 **/
public class TornaUtil {

    public static boolean setDebugEnv(ApiConfig apiConfig, TornaApi tornaApi) {
        //是否设置测试环境
        boolean hasDebugEnv = StringUtils.isNotBlank(apiConfig.getDebugEnvName())
                &&
                StringUtils.isNotBlank(apiConfig.getDebugEnvUrl());
        //设置测试环境
        List<DebugEnv> debugEnvs = new ArrayList<>();
        if (hasDebugEnv) {
            DebugEnv debugEnv = new DebugEnv();
            debugEnv.setName(apiConfig.getDebugEnvName());
            debugEnv.setUrl(apiConfig.getDebugEnvUrl());
            debugEnvs.add(debugEnv);
        }
        tornaApi.setDebugEnvs(debugEnvs);
        return hasDebugEnv;
    }

    public static void printDebugInfo(ApiConfig apiConfig, String responseMsg, Map<String, String> requestJson,String category) {
        if (apiConfig.isTornaDebug()) {
            String sb = "配置信息列表: \n" +
                    "OpenUrl: " +
                    apiConfig.getOpenUrl() +
                    "\n" +
                    "appToken: " +
                    apiConfig.getAppToken() +
                    "\n" +
                    "appKey: " +
                    apiConfig.getAppKey() +
                    "\n" +
                    "Secret: " +
                    apiConfig.getSecret() +
                    "\n";
            System.out.println(sb);
            JsonElement element = JsonParser.parseString(responseMsg);
            TornaRequestInfo info = new TornaRequestInfo()
                    .of()
                    .setCategory(category)
                    .setCode(element.getAsJsonObject().get(TornaConstants.CODE).getAsString())
                    .setMessage(element.getAsJsonObject().get(TornaConstants.MESSAGE).getAsString())
                    .setRequestInfo(requestJson)
                    .setResponseInfo(responseMsg);
            System.out.println(info.buildInfo());
        }
    }

    /**
     * build apis
     *
     * @param apiMethodDocs apiMethodDocs
     * @param hasDebugEnv   has debug environment
     * @return List of Api
     */
    public static List<Apis> buildApis(List<ApiMethodDoc> apiMethodDocs, boolean hasDebugEnv) {
        //参数列表
        List<Apis> apis = new ArrayList<>();
        Apis methodApi;
        //遍历分类接口
        for (ApiMethodDoc apiMethodDoc : apiMethodDocs) {
            methodApi = new Apis();
            methodApi.setIsFolder(TornaConstants.NO);
            methodApi.setName(apiMethodDoc.getDesc());
            methodApi.setUrl(hasDebugEnv ? apiMethodDoc.getPath() : apiMethodDoc.getUrl());
            methodApi.setHttpMethod(apiMethodDoc.getType());
            methodApi.setContentType(apiMethodDoc.getContentType());
            methodApi.setDescription(apiMethodDoc.getDetail());
            methodApi.setIsShow(TornaConstants.YES);
            methodApi.setAuthor(apiMethodDoc.getAuthor());
            methodApi.setOrderIndex(apiMethodDoc.getOrder());

            methodApi.setHeaderParams(buildHerder(apiMethodDoc.getRequestHeaders()));
            methodApi.setResponseParams(buildParams(apiMethodDoc.getResponseParams()));
            methodApi.setIsRequestArray(apiMethodDoc.getIsRequestArray());
            methodApi.setIsResponseArray(apiMethodDoc.getIsResponseArray());
            methodApi.setRequestArrayType(apiMethodDoc.getRequestArrayType());
            methodApi.setResponseArrayType(apiMethodDoc.getResponseArrayType());
            //Path
            if (CollectionUtil.isNotEmpty(apiMethodDoc.getPathParams())) {
                methodApi.setPathParams(buildParams(apiMethodDoc.getPathParams()));
            }
            //formData
            if (CollectionUtil.isNotEmpty(apiMethodDoc.getQueryParams())) {
                methodApi.setQueryParams(buildParams(apiMethodDoc.getQueryParams()));
            }
            //Json
            if (CollectionUtil.isNotEmpty(apiMethodDoc.getRequestParams())) {
                methodApi.setRequestParams(buildParams(apiMethodDoc.getRequestParams()));
            }
            apis.add(methodApi);
        }
        return apis;
    }

    /**
     * build apis
     *
     * @param apiMethodDocs apiMethodDocs
     * @return List of Api
     */
    public static List<Apis> buildDubboApis(List<JavaMethodDoc> apiMethodDocs) {
        //参数列表
        List<Apis> apis = new ArrayList<>();
        Apis methodApi;
        //遍历分类接口
        for (JavaMethodDoc apiMethodDoc : apiMethodDocs) {
            methodApi = new Apis();
            methodApi.setIsFolder(TornaConstants.NO);
            methodApi.setName(apiMethodDoc.getDesc());
            methodApi.setDescription(apiMethodDoc.getDetail());
            methodApi.setIsShow(TornaConstants.YES);
            methodApi.setAuthor(apiMethodDoc.getAuthor());
            methodApi.setUrl(apiMethodDoc.getMethodDefinition());
            methodApi.setResponseParams(buildParams(apiMethodDoc.getResponseParams()));
            methodApi.setOrderIndex(apiMethodDoc.getOrder());
            //Json
            if (CollectionUtil.isNotEmpty(apiMethodDoc.getRequestParams())) {
                methodApi.setRequestParams(buildParams(apiMethodDoc.getRequestParams()));
            }
            apis.add(methodApi);
        }
        return apis;
    }

    /**
     * build request header
     *
     * @param apiReqParams 请求头参数列表
     * @return List of HttpParam
     */
    public static List<HttpParam> buildHerder(List<ApiReqParam> apiReqParams) {
        /*
         * name": "token",
         *                     "required": "1",
         *                     "example": "iphone12",
         *                     "description": "商品名称描述"
         */
        HttpParam httpParam;
        List<HttpParam> headers = new ArrayList<>();
        for (ApiReqParam header : apiReqParams) {
            httpParam = new HttpParam();
            httpParam.setName(header.getName());
            httpParam.setRequired(header.isRequired() ? TornaConstants.YES : TornaConstants.NO);
            httpParam.setExample(StringUtil.removeQuotes(header.getValue()));
            httpParam.setDescription(header.getDesc());
            headers.add(httpParam);
        }
        return headers;
    }

    /**
     * build  request response params
     *
     * @param apiParams 参数列表
     * @return List of HttpParam
     */
    public static List<HttpParam> buildParams(List<ApiParam> apiParams) {
        HttpParam httpParam;
        List<HttpParam> bodies = new ArrayList<>();
        /*
         *                     "name": "goodsName",
         *                     "type": "string",
         *                     "required": "1",
         *                     "maxLength": "128",
         *                     "example": "iphone12",
         *                     "description": "商品名称描述",
         *                     "parentId": "",
         *                     "enumInfo": {
         *                         "name": "支付枚举",
         *                         "description": "支付状态",
         *                         "items": [
         *                             {
         *                                 "name": "WAIT_PAY",
         *                                 "type": "string",
         *                                 "value": "0",
         *                                 "description": "未支付"
         */
        for (ApiParam apiParam : apiParams) {
            httpParam = new HttpParam();
            httpParam.setName(apiParam.getField());
            httpParam.setMaxLength(apiParam.getMaxLength());
            httpParam.setType(apiParam.getType());
            httpParam.setRequired(apiParam.isRequired() ? TornaConstants.YES : TornaConstants.NO);
            httpParam.setExample(StringUtil.removeQuotes(apiParam.getValue()));
            httpParam.setDescription(apiParam.getDesc());
            if (apiParam.getChildren() != null) {
                httpParam.setChildren(buildParams(apiParam.getChildren()));
            }
            bodies.add(httpParam);
        }
        return bodies;
    }

    public static String buildDependencies(List<RpcApiDependency> dependencies) {
        StringBuilder s = new StringBuilder();
        if (CollectionUtil.isNotEmpty(dependencies)) {
            for (RpcApiDependency r : dependencies) {
                s.append(r.toString())
                        .append("\n\n");
            }
        }
        return s.toString();
    }

    public static List<CommonErrorCode> buildErrorCode(ApiConfig config) {
        List<CommonErrorCode> commonErrorCodes = new ArrayList<>();
        CommonErrorCode commonErrorCode;
        List<ApiErrorCode> errorCodes = DocUtil.errorCodeDictToList(config);
        if (CollectionUtil.isNotEmpty(errorCodes)) {
            for (EnumDictionary code : errorCodes) {
                commonErrorCode = new CommonErrorCode();
                commonErrorCode.setCode(code.getValue());
                // commonErrorCode.setSolution(code.getDesc());
                commonErrorCode.setMsg(code.getDesc());
                commonErrorCodes.add(commonErrorCode);
            }
        }
        return commonErrorCodes;
    }

    public static List<TornaDic> buildTornaDic(List<ApiDocDict> apiDocDicts) {
        List<TornaDic> dics = new ArrayList<>();
        TornaDic tornaDic = new TornaDic();
        if (CollectionUtil.isNotEmpty(apiDocDicts)) {
            for (ApiDocDict doc : apiDocDicts) {
                tornaDic.setName(doc.getTitle())
                       // .setDescription(doc.getTitle())
                        .setItems(buildTornaDicItems(doc.getDataDictList()));
                dics.add(tornaDic);
            }
        }
        return dics;
    }

    private static List<HttpParam> buildTornaDicItems(List<DataDict> dataDicts) {
        List<HttpParam> apis = new ArrayList<>();
        HttpParam api;
        if (CollectionUtil.isNotEmpty(dataDicts)) {
            for (EnumDictionary d : dataDicts) {
                api = new HttpParam();
                api.setName(d.getName());
                api.setType(d.getType());
                api.setValue(d.getValue());
                api.setDescription(d.getDesc());

                apis.add(api);
            }
        }
        return apis;
    }
}
