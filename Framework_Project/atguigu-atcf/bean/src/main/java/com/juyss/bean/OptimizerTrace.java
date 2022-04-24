package com.juyss.bean;

public class OptimizerTrace {
    private String query;

    private String trace;

    private Integer missingBytesBeyondMaxMemSize;

    private Boolean insufficientPrivileges;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query == null ? null : query.trim();
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace == null ? null : trace.trim();
    }

    public Integer getMissingBytesBeyondMaxMemSize() {
        return missingBytesBeyondMaxMemSize;
    }

    public void setMissingBytesBeyondMaxMemSize(Integer missingBytesBeyondMaxMemSize) {
        this.missingBytesBeyondMaxMemSize = missingBytesBeyondMaxMemSize;
    }

    public Boolean getInsufficientPrivileges() {
        return insufficientPrivileges;
    }

    public void setInsufficientPrivileges(Boolean insufficientPrivileges) {
        this.insufficientPrivileges = insufficientPrivileges;
    }
}