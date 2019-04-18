package com.melchior.vrolijk.secure_api.Secure.Web.API.demoObject;

import com.melchior.vrolijk.secure_api.Secure.Web.API.converter.Converter;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.Hashing;

public class DemoObject
{
    private static final String TEST_EMAIL = "test@test.com";
    private static final String TEST_PASSWORD = "passwordTest";
    private static final String ROOT_USER_PASSWORD = "5dcad19b31396b729a484bd84b39a0b268511ba11b9359d37051c5bd02a0db7f0dd96296165b63f73acb6f0146fc5cab1ac90f050c86085c0815a88cd103e2e0";
    private static final String ROOT_USER_NAME = "admin@application.com";
    private static final String ROOT_USER_FIRST_NAME = "Root";
    private static final String ROOT_USER_LAST_NAME = "User";
    private static final String ROOT_USER_OCCUPATION = "admin@application.com";
    public static long DEFAUL_USER_ID = -1;

    public static NewUserAuthenticationRequest createNewUserAuthentication()
    {
        NewUserAuthenticationRequest newUserAuthenticationRequest = new NewUserAuthenticationRequest();
        newUserAuthenticationRequest.setId(1L);
        newUserAuthenticationRequest.setFirstName("TestUser");
        newUserAuthenticationRequest.setLastName("LTestUser");
        newUserAuthenticationRequest.setOccupation("TestOccupation");
        newUserAuthenticationRequest.setEmail(TEST_EMAIL);
        newUserAuthenticationRequest.setPassword(TEST_PASSWORD);
        newUserAuthenticationRequest.setRole("USER");

        return newUserAuthenticationRequest;
    }

    public static NewUserAuthenticationRequest createNewUpdatedAuthenticationUser()
    {
        AuthenticationRequest authenticationRequest = createAuthenticationRequestTest();

        NewUserAuthenticationRequest newUserAuthenticationRequest = new NewUserAuthenticationRequest();
        newUserAuthenticationRequest.setId(1L);
        newUserAuthenticationRequest.setFirstName("TestUser1");
        newUserAuthenticationRequest.setLastName("LTestUser1");
        newUserAuthenticationRequest.setOccupation("TestOccupation1");
        newUserAuthenticationRequest.setEmail(authenticationRequest.getEmail());
        newUserAuthenticationRequest.setPassword(authenticationRequest.getPassword());
        newUserAuthenticationRequest.setRole("USER");

        return newUserAuthenticationRequest;
    }

    public static NewUserAuthenticationRequest createNewAuthenticationRequestUserTestObject()
    {
        AuthenticationRequest authenticationRequest = createAuthenticationRequestTest();

        NewUserAuthenticationRequest newUserAuthenticationRequest = new NewUserAuthenticationRequest();
        newUserAuthenticationRequest.setId(1L);
        newUserAuthenticationRequest.setFirstName("TestUser");
        newUserAuthenticationRequest.setLastName("LTestUser");
        newUserAuthenticationRequest.setOccupation("TestOccupation");
        newUserAuthenticationRequest.setEmail(authenticationRequest.getEmail());
        newUserAuthenticationRequest.setPassword(authenticationRequest.getPassword());
        newUserAuthenticationRequest.setRole("USER");

        return newUserAuthenticationRequest;
    }

    public static AuthenticationRequest createAuthenticationRequestTest()
    {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(TEST_EMAIL);
        authenticationRequest.setPassword(TEST_PASSWORD);

        return authenticationRequest;
    }

    public static UserEntity createUserEntity()
    {
        return Converter.convertToUserEntity(createNewAuthenticationRequestUserTestObject());
    }

    public static UserEntity createRootUserEntity()
    {
        UserEntity rootUser = new UserEntity();
        rootUser.setEmail(ROOT_USER_NAME);
        rootUser.setPassword(Hashing.hash(ROOT_USER_PASSWORD));
        rootUser.setFirstName(ROOT_USER_FIRST_NAME);
        rootUser.setLastName(ROOT_USER_LAST_NAME);
        rootUser.setOccupation(ROOT_USER_OCCUPATION);
        rootUser.setRole(UserRole.ROOT.toString());
        rootUser.setCreated(System.currentTimeMillis());
        rootUser.setUpdated(System.currentTimeMillis());
        rootUser.setAccountLocked(false);

        return rootUser;
    }
}
