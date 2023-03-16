package com.wa.last.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;


/**
 * @author wangwenchang
 * @date 2018/5/3 15:13
 */
@Configuration
@EnableSwagger2
//@Profile("!pro")
//@ComponentScan({"com.example"})
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    public SwaggerConfig() {
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //接口路径
                .apis(RequestHandlerSelectors.basePackage("com.chang.viv"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
//                .title("springboot利用swagger构建api文档")
                .title(applicationName)
                .description("")
//                .termsOfServiceUrl("http://blog.csdn.net/saytime")
                .version("1.0")
                .build();
    }
}
