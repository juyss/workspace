package com.github.tangyi.user.uploader;

import com.github.tangyi.oss.service.HwObsService;
import com.github.tangyi.user.api.module.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 上传到华为obs云存储
 *
 * @author tangyi
 * @date 2020/04/05 13:36
 */
@Slf4j
@Service
public class HwObsUploader extends AbstractUploader {

    private HwObsService hwObsService;

    public HwObsUploader(HwObsService hwObsService) {
        this.hwObsService = hwObsService;
    }

    @Override
    public Attachment upload(Attachment attachment, byte[] bytes) {
        attachNameHandle(attachment);

        String result = hwObsService.upload(bytes, attachment.getAttachName());
        attachment.setUploadResult(result);
        attachment.setPreviewUrl(attachment.getUploadResult());
        return attachment;
    }

    @Override
    public InputStream download(Attachment attachment) {
        return null;
    }

    @Override
    public boolean delete(Attachment attachment) {
        return hwObsService.delete(attachment.getAttachName());
    }

    @Override
    public boolean deleteAll(Attachment attachment) {
        return false;
    }

    private void attachNameHandle(Attachment attachment) {
        String name = "";
        if (StringUtils.isNotBlank(attachment.getBusiModule())) {
            name = attachment.getBusiModule() + "/";
        }
        if (StringUtils.isNotBlank(attachment.getAttachType())) {
            name += attachment.getAttachType() + "/";
        }
        name += attachment.getAttachName();
        attachment.setAttachName(name);
    }
}
