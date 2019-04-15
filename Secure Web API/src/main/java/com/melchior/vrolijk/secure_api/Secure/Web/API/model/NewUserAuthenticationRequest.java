package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class NewUserAuthenticationRequest
{
    @ApiModelProperty(readOnly = true)
    private Long id;

    private String firstName = "";

    private String lastName = "";

    private String occupation = "";

    @ApiModelProperty(required = true)
    private String email = "";

    @ApiModelProperty(required = true)
    private String password = "";

    @ApiModelProperty(readOnly = true)
    private String role = "";


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
