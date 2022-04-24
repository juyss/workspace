package com.juyss.bean;

public class StGeometryColumns {
    private String tableCatalog;

    private String tableSchema;

    private String tableName;

    private String columnName;

    private String srsName;

    private Integer srsId;

    private String geometryTypeName;

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog == null ? null : tableCatalog.trim();
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema == null ? null : tableSchema.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getSrsName() {
        return srsName;
    }

    public void setSrsName(String srsName) {
        this.srsName = srsName == null ? null : srsName.trim();
    }

    public Integer getSrsId() {
        return srsId;
    }

    public void setSrsId(Integer srsId) {
        this.srsId = srsId;
    }

    public String getGeometryTypeName() {
        return geometryTypeName;
    }

    public void setGeometryTypeName(String geometryTypeName) {
        this.geometryTypeName = geometryTypeName == null ? null : geometryTypeName.trim();
    }
}