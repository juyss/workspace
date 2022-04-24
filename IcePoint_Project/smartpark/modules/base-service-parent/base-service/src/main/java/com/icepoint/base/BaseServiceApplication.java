package com.icepoint.base;

import com.github.tangyi.common.core.constant.CommonConstant;
import com.huawei.it.eip.ump.client.producer.Producer;
import com.huawei.it.eip.ump.common.exception.UmpException;
import com.icepoint.base.config.mybatis.MybatisConfiguration;
import com.icepoint.base.web.sys.properties.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.*;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;

@Import(MybatisConfiguration.class)
@MapperScan({"com.icepoint.base.web.**.mapper", "com.github.tangyi.mapper"})
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = {CommonConstant.BASE_PACKAGE})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.icepoint.base", CommonConstant.BASE_PACKAGE},
        exclude = {MapperAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableConfigurationProperties({FileProperties.class})
@PropertySource({"classpath:mqs.properties"})
public class BaseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseServiceApplication.class, args);
    }

    @Bean
    public Producer mqsProducer(@Value("${servers}") String servers,
                                @Value("${appId}") String appId,
                                @Value("${appSecret}") String appSecret,
                                @Value("${topic}") String topic,
                                @Value("${tag}") String tag) {
        Producer producer = new Producer();
        producer.setUmpNamesrvUrls(servers);
        producer.setAppId(appId); // 设置客户端账号
        producer.setAppSecret(appSecret); // 设置客户端密钥
        producer.setTopic(topic); // 设置Topic Name
        producer.setEncryptTransport(false);// 设置是否需要加密传输
        producer.setTags(tag);
        try {
            producer.start();
        } catch (UmpException e) {
            e.printStackTrace();
        }
        return producer;
    }
}
