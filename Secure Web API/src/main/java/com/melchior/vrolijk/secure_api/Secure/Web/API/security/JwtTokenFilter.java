package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This class represent the JWT token extractor
 * @author Melchior Vrolijk
 */
public class JwtTokenFilter extends GenericFilterBean
{
    //region Local instances
    private JwtTokenGenerator jwtTokenGenerator;
    private JwtTokenAuthenticationExtractor jwtTokenAuthenticationRetrieval;
    //endregion

    //region Constructor
    /**
     * Constructor
     * @param jwtTokenGenerator The JWT token generator
     * @param jwtTokenAuthenticationExtractor The JWT token extractor instance
     */
    public JwtTokenFilter(JwtTokenGenerator jwtTokenGenerator, JwtTokenAuthenticationExtractor jwtTokenAuthenticationExtractor)
    {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.jwtTokenAuthenticationRetrieval = jwtTokenAuthenticationExtractor;
    }
    //endregion

    //region Generic filter Bean doFilter function
    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String token = JwtHttpRequestTokenRetrieval.retrieveToken((HttpServletRequest) servletRequest);

        if (token != null && JwtTokenValidator.isTokenvalid(token))
        {
            Authentication authentication = token != null ? jwtTokenAuthenticationRetrieval.getAuthentication(token) : null;

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
    //endregion

}
