package com.github.tangyi.file.uploader;

import com.github.tangyi.common.basic.properties.SysProperties;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.file.api.model.Attachment;
import com.github.tangyi.file.enums.AttachUploaderEnum;
import com.github.tangyi.file.service.AttachmentService;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @author gaokx
 */
@Slf4j
public class UploadInvoker {

    private Map<Integer, IUploader> uploaderMap = null;

    private static UploadInvoker instance;

    private AttachmentService attachmentService;

    public UploadInvoker(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    public synchronized static UploadInvoker getInstance() {
        if (instance == null) {
            instance = new UploadInvoker(SpringContextHolder.getApplicationContext().getBean(AttachmentService.class));
        }
        return instance;
    }

    /**
     * 上传附件
     *
     * @param attachment attachment
     * @param bytes      bytes
     * @return Attachment
     * @author tangyi
     * @date 2020/04/05 14:27
     */
    public Attachment upload(Attachment attachment, byte[] bytes) {
        if (attachment == null || bytes == null)
            return null;
        if (attachment.getUploadType() == null) {
            String uploadType = SpringContextHolder.getApplicationContext().getBean(SysProperties.class).getAttachUploadType();
            if (StringUtils.isNotBlank(uploadType)) {
                attachment.setUploadType(Integer.parseInt(uploadType));
            }
        }
        IUploader uploader = this.getUploader(attachment.getUploadType());
        if (uploader == null)
            throw new CommonException("uploader is null");
        attachment = uploader.upload(attachment, bytes);
        if (attachment != null) {
            uploader.save(attachment);
        }
        return attachment;
    }


    /**
     * 上传文件
     *
     * @param attachment attachment
     * @param file      bytes
     * @return Attachment
     * @author tangyi
     * @date 2020/04/05 14:27
     */
    public Attachment uploadFile(Attachment attachment, File file) {
        if (attachment == null || file.length()==0)
            return null;
        if (attachment.getUploadType() == null) {
            String uploadType = SpringContextHolder.getApplicationContext().getBean(SysProperties.class).getAttachUploadType();
            if (StringUtils.isNotBlank(uploadType)) {
                attachment.setUploadType(Integer.parseInt(uploadType));
            }
        }
        IUploader uploader = this.getUploader(attachment.getUploadType());
        if (uploader == null)
            throw new CommonException("uploader is null");
        attachment = uploader.uploadFile(attachment, file);
        if (attachment != null) {
            uploader.save(attachment);
        }
        return attachment;
    }

    /**
     * 下载附件
     *
     * @param attachment attachment
     * @return Attachment
     * @author tangyi
     * @date 2020/04/05 14:29
     */
    public InputStream download(Attachment attachment) {
        if (attachment == null)
            return null;
        IUploader uploader = this.getUploader(attachment.getUploadType());
        if (uploader == null)
            throw new CommonException("uploader is null");
        return uploader.download(attachment);
    }

    /**
     * 删除附件
     *
     * @param attachment attachment
     * @return Attachment
     * @author tangyi
     * @date 2020/04/05 14:29
     */
    public boolean delete(Attachment attachment) {
        if (attachment == null)
            return Boolean.FALSE;
        IUploader uploader = this.getUploader(attachment.getUploadType());
        if (uploader == null)
            throw new CommonException("uploader is null");
        return uploader.delete(attachment);
    }

    /**
     * 批量删除附件
     *
     * @param ids ids
     * @return Attachment
     * @author tangyi
     * @date 2020/04/05 15:03
     */
    public boolean deleteAll(Long[] ids) {
        boolean result = false;
        for (Long id : ids) {
            // 查询出实体
            Attachment attachmentSearch = new Attachment();
            attachmentSearch.setId(id);
            attachmentSearch = attachmentService.get(attachmentSearch);
            IUploader uploader = getUploader(attachmentSearch.getUploadType());
            // 删除对应存储方式中的附件
            result = uploader.delete(attachmentSearch);
            if (result) {
                uploader.delete(attachmentSearch);
            }
        }
        return result;
    }

    /**
     * 获取附件实现类
     *
     * @param uploadType uploadType
     * @return IUploader
     * @author tangyi
     * @date 2020/04/05 14:17
     */
    private IUploader getUploader(Integer uploadType) {
        IUploader uploader;
        if (uploaderMap == null) {
            uploaderMap = new HashMap<>();
        }
        uploader = uploaderMap.get(uploadType);
        try {
            if (uploader == null) {
                // 如果没有初始化则创建
                String implClass = AttachUploaderEnum.matchByValue(uploadType).getImplClass();
                Class<?> clazz = Class.forName(implClass);
                Object bean = SpringContextHolder.getApplicationContext().getBean(clazz);
                if (bean != null) {
                    uploader = (IUploader) bean;
                } else {
                    uploader = (IUploader) clazz.newInstance();
                }
                uploaderMap.put(uploadType, uploader);
            }
        } catch (Exception e) {
            log.error("getUploader error:{}", e.getMessage(), e);
            return null;
        }
        return uploader;
    }
}
