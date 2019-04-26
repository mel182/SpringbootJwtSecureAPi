package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * This class represent the JWT token extractor
 * @author Melchior Vrolijk
 */
@Component
class JwtTokenAuthenticationExtractor {

    //region Autowired instances
    @Autowired
    private AuthenticatedUserService authorizedUserDetailsService;
    //endregion

    //region Get user authentication
    /**
     * Get authenticated based on the token provided
     * @param token The raw JWT token
     * @return {@link Authentication} instance
     */
    Authentication getAuthentication(String token)
    {
        UserDetails userDetails = this.authorizedUserDetailsService.loadUserByUsername(JwtTokenDataRetrieval.getUserName(token));

        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
    //endregion
}
