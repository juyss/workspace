package com.juyss.bean;

public class KeyColumnUsage {
    private String constraintCatalog;

    private String constraintSchema;

    private String constraintName;

    private String tableCatalog;

    private String tableSchema;

    private String tableName;

    private String columnName;

    private Integer ordinalPosition;

    private Integer positionInUniqueConstraint;

    private String referencedTableSchema;

    private String referencedTableName;

    private String referencedColumnName;

    public String getConstraintCatalog() {
        return constraintCatalog;
    }

    public void setConstraintCatalog(String constraintCatalog) {
        this.constraintCatalog = constraintCatalog == null ? null : constraintCatalog.trim();
    }

    public String getConstraintSchema() {
        return constraintSchema;
    }

    public void setConstraintSchema(String constraintSchema) {
        this.constraintSchema = constraintSchema == null ? null : constraintSchema.trim();
    }

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName == null ? null : constraintName.trim();
    }

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

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public Integer getPositionInUniqueConstraint() {
        return positionInUniqueConstraint;
    }

    public void setPositionInUniqueConstraint(Integer positionInUniqueConstraint) {
        this.positionInUniqueConstraint = positionInUniqueConstraint;
    }

    public String getReferencedTableSchema() {
        return referencedTableSchema;
    }

    public void setReferencedTableSchema(String referencedTableSchema) {
        this.referencedTableSchema = referencedTableSchema == null ? null : referencedTableSchema.trim();
    }

    public String getReferencedTableName() {
        return referencedTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName == null ? null : referencedTableName.trim();
    }

    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public void setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName == null ? null : referencedColumnName.trim();
    }
}