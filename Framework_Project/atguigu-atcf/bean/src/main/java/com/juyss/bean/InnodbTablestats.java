package com.juyss.bean;

public class InnodbTablestats {
    private Long tableId;

    private String name;

    private String statsInitialized;

    private Long numRows;

    private Long clustIndexSize;

    private Long otherIndexSize;

    private Long modifiedCounter;

    private Long autoinc;

    private Integer refCount;

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

    public String getStatsInitialized() {
        return statsInitialized;
    }

    public void setStatsInitialized(String statsInitialized) {
        this.statsInitialized = statsInitialized == null ? null : statsInitialized.trim();
    }

    public Long getNumRows() {
        return numRows;
    }

    public void setNumRows(Long numRows) {
        this.numRows = numRows;
    }

    public Long getClustIndexSize() {
        return clustIndexSize;
    }

    public void setClustIndexSize(Long clustIndexSize) {
        this.clustIndexSize = clustIndexSize;
    }

    public Long getOtherIndexSize() {
        return otherIndexSize;
    }

    public void setOtherIndexSize(Long otherIndexSize) {
        this.otherIndexSize = otherIndexSize;
    }

    public Long getModifiedCounter() {
        return modifiedCounter;
    }

    public void setModifiedCounter(Long modifiedCounter) {
        this.modifiedCounter = modifiedCounter;
    }

    public Long getAutoinc() {
        return autoinc;
    }

    public void setAutoinc(Long autoinc) {
        this.autoinc = autoinc;
    }

    public Integer getRefCount() {
        return refCount;
    }

    public void setRefCount(Integer refCount) {
        this.refCount = refCount;
    }
}