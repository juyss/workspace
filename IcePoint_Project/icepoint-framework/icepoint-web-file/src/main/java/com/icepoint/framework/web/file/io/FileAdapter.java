package com.icepoint.framework.web.file.io;

import com.icepoint.framework.web.file.entity.FileMetadata;

/**
 * @author Administrator
 */
public class FileAdapter implements Uploader {
    AbstractFileManager abstractFileManager;

    public FileAdapter(FileMetadata fileMetadata){

    }


    @Override
    public String getUploader(FileMetadata fileMetadata) {
        return null;
    }
}
