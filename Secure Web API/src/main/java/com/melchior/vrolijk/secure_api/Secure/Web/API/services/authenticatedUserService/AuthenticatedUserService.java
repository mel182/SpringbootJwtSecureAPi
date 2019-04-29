package com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService;

import com.melchior.vrolijk.secure_api.Secure.Web.API.customException.UserAlreadyExistException;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.interfaces.DatabaseTask;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.*;
import com.melchior.vrolijk.secure_api.Secure.Web.API.repository.AuthenticatedUserRepository;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenGenerator;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.converter.Converter.*;

/**
 * This is the authentication user service which handles all task related to authenticated users.
 * It implement {@link DatabaseTask} and {@link UserDetailsService}.
 *
 * @author Melchior Vrolijk
 */
@SuppressWarnings("ALL")
@Service
public class AuthenticatedUserService implements DatabaseTask<AuthenticatedUser,NewUserAuthenticationRequest>, UserDetailsService
{
    //region Local instances
    @Autowired
    AuthenticatedUserRepository repository;

    @Autowired
    JwtTokenGenerator jwtTokenGenerator;
    //endregion


    public void clearAll()
    {
        this.repository.deleteAll();
    }

    //region Save new authenticated user to database
    /**
     * Save authenticated user to database
     * @param newUser The {@link NewUserAuthenticationRequest} instance
     * @return The new {@link AuthenticatedUser}
     * @throws UserAlreadyExistException Throws an {@link UserAlreadyExistException} will be throw if the user already exist in the database.
     */
    @Override
    public AuthenticatedUser save(NewUserAuthenticationRequest newUser) throws UserAlreadyExistException
    {
        if (userExist(newUser.getEmail()))
            throw new UserAlreadyExistException(newUser.getEmail());

        return saveToDB(newUser);
    }
    //endregion

    //region Get an authenticated user based on User ID
    /**
     * Get an authenticated user based on the user ID
     * @param id The user ID
     * @return The corresponding {@link AuthenticatedUser}
     */
    @Override
    public AuthenticatedUser get(long id) {

        AuthenticatedUser authenticatedUser = null;
        if (repository.existsById(id))
        {
            authenticatedUser = convertToAuthenticatedUser(repository.findById(id).get());
        }

        return authenticatedUser;
    }
    //endregion

    //region Get admin based on admin user ID provided
    /**
     * Get admin based on the admin user ID provided
     * @param id The admin user ID
     * @return The corresponding admin based on the user ID provided
     */
    private ResponseUser getAdmin(long id)
    {
        List<UserEntity> userEntities = repository.findAll();

        UserEntity userEntity = userEntities.stream().filter(user -> (user.getId() == id && user.getRole().equals(UserRole.ADMIN.toString())))
                .findFirst().orElse(null);

        return userEntity == null ? null : convertToResponseUser(userEntity);
    }
    //endregion

    //region Get user based on the user ID provided
    /**
     * Get user based on user ID
     * @param id The user ID
     * @return The corresponding user based on the ID provided or {@link java.lang.ref.ReferenceQueue.Null} if user is not found
     */
    private AuthenticatedUser getUser(long id)
    {
        Optional<UserEntity> userFound = repository.findById(id);
        UserEntity userEntities = userFound.orElse(null);

        return userEntities == null ? null : convertToAuthenticatedUser(userEntities);
    }
    //endregion

    //region Get user based on the user ID provided
    /**
     * Get {@link ResponseUser} based on user ID
     * @param id The user ID
     * @return The corresponding user based on the ID provided or {@link java.lang.ref.ReferenceQueue.Null} if user is not found
     */
    private ResponseUser getResponseUser(long id)
    {
        Optional<UserEntity> userFound = repository.findById(id);
        UserEntity userEntities = userFound.orElse(null);

        return userEntities == null ? null : convertToResponseUser(userEntities);
    }
    //endregion

    //region Remove user based on the UserID
    /**
     * Remove user based on the user ID provided
     * @param id The user ID
     * @return Details if the user that has been removed
     */
    public ResponseUser removeUser(long id)
    {
        ResponseUser user = getResponseUser(id);

        if (user != null)
        {
            repository.deleteById(id);
            return user;
        }

        return null;
    }
    //endregion

    //region Update user based on the new user data provided
    /**
     * Update user based on the user data provided
     * @param newUserData The updated data
     * @return The updated user data or null if task failed
     */
    public ResponseUser updateUser(NewUserAuthenticationRequest newUserData)
    {
        if (get(newUserData.getId()) != null)
        {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(newUserData.getId());
            userEntity.setFirstName(newUserData.getFirstName());
            userEntity.setLastName(newUserData.getLastName());
            userEntity.setEmail(newUserData.getEmail());
            userEntity.setOccupation(newUserData.getOccupation());
            userEntity.setUpdated(System.currentTimeMillis());

            repository.save(userEntity);
            return getResponseUser(newUserData.getId());
        }

        return null;
    }
    //endregion

