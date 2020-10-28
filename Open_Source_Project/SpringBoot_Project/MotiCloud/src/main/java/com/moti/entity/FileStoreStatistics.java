package com.moti.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName: FileStoreStatistics
 * @Description: 文件仓库数据统计类
 * @author: xw
 * @date 2020/2/10 21:40
 * @Version: 1.0
 **/
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FileStoreStatistics implements Serializable {

    /**
     * 文件仓库
     */
    private FileStore fileStore;
    /**
     * 文档数
     */
    private Integer doc;
    /**
     * 音乐数
     */
    private Integer music;
    /**
     * 视频数
     */
    private Integer video;
    /**
     * 图像数
     */
    private Integer image;
    /**
     * 其他
     */
    private Integer other;
    /**
     * 文件数
     */
    private Integer fileCount;
    /**
     * 文件夹数
     */
    private Integer folderCount;

}
