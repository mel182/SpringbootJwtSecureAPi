package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This is the authenticated user model which is used when providing responses containing entire details of a user
 * @author Melchior Vrolijk
 */
@ApiModel
public class AuthenticatedUser
{
    //region Local instances
    @ApiModelProperty(readOnly = true)
    private Long id;

    private String firstName;

    private String lastName;

    private String occupation;

    private String email;

    @ApiModelProperty(readOnly = true)
    private String sessionToken;
    //endregion

    //region ID getter and setter
    /**
     * Get user ID
     * @return The user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set user ID
     * @param id The user ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    //endregion

    //region User first name getter and setter
    /**
     * Get user first name
     * @return The user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set user first name
     * @param firstName The user first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    //endregion

    //region User last name getter and setter
    /**
     * Get user last name
     * @return The user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set user last name
     * @param lastName The user last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //endregion

    //region User occupation getter and setter
    /**
     * Get user occupation
     * @return The user occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Set user occupation
     * @param occupation The user occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    //endregion

    //region User e-mail getter and setter
    /**
     * Get user e-mail
     * @return The user e-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user e-mail
     * @param email The user e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    //region User session token getter and setter
    /**
     * Get user session token
     * @return The user session token
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * Set user session token
     * @param sessionToken The user session token
     */
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
    //endregion
}
