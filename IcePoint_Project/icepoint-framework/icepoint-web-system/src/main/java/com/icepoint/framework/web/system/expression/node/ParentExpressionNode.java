package com.icepoint.framework.web.system.expression.node;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public class ParentExpressionNode extends AbstractExpressionNode {

    private final String prefix;

    private final String suffix;

    public ParentExpressionNode(String prefix, String suffix, int startPos, int endPos,
            AbstractExpressionNode... children) {
        super(startPos, endPos, children);
        Assert.isTrue(ArrayUtils.isNotEmpty(children), "此节点为抽象的父级节点，子级节点不能为空");
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }
}
