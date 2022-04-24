package com.icepoint.framework.web.file;


import com.icepoint.framework.core.util.ApplicationContextUtils;
import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.web.file.dao.FileTypeRepository;
import com.icepoint.framework.web.file.entity.FileMetadata;
import com.icepoint.framework.web.file.entity.FileType;
import org.hibernate.Hibernate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
public class MergedFileType {

    private final List<FileType> fileTypes;

    public MergedFileType(FileType fileType) {
        Assert.notNull(fileType, MessageTemplates.notNull("fileType"));

        String includedIds = fileType.getIncludedIds();
        if (!StringUtils.hasText(includedIds)) {
            this.fileTypes = Collections.singletonList(Hibernate.unproxy(fileType, FileType.class));
        } else {
            FileTypeRepository repository = ApplicationContextUtils.getRequiredBean(FileTypeRepository.class);

            this.fileTypes = new ArrayList<>(fetchIncludedFileTypeIds(repository, includedIds)).stream()
                    .map(type -> Hibernate.unproxy(type, FileType.class))
                    .collect(Collectors.toList());
        }
    }

    public void validateCanUpload(FileMetadata fileMetadata) {
        FileType fileType = fileTypes.stream()
                .filter(type -> Objects.equals(type.getId(), fileMetadata.getFileTypeId()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("上传的文件类型与文件元数据不对应"));

        Long maxSize = fileType.getMaxSize();
        String acceptableTypes = fileType.getAcceptableTypes();
    }

    private List<FileType> fetchIncludedFileTypeIds(FileTypeRepository repository, String includedIds) {
        if (!StringUtils.hasText(includedIds))
            return Collections.emptyList();

        List<Long> idList = Arrays.stream(StringUtils.delimitedListToStringArray(includedIds, ","))
                .map(Long::valueOf)
                .collect(Collectors.toList());

        List<FileType> fileTypes = repository.findAllByIdInAndDeleted(idList, 0);

        for (FileType fileType : fileTypes) {
            String ids = fileType.getIncludedIds();
            if (StringUtils.hasText(ids)) {
                fetchIncludedFileTypeIds(repository, ids);
            }
        }

        return fileTypes;
    }

    private String[] toArray(String ids) {
        return StringUtils.delimitedListToStringArray(ids, ",");
    }
}
