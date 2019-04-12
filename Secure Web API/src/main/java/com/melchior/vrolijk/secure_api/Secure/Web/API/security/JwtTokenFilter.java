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

public class JwtTokenFilter extends GenericFilterBean
{
    private JwtTokenGenerator jwtTokenGenerator;
    private JwtTokenAutenticationRetrieval jwtTokenAutenticationRetrieval;

    public JwtTokenFilter(JwtTokenGenerator jwtTokenGenerator, JwtTokenAutenticationRetrieval jwtTokenAutenticationRetrieval)
    {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.jwtTokenAutenticationRetrieval = jwtTokenAutenticationRetrieval;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String token = JwtTokenRetrieval.retrieveToken((HttpServletRequest) servletRequest);

        if (token != null && JwtTokenValidator.isTokenvalid(token))
        {
            Authentication authentication = token != null ? jwtTokenAutenticationRetrieval.getAuthentication(token) : null;

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }


}
