package com.github.tangyi.common.feign.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * RestTemplateConfigo
 *
 * @author tangyi
 * @date 2019/3/16 20:41
 */
@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(6000);
        factory.setReadTimeout(6000);
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getInterceptors().add(logClientHttpRequestInterceptor());
        return restTemplate;
    }


    private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);

    private ClientHttpRequestInterceptor logClientHttpRequestInterceptor() {
        return ((httpRequest, bytes, clientHttpRequestExecution) -> {

            if (logger.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("------------------------rest request----------------------------")
                        .append("\r\n")
                        .append("request url:" + httpRequest.getURI())
                        .append("\r\n")
                        .append("request header:" + httpRequest.getHeaders().toString())
                        .append("\r\n")
                        .append("request method:" + httpRequest.getMethodValue())
                        .append("\r\n")
                        .append("request body:" + new String(bytes))
                        .append("\r\n")
                        .append("------------------------------------------------------------------------");
            }
            ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] bys = new byte[512];
                int length;
                while ((length = response.getBody().read(bys)) > 0) {
                    outputStream.write(bys, 0, length);
                }
            }

            if (logger.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("\r\n")
                        .append("\r\n")
                        .append("--------------------------------rest response---------------------------")
                        .append("\r\n")
                        .append("response code:" + response.getStatusCode())
                        .append("\r\n")
                        .append("response code msg:" + response.getStatusText())
                        .append("\r\n")
                        .append("response headers:" + response.getHeaders().toString())
                        .append("\r\n")
                        .append("response body:" + new String(outputStream.toByteArray(), StandardCharsets.UTF_8))
                        .append("\r\n" + "---------------------------------------------------------------");

                logger.debug(sb.toString());
            }

            return new ClientHttpResponseWrapper(response, outputStream.toByteArray());
        });
    }

    class ClientHttpResponseWrapper implements ClientHttpResponse {

        private ClientHttpResponse response;
        private InputStream in;

        public ClientHttpResponseWrapper(ClientHttpResponse response, byte[] body) {
            this.response = response;
            in = new ByteArrayInputStream(body);
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return response.getStatusCode();
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return response.getRawStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return response.getStatusText();
        }

        @Override
        public void close() {
            try {
                in.close();
                response.close();
            } catch (IOException e) {
            }
        }

        @Override
        public InputStream getBody() throws IOException {
            return in;
        }

        @Override
        public HttpHeaders getHeaders() {
            return response.getHeaders();
        }
    }

}
