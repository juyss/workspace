package com.github.tangyi.file.uploader;

import com.github.tangyi.file.api.model.Attachment;
import com.github.tangyi.oss.service.QiNiuUtil;

import java.io.File;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 上传到七牛云
 *
 * @author gaokx
 */
@Slf4j
@Service
public class QiNiuUploader extends AbstractUploader {

    @Override
    public Attachment upload(Attachment attachment, byte[] bytes) {
        String result = QiNiuUtil.getInstance().upload(bytes, attachment.getAttachName());
        attachment.setUploadResult(result);
        attachment.setPreviewUrl(attachment.getUploadResult());
        return attachment;
    }

    @Override
    public Attachment uploadFile(Attachment attachment, File file) {
        return null;
    }

    @Override
    public InputStream download(Attachment attachment) {
        return null;
    }

    @Override
    public boolean delete(Attachment attachment) {
        return QiNiuUtil.getInstance().delete(attachment.getAttachName());
    }

    @Override
    public boolean deleteAll(Attachment attachment) {
        return false;
    }
}
