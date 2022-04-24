package com.icepoint.framework.web.system.resource.query;

/**
 * @author Jiawei Zhao
 */
public interface Query {

    void accept(QueryVisitor visitor);

    boolean isEmpty();

    Query EMPTY = new Query() {

        @Override
        public void accept(QueryVisitor visitor) {
            // 忽略
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

    };
}
