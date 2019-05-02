package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


/**
 * This is the API Security configuration class
 *
 * @author Melchior Vrolijk
 */
@SuppressWarnings("ALL")
@Configuration
public class SecureAPiControllerConfiguration extends WebSecurityConfigurerAdapter
{
    //region Autowired instances
    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    JwtTokenAuthenticationExtractor jwtTokenAutenticationRetrieval;

    @Value("${server.address}")
    private String server_ip_address;
    //endregion

    //region Get the authentication manager bean
    /**
     * The authentication manager bean
     * @return The {@link AuthenticationManager} instance
     * @throws Exception Throws in case something went wrong in the process
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //endregion

    //region WebSecurityConfigurerAdapter configure(HttpSecurity)
    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("Server address: "+server_ip_address);
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/**").hasIpAddress(server_ip_address)
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/user/create").permitAll()
                .antMatchers("/api/v2/*").permitAll()
                //.antMatchers(HttpMethod.GET,"/post/all").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenGenerator,jwtTokenAutenticationRetrieval));
    }
    //endregion

    //region WebSecurityConfigurerAdapter configure(WebSecurity)
    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html",
                "/webjars/**");

    }
    //endregion
}
