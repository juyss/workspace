package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.ResultContainer;
import com.icepoint.framework.core.flow.Source;
import com.icepoint.framework.core.util.MessageTemplates;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.Assert;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Jiawei Zhao
 */
public abstract class FlowDefinition<B extends FlowDefinition<B, F>, F extends Flow>
        extends BaseFlowDefinition<B, F> {

    static {

        // 泛型过滤器
        registerComponentHandler(new ParameterizedTypeReference<GenericFilter<Object>>() {
        }, new ComponentHandler<GenericFilter<Object>>() {

            private boolean result = true;

            @Override
            public void handle(GenericFilter<Object> filter, Source<?> source, ResultContainer resultContainer) {
                result = filter.filter(source.getPayload(), source.getMetadata());
            }

            @Override
            public boolean shouldContinue() {
                return result;
            }
        });

        // 普通过滤器
        registerComponentHandler(Filter.class, new ComponentHandler<Filter>() {

            private boolean result = true;

            @Override
            public void handle(Filter filter, Source<?> source, ResultContainer resultContainer) {
                result = filter.filter(source);
            }

            @Override
            public boolean shouldContinue() {
                return result;
            }
        });

        // 泛型处理器
        registerComponentHandler(new ParameterizedTypeReference<GenericHandler<Object>>() {
        }, (handler, s, r) -> {

            Object newPayload = handler.handle(s.getPayload(), s.getMetadata());
            s.setPayloadAs(newPayload);
            r.setResult(s.getPayload());
        });

        // 普通处理器
        registerComponentHandler(Handler.class, (handler, s, r) -> handler.handle(s));
    }

    FlowDefinition() {
    }

    public B filter(Filter filter) {
        Assert.notNull(filter, MessageTemplates.notNull("filter"));
        return addComponent(filter);
    }

    public <P> B filter(GenericFilter<P> filter) {
        Assert.notNull(filter, MessageTemplates.notNull("filter"));
        return addComponent(filter);
    }

    public <P> B filter(Class<P> payloadType, GenericFilter<P> filter) {
        return filter(filter);
    }

    public B handle(Handler handler) {
        Assert.notNull(handler, MessageTemplates.notNull("handler"));
        return addComponent(handler);
    }

    public <P> B handle(GenericHandler<P> handler) {
        Assert.notNull(handler, MessageTemplates.notNull("handler"));
        return addComponent(handler);
    }

    public <P> B handle(Class<P> payloadType, GenericHandler<P> handler) {
        return handle(handler);
    }

    public AggregatorBuilder<B, ? extends F> branches(Consumer<BranchesFlowBuilder> consumer) {

        BranchesFlowBuilder builder = new BranchesFlowBuilder();
        consumer.accept(builder);

        return newAggregatorBuilder(builder);
    }

    public <K> AggregatorBuilder<B, ? extends F> routes(RouteMode routeMode, Function<Source<?>, K> matchKeyExtractor,
            BiPredicate<K, K> matchPredicate, Consumer<StandardRouterFlowBuilder<K>> consumer) {

        StandardRouterFlowBuilder<K> builder =
                new StandardRouterFlowBuilder<>(matchKeyExtractor, matchPredicate, routeMode);
        consumer.accept(builder);

        return newAggregatorBuilder(builder);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private AggregatorBuilder<B, ? extends F> newAggregatorBuilder(AggregatableFlowBuilder<?, ?> builder) {
        return new AggregatorBuilder(this, builder);
    }

}
