package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import com.auth0.jwt.JWT;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_PREFIX;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.USER_ROLE;

public class JwtTokenDataRetrieval
{
    static String getUserName(String token)
    {
        return JWT.decode(refineToken(token)).getSubject();
    }

    public static String getRole(String token)
    {
        return JWT.decode(refineToken(token)).getClaim(USER_ROLE).asString();
    }

    private static String refineToken(String raw_token)
    {
        String token_prefix_removed = raw_token.replace(AUTHORIZATION_PREFIX,"");
        return token_prefix_removed.trim();
    }


}
