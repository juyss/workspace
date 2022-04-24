package com.icepoint.framework.web.file.service.impl;

import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.web.core.message.DataNotFoundMessageException;
import com.icepoint.framework.web.core.message.RootCauseMessageException;
import com.icepoint.framework.web.core.util.MessageAssert;
import com.icepoint.framework.web.file.MergedFileType;
import com.icepoint.framework.web.file.dao.FileMetadataRepository;
import com.icepoint.framework.web.file.dao.FileTypeRepository;
import com.icepoint.framework.web.file.entity.FileMetadata;
import com.icepoint.framework.web.file.entity.FileType;
import com.icepoint.framework.web.file.io.FileManager;
import com.icepoint.framework.web.file.io.FileMessage;
import com.icepoint.framework.web.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {


    private final FileMetadataRepository metadataRepository;

    private final FileTypeRepository typeRepository;

    private final FileManager manager;

    @Override
    public List<FileMetadata> upload(MultipartFile[] files, FileType fileType) {
        MessageAssert.notEmpty(files);

        MergedFileType merged = merge(fileType);

        List<FileMetadata> metadataList = new ArrayList<>();
        for (MultipartFile file : files) {
            FileMetadata metadata = FileMetadata.of(file, fileType);
            merged.validateCanUpload(metadata);

            try {
                manager.upload(file, metadata);
            } catch (IOException e) {
                throw new RootCauseMessageException(FileMessage.FILE_UPLOAD_FAILED.getCode(), e);
            }
            metadataList.add(metadata);
        }

        return metadataList;
    }

    @Override
    public List<FileMetadata> upload(MultipartFile[] files, Long fileTypeId) {
        FileType fileType = typeRepository.findById(fileTypeId)
                .orElseThrow(DataNotFoundMessageException::new);
        return upload(files, fileType);
    }

    @Override
    public List<FileMetadata> upload(MultipartFile[] files, String entityName) {
        FileType fileType = typeRepository.findByEntityNameAndDeleted(entityName, 0)
                .orElseThrow(DataNotFoundMessageException::new);
        return upload(files, fileType);
    }

    @Override
    public void delete(FileMetadata metadata) {
        Assert.notNull(metadata, MessageTemplates.notNull("metadata"));

        try {
            manager.delete(metadata);
        } catch (IOException e) {
            throw new RootCauseMessageException(FileMessage.FILE_DELETE_FAILED.getCode(), e);
        }
    }

    @Override
    public void delete(String fileId) {
        Assert.hasText(fileId, MessageTemplates.notNull("fileId"));

        FileMetadata metadata = metadataRepository.findByFileIdAndDeleted(fileId, 0)
                .orElseThrow(() -> new IllegalArgumentException(MessageTemplates.notFound("fileId", fileId)));

        delete(metadata);
    }

    @Override
    public void delete(Long metadataId) {
        Assert.notNull(metadataId, MessageTemplates.notNull("metadataId"));

        metadataRepository.findById(metadataId)
                    .orElseThrow(() -> new IllegalArgumentException(MessageTemplates.notFound("metadataId", metadataId)));
    }

    @Override
    public void deleteAll(FileMetadata[] metadataList) {
        Assert.notEmpty(metadataList, MessageTemplates.notNull("metadataList"));

        for (FileMetadata metadata : metadataList) {
            delete(metadata);
        }
    }

    @Override
    public void deleteAll(String[] fileIds) {
        Assert.notEmpty(fileIds, MessageTemplates.notNull("fileIds"));

        for (String fileId : fileIds) {
            delete(fileId);
        }
    }

    @Override
    public void deleteAll(Long[] metadataIds) {
        Assert.notEmpty(metadataIds, MessageTemplates.notNull("metadataIds"));

        for (Long metadataId : metadataIds) {
            delete(metadataId);
        }
    }

    @Override
    public List<FileMetadata> findFileMetadataList(FileType fileType, Long entityId) {
        return findFileMetadataList(fileType.getId(), entityId);
    }

    @Override
    public List<FileMetadata> findFileMetadataList(Long fileTypeId, Long entityId) {
        Assert.notNull(fileTypeId, MessageTemplates.notNull("fileTypeId"));
        Assert.notNull(entityId, MessageTemplates.notNull("entityId"));

        return metadataRepository.findAllByFileTypeIdAndEntityIdAndDeleted(fileTypeId, entityId, 0);
    }

    @Override
    public List<FileMetadata> findFileMetadataList(String entityName, Long entityId) {
        FileType fileType = typeRepository.findByEntityNameAndDeleted(entityName, 0)
                .orElseThrow(() -> new IllegalArgumentException(MessageTemplates.notFound("entityName", entityName)));

        return findFileMetadataList(fileType, entityId);
    }

    private MergedFileType merge(FileType fileType) {
        return new MergedFileType(fileType);
    }
}
