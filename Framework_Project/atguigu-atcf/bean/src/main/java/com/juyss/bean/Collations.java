package com.juyss.bean;

public class Collations {
    private String collationName;

    private String characterSetName;

    private Long id;

    private String isDefault;

    private String isCompiled;

    private Integer sortlen;

    private String padAttribute;

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName == null ? null : collationName.trim();
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName == null ? null : characterSetName.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    public String getIsCompiled() {
        return isCompiled;
    }

    public void setIsCompiled(String isCompiled) {
        this.isCompiled = isCompiled == null ? null : isCompiled.trim();
    }

    public Integer getSortlen() {
        return sortlen;
    }

    public void setSortlen(Integer sortlen) {
        this.sortlen = sortlen;
    }

    public String getPadAttribute() {
        return padAttribute;
    }

    public void setPadAttribute(String padAttribute) {
        this.padAttribute = padAttribute == null ? null : padAttribute.trim();
    }
}