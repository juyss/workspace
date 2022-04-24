package com.juyss.bean;

public class InnodbIndexes {
    private Long indexId;

    private String name;

    private Long tableId;

    private Integer type;

    private Integer nFields;

    private Integer pageNo;

    private Integer space;

    private Integer mergeThreshold;

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getnFields() {
        return nFields;
    }

    public void setnFields(Integer nFields) {
        this.nFields = nFields;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getSpace() {
        return space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }

    public Integer getMergeThreshold() {
        return mergeThreshold;
    }

    public void setMergeThreshold(Integer mergeThreshold) {
        this.mergeThreshold = mergeThreshold;
    }
}