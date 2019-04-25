package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResponseUser
{
    @ApiModelProperty(readOnly = true)
    private Long id;

    @ApiModelProperty(readOnly = true)
    private String firstName;

    @ApiModelProperty(readOnly = true)
    private String lastName;

    @ApiModelProperty(readOnly = true)
    private String occupation;

    @ApiModelProperty(readOnly = true)
    private String email;

    public ResponseUser(Long id, String firstName, String lastName, String occupation, String email)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public String getEmail() {
        return this.email;
    }
}
