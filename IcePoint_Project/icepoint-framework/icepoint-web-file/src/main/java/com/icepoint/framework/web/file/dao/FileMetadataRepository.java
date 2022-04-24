package com.icepoint.framework.web.file.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.file.entity.FileMetadata;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@Repository
public interface FileMetadataRepository extends LongStdRepository<FileMetadata> {

    Optional<FileMetadata> findByFileIdAndDeleted(String fileId, Integer deleted);

    List<FileMetadata> findAllByFileTypeIdAndEntityIdAndDeleted(Long fileTypeId, Long entityId, Integer deleted);
}
