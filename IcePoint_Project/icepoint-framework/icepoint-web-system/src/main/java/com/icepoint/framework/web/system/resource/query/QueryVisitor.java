package com.icepoint.framework.web.system.resource.query;

/**
 * {@link Query}类的访问者接口
 *
 * @author Jiawei Zhao
 */
public interface QueryVisitor {

    void visit(Query query);

}
