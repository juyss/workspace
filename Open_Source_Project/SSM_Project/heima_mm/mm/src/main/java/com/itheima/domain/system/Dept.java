package com.itheima.domain.system;

/**
 * @author zxq
 * @create 2020-08-26 8:52
 */
public class Dept {
    private String id;
    private String deptName;//部门名称
    private String parentId;//上级部门的id
    private Integer state;//审核状态

    private Dept parent;//上一级部门

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }
}
