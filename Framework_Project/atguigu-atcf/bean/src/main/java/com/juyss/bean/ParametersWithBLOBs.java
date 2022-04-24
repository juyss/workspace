package com.juyss.bean;

public class ParametersWithBLOBs extends Parameters {
    private String dataType;

    private String dtdIdentifier;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getDtdIdentifier() {
        return dtdIdentifier;
    }

    public void setDtdIdentifier(String dtdIdentifier) {
        this.dtdIdentifier = dtdIdentifier == null ? null : dtdIdentifier.trim();
    }
}