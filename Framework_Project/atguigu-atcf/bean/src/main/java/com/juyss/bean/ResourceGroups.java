package com.juyss.bean;

public class ResourceGroups {
    private String resourceGroupName;

    private String resourceGroupType;

    private Boolean resourceGroupEnabled;

    private Integer threadPriority;

    private byte[] vcpuIds;

    public String getResourceGroupName() {
        return resourceGroupName;
    }

    public void setResourceGroupName(String resourceGroupName) {
        this.resourceGroupName = resourceGroupName == null ? null : resourceGroupName.trim();
    }

    public String getResourceGroupType() {
        return resourceGroupType;
    }

    public void setResourceGroupType(String resourceGroupType) {
        this.resourceGroupType = resourceGroupType == null ? null : resourceGroupType.trim();
    }

    public Boolean getResourceGroupEnabled() {
        return resourceGroupEnabled;
    }

    public void setResourceGroupEnabled(Boolean resourceGroupEnabled) {
        this.resourceGroupEnabled = resourceGroupEnabled;
    }

    public Integer getThreadPriority() {
        return threadPriority;
    }

    public void setThreadPriority(Integer threadPriority) {
        this.threadPriority = threadPriority;
    }

    public byte[] getVcpuIds() {
        return vcpuIds;
    }

    public void setVcpuIds(byte[] vcpuIds) {
        this.vcpuIds = vcpuIds;
    }
}