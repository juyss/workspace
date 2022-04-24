package com.icepoint.base.bean;

import lombok.Data;

@Data
public class ItemPicture {

    private int picId;
    private String url;
    private String picSource;
    private int picSourceId;
    private String picType;
    private String createDate;
    private Long createUser;
    private int status;
    private String fileName;
    private String sourceFileName;
    private String source;

}
