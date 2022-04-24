package com.github.tangyi.file.uploader;

import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.file.service.AttachmentService;
import com.github.tangyi.file.api.model.Attachment;
import java.io.File;
import java.io.InputStream;
import org.apache.commons.lang3.StringUtils;

/**
 * @author gaokx
 */
public abstract class AbstractUploader implements IUploader {

    @Override
    public int save(Attachment attachment) {
        return SpringContextHolder.getApplicationContext().getBean(AttachmentService.class).insert(attachment);
    }

    @Override
    public boolean delete(Attachment attachment) {
        return SpringContextHolder.getApplicationContext().getBean(AttachmentService.class).delete(attachment) > 0;
    }

    @Override
    public abstract Attachment upload(Attachment attachment, byte[] bytes);




    @Override
    public abstract InputStream download(Attachment attachment);

    /**
     * 获取附件存储目录
     *
     * @param attachment attachment
     * @param id         id
     * @return String
     */
    public String getFileRealDirectory(Attachment attachment, String id) {
        String applicationCode = attachment.getApplicationCode();
        String busiId = attachment.getBusiId();
        String fileName = attachment.getAttachName();
        String fileRealDirectory = SpringContextHolder.getApplicationContext().getBean(SysProperties.class).getAttachPath() + File.separator
                + applicationCode + File.separator;
        // 有分类就加上
        if (StringUtils.isNotBlank(attachment.getBusiModule())) {
            String busiModule = attachment.getBusiModule();
            fileRealDirectory = fileRealDirectory + busiModule + File.separator;
        }
        if (StringUtils.isNotBlank(attachment.getBusiType())) {
            String busiType = attachment.getBusiType();
            fileRealDirectory = fileRealDirectory + busiType + File.separator;
        }
        fileRealDirectory = fileRealDirectory + busiId;
        return fileRealDirectory;
    }
}
