package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.util.MessageTemplates;
import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public class AggregatorBuilder<B extends FlowDefinition<B, F>, F extends AggregatableComponent & Flow> {

    private final B targetBuilder;

    private final AggregatableFlowBuilder<B, F> aggregatableFlowBuilder;

    AggregatorBuilder(B targetBuilder, AggregatableFlowBuilder<B, F> aggregatableFlowBuilder) {
        this.targetBuilder = targetBuilder;
        this.aggregatableFlowBuilder = aggregatableFlowBuilder;
    }

    public B aggregate(Aggregator aggregator) {

        Assert.notNull(aggregator, MessageTemplates.notNull("aggregator"));

        aggregatableFlowBuilder.setAggregator(aggregator);
        targetBuilder.addComponent(aggregatableFlowBuilder.get());

        return targetBuilder;
    }

}
