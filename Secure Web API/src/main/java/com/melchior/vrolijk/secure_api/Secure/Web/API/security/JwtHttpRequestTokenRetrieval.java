package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import javax.servlet.http.HttpServletRequest;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_HEADER_KEY;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_PREFIX;

/**
 * This class represent the JWT Http request token generator
 * @author Melchior Vrolijk
 */
class JwtHttpRequestTokenRetrieval
{
    private static final int AUTHORIZATION_TOKEN_BEGIN_INDEX = 7;

    //region Retrieve token from http request
    /**
     * Retrieve token from Http request by reading the value the 'Authorization' of the request header
     * @param request The http request
     * @return The raw JWt token of the Http request header
     */
    static String retrieveToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER_KEY);
        if (bearerToken != null && bearerToken.startsWith(AUTHORIZATION_PREFIX))
        {
            try {
                return bearerToken.substring(AUTHORIZATION_TOKEN_BEGIN_INDEX);
            }catch (Exception ignored){}
        }

        return null;
    }
    //endregion
}
