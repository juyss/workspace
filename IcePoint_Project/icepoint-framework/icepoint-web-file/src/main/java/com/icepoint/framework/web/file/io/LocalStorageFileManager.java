package com.icepoint.framework.web.file.io;


import com.icepoint.framework.web.file.entity.FileMetadata;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jiawei Zhao
 */
public class LocalStorageFileManager implements FileManager {

    public String upload(byte[] file, String name) {
        return null;
    }

    public String upload(File file, String fileName) {
        return null;
    }

    @Override
    public String upload(InputStream in, FileMetadata metadata) throws IOException {
        // 未实现
        return null;
    }

    @Override
    public void delete(FileMetadata metadata) throws IOException {
        // 未实现
    }
}
