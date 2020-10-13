package com.juyss.bean;

public class FilesWithBLOBs extends Files {
    private String fileName;

    private byte[] tableSchema;

    private byte[] tableName;

    private byte[] fulltextKeys;

    private byte[] deletedRows;

    private byte[] updateCount;

    private byte[] creationTime;

    private byte[] lastUpdateTime;

    private byte[] lastAccessTime;

    private byte[] recoverTime;

    private byte[] transactionCounter;

    private byte[] tableRows;

    private byte[] avgRowLength;

    private byte[] dataLength;

    private byte[] maxDataLength;

    private byte[] indexLength;

    private byte[] createTime;

    private byte[] updateTime;

    private byte[] checkTime;

    private byte[] checksum;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public byte[] getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(byte[] tableSchema) {
        this.tableSchema = tableSchema;
    }

    public byte[] getTableName() {
        return tableName;
    }

    public void setTableName(byte[] tableName) {
        this.tableName = tableName;
    }

    public byte[] getFulltextKeys() {
        return fulltextKeys;
    }

    public void setFulltextKeys(byte[] fulltextKeys) {
        this.fulltextKeys = fulltextKeys;
    }

    public byte[] getDeletedRows() {
        return deletedRows;
    }

    public void setDeletedRows(byte[] deletedRows) {
        this.deletedRows = deletedRows;
    }

    public byte[] getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(byte[] updateCount) {
        this.updateCount = updateCount;
    }

    public byte[] getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(byte[] creationTime) {
        this.creationTime = creationTime;
    }

    public byte[] getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(byte[] lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public byte[] getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(byte[] lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public byte[] getRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(byte[] recoverTime) {
        this.recoverTime = recoverTime;
    }

    public byte[] getTransactionCounter() {
        return transactionCounter;
    }

    public void setTransactionCounter(byte[] transactionCounter) {
        this.transactionCounter = transactionCounter;
    }

    public byte[] getTableRows() {
        return tableRows;
    }

    public void setTableRows(byte[] tableRows) {
        this.tableRows = tableRows;
    }

    public byte[] getAvgRowLength() {
        return avgRowLength;
    }

    public void setAvgRowLength(byte[] avgRowLength) {
        this.avgRowLength = avgRowLength;
    }

    public byte[] getDataLength() {
        return dataLength;
    }

    public void setDataLength(byte[] dataLength) {
        this.dataLength = dataLength;
    }

    public byte[] getMaxDataLength() {
        return maxDataLength;
    }

    public void setMaxDataLength(byte[] maxDataLength) {
        this.maxDataLength = maxDataLength;
    }

    public byte[] getIndexLength() {
        return indexLength;
    }

    public void setIndexLength(byte[] indexLength) {
        this.indexLength = indexLength;
    }

    public byte[] getCreateTime() {
        return createTime;
    }

    public void setCreateTime(byte[] createTime) {
        this.createTime = createTime;
    }

    public byte[] getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(byte[] updateTime) {
        this.updateTime = updateTime;
    }

    public byte[] getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(byte[] checkTime) {
        this.checkTime = checkTime;
    }

    public byte[] getChecksum() {
        return checksum;
    }

    public void setChecksum(byte[] checksum) {
        this.checksum = checksum;
    }
}