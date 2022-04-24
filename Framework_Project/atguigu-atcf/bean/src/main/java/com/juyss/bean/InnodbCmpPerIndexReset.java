package com.juyss.bean;

public class InnodbCmpPerIndexReset {
    private String databaseName;

    private String tableName;

    private String indexName;

    private Integer compressOps;

    private Integer compressOpsOk;

    private Integer compressTime;

    private Integer uncompressOps;

    private Integer uncompressTime;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName == null ? null : databaseName.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
    }

    public Integer getCompressOps() {
        return compressOps;
    }

    public void setCompressOps(Integer compressOps) {
        this.compressOps = compressOps;
    }

    public Integer getCompressOpsOk() {
        return compressOpsOk;
    }

    public void setCompressOpsOk(Integer compressOpsOk) {
        this.compressOpsOk = compressOpsOk;
    }

    public Integer getCompressTime() {
        return compressTime;
    }

    public void setCompressTime(Integer compressTime) {
        this.compressTime = compressTime;
    }

    public Integer getUncompressOps() {
        return uncompressOps;
    }

    public void setUncompressOps(Integer uncompressOps) {
        this.uncompressOps = uncompressOps;
    }

    public Integer getUncompressTime() {
        return uncompressTime;
    }

    public void setUncompressTime(Integer uncompressTime) {
        this.uncompressTime = uncompressTime;
    }
}