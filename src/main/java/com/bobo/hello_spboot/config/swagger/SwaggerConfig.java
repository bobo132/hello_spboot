package com.bobo.hello_spboot.config.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket restApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bobo.hello_spboot.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("SpringBoot接口")
                        .description("详细信息")
                        .version("1.0")
                        .contact(new Contact("九月清晨", "https://www.cnblogs.com/bobo132/", "bobo13262@outlook.com"))
                        .license("The Apache License")
                        .licenseUrl("http://httpd.apache.org/docs/current/en/license.html")
                        .build()

                );
        return docket;
    }


}
