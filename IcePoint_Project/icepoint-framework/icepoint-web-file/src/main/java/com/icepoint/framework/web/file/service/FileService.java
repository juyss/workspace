package com.icepoint.framework.web.file.service;


import com.icepoint.framework.web.file.entity.FileMetadata;
import com.icepoint.framework.web.file.entity.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件服务
 *
 * @author Jiawei Zhao
 */
public interface FileService {

    List<FileMetadata> upload(MultipartFile[] files, FileType fileType);

    List<FileMetadata> upload(MultipartFile[] files, Long fileTypeId);

    List<FileMetadata> upload(MultipartFile[] files, String entityName);

    void delete(FileMetadata metadata);

    void delete(String fileId);

    void delete(Long metadataId);

    void deleteAll(FileMetadata[] metadataList);

    void deleteAll(String[] fileIds);

    void deleteAll(Long[] metadataIds);

    List<FileMetadata> findFileMetadataList(FileType fileType, Long entityId);

    List<FileMetadata> findFileMetadataList(Long fileTypeId, Long entityId);

    List<FileMetadata> findFileMetadataList(String entityName, Long entityId);


}
