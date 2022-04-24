package com.github.tangyi.core.common.web.util;

import com.github.tangyi.core.common.util.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求工具
 *
 * @author hedongzhou
 * @since 2018/07/24
 */
public class RequestUtils {

    /**
     * 获取流字符串
     *
     * @param request
     * @return
     */
    public static String getString(ServletRequest request) {
        try (BufferedReader reader = request.getReader()) {
            return IOUtils.toString(reader);
        } catch (Exception e) {
            LogUtils.error(e);

            return null;
        }
    }

    /**
     * 获取请求参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParameters(ServletRequest request) {
        Map<String, String[]> parameters = request.getParameterMap();

        Map<String, String> result = new HashMap<>(parameters.size());
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            result.put(entry.getKey(), StringUtils.join(entry.getValue()));
        }

        return result;
    }
    /**
     * @Author xh
     * @Description 输出json字符串
     * @Date 16:22 2019/9/20
     * @Param [request]
     * @return java.lang.String
     **/
    public static String  getParameters2(ServletRequest request) {
        Map<String, String[]> parameters = request.getParameterMap();

        String result = JsonUtils.toString(parameters);

        return result;
    }

    /**
     * 获取IP
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    LogUtils.error(e.getMessage(), e);
                }
                ip = inet.getHostAddress();
            }
        }
        return ip.indexOf(",") > -1 ? ip.substring(0, ip.indexOf(",")) : ip;
    }

    /**
     * 获取头部信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        return getHeaders(request, null);
    }

    /**
     * 获取头部信息
     *
     * @param request
     * @param includes
     * @return
     */
    public static Map<String, String> getHeaders(HttpServletRequest request,
                                                 Collection<String> includes) {
        Map<String, String> map = new HashMap<>(5);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();

            if (CheckUtils.isEmpty(includes)
                    || includes.contains(key)) {
                map.put(key, request.getHeader(key));
            }
        }
        return map;
    }

    /**
     * 获取完整的请求地址
     * @param req
     * @return
     */
    public static String getFullURL(HttpServletRequest req) {

        String scheme = req.getScheme(); // http
        String serverName = req.getServerName(); // hostname.com
        int serverPort = req.getServerPort(); // 80
        String contextPath = req.getContextPath(); // /mywebapp
        String servletPath = req.getServletPath(); // /servlet/MyServlet
        String pathInfo = req.getPathInfo(); // /a/b;c=123
        String queryString = req.getQueryString(); // d=789

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath).append(servletPath);

//        if (pathInfo != null) {
//            url.append(pathInfo);
//        }
//        if (queryString != null) {
//            url.append("?").append(queryString);
//        }
        return url.toString();
    }
}
