package com.icepoint.framework.generator.entity;

/**
 * @author Jiawei Zhao
 */
public class TableLink {

    private Long id;

    private Long tableId;

    private Long fkFieldId;

    private Long linkTableId;

    private AssociationType associationType;

    private String name;

    private String nameAlias;

    private TableMetadata linkTable;

    private FieldMetadata fkField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getFkFieldId() {
        return fkFieldId;
    }

    public void setFkFieldId(Long fkFieldId) {
        this.fkFieldId = fkFieldId;
    }

    public Long getLinkTableId() {
        return linkTableId;
    }

    public void setLinkTableId(Long linkTableId) {
        this.linkTableId = linkTableId;
    }

    public AssociationType getAssociationType() {
        return associationType;
    }

    public void setAssociationType(AssociationType associationType) {
        this.associationType = associationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAlias() {
        return nameAlias;
    }

    public void setNameAlias(String nameAlias) {
        this.nameAlias = nameAlias;
    }

    public TableMetadata getLinkTable() {
        return linkTable;
    }

    public void setLinkTable(TableMetadata linkTable) {
        this.linkTable = linkTable;
    }

    public FieldMetadata getFkField() {
        return fkField;
    }

    public void setFkField(FieldMetadata fkField) {
        this.fkField = fkField;
    }
}
