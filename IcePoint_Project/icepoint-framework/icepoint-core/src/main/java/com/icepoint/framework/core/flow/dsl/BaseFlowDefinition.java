package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.util.CastUtils;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
public abstract class BaseFlowDefinition<B extends BaseFlowDefinition<B, F>, F extends Flow>
        implements ComponentBuilder<F> {

    protected static final ComponentHandlerRegistry REGISTRY = new ComponentHandlerRegistry();

    private final List<Component> components = new LinkedList<>();

    BaseFlowDefinition() {
    }

    protected B addComponent(Component component) {
        this.components.add(component);
        return _this();
    }

    protected B addComponents(List<Component> components) {
        this.components.addAll(components);
        return _this();
    }

    protected List<Component> getComponents() {
        return components;
    }

    protected static <C extends Component> void registerComponentHandler(Class<C> componentType,
            ComponentHandler<C> handler) {

        REGISTRY.register(componentType, handler);
    }

    protected static <C extends Component> void registerComponentHandler(ParameterizedTypeReference<C> reference,
            ComponentHandler<C> handler) {

        ParameterizedType type = (ParameterizedType) reference.getType();
        REGISTRY.register(CastUtils.cast(type.getRawType()), handler);
    }

    protected B _this() {
        return CastUtils.cast(this);
    }

}
