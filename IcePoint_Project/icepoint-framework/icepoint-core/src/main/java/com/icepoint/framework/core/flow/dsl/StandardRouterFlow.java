package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.Source;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * @author Jiawei Zhao
 */
public class StandardRouterFlow<K> extends AbstractRouterFlow<K> {

    private final Function< Source<?>, K> matchKeyExtractor;

    public StandardRouterFlow(Function< Source<?>, K> matchKeyExtractor, List<Router<K>> routers,
            BiPredicate<K, K> matchPredicate, Aggregator aggregator, RouteMode routeMode,
            ComponentHandlerRegistry registry) {

        super(routers, matchPredicate, aggregator, routeMode, registry);
        this.matchKeyExtractor = matchKeyExtractor;
    }

    @Override
    protected K getMatchKey(Source<?> source) {
        return matchKeyExtractor.apply(source);
    }
}
