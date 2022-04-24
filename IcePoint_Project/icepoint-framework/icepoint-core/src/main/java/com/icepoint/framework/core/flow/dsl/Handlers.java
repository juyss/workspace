package com.icepoint.framework.core.flow.dsl;

import java.util.function.Supplier;

/**
 * @author Jiawei Zhao
 */
public class Handlers {

    private Handlers() {
    }

    public static Handler handle(Runnable runnable) {
        return source -> runnable.run();
    }

    public static <R> GenericHandler<?> returns(Supplier<R> supplier) {
        return (s, m) -> supplier.get();
    }

}
