package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This is the authenticated request model which is used when authenticated user
 * @author Melchior Vrolijk
 */
@ApiModel
public class AuthenticationRequest
{
    //region Local instances
    @ApiModelProperty(required = true)
    private String email;

    @ApiModelProperty(required = true)
    private String password;
    //endregion

    //region User e-mail getter and setter
    /**
     * Get user e-mail address
     * @return The user e-mail address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user e-mail address
     * @param email The user e-mail address
     */
    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    //region User password getter and setter
    /**
     * Get user password
     * @return The user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user password
     * @param password The user password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}
