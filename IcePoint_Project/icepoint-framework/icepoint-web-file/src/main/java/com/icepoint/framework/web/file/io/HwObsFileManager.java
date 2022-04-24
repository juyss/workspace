package com.icepoint.framework.web.file.io;

import com.icepoint.framework.web.file.entity.FileMetadata;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Administrator
 */
@Slf4j
public class HwObsFileManager extends AbstractFileManager {

    public static final String BASE_BUCKET_NAME = "icePoint";

    private final ObsClient client;

    public HwObsFileManager(ObsClient client) {
        this.client = client;
    }


    public String upload(byte[] file, String name) {
        PutObjectRequest request = new PutObjectRequest(BASE_BUCKET_NAME, name);
        request.setInput(new ByteArrayInputStream(file));
        request.setProgressListener(status -> {
            // 获取上传平均速率
            log.info("AverageSpeed: {}", status.getAverageSpeed());
            // 获取上传进度百分比
            log.info("TransferPercentage: {}", status.getTransferPercentage());
        });
        // 每上传1MB数据反馈上传进度
        request.setProgressInterval(1024 * 1024L);
        PutObjectResult putObjectResult = client.putObject(request);
        return putObjectResult.getObjectUrl();
    }


    @Override
    public String upload(File file, FileMetadata fileMetadata) {
        PutObjectResult putObjectResult = client.putObject(BASE_BUCKET_NAME, fileMetadata.getName(), file);
        return putObjectResult.getObjectUrl();
    }


    @Override
    public String upload(InputStream in, FileMetadata metadata) throws IOException {
        PutObjectResult putObjectResult = client.putObject(BASE_BUCKET_NAME, metadata.getName(), in);
        return putObjectResult.getObjectUrl();
    }

    @Override
    public void delete(FileMetadata metadata) throws IOException {

    }
}
