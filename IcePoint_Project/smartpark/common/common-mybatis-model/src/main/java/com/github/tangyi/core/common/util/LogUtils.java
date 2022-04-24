package com.github.tangyi.core.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.slf4j.spi.LocationAwareLogger;

/**
 * 日志工具类
 *
 * @author hedongzhou
 * @date 2018/02/03
 */
public class LogUtils {

    /**
     * 空数组
     */
    private static final Object[] EMPTY_ARRAY = new Object[]{};

    /**
     * 日志工具类类名
     */
    private static final String FQCN = LogUtils.class.getName();

    /**
     * 获取日对象
     *
     * @param obj 对象
     * @return
     */
    public static Logger get(Object obj) {
        return get(obj.getClass());
    }

    /**
     * 获取日志对象
     *
     * @param cls 类型
     * @return
     */
    public static Logger get(Class<?> cls) {
        return get(cls.getName());
    }

    /**
     * 获取日志对象
     *
     * @param name 名称
     * @return
     */
    public static Logger get(String name) {
        return LoggerFactory.getLogger(name);
    }

    /**
     * debug
     *
     * @param t
     */
    public static void debug(Throwable t) {
        debug(t, t.getMessage());
    }

    /**
     * debug
     *
     * @param format
     * @param arguments
     */
    public static void debug(String format, Object... arguments) {
        debug(null, format, arguments);
    }

    /**
     * debug
     *
     * @param t
     * @param format
     * @param arguments
     */
    public static void debug(Throwable t, String format, Object... arguments) {
        log(Level.DEBUG, format, arguments, t);
    }

    /**
     * info
     *
     * @param t
     */
    public static void info(Throwable t) {
        info(t, t.getMessage());
    }

    /**
     * info
     *
     * @param format
     * @param arguments
     */
    public static void info(String format, Object... arguments) {
        info(null, format, arguments);
    }

    /**
     * info
     *
     * @param t
     * @param format
     * @param arguments
     */
    public static void info(Throwable t, String format, Object... arguments) {
        log(Level.INFO, format, arguments, t);
    }

    /**
     * warn
     *
     * @param t
     */
    public static void warn(Throwable t) {
        warn(t, t.getMessage());
    }

    /**
     * warn
     *
     * @param format
     * @param arguments
     */
    public static void warn(String format, Object... arguments) {
        warn(null, format, arguments);
    }

    /**
     * warn
     *
     * @param t
     * @param format
     * @param arguments
     */
    public static void warn(Throwable t, String format, Object... arguments) {
        log(Level.WARN, format, arguments, t);
    }

    /**
     * error
     *
     * @param t
     */
    public static void error(Throwable t) {
        error(t, t.getMessage());
    }

    /**
     * error
     *
     * @param format
     * @param arguments
     */
    public static void error(String format, Object... arguments) {
        error(null, format, arguments);
    }

    /**
     * error
     *
     * @param t
     * @param format
     * @param arguments
     */
    public static void error(Throwable t, String format, Object... arguments) {
        log(Level.ERROR, format, arguments, t);
    }

    /**
     * 打印日志
     *
     * @param level   等级
     * @param message 消息
     * @param t       异常
     */
    public static void log(Level level, String message, Object[] argArray, Throwable t) {
        LocationAwareLogger logger = null;

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stack.length; i++) {
            if (!stack[i].getClassName().equals(LogUtils.class.getName())) {
                logger = (LocationAwareLogger) LoggerFactory.getLogger(stack[i].getClassName());
                break;
            }
        }

        int levelFlag;
        switch (level) {
            case DEBUG:
                levelFlag = LocationAwareLogger.DEBUG_INT;
                break;
            case INFO:
                levelFlag = LocationAwareLogger.INFO_INT;
                break;
            case WARN:
                levelFlag = LocationAwareLogger.WARN_INT;
                break;
            case ERROR:
                levelFlag = LocationAwareLogger.ERROR_INT;
                break;
            case TRACE:
                levelFlag = LocationAwareLogger.TRACE_INT;
                break;
            default:
                levelFlag = LocationAwareLogger.INFO_INT;
        }

        if (logger != null) {
            logger.log(null, FQCN,
                    levelFlag, message, argArray == null ? EMPTY_ARRAY : argArray, t);
        }
    }

}
