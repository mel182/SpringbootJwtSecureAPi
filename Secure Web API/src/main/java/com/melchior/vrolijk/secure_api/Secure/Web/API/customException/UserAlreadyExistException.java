package com.melchior.vrolijk.secure_api.Secure.Web.API.customException;

public class UserAlreadyExistException extends Exception
{
    private String user_email;

    public UserAlreadyExistException(String email)
    {
        this.user_email = email;
    }

    @Override
    public String toString()
    {
        return String.format("User with e-mail address %s already exist",user_email);
    }
}
