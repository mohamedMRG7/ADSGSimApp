package com.sim.adsg.adsg.configurations;

import com.sim.adsg.adsg.constants.ApplicationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage(ApplicationConstants.BASE_PACKAGE))
                .paths(PathSelectors.ant(ApplicationConstants.ALLOWED_URL))
                //.paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Noc Mobile Integration - NOC",
                "A REST Mobile service for Noc",
                "1.0.0",
                "Terms of service",
                new Contact("egabi solution","www.egabi.com","support@egabi.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
