package com.github.tangyi.file.uploader;

import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.utils.FileUtil;
import com.github.tangyi.file.api.model.Attachment;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 上传到本地目录
 *
 * @author gaokx
 */
@Slf4j
@Service
public class FileUploader extends AbstractUploader {

    @Override
    public Attachment upload(Attachment attachment, byte[] bytes) {
        try {
            String fileRealDirectory = getFileRealDirectory(attachment, attachment.getId().toString());
            fileRealDirectory = fileRealDirectory.replaceAll("\\\\", "/");
            String fileName = attachment.getAttachName();
            attachment.setAttachSize(String.valueOf(bytes.length));
            log.info("file read directory: {}", fileRealDirectory);
            FileUtil.createDirectory(fileRealDirectory);
            log.info("start write file: {}", fileName);
            saveFileFormByteArray(bytes, fileRealDirectory, fileName);
            log.info("write file finished: {}", fileName);
            attachment.setFastFileId(fileRealDirectory);
            return attachment;
        } catch (Exception e) {
            log.error("FileUploader error：{}, {}", attachment.getAttachName(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Attachment uploadFile(Attachment attachment, File file) {
        try {
            String fileRealDirectory = getFileRealDirectory(attachment, attachment.getId().toString());
            fileRealDirectory = fileRealDirectory.replaceAll("\\\\", "/");
            String fileName = attachment.getAttachName();
            attachment.setAttachSize(String.valueOf(file.length()));
            log.info("file read directory: {}", fileRealDirectory);
            FileUtil.createDirectory(fileRealDirectory);
            log.info("start write file: {}", fileName);
            attachment.setFastFileId(fileRealDirectory);
            return attachment;
        } catch (Exception e) {
            log.error("FileUploader error：{}, {}", attachment.getAttachName(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    public InputStream download(Attachment attachment) {
        String path = attachment.getFastFileId() + File.separator + attachment.getAttachName();
        InputStream input = null;
        try {
            String fileRealDirectory = getFileRealDirectory(attachment, attachment.getId().toString());
            fileRealDirectory = fileRealDirectory.replaceAll("\\\\", "/");
            if (StringUtils.isNotBlank(fileRealDirectory) && !fileRealDirectory.equals(attachment.getFastFileId()))
                throw new CommonException("attach path validate failure！attachPath：" + attachment.getFastFileId() + ", fileRealDirectory: " + fileRealDirectory);
            input = new FileInputStream(new File(path));
        } catch (Exception e) {
            log.error("download attachment failure: {}", e.getMessage(), e);
        }
        return input;
    }

    @Override
    public boolean delete(Attachment attachment) {
        String path = attachment.getFastFileId()
                + File.separator
                + attachment.getAttachName();
        File file = new File(path);
        if (file.delete()) {
            FileUtil.deleteDirectory(attachment.getFastFileId());
            return super.delete(attachment);
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean deleteAll(Attachment attachment) {
        return false;
    }

    private void saveFileFormByteArray(byte[] b, String path, String fileName) throws IOException {
        BufferedOutputStream fs = new BufferedOutputStream(new FileOutputStream(path + "/" + fileName, true));
        fs.write(b);
        fs.flush();
        fs.close();
    }
}
