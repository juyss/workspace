package com.github.tangyi.webcast.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * httpclient的操作实现类
 */
public class HttpClientUtil {

    private static final String QUESTION_MARK = "?";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
    public static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";

    private final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
            .setConnectionRequestTimeout(10000).build();

    private static HttpClientUtil instance = null;

    private HttpClient ignoreSslClient;

    private HttpClient client;

    private HttpClientUtil() {
        this.ignoreSslClient = createHttpsClient(true);
        this.client = createHttpsClient(false);
    }

    public static HttpClientUtil getInstance() {
        if (instance == null) {
            instance = new HttpClientUtil();
        }
        return instance;
    }

    private HttpClient getHttpClient(boolean ignoreSsl) {
        return ignoreSsl ? ignoreSslClient : client;
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @return 请求结果
     */
    public String sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:json格式的内容)
     * @param headers 请求头
     * @return 请求结果
     */
    public String sendHttpPostJson(String httpUrl, String params, Map<String, String> headers) {
        return sendHttpPostJson(httpUrl, params, headers, false);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl   地址
     * @param params    参数(格式:json格式的内容)
     * @param headers   请求头
     * @param ignoreSsl 是否跳过证书校验，true为跳过 false为不跳过
     * @return 请求结果
     */
    public String sendHttpPostJson(String httpUrl, String params, Map<String, String> headers, boolean ignoreSsl) {
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, DEFAULT_ENCODING);
            stringEntity.setContentType(APPLICATION_JSON_UTF8_VALUE);
            httpPost.setEntity(stringEntity);
            if (null != headers && headers.size() > 0) {
                for (String name : headers.keySet()) {
                    httpPost.addHeader(name, headers.get(name));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sendHttpRequest(httpPost, ignoreSsl);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param maps    参数
     * @param body    请求体
     * @return 请求结果
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps, String body) {
        String paramStr = PolyvTool.mapJoinNotEncode(maps);
        if (null != paramStr) {
            paramStr = QUESTION_MARK + paramStr;
        }
        String urlStr = httpUrl + paramStr;
        HttpPost httpPost = new HttpPost(urlStr);
        try {
            StringEntity entity = new StringEntity(body, DEFAULT_ENCODING);
            entity.setContentType(APPLICATION_JSON_UTF8_VALUE);
            httpPost.setEntity(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sendHttpRequest(httpPost, true);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:key1=value1&key2=value2)
     * @return 请求结果
     */
    public String sendHttpPost(String httpUrl, String params) {
        return sendHttpPostWithHeader(httpUrl, params, null);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param headers 头
     * @param params  参数(格式:key1=value1&key2=value2)
     * @return 请求结果
     */
    public String sendHttpPostWithHeader(String httpUrl, String params, Header[] headers) {
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            if (null != headers) {
                httpPost.setHeaders(headers);
            }
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, DEFAULT_ENCODING);
            stringEntity.setContentType(APPLICATION_FORM_URLENCODED_VALUE);
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param maps    参数
     * @return 请求结果
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps) {
        return sendHttpPostWithHeader(httpUrl, maps, null);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param maps    参数
     * @return 请求结果
     */
    public String sendHttpPostWithHeader(String httpUrl, Map<String, String> maps, Header[] headers) {
        HttpPost httpPost = new HttpPost(httpUrl);
        if (null != headers) {
            httpPost.setHeaders(headers);
        }
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, DEFAULT_ENCODING));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl 地址
     * @param headers 请求头
     * @return 请求结果
     */
    public String sendHttpGet(String httpUrl, Map<String, String> headers) {
        // 创建httpGet
        HttpGet httpGet = new HttpGet(httpUrl);
        try {
            if (null != headers && headers.size() > 0) {
                for (String name : headers.keySet()) {
                    httpGet.addHeader(name, headers.get(name));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sendHttpGet(httpGet);
    }

    /**
     * 发送 post请求（带文件）
     *
     * @param httpUrl 地址
     * @param maps    参数
     * @param files   附件
     * @return 请求结果
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> files, String fileParamName) {
        HttpPost httpPost = new HttpPost(httpUrl);
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        // 设置此参数后可以保证服务器拿到的文件名不会出现乱码
        meBuilder.setMode(HttpMultipartMode.RFC6532);
        for (String key : maps.keySet()) {
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
        }
        for (File file : files) {
            FileBody fileBody = new FileBody(file);
            fileParamName = (null == fileParamName) ? "files" : fileParamName;
            meBuilder.addPart(fileParamName, fileBody);
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求（带文件）
     *
     * @param httpUrl       地址
     * @param maps          参数
     * @param file          上传文件
     * @param fileParamName 上传文件参数名
     */
    private static String sendHttpPost(String httpUrl, Map<String, String> maps, File file, String fileParamName) {
        fileParamName = (null == fileParamName) ? "file" : fileParamName;
        return sendHttpPost(httpUrl, maps, file, fileParamName);
    }

    /**
     * 发送Post请求
     *
     * @param httpPost post请求
     * @return 请求结果
     */
    private String sendHttpPost(HttpPost httpPost) {
        return sendHttpRequest(httpPost);
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl 请求的路径
     * @return 成功时为响应内容，失败时为 null
     */
    public String sendHttpGet(String httpUrl) {
        // 创建get请求
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet);
    }

    /**
     * 发送DELETE请求
     *
     * @param httpUrl 请求的路径
     * @return 成功时为响应内容，失败时为 null
     */
    public String sendHttpDelete(String httpUrl) {
        HttpDelete httpDelete = new HttpDelete(httpUrl);
        return sendHttpRequest(httpDelete);
    }

    /**
     * 发送 get请求Https
     *
     * @param httpUrl        请求的路径
     * @param ignoreSslCerts 是否忽略证书校验
     */
    public String sendHttpsGet(String httpUrl, boolean ignoreSslCerts) {
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpsGet(httpGet, ignoreSslCerts);
    }

    /**
     * 发送Get请求
     *
     * @param httpGet 请求
     * @return 成功时为响应内容，失败时为 null
     */
    private String sendHttpGet(HttpGet httpGet) {
        return sendHttpRequest(httpGet);
    }

    /**
     * 发送Get请求
     *
     * @param requestBase 请求
     * @return 成功时为响应内容，失败时为 null
     */
    private String sendHttpRequest(HttpRequestBase requestBase) {
        return sendHttpRequest(requestBase, false);
    }

    /**
     * 发送请求
     *
     * @param requestBase    请求
     * @param ignoreSslCerts 忽略证书和域名校验
     * @return 成功时为响应内容，失败时为 null
     */
    private String sendHttpRequest(HttpRequestBase requestBase, boolean ignoreSslCerts) {
        HttpResponse response = null;
        HttpEntity entity;
        try {
            // 创建默认的httpClient实例.
            requestBase.setConfig(requestConfig);
            // 执行请求
            response = getHttpClient(ignoreSslCerts).execute(requestBase);
            entity = response.getEntity();
            return EntityUtils.toString(entity, DEFAULT_ENCODING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
        return null;
    }

    /**
     * 发送Get请求Https
     * ignoreSslCerts参数为true可以忽略证书和域名的校验，可以避免 {@link sun.security.validator.ValidatorException}
     *
     * @param httpGet        使用https发送get请求
     * @param ignoreSslCerts 忽略证书和域名校验
     * @return 返回内容
     */
    private String sendHttpsGet(HttpGet httpGet, boolean ignoreSslCerts) {
        HttpResponse response = null;
        String responseContent = null;
        try {
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = getHttpClient(ignoreSslCerts).execute(httpGet);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, DEFAULT_ENCODING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
        return responseContent;
    }

    /**
     * 初始化https请求Client
     *
     * @param ignoreSslCerts 忽略证书
     */
    private CloseableHttpClient createHttpsClient(boolean ignoreSslCerts) {
        CloseableHttpClient httpClient;
        if (ignoreSslCerts) {
            SSLContext ctx = null;
            try {
                // 忽略客户端证书校验
                ctx = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
                System.out.println("create ssl context error");
            }

            // 忽略证书和hostName校验
            httpClient = HttpClients.custom()
                    .setSSLHostnameVerifier((host, session) -> true)
                    .setSSLSocketFactory(new SSLConnectionSocketFactory(ctx)).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }
}


