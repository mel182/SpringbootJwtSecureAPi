package com.melchior.vrolijk.secure_api.Secure.Web.API.controller;

import com.melchior.vrolijk.secure_api.Secure.Web.API.controller.baseClasses.BaseSecurityControllerVerifier;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.PostEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticatedUser;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService.AuthenticatedUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the authentication controller that extends {@link BaseSecurityControllerVerifier}
 *
 * @author Melchior Vrolijk
 */
@Api(tags = "Authentication", description = "Endpoints for authenticating")
@RestController
@RequestMapping("/auth")
public class AuthenticationController extends BaseSecurityControllerVerifier
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
                    response = AuthenticatedUser.class, responseHeaders = {
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
}
