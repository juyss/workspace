package com.github.tangyi.file.enums;

import lombok.Getter;

/**
 * 附件存储类型
 */
@Getter
public enum AttachUploaderEnum {

    FILE(1, "文件", "com.github.tangyi.file.uploader.FileUploader")
    , FAST_DFS(2, "FastDfs", "com.github.tangyi.file.uploader.FastDfsUploader"),
    QI_NIU(3, "七牛云", "com.github.tangyi.file.uploader.QiNiuUploader")
    ,HW_OBS(4,"华为obs","com.github.tangyi.file.uploader.HwObsUploader")
    ;

    private Integer value;

    private String desc;

    private String implClass;

    AttachUploaderEnum(int value, String desc, String implClass) {
        this.value = value;
        this.desc = desc;
        this.implClass = implClass;
    }

    public static AttachUploaderEnum matchByValue(Integer value) {
        for (AttachUploaderEnum item : AttachUploaderEnum.values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return FILE;
    }
}
