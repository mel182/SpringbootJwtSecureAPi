package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import com.auth0.jwt.JWT;
import io.jsonwebtoken.JwtException;

import java.util.Date;

/**
 * This is the JWT token validator
 * @author Melchior Vrolijk
 */
public class JwtTokenValidator {

    //region Determine if raw JWT is valid
    /**
     * Determine if the JWT token is valid
     * @param token The raw JWT token
     * @return 'True' if token is valid and 'false' if it is invalid
     */
    static boolean isTokenvalid(String token)
    {
        try{

            if (!decodeExpirationDay(token).before(new Date()))
                return true;

        }catch (JwtException | IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    //endregion

    //region Decode expiration date from raw JWT token
    /**
     * Decode JWT token expiration date
     * @param token The raw JWT token
     * @return {@link Date} containing the expiration date
     */
    private static Date decodeExpirationDay(String token)
    {
        return JWT.decode(token).getExpiresAt();
    }
    //endregion
}
