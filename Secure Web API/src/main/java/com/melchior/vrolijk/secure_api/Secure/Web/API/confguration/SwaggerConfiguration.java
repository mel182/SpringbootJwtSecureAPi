package com.melchior.vrolijk.secure_api.Secure.Web.API.confguration;

import com.google.common.base.Predicates;
import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * This is the swagger plugin {@link Docket} configuration class
 * @author Melchior Vrolijk
 */
@SuppressWarnings("ALL")
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    //region Configure the swagger docket bean
    /**
     * Configure the swagger docket bean
     * @return The plugin {@link Docket}
     */
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(regex("/error.*")))
                .build()
                .securityContexts(Arrays.asList(securityContextAll()));
    }
    //endregion

    //region Add resource handler
    /**
     * Add resource handler which enables the swagger ui html pages
     * @param registry The {@link ResourceHandlerRegistry} registry
     * @see WebMvcConfigurationSupport
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    //endregion

    //region get Api info
    /**
     * Get swagger api info section which is display at the top of the html page
     * @return The api info section
     */
    private ApiInfo getApiInfo()
    {
        Contact contact = new Contact("Melchior Vrolijk", "","vrol0004@gmail.com");

        return new ApiInfo(
                "Spring boot secure API documentation",
                "This is the demo spring boot secure api project documentation",
                "1.0","",contact,
                "","",Collections.emptyList());
    }
    //endregion

    //region Set the security configuration
    /**
     * Set the security configuration
     * @return The {@link SecurityConfiguration}
     */
    @Bean
    public SecurityConfiguration securityConfiguration()
    {
        return SecurityConfigurationBuilder.builder()
                .scopeSeparator("")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }
    //endregion

    //region Set authorization scopes
    /**
     * Set authorization scope
     * @return The list of {@link AuthorizationScope}
     */
    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations")};
        return scopes;
    }
    //endregion

    //region Security context
    /**
     * Set security context
     * @return The security context
     */
    private SecurityContext securityContextAll() {
        return SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(new SecurityReference("authorization", scopes())))
                .forPaths(PathSelectors.any())
                .forPaths(Predicates.not(regex("/auth/user")))
                .build();
    }
    //endregion
}