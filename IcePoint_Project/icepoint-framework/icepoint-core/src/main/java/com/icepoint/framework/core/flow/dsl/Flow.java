package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.ResultContainer;
import com.icepoint.framework.core.flow.Source;

/**
 * @author Jiawei Zhao
 */
public interface Flow extends Component {

    default void execute(Source<?> source, ResultContainer resultContainer) {
    }
}
