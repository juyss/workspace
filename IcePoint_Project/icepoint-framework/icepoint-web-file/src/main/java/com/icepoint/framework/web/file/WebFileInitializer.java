package com.icepoint.framework.web.file;

import com.icepoint.framework.web.core.AbstractApplicationInitializer;
import com.icepoint.framework.web.core.dao.ResponseMessageRepository;
import com.icepoint.framework.web.file.io.FileMessage;
import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@Component
public class WebFileInitializer extends AbstractApplicationInitializer<FileMessage> {

    public WebFileInitializer(ResponseMessageRepository messageRepository) {
        super(messageRepository);
    }

    @Override
    public void initialize() {
        // 未定义
    }

    @Override
    protected String getModuleName() {
        return "WebFile";
    }

    @Override
    protected String getMissingMessage(FileMessage message) {
        switch (message) {
            case FILE_UPLOAD_FAILED:
                return "文件上传失败, 失败原因: {reason}";
            case FILE_DELETE_FAILED:
                return "文件删除失败, 失败原因: {reason}";
            default:
                throw new IllegalStateException("Unexpected value: " + message);
        }
    }


}
