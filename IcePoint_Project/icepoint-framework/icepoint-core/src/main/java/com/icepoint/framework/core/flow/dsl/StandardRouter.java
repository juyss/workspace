package com.icepoint.framework.core.flow.dsl;

import java.util.function.Supplier;

/**
 * @author Jiawei Zhao
 */
public class StandardRouter<K> implements Router<K> {

   private final Supplier<K> keySupplier;

   private final Flow flow;

    public StandardRouter( Supplier<K> keySupplier, Flow flow) {
        this.keySupplier = keySupplier;
        this.flow = flow;
    }

    @Override
    public Supplier<K> getKeySupplier() {
        return keySupplier;
    }

    @Override
    public Flow getFlow() {
        return flow;
    }
}
