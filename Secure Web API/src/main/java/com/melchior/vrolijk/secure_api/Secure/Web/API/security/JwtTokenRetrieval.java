package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import javax.servlet.http.HttpServletRequest;

class JwtTokenRetrieval {

    static String retrieveToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer"))
        {
            return bearerToken.substring(7);
        }

        return null;
    }
}
