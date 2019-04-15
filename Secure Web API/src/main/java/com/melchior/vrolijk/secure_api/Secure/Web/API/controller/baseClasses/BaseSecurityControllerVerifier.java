package com.melchior.vrolijk.secure_api.Secure.Web.API.controller.baseClasses;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticatedUser;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenDataRetrieval;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.valueVerifier.RequestValueVerifier;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    protected boolean isOwner(String token, long requestedUserID)
    {
        long ID = Long.parseLong(extractUserID(token));
        return ID == requestedUserID;
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

    private String extractUserID(String token)
    {
        return JwtTokenDataRetrieval.extractUserID(token);
    }

    protected ResponseEntity getUnAuthorizedResponse()
    {
        return new ResponseEntity<>("Not authorized to perform such action", HttpStatus.UNAUTHORIZED);
    }

    //region Check if new user data contain valid username and password
    /**
     * Check new user data contain a valid email (username) and password
     * @param newUserData The new user data
     * @return 'True' if all data are valid and 'false' if not
     */
    protected boolean containValidEmailPassword(NewUserAuthenticationRequest newUserData)
    {
        if (newUserData.getEmail() == null || newUserData.getPassword() == null)
            return false;

        if (newUserData.getEmail().equals("") || newUserData.getPassword().equals(""))
            return false;

        if (!EmailValidator.getInstance().isValidEmail(newUserData.getEmail()))
            return false;

        return true;
    }
    //endregion

    //region Create authenticated user
    /**
     * Create {@link AuthenticatedUser} instance
     * @param newUserData The {@link NewUserAuthenticationRequest}
     * @param userRole The {@link UserRole}
     * @return {@link NewUserAuthenticationRequest} containing all necessary data
     */
    protected NewUserAuthenticationRequest createAuthenticatedUser(NewUserAuthenticationRequest newUserData, UserRole userRole)
    {
        List<String> dataList = new ArrayList<>();
        dataList.add(newUserData.getFirstName());
        dataList.add(newUserData.getLastName());
        dataList.add(newUserData.getOccupation());
        dataList.add(newUserData.getEmail());
        dataList.add(newUserData.getPassword());

        boolean containInvalidData = dataList
                .stream()
                .anyMatch(data -> RequestValueVerifier.containInvalidValues(data,true));

        if (!containInvalidData)
        {
            newUserData.setRole(userRole.toString());
            return newUserData;
        }

        return null;
    }
    //endregion

    protected boolean containInValidUser(NewUserAuthenticationRequest newUserData)
    {
        List<String> dataList = new ArrayList<>();
        dataList.add(newUserData.getFirstName());
        dataList.add(newUserData.getLastName());
        dataList.add(newUserData.getOccupation());
        dataList.add(newUserData.getEmail());
        dataList.add(newUserData.getPassword());

        return dataList
                .stream()
                .anyMatch(data -> RequestValueVerifier.containInvalidValues(data,true));
    }
}
