package com.icepoint.framework.core.flow.dsl;

/**
 * @author Jiawei Zhao
 */
public abstract class AggregatableFlowBuilder<B extends BaseFlowDefinition<B, F>, F extends AggregatableComponent & Flow>
        extends BaseFlowDefinition<B, F>
        implements AggregatableComponentBuilder<F> {

    private Aggregator aggregator;

    protected void setAggregator(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    protected Aggregator getAggregator() {
        return aggregator;
    }
}
