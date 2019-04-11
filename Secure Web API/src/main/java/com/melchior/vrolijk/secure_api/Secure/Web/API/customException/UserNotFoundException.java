package com.melchior.vrolijk.secure_api.Secure.Web.API.customException;

public class UserNotFoundException extends Exception
{
    Long user_id;


    public UserNotFoundException(long user_id)
    {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserNotFoundException: unable to find user with ID: '"+user_id+"'";
    }
}
