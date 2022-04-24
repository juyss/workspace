package com.juyss.bean;

public class InnodbCachedIndexes {
    private Integer spaceId;

    private Long indexId;

    private Long nCachedPages;

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public Long getnCachedPages() {
        return nCachedPages;
    }

    public void setnCachedPages(Long nCachedPages) {
        this.nCachedPages = nCachedPages;
    }
}