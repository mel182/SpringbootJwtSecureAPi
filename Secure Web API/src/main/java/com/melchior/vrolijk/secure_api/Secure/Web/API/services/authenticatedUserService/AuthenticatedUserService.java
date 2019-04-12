package com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService;

import com.melchior.vrolijk.secure_api.Secure.Web.API.customException.UserAlreadyExistException;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.interfaces.DatabaseTask;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticatedUser;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.CustomAuthenticatedUserDetail;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
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

@Service
public class AuthenticatedUserService implements DatabaseTask<AuthenticatedUser,NewUserAuthenticationRequest>, UserDetailsService
{
    @Autowired
    AuthenticatedUserRepository repository;

    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @Override
    public AuthenticatedUser save(NewUserAuthenticationRequest newUser) throws UserAlreadyExistException
    {
        if (userExist(newUser.getEmail()))
            throw new UserAlreadyExistException(newUser.getEmail());

        return saveToDB(newUser);
    }


    @Override
    public AuthenticatedUser get(long id) {

        AuthenticatedUser authenticatedUser = null;
        if (repository.existsById(id))
        {
            authenticatedUser = convertToAuthenticatedUser(repository.findById(id).get());
        }

        return authenticatedUser;
    }

    private AuthenticatedUser getAdmin(long id)
    {
        List<UserEntity> userEntities = repository.findAll();

        UserEntity userEntity = userEntities.stream().filter(user -> (user.getId() == id && user.getRole().equals(UserRole.ADMIN.toString())))
                .findFirst().orElse(null);

        return userEntity == null ? null : convertToAuthenticatedUser(userEntity);
    }

    private AuthenticatedUser getUser(long id)
    {
        Optional<UserEntity> userFound = repository.findById(id);
        UserEntity userEntities = userFound.orElse(null);

        return userEntities == null ? null : convertToAuthenticatedUser(userEntities);
    }

    public AuthenticatedUser removeUser(long id)
    {
        AuthenticatedUser user = getUser(id);

        if (user != null)
        {
            repository.deleteById(id);
            return user;
        }

        return null;
    }

    public AuthenticatedUser updateUser(NewUserAuthenticationRequest newUserData)
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
            return getUser(newUserData.getId());
        }

        return null;
    }

    public AuthenticatedUser removeAdmin(long id)
    {
        AuthenticatedUser adminFound = getAdmin(id);

        if (adminFound != null)
        {
            repository.deleteById(adminFound.getId());
            return adminFound;
        }

        return null;
    }

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

    public List<AuthenticatedUser> getAllAdmins()
    {
        List<UserEntity> userEntities = repository.findAll();

        List<AuthenticatedUser> authenticatedUserList = new ArrayList<>();

        userEntities.forEach((userEntity ->
        {
            if (userEntity.getRole().equals(UserRole.ADMIN.toString()))
                authenticatedUserList.add(convertToAuthenticatedUser(userEntity));
        }));

        return authenticatedUserList;
    }

    public List<AuthenticatedUser> getAllUsers()
    {
        List<AuthenticatedUser> authenticatedUserList = new ArrayList<>();

        List<UserEntity> userEntities = repository.findAll();

        userEntities.forEach((userEntity ->
        {
            if (userEntity.getRole().equals(UserRole.USER.toString()))
                authenticatedUserList.add(convertToAuthenticatedUser(userEntity));
        }));

        return authenticatedUserList;
    }

    public AuthenticatedUser getUser(String email)
    {
        return getAll().stream().filter(authenticatedUser -> authenticatedUser.getEmail().equals(email))
                .findFirst().orElse(null);
    }

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

    private boolean userExist(String email)
    {
        return repository.findByEmail(email).isPresent();
    }

    private AuthenticatedUser saveToDB(NewUserAuthenticationRequest newUser)
    {
        UserEntity userEntity = convertToUserEntity(newUser);
        return convertToAuthenticatedUser(repository.save(userEntity));
    }


    private UserEntity convertToUserEntity(NewUserAuthenticationRequest newUser)
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

    private AuthenticatedUser convertToAuthenticatedUser(UserEntity userEntity)
    {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setId(userEntity.getId());
        authenticatedUser.setFirstName(userEntity.getFirstName());
        authenticatedUser.setLastName(userEntity.getLastName());
        authenticatedUser.setEmail(userEntity.getEmail());
        authenticatedUser.setOccupation(userEntity.getOccupation());

        return authenticatedUser;
    }

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
}
