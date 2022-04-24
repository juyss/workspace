package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.ResultContainer;
import com.icepoint.framework.core.flow.Source;

/**
 * @author Jiawei Zhao
 */
@FunctionalInterface
public interface ComponentHandler<C extends Component> {

    default boolean shouldHandle(C component, Source<?> source, ResultContainer resultContainer) {
        return true;
    }

    void handle(C component, Source<?> source, ResultContainer resultContainer);

    default boolean shouldContinue() {
        return true;
    }
}
