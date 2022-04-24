package com.icepoint.framework.core.flow;

import com.icepoint.framework.core.util.CastUtils;

/**
 * @author Jiawei Zhao
 */
public interface Source<P> {

    P getPayload();

    void setPayload(P payload);

    default <PL> void setPayloadAs(PL payload) {
        setPayload(CastUtils.cast(payload));
    }

    FlowMetadata getMetadata();
}
