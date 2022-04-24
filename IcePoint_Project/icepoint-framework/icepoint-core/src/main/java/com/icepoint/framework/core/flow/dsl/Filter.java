package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.Source;

/**
 * @author Jiawei Zhao
 */
@FunctionalInterface
public interface Filter extends Component {

    boolean filter(Source<?> source);
}
