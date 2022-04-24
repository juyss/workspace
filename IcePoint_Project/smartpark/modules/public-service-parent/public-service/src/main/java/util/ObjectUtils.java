package util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

/**
 * 对象工具类
 *
 * @author jiawei
 * date: 2020/07/20
 */
public abstract class ObjectUtils {

    /**
     * 如果Object不为空，返回入参Object本身，否则抛出指定异常
     *
     * @param object 验证对象
     * @param exceptionSupplier 异常
     * @return Object
     * @author jiawei
     */
    public static <T> T nullUnsafeGet(T object, @Nullable Supplier<RuntimeException> exceptionSupplier) {
        if (object != null) return object;

        if (exceptionSupplier != null) {
            RuntimeException ex = exceptionSupplier.get();
            if (ex != null) {
                throw ex;
            }
        }

        throw new NullPointerException();
    }


    /**
     * 将对象解析为Long，如果入参为null或者或者空串，这直接返回null
     *
     * @param strObj 要解析的对象
     * @return Long
     * @author jiawei
     * date: 2020/07/31
     */
    public static Long nullSafeParseLong(@Nullable Object strObj) {
        if (strObj == null)
            return null;

        if (strObj.getClass() == Long.class)
            return (Long) strObj;

        if (strObj.getClass() == String.class) {
            String str;
            if (!StringUtils.hasText(str = (String) strObj)) {
                return null;
            }
            return Long.parseLong(str);
        }

        if (strObj instanceof Number) {
            return Double.valueOf(strObj.toString()).longValue();
        }

        throw new IllegalArgumentException("Invalid argument type: " + strObj.getClass().getName());
    }

    /**
     * 将对象解析为Double，如果入参为null或者或者空串，这直接返回null
     *
     * @param strObj 要解析的对象
     * @return Long
     * @author jiawei
     * date: 2020/08/06
     */
    public static Double nullSafeParseDouble(@Nullable Object strObj) {
        if (strObj == null)
            return null;

        if (strObj.getClass() == Double.class)
            return (Double) strObj;

        if (strObj.getClass() == String.class) {
            String str;
            if (!StringUtils.hasText(str = (String) strObj)) {
                return null;
            }
            return Double.parseDouble(str);
        }

        if (strObj instanceof Number) {
            return Double.valueOf(strObj.toString());
        }

        throw new IllegalArgumentException("Invalid argument type: " + strObj.getClass().getName());
    }

    /**
     * 调用入参的toString方法，如果为空则返回空
     * @param object 将要调用ToString的对象
     * @return String
     * @author jiawei
     */
    public static String nullSafeToString(@Nullable Object object) {
        if (object == null)
            return null;

        if (object.getClass() == String.class)
            return (String) object;

        return object.toString();
    }

    /**
     * 根据分隔符切割字符串
     *
     * @param object 切割的字符串
     * @param separator 分隔符
     * @return String[]
     * @author jiawei
     * date: 2020/08/04
     */
    public static String[] nullSafeSplit(@Nullable Object object, @NonNull String separator) {
        if (object == null || !StringUtils.hasText(separator))
            return null;

        if (object.getClass() == String[].class)
            return (String[]) object;

        String str;
        if (object.getClass() == String.class
            && StringUtils.hasText(str = (String) object))
        {
            return str.split(separator);
        }

        throw new IllegalArgumentException("invalid argument type: " + object.getClass().getName());

    }
}
