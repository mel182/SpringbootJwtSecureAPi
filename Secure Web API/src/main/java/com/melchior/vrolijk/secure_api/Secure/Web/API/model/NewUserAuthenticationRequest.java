package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This is the new user authentication request model
 * @author Melchior Vrolijk
 */
@ApiModel
public class NewUserAuthenticationRequest
{
    //region Local instances
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
    //endregion

    //region User ID getter and setter
    /**
     * Get new authenticated user ID
     * @return The user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set new authenticated user ID
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

    //region User last name
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

    //region User role getter and setter
    /**
     * Get user role
     * @return The user role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set user role
     * @param role The user role
     */
    public void setRole(String role) {
        this.role = role;
    }
    //endregion
}
