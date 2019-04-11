package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue;

import javax.servlet.http.HttpServletRequest;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_HEADER_KEY;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_PREFIX;

class JwtTokenRetrieval
{
    static String retrieveToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER_KEY);
        if (bearerToken != null && bearerToken.startsWith(AUTHORIZATION_PREFIX))
        {
            return bearerToken.substring(7);
        }

        return null;
    }
}
