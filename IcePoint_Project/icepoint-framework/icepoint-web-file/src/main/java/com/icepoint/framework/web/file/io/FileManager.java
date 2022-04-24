package com.icepoint.framework.web.file.io;

import com.icepoint.framework.web.file.entity.FileMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件管理器
 *
 * @author Jiawei Zhao
 */
public interface FileManager {


    String upload(InputStream in, FileMetadata metadata) throws IOException;

    default String upload(File file, FileMetadata metadata) throws IOException {
        return   upload(new FileInputStream(file), metadata);
    }

    default void upload(MultipartFile file, FileMetadata metadata) throws IOException {
        upload(file.getInputStream(), metadata);
    }

    default void upload(byte[] bytes, FileMetadata metadata) throws IOException {
        upload(new ByteArrayInputStream(bytes), metadata);
    }

    void delete(FileMetadata metadata) throws IOException;



}
