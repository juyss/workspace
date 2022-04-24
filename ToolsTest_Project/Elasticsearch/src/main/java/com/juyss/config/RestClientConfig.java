package com.juyss.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: RestClientConfig
 * @Desc: RestHighLevelClient配置类
 * @package com.juyss.config
 * @project elasticsearch
 * @date 2020/12/30 18:17
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {


    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("192.168.112.133:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
