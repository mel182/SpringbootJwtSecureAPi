package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomAuthenticatedUserDetail implements UserDetails
{
    private String password;
    private String userName;
    private boolean isEnabled;

    public CustomAuthenticatedUserDetail(String userName, String password,
                                         boolean isEnabled)
    {
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
