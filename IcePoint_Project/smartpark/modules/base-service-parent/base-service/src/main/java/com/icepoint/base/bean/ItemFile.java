package com.icepoint.base.bean;

import lombok.Data;

import java.util.Date;


@Data
public class ItemFile {

    private int fileId;
    private String url;
    private String fileSource;
    private int fileSourceId;
    private String fileType;
    private String createDate;
    private int createUser;
    private String fileName;
    private int status;
    private String duration;
    private String sourceFileName;
    private String source;
    private int id;
    private String imgUrl;
    private Date uploadTime;
    private int bussinessId;

}
