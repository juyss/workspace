package com.icepoint.framework.autoconfigure.web.file;

import com.icepoint.framework.web.file.io.HwObsFileManager;
import com.obs.services.ObsClient;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.icepoint.framework.web.file.io.HwObsFileManager.BASE_BUCKET_NAME;

/**
 * @author Administrator
 */
public class FileManagerAutoConfiguration {

    /**
     * 华为obs云存储配置
     *
     * @date 14:39 2020/11/20
     **/
    @Configuration
    @ConfigurationProperties(prefix = "hwobs")
    @ConditionalOnExpression("!'${hwobs}'.isEmpty()")
    @ConditionalOnClass(ObsClient.class)
    public static class HwObsAutoConfiguration {

        private String endPoint;
        private String accessKey;
        private String secretKey;


        @Bean
        public HwObsFileManager hwObsFileManager() {
            ObsClient obsClient = new ObsClient(this.accessKey, this.secretKey, this.endPoint);
            boolean exists = obsClient.headBucket(BASE_BUCKET_NAME);
            if (!exists) {
                obsClient.createBucket(BASE_BUCKET_NAME);
            }
            return new HwObsFileManager(obsClient);
        }

    }
//    /**
//     * @Author ck
//     * @Description fastDfs配置
//     * @Date 14:39 2020/11/20
//     **/
//    @Configuration
//    @Import(FastDfsConfiguration.class)
//    @EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
//    @ConditionalOnExpression("!'${fdfs}'.isEmpty()")
//    public static class FastDfsConfiguration {
//
//    }

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "qiniu")
    @ConditionalOnExpression("!'${qiniu}'.isEmpty()")
    public static class QiNiuConfig {

        private String accessKey;

        private String secretKey;

        private String bucket;

        /**
         * 外部访问域名
         */
        private String domainOfBucket;

        /**
         * 链接超时时间，单位秒
         */
        private Integer expire;


    }

}
