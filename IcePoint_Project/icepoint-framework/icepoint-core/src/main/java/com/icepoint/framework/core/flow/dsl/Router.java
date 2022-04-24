package com.icepoint.framework.core.flow.dsl;

import java.util.function.Supplier;

/**
 * @author Jiawei Zhao
 */
public interface Router<K> {

    Supplier<K> getKeySupplier();

    Flow getFlow();
}
