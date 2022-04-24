package com.juyss.bean;

public class TriggersWithBLOBs extends Triggers {
    private byte[] actionCondition;

    private String actionStatement;

    private byte[] actionReferenceOldTable;

    private byte[] actionReferenceNewTable;

    public byte[] getActionCondition() {
        return actionCondition;
    }

    public void setActionCondition(byte[] actionCondition) {
        this.actionCondition = actionCondition;
    }

    public String getActionStatement() {
        return actionStatement;
    }

    public void setActionStatement(String actionStatement) {
        this.actionStatement = actionStatement == null ? null : actionStatement.trim();
    }

    public byte[] getActionReferenceOldTable() {
        return actionReferenceOldTable;
    }

    public void setActionReferenceOldTable(byte[] actionReferenceOldTable) {
        this.actionReferenceOldTable = actionReferenceOldTable;
    }

    public byte[] getActionReferenceNewTable() {
        return actionReferenceNewTable;
    }

    public void setActionReferenceNewTable(byte[] actionReferenceNewTable) {
        this.actionReferenceNewTable = actionReferenceNewTable;
    }
}