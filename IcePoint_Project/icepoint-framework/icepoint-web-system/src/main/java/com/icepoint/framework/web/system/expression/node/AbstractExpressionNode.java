package com.icepoint.framework.web.system.expression.node;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractExpressionNode implements ExpressionNode {

    private final int startPos;

    private final int endPos;

    private ExpressionNode parent;

    private ExpressionNode[] children = NO_CHILDREN;

    protected AbstractExpressionNode(int startPos, int endPos, AbstractExpressionNode... children) {

        this.startPos = startPos;
        this.endPos = endPos;

        if (ArrayUtils.isNotEmpty(children)) {
            this.children = children;
            for (AbstractExpressionNode child : children) {
                Assert.notNull(child, "子级表达式节点不能为null");
                child.parent = this;
            }
        }
    }

    @Override
    public int getStartPosition() {
        return startPos;
    }

    @Override
    public int getEndPosition() {
        return endPos;
    }

    @Override
    public ExpressionNode getParent() {
        return parent;
    }

    @Override
    public ExpressionNode[] getChildren() {
        return children;
    }

}
