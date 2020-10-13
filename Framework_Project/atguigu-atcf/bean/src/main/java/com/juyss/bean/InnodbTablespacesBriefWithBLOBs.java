package com.juyss.bean;

public class InnodbTablespacesBriefWithBLOBs extends InnodbTablespacesBrief {
    private byte[] space;

    private byte[] flag;

    public byte[] getSpace() {
        return space;
    }

    public void setSpace(byte[] space) {
        this.space = space;
    }

    public byte[] getFlag() {
        return flag;
    }

    public void setFlag(byte[] flag) {
        this.flag = flag;
    }
}