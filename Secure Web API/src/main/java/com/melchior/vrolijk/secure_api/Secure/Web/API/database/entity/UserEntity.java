package com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel
@Entity
@Table(name="USERS")
/***
 * <p>This is the user entity database model.</p>
 * <p>Note: all properties are read-only when exposes to clients</p>
 *
 * @author Melchior Vrolijk
 * @date 2019/4/8
 */
public class UserEntity
{
    //region Model properties
    @ApiModelProperty(readOnly = true)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ApiModelProperty(readOnly = true)
    @Column(name="FIRST_NAME")
    private String firstName;

    @ApiModelProperty(readOnly = true)
    @Column(name="LAST_NAME")
    private String lastName;

    @ApiModelProperty(readOnly = true)
    @Column(name="OCCUPATION")
    private String occupation;

    @ApiModelProperty(readOnly = true)
    @Column(name="EMAIL")
    private String email;

    @ApiModelProperty(readOnly = true)
    @Column(name="PASSWORD")
    private String password;

    @ApiModelProperty(readOnly = true)
    @Column(name="ACCOUNT_LOCKED")
    private boolean accountLocked;

    @ApiModelProperty(readOnly = true)
    @Column(name="ACCOUNT_NON_LOCKED")
    private String accountNonLocked;

    @ApiModelProperty(readOnly = true)
    @Column(name="ACCOUNT_ENABLED")
    private boolean accountEnabled;

    @ApiModelProperty(readOnly = true)
    @Column(name="ROLE")
    private String role;

    @ApiModelProperty(readOnly = true)
    @Column(name="CREATED")
    private Long created;

    @ApiModelProperty(readOnly = true)
    @Column(name="UPDATED")
    private Long updated;
    //endregion

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(String accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
}
