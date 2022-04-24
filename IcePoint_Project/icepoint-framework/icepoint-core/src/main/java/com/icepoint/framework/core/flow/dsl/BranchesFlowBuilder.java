package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.util.CastUtils;
import org.springframework.util.Assert;

import java.util.function.Consumer;

/**
 * @author Jiawei Zhao
 */
public class BranchesFlowBuilder extends AggregatableFlowBuilder<BranchesFlowBuilder, BranchesFlow> {

    BranchesFlowBuilder() {
    }

    public BranchesFlowBuilder branch(Consumer<StandardFlowBuilder> consumer) {
        StandardFlowBuilder builder = new StandardFlowBuilder();
        consumer.accept(builder);
        return addComponent(builder.get());
    }

    public <P> BranchesFlowBuilder branch(GenericFilter<P> filter, Consumer<StandardFlowBuilder> consumer) {
        StandardFlowBuilder builder = newStandardBranchBuilder();
        consumer.accept(builder.filter(filter));
        return addComponent(builder.get());
    }

    public <P> BranchesFlowBuilder branch(Class<P> payloadType, GenericFilter<P> filter,
            Consumer<StandardFlowBuilder> consumer) {

        StandardFlowBuilder builder = newStandardBranchBuilder();
        consumer.accept(builder.filter(payloadType, filter));
        return addComponent(builder.get());
    }

    public BranchesFlowBuilder branch(Filter filter, Consumer<StandardFlowBuilder> consumer) {
        StandardFlowBuilder builder = newStandardBranchBuilder();
        consumer.accept(builder.filter(filter));
        return addComponent(builder.get());
    }

    @Override
    public BranchesFlow get() {

        Aggregator aggregator = getAggregator();
        Assert.notNull(aggregator, "聚合器还没设置");

        return new BranchesFlow(CastUtils.cast(getComponents()), aggregator, REGISTRY);
    }

    private StandardFlowBuilder newStandardBranchBuilder() {
        return new StandardFlowBuilder();
    }
}
