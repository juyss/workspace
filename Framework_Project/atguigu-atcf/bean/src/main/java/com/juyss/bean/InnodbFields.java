package com.juyss.bean;

public class InnodbFields {
    private String name;

    private Long pos;

    private byte[] indexId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getPos() {
        return pos;
    }

    public void setPos(Long pos) {
        this.pos = pos;
    }

    public byte[] getIndexId() {
        return indexId;
    }

    public void setIndexId(byte[] indexId) {
        this.indexId = indexId;
    }
}