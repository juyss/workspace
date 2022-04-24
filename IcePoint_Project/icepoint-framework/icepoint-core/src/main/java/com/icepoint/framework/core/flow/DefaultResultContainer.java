package com.icepoint.framework.core.flow;

/**
 * @author Jiawei Zhao
 */
public class DefaultResultContainer implements ResultContainer {

    private Object result;

    public DefaultResultContainer(Object result) {
        this.result = result;
    }

    public DefaultResultContainer() {
    }

    @Override
    public Object getResult() {
        return this.result;
    }

    @Override
    public void setResult(Object result) {
        this.result = result;
    }
}
