package com.github.tangyi.core.common.context;


import com.github.tangyi.core.common.util.CollectionUtils;
import com.github.tangyi.core.common.util.LogUtils;
import com.github.tangyi.core.common.web.util.RequestUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

/**
 * 应用上下文
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public class ApplicationContext {

    /**
     * ApplicationContext
     */
    protected static org.springframework.context.ApplicationContext applicationContext = null;

    /**
     * ServletContext
     */
    protected static ServletContext servletContext = null;

    /**
     * 获取ApplicationContext
     *
     * @return
     */
    public static org.springframework.context.ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 设置ApplicationContext
     *
     * @param applicationContext
     */
    public static void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) {
        ApplicationContext.applicationContext = applicationContext;
    }

    /**
     * 获取ServletContext
     *
     * @return
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * 设置ServletContext
     *
     * @param servletContext
     */
    public static void setServletContext(ServletContext servletContext) {
        ApplicationContext.servletContext = servletContext;
    }

    /**
     * 获取Bean
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clz) {
        try {
            return getApplicationContext().getBean(clz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取Bean
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        try {
            return (T) getApplicationContext().getBean(name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取Bean
     *
     * @param name
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clz) {
        try {
            return getApplicationContext().getBean(name, clz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取多个bean
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeanMap(Class<T> type) {
        try {
            return getApplicationContext().getBeansOfType(type);
        } catch (Exception e) {
            LogUtils.error(e);
            return CollectionUtils.emptyMap();
        }
    }

    /**
     * 获取多个bean
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> getBeanList(Class<T> type) {
        return new ArrayList<>(getBeanMap(type).values());
    }

    /**
     * 发布事件
     *
     * @param event
     */
    public static void publishEvent(ApplicationEvent event) {
        try {
            getApplicationContext().publishEvent(event);
        } catch (Exception e) {
            LogUtils.error(e);
        }
    }

    /**
     * 获取请求
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取请求返回
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取请求的ip地址
     *
     * @return
     */
    public static String getRequestIp() {
        return RequestUtils.getIp(getRequest());
    }

    /**
     * 获取请求用户locale
     *
     * @return
     */
    public static Locale getRequestLocale() {
        return RequestContextUtils.getLocale(getRequest());
    }

    /**
     * 国际化消息内容
     *
     * @param message
     * @param args
     * @return
     */
    public static String getMessage(String message, Object... args) {
        try {
            org.springframework.context.ApplicationContext context = getApplicationContext();
            return context != null ? context.getMessage(message, args, message, getRequestLocale()) : message;
        } catch (Exception e) {
            return message;
        }
    }

}
