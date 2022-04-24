package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.Source;
import com.icepoint.framework.core.util.MessageTemplates;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Jiawei Zhao
 */
public class StandardRouterFlowBuilder<K>
        extends AggregatableFlowBuilder<StandardRouterFlowBuilder<K>, StandardRouterFlow<K>> {

    private final Function<Source<?>, K> matchKeyExtractor;

    private final BiPredicate<K, K> matchPredicate;

    private final RouteMode routeMode;

    private final List<Router<K>> routers = new ArrayList<>();

    public StandardRouterFlowBuilder(Function<Source<?>, K> matchKeyExtractor,
            BiPredicate<K, K> matchPredicate,
            RouteMode routeMode) {

        this.matchKeyExtractor = matchKeyExtractor;
        this.matchPredicate = matchPredicate;
        this.routeMode = routeMode;
    }

    public StandardRouterFlowBuilder<K> route(Router<K> router) {
        routers.add(router);
        return this;
    }

    public StandardRouterFlowBuilder<K> route(Supplier<K> keySupplier, Consumer<StandardFlowBuilder> consumer) {
        StandardFlowBuilder builder = Flows.builder();
        consumer.accept(builder);
        return route(Routers.router(keySupplier, builder.get()));
    }

    public StandardRouterFlowBuilder<K> route(K key, Consumer<StandardFlowBuilder> consumer) {
        return route(() -> key, consumer);
    }

    @Override
    public StandardRouterFlow<K> get() {

        Aggregator aggregator = getAggregator();
        Assert.notNull(aggregator, MessageTemplates.notNull("aggregator"));

        return new StandardRouterFlow<>(matchKeyExtractor, routers, matchPredicate, aggregator, routeMode, REGISTRY);
    }

}
