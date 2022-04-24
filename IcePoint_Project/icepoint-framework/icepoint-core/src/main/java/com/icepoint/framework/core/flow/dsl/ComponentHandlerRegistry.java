package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.MapUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jiawei Zhao
 */
public class ComponentHandlerRegistry {

    private final Map<Class<?>, ComponentHandler<?>> handlers = new ConcurrentHashMap<>();

    ComponentHandlerRegistry() {
    }

    public <C extends Component> void register(Class<C> componentType, ComponentHandler<C> handler) {
        handlers.merge(componentType, handler, MapUtils.duplicateKeys());
    }

    public <C extends Component> ComponentHandler<C> getHandler(C component) {

        for (Map.Entry<Class<?>, ComponentHandler<?>> entry : handlers.entrySet()) {

            Class<?> componentType = entry.getKey();
            ComponentHandler<C> handler = CastUtils.cast(entry.getValue());

            if (componentType.isInstance(component)) {
                return handler;
            }
        }

        throw new IllegalArgumentException("没有对应的类型的处理器");
    }
}
