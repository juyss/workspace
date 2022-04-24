package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.Source;

/**
 * @author Jiawei Zhao
 */
@FunctionalInterface
public interface Handler extends Component {

    void handle(Source<?> source);
}
