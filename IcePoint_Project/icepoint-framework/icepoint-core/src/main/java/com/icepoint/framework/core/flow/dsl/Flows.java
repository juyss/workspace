package com.icepoint.framework.core.flow.dsl;

import com.icepoint.framework.core.flow.DefaultResultContainer;
import com.icepoint.framework.core.flow.DefaultSource;
import com.icepoint.framework.core.util.CastUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * @author Jiawei Zhao
 */
public class Flows {

    public static void main(String[] args) {

//        DefaultResultContainer container = new DefaultResultContainer();
//
//        new StandardFlowBuilder()
//                .filter(String.class, (s, m) -> "hello".equals(s))
//                .handle(s -> System.out.println("开始处理"))
//                .branches(bs -> bs
//                        .branch(b21 -> b21
//                                .handle(s -> System.out.println("第一层分支1"))
//                                .branches(bs2 -> bs2
//                                        .branch(b31 -> b31.handle(String.class, (s, m) -> "第二层分支1"))
//                                        .branch(b32 -> b32.handle(String.class, (s, m) -> "第二层分支2"))
//                                        .branch(b33 -> b33.handle(String.class, (s, m) -> "第二层分支3")))
//                                .aggregate(a -> {
//                                    System.out.println("| 执行第二层分支执行结果聚合");
//                                    System.out.println("| -> " + String.join("\n| -> ", (List<String>) CastUtils.cast(a)));
//                                    return "第一层分支1";
//                                }))
//                        .branch(b22 -> b22.handle(s -> System.out.println("第一层分支2"))
//                                .handle(String.class, (s, m) -> "第一层分支2"))
//                        .branch(b23 -> b23.handle(s -> System.out.println("第一层分支3"))
//                                .handle(String.class, (s, m) -> "第一层分支3")))
//                .aggregate(a -> {
//                    System.out.println("执行第一层分支执行结果聚合");
//                    System.out.println("-> " + String.join("\n-> ", (List<String>) CastUtils.cast(a)));
//                    return "处理完成";
//                })
//                .get()
//                .execute(new DefaultSource<>("hello"), container);
//
//        System.out.println(container.getResult());

//        AtomicReference<String> reference = new AtomicReference<>("abc");
//        String result = Flows.routeFirstMatches(reference, AtomicReference::get, String::startsWith,
//                Routers.router("b", Handlers.returns(() -> "notHit")),
//                Routers.router("as", Handlers.returns(() -> "hit")),
//                Routers.router("ab", Handlers.returns(() -> "willNotHit")));
//
//        System.out.println(result);


       
    }

    public static StandardFlowBuilder builder() {
        return new StandardFlowBuilder();
    }

    @SafeVarargs
    public static <P, K, R> R routeFirstMatches(P payload, Function<P, K> matchKeyExtractor,
            BiPredicate<K, K> predicate,
            Router<K>... routers) {

        DefaultResultContainer container = new DefaultResultContainer();

        new StandardFlowBuilder()
                .routes(RouteMode.FIRST_MATCHES,
                        s -> matchKeyExtractor.apply(CastUtils.cast(s.getPayload())),
                        predicate,
                        r -> Arrays.stream(routers).forEach(r::route))
                .aggregate(Aggregators.any())
                .get()
                .execute(new DefaultSource<>(payload), container);

        Object result = container.getResult();
        return result == null ? null : CastUtils.cast(result);
    }

    @SafeVarargs
    public static <P, K, R> R routeFirstMatches(P payload, Function<P, K> matchKeyExtractor,
            Router<K>... routers) {

        return routeFirstMatches(payload, matchKeyExtractor, Objects::equals, routers);
    }

    @SafeVarargs
    public static <P, R> R routeFirstMatches(P payload,
            BiPredicate<P, P> predicate,
            Router<P>... routers) {

        return routeFirstMatches(payload, p -> p, predicate, routers);
    }

    @SafeVarargs
    public static <P, R> R routeFirstMatches(P payload, Router<P>... routers) {

        return routeFirstMatches(payload, p -> p, Objects::equals, routers);
    }
}
