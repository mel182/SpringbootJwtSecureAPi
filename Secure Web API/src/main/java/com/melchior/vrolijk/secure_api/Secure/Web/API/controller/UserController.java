package com.melchior.vrolijk.secure_api.Secure.Web.API.controller;

import com.melchior.vrolijk.secure_api.Secure.Web.API.controller.baseClasses.BaseSecurityControllerVerifier;
import com.melchior.vrolijk.secure_api.Secure.Web.API.customException.UserAlreadyExistException;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.PostEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticatedUser;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.ResponseUser;
import com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService.AuthenticatedUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.ADMIN_AUTHORIZATION_REQUIRED;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_HEADER_KEY;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_REQUIRED;

@SuppressWarnings("ALL")
@Api(tags = "Users", description = "Endpoints for manipulating registered user data")
@RestController
@RequestMapping("/user")
public class UserController extends BaseSecurityControllerVerifier
{
    //region Local instances
    @Autowired
    AuthenticatedUserService authenticatedUserService;
    //endregion

    //region Get list of all users GET API
    @ApiOperation(value = "Retrieve list of users ",
            response = ResponseUser[].class, responseContainer = "List of registered users",
            notes = "This API can be ONLY perform by the admin/root user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of registered users"),
            @ApiResponse(code = 400, message = "Bad request or either body is missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    @GetMapping("/all")
    public ResponseEntity getAllUsers(@ApiParam(value = ADMIN_AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization)
    {
        if (isRootUser(authorization) || isAdmin(authorization))
        {
            return ResponseEntity.ok(authenticatedUserService.getAllUsers());
        }

        return getUnAuthorizedResponse();
    }
    //endregion

    //region Create a new user API
    @ApiOperation(value = "Create a new user",
            response = AuthenticatedUser.class, responseContainer = "The new authenticated user details")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "The new authenticated user details",
                    response = ResponseUser.class, responseHeaders = {
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
    @PostMapping("/create")
    public ResponseEntity createNewUser(@ApiParam(value = "New user data", required = true) @RequestBody NewUserAuthenticationRequest UserData)
    {
        if (containValidEmailPassword(UserData))
        {
            if (createAuthenticatedUser(UserData, UserRole.USER) != null)
            {
                try {
                    AuthenticatedUser newRegisteredUser = authenticatedUserService.save(UserData);
                    return new ResponseEntity<>(newRegisteredUser, HttpStatus.CREATED);
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

    //region Update a user PUT API
    @ApiOperation(value = "Update a user",
            response = AuthenticatedUser.class, responseContainer = "Updated user details")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Updated user details",
                    response = ResponseUser.class, responseHeaders = {
            }),
            @ApiResponse(code = 400, message = "Bad request or either body is missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    /**
     * Update user PUT API
     * @param newUserData Updated user data
     * @return The {@link ResponseEntity}
     */
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@ApiParam(value = AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization, @ApiParam(value = "Updated user data", required = true) @RequestBody NewUserAuthenticationRequest UserData, @ApiParam(value = "The user ID", required = true) @PathVariable long id )
    {
        if (isOwner(authorization,id))
        {
            if (!containInValidUser(UserData))
            {
                UserData.setId(id);
                ResponseUser updatedUser = authenticatedUserService.updateUser(UserData);
                if (updatedUser != null)
                {
                    return ResponseEntity.ok(updatedUser);
                }
            }else{
                return new ResponseEntity<>("Bad request",HttpStatus.BAD_REQUEST);
            }
        }

        return getUnAuthorizedResponse();
    }
    //endregion

    //region Remove user DELETE API
    @ApiOperation(value = "Remove user",
            response = ResponseUser.class, responseContainer = "User that has been removed details",
            notes = "This API can be ONLY perform by the admin/root user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully removed"),
            @ApiResponse(code = 400, message = "Bad request or either body is missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    @DeleteMapping("/{id}")
    public ResponseEntity removeUser(@ApiParam(value = ADMIN_AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization, @ApiParam(value = "The corresponding user ID", required = true) @PathVariable long id)
    {
        if (isRootUser(authorization) || isAdmin(authorization))
        {
            ResponseUser user = authenticatedUserService.removeUser(id);

            if (user != null)
            {
                return ResponseEntity.ok(user);
            }else{
                return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
            }
        }

        return getUnAuthorizedResponse();
    }
    //endregion
}
