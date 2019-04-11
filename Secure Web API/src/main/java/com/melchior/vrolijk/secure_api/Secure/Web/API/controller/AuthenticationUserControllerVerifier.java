package com.melchior.vrolijk.secure_api.Secure.Web.API.controller;

import com.melchior.vrolijk.secure_api.Secure.Web.API.controller.baseClasses.BaseSecurityControllerVerifier;
import com.melchior.vrolijk.secure_api.Secure.Web.API.customException.UserAlreadyExistException;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.PostEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticatedUser;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.valueVerifier.RequestValueVerifier;
import com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService.AuthenticatedUserService;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.EmailValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_HEADER_KEY;

@Api(tags = "Authentication", description = "Endpoints for creating and authenticating new or existent user/admin")
@RestController
@RequestMapping("/auth")
public class AuthenticationUserControllerVerifier extends BaseSecurityControllerVerifier
{
    //region Local instances
    @Autowired
    AuthenticatedUserService authenticatedUserService;
    //endregion

    //region Login API
    @ApiOperation(value = "Authenticate as a user",
            response = AuthenticatedUser.class, responseContainer = "The authenticated user details",
            notes = "User must be registered")

    @ApiResponses(value = {
            @ApiResponse(code=200, message = "The corresponding authenticated user details",
                    response = PostEntity.class, responseHeaders = {
            }),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    /**
     * This is the login API function '/auth/login'
     * @param authenticationRequest The {@link AuthenticationRequest} contains the new user data
     * @return The {@link ResponseEntity}
     */
    @PostMapping("/login")
    public ResponseEntity login(@ApiParam(value = "Login credentials", required = true) @RequestBody AuthenticationRequest credentials)
    {
        AuthenticatedUser authenticatedUser = this.authenticatedUserService.login(credentials);

        if (authenticatedUser != null)
        {
            return ResponseEntity.ok(authenticatedUser);
        }

        return new ResponseEntity<>("Invalid credentials",HttpStatus.UNAUTHORIZED);
    }
    //endregion

    //region Create a new user API
    @ApiOperation(value = "Create a new user",
            response = AuthenticatedUser.class, responseContainer = "The new authenticated user details")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "The new authenticated user details",
                    response = PostEntity.class, responseHeaders = {
            }),
            @ApiResponse(code = 400, message = "Bad request or either body is missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    /**
     * Create a new user API
     * @param newUserData The new user details
     * @return The {@link ResponseEntity}
     */
    @PostMapping("/user")
    public ResponseEntity createNewUser(@ApiParam(value = "New user data", required = true) @RequestBody NewUserAuthenticationRequest UserData)
    {
        if (containValidEmailPassword(UserData))
        {
            if (createAuthenticatedUser(UserData,UserRole.USER) != null)
            {
                try {
                    AuthenticatedUser newRegisteredUser = authenticatedUserService.save(UserData);
                    return new ResponseEntity<>(newRegisteredUser,HttpStatus.CREATED);
                } catch (UserAlreadyExistException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>("User already exists",HttpStatus.NOT_IMPLEMENTED);
                }
            }
        }else{
            return new ResponseEntity<>("Missing body values",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Not authorized to perform such action",HttpStatus.UNAUTHORIZED);
    }
    //endregion

    //region Create new admin API
    @ApiOperation(value = "Create a new admin ",
            response = AuthenticatedUser.class, responseContainer = "The new authenticated admin details",
            notes = "This API can be ONLY perform by the root user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New authenticated user is successfully created"),
            @ApiResponse(code = 400, message = "Bad request or either body is missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    @PostMapping("/admin")
    public ResponseEntity createAdminUser(@ApiParam(value = "Root user authentication token", required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization, @ApiParam(value = "New Admin data", required = true) @RequestBody NewUserAuthenticationRequest newUserData)
    {
        if (isRootUser(authorization))
        {
            if (containValidEmailPassword(newUserData))
            {
                if (createAuthenticatedUser(newUserData,UserRole.ADMIN) != null)
                {
                    try {
                        AuthenticatedUser newRegisteredUser = authenticatedUserService.save(newUserData);
                        return new ResponseEntity<>(newRegisteredUser,HttpStatus.CREATED);
                    } catch (UserAlreadyExistException e) {
                        e.printStackTrace();
                        return new ResponseEntity<>("User already exists",HttpStatus.NOT_IMPLEMENTED);
                    }
                }
            }else{
                return new ResponseEntity<>("Missing body values",HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("Not authorized to perform such action",HttpStatus.UNAUTHORIZED);
    }
    //endregion

    //region Create authenticated user
    /**
     * Create {@link AuthenticatedUser} instance
     * @param newUserData The {@link NewUserAuthenticationRequest}
     * @param userRole The {@link UserRole}
     * @return {@link NewUserAuthenticationRequest} containing all necessary data
     */
    private NewUserAuthenticationRequest createAuthenticatedUser(NewUserAuthenticationRequest newUserData, UserRole userRole)
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

    //region Check if new user data contain valid username and password
    /**
     * Check new user data contain a valid email (username) and password
     * @param newUserData The new user data
     * @return 'True' if all data are valid and 'false' if not
     */
    private boolean containValidEmailPassword(NewUserAuthenticationRequest newUserData)
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

    //TODO: Implement delete admin API

    //TODO: Implement delete user API

    //TODO: Implement user profile API

    //TODO: Implement update user profile API
}
