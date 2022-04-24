package com.icepoint.framework.web.system.expression.node;

import org.springframework.lang.Nullable;

/**
 * 表达式节点，表达式中任何一个有单独意义的都可以称为一个表达式节点。
 * 例如：一个+号，一个&号，一个数字，一个变量等等。
 * 2+3，这个表达式就由3个节点组成，分别是：字面量2，运算符号+，字面量3。
 *
 * 表达式节点可以有父子关系，例如：
 * eq{3}，这个表达式由3个节点组成，分别是eq，花括号，字面量3。
 * 这个例子中，花括号是3的父级节点，3是花括号的子级节点
 *
 * @author Jiawei Zhao
 */
public interface ExpressionNode {

    ExpressionNode[] NO_CHILDREN = new ExpressionNode[0];

    /**
     * 此节点在表达式的开始下标位置，包含自身
     *
     * @return int
     */
    int getStartPosition();

    /**
     * 此节点在表达式的结束下标位置，不含自身，如结束字符的下标为5，那么会返回6
     *
     * @return int
     */
    int getEndPosition();

    /**
     * 此节点的父级节点
     *
     * @return 返回父级节点，可以为null
     */
    @Nullable
    ExpressionNode getParent();

    /**
     * 此节点的子级节点
     *
     * @return 返回子级节点
     */
    ExpressionNode[] getChildren();
}
