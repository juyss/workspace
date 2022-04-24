package com.github.tangyi.core.common.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 接口返回数据bean
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public class ResultBean {

    /**
     * 结果码
     */
    @JsonProperty("code")
    protected Integer code;

    /**
     * 消息
     */
    @JsonProperty("desc")
    protected String message;

    /**
     * 数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    protected Object data;

    /**
     * 分页信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page_info")
    protected PageInfo pageInfo;

    public ResultBean() {
    }

    public ResultBean(Integer code, String message) {
        this(code, message, null);
    }

    public ResultBean(Integer code, String message, Object data) {
        this(code, message, data, null, true);
    }

    public ResultBean(Integer code, String message, Object data, Object defaultData, boolean needTrans) {
        this.code = code;
        this.message = message;

        if (needTrans) {
            if (data instanceof PageResult) {
                PageResult pageResult = (PageResult) data;

                this.data = pageResult.getRows();
                this.pageInfo = pageResult.pageInfo;
            } else {
                this.data = data;
            }
        } else {
            this.data = data;
        }

        if (this.data == null) {
            this.data = defaultData;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public <T> T getData() {
        return (T) data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", pageInfo=" + pageInfo +
                '}';
    }

    /**
     * 是否成功
     *
     * @return
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.code != null && BaseResultCode.success.code == this.code;
    }

}
