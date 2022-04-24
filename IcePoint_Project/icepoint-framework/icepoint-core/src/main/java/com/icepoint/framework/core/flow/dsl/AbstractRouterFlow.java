package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.ResultContainer;
import com.icepoint.framework.core.flow.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractRouterFlow<K> extends BranchesFlow implements RouterFlow {

    private final List<Router<K>> routers;

    private final BiPredicate<K, K> matchPredicate;

    private final RouteMode routeMode;

    protected AbstractRouterFlow(
            List<Router<K>> routers,
            BiPredicate<K, K> matchPredicate, Aggregator aggregator,
            RouteMode routeMode, ComponentHandlerRegistry registry) {

        super(new ArrayList<>(), aggregator, registry);
        this.routers = routers;
        this.matchPredicate = matchPredicate;
        this.routeMode = routeMode;
    }

    @Override
    protected void configureComponentsBeforeAllHandle(List<Component> components, Source<?> source,
            ResultContainer resultContainer) {

        components.addAll(this.route(source));
    }

    @Override
    public final List<Flow> route(Source<?> source) {

        List<Flow> chosenFlows = new ArrayList<>();

        K matchKey = getMatchKey(source);

        for (Router<K> router : routers) {

            Supplier<K> routerKeySupplier = router.getKeySupplier();
            Flow flow = router.getFlow();

            if (matchPredicate.test(matchKey, routerKeySupplier.get())) {

                chosenFlows.add(flow);

                if (routeMode == RouteMode.FIRST_MATCHES) {
                    break;
                }
            }
        }

        return chosenFlows;
    }

    protected abstract K getMatchKey(Source<?> source);
}
