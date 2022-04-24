package com.icepoint.framework.web.system.expression.node;

import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public class EntityPath extends AbstractExpressionNode {

    private static final String SEPARATOR = "\\.";

    private final String[] paths;

    public EntityPath(String paths, int startPos, int endPos) {
        super(startPos, endPos);

        Assert.hasText(paths, "实体路径不能为空");
        this.paths = paths.trim().split(SEPARATOR);
    }

    public String[] getPaths() {
        return this.paths;
    }


}
