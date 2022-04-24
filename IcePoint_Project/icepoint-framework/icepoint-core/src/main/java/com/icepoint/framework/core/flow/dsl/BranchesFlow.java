package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.ResultContainer;
import com.icepoint.framework.core.flow.Source;
import com.icepoint.framework.core.util.CastUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class BranchesFlow extends StandardFlow implements AggregatableComponent {

    private final Aggregator aggregator;

    private final List<Object> results = new LinkedList<>();

    public BranchesFlow(List<Flow> flows,
            Aggregator aggregator, ComponentHandlerRegistry registry) {

        super(CastUtils.cast(flows), registry);
        this.aggregator = aggregator;
    }

    @Override
    protected Source<?> processBeforeAllHandle(Source<?> source, ResultContainer resultContainer) {
        return source;
    }

    @Override
    protected Source<?> processBeforeEachHandle(Source<?> source, Source<?> newSource,
            ResultContainer resultContainer) {

        return copySource(source);
    }

    @Override
    protected void processAfterEachHandled(Component component, Source<?> source, ResultContainer resultContainer) {
        results.add(resultContainer.getResult());
    }

    @Override
    protected void processAfterAllHandled(Source<?> source, ResultContainer resultContainer) {
        Object aggregateResult = aggregator.aggregate(results);
        source.setPayloadAs(aggregateResult);
        resultContainer.setResult(aggregateResult);
    }
}
