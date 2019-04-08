package com.melchior.vrolijk.secure_api.Secure.Web.API.confguration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
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
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

        /*

         .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .paths(Predicates.not(PathSelectors.regex("/error.*")))


        .apis(RequestHandlerSelectors.basePackage("com.melchior.vrolijk.secure_api.Secure.Web.API"))
                .paths(regex("/api/v2/*"))
                .build();


        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(PathSelectors.any())
            .build();
        */
        //.apis( RequestHandlerSelectors.basePackage( "your package" ) )
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
        Contact contact = new Contact("Melchior Vrolijk", "","vrol0004@gmail.com");

        return new ApiInfo(
                "Spring boot secure API documentation",
                "This is the demo spring boot secure api project documentation",
                "1.0","",contact,
                "","www.google.com",Collections.emptyList());

    }
}