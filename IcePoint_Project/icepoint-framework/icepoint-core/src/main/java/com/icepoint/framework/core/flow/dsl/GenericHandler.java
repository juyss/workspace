package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.FlowMetadata;

/**
 * @author Jiawei Zhao
 */
@FunctionalInterface
public interface GenericHandler<P> extends Component {

    Object handle(P payload, FlowMetadata metadata);
}
