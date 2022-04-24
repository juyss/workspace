package com.icepoint.base.config.mybatis;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

public abstract class InterceptorSupports implements Interceptor {

    @Override
    public final Object intercept(Invocation invocation) throws Throwable {
        InterceptorComponents components = createInterceptorComponents(invocation);
        if (!supports(invocation))
            return invocation.proceed();
        else
            return intercept(invocation, components);
    }

    protected abstract Object intercept(Invocation invocation, InterceptorComponents components) throws Throwable;

    protected abstract boolean supports(Invocation invocation);

    protected abstract InterceptorComponents createInterceptorComponents(Invocation invocation);
}