    //region Remove admin based on the admin user ID
    /**
     * Remove admin based on the admin user ID provided
     * @param id The admin user ID
     * @return The admin details that has been removed
     */
    public ResponseUser removeAdmin(long id)
    {
        ResponseUser adminFound = getAdmin(id);

        if (adminFound != null)
        {
            repository.deleteById(adminFound.getId());
            return adminFound;
        }

        return null;
    }
    //endregion

    //region Get all users
    /**
     * Get all user of the database
     * @return The list of authenticated admin/user
     */
    @Override
    public List<AuthenticatedUser> getAll()
    {
        List<UserEntity> userEntities = repository.findAll();

        List<AuthenticatedUser> authenticatedUserList = new ArrayList<>();

        userEntities.forEach((userEntity -> {
             authenticatedUserList.add(convertToAuthenticatedUser(userEntity));
        }));

        return authenticatedUserList;
    }
    //endregion

    //region Get all admins
    /**
     * Get all admins
     * @return The list of admins
     */
    public List<ResponseUser> getAllAdmins()
    {
        List<UserEntity> userEntities = repository.findAll();

        List<ResponseUser> authenticatedUserList = new ArrayList<>();

        userEntities.forEach((userEntity ->
        {
            if (userEntity.getRole().equals(UserRole.ADMIN.toString()))
                authenticatedUserList.add(convertToResponseUser(userEntity));
        }));

        return authenticatedUserList;
    }
    //endregion

    //region Get all users NOT ADMIN
    /**
     * Get all authenticated users Not ADMINS
     * @return The list of authenticated users
     */
    public List<ResponseUser> getAllUsers()
    {
        List<ResponseUser> authenticatedUserList = new ArrayList<>();

        List<UserEntity> userEntities = repository.findAll();

        userEntities.forEach((userEntity ->
        {
            if (userEntity != null)
            {
                if (userEntity.getRole().equals(UserRole.USER.toString()))
                    authenticatedUserList.add(convertToResponseUser(userEntity));
            }
        }));

        return authenticatedUserList;
    }
    //endregion

    //region Get user based on e-mail address provided
    /**
     * Get user based on the email provided
     * @param email The user email address
     * @return The corresponding user
     */
    public AuthenticatedUser getUser(String email)
    {
        return getAll().stream().filter(authenticatedUser -> authenticatedUser.getEmail().equals(email))
                .findFirst().orElse(null);
    }
    //endregion

    //region Login
    /**
     * Authenticated based on the username (e-mail) and password provided
     * @param authenticationRequest The {@link AuthenticationRequest} containing the login credentials
     * @return The user details or {@link javax.validation.constraints.Null} in case it failed
     */
    public AuthenticatedUser login(AuthenticationRequest authenticationRequest)
    {
        String passwordProvidedHashed = Hashing.hash(authenticationRequest.getPassword());

        List<UserEntity> userEntities = repository.findAll();

        UserEntity userEntity = userEntities.stream().filter(user -> user.getEmail().equals(authenticationRequest.getEmail()))
                .findFirst().orElse(null);

        if (userEntity != null)
        {
            if (userEntity.getPassword().equals(passwordProvidedHashed))
            {
                String token = jwtTokenGenerator.createToken(userEntity);
                AuthenticatedUser authenticatedUser = convertToAuthenticatedUser(userEntity);
                authenticatedUser.setSessionToken(token);

                return authenticatedUser;
            }
        }

        return null;
    }
    //endregion

    //region Delete an user based on the id provided
    /**
     * Delete an authenticated user based on the user ID provided
     * @param id The user ID
     * @return The authenticated user details
     */
    @Override
    public AuthenticatedUser delete(long id)
    {
        if (repository.existsById(id))
        {
            AuthenticatedUser deletedUser = get(id);

            if (deletedUser != null)
            {
                repository.deleteById(id);
                return deletedUser;
            }
        }

        return null;
    }
    //endregion

    //region Check if user exist
    /**
     * Determine if a user already exist in database
     * @param email The user email address
     * @return 'True' if user already exist or 'False' if user does not exist
     */
    private boolean userExist(String email)
    {
        return repository.findByEmail(email).isPresent();
    }
    //endregion

    //region Save data to datbase
    /**
     * Save data to database
     * @param newUser The user data
     * @return The user details
     */
    private AuthenticatedUser saveToDB(NewUserAuthenticationRequest newUser)
    {
        UserEntity userEntity = convertToUserEntity(newUser);
        repository.save(userEntity);
        return getUser(newUser.getEmail());
    }
    //endregion

    //region Load user by user name (email)
    /**
     * Load user by user name (email)
     * @param email The user e-mail
     * @return The {@link UserDetails}'instance
     * @throws UsernameNotFoundException Throw if email is not found in the database
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        List<UserEntity> userEntities = repository.findAll();

        UserEntity userEntity = userEntities.stream().filter(user -> user.getEmail().equals(email))
                .findFirst().orElse(null);

        if (userEntity != null)
        {
            return new CustomAuthenticatedUserDetail(userEntity.getEmail(),userEntity.getPassword(),userEntity.isAccountEnabled());
        }

        return null;
    }
    //endregion
}
