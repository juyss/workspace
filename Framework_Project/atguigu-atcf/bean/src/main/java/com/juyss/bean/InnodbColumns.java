package com.juyss.bean;

public class InnodbColumns {
    private Long tableId;

    private String name;

    private Long pos;

    private Integer mtype;

    private Integer prtype;

    private Integer len;

    private Integer hasDefault;

    private String defaultValue;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getPos() {
        return pos;
    }

    public void setPos(Long pos) {
        this.pos = pos;
    }

    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    public Integer getPrtype() {
        return prtype;
    }

    public void setPrtype(Integer prtype) {
        this.prtype = prtype;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public Integer getHasDefault() {
        return hasDefault;
    }

    public void setHasDefault(Integer hasDefault) {
        this.hasDefault = hasDefault;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }
}