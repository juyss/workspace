package com.github.tangyi.file.api.feign.fallback;

import com.github.pagehelper.PageInfo;
import com.github.tangyi.common.basic.vo.AttachmentVo;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.file.api.feign.AttachmentServiceClient;
import com.github.tangyi.file.api.model.Attachment;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件中心服务断路器
 *
 * @author gaokx
 * @date 2020/11/30 13:07
 */
@Slf4j
@Component
public class AttachmentServiceClientFallbackImpl implements AttachmentServiceClient {

    private Throwable throwable;

    @Override
    public ResponseBean<?> upload(Attachment smsDto) {
        log.error("Feign upload message failed : {}, {}", smsDto, throwable);
        return null;
    }

    @Override
    public ResponseBean<?> upload(MultipartFile file, Integer uploadType) {
        log.error("Feign upload message failed : {}, {}", file.getOriginalFilename(), throwable);
        return null;
    }

    @Override
    public ResponseBean<Attachment> attachment(Long id) {
        log.error("Feign attachment message failed :  {}, {}", id, throwable);
        return null;
    }

    @Override
    public PageInfo<Attachment> attachmentList(String pageNum, String pageSize, String sort,
                                               String order, Attachment attachment) {
        log.error("Feign attachmentList message failed : {}, {},{},{},{},{}", pageNum,
                pageSize, sort, order, attachment, throwable);
        return null;
    }

    @Override
    public ResponseBean<Boolean> delete(Long id) {
        log.error("Feign delete message failed : {}, {}", id, throwable);
        return null;
    }

    @Override
    public ResponseBean<Boolean> deleteAllAttachments(Long[] ids) {
        log.error("Feign deleteAllAttachments message  failed : {}, {}", ids, throwable);
        return null;
    }

    @Override
    public ResponseBean<List<AttachmentVo>> findById(Long[] ids) {
        log.error("Feign findById  message failed : {}, {}", ids, throwable);
        return null;
    }

    @Override
    public ResponseBean<Boolean> canPreview(Long id) {
        log.error("Feign canPreview message failed : {}, {}", id, throwable);
        return null;
    }

    @Override
    public void preview(HttpServletResponse response, Long id) {
        log.error("Feign preview message failed : {}, {}", id, throwable);
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
