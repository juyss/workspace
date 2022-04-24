package com.icepoint.framework.web.system.resource.query;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class ConditionCollectingVisitor extends QueryVisitorAdapter implements Iterable<Parameter> {

    private List<Parameter> parameters;

    @Override
    protected void visit(DefaultQuery defaultQuery) {
        parameters = defaultQuery.getParameters();
    }

    public List<Parameter> getParameters() {
        return parameters == null ? Collections.emptyList() : Collections.unmodifiableList(parameters);
    }

    public boolean hasParameters() {
        return !getParameters().isEmpty();
    }

    @Override
    public Iterator<Parameter> iterator() {
        return getParameters().iterator();
    }
}
