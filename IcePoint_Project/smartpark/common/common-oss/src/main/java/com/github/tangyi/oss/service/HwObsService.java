package com.github.tangyi.oss.service;

import com.github.tangyi.oss.config.HwObsConfig;
import com.obs.services.ObsClient;
import com.obs.services.model.*;

import java.io.File;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

/**
 * 华为obs 上传
 */

@Service
public class HwObsService {
    private static Logger logger = LoggerFactory.getLogger(HwObsService.class);
    private HwObsConfig hwObsConfig;
    private ObsClient obsClient;

    public HwObsService(HwObsConfig hwObsConfig) {
        this.hwObsConfig = hwObsConfig;
        //创建客户端
        obsClient = new ObsClient(hwObsConfig.getAccessKey(), hwObsConfig.getSecretKey(), hwObsConfig.getEndPoint());
        //创建基本桶
        boolean exists = obsClient.headBucket(hwObsConfig.getBaseBucketName());
        if (!exists) {
            obsClient.createBucket(hwObsConfig.getBaseBucketName());
        }
    }

    public String upload(byte[] bytes, String attachName) {
        PutObjectRequest request = new PutObjectRequest(hwObsConfig.getBaseBucketName(), attachName);
        request.setInput(new ByteArrayInputStream(bytes));
        request.setProgressListener(status -> {
            // 获取上传平均速率
            logger.info("AverageSpeed: {}", status.getAverageSpeed());
            // 获取上传进度百分比
            logger.info("TransferPercentage: {}", status.getTransferPercentage());
        });
// 每上传1MB数据反馈上传进度
        request.setProgressInterval(1024 * 1024L);
        PutObjectResult putObjectResult = obsClient.putObject(request);
        return putObjectResult.getObjectUrl();
    }

    public boolean delete(String attachName) {
        DeleteObjectResult deleteObjectResult = obsClient.deleteObject(hwObsConfig.getBaseBucketName(), attachName);
        return deleteObjectResult.isDeleteMarker();
    }
    public String uloadFile(File file,String attachName){
        PutObjectResult putObjectResult = obsClient.putObject(hwObsConfig.getBaseBucketName(), attachName, file);
       return  putObjectResult.getObjectUrl();

    }




    /**
     * 文件下载
     */
    public InputStream downloadFile(String fileName) {
        try {
            ObsObject obsObject = obsClient.getObject(hwObsConfig.getBaseBucketName(), fileName);
            return obsObject.getObjectContent();
        } catch (Exception e) {
            logger.error("下载附件失败！", e);
        }
        return null;
    }

}
