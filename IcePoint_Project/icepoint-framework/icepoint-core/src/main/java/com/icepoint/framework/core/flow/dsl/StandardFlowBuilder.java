package com.icepoint.framework.core.flow.dsl;

/**
 * @author Jiawei Zhao
 */
public class StandardFlowBuilder extends FlowDefinition<StandardFlowBuilder, StandardFlow> {

    static {
        // 次级流程
        registerComponentHandler(Flow.class, Flow::execute);
    }

    private StandardFlow flow;

    StandardFlowBuilder() {
    }

    @Override
    public StandardFlow get() {

        if (this.flow == null) {
            this.flow = new StandardFlow(getComponents(), REGISTRY);
        }

        return this.flow;
    }
}
