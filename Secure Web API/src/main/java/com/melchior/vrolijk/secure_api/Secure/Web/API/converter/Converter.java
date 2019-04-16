package com.melchior.vrolijk.secure_api.Secure.Web.API.converter;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticatedUser;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.Hashing;

public class Converter
{
    //region Convert new user authenticated request to user entity
    /**
     * Convert {@link NewUserAuthenticationRequest} to a {@link UserEntity} instance
     * @param newUser The {@link NewUserAuthenticationRequest} instance
     * @return The {@link UserEntity}
     */
    public static UserEntity convertToUserEntity(NewUserAuthenticationRequest newUser)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(newUser.getFirstName());
        userEntity.setLastName(newUser.getLastName());
        userEntity.setEmail(newUser.getEmail());
        userEntity.setOccupation(newUser.getOccupation());
        userEntity.setPassword(Hashing.hash(newUser.getPassword()));
        userEntity.setCreated(System.currentTimeMillis());
        userEntity.setRole(newUser.getRole());
        userEntity.setUpdated(System.currentTimeMillis());

        return userEntity;
    }
    //endregion

    //region Convert entity user to authenticated user
    /**
     * Convert {@link UserEntity} to {@link AuthenticatedUser}
     * @param userEntity The user entity
     * @return The {@link AuthenticatedUser}
     */
    public static AuthenticatedUser convertToAuthenticatedUser(UserEntity userEntity)
    {
        System.out.println("User entity: "+userEntity);
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setId(userEntity.getId());
        authenticatedUser.setFirstName(userEntity.getFirstName());
        authenticatedUser.setLastName(userEntity.getLastName());
        authenticatedUser.setEmail(userEntity.getEmail());
        authenticatedUser.setOccupation(userEntity.getOccupation());

        return authenticatedUser;
    }
    //endregion
}
