package com.icepoint.framework.web.file.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.file.entity.FileType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@Repository
public interface FileTypeRepository extends LongStdRepository<FileType> {

    Optional<FileType> findByEntityNameAndDeleted(String entityName, Integer deleted);

    List<FileType> findAllByIdInAndDeleted(List<Long> ids, Integer deleted);
}
