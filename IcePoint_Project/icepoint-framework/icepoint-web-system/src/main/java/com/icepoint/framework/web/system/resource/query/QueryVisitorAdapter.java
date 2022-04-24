package com.icepoint.framework.web.system.resource.query;

/**
 * 根据类型适配的{@link QueryVisitor}
 *
 * @author Jiawei Zhao
 */
public abstract class QueryVisitorAdapter implements QueryVisitor {

    @Override
    public final void visit(Query query) {
        if (query instanceof DefaultQuery) {
            visit((DefaultQuery) query);
        }
    }

    protected void visit(DefaultQuery defaultQuery) {
    }

}
