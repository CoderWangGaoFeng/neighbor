package com.neighbor.confinguration;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration//配置文件专用注解
@EnableSwagger2
public class Swagger2Configure {

    @Bean
    public Docket createRestApi(){
        return (new Docket(DocumentationType.SWAGGER_2))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.neighbor.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //大标题
                .title("SWAGGER2搭建RESTFUL风格的社区论坛api")
                //详细描述
                .description("请关注XXXXXXXXXX")
//                .termsOfServiceUrl("www.baidu.com")
                //版本
                .version("1.0")
                //作者信息
//                .contact(new Contact("wgf","www.baidu.com","wgf_1991@163.com"))
                .build();
    }
}
