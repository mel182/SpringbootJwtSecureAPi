package com.melchior.vrolijk.secure_api.Secure.Web.API.confguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

        /*
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("guru.springframework"))
                .paths(regex("/api/v1/*"))
                .build().apiInfo(getApiInfo());
        */
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo getApiInfo()
    {
//        return new ApiInfo(
//                "Spring boot secure API documentation",
//                "This is the demo spring boot secure api project documentation",
//                "1","",new Contact("Melchior Vrolijk", "1","vrol0004@gmail.com"),
//                "","www.google.com",Collections.emptyList());

        Contact contact = new Contact("Melchior Vrolijk", "","vrol0004@gmail.com");

        return new ApiInfoBuilder()
                .title("Spring boot secure API documentation")
                .description("This is the demo spring boot secure api project documentation")
                .version("1")
                .license("")
                .contact(contact)
                .licenseUrl("")
                .build();

    }
}