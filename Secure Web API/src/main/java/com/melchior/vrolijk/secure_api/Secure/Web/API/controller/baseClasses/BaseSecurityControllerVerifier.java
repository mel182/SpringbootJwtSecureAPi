package com.melchior.vrolijk.secure_api.Secure.Web.API.controller.baseClasses;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenDataRetrieval;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole.ADMIN;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole.ROOT;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole.USER;

public class BaseSecurityControllerVerifier
{

    protected boolean isAdmin(String token)
    {
        return getUserRole(token).toString().equals(ADMIN.toString());
    }

    protected boolean isAuthorizedUser(String token)
    {
        return getUserRole(token).toString().equals(USER.toString());
    }

    protected boolean isRootUser(String token)
    {
        return getUserRole(token).toString().equals(ROOT.toString());
    }


    private UserRole getUserRole(String token)
    {
        String extractedRole = extractUserRoles(token);
        return getRoles().stream().filter(availableRole -> availableRole.toString().equals(extractedRole))
                .findFirst().orElse(UserRole.UNAUTHORIZED);
    }

    private List<UserRole> getRoles()
    {
        return new ArrayList<>(EnumSet.allOf(UserRole.class));
    }

    private String extractUserRoles(String token)
    {
        return JwtTokenDataRetrieval.getRole(token);
    }
}
