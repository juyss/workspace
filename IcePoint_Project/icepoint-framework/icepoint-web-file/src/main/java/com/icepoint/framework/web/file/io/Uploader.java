package com.icepoint.framework.web.file.io;

import com.icepoint.framework.web.file.entity.FileMetadata;

/**
 * @author Administrator
 */
public interface Uploader {

    String getUploader(FileMetadata fileMetadata);

}
