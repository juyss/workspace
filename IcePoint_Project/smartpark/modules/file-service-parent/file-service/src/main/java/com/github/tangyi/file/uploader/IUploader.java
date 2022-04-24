package com.github.tangyi.file.uploader;

import com.github.tangyi.file.api.model.Attachment;

import java.io.File;
import java.io.InputStream;

/**
 * @author gaokx
 */
public interface IUploader {

    /**
     * 上传附件
     * @param attachment attachment
     * @param bytes bytes
     * @return Attachment
     */
    Attachment upload(Attachment attachment, byte[] bytes);

    Attachment uploadFile(Attachment attachment, File file);
    /**
     * 保存附件信息
     * @param attachment attachment
     * @return int
     */
    int save(Attachment attachment);

    /**
     * 下载附件
     * @param attachment attachment
     * @return InputStream
     */
    InputStream download(Attachment attachment);

    /**
     * 删除附件
     * @param attachment attachment
     * @return boolean
     */
    boolean delete(Attachment attachment);

    /**
     * 批量删除
     * @param attachment attachment
     * @return boolean
     */
    boolean deleteAll(Attachment attachment);


}
