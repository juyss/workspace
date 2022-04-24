package com.icepoint.framework.web.file.entity;

import com.icepoint.framework.data.domain.LongStdEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 文件元数据
 *
 * @author Jiawei Zhao
 */
@Table(name = "sys_file_metadata")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
public class FileMetadata extends LongStdEntity {

    /**
     * 文件id，除id外文件的唯一标识，扩展用
     */
    private String fileId;

    /**
     * 文件类型id
     */
    @NotNull
    private Long fileTypeId;

    /**
     * 实体id
     */
    @NotNull
    private Long entityId;

    /**
     * 文件名称
     */
    @NotEmpty
    private String name;

    /**
     * 文件后缀名
     */
    @NotEmpty
    private String ext;

    /**
     * 文件大小
     */
    @Min(0)
    @NotNull
    private Long size;

    /**
     * 文件的访问url
     */
    @NotEmpty
    private String url;

    /**
     * 文件存放路径，可以理解为应用内部访问文件的url
     */
    @NotEmpty
    private String path;
   
//可以考虑添加，并存储本地物理表
//    /**
//     * 音视频时长
//     */
//    private Integer duration;
//    /**
//     * 宽
//     */
//    private Integer width;
//    /**
//     * 高
//     */
//    private Integer height;
//    /**
//     * 缩略文件
//     */
//    private String thumbFile;
//    /**
//     * 文件描述	
//     */
//    private String fileDesc;
    
    public static FileMetadata of(MultipartFile file, FileType fileType) {
        String filename = file.getName();

        return FileMetadata.builder()
                .name(StringUtils.getFilename(filename))
                .ext(StringUtils.getFilenameExtension(filename))
                .size(file.getSize())
                .fileTypeId(fileType.getId())
                .build();
    }
}
