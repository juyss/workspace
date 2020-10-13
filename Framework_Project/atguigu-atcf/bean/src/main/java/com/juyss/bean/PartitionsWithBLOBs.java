package com.juyss.bean;

public class PartitionsWithBLOBs extends Partitions {
    private String partitionDescription;

    private String partitionComment;

    public String getPartitionDescription() {
        return partitionDescription;
    }

    public void setPartitionDescription(String partitionDescription) {
        this.partitionDescription = partitionDescription == null ? null : partitionDescription.trim();
    }

    public String getPartitionComment() {
        return partitionComment;
    }

    public void setPartitionComment(String partitionComment) {
        this.partitionComment = partitionComment == null ? null : partitionComment.trim();
    }
}