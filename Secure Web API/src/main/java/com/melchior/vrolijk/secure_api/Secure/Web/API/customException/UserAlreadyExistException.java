package com.melchior.vrolijk.secure_api.Secure.Web.API.customException;

/**
 * This is the custom exception used when user already exist in DB.
 *
 * @author Melchior Vrolijk
 */
public class UserAlreadyExistException extends Exception
{
    private String user_email;

    /**
     * Constructor
     * @param email The target user e-mail
     */
    public UserAlreadyExistException(String email)
    {
        this.user_email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return String.format("User with e-mail address %s already exist",user_email);
    }
}
