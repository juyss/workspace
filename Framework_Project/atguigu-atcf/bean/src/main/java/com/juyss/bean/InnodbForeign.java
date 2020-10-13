package com.juyss.bean;

public class InnodbForeign {
    private String id;

    private String forName;

    private String refName;

    private Long nCols;

    private Long type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName == null ? null : forName.trim();
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName == null ? null : refName.trim();
    }

    public Long getnCols() {
        return nCols;
    }

    public void setnCols(Long nCols) {
        this.nCols = nCols;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}