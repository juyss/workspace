package com.icepoint.framework.web.system.expression;

import com.icepoint.framework.web.system.expression.node.EntityPath;

/**
 * @author Jiawei Zhao
 */
public class EntityPathOnlyExpression extends AbstractExpression {

    private final EntityPath entityPath;

    public EntityPathOnlyExpression(String expressionString, EntityPath entityPath) {
        super(expressionString);
        this.entityPath = entityPath;
    }

    public EntityPath getEntityPath() {
        return this.entityPath;
    }
}
