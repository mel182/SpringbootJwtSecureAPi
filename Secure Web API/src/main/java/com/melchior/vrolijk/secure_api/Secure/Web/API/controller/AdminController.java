package com.melchior.vrolijk.secure_api.Secure.Web.API.controller;

import com.melchior.vrolijk.secure_api.Secure.Web.API.controller.baseClasses.BaseSecurityControllerVerifier;
import com.melchior.vrolijk.secure_api.Secure.Web.API.customException.UserAlreadyExistException;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticatedUser;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService.AuthenticatedUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.AUTHORIZATION_HEADER_KEY;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.ONLY_USED_BY_ROOT_USER;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.ROOT_AUTHORIZATION_REQUIRED;

@Api(tags = "Admin", description = "Endpoints for manipulating registered admins data")
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseSecurityControllerVerifier
{
    //region Local instances
    @Autowired
    AuthenticatedUserService authenticatedUserService;
    //endregion

    //region Get list of all admins GET API
    @ApiOperation(value = "Retrieve list of admins ",
            response = AuthenticatedUser[].class, responseContainer = "List of registered admins",
            notes = "This API can be ONLY perform by the root user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of registered admins"),
            @ApiResponse(code = 400, message = "Bad request or either body is missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    @GetMapping("/all")
    public ResponseEntity getAllAdmin(@ApiParam(value = ROOT_AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization)
    {
        if (isRootUser(authorization))
        {
            return ResponseEntity.ok(authenticatedUserService.getAllAdmins());
        }

        return getUnAuthorizedResponse();
    }
    //endregion

    //region Create new admin POST API
    @ApiOperation(value = "Create a new admin ",
            response = AuthenticatedUser.class, responseContainer = "The new authenticated admin details",
            notes = ONLY_USED_BY_ROOT_USER)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New admin successfully created"),
            @ApiResponse(code = 400, message = "Bad request or either body is missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    @PostMapping("/create")
    public ResponseEntity createAdminUser(@ApiParam(value = ROOT_AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization, @ApiParam(value = "New Admin data", required = true) @RequestBody NewUserAuthenticationRequest newUserData)
    {
        if (isRootUser(authorization))
        {
            if (containValidEmailPassword(newUserData))
            {
                if (createAuthenticatedUser(newUserData, UserRole.ADMIN) != null)
                {
                    try {
                        AuthenticatedUser newRegisteredUser = authenticatedUserService.save(newUserData);
                        return new ResponseEntity<>(newRegisteredUser, HttpStatus.CREATED);
                    } catch (UserAlreadyExistException e) {
                        e.printStackTrace();
                        return new ResponseEntity<>("User already exists",HttpStatus.NOT_IMPLEMENTED);
                    }
                }
            }else{
                return new ResponseEntity<>("Missing body values",HttpStatus.BAD_REQUEST);
            }
        }

        return getUnAuthorizedResponse();
    }
    //endregion

    //region Remove admin DELETE API
    @ApiOperation(value = "Remove admin ",
            response = AuthenticatedUser.class, responseContainer = "Admin that has been removed details",
            notes = ONLY_USED_BY_ROOT_USER)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Admin successfully removed"),
            @ApiResponse(code = 400, message = "Bad request or either body is missing minimum required data"),
            @ApiResponse(code = 401, message = "Unauthorized since you are not authorized user"),
            @ApiResponse(code = 403, message = "Forbidden since you are not authorized to perform such action"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Validated
    @DeleteMapping("/{id}")
    public ResponseEntity removeAdmin(@ApiParam(value = ROOT_AUTHORIZATION_REQUIRED, required = true) @RequestHeader(AUTHORIZATION_HEADER_KEY) String authorization, @ApiParam(value = "The admin ID", required = true) @PathVariable long id)
    {
        if (isRootUser(authorization))
        {
            AuthenticatedUser user = authenticatedUserService.removeAdmin(id);

            if (user != null)
            {
                return ResponseEntity.ok(user);
            }else{
                return new ResponseEntity<>("Unable to remove admin",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return getUnAuthorizedResponse();
    }
    //endregion
}
