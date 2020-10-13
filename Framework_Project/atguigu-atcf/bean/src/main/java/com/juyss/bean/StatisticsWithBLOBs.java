package com.juyss.bean;

public class StatisticsWithBLOBs extends Statistics {
    private byte[] packed;

    private String expression;

    public byte[] getPacked() {
        return packed;
    }

    public void setPacked(byte[] packed) {
        this.packed = packed;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression == null ? null : expression.trim();
    }
}