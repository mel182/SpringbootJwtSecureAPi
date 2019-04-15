package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AuthenticationRequest
{
    @ApiModelProperty(required = true)
    private String email;

    @ApiModelProperty(required = true)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
