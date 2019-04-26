package com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant;

/**
 * This is the security constant values
 * @author Melchior Vrolijk
 */
public class SecurityConstantValue
{
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_PREFIX = "Bearer";
    public static final String USER_ROLE = "user_role";
    public static final String USER_ID = "user_id";

    private static final String TOKEN_EXAMPLE = "'Example: Bearer + token'";
    public static final String ROOT_AUTHORIZATION_REQUIRED = "Root user authentication token "+TOKEN_EXAMPLE;
    public static final String ADMIN_AUTHORIZATION_REQUIRED = "Admin user authentication token "+TOKEN_EXAMPLE;
    public static final String AUTHORIZATION_REQUIRED = "Authentication token "+TOKEN_EXAMPLE;

    public static final String ONLY_USED_BY_ROOT_USER = "This API can ONLY be used by the root user";
    public static final String ONLY_USED_BY_ADMIN = "This API can ONLY be used by admin";

}
