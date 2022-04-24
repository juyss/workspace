package com.juyss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SwaggerConfig
 * @Desc:
 * @package com.juyss.config
 * @project KuangStudy-SpringBoot
 * @date 2020/10/25 15:50
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment){

        Profiles profiles = Profiles.of("dev");
        boolean isDev = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .enable(isDev);
    }

    private ApiInfo getApiInfo(){
        Contact contact = new Contact("Juyss", "http://juyss.com", "1028507471@qq.com");

        return new ApiInfo( "Swagger Api Documentation Title",
                            "Swagger Api Documentation Description",
                            "1.0",
                            "urn:tos",
                            contact,
                            "Apache 2.0",
                            "http://www.apache.org/licenses/LICENSE-2.0",
                            new ArrayList<>());
    }

}
