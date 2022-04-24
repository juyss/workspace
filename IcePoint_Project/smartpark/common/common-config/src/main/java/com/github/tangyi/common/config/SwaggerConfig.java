package com.github.tangyi.common.config;

import com.github.tangyi.common.security.constant.SecurityConstant;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置增强
 *
 * @author gaokx
 * @date 2020/10/22 21:26
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@EnableSwaggerBootstrapUI
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket createRestApi() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(authorizationParameter());
        parameterList.add(tenantCodeParameter());
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();//.globalOperationParameters(parameterList);
        setDocketSecurity(docket);
        return docket ;
    }

    /*
     *
     * 配置API接口文档组件token安全设置。
     *
     * @param apiDocket API接口文档组件
     */
    private void setDocketSecurity(Docket apiDocket) {
        ApiKey authorizationKey = new ApiKey("Authorization", "Authorization", "header");
        ApiKey tentantCodeKey = new ApiKey("Tenant-Code", "Tenant-Code", "header");
        List<ApiKey> keys = new ArrayList<>();
        keys.add(authorizationKey);
        keys.add(tentantCodeKey);
        apiDocket.securitySchemes(Lists.newArrayList(keys));
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        List<SecurityReference> securityReferences = Lists.newArrayList(
            new SecurityReference("Authorization", new AuthorizationScope[] {authorizationScope}));
        SecurityContext securityContext =
            SecurityContext.builder().securityReferences(securityReferences).build();
        apiDocket.securityContexts(Lists.newArrayList(securityContext));
    }


    /**
     * Authorization 请求头
     *
     * @return Parameter
     */
    private Parameter authorizationParameter() {
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        tokenBuilder.name("Authorization")
                .description("access_token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        return tokenBuilder.build();
    }

    /**
     * Tenant-Code 请求头
     *
     * @return Parameter
     */
    private Parameter tenantCodeParameter() {
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        tokenBuilder.name("Tenant-Code")
                .defaultValue(SecurityConstant.DEFAULT_TENANT_CODE)
                .description("租户标识")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        return tokenBuilder.build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("园区公共服务")
               // .description("https://gitee.com/wells2333/spring-microservice-exam")
                //.termsOfServiceUrl("https://gitee.com/wells2333/spring-microservice-exam")
                //.contact(new Contact("tangyi", "https://gitee.com/wells2333/spring-microservice-exam", "1633736729@qq.com"))
                .version("1.0.0")
                .build();
    }

    /**
     * 显示swagger-ui.html 、doc.html 文档展示页，还必须注入swagger资源：
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry
            .addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry
            .addResourceHandler("doc.html")
            .addResourceLocations("classpath:/META-INF/resources/");
    }
}
