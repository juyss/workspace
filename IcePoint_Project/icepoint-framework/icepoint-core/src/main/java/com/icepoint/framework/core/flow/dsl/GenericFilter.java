package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.FlowMetadata;

/**
 * @author Jiawei Zhao
 */
@FunctionalInterface
public interface GenericFilter<P> extends Component {

    boolean filter(P payload, FlowMetadata metadata);
}
