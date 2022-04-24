package com.icepoint.framework.core.flow.dsl;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
@FunctionalInterface
public interface Aggregator extends Component {

    Object aggregate(List<Object> results);
}
