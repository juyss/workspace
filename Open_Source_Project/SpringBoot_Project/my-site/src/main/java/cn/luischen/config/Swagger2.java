package cn.luischen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置
 * Created by Donghua.Chen on 2018/4/20.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi(Environment environment) {

        Profiles profiles = Profiles.of("dev");
        boolean isDev = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(isDev)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.luischen.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Juyss", "juyss.com", "1028507471@qq.com");
        return new ApiInfoBuilder()
                .title("Juyss Site Swagger Restful API")
                .description("更多Spring Boot相关文章请关注：http://juyss.com/")
                .termsOfServiceUrl("http://juyss.com/")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
