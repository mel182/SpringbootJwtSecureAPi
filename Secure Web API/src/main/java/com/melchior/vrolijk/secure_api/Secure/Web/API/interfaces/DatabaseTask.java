package com.melchior.vrolijk.secure_api.Secure.Web.API.interfaces;

import com.melchior.vrolijk.secure_api.Secure.Web.API.customException.UserAlreadyExistException;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface DatabaseTask<T,U>
{
    T save(U object) throws UserAlreadyExistException;
    T get(long id);
    List<T> getAll();
    T delete(long id);
}
