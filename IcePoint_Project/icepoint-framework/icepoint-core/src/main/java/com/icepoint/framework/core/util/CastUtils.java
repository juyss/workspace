package com.icepoint.framework.core.util;

import org.jetbrains.annotations.Contract;
import org.springframework.lang.Nullable;

/**
 * 类型强转工具类，主要为了方便泛型的转型
 *
 * @author Jiawei Zhao
 */
public final class CastUtils {

    private CastUtils() {
    }

    /**
     * 将obj转为任意类型，可以传入null，传入null时会返回null，转入对象不为null时一定不为null
     *
     * @param obj 要强转的对象
     * @param <T> 要强转的目标类型
     * @return 强转后的对象
     * @throws ClassCastException 当返回值接收的类型与obj真实类型不匹配时
     */
    @SuppressWarnings("unchecked")
    @Contract("!null -> !null; null -> null")
    @Nullable
    public static <T> T cast(@Nullable Object obj) throws ClassCastException {
        return obj == null ? null : (T) obj;
    }

}
