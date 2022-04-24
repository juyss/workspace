package com.icepoint.framework.core.flow;

import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public class DefaultSource<P> implements Source<P> {

    private P payload;

    private final FlowMetadata metadata;

    public DefaultSource(@Nullable P payload, @Nullable FlowMetadata metadata) {
        this.payload = payload;
        this.metadata = metadata == null ? new DefaultFlowMetadata() : metadata;
    }

    public DefaultSource(P payload) {
        this(payload, null);
    }

    public DefaultSource() {
        this(null, null);
    }

    @Override
    public P getPayload() {
        return this.payload;
    }

    @Override
    public void setPayload(P payload) {
        this.payload = payload;
    }

    @Override
    public FlowMetadata getMetadata() {
        return metadata;
    }

}
