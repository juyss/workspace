package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.util.Functions;

import java.util.function.Supplier;

/**
 * @author Jiawei Zhao
 */
public class Routers {

    private Routers() {
    }

    public static <K> Router<K> router(Supplier<K> keySupplier, StandardFlowBuilder builder) {
        return new StandardRouter<>(keySupplier, builder.get());
    }

    public static <K> Router<K> router(K key, StandardFlowBuilder builder) {
        return new StandardRouter<>(Functions.toSupplier(key), builder.get());
    }

    public static <K> Router<K> router(Supplier<K> keySupplier, Flow flow) {
        return new StandardRouter<>(keySupplier, flow);
    }

    public static <P, K> Router<K> router(Supplier<K> keySupplier, GenericHandler<P> handler) {
        return router(keySupplier, new StandardFlowBuilder().handle(handler).get());
    }

    public static <P, K> Router<K> router(K key, GenericHandler<P> handler) {
        return router(Functions.toSupplier(key), handler);
    }

    public static <K> Router<K> router(Supplier<K> keySupplier, Handler handler) {
        return router(keySupplier, new StandardFlowBuilder().handle(handler).get());
    }

    public static <K> Router<K> router(K key, Handler handler) {
        return router(Functions.toSupplier(key), handler);
    }

}
