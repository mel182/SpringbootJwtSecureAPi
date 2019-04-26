package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * This is the custom {@link UserDetails} class used for authenticating user over the security layer of the API
 * @author Melchior Vrolijk
 */
public class CustomAuthenticatedUserDetail implements UserDetails
{
    //region Local instances
    private String password;
    private String userName;
    private boolean isEnabled;
    //endregion

    //region Constructor
    public CustomAuthenticatedUserDetail(String userName, String password, boolean isEnabled)
    {
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
    }
    //endregion

    //region UserDetails override methods
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    //endregion
}
