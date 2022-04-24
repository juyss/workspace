package com.juyss.bean;

public class InnodbTables {
    private Long tableId;

    private String name;

    private Integer flag;

    private Integer nCols;

    private Long space;

    private String rowFormat;

    private Integer zipPageSize;

    private String spaceType;

    private Integer instantCols;

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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getnCols() {
        return nCols;
    }

    public void setnCols(Integer nCols) {
        this.nCols = nCols;
    }

    public Long getSpace() {
        return space;
    }

    public void setSpace(Long space) {
        this.space = space;
    }

    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat == null ? null : rowFormat.trim();
    }

    public Integer getZipPageSize() {
        return zipPageSize;
    }

    public void setZipPageSize(Integer zipPageSize) {
        this.zipPageSize = zipPageSize;
    }

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType == null ? null : spaceType.trim();
    }

    public Integer getInstantCols() {
        return instantCols;
    }

    public void setInstantCols(Integer instantCols) {
        this.instantCols = instantCols;
    }
}