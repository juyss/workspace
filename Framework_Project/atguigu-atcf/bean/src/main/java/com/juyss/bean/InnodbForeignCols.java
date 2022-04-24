package com.juyss.bean;

public class InnodbForeignCols {
    private String id;

    private String forColName;

    private String refColName;

    private Integer pos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getForColName() {
        return forColName;
    }

    public void setForColName(String forColName) {
        this.forColName = forColName == null ? null : forColName.trim();
    }

    public String getRefColName() {
        return refColName;
    }

    public void setRefColName(String refColName) {
        this.refColName = refColName == null ? null : refColName.trim();
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }
}