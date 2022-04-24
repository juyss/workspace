package com.juyss.bean;

public class InnodbFtIndexCache {
    private String word;

    private Long firstDocId;

    private Long lastDocId;

    private Long docCount;

    private Long docId;

    private Long position;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    public Long getFirstDocId() {
        return firstDocId;
    }

    public void setFirstDocId(Long firstDocId) {
        this.firstDocId = firstDocId;
    }

    public Long getLastDocId() {
        return lastDocId;
    }

    public void setLastDocId(Long lastDocId) {
        this.lastDocId = lastDocId;
    }

    public Long getDocCount() {
        return docCount;
    }

    public void setDocCount(Long docCount) {
        this.docCount = docCount;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}