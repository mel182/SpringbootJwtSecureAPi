package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.JwtException;

import java.util.Date;

public class JwtTokenValidator {

    static boolean isTokenvalid(String token)
    {
        try{

            if (!getExpirationDay(token).before(new Date()))
                return true;

        }catch (JwtException | IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private static Date getExpirationDay(String token)
    {
        return JWT.decode(token).getExpiresAt();
    }

}
