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

/**
 * This is the base controller security base class
 *
 * @author Melchior Vrolijk
 */
@SuppressWarnings("ALL")
public class BaseSecurityControllerVerifier
{
    //region Determine if user has a admin role
    /**
     * Determine if the user has the admin role based on the raw JWT token provided
     * @param token The raw JWT token
     * @return 'True' if user is admin and 'False' if does not have an admin role
     */
    protected boolean isAdmin(String token)
    {
        return getUserRole(token).toString().equals(ADMIN.toString());
    }
    //endregion

    //region Determine if an user is authorized
    /**
     * Determine if the user is authorized based on the JWT token provided
     * @param token The raw JWT token
     * @return 'True' if user is authorized and 'false' if user is unauthorized
     */
    protected boolean isAuthorized(String token)
    {
        String userRole = getUserRole(token).toString();
        return userRole.equals(USER.toString()) || userRole.equals(ADMIN.toString()) || userRole.equals(ROOT.toString());
    }
    //endregion

    //region Determine if a user is the root user
    /**
     * Determine if the user is a root user
     * @param token The raw JWT token
     * @return 'True' if user is root-user and 'False' if user is not the root-user
     */
    protected boolean isRootUser(String token)
    {
        return getUserRole(token).toString().equals(ROOT.toString());
    }
    //endregion

    //region Determine if the user is account owner
    /**
     * Determine if JWT corresponds to the account owner
     * @param token The raw JWT token
     * @param requestedUserID The requested user ID
     * @return 'True' if user is the account owner and 'false' if it is not account owner
     */
    protected boolean isOwner(String token, long requestedUserID)
    {
        long ID = Long.parseLong(extractUserID(token));
        return ID == requestedUserID;
    }
    //endregion

    //region Get user role of the raw JWT
    /**
     * Get user role based based on the JWT token provided
     * @param token The raw JWT token
     * @return The user role extracted from the JWT token
     */
    private UserRole getUserRole(String token)
    {
        String extractedRole = extractUserRoles(token);
        return getRoles().stream().filter(availableRole -> availableRole.toString().equals(extractedRole))
                .findFirst().orElse(UserRole.UNAUTHORIZED);
    }
    //endregion

    //region Get user role of the raw JWT
    /**
     * Get all valid available user roles
     * @return The list of valid available roles
     */
    private List<UserRole> getRoles()
    {
        return new ArrayList<>(EnumSet.allOf(UserRole.class));
    }
    //endregion

    //region Extract user roles of the JWT
    /**
     * Extract user
     * @param token The raw JWT token
     * @return The extracted user role of the JWT token
     */
    protected String extractUserRoles(String token)
    {
        return JwtTokenDataRetrieval.getRole(token);
    }
    //endregion

    //region Extract user ID of the raw JWT
    /**
     * Extract user ID of the raw JWT user ID
     * @param token The raw JWT token
     * @return The extracted user ID of the JWT token
     */
    protected String extractUserID(String token)
    {
        return JwtTokenDataRetrieval.extractUserID(token);
    }
    //endregion

    //region Get default unauthorized response entity
    /**
     * Get the default unauthorized {@link ResponseEntity}
     * @return The default unauthorized response
     */
    protected ResponseEntity getUnAuthorizedResponse()
    {
        return new ResponseEntity<>("Not authorized to perform such action", HttpStatus.UNAUTHORIZED);
    }
    //endregion

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

    //region Determine if request data contain invalid data
    /**
     * Determine if request data contains invalid data
     * @param newUserData The new user request data
     * @return 'True' if it contain valid data and 'False' if not
     */
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
    //endregion
}
