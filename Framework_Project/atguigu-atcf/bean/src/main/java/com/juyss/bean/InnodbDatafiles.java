package com.juyss.bean;

public class InnodbDatafiles {
    private String path;

    private byte[] space;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public byte[] getSpace() {
        return space;
    }

    public void setSpace(byte[] space) {
        this.space = space;
    }
}