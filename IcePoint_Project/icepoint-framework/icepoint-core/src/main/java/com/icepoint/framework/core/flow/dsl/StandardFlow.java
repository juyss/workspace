package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.DefaultSource;
import com.icepoint.framework.core.flow.ResultContainer;
import com.icepoint.framework.core.flow.Source;
import com.icepoint.framework.core.util.CastUtils;

import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class StandardFlow implements Flow {

    private final List<Component> components;

    private final ComponentHandlerRegistry registry;

    StandardFlow(List<Component> components, ComponentHandlerRegistry registry) {
        this.components = components;
        this.registry = registry;
    }

    @Override
    public void execute(Source<?> source, ResultContainer resultContainer) {

        configureComponentsBeforeAllHandle(components, source, resultContainer);

        Source<?> newSource = processBeforeAllHandle(source, resultContainer);

        for (Component component : components) {

            ComponentHandler<?> handler = registry.getHandler(component);
            newSource = processBeforeEachHandle(source, newSource, resultContainer);

            if (!handler.shouldHandle(CastUtils.cast(component), newSource, resultContainer)) {
                continue;
            }

            handler.handle(CastUtils.cast(component), newSource, resultContainer);
            processAfterEachHandled(component, newSource, resultContainer);

            if (!handler.shouldContinue()) {
                break;
            }
        }

        processAfterAllHandled(newSource, resultContainer);
    }

    protected void configureComponentsBeforeAllHandle(List<Component> components,
            Source<?> source, ResultContainer resultContainer) {
        // Template Method
    }

    protected Source<?> processBeforeAllHandle(Source<?> source, ResultContainer resultContainer) {
        return copySource(source);
        // Template Method
    }

    protected Source<?> processBeforeEachHandle(Source<?> source, Source<?> newSource,
            ResultContainer resultContainer) {

        return newSource;
    }

    protected void processAfterAllHandled(Source<?> source, ResultContainer resultContainer) {
    }

    protected void processAfterEachHandled(Component component, Source<?> source, ResultContainer resultContainer) {
    }

    protected Source<?> copySource(Source<?> source) {
        return new DefaultSource<>(source.getPayload());
    }
}
