package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import com.auth0.jwt.JWT;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.*;

/**
 * The JWT token data retrieval which handles all task related to retrieving the JWT token.
 *
 * @author Melchior Vrolijk
 */
public class JwtTokenDataRetrieval
{
    //region Extract user name (email) of the JWT token
    /**
     * Extract user name (email address) of the JWT token
     * @param token The raw JWT token
     * @return The user email address
     */
    static String getUserName(String token)
    {
        return JWT.decode(refineToken(token)).getSubject();
    }
    //endregion

    //region Extract user role of the JWT token
    /**
     * Extract user role of the JWT token
     * @param token The raw JWT token
     * @return The user role
     */
    public static String getRole(String token)
    {
        return JWT.decode(refineToken(token)).getClaim(USER_ROLE).asString();
    }
    //endregion

    //region Extract user ID of the JWT token
    /**
     * Extract user ID of the raw JWT token
     * @param token The raw JWT token
     * @return The user ID
     */
    public static String extractUserID(String token)
    {
        return JWT.decode(refineToken(token)).getClaim(USER_ID).asString();
    }
    //endregion

    //region Refine JWT token
    /**
     * Refine JWT token by removing the authorization prefix
     * @param raw_token The raw JWT token
     * @return Refined JWT token
     */
    private static String refineToken(String raw_token)
    {
        String token_prefix_removed = raw_token.replace(AUTHORIZATION_PREFIX,"");
        return token_prefix_removed.trim();
    }
    //endregion
}
