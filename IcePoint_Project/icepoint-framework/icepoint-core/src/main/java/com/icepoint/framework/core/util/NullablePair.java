package com.icepoint.framework.core.util;

import javax.annotation.Nullable;

/**
 * @author Jiawei Zhao
 */
public class NullablePair<F, S> {

    private final F first;

    private final S second;

    private NullablePair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Nullable
    public F getFirst() {
        return first;
    }

    @Nullable
    public S getSecond() {
        return second;
    }

    public static <F, S> NullablePair<F, S> of(F first, S second) {
        return new NullablePair<>(first, second);
    }
}
