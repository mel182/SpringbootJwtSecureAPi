package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This jwt configuration handles all task related to configuring JWT
 * @author Melchior Vrolijk
 */
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>
{
    //region Local instances
    private JwtTokenGenerator jwtTokenGenerator;
    private JwtTokenAuthenticationExtractor jwtTokenAutenticationRetrieval;
    //endregion

    //region Constructor
    /**
     * Constructor
     * @param jwtTokenGenerator The JWT token generator instance
     * @param jwtTokenAutenticationRetrieval The JWT token retrieval instance
     */
    public JwtConfigurer(JwtTokenGenerator jwtTokenGenerator, JwtTokenAuthenticationExtractor jwtTokenAutenticationRetrieval) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.jwtTokenAutenticationRetrieval = jwtTokenAutenticationRetrieval;
    }
    //endregion

    //region Security configurer adapter override method
    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(HttpSecurity httpSecurityBuilder) throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenGenerator,jwtTokenAutenticationRetrieval);
        httpSecurityBuilder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
    //endregion
}
