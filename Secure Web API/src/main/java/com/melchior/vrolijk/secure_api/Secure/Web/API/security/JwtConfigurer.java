package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>
{
    private JwtTokenGenerator jwtTokenGenerator;
    private JwtTokenAutenticationRetrieval jwtTokenAutenticationRetrieval;

    public JwtConfigurer(JwtTokenGenerator jwtTokenGenerator, JwtTokenAutenticationRetrieval jwtTokenAutenticationRetrieval) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.jwtTokenAutenticationRetrieval = jwtTokenAutenticationRetrieval;
    }

    @Override
    public void configure(HttpSecurity httpSecurityBuilder) throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenGenerator,jwtTokenAutenticationRetrieval);

        httpSecurityBuilder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
