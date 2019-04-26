package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This is the response user model
 * @author Melchior Vrolijk
 */
@ApiModel
public class ResponseUser
{
    //region Local instances
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
    //endregion

    //region Constructor
    /**
     * Constructor
     * @param id The user ID
     * @param firstName The user first name
     * @param lastName The user last name
     * @param occupation The user occupation
     * @param email The user e-mail
     */
    public ResponseUser(Long id, String firstName, String lastName, String occupation, String email)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.email = email;
    }
    //endregion

    //region User ID getter
    /**
     * Get user ID
     * @return The user ID
     */
    public Long getId() {
        return this.id;
    }
    //endregion

    //region User first name getter
    /**
     * Set the user ID
     * @return The user ID
     */
    public String getFirstName() {
        return this.firstName;
    }
    //endregion

    //region User last name getter
    /**
     * Get user last name
     * @return The user last name
     */
    public String getLastName() {
        return this.lastName;
    }
    //endregion

    //region User occupation getter
    /**
     * Get user occupation
     * @return The user occupation
     */
    public String getOccupation() {
        return this.occupation;
    }
    //endregion

    //region User e-mail getter
    /**
     * Get user e-mail
     * @return The user e-mail
     */
    public String getEmail() {
        return this.email;
    }
    //endregion
}
