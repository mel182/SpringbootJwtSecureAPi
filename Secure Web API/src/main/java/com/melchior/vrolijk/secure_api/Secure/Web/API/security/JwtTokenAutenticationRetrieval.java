package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
class JwtTokenAutenticationRetrieval {

    @Autowired
    private AuthenticatedUserService authorizedUserDetailsService;

    Authentication getAuthentication(String token)
    {
        UserDetails userDetails = this.authorizedUserDetailsService.loadUserByUsername(JwtTokenDataRetrieval.getUserName(token));

        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
}